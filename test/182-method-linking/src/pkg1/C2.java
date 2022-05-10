/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pkg1;

import pkg2.B;

public class C2 extends B {
    public void callC2Foo() {
        System.out.println("Calling pkg1.C2.foo on " + getClass().getName());
        foo();
    };

    // This overrides package-private method as public.
    public void foo() {
        System.out.println("pkg1.C2.foo");
    }
}
