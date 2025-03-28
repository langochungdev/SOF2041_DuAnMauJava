package Entity;

public class ChuyenDe {
    private String maCD;
    private String tenCD;
    private double hocPhi;
    private int thoiLuong;
    private String hinh;
    private String moTa;
    private String doKho;


    public ChuyenDe() {
    }
    public ChuyenDe(String maCD, String tenCD, double hocPhi, int thoiLuong, String hinh, String moTa, String doKho) {
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.hinh = hinh;
        this.moTa = moTa;
        this.doKho = doKho;
    }
    public String getMaCD() {
        return maCD;
    }
    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }
    public String getTenCD() {
        return tenCD;
    }
    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }
    public double getHocPhi() {
        return hocPhi;
    }
    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }
    public int getThoiLuong() {
        return thoiLuong;
    }
    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }
    public String getHinh() {
        return hinh;
    }
    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
    public String getMoTa() {
        return moTa;
    }
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    public String getDoKho() {
        return doKho;
    }
    public void setDoKho(String doKho) {
        this.doKho = doKho;
    }
    
    
    @Override
    public String toString() {
        return this.tenCD;
    }
}
