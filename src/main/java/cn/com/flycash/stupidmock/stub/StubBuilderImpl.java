package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StubBuilderImpl<T> extends AbstractStubBuilder<T> {

    /**
     * mock obj
     */
    private Object target;

    private Method method;
    private List<ArgMatcher> argMatchers = new LinkedList<>();
    private Answer<T> answer;

    @Override
    public StubBuilder<T> addArgMatchers(ArgMatcher... matchers) {
        Collections.addAll(argMatchers, matchers);
        return this;
    }

    @Override
    public StubBuilder<T> setMethod(Method method) {
        this.method = method;
        return this;
    }

    @Override
    public StubBuilder<T> setTarget(Object target) {
        this.target = target;
        return this;
    }

    @Override
    public void then(Answer<T> answer) {
        this.answer = answer;
        this.build();
    }

    @Override
    public IStub<T> doBuild() {
        return new DefaultStubImpl<>(target, method, argMatchers, answer);
    }
}
