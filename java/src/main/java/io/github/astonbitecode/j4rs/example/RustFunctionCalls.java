/*
 * Copyright 2020 astonbitecode
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.astonbitecode.j4rs.example;

import static org.astonbitecode.j4rs.api.java2rust.Java2RustUtils.createInstance;
import static org.astonbitecode.j4rs.api.java2rust.Java2RustUtils.getObjectCasted;

import java.util.List;
import org.astonbitecode.j4rs.api.Instance;
public class RustFunctionCalls {
    private static native void fnnoargs();

    private static native void fnstringarg(Instance<String> s);

    private static native void fntwoargs(Instance<Integer> i1, Instance<Integer> i2);

    private static native void fnthreeargs(Instance<Integer> i1, Instance<Integer> i2, Instance<Integer> i3);

    private static native Instance addintegers(Instance<Integer> i1, Instance<Integer> i2);

    private static native void fncustomobject(Instance<MyClass> i);

    private static native Instance fncustomobjectret();

    private static native Instance throwexception();

    private static native void fnlist(Instance<List<Integer>> i);

    static {
        System.load("/Users/home/Android/j4rs-java-call-rust/rust/target/debug/librustlib.dylib");
    }

    public void doCallNoArgs() {
        fnnoargs();
    }

    public void doCallWithStringArg(String s) {
        fnstringarg(createInstance(s));
    }

    public void doCallWithTwoArgs(Integer i1, Integer i2) {
        fntwoargs(createInstance(i1), createInstance(i2));
    }

    public void doCallWithThreeArgs(Integer i1, Integer i2, Integer i3) {
        fnthreeargs(createInstance(i1), createInstance(i2), createInstance(i3));
    }

    public Integer addInRust(Integer i1, Integer i2) {
        Instance instance = addintegers(
                createInstance(i1),
                createInstance(i2));
        return getObjectCasted(instance);
    }

    public void doCallWithCustomClass(MyClass myClass) {
        fncustomobject(createInstance(myClass));
    }

    public MyClass doCallWithCustomClassRet() {
        Instance instance = fncustomobjectret();
        return getObjectCasted(instance);
    }

    public void throwExceptionFromRust() {
        throwexception();
    }

    public void doCallWithList(List<Integer> list) {
        fnlist(createInstance(list));
    }
}
