package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.Invocation;
import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.answer.DefaultValueAnswer;

public class DefaultValueStubImpl extends AbstractStub {

    public static final DefaultValueStubImpl INSTANCE = new DefaultValueStubImpl();

    private DefaultValueStubImpl() {
    }

    @Override
    public boolean match(Invocation invocation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Answer getAnswer() {
        return DefaultValueAnswer.INSTANCE;
    }
}
