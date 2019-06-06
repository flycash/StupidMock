package cn.com.flycash.stupidmock;

import cn.com.flycash.stupidmock.testobj.SimpleObject;
import org.junit.Test;
import org.mockito.Mockito;

public class WhenThenTest {
    @Test
    public void mockInvoke() {
        SimpleObject simpleObject = Mockito.mock(SimpleObject.class);
        Mockito.when(simpleObject.doSomething("a", "b")).thenReturn("Hello");

        simpleObject.doSomething("a", "c");

        simpleObject.doSomething("a", "b");
    }
}
