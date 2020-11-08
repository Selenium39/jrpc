package com.selenium.jrpc.server;


import com.selenium.jrpc.codec.Decoder;
import com.selenium.jrpc.codec.Encoder;
import com.selenium.jrpc.codec.JsonDecoder;
import com.selenium.jrpc.codec.JsonEncoder;
import com.selenium.transport.HttpTransportServer;
import com.selenium.transport.TransportServer;
import lombok.Data;

@Data
public class RpcServerConfig {
    //网络协议
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    //序列化
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;

    private int port = 3000;
}
