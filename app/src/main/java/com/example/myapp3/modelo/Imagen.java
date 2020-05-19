package com.example.myapp3.modelo;

public class Imagen {
    int id;
    String name, tumbnail_url, provider, price, delivery;

    public Imagen(int id, String name, String tumbnail_url, String price, String provider, String delivery) {
        this.id = id;
        this.name = name;
        this.tumbnail_url = tumbnail_url;
        this.provider = provider;
        this.price = price;
        this.delivery = delivery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTumbnail_url() {
        return tumbnail_url;
    }

    public void setTumbnail_url(String tumbnail_url) {
        this.tumbnail_url = tumbnail_url;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
