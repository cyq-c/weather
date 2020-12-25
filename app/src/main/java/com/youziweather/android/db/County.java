package com.youziweather.android.db;

import org.litepal.crud.LitePalSupport;

public class County extends LitePalSupport {
    /*
    * id            记录县的ID
    * countyName    记录县的名字
    * weatherId     记录县所对应天气的ID
    * cityId        记录当前县所属的市的ID值
    * */
    private int id;
    private String countyName;
    private String weatherId;
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
