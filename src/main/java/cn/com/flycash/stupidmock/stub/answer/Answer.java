package cn.com.flycash.stupidmock.stub.answer;

import cn.com.flycash.stupidmock.invocation.Invocation;

public interface Answer {
    Object answer(Invocation invocation);
}
