package analysis;

import com.silita.biaodaa.common.Constant;
import com.silita.biaodaa.disruptor.DisruptorOperator;
import com.silita.biaodaa.service.CommonService;
import com.silita.biaodaa.service.ConfigTest;
import com.silita.biaodaa.service.TestService;
import com.silita.biaodaa.task.AnalysisTask;
import com.snatch.model.EsNotice;
import com.snatch.model.Notice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangxiahui on 18/3/20.
 */
public class NoticeAnalysisTest extends ConfigTest {
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
        String title ="隆阳区第一中学女生宿舍楼建设项目";
        title =null;
        String tbName = "test.dev_zhongbiao_20181024_all";//uat_zhongbiao_20181108_all
        //dev_zhongbiao_20181031_all dev_zhongbiao_20181024_all  uat_zhongbiao_20181108_all
//        title = "河北建投新能源有限公司生产及技术改造工程中标候选人公示";dev_zhaobiao_20181024_all
         Map argMap = new HashMap();
        argMap.put("tbName",tbName);
        argMap.put("title",title);
        argMap.put("redisId",1448087);
        testService.pushCustomRedisNotice(argMap);
        String s = "(<.{0,7}>|^[。，,;；：: )）“”\"]+|((<.?[^>]?p>|[。，,;；：: )）“”\"])([\\S\\W\\D\\d]){0,2}$))";
//        testService.pushCustomRedisSec(tbName,0,100,title,null);
//        testService.pushCustomRedisNotice("test.dev_zhongbiao_2018_all",null,null);dev_zhongbiao_20181024_all
//        analyzeHandler();
//        dev_zhaobiao_20181024_all  dev_zhongbiao_2018_all
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
