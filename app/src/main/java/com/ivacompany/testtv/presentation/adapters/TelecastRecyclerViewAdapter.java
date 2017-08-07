package com.ivacompany.testtv.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ivacompany.testtv.databinding.ItemTelecastBinding;
import com.ivacompany.testtv.domain.models.Telecast;
import com.ivacompany.testtv.presentation.PhotoActionListener;

import java.util.List;


/**
 * Created by iva on 07.08.17.
 */
public class TelecastRecyclerViewAdapter extends RecyclerView.Adapter<TelecastRecyclerViewAdapter.TelecastHolder> {

    private List<Telecast> items;
    private PhotoActionListener photoActionListener;

    public TelecastRecyclerViewAdapter(List<Telecast> items, PhotoActionListener photoActionListener) {
        this.items = items;
        this.photoActionListener = photoActionListener;
    }

    @Override
    public TelecastHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        return new TelecastHolder(ItemTelecastBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(TelecastHolder holder, int position) {
        Telecast item = items.get(position);
        photoActionListener.loadPhoto(holder.binding.telecastPhotoIv, item.getIconUrl());
        holder.binding.telecastNameTv.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    public void addTelecasts(List<Telecast> telecastList) {
        this.items.addAll(telecastList);
        notifyItemRangeInserted(items.size() - telecastList.size(), telecastList.size());
    }

    public void setTelecasts(List<Telecast> telecastList) {
        this.items = telecastList;
        notifyDataSetChanged();
    }

    public class TelecastHolder extends RecyclerView.ViewHolder {

        private ItemTelecastBinding binding;

        public TelecastHolder(ItemTelecastBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}