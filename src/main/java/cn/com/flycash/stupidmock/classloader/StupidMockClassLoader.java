package cn.com.flycash.stupidmock.classloader;

import cn.com.flycash.stupidmock.classloader.annotation.PrepareForTest;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class StupidMockClassLoader extends ClassLoader {

    //缓存
    private final ConcurrentMap<String, SoftReference<Class<?>>> classes;

    private final static String[] ALWAYS_IGNORE_PACKAGE = new String[]{
            "java.",
            "sun.",
            "jdk.",
            "org.junit."
    };


    private Set<String> clzNeedToModified = new HashSet<>();
    private String testClzName;
    public StupidMockClassLoader(Class<?> testClz) {
        super(Thread.currentThread().getContextClassLoader());
        PrepareForTest annotation = testClz.getAnnotation(PrepareForTest.class);
        if (annotation != null) {
            Class[] targets = annotation.targets();
            clzNeedToModified = Arrays.stream(targets)
                    .map(Class::getName)
                    .collect(Collectors.toSet());
        }

        classes = new ConcurrentHashMap<>();
        testClzName = testClz.getName();
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> result = findLoadedClass1(name);
            if (result != null) {
                return result;
            }
            if (!needLoadByThis(name)) {
                result = getParent().loadClass(name);

            } else {
                if (needModify(name)) {
                    result = loadModifiedClass(name);
                } else {
                    result = loadUnmodifiedClass(name);
                }

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

    private Class<?> loadUnmodifiedClass(String name) throws ClassNotFoundException {
        try {
            ClassReader reader = new ClassReader(name);
            ClassWriter writer = new ClassWriter(reader, 0);
            reader.accept(new DoNothingClassVisitor(writer), ClassReader.SKIP_CODE);
            byte[] bytes = writer.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }



    private Class<?> loadModifiedClass(String name) throws ClassNotFoundException {
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

    private boolean needModify(String name) {
        return clzNeedToModified.contains(name);
    }

    private boolean needLoadByThis(String name) {
        return Arrays.stream(ALWAYS_IGNORE_PACKAGE).noneMatch(name::startsWith) || name.equals(testClzName);
    }

    private static final class DoNothingClassVisitor extends ClassVisitor {
        public DoNothingClassVisitor(ClassVisitor classVisitor) {
            super(Opcodes.ASM7, classVisitor);
        }
    }
}
