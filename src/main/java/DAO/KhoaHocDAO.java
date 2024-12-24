package DAO;
import Entity.KhoaHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.JdbcHelper;

public class KhoaHocDAO extends EduSysDAO<KhoaHoc, String> {
    String INSERT_SQL = "INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV) VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE KhoaHoc SET MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=? WHERE MaKH=?";
    String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH=?";
    String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH=?";
    String SELECT_BY_MA_CD = "SELECT * FROM KhoaHoc WHERE MaCD=?";

    @Override
    public void insert(KhoaHoc e) {
        JdbcHelper.execUpdate(INSERT_SQL,
                e.getMaCD(),
                e.getHocPhi(),
                e.getThoiLuong(),
                e.getNgayKG(),
                e.getGhiChu(),
                e.getMaNV());
    }

    @Override
    public void update(KhoaHoc e) {
        JdbcHelper.execUpdate(UPDATE_SQL,
                e.getMaCD(),
                e.getHocPhi(),
                e.getThoiLuong(),
                e.getNgayKG(),
                e.getGhiChu(),
                e.getMaNV(),
                e.getMaKH());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.execUpdate(DELETE_SQL, id);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhoaHoc selectById(String id) {
        List<KhoaHoc> ds = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public List<KhoaHoc> selectBySql(String sql, Object...args) {
        List<KhoaHoc> ds = new ArrayList<>();
        ResultSet rs = JdbcHelper.execQuery(sql, args);
        try {
            while (rs.next()) {
                KhoaHoc e = new KhoaHoc();
                e.setMaKH(rs.getInt("MaKH"));
                e.setMaCD(rs.getString("MaCD"));
                e.setHocPhi(rs.getDouble("HocPhi"));
                e.setThoiLuong(rs.getInt("ThoiLuong"));
                e.setNgayKG(rs.getDate("NgayKG"));
                e.setGhiChu(rs.getString("GhiChu"));
                e.setMaNV(rs.getString("MaNV"));
                e.setNgayTao(rs.getDate("NgayTao"));
                ds.add(e);
            }
            rs.getStatement().getConnection().close();
            return ds;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<KhoaHoc> selectByMaCD(String maCD){
        String SQL = "select*from KhoaHoc where MaCD =?";
        return this.selectBySql(SQL, maCD);
    }

    public List<Integer> selectYears(){
        String sql = "delect distinct year(NgayKG) Year from KhoaHoc order by Year desc";
        List<Integer> ds = new ArrayList<>();
        ResultSet rs = JdbcHelper.execQuery(sql);
        try {
            while(rs.next()) {
                ds.add(rs.getInt(1));
            }
            return ds;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<KhoaHoc> selectKhoaHocByMaCD(String maCD) {
        return this.selectBySql(SELECT_BY_MA_CD, maCD);
    }
}
