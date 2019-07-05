package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.classloader.annotation.PrepareForTest;
import cn.com.flycash.stupidmock.runner.StupidMockJunit4Runner;
import cn.com.flycash.stupidmock.testobj.StaticObj;
import org.junit.Test;
import org.junit.runner.RunWith;


@PrepareForTest(targets = {StaticObj.class})
@RunWith(StupidMockJunit4Runner.class)
public class MockStaticMethodTest {

    @Test
    public void mockStaticMethod() {
        StupidMock.mockStatic(StaticObj.class);
        StupidMock.when(StaticObj.staticMethod("a", "b")).thenReturn("hello");
        StaticObj.staticMethod("a", "b");
    }

}
