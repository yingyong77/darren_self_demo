package com.darren.demo.code.tomcat.nioEndPoint;

import org.apache.tomcat.util.net.Acceptor;
import org.apache.tomcat.util.net.NioEndpoint;

/**
 * poller的数量取决于处理器的核数
 * <p>
 * ConcurrentLinkedQueue 无界线程安全队列
 * <p>
 * tomcat7.0中poller用的是{@link ConcurrentLinkedQueue}
 * tomcat9.0中poller用的是 {@link org.apache.tomcat.util.collections.SynchronizedQueue}
 *
 * @author : darren
 * @date : 2021/7/16
 */
public class ConcurrentLinkedQueue {

    Acceptor acceptor;

    NioEndpoint.Poller poller;

    java.util.concurrent.ConcurrentLinkedQueue concurrentLinkedQueue;

}
