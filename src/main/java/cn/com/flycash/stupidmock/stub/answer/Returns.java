package cn.com.flycash.stupidmock.stub.answer;

import cn.com.flycash.stupidmock.Invocation;

public class Returns<T> implements Answer<T> {

    private T returns;

    public Returns(T value) {
        this.returns = value;
    }

    @Override
    public T answer(Invocation invocation) {
        return returns;
    }
}
