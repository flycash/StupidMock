package cn.com.flycash.stupidmock;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Invocation {

    private Object invoker;
    private Method method;
    private Object[] args;

    private MethodProxy proxy;

    public Invocation(Object invoker, Method method, Object[] args) {
        this.invoker = invoker;
        this.method = method;
        this.args = args;
    }

    public Invocation(Object invoker, Method method, Object[] args, MethodProxy proxy) {
        this.invoker = invoker;
        this.method = method;
        this.args = args;
        this.proxy = proxy;
    }

    public Object getInvoker() {
        return invoker;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }

    public MethodProxy getProxy() {
        return proxy;
    }
}
