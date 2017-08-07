package com.ivacompany.testtv.presentation.presenters.interfaces;

import com.ivacompany.testtv.domain.models.Telecast;

import java.util.List;

/**
 * Created by iva on 07.08.17.
 */

public interface MainPresenter {

    void nextTelecastPage();

    void refreshTelecast();

    void checkRecyclerViewPagination(int lastVisibleItemPosition);

    void checkFloatingButtonVisibilityState(int firstVisibleItemPosition, int lastVisibleItemPosition);

    interface View {
        void showTelecast(List<Telecast> telecastList, boolean isAppendList);

        void showProgress();

        void hideProgress();

        void showError();

        void showScrollBt();

        void hideScrollBt();
    }

}
