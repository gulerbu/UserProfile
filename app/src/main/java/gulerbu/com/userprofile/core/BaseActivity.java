/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.core;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(getContentResourceLayoutId());
        getPresenter().attachView(this);
        initUserInterface();
    }

    @Override
    protected void onDestroy() {
        getPresenter().detachView();
        super.onDestroy();
    }

    /**
     * Method for user interface related operations.
     */
    @CallSuper
    protected void initUserInterface() {
        ButterKnife.bind(this);
    }

    @LayoutRes
    protected abstract int getContentResourceLayoutId();

    protected abstract IPresenter getPresenter();

}
