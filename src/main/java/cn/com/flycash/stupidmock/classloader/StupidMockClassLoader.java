package cn.com.flycash.stupidmock.classloader;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

public class StupidMockClassLoader extends ClassLoader {

    public StupidMockClassLoader() {
        super(ClassLoader.getSystemClassLoader().getParent());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
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

}
