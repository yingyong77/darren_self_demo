package com.netty.netty.config;

import com.netty.netty.protocol.Serializer;
import lombok.Getter;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class Config {
    @Getter
    static Properties properties;

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("properties/netty.properties");
            properties = new Properties();
            properties.load(inputStream);
            System.out.println(properties);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static int getServerPort() {
        String value = properties.getProperty("server.port");
        if (value == null) {
            return 8080;
        } else {
            return Integer.parseInt(value);
        }
    }

    public static Serializer.Algorithm getSerializerAlgorithm() {
        String value = properties.getProperty("serializer.algorithm");
        //sSystem.out.println("serializer.algorithm ----" + value);
        if (value == null) {
            return Serializer.Algorithm.JAVA;
        } else {
            return Serializer.Algorithm.valueOf(value);
        }
    }
}