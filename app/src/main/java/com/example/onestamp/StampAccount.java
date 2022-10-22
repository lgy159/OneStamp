package com.example.onestamp;

/* 사용자 계정 정보 모델 클래스 */

public class StampAccount {

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    private String store_address;

    public int getStamp() {
        return stamp;
    }

    public void setStamp(int stamp) {
        this.stamp = stamp;
    }

    private int stamp;
}