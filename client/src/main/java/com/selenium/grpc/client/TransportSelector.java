package com.selenium.grpc.client;

import com.selenium.grpc.Peer;
import com.selenium.transport.TransportClient;

import java.util.List;

/**
 * 选择哪个Server去连接
 */
public interface TransportSelector {

    /**
     * 初始化selector
     * @param peers 可以连接的server端信息
     * @param count client与server建立多少个连接
     * @param clazz  client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    //选择一个transport与server交互
    TransportClient select();

    //释放用完的client
    void release(TransportClient client);

    void close();
}
