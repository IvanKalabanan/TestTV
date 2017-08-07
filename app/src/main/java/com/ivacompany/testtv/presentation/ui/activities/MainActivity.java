package com.ivacompany.testtv.presentation.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ivacompany.testtv.R;
import com.ivacompany.testtv.domain.models.Telecast;
import com.ivacompany.testtv.presentation.presenters.interfaces.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showTelecast(List<Telecast> telecastList) {

    }
}
