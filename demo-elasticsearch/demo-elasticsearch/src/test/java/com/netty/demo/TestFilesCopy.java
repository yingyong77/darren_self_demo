package com.netty.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件路径拷贝
 *
 * @author : darren
 * @date : 2022/3/7
 */
public class TestFilesCopy {

    public static void main(String[] args) throws IOException {
        String source = "D:\\work-数腾\\sssss";
        String target = "D:\\work-数腾\\sssss1";
        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetName = path.toString().replace(source, target);
                //是目录
                if (Files.isDirectory(path)) {
                    Files.createDirectories(Paths.get(targetName));

                } else if (Files.isRegularFile(path)) {  //普通目录

                    Files.copy(path, Paths.get(targetName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
