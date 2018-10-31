package kafka;

import com.silita.biaodaa.common.kafka.KafkaProducerUtil;
import com.silita.biaodaa.service.ConfigTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dh on 2018/7/5.
 */
public class TestKafkaMsg extends ConfigTest {

    @Autowired
    KafkaProducerUtil kafkaProducerUtil;

    Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Test
    public void testSend(){
        int partitionKey =0;
        while (partitionKey<7) {
            for (int i = 1; i <= 100; i++) {
                Map map = new HashMap();
                map.put("no", i);
                map.put("name", "daihuan" + i);
                map.put("partitionKey", partitionKey);
                kafkaProducerUtil.sendMsg(map, partitionKey);
                logger.info("发送消息finished：" + map.toString());
            }
            partitionKey++;
        }
    }
}
