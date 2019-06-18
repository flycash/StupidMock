package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.answer.Returns;
import cn.com.flycash.stupidmock.stub.answer.Throws;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractStubBuilder<T> implements StubBuilder<T> {

    private List<BuildingStubObserver> observerList = new LinkedList<>();

    @Override
    public void thenReturn(T obj) {
        this.then(new Returns<>(obj));
    }

    @Override
    public void thenThrow(RuntimeException e) {
        this.then(new Throws<>(e));
    }

    @Override
    public void addObserver(BuildingStubObserver observer) {
        observerList.add(observer);
    }

    @Override
    public IStub<T> build() {
        IStub<T> stub = doBuild();
        observerList.forEach(observer -> observer.notify(stub));
        return stub;
    }

    @Override
    public <MOCK> MOCK when(MOCK mock) {
        this.setTarget(mock);
        return mock;
    }

    protected abstract IStub<T> doBuild();
}
