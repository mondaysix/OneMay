package com.oy.entity;

/**
 * Created by Lucky on 2016/11/2.
 */
public class CityEntity {


    /**
     * id : 1
     * province : 北京
     * city : 北京
     * district : 北京
     */

    private String id;
    private String province;
    private String city;
    private String district;
    public CityEntity(){

    }
    public CityEntity(String id, String province, String city, String district) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.district = district;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
