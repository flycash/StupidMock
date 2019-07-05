package cn.com.flycash.stupidmock.classloader;

import org.objectweb.asm.MethodVisitor;


public class InstrumentingStaticMethodVisitor extends MethodVisitor {

    public InstrumentingStaticMethodVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
//        System.out.println("opcode: " + opcode);
//        System.out.println("owner: " + owner);
//        System.out.println("name: " + name);
//        System.out.println("descriptor: " + name);
//        System.out.println("isInterface: " + name);
//        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        mv.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }
}
