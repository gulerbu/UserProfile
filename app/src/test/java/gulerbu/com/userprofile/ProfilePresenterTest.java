/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import gulerbu.com.userprofile.data.model.User;
import gulerbu.com.userprofile.data.source.UserRepository;
import gulerbu.com.userprofile.profile.ProfileContract;
import gulerbu.com.userprofile.profile.ProfilePresenter;
import gulerbu.com.userprofile.util.MockResourceHelper;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProfilePresenterTest {

    private static User USER;
    @Mock
    private
    UserRepository userRepository;

    @Mock
    private
    ProfileContract.View view;

    @Captor
    private
    ArgumentCaptor<UserRepository.GetUserInfoCallback> getUserInfoCallback;

    @Captor
    private
    ArgumentCaptor<UserRepository.DeleteUserPhoneCallback> deleteUserPhoneCallback;

    private MockResourceHelper resourceHelper = new MockResourceHelper();
    private ProfilePresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        USER = new User("9bfb7d75-4f0e-4b09-aba7-1ce8a93d41da",
                "Wa",
                "vy",
                "+4900000101010",
                "wavy@wavy.com",
                "https://cdn-images-1.medium.com/max/1200/1*SXwAJmh1cMSi1vApbqCelQ@2x.png");

        presenter = new ProfilePresenter(userRepository, resourceHelper);
        presenter.attachView(view);

    }

    @Test
    public void testFetchUserInfoSuccessfulUpdateViews() {
        presenter.fetchUserInfo();
        verify(view).showProgress();
        verify(userRepository).fetchUserInfo(getUserInfoCallback.capture());
        getUserInfoCallback.getValue().onUserInfoFetched(USER);

        verify(view).dismissProgress();
        verify(view).showDeleteOption(true);
        verify(view).updateUserName(ArgumentMatchers.anyString());
        verify(view).updateUserPhone(ArgumentMatchers.anyString());
        verify(view).updateUserEmail(ArgumentMatchers.anyString());
        verify(view).updateUserProfilePicture(ArgumentMatchers.anyString());

    }

    @Test
    public void testFetchUserInfoErrorShowMessage() {
        presenter.fetchUserInfo();
        verify(view).showProgress();
        verify(userRepository).fetchUserInfo(getUserInfoCallback.capture());
        getUserInfoCallback.getValue().onErrorOccurred();

        verify(view).dismissProgress();
        verify(view).showDeleteOption(false);
        verify(view).showGetUserError(resourceHelper.getMockString());


    }

    @Test
    public void testDeleteUserPhoneSuccessShowMessageAndUpdatePhone() {
        presenter.deleteUserPhone();
        ArgumentCaptor<String> idArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(userRepository).deleteUserPhone(idArgumentCaptor.capture(), deleteUserPhoneCallback.capture());
        deleteUserPhoneCallback.getValue().onSuccess();

        verify(view).showDeleteUserInfoSuccess(resourceHelper.getMockString());
        verify(view).updateUserPhone(ArgumentMatchers.anyString());

    }

    @Test
    public void testDeleteUserPhoneErrorShowMessage() {
        presenter.deleteUserPhone();
        ArgumentCaptor<String> idArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(userRepository).deleteUserPhone(idArgumentCaptor.capture(), deleteUserPhoneCallback.capture());
        deleteUserPhoneCallback.getValue().onError();

        verify(view).showDeleteUserInfoError(resourceHelper.getMockString());

    }
}
