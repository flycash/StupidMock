package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.testobj.FinalObject;
import cn.com.flycash.stupidmock.testobj.NoDefaultConstructorClass;
import cn.com.flycash.stupidmock.testobj.SimpleInterface;
import org.junit.Test;

import static org.junit.Assert.*;

public class StupidMockTest {

    @Test
    public void mockFinal() throws Exception {
        FinalObject mockObj = StupidMock.mock(FinalObject.class);
        assertNotNull(mockObj);
    }

    @Test
    public void mockNoDefaultConstructor() throws Exception {
        NoDefaultConstructorClass noDefaultConstructorObj = StupidMock.mock(NoDefaultConstructorClass.class);
        assertNotNull(noDefaultConstructorObj);
    }

    @Test
    public void mockInterface() throws Exception {
        SimpleInterface obj = StupidMock.mock(SimpleInterface.class);
        assertNotNull(obj);
    }
}