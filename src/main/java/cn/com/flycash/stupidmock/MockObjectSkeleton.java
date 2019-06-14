package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.stub.DefaultValueStubImpl;
import cn.com.flycash.stupidmock.stub.IStub;
import cn.com.flycash.stupidmock.stub.StubBuilder;
import cn.com.flycash.stupidmock.stub.ThreadSafeStubBuilder;

import java.util.LinkedList;

/**
 * mock对象的骨架。所有的跟mock相关的逻辑，都可以放在这里。
 */
public class MockObjectSkeleton {

    private final LinkedList<IStub> stubList = new LinkedList<>();

    @SuppressWarnings("unchecked")
    public synchronized <T> T mock(Invocation invocation) {

        StubBuilder stubBuilder = new ThreadSafeStubBuilder();
        stubBuilder.setTarget(invocation.getInvoker())
                .setMethod(invocation.getMethod())
                .addObserver(this::addStub);

        return (T) stubList.stream()
                .filter(stub -> stub.match(invocation))
                .findFirst()
                .orElse(DefaultValueStubImpl.INSTANCE)
                .getAnswer()
                .answer(invocation);
    }

    public synchronized void addStub(IStub stub) {
        stubList.addFirst(stub);
    }
}
