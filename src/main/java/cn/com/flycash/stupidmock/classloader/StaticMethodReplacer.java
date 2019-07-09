package cn.com.flycash.stupidmock.classloader;

import org.objectweb.asm.*;


public class StaticMethodReplacer extends ClassVisitor {
    private String clzName;
    public StaticMethodReplacer(ClassVisitor classVisitor, String clzName) {
        super(Opcodes.ASM7, classVisitor);
        this.clzName = clzName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (staticMethod(access)) {
            generateNewBody(access, name, descriptor, signature, exceptions);
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    public void generateNewBody(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("Hello, I'm new body");
//        System.out.println(this.clzName);
        MethodVisitor mv = super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                "skeleton",
                "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/Object;",
                null,
                null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(Opcodes.GETFIELD, "cn/com/flycash/stupidmock/classloader/StaticMethodReplacer",
                "clzName",
                "Ljava/lang/String;");
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ALOAD, 2);
        mv.visitVarInsn(Opcodes.ALOAD, 3);
        mv.visitVarInsn(Opcodes.ALOAD, 4);
        mv.visitVarInsn(Opcodes.ALOAD, 5);
        mv.visitMaxs(6, 6);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "cn/com/flycash/stupidmock/StaticMethodSkeleton",
                "skeleton",
                "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/Object;",
                false);
        mv.visitEnd();
    }

    private boolean staticMethod(int access) {
        return (access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC;
    }
}
