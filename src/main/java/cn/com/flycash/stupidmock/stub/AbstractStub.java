package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;

public abstract class AbstractStub implements IStub {
    protected Object target;
    protected Method method;
    protected ArgMatcher[] argMatchers;
}
