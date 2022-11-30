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

package android.test.app;

import android.test.lib.TestUtils;
import android.test.systemsharedlib.SystemSharedLib;
import androidx.test.filters.SmallTest;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class VendorAppTest {
    @Test
    public void testLoadExtendedPublicLibraries() {
        TestUtils.assertLinkerNamespaceError(() -> System.loadLibrary("foo.oem1"));
        TestUtils.assertLinkerNamespaceError(() -> System.loadLibrary("bar.oem1"));
        TestUtils.assertLinkerNamespaceError(() -> System.loadLibrary("foo.oem2"));
        TestUtils.assertLinkerNamespaceError(() -> System.loadLibrary("bar.oem2"));
        System.loadLibrary("foo.product1");
        System.loadLibrary("bar.product1");
    }

    @Test
    public void testLoadPrivateLibraries() {
        TestUtils.assertLinkerNamespaceError(() -> System.loadLibrary("system_private1"));
        TestUtils.assertLibraryNotFound(() -> System.loadLibrary("product_private1"));
        // TODO(mast): The vendor app fails to load a private vendor library because it gets
        // classified as untrusted_app in SELinux, which doesn't have access to vendor_file. Even an
        // app in /vendor/priv-app, which gets classified as priv_app, still doesn't have access to
        // vendor_file. Check that the test setup is correct and if this is WAI.
        TestUtils.assertLibraryNotFound(() -> System.loadLibrary("vendor_private1"));
    }

    @Test
    public void testLoadPrivateLibrariesViaSystemSharedLib() {
        // TODO(b/237577392): Fix this use case.
        TestUtils.assertLinkerNamespaceError(() -> SystemSharedLib.loadLibrary("system_private2"));

        TestUtils.assertLibraryNotFound(() -> SystemSharedLib.loadLibrary("product_private2"));
        TestUtils.assertLibraryNotFound(() -> SystemSharedLib.loadLibrary("vendor_private2"));
    }
}
