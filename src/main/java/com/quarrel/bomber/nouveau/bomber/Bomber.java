package com.quarrel.bomber.nouveau.bomber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Socket;

public class Bomber implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Bomber.class);

    private String host;
    private int port;

    public Bomber(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try(Socket socket = new Socket()) {
            logger.info("Start connection");
            socket.connect(new InetSocketAddress(host, port));
            logger.info("Close socket");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
