package com.ivacompany.testtv.presentation.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ivacompany.testtv.R;
import com.ivacompany.testtv.databinding.ActivityMainBinding;
import com.ivacompany.testtv.domain.models.Telecast;
import com.ivacompany.testtv.domain.repository.base.RestAPICommunicator;
import com.ivacompany.testtv.presentation.PhotoActionListener;
import com.ivacompany.testtv.presentation.adapters.TelecastRecyclerViewAdapter;
import com.ivacompany.testtv.presentation.presenters.impl.MainPresenterImpl;
import com.ivacompany.testtv.presentation.presenters.interfaces.MainPresenter;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements MainPresenter.View, PhotoActionListener {

    private ActivityMainBinding binding;
    private MainPresenter presenter;

    private TelecastRecyclerViewAdapter telecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        presenter = new MainPresenterImpl(
                this,
                UUID.randomUUID().toString(),
                RestAPICommunicator.getInstance().getCalls()
        );
        initSwipeToRefresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                binding.swipeRefreshLayout.setRefreshing(true);
                presenter.refreshTelecast();
            }
        });
    }

    private void initSwipeToRefresh() {
        binding.swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent));
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshTelecast();
            }
        });
    }

    @Override
    public void showTelecast(List<Telecast> telecastList, boolean isAppendList) {
        binding.swipeRefreshLayout.setRefreshing(false);
        if (telecastAdapter != null) {
            if (isAppendList) {
                telecastAdapter.addTelecasts(telecastList);
                return;
            }
            telecastAdapter.setTelecasts(telecastList);
            return;
        }
        telecastAdapter = new TelecastRecyclerViewAdapter(telecastList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(telecastAdapter);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                presenter.checkRecyclerViewPagination(layoutManager.findLastVisibleItemPosition());
                if (dy > 0) {
                    presenter.checkFloatingButtonVisibilityState(
                            layoutManager.findFirstVisibleItemPosition(),
                            layoutManager.findLastVisibleItemPosition());
                return;
                }
                hideScrollBt();
            }
        });
    }

    @Override
    public void showProgress() {
        binding.swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        binding.swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(this, getString(R.string.server_occurred_problem), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showScrollBt() {
        binding.floatingBt.setVisibility(View.VISIBLE);
        binding.floatingBt.setClickable(true);
    }

    @Override
    public void hideScrollBt() {
        binding.floatingBt.setVisibility(View.INVISIBLE);
        binding.floatingBt.setClickable(false);
    }

    @Override
    public void loadPhoto(ImageView imageView, Object photo) {
        Glide
                .with(this)
                .load(photo)
                .thumbnail(0.5f)
                .into(imageView);
    }
}
