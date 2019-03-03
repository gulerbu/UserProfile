/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.profile;

import javax.inject.Inject;

import gulerbu.com.userprofile.R;
import gulerbu.com.userprofile.core.BasePresenter;
import gulerbu.com.userprofile.data.model.User;
import gulerbu.com.userprofile.data.source.UserRepository;
import gulerbu.com.userprofile.util.ResourceHelper;

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {

    private final UserRepository repository;
    private String userId;
    private ResourceHelper resourceHelper;

    @Inject
    public ProfilePresenter(UserRepository repository, ResourceHelper resourceHelper) {
        this.repository = repository;
        this.resourceHelper = resourceHelper;
    }

    @Override
    public void fetchUserInfo() {
        if (getView() != null) {
            getView().showProgress();
        }
        repository.fetchUserInfo(new UserRepository.GetUserInfoCallback() {
            @Override
            public void onUserInfoFetched(User user) {
                userId = user.getId();
                if (getView() != null) {
                    getView().dismissProgress();
                    getView().showDeleteOption(true);
                    getView().updateUserEmail(user.getEmail());
                    getView().updateUserName(user.getFirstName() + " " + user.getLastName());
                    getView().updateUserPhone(user.getPhoneNumber());
                    getView().updateUserProfilePicture(user.getProfilePicture());
                }

            }

            @Override
            public void onErrorOccurred() {
                if (getView() != null) {
                    getView().dismissProgress();
                    getView().showDeleteOption(false);
                    getView().showGetUserError(resourceHelper.getString(R.string.get_user_info_error_message));
                }

            }
        });

    }

    @Override
    public void deleteUserPhone() {
        repository.deleteUserPhone(userId, new UserRepository.DeleteUserPhoneCallback() {
            @Override
            public void onSuccess() {
                if (getView() != null) {
                    getView().showDeleteUserInfoSuccess(resourceHelper.getString(R.string.delete_phone_success_message));
                    getView().updateUserPhone("-");
                }

            }

            @Override
            public void onError() {
                if (getView() != null) {
                    getView().showDeleteUserInfoError(resourceHelper.getString(R.string.delete_phone_error_message));
                }

            }
        });

    }
}
