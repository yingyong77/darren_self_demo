package com.spring.bean;

import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @author : darren
 * @date : 2021/12/28
 */
public class TxTest {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "d:\\code");

    }
}
