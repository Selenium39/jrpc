package com.selenium.jrpc.codec;

/**
 * 反序列化: byte[]->对象
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
