package com.ivacompany.testtv.domain.models;

import java.util.List;

/**
 * Created by iva on 08.08.17.
 */

public class BaseModel {
    private List<Telecast> items;
    private int itemsNumber;

    public List<Telecast> getItems() {
        return items;
    }

    public void setItems(List<Telecast> items) {
        this.items = items;
    }

    public int getItemsNumber() {
        return itemsNumber;
    }

    public void setItemsNumber(int itemsNumber) {
        this.itemsNumber = itemsNumber;
    }
}
