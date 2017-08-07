package com.ivacompany.testtv.domain.repository.base;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ivacompany.testtv.domain.Utils.Constants;
import com.ivacompany.testtv.domain.repository.APICalls;

import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rabbit on 10/24/16.
 */
public class RestAPICommunicator {

    private static final String TAG = "RestAPICommunicator";
    private static RestAPICommunicator instance;


    private Retrofit retrofit;

    public static RestAPICommunicator getInstance() {
        if (instance == null) {
            instance = new RestAPICommunicator();
        }
        return instance;
    }

    public RestAPICommunicator() {
        init();
    }

    private void init() {


        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // set your desired log level
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(
                        new OkHttpClient.Builder()
                                .addInterceptor(loggingInterceptor)
                                .connectTimeout(10, TimeUnit.SECONDS)
                                .writeTimeout(10, TimeUnit.SECONDS)
                                .readTimeout(30, TimeUnit.SECONDS)
                                .build()
                )
                .build();
    }

    public APICalls getCalls() {

        return retrofit.create(APICalls.class);
    }
}
