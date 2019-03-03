/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.util;

import android.support.annotation.NonNull;

public class MockResourceHelper implements ResourceHelper {

    // Returns mock string for test purposes
    @NonNull
    @Override
    public String getString(int stringRes) {
        return "Mock";
    }

    @NonNull
    public String getMockString() {
        return "Mock";
    }
}
