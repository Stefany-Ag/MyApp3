package com.example.myapp3.modelo;

public class Producto {
    String name, imag_url, desc;
    long id;

    public Producto(long id, String name, String imag_url, String desc) {
        this.id = id;
        this.name = name;
        this.imag_url = imag_url;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImag_url() {
        return imag_url;
    }

    public void setImag_url(String imag_url) {
        this.imag_url = imag_url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
