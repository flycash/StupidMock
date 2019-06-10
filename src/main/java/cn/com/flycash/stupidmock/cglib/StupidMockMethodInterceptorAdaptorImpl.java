package cn.com.flycash.stupidmock.cglib;

import cn.com.flycash.stupidmock.MockObjectSkeleton;
import cn.com.flycash.stupidmock.stub.StubBuilder;
import cn.com.flycash.stupidmock.stub.ThreadSafeStubBuilder;
import cn.com.flycash.stupidmock.stub.args.ArgMatcher;
import cn.com.flycash.stupidmock.stub.args.FixedValueArgMatcherImpl;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 这个类只是作为一个"胶水"，将StupidMock的实现和cglib粘在一起。实际上的任务，都被委托给MockSkeleton自身去完成了。
 */
public class StupidMockMethodInterceptorAdaptorImpl implements MethodInterceptor {

    private MockObjectSkeleton skeleton = new MockObjectSkeleton();

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        StubBuilder stubBuilder = new ThreadSafeStubBuilder();
        stubBuilder.setTarget(obj)
                .setMethod(method)
                .setArgMatchers();
        return skeleton.invoke(method, args);
    }

    private ArgMatcher[] fetchMatchers(Object[] args) {
        if (args == null || args.length == 0) {
            return new ArgMatcher[0];
        }
        return Arrays.stream(args)
                .map(FixedValueArgMatcherImpl::new)
                .collect(Collectors.toList())
                .toArray(new ArgMatcher[args.length]);
    }
}
