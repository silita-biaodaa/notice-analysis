package com.silita.biaodaa.common.kafka;

import com.silita.biaodaa.disruptor.DisruptorOperator;
import com.silita.biaodaa.utils.BeanUtils;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public abstract class KafkaConsumerBase {
    private static Logger logger = Logger.getLogger(KafkaConsumerBase.class);

    private static Properties properties = null;

    private static String topic =  null;

    @Autowired
    DisruptorOperator disruptorOperator;

    public void init(){
        ConsumerConnector consumer  =null;
        Map<String, List<KafkaStream<byte[], byte[]>>>  messageStreams = null;
        KafkaStream<byte[], byte[]> stream =null;
        ConsumerIterator<byte[], byte[]> iterator =null;
//        stream.reversed();
        if(properties==null) {
            loadProperties();
        }
        int retry=0;
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, 1);
        while(consumer==null || retry< 50) {
            try {
                consumer = createConsumer();//初始化kafka消费者
                messageStreams = consumer.createMessageStreams(topicCountMap);
                stream = messageStreams.get(topic).get(0);
                iterator =  stream.iterator();
                execute(consumer, iterator);
            } catch (Exception e) {
                logger.error(e, e);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e1) {
                    logger.error(e1, e1);
                }
            }finally {
                retry++;
                iterator = null;
                if(stream!=null) {
                    stream.clear();
                    stream=null;
                }
                if(messageStreams!=null) {
                    messageStreams.clear();
                    messageStreams=null;
                }
                consumer.shutdown();
                consumer=null;
            }
        }
    }

    private static void loadProperties(){
        properties = new Properties();
        InputStream in = null;
        try {
            in =KafkaConsumerBase.class.getClassLoader().getResource("/config/kafka/kafka-consumer.properties").openStream();
            properties.load(in);
            topic = properties.getProperty("topic");
            logger.info("init consumer topic:" + topic);
        } catch (IOException e) {
            logger.error(e,e);
        }finally {
            try {
                if(in!= null) in.close();
            } catch (IOException e) {
                logger.error(e,e);
            }
            in =null;
        }
    }

    public void execute(ConsumerConnector consumer, ConsumerIterator<byte[], byte[]> iterator) {
//        iterator.reversed();
        try {
            while (iterator.hasNext()) {
                Object msg = BeanUtils.BytesToObject(iterator.next().message());
                msgHandle(msg);
                consumer.commitOffsets();
            }
        }catch (Exception e){
            logger.error(e,e);
        }
    }

    /**
     * 接收消息的处理
     * @param msg
     * @throws Exception
     */
    protected abstract void msgHandle(Object msg)throws Exception;

    private ConsumerConnector createConsumer() {
        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }

}