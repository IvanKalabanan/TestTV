package com.ivacompany.testtv.domain.interactors.impl;

import com.ivacompany.testtv.domain.interactors.interfaces.TelecastInteractor;
import com.ivacompany.testtv.domain.models.Telecast;
import com.ivacompany.testtv.domain.repository.APICalls;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iva on 07.08.17.
 */

public class TelecastInteractorImpl implements TelecastInteractor {

    private APICalls calls;

    public TelecastInteractorImpl(APICalls calls) {
        this.calls = calls;
    }

    @Override
    public void getTelecast(String uuid, int borderId, int direction, final TelecastRetrieveListener telecastRetrieveListener) {
        calls.getTelecasts(uuid, borderId, direction).enqueue(new Callback<List<Telecast>>() {

            @Override
            public void onResponse(Call<List<Telecast>> call, Response<List<Telecast>> response) {
                telecastRetrieveListener.onRetrieve(response.body());
            }

            @Override
            public void onFailure(Call<List<Telecast>> call, Throwable t) {

            }
        });
    }
}
