package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.cglib.StupidMockMethodInterceptorAdaptorImpl;
import cn.com.flycash.stupidmock.stub.IStub;
import cn.com.flycash.stupidmock.stub.StubBuilder;
import cn.com.flycash.stupidmock.stub.ThreadSafeStubBuilder;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

public class StupidMock {

    @SuppressWarnings("unchecked")
    public static <T> T mock(Class<T> tClass) {
        Enhancer enhancer = new Enhancer();

        if (tClass.isInterface()) {
            enhancer.setInterfaces(new Class[]{tClass});
        } else {
            enhancer.setSuperclass(tClass);
        }
        enhancer.setCallbackType(StupidMockMethodInterceptorAdaptorImpl.class);
        Objenesis objenesis = new ObjenesisStd(true);
        return (T) objenesis.newInstance(enhancer.createClass());
    }

    public static <T> StubBuilder<T> when(T methodCall) {
        return new ThreadSafeStubBuilder();
    }
}
