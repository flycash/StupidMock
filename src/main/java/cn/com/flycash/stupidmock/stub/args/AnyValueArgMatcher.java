package cn.com.flycash.stupidmock.stub.args;

public class AnyValueArgMatcher implements ArgMatcher {

    private final Class<?> clz;

    public AnyValueArgMatcher(Class<?> clz) {
        this.clz = clz;
    }

    @Override
    public boolean match(Object target) {
        if (target == null) {
            //基本类型不能为null
            return !clz.isPrimitive();
        }
        return clz.equals(target.getClass());
    }
}
