package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.stub.DefaultValueStubImpl;
import cn.com.flycash.stupidmock.stub.IStub;

import java.util.*;

/**
 * mock对象的骨架。所有的跟mock相关的逻辑，都可以放在这里。
 */
public class MockObjectSkeleton {

    private final LinkedList<IStub> stubList = new LinkedList<>();

    @SuppressWarnings("unchecked")
    public synchronized  <T> T mock(Invocation invocation) {

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
