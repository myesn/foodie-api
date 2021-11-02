package cn.myesn.service.impl;

import cn.myesn.service.StuService;
import cn.myesn.service.TestTransactionalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestTransactionalServiceImpl implements TestTransactionalService {

    private final StuService stuService;

    public TestTransactionalServiceImpl(StuService stuService) {
        this.stuService = stuService;
    }

    /**
     * 事物传播 - Propagation
     * REQUIRED（默认）: 使用当前的事务，如果当前没有事务，则自己新建一个事务，子方法必须运行在一个事务中；如果当前存在事务，则加入这个事务，成为一个整体
     * SUPPORTS: 如果当前有事务，则使用事务；如果当前没有事务，则不使用事务。主要用于查询，因为查询不需要事务。
     * MANDATORY: 强制要求必须存在一个事务，如果不存在，则抛出异常
     * REQUIRES_NEW: 如果当前有事务，则挂起该事务，并自己创建一个新的事务给自己使用；如果当前没有事务，则同 REQUIRED
     * NOT_SUPPORTED: 如果当前有事务，则把事务挂起，自己不适用事务去执行数据库操作
     * NERVER: 如果当前有事务存在，则抛出异常
     * NESTED: 如果当前有事务，则开启子事务（嵌套事务），嵌套事务是独立提交或者回滚；如果当前没有事务，则同 REQUIRED
     *         但是如果主事务提交，则会携带子事务一起提交。
     *         如果主事务回滚，则子事务会一起回滚。相反，子事务异常，则父事务可以回滚或不回滚。
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void testPropagationTransactional() {
        stuService.saveParent();

//        try {
//            // save point
            stuService.saveChildren();
//        } catch (Exception e) {
//
//        }

        if (false) {
            throw new IllegalArgumentException();
        }
    }
}
