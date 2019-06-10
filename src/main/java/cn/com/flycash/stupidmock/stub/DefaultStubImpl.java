package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.Invocation;
import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;

import java.lang.reflect.Method;

public class DefaultStubImpl implements IStub {

    private final Object target;
    private final Method method;
    private final ArgMatcher[] matchers;
    private final Answer answer;

    public DefaultStubImpl(Object target, Method method, ArgMatcher[] matchers, Answer answer) {
        this.target = target;
        this.method = method;
        this.matchers = matchers;
        this.answer = answer;
    }

    @Override
    public boolean match(Invocation invocation) {

        if (!this.target.equals(invocation.getInvoker())) {
            return false;
        }
        if (!this.method.equals(invocation.getMethod())) {
           return false;
        }
        Object[] args = invocation.getArgs();
        if (emptyArray(args) && emptyArray(matchers)) {
            return true;
        }
        if (matchers.length != args.length) {
            return false;
        }

        for (int i = 0; i < matchers.length; i++) {
            ArgMatcher matcher = matchers[i];
            if (!matcher.match(args[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Answer getAnswer() {
        return answer;
    }

    private boolean emptyArray(Object[] array) {
        return array == null || array.length == 0;
    }
}
