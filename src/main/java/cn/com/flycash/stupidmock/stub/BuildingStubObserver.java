package cn.com.flycash.stupidmock.stub;

@FunctionalInterface
public interface BuildingStubObserver {
    void notify(IStub createdStub);
}
