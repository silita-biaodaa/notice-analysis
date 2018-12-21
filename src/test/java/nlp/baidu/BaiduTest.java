package nlp.baidu;

import com.alibaba.fastjson.JSONObject;
import nlp.Sender;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dh on 2018/12/14.
 */
public class BaiduTest {

//    /**
//     * @BeforeClass:这个注解表示这个方法会在所有测试方法执行之前执行
//     */
//    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//        System.out.println("######this is before class");
//    }
//
//    /**
//     * @AfterClass:这个注解表示这个方法会在所有方法执行完毕之后执行，通常用来释放资源
//     */
//    @AfterClass
//    public static void tearDownAfterClass() throws Exception {
//        System.out.println("this is after class");
//    }
//
//    /**
//     * @Before:这个注解表示这个方法会在每个测试方法执行之前执行一次
//     * 有多少个测试方法就会执行多少次
//     */
//    @Before
//    public void setUp(){
//        System.out.println("before method");
//    }
//
//    /**
//     * @After:这个注解表示这个方法会在每个测试方法执行之后执行一次
//     * 有多少个测试方法就会执行多少次
//     */
//    @After
//    public void tearDown(){
//        System.out.println("~~~after method");
//    }

    @Test
    public void initAccessToken(){
        String url="https://aip.baidubce.com/oauth/2.0/token?charset=UTF-8" +
                "grant_type=client_credentials&client_id=RzDBrqIB5DfyZq2oA7cWIkKX&client_secret=YCOlv7td8bqpGqrZfXmpVCq3iKf7imYt&";
        String body="";
        String res = Sender.sendPost(url,body,"UTF-8");
        System.out.println(res);

        //{"refresh_token":"25.34a51da9433c8b2be2a90eca75ad25ed.315360000.1860140190.282335-15164543","expires_in":2592000,"session_key":"9mzdWBWXHckK79hRYLNhG82uhLCXqn5idOr9rbQhOJPEALFVUpjV4UrrwXYKa5rBC40ksNohZt6pADFo0XHAFq04VS1Hiw==","access_token":"24.ce14252c9e4b64d6edf1b205f9d4de0f.2592000.1547372190.282335-15164543","scope":"public nlp_simnet nlp_wordemb nlp_comtag nlp_dnnlm_cn brain_nlp_lexer brain_all_scope brain_nlp_comment_tag brain_nlp_dnnlm_cn brain_nlp_word_emb_vec brain_nlp_word_emb_sim brain_nlp_sentiment_classify brain_nlp_simnet brain_nlp_depparser brain_nlp_wordembedding brain_nlp_dnnlm_cn_legacy brain_nlp_simnet_legacy brain_nlp_comment_tag_legacy brain_nlp_lexer_custom brain_nlp_keyword brain_nlp_topic brain_nlp_emotion brain_nlp_comment_tag_custom wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test\u6743\u9650 vis-classify_flower lpq_\u5f00\u653e cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base iop_autocar oauth_tp_app smartapp_smart_game_openapi oauth_sessionkey smartapp_swanid_verify","session_secret":"ffe4c35262af9b3501cf774e80c6855d"}

    }

    public String access_token="24.055e3413ea4c6cbab453713c9513546f.2592000.1547604952.282335-15164543";

    /**
     * 词法分析
     */
    @Test
    public void testlexer(){
        String url="https://aip.baidubce.com/rpc/2.0/nlp/v1/lexer?charset=UTF-8&access_token="+access_token;
        //  怀化市水利电力工程建设总承包有限公司 中国电建集团中南勘测设计研究院有限公司
        Map param = new HashMap();
        param.put("text","怀化市水利电力工程建设总承包有限公司常德分公司");
        String body = JSONObject.toJSONString(param);
        System.out.println(body);
        String res = Sender.sendPost(url,body,"utf-8");
        System.out.println(res);
    }

    /**
     * 依存句法分析
     */
    @Test
    public void test_depparser(){
        String url="https://aip.baidubce.com/rpc/2.0/nlp/v1/depparser?charset=UTF-8&access_token="+access_token;
        Map<String,String> param = new HashMap();
        param.put("text","五、开标地点、成交供应商名称、地址和成交金额\n" +
                "\n" +
                "开标地点：娄底市公共资源交易中心\n" +
                "\n" +
                "成交供应商名称：深圳市盾牌安保科技有限公司\n" +
                "\n" +
                "地址：深圳市福田区福田街道福山社区滨河大道5022号联合广场A座29层05室\n" +
                "\n" +
                "成交金额：540000.00元");
        param.put("mode","1");
        String body = JSONObject.toJSONString(param);
        System.out.println(body);
        String res = Sender.sendPost(url,body,"utf-8");
        System.out.println(res);
    }


    @Test
    public void test_word_emb_vec(){
        String url="https://aip.baidubce.com/rpc/2.0/nlp/v2/word_emb_vec?charset=UTF-8&access_token="+access_token;
        Map<String,String> param = new HashMap();
        param.put("word","红米");
        String body = JSONObject.toJSONString(param);
        System.out.println(body);
        String res = Sender.sendPost(url,body,"utf-8");
        System.out.println(res);
    }

    /**
     * 中文DNN语言模型接口用于输出切词结果并给出每个词在句子中的概率值,判断一句话是否符合语言表达习惯。
     *
     */
    @Test
    public void test_dnn(){
        String url="https://aip.baidubce.com/rpc/2.0/nlp/v2/dnnlm_cn?charset=UTF-8&access_token="+access_token;
        Map<String,String> param = new HashMap();
        param.put("text","湖南巴陵水利水电建筑工程有限公司");
        String body = JSONObject.toJSONString(param);
        System.out.println(body);
        String res = Sender.sendPost(url,body,"utf-8");
        System.out.println(res);
    }


    /**
     * 词义相似度
     * (0,1]分数越大越相似
     */
    @Test
    public void test_word_sim(){
        String url="https://aip.baidubce.com/rpc/2.0/nlp/v2/word_emb_sim?charset=UTF-8&access_token="+access_token;
        Map<String,String> param = new HashMap();
        param.put("word_1","资质");
        param.put("word_2","证书");
        String body = JSONObject.toJSONString(param);
        System.out.println(body);
        String res = Sender.sendPost(url,body,"utf-8");
        System.out.println(res);
    }

    /**
     * 短文本相识度
     * 相似度结果取值(0,1]，分数越高说明相似度越高
     */
    @Test
    public void test_simnet(){
        String url="https://aip.baidubce.com/rpc/2.0/nlp/v2/simnet?charset=UTF-8&access_token="+access_token;
        Map<String,String> param = new HashMap();
        param.put("text_1","公路总承包企业一级");
        param.put("text_2","冶炼工程总承包一级");
        param.put("model","CNN");//默认为"BOW"，可选"BOW"、"CNN"与"GRNN"
        String body = JSONObject.toJSONString(param);
        System.out.println(body);
        String res = Sender.sendPost(url,body,"utf-8");
        System.out.println(res);
    }

    /**
     * 评论观点抽取
     *
     */
    @Test
    public void test_comment_tag(){
        String url="https://aip.baidubce.com/rpc/2.0/nlp/v2/comment_tag?charset=UTF-8&access_token="+access_token;
        Map<String,Object> param = new HashMap();
        param.put("text","公路总承包企业一级");
        param.put("type",9);
        String body = JSONObject.toJSONString(param);
        System.out.println(body);
        String res = Sender.sendPost(url,body,"utf-8");
        System.out.println(res);
    }

}
