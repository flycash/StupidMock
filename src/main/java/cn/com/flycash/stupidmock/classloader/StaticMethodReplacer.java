package cn.com.flycash.stupidmock.classloader;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class StaticMethodReplacer extends ClassVisitor {
    public StaticMethodReplacer(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (staticMethod(access)) {
            generateNewBody(access, name, descriptor, signature, exceptions);
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    private void generateNewBody(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        Type type = Type.getMethodType(descriptor);
        Type retType = type.getReturnType();
        Type[] paramTypes = type.getArgumentTypes();


    }

    private boolean staticMethod(int access) {
        return (access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC;
    }
}
