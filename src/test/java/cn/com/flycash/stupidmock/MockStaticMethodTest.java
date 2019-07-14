package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.classloader.annotation.PrepareForTest;
import cn.com.flycash.stupidmock.runner.StupidMockJunit4Runner;
import cn.com.flycash.stupidmock.testobj.StaticObj;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


@PrepareForTest(targets = {StaticObj.class})
@RunWith(StupidMockJunit4Runner.class)
public class MockStaticMethodTest {

    @Test
    public void mockStaticMethod() {
        StupidMock.when(StaticObj.staticMethod(Matchers.any(String.class), Matchers.any(String.class))).thenReturn("hello");
        String result = StaticObj.staticMethod("a", "b");
        assertEquals("hello", result);
    }

    @Test
    public void mockIntStaticMethod() {

        StupidMock.when(StaticObj.intMethod(Matchers.any(int.class), Matchers.any(int.class))).thenReturn(12);
        assertEquals(12, StaticObj.intMethod(123, 345));
    }

}
