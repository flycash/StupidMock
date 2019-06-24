package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.classloader.annotation.PrepareForTest;
import cn.com.flycash.stupidmock.runner.StupidMockJunit4Runner;
import cn.com.flycash.stupidmock.testobj.SimpleObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static cn.com.flycash.stupidmock.Matchers.*;


@PrepareForTest(targets = {SimpleObject.class})
@RunWith(StupidMockJunit4Runner.class)
public class WhenThenTest {
    @Test
    public void mockEqual() {
        SimpleObject simpleObject = StupidMock.mock(SimpleObject.class);
        StupidMock.when(simpleObject.doSomething(equal("a"), equal("b"))).thenReturn("Hello");
        String result = simpleObject.doSomething("a", "b");

        assertEquals("Hello", result);

        StupidMock.when(simpleObject.doSomething(equal("c"), equal("d"))).thenReturn("My name is Li");

        result = simpleObject.doSomething("c", "d");
        assertEquals("My name is Li", result);
    }

    @Test
    public void mockOneOf() {
        SimpleObject simpleObject = StupidMock.mock(SimpleObject.class);
        StupidMock.when(simpleObject.doSomething(oneOf("a", "b"), oneOf("c", "d")))
                .thenReturn("one of");
        assertEquals("one of", simpleObject.doSomething("a", "d"));
    }

    @Test
    public void mockAny() {
        SimpleObject simpleObject = StupidMock.mock(SimpleObject.class);
        StupidMock.when(simpleObject.doSomething(any(String.class), any(String.class)))
                .thenReturn("any");
        assertEquals("any", simpleObject.doSomething("aaaaa", "bbb"));
    }

    @Test
    public void mockAnswer() {
        SimpleObject simpleObject = StupidMock.mock(SimpleObject.class);
        StupidMock.when(simpleObject.doSomething(any(String.class), any(String.class)))
                .then(invocation -> {
                    Object[] args = invocation.getArgs();
                    if (args[0].equals("a")) {
                        if (args[1].equals("a1") || args[1].equals("a2")) {
                            return "hello, world";
                        }
                    }
                    return null;
                });

        String hello = simpleObject.doSomething("a", "a1");
        assertEquals("hello, world", hello);

        assertNull(simpleObject.doSomething("c", "a1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mockThrows() {
        SimpleObject simpleObject = StupidMock.mock(SimpleObject.class);
        StupidMock.when(simpleObject.doSomething(any(String.class), any(String.class)))
                .thenThrow(new IllegalArgumentException());
        simpleObject.doSomething("a", "c");
    }

    @Test
    public void voidMethod() {
        SimpleObject simpleObject = StupidMock.mock(SimpleObject.class);
        StupidMock.doNothing()
                .when(simpleObject)
                .voidMethod(any(String.class), any(String.class));
        simpleObject.voidMethod("a", "b");

        StupidMock.answer(invocation -> {
            System.out.println("I'm coming!");
            return null;
        }).when(simpleObject).voidMethod(any(String.class), any(String.class));

        simpleObject.voidMethod("a", "b");
    }

    @Test
    public void finalMethod() {
        SimpleObject simpleObject = StupidMock.mock(SimpleObject.class);
        StupidMock.when(simpleObject.finalMethod(any(String.class), any(String.class)))
                .thenReturn("final");
        String result = simpleObject.finalMethod("a", "b");
        assertEquals("final", result);
    }
}
