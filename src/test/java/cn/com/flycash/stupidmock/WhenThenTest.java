package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.testobj.SimpleObject;
import org.junit.Test;

import static org.junit.Assert.*;


public class WhenThenTest {
    @Test
    public void mockInvoke() {
        SimpleObject simpleObject = StupidMock.mock(SimpleObject.class);
        StupidMock.when(simpleObject.doSomething("a", "b")).thenReturn("Hello");
        String result = simpleObject.doSomething("a", "b");

        assertEquals("Hello", result);

//
//        simpleObject.doSomething("a", "c");

//        simpleObject.doSomething("a", "b");
    }
}
