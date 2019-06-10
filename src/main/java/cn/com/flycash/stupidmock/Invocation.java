package cn.com.flycash.stupidmock;

import java.lang.reflect.Method;

public class Invocation {

    private Object invoker;
    private Method method;
    private Object[] args;

    public Invocation(Object invoker, Method method, Object[] args) {
        this.invoker = invoker;
        this.method = method;
        this.args = args;
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
}
