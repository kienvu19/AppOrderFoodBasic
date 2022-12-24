package com.example.OrderFoodAppbyKienVu;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Food implements Serializable{

    private int image;

    private String name;

    private int gia;

    private String gioithieu;

    public Food(int image, String name, int gia, String gioithieu) {
        this.image = image;
        this.name = name;

        this.gia = gia;

        this.gioithieu = gioithieu;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getGioithieu() {
        return gioithieu;
    }

    public void setGioithieu(String gioithieu) {
        this.gioithieu = gioithieu;
    }
}
