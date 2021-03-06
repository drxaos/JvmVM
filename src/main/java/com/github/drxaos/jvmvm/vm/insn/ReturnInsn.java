package com.github.drxaos.jvmvm.vm.insn;

import com.github.drxaos.jvmvm.vm.Frame;
import com.github.drxaos.jvmvm.vm.VirtualMachine;

import static org.objectweb.asm.Opcodes.*;

public class ReturnInsn extends Insn {
    static final ReturnInsn instance = new ReturnInsn();

    public static Insn getInsn(int opcode) {
        switch (opcode) {
            case IRETURN:
                return IReturnInsn.instance;
            case LRETURN:
                return LReturnInsn.instance;
            case FRETURN:
                return FReturnInsn.instance;
            case DRETURN:
                return DReturnInsn.instance;
            case ARETURN:
                return AReturnInsn.instance;
            default:
                return instance;
        }
    }

    public void execute(VirtualMachine vm) {
        Frame frame = vm.getFrame();
        Frame parent = frame.getParent();
        if (parent != null) parent = parent.getMutableCopy();
        vm.setResult(null);
        vm.setCp(frame.getRet());
        vm.setFrame(parent);
    }

    public boolean canReturn(Class cls) {
        return cls == void.class;
    }

    @Override
    public String toString() {
        return getOpcodeName(RETURN);
    }


    static final class IReturnInsn extends ReturnInsn {
        static final IReturnInsn instance = new IReturnInsn();

        public void execute(VirtualMachine vm) {
            Frame frame = vm.getFrame();
            Frame parent = frame.getParent();
            if (parent != null) {
                parent = parent.getMutableCopy();
                parent.pushInt(frame.popInt());
            } else {
                vm.setResult(frame.popInt());
            }
            vm.setCp(frame.getRet());
            vm.setFrame(parent);
        }

        public boolean canReturn(Class cls) {
            return cls == int.class || cls == byte.class || cls == short.class || cls == char.class || cls == boolean.class;
        }

        @Override
        public String toString() {
            return getOpcodeName(IRETURN);
        }
    }

    static final class LReturnInsn extends ReturnInsn {
        static final LReturnInsn instance = new LReturnInsn();

        public void execute(VirtualMachine vm) {
            Frame frame = vm.getFrame();
            Frame parent = frame.getParent();
            if (parent != null) {
                parent = parent.getMutableCopy();
                parent.pushLong(frame.popLong());
            } else {
                vm.setResult(frame.popLong());
            }
            vm.setCp(frame.getRet());
            vm.setFrame(parent);
        }

        public boolean canReturn(Class cls) {
            return cls == long.class;
        }

        @Override
        public String toString() {
            return getOpcodeName(LRETURN);
        }
    }

    static final class FReturnInsn extends ReturnInsn {
        static final FReturnInsn instance = new FReturnInsn();

        public void execute(VirtualMachine vm) {
            Frame frame = vm.getFrame();
            Frame parent = frame.getParent();
            if (parent != null) {
                parent = parent.getMutableCopy();
                parent.pushFloat(frame.popFloat());
            } else {
                vm.setResult(frame.popFloat());
            }
            vm.setCp(frame.getRet());
            vm.setFrame(parent);
        }

        public boolean canReturn(Class cls) {
            return cls == float.class;
        }

        @Override
        public String toString() {
            return getOpcodeName(FRETURN);
        }
    }

    static final class DReturnInsn extends ReturnInsn {
        static final DReturnInsn instance = new DReturnInsn();

        public void execute(VirtualMachine vm) {
            Frame frame = vm.getFrame();
            Frame parent = frame.getParent();
            if (parent != null) {
                parent = parent.getMutableCopy();
                parent.pushDouble(frame.popDouble());
            } else {
                vm.setResult(frame.popDouble());
            }
            vm.setCp(frame.getRet());
            vm.setFrame(parent);
        }

        public boolean canReturn(Class cls) {
            return cls == double.class;
        }

        @Override
        public String toString() {
            return getOpcodeName(DRETURN);
        }
    }

    static final class AReturnInsn extends ReturnInsn {
        static final AReturnInsn instance = new AReturnInsn();

        public void execute(VirtualMachine vm) {
            Frame frame = vm.getFrame();
            Frame parent = frame.getParent();
            if (parent != null) {
                parent = parent.getMutableCopy();
                parent.pushObject(frame.popObject());
            } else {
                vm.setResult(frame.popObject());
            }
            vm.setCp(frame.getRet());
            vm.setFrame(parent);
        }

        public boolean canReturn(Class cls) {
            return !cls.isPrimitive();
        }

        @Override
        public String toString() {
            return getOpcodeName(ARETURN);
        }
    }
}