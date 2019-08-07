package cn.com.flycash.stupidmock.stub.answer;

import cn.com.flycash.stupidmock.Invocation;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public enum RealCallAnswer implements Answer {
    INSTANCE;

    @Override
    public Object answer(Invocation invocation) {
        Object target = invocation.getInvoker();
        Object[] args = invocation.getArgs();
        MethodProxy proxy = invocation.getProxy();
        Method method = invocation.getMethod();
        try {

            if ((method.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
                String name = "rename____" + method.getName();
                Method originMethod;
                Class[] paramClz = paramsClz(args);
                if (paramClz == null) {
                    originMethod = method.getDeclaringClass().getMethod(name);
                } else {
                    originMethod = method.getDeclaringClass().getMethod(name, paramClz);
                }
                return originMethod.invoke(null, args);
            } else {
                return proxy.invokeSuper(target, args);
            }

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private Class[] paramsClz(Object[] params) {
        if (params == null || params.length == 0) {
            return null;
        }
        Class[] result = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            result[i] = params[i].getClass();
        }
        return result;
    }
}
