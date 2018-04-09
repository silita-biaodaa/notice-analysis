package com.silita.biaodaa.common.kafka;

import com.silita.biaodaa.common.Constant;
import com.snatch.model.EsNotice;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Component
public class KafkaProducerUtil {
    private static Logger log = Logger.getLogger(KafkaProducerUtil.class);

    private static Producer producer = null;

    private static Properties properties = new Properties();

    private static String topic =  null;

    @PostConstruct
    public void init() throws FileNotFoundException {
        String path = KafkaProducerUtil.class.getClassLoader().getResource("kafka-producer.properties").getPath();
        FileInputStream in = new FileInputStream(path);
        try {
            properties.load(in);
            topic =  properties.getProperty("topic");
        } catch (IOException e) {
            log.error("producer config read error."+e,e);
        }
    }

    public void sendkafkaMsg(EsNotice es){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deliveryId", es.getRedisId());
        map.put("model", es);
        map.put("start", Constant.NOTICE_START);
        sendMsg(map);//kafka消息发送
        log.info("kafka发送消息finished。redisId:"+es.getRedisId()+"##title:" + es.getTitle() + "##Opendate:" + es.getOpenDate() + "##catchtype-->type:" + es.getType() + "##BusinessType:" + es.getBusinessType());
        map=null;
    }

    public void sendMsg(Object msg){
        int retry = 0;
        while (producer == null && retry < 3){
            retry++;
            producer = createProducer();
            if (producer == null) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                }catch (Exception e){
                    log.error(e,e);
                }
            }
        }

        if(producer != null){
            if(topic!=null) {
                producer.send(new KeyedMessage<Long, Object>(topic, System.nanoTime(), msg));
            }else{
                log.error("topic is null"+topic);
            }
        }else{
            log.error("kafka producer is null");
        }

    }

    private synchronized Producer createProducer() {
        return new Producer<Long, Object>(new ProducerConfig(properties));
    }


}
