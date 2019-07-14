package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.Invocation;
import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Method;
import java.util.List;

public class DefaultStubImpl<T> implements IStub<T> {

    private final Object target;
    private final Method method;
    private final List<ArgMatcher> matchers;
    private final Answer<T> answer;

    public DefaultStubImpl(Object target, Method method, List<ArgMatcher> matchers, Answer<T> answer) {
        this.target = target;
        this.method = method;
        this.matchers = matchers;
        this.answer = answer;
    }

    @Override
    public boolean match(Invocation invocation) {

        if (this.target != null && !this.target.equals(invocation.getInvoker())) {
            return false;
        }
        if (!this.method.equals(invocation.getMethod())) {
            return false;
        }
        Object[] args = invocation.getArgs();
        if (emptyArray(args) && CollectionUtils.isEmpty(matchers)) {
            return true;
        }
        if (matchers.size() != args.length) {
            return false;
        }

        for (int i = 0; i < matchers.size(); i++) {
            ArgMatcher matcher = matchers.get(i);
            if (!matcher.match(args[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Answer<T> getAnswer() {
        return answer;
    }

    private boolean emptyArray(Object[] array) {
        return array == null || array.length == 0;
    }
}
