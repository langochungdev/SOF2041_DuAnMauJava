package DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.JdbcHelper;

public class ThongKeDAO{
    private List<Object[]> getListOfArray(String sql, String[] tenCot, Object...mang){
        List<Object[]> ds = new ArrayList<>();
        ResultSet rs = JdbcHelper.execQuery(sql, mang);
        try {
            while (rs.next()) {
                Object[] giaTri = new Object[tenCot.length];
                for (int i=0; i < tenCot.length; i++) {
                    giaTri[i] = rs.getObject(tenCot[i]);
                }
                ds.add(giaTri);
            }
            return ds;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getBangDiem(Integer maKH) {
        String sql = "{CALL sp_BangDiem(?)}";
        String[] tenCot = { "MaNH", "HoTen", "Diem" };
        return this.getListOfArray(sql, tenCot, maKH);
    }

    public List<Object[]> getLuongNguoiHoc() {
        String sql = "{CALL sp_LuongNguoiHoc}";
        String[] tenCot = { "Nam", "SoLuong", "DauTien", "CuoiCung" };
        return this.getListOfArray(sql, tenCot);
    }

    public List<Object[]> getDiemChuyenDe() {
        String sql = "{CALL sp_DiemChuyenDe}";
        String[] tenCot = { "ChuyenDe", "SoHV", "ThapNhat", "CaoNhat", "TrungBinh" };
        return this.getListOfArray(sql, tenCot);
    }

    public List<Object[]> getDoanhThu(int nam) {
        String sql = "{CALL sp_DoanhThu(?)}";
        String[] tenCot = { "ChuyenDe", "SoKH", "SoHV", "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh" };
        return this.getListOfArray(sql, tenCot, nam);
    }
}
