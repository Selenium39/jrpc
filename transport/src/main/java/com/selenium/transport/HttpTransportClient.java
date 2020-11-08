package com.selenium.transport;

import com.selenium.grpc.Peer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 基于Http(HttpURLConnection)的客户端:短连接
 */
@Slf4j
public class HttpTransportClient implements TransportClient {
    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            log.info("URL:" + this.url);
            HttpURLConnection connection = (HttpURLConnection) new URL(this.url).openConnection();
            //是否能够向connection中输入,如发送post请求,默认是false
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.connect();
            //将data写入connection
            IOUtils.copy(data, connection.getOutputStream());

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                return connection.getInputStream();
            } else {
                return connection.getErrorStream();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {

    }
}
