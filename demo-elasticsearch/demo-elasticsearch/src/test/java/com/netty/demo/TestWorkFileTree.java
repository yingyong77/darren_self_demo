package com.netty.demo;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 访问者模式
 * visit模式的应用
 * <p>
 * 删除目录是不安全操作
 *
 * @author : darren
 * @date : 2022/3/7
 */
public class TestWorkFileTree {

    public static void main(String[] args) {
        
    }


    public void deleteDir() throws IOException {

        //Files.delete(Paths.get("D:\\work-数腾\\qaaaa"));  //java.nio.file.DirectoryNotEmptyException

        Files.walkFileTree(Paths.get("D:\\work-数腾\\qaaaa"), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("=====>进入" + dir);

                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("=====>文件" + file);
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("<=====退出" + dir);
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

    /**
     * 统计jar包个数
     *
     * @throws IOException
     */
    public void f2() throws IOException {

        AtomicInteger fileCount = new AtomicInteger();

        Files.walkFileTree(Paths.get("C:\\Program Files\\Java\\jdk1.8.0_201"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".jar")) {
                    fileCount.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }
        });

        System.out.println("jar包个数===>" + fileCount);

    }

    private static void f1() throws IOException {

        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();

        Files.walkFileTree(Paths.get("C:\\Program Files\\Java\\jdk1.8.0_201"), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("==========>" + dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("==========>" + file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        System.out.println("dirCount===>" + dirCount);
        System.out.println("fileCount===>" + fileCount);
    }
}
