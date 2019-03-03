/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package gulerbu.com.userprofile.core;

public interface IPresenter<T extends BaseView> {

    /**
     * Attach view when it is ready to interact with presenter.
     *
     * @param view the view associated with this presenter
     */

    void attachView(T view);

    /**
     * Detach view when it is not accessible.
     */

    void detachView();
}
