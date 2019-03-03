/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.profile;

import dagger.Binds;
import dagger.Module;
import gulerbu.com.userprofile.di.ActivityScope;

@Module
public abstract class ProfileModule {

    @ActivityScope
    @Binds
    abstract ProfileContract.Presenter profilePresenter(ProfilePresenter presenter);
}
