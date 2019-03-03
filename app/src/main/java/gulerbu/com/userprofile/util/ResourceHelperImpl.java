/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.util;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class ResourceHelperImpl implements ResourceHelper {

    private Context appContext;

    @Inject
    public ResourceHelperImpl(Context appContext) {
        this.appContext = appContext;
    }

    @NonNull
    @Override
    public String getString(int stringRes) {
        return appContext.getString(stringRes);
    }
}
