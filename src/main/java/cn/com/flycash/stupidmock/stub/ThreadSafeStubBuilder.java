package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;

/**
 * 这是一个Decorator模式。
 */
public class ThreadSafeStubBuilder<T> implements StubBuilder<T> {

    private static final ThreadLocal<StubBuilder<?>> stubBuilder = new ThreadLocal<>();

    public ThreadSafeStubBuilder() {
        if (stubBuilder.get() == null){
            stubBuilder.set(new StubBuilderImpl());
        }
    }

    @Override
    public void then(Answer answer) {
        builder().then(answer);
    }

    @Override
    public StubBuilder<T> setTarget(Object mockObj) {
        return builder().setTarget(mockObj);
    }

    @Override
    public StubBuilder<T> setArgMatchers(ArgMatcher... matchers) {
        return builder().setArgMatchers(matchers);
    }

    @Override
    public StubBuilder<T> setMethod(Method method) {
        return builder().setMethod(method);
    }

    @Override
    public void addObserver(BuildingStubObserver observer) {
        builder().addObserver(observer);
    }

    @Override
    public void thenReturn(T obj) {
        builder().thenReturn(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public IStub<T> build() {
        IStub stub = builder().build();
        stubBuilder.remove();
        return stub;
    }

    @SuppressWarnings("unchecked")
    private StubBuilder<T> builder() {
        return (StubBuilder<T>) stubBuilder.get();
    }
}
