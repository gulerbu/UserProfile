/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.profile;

import gulerbu.com.userprofile.core.BaseView;
import gulerbu.com.userprofile.core.IPresenter;

public interface ProfileContract {

    interface View extends BaseView {

        void updateUserName(String name);

        void updateUserPhone(String phoneNumber);

        void updateUserEmail(String userEmail);

        void updateUserProfilePicture(String url);

        void showGetUserError(String error);

        void showDeleteUserInfoError(String error);

        void showDeleteUserInfoSuccess(String message);

        void showDeleteOption(boolean show);

    }

    interface Presenter extends IPresenter<View> {

        void fetchUserInfo();

        void deleteUserPhone();

    }
}
