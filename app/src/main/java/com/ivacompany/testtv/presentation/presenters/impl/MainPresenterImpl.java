package com.ivacompany.testtv.presentation.presenters.impl;

import com.ivacompany.testtv.domain.interactors.impl.TelecastInteractorImpl;
import com.ivacompany.testtv.domain.interactors.interfaces.TelecastInteractor;
import com.ivacompany.testtv.domain.models.BaseModel;
import com.ivacompany.testtv.domain.repository.APICalls;
import com.ivacompany.testtv.presentation.presenters.interfaces.MainPresenter;

/**
 * Created by iva on 07.08.17.
 */

public class MainPresenterImpl implements MainPresenter {

    private View view;

    private TelecastInteractor telecastInteractor;
    private String uuid;
    private int lastItemId;
    private int itemsCount = 0;

    private boolean isRetrieveDataNow;

    public MainPresenterImpl(View view, String uuid, APICalls calls) {
        this.view = view;
        this.uuid = uuid;
        telecastInteractor = new TelecastInteractorImpl(calls);
    }

    @Override
    public void nextTelecastPage() {
        view.showProgress();
        telecastInteractor.getTelecast(uuid, lastItemId, TelecastInteractorImpl.NEXT_PAGE, new TelecastInteractor.TelecastRetrieveListener() {
            @Override
            public void onRetrieve(BaseModel baseModel) {
                view.hideProgress();
                itemsCount += baseModel.getItemsNumber();
                lastItemId = baseModel.getItems().get(baseModel.getItems().size() - 1).getId();
                view.showTelecast(baseModel.getItems(), true);
                isRetrieveDataNow = false;
            }

            @Override
            public void onFailure() {
                view.showError();
            }
        });
    }

    @Override
    public void refreshTelecast() {
        view.showProgress();
        telecastInteractor.getTelecast(uuid, TelecastInteractorImpl.DEFAULT_PAGE, TelecastInteractorImpl.DEFAULT_PAGE, new TelecastInteractor.TelecastRetrieveListener() {
            @Override
            public void onRetrieve(BaseModel baseModel) {
                itemsCount = baseModel.getItemsNumber();
                view.showProgress();
                lastItemId = baseModel.getItems().get(baseModel.getItems().size() - 1).getId();
                view.showTelecast(baseModel.getItems(), false);
            }

            @Override
            public void onFailure() {
                view.showError();
            }
        });
    }

    @Override
    public void checkRecyclerViewPagination(int lastVisibleItemPosition) {
        if(itemsCount == lastVisibleItemPosition + 1 && !isRetrieveDataNow) {
            isRetrieveDataNow = true;
            nextTelecastPage();
        }
    }

    @Override
    public void checkFloatingButtonVisibilityState(int firstVisibleItemPosition, int lastVisibleItemPosition) {
        // 15 is a border for show/hide floating button
        if (lastVisibleItemPosition > 15) {
            view.showScrollBt();
        } else if (firstVisibleItemPosition <= 15) {
            view.hideScrollBt();
        }
    }
}
