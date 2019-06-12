package cn.com.flycash.stupidmock.cglib;

import cn.com.flycash.stupidmock.Invocation;
import cn.com.flycash.stupidmock.MockObjectSkeleton;
import cn.com.flycash.stupidmock.stub.StubBuilder;
import cn.com.flycash.stupidmock.stub.ThreadSafeStubBuilder;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;
import cn.com.flycash.stupidmock.stub.args.FixedValueArgMatcherImpl;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 这个类只是作为一个"胶水"，将StupidMock的实现和cglib粘在一起。实际上的任务，都被委托给MockSkeleton自身去完成了。
 */
public class StupidMockMethodInterceptorAdaptorImpl implements MethodInterceptor {

    private MockObjectSkeleton skeleton = new MockObjectSkeleton();

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        if (method.getDeclaringClass() == Object.class) {
            return proxy.invokeSuper(obj, args);
        }

        StubBuilder stubBuilder = new ThreadSafeStubBuilder();
        stubBuilder.setTarget(obj)
                .setMethod(method)
                .addObserver(createdStub -> skeleton.addStub(createdStub));
        if (args != null && args.length != 0) {
            ArgMatcher[] matchers = Arrays.stream(args)
                    .map(FixedValueArgMatcherImpl::new)
                    .toArray(ArgMatcher[]::new);
            stubBuilder.setArgMatchers(matchers);
        }
        return skeleton.mock(new Invocation(obj, method, args));
    }
}
