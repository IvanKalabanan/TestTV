package com.ivacompany.testtv.presentation.presenters.impl;

import com.ivacompany.testtv.presentation.presenters.interfaces.MainPresenter;

/**
 * Created by iva on 07.08.17.
 */

public class MainPresenterImpl implements MainPresenter {

    private View view;

    public MainPresenterImpl(View view, String uuid) {
        this.view = view;
    }

    @Override
    public void getTelecast(String uuid) {

    }
}
