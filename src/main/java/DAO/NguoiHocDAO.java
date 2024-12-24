package DAO;
import Entity.NguoiHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.JdbcHelper;

public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{
    String INSERT_SQL = "INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?,MaNV=? WHERE MaNH=?";
    String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH=?";
    String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM NguoiHoc WHERE MaNH=?";
    String SELECT_NOT_IN_COURSE_ID_SQL = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? AND MaNH NOT IN(SELECT MaNH FROM HocVien WHERE MaKH = ?)";
    
    @Override
    public void insert(NguoiHoc e) {
        JdbcHelper.execUpdate(INSERT_SQL,
                e.getMaNH(),
                e.getHoTen(),
                e.getNgaySinh(),
                e.isGioiTinh(),
                e.getDienThoai(),
                e.getEmail(),
                e.getGhiChu(),
                e.getMaNV());
    }

    @Override
    public void update(NguoiHoc e) {
        JdbcHelper.execUpdate(UPDATE_SQL,
                e.getHoTen(),
                e.getNgaySinh(),
                e.isGioiTinh(),
                e.getDienThoai(),
                e.getEmail(),
                e.getGhiChu(),
                e.getMaNV(),
                e.getMaNH());
    }
    
    @Override
    public void delete(String id) {
       JdbcHelper.execUpdate(DELETE_SQL, id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }
    
    @Override
    public NguoiHoc selectById(String id){
        List<NguoiHoc> ds = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public List<NguoiHoc> selectBySql(String sql, Object...args) {
        List<NguoiHoc> ds = new ArrayList<>();
        ResultSet rs = JdbcHelper.execQuery(sql, args);
        try {
            while (rs.next()) {
                NguoiHoc e = new NguoiHoc();
                e.setMaNH(rs.getString("MaNH"));
                e.setHoTen(rs.getString("HoTen"));
                e.setNgaySinh(rs.getDate("NgaySinh"));
                e.setGioiTinh(rs.getBoolean("GioiTinh"));
                e.setDienThoai(rs.getString("DienThoai"));
                e.setEmail(rs.getString("Email"));
                e.setGhiChu(rs.getString("GhiChu"));
                e.setMaNV(rs.getString("MaNV"));
                ds.add(e);
            }
            return ds;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "select*from NguoiHoc where HoTen like ? or MaNH like ? or DienThoai like ? or Email like ? or MaNV like ?";
        String searchPattern = "%" + keyword + "%";
        return this.selectBySql(sql, searchPattern, searchPattern, searchPattern, searchPattern, searchPattern);
    }

    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        return this.selectBySql(SELECT_NOT_IN_COURSE_ID_SQL, "%" + keyword + "%", makh);
    }
}
