package cn.com.flycash.stupidmock.classloader;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ClassCache {

    public static final ClassCache INSTANCE = new ClassCache();

    private final ConcurrentMap<String, Class> classes = new ConcurrentHashMap<>();

    private ClassCache() {

    }

    public void put(Class clz) {
        classes.put(clz.getName(), clz);
    }

    public Class get(String clzName) {
        switch (clzName) {
            case "int": return int.class;
            case "boolean":
                return boolean.class;
            case "byte":
                return byte.class;
            case "short":
                return short.class;
            case "long":
                return long.class;
            case "float":
                return float.class;
            case "double":
                return double.class;
            case "char":
                return char.class;
            case "void":
                return void.class;
        }
        return classes.get(clzName);
    }
}
