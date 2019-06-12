package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;

public interface StubBuilder<T> {

    StubBuilder<T> setTarget(Object mockObj);

    StubBuilder<T> setArgMatchers(ArgMatcher...matchers);

    StubBuilder<T> setMethod(Method method);

    void then(Answer answer);

    void thenReturn(T obj);

    void addObserver(BuildingStubObserver observer);

    IStub<T> build();
}
