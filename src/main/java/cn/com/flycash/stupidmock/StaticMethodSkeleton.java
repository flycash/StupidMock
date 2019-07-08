package cn.com.flycash.stupidmock;

public class StaticMethodSkeleton {

    public static String skeleton(String a, String b) {
        return "aaaa, hello";
    }

    public static Object skeleton(String clzName,
                                  int access,
                                  String name,
                                  String descriptor,
                                  String signature,
                                  String[] exceptions) {
        System.out.println("I'm coming");
        return null;
    }

    public static Object invoke(String clzName,
                                int access,
                                String name,
                                String descriptor,
                                String signature,
                                String[] exceptions) {
        return skeleton(clzName, access, name, descriptor, signature, exceptions);
    }
}
