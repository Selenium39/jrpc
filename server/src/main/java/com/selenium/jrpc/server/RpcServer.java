package com.selenium.jrpc.server;

import com.selenium.grpc.Request;
import com.selenium.grpc.Response;
import com.selenium.grpc.ServiceDescriptor;
import com.selenium.jrpc.codec.Decoder;
import com.selenium.jrpc.codec.Encoder;
import com.selenium.jrpc.utils.ReflectionUtils;
import com.selenium.transport.RequestHandler;
import com.selenium.transport.TransportServer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

@Data
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;
    private RequestHandler handler = new RequestHandler() {

        @Override
        public void onRequest(InputStream in, OutputStream out) {
            Response response = new Response();
            try {
                byte[] bytes = IOUtils.readFully(in, in.available(), true);
                Request request = decoder.decode(bytes, Request.class);
                ServiceInstance instance = serviceManager.lookup(request);
                Object data = serviceInvoker.invoke(instance, request);
                response.setData(data);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
                response.setCode(-1);
                response.setMsg("RpcServer error: " + e.getMessage());
            } finally {
                byte[] bytes = encoder.encode(response);
                try {
                    out.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        //net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), handler);
        //encode
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        //decode
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    //注册服务
    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }
}
