package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.cglib.StupidMockMethodInterceptorAdaptorImpl;
import cn.com.flycash.stupidmock.stub.DefaultValueStubImpl;
import cn.com.flycash.stupidmock.stub.StubBuilder;
import cn.com.flycash.stupidmock.stub.ThreadSafeStubBuilder;
import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.answer.DefaultValueAnswer;
import cn.com.flycash.stupidmock.stub.answer.RealCallAnswer;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;
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
        enhancer.setCallbackType(MethodInterceptor.class);
        enhancer.setUseFactory(true);
        Objenesis objenesis = new ObjenesisStd(true);
        Object proxy = objenesis.newInstance(enhancer.createClass());
        Factory factory = (Factory) proxy;
        factory.setCallbacks(new Callback[]{new StupidMockMethodInterceptorAdaptorImpl()});
        return (T) proxy;
    }

    public static <T> StubBuilder<T> when(T methodCall) {
        return new ThreadSafeStubBuilder<>();
    }

    @SuppressWarnings("unchecked")
    public static <T> StubBuilder<T> doNothing() {
        return answer((Answer) DefaultValueAnswer.INSTANCE);
    }

    public static <T> StubBuilder<T> answer(Answer<T> answer) {
        StubBuilder<T> builder = new ThreadSafeStubBuilder<>();
        builder.doAnswer(answer);
        return builder;
    }
}
