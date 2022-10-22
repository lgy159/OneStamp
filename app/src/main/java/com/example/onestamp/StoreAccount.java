package com.example.onestamp;

/* 사용자 계정 정보 모델 클래스 */

public class StoreAccount {

    private String store_number;

    public String getStore_number() { return store_number;}

    public void setStore_number(String store_number) { this.store_number = store_number; }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) { this.store_name = store_name; }

    private String store_name;

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    private String store_address;
}