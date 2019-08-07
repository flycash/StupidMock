package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.classloader.ClassCache;
import cn.com.flycash.stupidmock.stub.DefaultValueStubImpl;
import cn.com.flycash.stupidmock.stub.IStub;
import cn.com.flycash.stupidmock.stub.StubBuilder;
import cn.com.flycash.stupidmock.stub.ThreadSafeStubBuilder;
import cn.com.flycash.stupidmock.stub.answer.Answer;
import cn.com.flycash.stupidmock.stub.answer.RealCallAnswer;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class StaticMethodSkeleton {

    private static final ConcurrentMap<String, LinkedBlockingQueue<IStub>> clzToStub = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static Object skeleton(String clzName,
                                  String name,
                                  String descriptor,
                                  Object[] args
    ) {
//        return "hello";
        LinkedBlockingQueue queue = clzToStub.computeIfAbsent(clzName, key-> new LinkedBlockingQueue<>());
        Type methodType = Type.getMethodType(descriptor);
        Class staticMethodClz = ClassCache.INSTANCE.get(clzName);
        Type[] targetMethodParamsType = methodType.getArgumentTypes();

        Class[] paramClzs = new Class[targetMethodParamsType.length];


        try {

            for (int i = 0; i < targetMethodParamsType.length; i++) {
                Class paramClz = ClassCache.INSTANCE.get(targetMethodParamsType[i].getClassName());
                paramClzs[i] = paramClz;
            }

            Method method = staticMethodClz.getDeclaredMethod(name, paramClzs);
            StubBuilder safeStubBuilder= new ThreadSafeStubBuilder();
            safeStubBuilder.setMethod(method)
                    .setTarget(null);
            safeStubBuilder.addObserver(stub -> queue.add(stub));
            Iterator<IStub> iStubs = queue.iterator();

            Invocation invocation = new Invocation(null, method, args);

            while (iStubs.hasNext()) {
                IStub stub = iStubs.next();
                if (stub.match(invocation)) {
                    return stub.getAnswer().answer(invocation);
                }
            }
            return DefaultValueStubImpl.INSTANCE.getAnswer().answer(invocation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Can not find the method. Class name: " + clzName
                    + ", method name: " + name +", descriptor:" + descriptor, e);
        }
    }
}