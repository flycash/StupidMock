package cn.com.flycash.stupidmock.classloader;

import cn.com.flycash.stupidmock.StaticMethodSkeleton;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class StaticMethodReplacer extends ClassVisitor {

    private static final String SKELETON_METHOD_NAME = "skeleton";

    private static Type skeletonType;
    private static Method skeletonMethod;

    private String clzName;

    static {
        try {
            skeletonType = Type.getType(StaticMethodSkeleton.class);
            skeletonMethod = Method.getMethod(StaticMethodSkeleton.class.getMethod(SKELETON_METHOD_NAME,
                    String.class, String.class, String.class, Object[].class));
        } catch (Exception e) {
            throw new RuntimeException("Can not get the static method skeleton", e);
        }
    }

    public StaticMethodReplacer(ClassVisitor classVisitor, String clzName) {
        super(Opcodes.ASM7, classVisitor);
        this.clzName = clzName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//        MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions);

        if (name.equals("<init>") || !staticMethod(access)) {
            return super.visitMethod(access, name, descriptor, signature, exceptions);
        }
        generateNewBody(access, name, descriptor, signature, exceptions);

        return super.visitMethod(access, "rename____" + name, descriptor, signature, exceptions);
    }

    private void generateNewBody(int access, String name, String descriptor, String signature, String[] exceptions) {
        try {
            MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);

            GeneratorAdapter adapter = new GeneratorAdapter(mv, access, name, descriptor);
            adapter.visitLdcInsn(clzName);
            adapter.visitLdcInsn(name);
            adapter.visitLdcInsn(descriptor);
            adapter.loadArgArray();
            adapter.invokeStatic(skeletonType, skeletonMethod);
//            adapter.returnValue();
            Type returnType = Type.getMethodType(descriptor).getReturnType();

            if (returnType == Type.VOID_TYPE) {
                mv.visitInsn(Opcodes.RETURN);
            } else if (returnType == Type.INT_TYPE) {
                String integerName = Type.getType(Integer.class).getInternalName();
                mv.visitTypeInsn(Opcodes.CHECKCAST, integerName);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, integerName, "intValue", "()I",false);
                mv.visitInsn(Opcodes.IRETURN);
            } else if (returnType == Type.SHORT_TYPE) {
                String shortName = Type.getType(Short.class).getInternalName();
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, shortName, "shortValue", "()S",false);
                mv.visitTypeInsn(Opcodes.CHECKCAST, shortName);
                mv.visitInsn(Opcodes.IRETURN);
            } else if (returnType == Type.CHAR_TYPE) {
                String charName = Type.getType(Character.class).getInternalName();
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, charName, "charValue", "()C",false);
                mv.visitTypeInsn(Opcodes.CHECKCAST, charName);
                mv.visitInsn(Opcodes.IRETURN);
            } else if (returnType == Type.BOOLEAN_TYPE) {
                String booleanName = Type.getType(Boolean.class).getInternalName();
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, booleanName, "booleanValue", "()Z",false);
                mv.visitTypeInsn(Opcodes.CHECKCAST, booleanName);
                mv.visitInsn(Opcodes.IRETURN);
            } else if (returnType == Type.BYTE_TYPE) {
                String byteName = Type.getType(Byte.class).getInternalName();
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, byteName, "byteValue", "()B",false);
                mv.visitTypeInsn(Opcodes.CHECKCAST, byteName);
                mv.visitInsn(Opcodes.IRETURN);
            } else if (returnType == Type.FLOAT_TYPE) {
                String floatName = Type.getType(Float.class).getInternalName();
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, floatName, "floatValue", "()F",false);
                mv.visitTypeInsn(Opcodes.CHECKCAST, floatName);
                mv.visitInsn(Opcodes.FRETURN);
            } else if (returnType == Type.DOUBLE_TYPE) {
                String doubleName = Type.getType(Double.class).getInternalName();
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, doubleName, "doubleValue", "()D",false);
                mv.visitTypeInsn(Opcodes.CHECKCAST, doubleName);
                mv.visitInsn(Opcodes.DRETURN);
            } else {
                mv.visitTypeInsn(Opcodes.CHECKCAST, returnType.getInternalName());
                mv.visitInsn(Opcodes.ARETURN);
            }
            adapter.endMethod();
        }catch (Exception e) {
            throw new RuntimeException(String.format("Can not modified the method, clz: %s, method: %s, descriptor: %s", clzName, name, descriptor));
        }
    }

    private boolean staticMethod(int access) {
        return (access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC;
    }
}
