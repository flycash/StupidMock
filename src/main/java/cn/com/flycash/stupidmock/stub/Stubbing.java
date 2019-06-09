package cn.com.flycash.stupidmock.stub;

import java.lang.reflect.Method;

public class Stubbing implements IStub {

    private Method method;
    public Stubbing(Method method) {
        this.method = method;
    }

    @Override
    public Object invoke(Object[] args) {
        return null;
    }
}
