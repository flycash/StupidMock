package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.classloader.StupidMockClassLoader;
import cn.com.flycash.stupidmock.runner.StupidMockJunit4Runner;
import cn.com.flycash.stupidmock.testobj.FinalObject;
import cn.com.flycash.stupidmock.testobj.SimpleObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(StupidMockJunit4Runner.class)
public class StupidMockTest {

    @Test
    public void mockFinal() throws Exception {
        FinalObject mockObj = StupidMock.mock(FinalObject.class);
        assertNotNull(mockObj);
    }
}