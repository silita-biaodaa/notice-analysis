package nlp.ali;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import nlp.Sender;
import org.junit.Test;

public class AliTest  {

    @Test
    public void testRequest(){
        // nlp.cn-shanghai.aliyuncs.com/nlp/api/ie/contract  信息收取接口
        // http://nlp.cn-shanghai.aliyuncs.com/nlp/api/entity/ecommerce 命名实体
        // http://nlp.cn-shanghai.aliyuncs.com/nlp/api/wordpos/general  词性标注
        // /nlp/api/wordpos/
        String serviceURL = "http://nlp.cn-shanghai.aliyuncs.com/nlp/api/entity/ecommerce";
        String accessKeyId = "LTAIVLTf1eLK9MWJ";
        String accessKeySecret = "Ti9oFBqhbVXu3un5AnpR908SObVAAe";
//        StringBuilder content=new StringBuilder("<p>受古丈县教育和体育局的委托，湘西自治州广信招标代理有限公司对\"古丈县义教均衡发展计算机教室及班班通设备采购项目\"进行公开招标采购，采购项目于2018年7月31日10:30时，在湘西自治州公共资源交易中心进行了开标、评标活动，现将中标公告如下：</p><p>一、采购项目情况</p><p>1、采购项目名称：古丈县义教均衡发展计算机教室及班班通设备采购项目</p><p>2、预算：班班通设备（品目二）175万元；</p><p>3、采购计划编号：古财采计[2018]35号；</p><p>委托代理编号：GXGZ[2018]-028；</p><p>4、招标信息发布日期：2018年7月10日。</p><p>二、中标人候选名单</p><table class='table table-bordered'><tbody><tr><td><p>序号</p></td><td><p>投标人</p></td><td><p>投标报价</p></td><td><p>政策功能优惠扣除后的报价</p></td><td><p>投标人得分</p></td><td><p>候选名单</p></td></tr><tr><td><p>1</p></td><td><p>常德市新方教学仪器设备有限公司</p></td><td><p>1739616.00</p></td><td><p>/</p></td><td><p>96.59</p></td><td><p>第一名</p></td></tr><tr><td><p>2</p></td><td><p>湘西自治州联创信息产业发展有限公司</p></td><td><p>1687250.00</p></td><td><p>/</p></td><td><p>82.51</p></td><td><p>第二名</p></td></tr><tr><td><p>3</p></td><td><p>长沙全丰教育设备有限公司</p></td><td><p>1746680.00</p></td><td><p>/</p></td><td><p>77.98</p></td><td><p>第三名</p></td></tr></tbody></table><p>三、评标委员会成员名单</p><p>田晓霆、龚晓阳、甄凤其、陈胜梅、田义军</p><p>四、中标供应商名称及中标金额</p><p>1、中标供应商名称：常德市新方教学仪器设备有限公司；</p><p>2、中标金额：1739616.00整。</p><p>3、中标供应商地址：常德市武陵区芷兰街道办事处芷荷社区柳叶路18号（新世纪花园3栋117号）</p><p>五、采购单位联系人姓名、电话</p><p>采购单位：古丈县教育和体育局</p><p>联系人：张先生</p><p>电话：18907439725</p><p>地址：湖南省古丈县城内</p><p>六、代理机构联系人姓名、电话</p><p>采购代理机构名称：湘西自治州广信招标代理有限公司</p><p>地址：吉首市乾州新区水利大厦2单元2703室</p><p>联系人：周春琴、葛文英</p><p>电话：0743-8521508</p>");
//        String c= content.toString().replaceAll("(<.*?>)","");
//        c= c.replaceAll("\"","'");
        String c="铁路施工工程总承包企业二级";

        String postBody =
                "{" +
                        "\"type\":\"full\"," +
                        "\"text\":\""+c+"\"" +
                 "}";
        System.out.println(c+"\n"+postBody);
        String result =  Sender.sendPost(serviceURL, postBody, accessKeyId, accessKeySecret);
        JSONObject obj = JSON.parseObject(result);
        System.out.println(result);
//        System.out.println( "命名实体："+obj.getString("extracted_fields"));
    }
}
