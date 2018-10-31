package com.silita.biaodaa.common.kafka;

import com.silita.biaodaa.utils.BeanUtils;
import kafka.utils.VerifiableProperties;

public class MyObjectEncoder implements kafka.serializer.Encoder<Object>{
    public MyObjectEncoder(){}
    public MyObjectEncoder(VerifiableProperties verifiableProperties){
    }

    @Override
    public byte[] toBytes(Object map) {
        return BeanUtils.ObjectToBytes(map);
    }
}
