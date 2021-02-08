package com.hyh.dang.entity;

import java.io.Serializable;

public class ConsulInfoKey implements Serializable {
    private String address;

    private String service;

    private static final long serialVersionUID = 1L;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service == null ? null : service.trim();
    }
}