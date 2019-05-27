package cn.com.flycash.stupidmock.classloader;

import cn.com.flycash.stupidmock.classloader.annotation.PrepareForTest;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class StupidMockClassLoader extends ClassLoader {

    private final ConcurrentMap<String, SoftReference<Class<?>>> classes;

    private Set<String> clzNeedToMock = new HashSet<>();
    public StupidMockClassLoader(Class<?> testClz) {
        super(Thread.currentThread().getContextClassLoader());
        PrepareForTest annotation = testClz.getAnnotation(PrepareForTest.class);
        if (annotation != null) {
            Class[] targets = annotation.targets();
            clzNeedToMock = Arrays.stream(targets)
                    .map(Class::getName)
                    .collect(Collectors.toSet());
        }

        classes = new ConcurrentHashMap<>();
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> result = findLoadedClass1(name);
            if (result != null) {
                return result;
            }
            if (!clzNeedToMock.contains(name)) {
                result = getParent().loadClass(name);

            } else {
                result = loadMockClass(name);
            }
            if (result == null) {
                throw new ClassNotFoundException("Can not load the class: " + name);
            }

            if (resolve) {
                resolveClass(result);
            }
            classes.put(name, new SoftReference<>(result));
            return result;
        }
    }

    private Class<?> loadMockClass(String name) throws ClassNotFoundException {
        try {
            ClassReader reader = new ClassReader(name);
            ClassWriter writer = new ClassWriter(reader, 0);
            RemoveFinalFlagClassVisitor classVisitor = new RemoveFinalFlagClassVisitor(writer);
            reader.accept(classVisitor, ClassReader.SKIP_CODE);
            byte[] bytes = writer.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }

    private Class<?> findLoadedClass1(String name) {
        SoftReference<Class<?>> reference = classes.get(name);
        Class<?> clazz = null;
        if (reference != null) {
            clazz = reference.get();
        }
        if (clazz == null) {
            clazz = findLoadedClass(name);
        }
        return clazz;
    }

}
