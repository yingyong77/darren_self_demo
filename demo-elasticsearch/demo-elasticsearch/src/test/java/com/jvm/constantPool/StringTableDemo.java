package com.jvm.constantPool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * StringTable 性能调优
 * -Xms500m -Xmx500 -XX:+PrintStringTableStatistics -XX StringTableSize=200
 *
 * @author : darren
 * @date : 2022/2/11
 */
public class StringTableDemo {

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(""), "utf-8"))) {
            String line = null;
            long start = System.nanoTime();
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
            }
            System.out.println("cost:" + (System.nanoTime() - start) / 1000000);
        }

    }


}
