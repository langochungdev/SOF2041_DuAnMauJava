package Entity;

public class NhanVien {
    private String maNV;
    private String hoTen;
    private String matKhau;
    private boolean vaiTro;
    private String mail;
    private String gioiTinh;

    public NhanVien(){
    }
    public NhanVien(String maNV, String matKhau, String hoTen, boolean vaiTro, String mail, String gioiTinh){
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
        this.mail = mail;
        this.gioiTinh = gioiTinh;
    }
    public String getMaNV() {
        return maNV;
    }
    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    public String getMatKhau() {
        return matKhau;
    }
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    public boolean isVaiTro() {
        return vaiTro;
    }
    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }
    public String getMail(){
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getGioiTinh(){
        return gioiTinh;
    }
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return this.hoTen;
    }
    
    
}
