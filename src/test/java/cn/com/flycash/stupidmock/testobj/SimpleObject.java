package cn.com.flycash.stupidmock.testobj;

public class SimpleObject {

    public String doSomething(String a, String b) {
        return a + b;
    }

    public void voidMethod(String a, String b) {
        System.out.println(a+b);
    }

    public final String finalMethod(String a, String b) {
        return a + b;
    }
}
