package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;

public interface StubBuilder {

    StubBuilder setTarget(Object mockObj);

    StubBuilder setArgMatchers(ArgMatcher...matchers);

    StubBuilder setMethod(Method method);

    StubBuilder setAnswer(Answer answer);

    IStub build();
}
