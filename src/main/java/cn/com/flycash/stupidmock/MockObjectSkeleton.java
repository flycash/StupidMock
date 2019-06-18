package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.stub.DefaultValueStubImpl;
import cn.com.flycash.stupidmock.stub.IStub;
import cn.com.flycash.stupidmock.stub.StubBuilder;
import cn.com.flycash.stupidmock.stub.ThreadSafeStubBuilder;
import cn.com.flycash.stupidmock.stub.answer.Answer;

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
                .setMethod(invocation.getMethod());

        IStub<T> stub = stubList.stream()
                .filter(s -> s.match(invocation))
                .findFirst()
                .orElse(DefaultValueStubImpl.INSTANCE)
                ;
        Object result = stub.getAnswer()
                .answer(invocation);

        if (stubBuilder.prepare()) {
            stubList.addFirst(stubBuilder.build());
        } else {
            stubBuilder.addObserver(this::addStub);
        }

        return (T) result;
    }

    public synchronized void addStub(IStub stub) {
        stubList.addFirst(stub);
    }
}
