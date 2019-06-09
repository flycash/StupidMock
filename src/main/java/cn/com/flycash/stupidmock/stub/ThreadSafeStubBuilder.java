package cn.com.flycash.stupidmock.stub;

/**
 * 这是一个Decorator模式。
 */
public class ThreadSafeStubBuilder implements StubBuilder {

    private static final ThreadLocal<StubBuilder> stubBuilder = new ThreadLocal<>();

    @Override
    public IStub build() {
        return null;
    }
}
