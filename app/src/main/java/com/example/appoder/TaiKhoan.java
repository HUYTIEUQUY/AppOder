package com.example.appoder;

public class TaiKhoan {
    private  String Email;
    private String User;
    private  String DiaChi;
    private String Sdt;


    public TaiKhoan() {
    }

    public TaiKhoan(String email, String user, String diaChi, String sdt) {
        Email = email;
        User = user;
        DiaChi = diaChi;
        Sdt = sdt;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }
}
