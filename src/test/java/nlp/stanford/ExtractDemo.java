//package nlp.stanford;
//
//import edu.stanford.nlp.ie.AbstractSequenceClassifier;
//import edu.stanford.nlp.ie.crf.CRFClassifier;
//import edu.stanford.nlp.ling.CoreLabel;
//
//import java.util.Properties;
//
//public class ExtractDemo {
//    private static AbstractSequenceClassifier<CoreLabel> ner;
//    public ExtractDemo() {
//        InitNer();
//    }
//    public void InitNer() {
//        String serializedClassifier = "edu/stanford/nlp/models/ner/chinese.misc.distsim.crf.ser.gz"; // chinese.misc.distsim.crf.ser.gz
//        if (ner == null) {
//            ner = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
//        }
//    }
//
//    public String doNer(String sent) {
//        return ner.classifyWithInlineXML(sent);
//    }
//
//    public static void main(String args[]) {
//        String str = "衡阳市石鼓区环城北路101市住建局6楼会议室，本次招标将于上述投标截止的同一时间、同一地点公开开标";
//        ExtractDemo extractDemo = new ExtractDemo();
//
//
//        String doSegment = doSegment(str);
//        System.out.println(doSegment);
//
//        System.out.println(extractDemo.doNer(doSegment));
//        System.out.println("Complete!");
//    }
//
//    public static CRFClassifier<CoreLabel> segmenter;
//    static {
//// 设置一些初始化参数
//        Properties props = new Properties();
//        props.setProperty("sighanCorporaDict", "edu/stanford/nlp/models/segmenter/chinese");
//        props.setProperty("serDictionary", "edu/stanford/nlp/models/segmenter/chinese/dict-chris6.ser.gz");
//        props.setProperty("inputEncoding", "UTF-8");
//        props.setProperty("sighanPostProcessing", "true");
//        segmenter = new CRFClassifier<CoreLabel>(props);
//        segmenter.loadClassifierNoExceptions("edu/stanford/nlp/models/segmenter/chinese/ctb.gz", props);
//        segmenter.flags.setProperties(props);
//    }
//
//    public static String doSegment(String sent) {
//        String[] strs = (String[]) segmenter.segmentString(sent).toArray();
//        StringBuffer buf = new StringBuffer();
//        for (String s : strs) {
//            buf.append(s + " ");
//        }
//        System.out.println("segmented res: " + buf.toString());
//        return buf.toString();
//    }
//
//}
