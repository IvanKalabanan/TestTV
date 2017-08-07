package com.ivacompany.testtv.domain.repository;


import com.ivacompany.testtv.domain.Utils.Constants;
import com.ivacompany.testtv.domain.models.BaseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by iva on 11.04.17.
 */

public interface APICalls {

    @GET("/demo")
    Call<BaseModel> getTelecasts(
            @Query(Constants.SERIAL_NUMBER) String uuid,
            @Query(Constants.BORDER_ID) int borderId,
            @Query(Constants.DIRECTION) int direction);

}
