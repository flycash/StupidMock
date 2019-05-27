package cn.com.flycash.stupidmock.classloader;

import org.junit.Test;
import static org.junit.Assert.*;

public class StupidMockClassLoaderTest {

    @Test
    @SuppressWarnings("unchecked")
    public void findClass() throws Exception {
        StupidMockClassLoader classLoader = new StupidMockClassLoader(StupidMockClassLoaderTest.class);
        Class finalObjectClass =Class.forName(
                "cn.com.flycash.stupidmock.testobj.FinalObject",
                true,
                classLoader);
        Object object = finalObjectClass.getConstructor().newInstance();
        assertNotNull(object);
    }


}