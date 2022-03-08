package com.yjxxt.dto;

public class Dto {

    private String value;
    private String title;
    private Integer price;



    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Dto() {
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Dto{" +
                "value='" + value + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Dto(String value, String title, Integer price) {
        this.value = value;
        this.title = title;
        this.price = price;
    }
}
