package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;

public class StubBuilderImpl<T> extends AbstractStubBuilder<T> {

    /**
     * mock obj
     */
    private Object target;

    private Method method;
    private ArgMatcher[] argMatchers;
    private Answer answer;

    @Override
    public StubBuilder<T> setArgMatchers(ArgMatcher... matchers) {
        if (matchers == null) {
            this.argMatchers = new ArgMatcher[0];
        } else {
            this.argMatchers = matchers;
        }
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
    public void then(Answer answer) {
        this.answer = answer;
        this.build();
    }

    @Override
    public IStub<T> doBuild() {
        return new DefaultStubImpl<>(target, method, argMatchers, answer);
    }
}
