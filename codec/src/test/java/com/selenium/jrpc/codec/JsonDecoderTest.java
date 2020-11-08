package com.selenium.jrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonDecoderTest {

    @Test
    public void decode() {

        //序列化
        Encoder encoder = new JsonEncoder();
        TestBean bean = new TestBean(1, "test");
        byte[] bytes = encoder.encode(bean);

        //反序列化
        Decoder decoder = new JsonDecoder();
        TestBean bean1 = decoder.decode(bytes, TestBean.class);
        assertEquals(bean.getId(), bean1.getId());
        assertEquals(bean.getName(), bean1.getName());
    }
}