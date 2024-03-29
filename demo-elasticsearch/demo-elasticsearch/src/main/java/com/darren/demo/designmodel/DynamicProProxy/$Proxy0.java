package com.darren.demo.designmodel.DynamicProProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * <p>
 * 动态代理通过中介类实现了具体的代理功能
 *
 * @author : darren
 * @date : 2021/7/14
 */
public final class $Proxy0 extends Proxy implements CommonFunction {
    private static final Method m1;
    private static final Method m2;
    private static final Method m3;
    private static final Method m0;

    /**
     * 注意这里是生成代理类的构造方法，方法参数为InvocationHandler类型，看到这，是不是就有点明白
     * 为何代理对象调用方法都是执行InvocationHandler中的invoke方法，而InvocationHandler又持有一个
     * 被代理对象的实例，不禁会想难道是....？ 没错，就是你想的那样。
     * <p>
     * super(paramInvocationHandler)，是调用父类Proxy的构造方法。
     * 父类持有：protected InvocationHandler h;
     * Proxy构造方法：
     * protected Proxy(InvocationHandler h) {
     * Objects.requireNonNull(h);
     * this.h = h;
     * }
     */
    public $Proxy0(InvocationHandler paramInvocationHandler) throws Exception {
        super(paramInvocationHandler);
    }

    //这个静态块本来是在最后的，我把它拿到前面来，方便描述
    static {
        try {
            //看看这儿静态块儿里面有什么，是不是找到了giveMoney方法。请记住giveMoney通过反射得到的名字m3，其他的先不管
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m3 = Class.forName("proxy.Person").getMethod("doWork");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
            //return;
        } catch (NoSuchMethodException localNoSuchMethodException) {
            throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
        }
    }

    /**
     * 这里调用代理对象的doWork方法，直接就调用了InvocationHandler中的invoke方法，并把m3传了进去。
     * this.h.invoke(this, m3, null);这里简单，明了。
     * 来，再想想，代理对象持有一个InvocationHandler对象，InvocationHandler对象持有一个被代理的对象，
     * 再联系到InvacationHandler中的invoke方法。嗯，就是这样。
     */
    @Override
    public void doWork() {
        try {
            this.h.invoke(this, m3, null);
            return;
        } catch (Error | RuntimeException localError) {
            throw localError;
        } catch (Throwable localThrowable) {
            throw new UndeclaredThrowableException(localThrowable);
        }
    }

    //注意，这里为了节省篇幅，省去了toString，hashCode、equals方法的内容。原理和giveMoney方法一毛一样。

}

