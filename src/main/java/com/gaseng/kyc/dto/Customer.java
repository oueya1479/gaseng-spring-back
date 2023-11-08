package com.gaseng.kyc.dto;

public class Customer {
    private String name;
    private String birth;
    private String addr;
    private String job;

    public Customer(String name, String birth, String addr, String job) {
        this.name = name;
        this.birth = birth;
        this.addr = addr;
        this.job = job;
    }

    // 필요한 게터(getter) 및 세터(setter) 메서드

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}