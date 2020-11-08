package com.selenium.jrpc.server;

import com.selenium.grpc.Request;
import com.selenium.grpc.ServiceDescriptor;
import com.selenium.jrpc.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {

    public ServiceManager sm;

    @Before
    public void init() {
        sm = new ServiceManager();
        register();
    }

    @Test
    public void register() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class, method);
        Request request = new Request(sdp, null);
        ServiceInstance instance = sm.lookup(request);
        assertNotNull(instance);
    }
}