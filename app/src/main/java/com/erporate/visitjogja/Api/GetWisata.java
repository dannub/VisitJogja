package com.erporate.visitjogja.Api;

import com.erporate.visitjogja.Model.Wisata;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetWisata {
    @SerializedName("result")
    String status;
    @SerializedName("data")
    List<Wisata> listDataWisata;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Wisata> getListDataWisata() {
        return listDataWisata;
    }

    public void setListDataWisata(List<Wisata> listDataWisata) {
        this.listDataWisata = listDataWisata;
    }
}
