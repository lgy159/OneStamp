package com.example.onestamp;

/* 사용자 계정 정보 모델 클래스 */

public class UserAccount {

    public UserAccount() { }

    private boolean store;

    public boolean getStore() { return store;}

    public void setStore(boolean store) {
        this.store = store;
    }

    private int stamp;

    public int getStamp() { return stamp;}

    public void setStamp(int stamp) {
        this.stamp = stamp;
    }

    private String stampAccount;

    public String getStampAccount() { return stampAccount;}

    public void setStampAccount(String stampAccount) {
        this.stampAccount = stampAccount;
    }

    private String phone;

    public String getPhone() { return phone;}

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) { this.idToken = idToken; }

    private String idToken;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    private String emailId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;



}