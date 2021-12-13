package com.darren.demo.spring.transactional;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

/**
 * 事务管理器接口
 *
 * @author : darren
 * @date : 2021/12/7
 */
public class PlatformTransactionManagerTest {

    PlatformTransactionManager platformTransactionManager;

    AbstractPlatformTransactionManager abstractPlatformTransactionManager;

    TransactionStatus transactionStatus;

    TransactionDefinition transactionDefinition;
}
