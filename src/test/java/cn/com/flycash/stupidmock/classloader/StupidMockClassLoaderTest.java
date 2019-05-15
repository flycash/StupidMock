package cn.com.flycash.stupidmock.classloader;

import cn.com.flycash.stupidmock.testobj.FinalObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class StupidMockClassLoaderTest {

    @Test
    @SuppressWarnings("unchecked")
    public void findClass() throws Exception {
        StupidMockClassLoader classLoader = new StupidMockClassLoader();
        Class finalObjectClass =Class.forName(
                "cn.com.flycash.stupidmock.testobj.FinalObject",
                true,
                classLoader);

        FinalObject object = (FinalObject) finalObjectClass.getConstructor().newInstance();
        assertNotNull(object);
    }


}