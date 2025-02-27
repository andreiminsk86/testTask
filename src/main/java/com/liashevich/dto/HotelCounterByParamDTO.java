package com.liashevich.dto;

public class HotelCounterByParamDTO {
    private String param;
    private long counter;

    public HotelCounterByParamDTO(String param, long counter) {
        this.param = param;
        this.counter = counter;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
