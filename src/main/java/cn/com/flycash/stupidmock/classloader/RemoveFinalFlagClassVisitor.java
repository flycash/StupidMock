package cn.com.flycash.stupidmock.classloader;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * this class visitor will remove the final tag of class declaration
 */
public class RemoveFinalFlagClassVisitor extends ClassVisitor {

    public RemoveFinalFlagClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM7, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, removeFinal(access), name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(
            final int access,
            final String name,
            final String descriptor,
            final String signature,
            final String[] exceptions) {

        MethodVisitor methodVisitor = this.cv.visitMethod(removeFinal(access), name, descriptor, signature, exceptions);
        return methodVisitor;
   }

    private boolean staticMethod(int access) {
        return (access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC;
    }

    private int removeFinal(int access) {
        if ((access & Opcodes.ACC_FINAL) == Opcodes.ACC_FINAL) {
            //remove the final flag
            return access ^ Opcodes.ACC_FINAL;
        }
        return access;
    }

}
