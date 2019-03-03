/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.core;

import java.lang.ref.WeakReference;

public class BasePresenter<View extends BaseView> implements IPresenter<View> {

    private WeakReference<View> viewRef;

    @Override
    public void attachView(View view) {
        viewRef = new WeakReference<>(view);

    }

    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
        }
        viewRef = null;
    }

    public View getView() {
        if (viewRef != null) {
            return viewRef.get();
        }

        return null;
    }

    public boolean isViewAttached() {
        return (viewRef != null && viewRef.get() != null);
    }
}
