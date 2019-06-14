package cn.com.flycash.stupidmock.runner;

import cn.com.flycash.stupidmock.classloader.StupidMockClassLoader;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class StupidMockJunit4Runner extends BlockJUnit4ClassRunner {

    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @param klass
     * @throws InitializationError if the test class is malformed.
     */
    public StupidMockJunit4Runner(Class<?> klass) throws InitializationError {
        super(mockClz(klass));
    }

    private static Class<?> mockClz(Class<?> klass) throws InitializationError {
        try {
            ClassLoader loader = new StupidMockClassLoader(klass);
            Class<?> afterModified = Class.forName(klass.getName(), true, loader);
            return afterModified;
        } catch (Exception e) {
            throw new InitializationError(e);
        }
    }
}
