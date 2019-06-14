package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.stub.ThreadSafeStubBuilder;
import cn.com.flycash.stupidmock.stub.args.AnyValueArgMatcher;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;
import cn.com.flycash.stupidmock.stub.args.FixedValueArgMatcherImpl;
import cn.com.flycash.stupidmock.stub.args.OneOfArgMatcherImpl;
import com.google.common.base.Defaults;

public class Matchers {

    @SuppressWarnings("unchecked")
    public static <T> T equal(T obj) {
        reportMatcher(new FixedValueArgMatcherImpl(obj));
        return (T) Defaults.defaultValue(obj.getClass());
    }

    public static <T> T any(Class<T> clz) {
        reportMatcher(new AnyValueArgMatcher(clz));
        return Defaults.defaultValue(clz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T oneOf(T...candidates) {
        if (candidates == null || candidates.length == 0) {
            throw new IllegalArgumentException();
        }
        reportMatcher(new OneOfArgMatcherImpl(candidates));
        return (T) Defaults.defaultValue(candidates[0].getClass());
    }

    private static void reportMatcher(ArgMatcher matcher) {
        new ThreadSafeStubBuilder().addArgMatchers(matcher);
    }
}
