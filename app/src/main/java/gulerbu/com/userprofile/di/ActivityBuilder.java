/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import gulerbu.com.userprofile.profile.ProfileActivity;
import gulerbu.com.userprofile.profile.ProfileModule;

@Module
public abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = ProfileModule.class)
    abstract ProfileActivity bindProfileActivity();

}
