package com.erporate.visitjogja.Model;

import com.google.gson.annotations.SerializedName;

public class Wisata {
    @SerializedName("nama_pariwisata")
    private String namaPariwisata;
    @SerializedName("alamat_pariwisata")
    private String alamatPariwisata;
    @SerializedName("gambar_pariwisata")
    private String gambarPariwisata;
    @SerializedName("detail_pariwisata")
    private String detailPariwisata;


    public Wisata(String namaPariwisata, String alamatPariwisata, String detailPariwisata, String gambarPariwisata) {
        this.namaPariwisata = namaPariwisata;
        this.alamatPariwisata = alamatPariwisata;
        this.detailPariwisata = detailPariwisata;
        this.gambarPariwisata = gambarPariwisata;
    }

    public String getNamaPariwisata() {
        return namaPariwisata;
    }

    public void setNamaPariwisata(String namaPariwisata) {
        this.namaPariwisata = namaPariwisata;
    }

    public String getAlamatPariwisata() {
        return alamatPariwisata;
    }

    public void setAlamatPariwisata(String alamatPariwisata) {
        this.alamatPariwisata = alamatPariwisata;
    }

    public String getDetailPariwisata() {
        return detailPariwisata;
    }

    public void setDetailPariwisata(String detailPariwisata) {
        this.detailPariwisata = detailPariwisata;
    }

    public String getGambarPariwisata() {
        return gambarPariwisata;
    }

    public void setGambarPariwisata(String gambarPariwisata) {
        this.gambarPariwisata = gambarPariwisata;
    }
}
