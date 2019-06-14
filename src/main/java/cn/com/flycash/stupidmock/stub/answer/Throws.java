package cn.com.flycash.stupidmock.stub.answer;

import cn.com.flycash.stupidmock.Invocation;

public class Throws<T> implements Answer<T> {
    private final RuntimeException runtimeException;

    public Throws(RuntimeException e) {
        runtimeException = e;
    }

    @Override
    public T answer(Invocation invocation) {
        throw runtimeException;
    }
}
