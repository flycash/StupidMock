package cn.com.flycash.stupidmock.stub.args;

/**
 * 用于判断某个参数（实际调用的参数）是否匹配上某个
 */
public interface ArgMatcher {
    boolean match(Object target);
}
