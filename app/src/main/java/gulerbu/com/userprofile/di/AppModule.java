/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import gulerbu.com.userprofile.network.UserService;
import gulerbu.com.userprofile.util.ResourceHelper;
import gulerbu.com.userprofile.util.ResourceHelperImpl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {
    //expose Application as an injectable context
    @Binds
    abstract Context bindContext(Application application);

    @Singleton
    @Provides
    static UserService provideUserService() {
        return new Retrofit.Builder()
                .baseUrl("https://private-anon-9e5f71b553-test16231.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);

    }

    @Singleton
    @Provides
    static ResourceHelper provideResourceHelper(Context context) {
        return new ResourceHelperImpl(context);
    }


}