package cn.com.flycash.stupidmock.cglib;

import cn.com.flycash.stupidmock.invocation.InvocationObserver;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class MethodInterceptorObserverImpl implements MethodInterceptor {

    private List<InvocationObserver> observers = new LinkedList<>();

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        return null;
    }
}
