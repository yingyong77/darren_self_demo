package com.darren.demo.spring.transactional.declareway;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author : darren
 * @date : 2021/12/7
 */
public class TransactionTemplateTest {

    private TransactionTemplate transactionTemplate;

    /**
     *
     */
    private void doSomeThing() {
        //事务的传播行为
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setTimeout(30000);

        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                //业务代码
                return null;
            }
        });

    }
}
