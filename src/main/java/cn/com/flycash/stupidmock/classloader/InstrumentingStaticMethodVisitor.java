package cn.com.flycash.stupidmock.classloader;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.GeneratorAdapter;


public class InstrumentingStaticMethodVisitor extends GeneratorAdapter {

    public InstrumentingStaticMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        System.out.println("visit method insn");
        super.visitMethodInsn(Opcodes.INVOKESTATIC, "cn/com/flycash/stupidmock/stub/StaticMethodSkeleton",
                "skeleton", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", isInterface);
    }
}
