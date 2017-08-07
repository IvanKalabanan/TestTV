package com.ivacompany.testtv.domain.interactors.interfaces;

import com.ivacompany.testtv.domain.models.BaseModel;

/**
 * Created by iva on 31.05.17.
 */

public interface TelecastInteractor {

    void getTelecast(String uuid, int borderId, int direction, TelecastRetrieveListener telecastRetrieveListener);

    interface TelecastRetrieveListener{
        void onRetrieve(BaseModel baseModel);

        void onFailure();
    }

}
