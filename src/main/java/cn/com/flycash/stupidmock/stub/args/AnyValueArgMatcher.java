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
        if (clz.isPrimitive()) {
            if (clz.equals(int.class)) {
                return target.getClass().getName().equals("java.lang.Integer");
            } else if (clz.equals(byte.class)) {
                return target.getClass().getName().equals("java.lang.Byte");
            } else if (clz.equals(char.class)) {
                return target.getClass().getName().equals("java.lang.Character");
            } else if (clz.equals(boolean.class)) {
                return target.getClass().getName().equals("java.lang.Boolean");
            } else if (clz.equals(float.class)) {
                return target.getClass().getName().equals("java.lang.Float");
            } else if (clz.equals(double.class)) {
                return target.getClass().getName().equals("java.lang.Double");
            } else if (clz.equals(short.class)) {
                return target.getClass().getName().equals("java.lang.Short");
            } else if (clz.equals(long.class)) {
                return target.getClass().getName().equals("java.lang.Long");
            }
        }
        return clz.equals(target.getClass());
    }
}
