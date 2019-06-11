package cn.com.flycash.stupidmock.stub;

import cn.com.flycash.stupidmock.stub.answer.Returns;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractStubBuilder<T> implements StubBuilder<T> {

    protected List<BuildingStubObserver> observerList = new LinkedList<>();

    @Override
    public StubBuilder<T> thenReturn(T obj) {
        return this.then(new Returns<>(obj));
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

    protected abstract IStub<T> doBuild();
}
