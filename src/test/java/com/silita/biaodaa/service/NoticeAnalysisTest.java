package com.silita.biaodaa.service;

import com.silita.biaodaa.common.Constant;
import com.silita.biaodaa.disruptor.DisruptorOperator;
import com.silita.biaodaa.task.AnalysisTask;
import com.snatch.model.EsNotice;
import com.snatch.model.Notice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangxiahui on 18/3/20.
 */
public class NoticeAnalysisTest extends ConfigTest  {


    @Autowired
    TestService testService;

    @Autowired
    DisruptorOperator disruptorOperator;

    @Autowired
    AnalysisTask analysisTask;

    @Autowired
    @Qualifier("jedisTemplate")
    RedisTemplate redisTemplate;


    /**
     *加载自定义表中的解析任务
     *支持大批量数据，分批导入
     */
    @Test
    public void pushCustomRedisNotice() {
        //"洪江市德坤矿业贸易有限公司污染场地修复EPC项目招标公告", 两栋原烟仓库喷淋系统采购项目招标公告
        //西湖山片区开发游步道项目
        //石门县人民医院北扩工程
        //testService.pushCustomRedisNotice("test.hunan","石门");
        //grunt 新民市2015年度农业综合开发土地治理存量资金项目第一标段、第二标段、第三标段施工 \
        // "嘉禾县2018年普通公路安防工程施工招标公告test"
        //华联社区棚户区改造二期项目2#3#楼F中标候选人公示
        //title like '%鼎城区韩公渡镇改扩翻建配套设施建设项目招标公告%'
        //"华联社区棚户区改造二期项目2#3#楼"
//        testService.pushCustomRedisNotice("test.test_hunan_repeat",null);导入湖南样本数据
        //新化县上渡办事处中心学校（中心小学校区）弱电系统及教学办公设备采购更正公告  宁乡市双江口镇草溪渔场至千龙湖公路改造项目施工澄清公告
//        testService.pushCustomRedisNotice("test.quanguo918",null,null);//导入全国样本数据
//        testService.pushCustomRedisSec("test.liaon",0,100,null,null);葫芦岛市南票渔山新区小区围墙及门卫工程施工
        testService.pushCustomRedisNotice("test.liaon",null,null);
//        analyzeHandler();
    }

    @Test
    public void testAnalysisProbe(){
        Constant.IS_DEBUG=true;
        List<Notice> list = testService.debugNoticeList("test.notice","绥阳县2018年新增千亿斤粮食产能规划田间工程建设项目（平坝村）中标公示");
        for (Notice n:list) {
            EsNotice esNotice = AnalysisTask.noticeToEsNotice(n);
            Map result = testService.analysisProbe(esNotice);
            if(result!=null && result.keySet().size()>0){
                Iterator ite = result.keySet().iterator();
                while(ite.hasNext()){
                    String key = ite.next().toString();
                    String val = result.get(key).toString();
                    System.out.println(key+"###"+val);
                }
            }

        }
    }


    /**
     *加载test.notice中的解析任务
     *不支持大批量数据
     */
    @Test
    public void pushRedisNotice(){
        testService.pushRedisNotice();
    }

    /**
     *加载test.hunan_notice中的解析任务
     *支持大批量数据，分批导入
     */
    @Test
    public void pushHunanRedisNotice() {
        testService.pushHunanRedisNotice();
    }


    /**
     * 测试解析任务,从redis获取
     */
    @Test
    public void analyzeHandler(){
        disruptorOperator.start();
        Notice notice = null ;

        while (true){
            try {
                try{
                    notice = (Notice) redisTemplate.opsForList().leftPop("liuqi",0, TimeUnit.SECONDS);
                    EsNotice esNotice = analysisTask.noticeToEsNotice(notice);
                    disruptorOperator.publish(esNotice);
                }finally{
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    CommonService commonService;

    @Test
    public void TestRegexClean(){
        commonService.queryRegexMapByField("applyAddress");
        commonService.cleanRegexCache();
        commonService.queryRegexMapByField("applyAddress");
    }
}
