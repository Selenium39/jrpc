package com.selenium.jrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JsonEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JsonEncoder();
        TestBean bean = new TestBean(1, "test");
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
    }
}