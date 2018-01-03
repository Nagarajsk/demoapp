package com.example.bitjini.demoapp;

/**
 * Created by bitjini on 16/12/17.
 */

public class DataProvider {

    private int img_res;

    private String title;

    public DataProvider(int img_res, String title) {
        this.setImg_res(img_res);
        this.set_title(title);
    }

    public int getImg_res() {
        return img_res;
    }


    public String get_title() {
        return title;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public void set_title(String title) {
        this.title = title;
    }
}
