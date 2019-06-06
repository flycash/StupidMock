package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.classloader.annotation.PrepareForTest;
import cn.com.flycash.stupidmock.runner.StupidMockJunit4Runner;
import cn.com.flycash.stupidmock.testobj.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(StupidMockJunit4Runner.class)
@PrepareForTest(targets = {FinalObject.class})
public class StupidMockTest {

    @Test
    public void mockFinal() throws Exception {
        FinalObject mockObj = StupidMock.mock(FinalObject.class);
        assertNotNull(mockObj);
        FinalObjectUsage.printFinalObject(mockObj);
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