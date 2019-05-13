package cn.com.flycash.stupidmock.runner;

import cn.com.flycash.stupidmock.classloader.StupidMockClassLoader;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class StupidMockJunit4Runner extends BlockJUnit4ClassRunner {

    private static final ClassLoader classLoader = new StupidMockClassLoader();

    public StupidMockJunit4Runner(Class<?> klass) throws InitializationError {
        super(loadClass(klass));
    }

    @Override
    public void run(RunNotifier notifier) {
        Runnable runnable = () -> {
            super.run(notifier);
        };
        Thread thread = new Thread(runnable);
        thread.setContextClassLoader(classLoader);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Class<?> loadClass(Class<?> klass) throws InitializationError {
        try {
            Thread.currentThread().setContextClassLoader(classLoader);
            return classLoader.loadClass(klass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new InitializationError(e);
        }
    }
}
