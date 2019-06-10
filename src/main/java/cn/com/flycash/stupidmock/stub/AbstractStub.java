package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;
import java.util.Objects;

public abstract class AbstractStub implements IStub {
    protected Object target;
    protected Method method;
    protected ArgMatcher[] argMatchers;

    @Override
    public AbstractStub setArgMatchers(ArgMatcher[] argMatchers) {
        this.argMatchers = Objects.requireNonNullElse(argMatchers, new ArgMatcher[0]);
        return this;
    }

    @Override
    public AbstractStub setMethod(Method method) {
        this.method = method;
        return this;
    }

    @Override
    public AbstractStub setTarget(Object target) {
        this.target = target;
        return this;
    }
}
