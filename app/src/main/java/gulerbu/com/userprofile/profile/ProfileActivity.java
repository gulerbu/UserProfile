/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.profile;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import gulerbu.com.userprofile.R;
import gulerbu.com.userprofile.core.BaseActivity;
import gulerbu.com.userprofile.core.IPresenter;
import gulerbu.com.userprofile.util.GlideApp;

public class ProfileActivity extends BaseActivity implements ProfileContract.View {

    @Inject
    ProfileContract.Presenter profilePresenter;

    @BindView(R.id.activity_profile_view_switcher)
    ViewSwitcher viewSwitcher;

    @BindView(R.id.activity_profile_view_switcher_info)
    ViewSwitcher viewSwitcherInfo;

    @BindView(R.id.activity_profile_constraint_layout)
    ConstraintLayout constraintLayout;

    @BindView(R.id.activity_profile_text_view_name)
    AppCompatTextView textViewName;

    @BindView(R.id.layout_user_info_text_view_phone)
    AppCompatTextView textViewPhone;

    @BindView(R.id.layout_user_info_text_view_email)
    AppCompatTextView textViewEmail;

    @BindView(R.id.activity_profile_image_view_profile_image)
    ImageView imageViewProfilePicture;

    @BindView(R.id.layout_error_text_view_message)
    AppCompatTextView textViewErrorMessage;

    private MenuItem menuItemDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profilePresenter.fetchUserInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        menuItemDelete = menu.findItem(R.id.action_delete);
        menuItemDelete.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            profilePresenter.deleteUserPhone();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentResourceLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected IPresenter getPresenter() {
        return profilePresenter;
    }

    @Override
    public void showProgress() {
        viewSwitcher.setDisplayedChild(0);

    }

    @Override
    public void dismissProgress() {
        viewSwitcher.setDisplayedChild(1);

    }

    @Override
    public void updateUserName(String name) {
        textViewName.setText(name);
        viewSwitcherInfo.setDisplayedChild(1);

    }

    @Override
    public void updateUserPhone(String phoneNumber) {
        textViewPhone.setText(phoneNumber);

    }

    @Override
    public void updateUserEmail(String userEmail) {
        textViewEmail.setText(userEmail);

    }

    @Override
    public void updateUserProfilePicture(String url) {
        GlideApp.with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.profile_picture_placeholder)
                .dontAnimate()
                .into(imageViewProfilePicture);

    }

    @Override
    public void showGetUserError(String error) {
        viewSwitcherInfo.setDisplayedChild(0);
        textViewErrorMessage.setText(error);

    }

    @Override
    public void showDeleteUserInfoError(String error) {
        Snackbar.make(constraintLayout, error, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        profilePresenter.deleteUserPhone();
                    }
                }).show();
    }

    @Override
    public void showDeleteUserInfoSuccess(String message) {
        Snackbar.make(constraintLayout, message, Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void showDeleteOption(boolean show) {
        menuItemDelete.setVisible(show);
    }

    @OnClick(R.id.layout_error_linear_layout_container)
    public void onTapToRetryClick() {
        profilePresenter.fetchUserInfo();
    }
}
