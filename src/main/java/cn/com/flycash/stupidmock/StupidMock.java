package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.classloader.StupidMockClassLoader;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

public class StupidMock {

    @SuppressWarnings("unchecked")
    public static <T> T mock(Class<T> tClass) {
        Enhancer enhancer = new Enhancer();

        // is final class
        if ((tClass.getModifiers() & 16) > 0) {
            try {
                Class removeFinalClass = new StupidMockClassLoader().loadClass(tClass.getName());
                enhancer.setSuperclass(removeFinalClass);
                enhancer.setCallback(NoOp.INSTANCE);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (tClass.isInterface()) {
            enhancer.setInterfaces(new Class[]{tClass});
        } else {
            enhancer.setSuperclass(tClass);
        }

        return (T) enhancer.create();
    }
}
