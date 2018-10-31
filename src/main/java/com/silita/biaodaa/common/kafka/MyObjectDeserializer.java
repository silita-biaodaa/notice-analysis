package com.silita.biaodaa.common.kafka;

import com.silita.biaodaa.utils.BeanUtils;

import java.util.Map;

public class MyObjectDeserializer implements org.apache.kafka.common.serialization.Deserializer{
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        return BeanUtils.BytesToObject(bytes);
    }

    @Override
    public void close() {

    }
}
