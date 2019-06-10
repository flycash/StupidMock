package cn.com.flycash.stupidmock.stub;


import cn.com.flycash.stupidmock.Invocation;
import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;


public interface IStub {

    IStub setTarget(Object mockObj);

    IStub setArgMatchers(ArgMatcher...matchers);

    IStub setMethod(Method method);

    IStub then(Answer answer);

    boolean match(Invocation invocation);

    Answer getAnswer();

}
