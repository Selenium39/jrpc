package com.selenium.grpc.client;

import com.selenium.grpc.Peer;
import com.selenium.jrpc.codec.Decoder;
import com.selenium.jrpc.codec.Encoder;
import com.selenium.jrpc.codec.JsonDecoder;
import com.selenium.jrpc.codec.JsonEncoder;
import com.selenium.transport.HttpTransportClient;
import com.selenium.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));
}
