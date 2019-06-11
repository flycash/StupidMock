package cn.com.flycash.stupidmock.stub.answer;

import cn.com.flycash.stupidmock.Invocation;

@FunctionalInterface
public interface Answer<T> {
    T answer(Invocation invocation);
}
