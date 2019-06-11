package cn.com.flycash.stupidmock.stub;


import cn.com.flycash.stupidmock.Invocation;
import cn.com.flycash.stupidmock.stub.answer.Answer;

public interface IStub<T> {

    boolean match(Invocation invocation);

    Answer<T> getAnswer();

}
