package cn.com.flycash.stupidmock.cglib;

import cn.com.flycash.stupidmock.MockObjectSkeleton;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 这个类只是作为一个"胶水"，将StupidMock的实现和cglib粘在一起。实际上的任务，都被委托给MockSkeleton自身去完成了。
 */
public class StupidMockMethodInterceptorAdaptorImpl implements MethodInterceptor {

    private MockObjectSkeleton skeleton = new MockObjectSkeleton();

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        proxy.i
    }
}
