package cn.com.flycash.stupidmock.stub.args;

public class AnyArgMatcher implements ArgMatcher {
    @Override
    public boolean match(Object target) {
        return true;
    }
}
