package com.selenium.jrpc.codec;

/**
 * 序列化:对象->byte[]
 */
public interface Encoder {
    byte[] encode(Object obj);
}
