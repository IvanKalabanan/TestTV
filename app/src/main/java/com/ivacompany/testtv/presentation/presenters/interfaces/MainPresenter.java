package com.ivacompany.testtv.presentation.presenters.interfaces;

import com.ivacompany.testtv.domain.models.Telecast;

import java.util.List;

/**
 * Created by iva on 07.08.17.
 */

public interface MainPresenter {

    void getTelecast(String uuid);

    interface View {
        void showTelecast(List<Telecast> telecastList);
    }

}
