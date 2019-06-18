package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;

public interface StubBuilder<T> {

    StubBuilder<T> setTarget(Object mockObj);

    StubBuilder<T> addArgMatchers(ArgMatcher... matchers);

    StubBuilder<T> setMethod(Method method);

    void then(Answer<T> answer);

    void thenReturn(T obj);

    void thenThrow(RuntimeException e);

    void addObserver(BuildingStubObserver observer);

    <MOCK> MOCK when(MOCK mock);

    void doAnswer(Answer<T> answer);

    boolean prepare();

    IStub<T> build();
}
