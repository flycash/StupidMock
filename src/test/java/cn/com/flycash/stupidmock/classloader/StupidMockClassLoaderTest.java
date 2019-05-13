package cn.com.flycash.stupidmock.classloader;

import cn.com.flycash.stupidmock.StupidMock;
import cn.com.flycash.stupidmock.testobj.FinalObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class StupidMockClassLoaderTest {

    @Test
    @SuppressWarnings("unchecked")
    public void findClass() throws Exception {
        StupidMockClassLoader classLoader = new StupidMockClassLoader();
        Class<FinalObject> originClass = FinalObject.class;
        Class<FinalObject> finalObjectClass = (Class<FinalObject>) classLoader.findClass("cn.com.flycash.stupidmock.testobj.FinalObject");
        System.out.println(originClass.getModifiers());
        System.out.println(finalObjectClass.getModifiers());
        assertEquals(16, originClass.getModifiers()- finalObjectClass.getModifiers());
    }


}