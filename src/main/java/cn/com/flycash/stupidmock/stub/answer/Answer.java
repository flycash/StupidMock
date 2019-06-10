package cn.com.flycash.stupidmock.stub.answer;

import cn.com.flycash.stupidmock.Invocation;

public interface Answer {
    Object answer(Invocation invocation);
}
