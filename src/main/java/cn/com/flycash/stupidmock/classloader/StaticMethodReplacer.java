package cn.com.flycash.stupidmock.classloader;

import org.objectweb.asm.*;

import javax.tools.OptionChecker;

public class StaticMethodReplacer extends ClassVisitor {
    private String clzName;
    public StaticMethodReplacer(int api, ClassVisitor classVisitor, String clzName) {
        super(api, classVisitor);
        this.clzName = clzName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (staticMethod(access)) {
            generateNewBody(access, name, descriptor, signature, exceptions);
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    private void generateNewBody(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                "skeleton",
                "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/Object;",
                null,
                null);
        mv.visitEnd();
    }

    private boolean staticMethod(int access) {
        return (access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC;
    }
}
