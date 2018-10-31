//package nlp.stanford;
//
//import edu.stanford.nlp.ie.AbstractSequenceClassifier;
//import edu.stanford.nlp.ie.crf.*;
//import edu.stanford.nlp.io.IOUtils;
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.sequences.DocumentReaderAndWriter;
//import edu.stanford.nlp.util.Triple;
//
//import java.util.List;
//
//
///** This is a demo of calling CRFClassifier programmatically.
// *  <p>
// *  Usage: {@code java -mx400m -cp "*" NERDemo [serializedClassifier [fileName]] }
// *  <p>
// *  If arguments aren't specified, they default to
// *  classifiers/english.all.3class.distsim.crf.ser.gz and some hardcoded sample text.
// *  If run with arguments, it shows some of the ways to get k-best labelings and
// *  probabilities out with CRFClassifier. If run without arguments, it shows some of
// *  the alternative output formats that you can get.
// *  <p>
// *  To use CRFClassifier from the command line:
// *  </p><blockquote>
// *  {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -textFile [file] }
// *  </blockquote><p>
// *  Or if the file is already tokenized and one word per line, perhaps in
// *  a tab-separated value format with extra columns for part-of-speech tag,
// *  etc., use the version below (note the 's' instead of the 'x'):
// *  </p><blockquote>
// *  {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -testFile [file] }
// *  </blockquote>
// *
// *  @author Jenny Finkel
// *  @author Christopher Manning
// */
//
//public class NERDemo {
//
//    public static void main(String[] args) throws Exception {
//
//        //chinese.kbp.distsim.crf.ser.gz  chinese.misc.distsim.crf.ser.gz
//        String serializedClassifier = "edu/stanford/nlp/models/ner/chinese.kbp.distsim.crf.ser.gz";
//        if (args.length > 0) {
//            serializedClassifier = args[0];
//        }
//
//        AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);
//
//    /* For either a file to annotate or for the hardcoded text example, this
//       demo file shows several ways to process the input, for teaching purposes.
//    */
//
//        if (args.length > 1) {
//
//      /* For the file, it shows (1) how to run NER on a String, (2) how
//         to get the entities in the String with character offsets, and
//         (3) how to run NER on a whole file (without loading it into a String).
//      */
//
//            String fileContents = IOUtils.slurpFile(args[1]);
//            List<List<CoreLabel>> out = classifier.classify(fileContents);
//            for (List<CoreLabel> sentence : out) {
//                for (CoreLabel word : sentence) {
//                    System.out.print(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
//                }
//                System.out.println();
//            }
//
//            System.out.println("---");
//            out = classifier.classifyFile(args[1]);
//            for (List<CoreLabel> sentence : out) {
//                for (CoreLabel word : sentence) {
//                    System.out.print(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
//                }
//                System.out.println();
//            }
//
//            System.out.println("---");
//            List<Triple<String, Integer, Integer>> list = classifier.classifyToCharacterOffsets(fileContents);
//            for (Triple<String, Integer, Integer> item : list) {
//                System.out.println(item.first() + ": " + fileContents.substring(item.second(), item.third()));
//            }
//            System.out.println("---");
//            System.out.println("Ten best entity labelings");
//            DocumentReaderAndWriter<CoreLabel> readerAndWriter = classifier.makePlainTextReaderAndWriter();
//            classifier.classifyAndWriteAnswersKBest(args[1], 10, readerAndWriter);
//
//            System.out.println("---");
//            System.out.println("Per-token marginalized probabilities");
//            classifier.printProbs(args[1], readerAndWriter);
//
//            // -- This code prints out the first order (token pair) clique probabilities.
//            // -- But that output is a bit overwhelming, so we leave it commented out by default.
//            // System.out.println("---");
//            // System.out.println("First Order Clique Probabilities");
//            // ((CRFClassifier) classifier).printFirstOrderProbs(args[1], readerAndWriter);
//
//        } else {
//
//      /* For the hard-coded String, it shows how to run it on a single
//         sentence, and how to do this and produce several formats, including
//         slash tags and an inline XML output format. It also shows the full
//         contents of the {@code CoreLabel}s that are constructed by the
//         classifier. And it shows getting out the probabilities of different
//         assignments and an n-best list of classifications with probabilities.
//      */
//
////            String[] example = {"5月  8日  下午  ，  李克强  考察  河南  新乡  封丘县  黄河  滩区  后  ，  随即  在  当地  居民  迁建  指挥部  主持  召开  现场会  ，  专题  研究  河南  、  山东  两  省  黄河  滩区  居民  迁建  工作  。  除  陪同  总理  考察  的  国务院  领导  及  发改委  、  财政部  、  水利部  、  黄河  水利  委员会  、  河南省  负责人  外  ，  山东省  省长  也  专程  赶来  参会  。  窗外  一直  下  着  雨  。  会前  ，  李克强  结束  开封  考察  后  ，  专程  驱车  一  小时  赴  新乡市  封丘县  黄河  滩区  ，  冒雨  踩  着  泥泞  小路  实地  察看  黄河  滩区  ，  并  入户  探望  滩区  居民  。  “  黄河  滩区  问题  是  多年来  历史  形成  的  ，  现在  到  了  该  解决  的  时候  了  ！  ”  李克强  面色  凝重  地说  ，  “  滩区  迁建  关乎  近  200万  滩区  居民  的  生活  和  发展  ，  也  关系  黄河  的  长治久安  ，  黄河  的  事  是  天下  大  事  ！"
////            };
//            String[] example = {"衡阳市石鼓区环城北路101市住建局6楼会议室，本次招标将于上述投标截止的同一时间、同一地点公开开标"};
//
//            for (String str : example) {
//                System.out.println(classifier.classifyToString(str));
//            }
//            System.out.println("---");
//
//            for (String str : example) {
//                // This one puts in spaces and newlines between tokens, so just print not println.
//                System.out.print(classifier.classifyToString(str, "slashTags", false));
//            }
//            System.out.println("---");
//
//            for (String str : example) {
//                // This one is best for dealing with the output as a TSV (tab-separated column) file.
//                // The first column gives entities, the second their classes, and the third the remaining text in a document
//                System.out.print(classifier.classifyToString(str, "tabbedEntities", false));
//            }
//            System.out.println("---");
//
//            for (String str : example) {
//                System.out.println(classifier.classifyWithInlineXML(str));
//            }
//            System.out.println("---");
//
//            for (String str : example) {
//                System.out.println(classifier.classifyToString(str, "xml", true));
//            }
//            System.out.println("---");
//
//            for (String str : example) {
//                System.out.print(classifier.classifyToString(str, "tsv", false));
//            }
//            System.out.println("---");
//
//            // This gets out entities with character offsets
//            int j = 0;
//            for (String str : example) {
//                j++;
//                List<Triple<String,Integer,Integer>> triples = classifier.classifyToCharacterOffsets(str);
//                for (Triple<String,Integer,Integer> trip : triples) {
//                    System.out.printf("%s over character offsets [%d, %d) in sentence %d.%n",
//                            trip.first(), trip.second(), trip.third, j);
//                }
//            }
//            System.out.println("---");
//
//            // This prints out all the details of what is stored for each token
//            int i=0;
//            for (String str : example) {
//                for (List<CoreLabel> lcl : classifier.classify(str)) {
//                    for (CoreLabel cl : lcl) {
//                        System.out.print(i++ + ": ");
//                        System.out.println(cl.toShorterString());
//                    }
//                }
//            }
//
//            System.out.println("---");
//
//        }
//    }
//
//}
