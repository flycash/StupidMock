package cn.com.flycash.stupidmock.stub.args;


import java.util.Objects;

public class FixedValueArgMatcherImpl implements ArgMatcher {

    private Object fixedValue;

    public FixedValueArgMatcherImpl(Object value) {
        this.fixedValue = value;
    }

    @Override
    public boolean match(Object target) {
        return Objects.equals(fixedValue, target);
    }
}
