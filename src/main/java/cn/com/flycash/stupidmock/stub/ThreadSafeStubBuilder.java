package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;

/**
 * 这是一个Decorator模式。
 */
public class ThreadSafeStubBuilder implements StubBuilder {

    private static final ThreadLocal<StubBuilder> stubBuilder = new ThreadLocal<>();

    public ThreadSafeStubBuilder() {
        if (stubBuilder.get() == null){
            stubBuilder.set(new StubBuilderImpl());
        }
    }

    @Override
    public StubBuilder setTarget(Object mockObj) {
        return stubBuilder.get().setTarget(mockObj);
    }

    @Override
    public StubBuilder setArgMatchers(ArgMatcher... matchers) {
        return stubBuilder.get().setArgMatchers(matchers);
    }

    @Override
    public StubBuilder setMethod(Method method) {
        return stubBuilder.get().setMethod(method);
    }

    @Override
    public StubBuilder setAnswer(Answer answer) {
        return stubBuilder.get().setAnswer(answer);
    }

    @Override
    public IStub build() {
        IStub stub = stubBuilder.get().build();
        stubBuilder.remove();
        return stub;
    }
}
