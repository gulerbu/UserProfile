/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.data.source;


import javax.inject.Inject;

import gulerbu.com.userprofile.data.model.User;
import gulerbu.com.userprofile.network.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository implements UserDataSource {

    private final UserService userService;

    public interface GetUserInfoCallback {

        void onUserInfoFetched(User user);

        void onErrorOccurred();

    }

    public interface DeleteUserPhoneCallback {

        void onSuccess();

        void onError();
    }

    @Inject
    UserRepository(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void fetchUserInfo(final GetUserInfoCallback callback) {
        Call<User> call = userService.getUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    callback.onUserInfoFetched(response.body());
                } else {
                    callback.onErrorOccurred();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onErrorOccurred();

            }
        });
    }

    @Override
    public void deleteUserPhone(String id, final DeleteUserPhoneCallback callback) {
        Call<Void> call = userService.deleteUser(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callback.onSuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError();

            }
        });
    }
}
