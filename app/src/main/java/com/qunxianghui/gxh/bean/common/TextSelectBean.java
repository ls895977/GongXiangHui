package com.qunxianghui.gxh.bean.common;

import com.contrarywind.interfaces.IPickerViewData;

public class TextSelectBean implements IPickerViewData{
    int id;
  String itemText;

    public TextSelectBean(int id, String itemText) {
        this.id = id;
        this.itemText = itemText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    @Override
    public String getPickerViewText() {
        return itemText;
    }
}
