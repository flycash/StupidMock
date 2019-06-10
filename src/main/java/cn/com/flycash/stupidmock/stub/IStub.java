package cn.com.flycash.stupidmock.stub;


import cn.com.flycash.stupidmock.invocation.Invocation;
import cn.com.flycash.stupidmock.stub.answer.Answer;


public interface IStub {

    boolean match(Invocation invocation);

    Answer getAnswer();

}
