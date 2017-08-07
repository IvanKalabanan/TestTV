package com.ivacompany.testtv.domain.interactors.impl;

import com.ivacompany.testtv.domain.interactors.interfaces.TelecastInteractor;
import com.ivacompany.testtv.domain.models.BaseModel;
import com.ivacompany.testtv.domain.repository.APICalls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iva on 07.08.17.
 */

public class TelecastInteractorImpl implements TelecastInteractor {

    public static final int NEXT_PAGE = 1;
    public static final int PREVIOUS_PAGE = -1;
    public static final int DEFAULT_PAGE = 0;

    private APICalls calls;

    public TelecastInteractorImpl(APICalls calls) {
        this.calls = calls;
    }

    @Override
    public void getTelecast(String uuid, int borderId, int direction, final TelecastRetrieveListener telecastRetrieveListener) {
        calls.getTelecasts(uuid, borderId, direction).enqueue(new Callback<BaseModel>() {

            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                telecastRetrieveListener.onRetrieve(response.body());
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                telecastRetrieveListener.onFailure();
            }
        });
    }
}
