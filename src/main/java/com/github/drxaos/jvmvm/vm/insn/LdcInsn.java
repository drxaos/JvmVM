/**
 * Copyright (c) 2005 Nuno Cruces
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holders nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 **/

package com.github.drxaos.jvmvm.vm.insn;

import com.github.drxaos.jvmvm.vm.Types;
import com.github.drxaos.jvmvm.vm.VirtualMachine;
import com.github.drxaos.jvmvm.vm.ref.ClassRef;
import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.LDC;

public abstract class LdcInsn extends Insn {
    private Object value;

    public static Insn getInsn(Object cst, Class<?> cls) {
        LdcInsn res = null;
        if (cst instanceof Integer) res = new LdcIntegerInsn((Integer) cst);
        if (cst instanceof Long) res = new LdcLongInsn((Long) cst);
        if (cst instanceof Float) res = new LdcFloatInsn((Float) cst);
        if (cst instanceof Double) res = new LdcDoubleInsn((Double) cst);
        if (cst instanceof String) res = new LdcStringInsn((String) cst);
        if (cst instanceof Type) res = new LdcClassInsn((Type) cst, cls);
        assert res != null;
        res.value = cst;
        return res;
    }

    @Override
    public String toString() {
        return getOpcodeName(LDC) + (value == null ? " null" : " (" + value.getClass().getSimpleName() + ") " + value);
    }

    static final class LdcIntegerInsn extends LdcInsn {
        private final int i;

        LdcIntegerInsn(Integer i) {
            this.i = i;
        }

        public void execute(VirtualMachine vm) {
            vm.getFrame().pushInt(i);
        }
    }

    static final class LdcFloatInsn extends LdcInsn {
        private final float f;

        LdcFloatInsn(Float f) {
            this.f = f;
        }

        public void execute(VirtualMachine vm) {
            vm.getFrame().pushFloat(f);
        }
    }

    static final class LdcLongInsn extends LdcInsn {
        private final long l;

        LdcLongInsn(Long l) {
            this.l = l;
        }

        public void execute(VirtualMachine vm) {
            vm.getFrame().pushLong(l);
        }
    }

    static final class LdcDoubleInsn extends LdcInsn {
        private final double d;

        LdcDoubleInsn(Double d) {
            this.d = d;
        }

        public void execute(VirtualMachine vm) {
            vm.getFrame().pushDouble(d);
        }
    }

    static final class LdcStringInsn extends LdcInsn {
        private final String s;

        LdcStringInsn(String s) {
            this.s = s.intern();
        }

        public void execute(VirtualMachine vm) {
            vm.getFrame().pushObject(s);
        }
    }

    static final class LdcClassInsn extends LdcInsn {
        private final ClassRef c;

        LdcClassInsn(Type t, Class<?> c) {
            this.c = new ClassRef(Types.getInternalName(t), c);
        }

        public void execute(VirtualMachine vm) {
            vm.getFrame().pushObject(c.get());
        }
    }
}
