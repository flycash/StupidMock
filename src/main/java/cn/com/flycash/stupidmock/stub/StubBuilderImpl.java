package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;

public class StubBuilderImpl implements StubBuilder {

    /**
     * mock obj
     */
    private Object target;

    private Method method;
    private ArgMatcher[] argMatchers;
    private Answer answer;

    @Override
    public StubBuilder setArgMatchers(ArgMatcher... matchers) {
        if (argMatchers == null) {
            this.argMatchers = new ArgMatcher[0];
        } else {
            this.argMatchers = matchers;
        }
        return this;
    }

    @Override
    public StubBuilder setMethod(Method method) {
        this.method = method;
        return null;
    }

    @Override
    public StubBuilder setAnswer(Answer answer) {
        this.answer = answer;
        return null;
    }

    @Override
    public StubBuilderImpl setTarget(Object target) {
        this.target = target;
        return this;
    }

    @Override
    public IStub build() {
        return new DefaultStubImpl(target, method, argMatchers, answer);
    }
}
