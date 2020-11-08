package com.selenium.jrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于Json的反序列化
 */
public class JsonDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
