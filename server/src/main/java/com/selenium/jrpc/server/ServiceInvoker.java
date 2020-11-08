package com.selenium.jrpc.server;

import com.selenium.grpc.Request;
import com.selenium.jrpc.utils.ReflectionUtils;

/**
 * 调用具体服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParams());
    }
}
