package com.darren.demo.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 线程安全的无界非阻塞队列
 * 数据结构----单向链表
 * 出队入队使用CAS来实现线程安全
 * 相比于阻塞算法，这是使用cpu资源来换取阻塞所带开的开销
 *
 * @author : darren
 * @date : 2021/8/27
 * @see java.util.concurrent.ConcurrentLinkedQueue
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcurrentLinkedQueueTest {

    private final static ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

    @Test
    public void test() {
    }


//    ------------------from source code
//    public boolean offer(E e) {
//        //(1)判断空指针
//        checkNotNull(e);
//        //(2) 构造Node节点,在构造函数内部调用unsafe.putobject
//        final ConcurrentLinkedQueue.Node<E> newNode = new ConcurrentLinkedQueue.Node<E>(e);
//
//        //(3) 从尾节点进行插入
//        for (ConcurrentLinkedQueue.Node<E> t = tail, p = t; ; ) {
//            ConcurrentLinkedQueue.Node<E> q = p.next;
//            //(4)如果q=null说明p是尾节点,执行插入
//            if (q == null) {
//                // p is last node
//                //(5) 使用CAS设置p节点的next节点
//                if (p.casNext(null, newNode)) {
//                    //（6）CAS成功，则说明新增节点已经被放入链表，然后设置当前尾节点（包含head，第//1,3,5,7个为尾节点）
//                    // Successful CAS is the linearization point
//                    // for e to become an element of this queue,
//                    // and for newNode to become "live".
//                    if (p != t) // hop two nodes at a time
//                        casTail(t, newNode);  // Failure is OK.
//                    return true;
//                }
//                // Lost CAS race to another thread; re-read next
//                //(7)
//            } else if (p == q)

//                // We have fallen off list.  If tail is unchanged, it
//                // will also be off-list, in which case we need to
//                // jump to head, from which all live nodes are always
//                // reachable.  Else the new tail is a better bet.
//                p = (t != (t = tail)) ? t : head;
//            else
//                //（8）寻找尾节点
//                // Check for tail updates after two hops.
//                p = (p != t && t != (t = tail)) ? t : q;
//        }
//    }
}
