package DAO;

import Entity.HocVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.JdbcHelper;

public class HocVienDAO extends EduSysDAO<HocVien, String> {
    String INSERT_SQL = "INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES(?, ?, ?)";
    String UPDATE_SQL = "UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
    String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV=?";
    String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE MaHV=?";

    @Override
    public void insert(HocVien entity) {
        JdbcHelper.execUpdate(INSERT_SQL, entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JdbcHelper.execUpdate(UPDATE_SQL,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem(),
                entity.getMaHV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.execUpdate(DELETE_SQL, id);
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HocVien selectById(String id) {
        List<HocVien> ds = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> ds = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.execQuery(sql, args);
            while (rs.next()) {
                HocVien e = new HocVien();
                e.setMaHV(rs.getInt("MaHV"));
                e.setMaKH(rs.getInt("MaKH"));
                e.setMaNH(rs.getString("MaNH"));
                e.setDiem(rs.getDouble("Diem"));
                ds.add(e);
            }
            return ds;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<HocVien> selectByKhoaHoc(int makh) {
        String SQL = "SELECT * FROM HocVien WHERE MaKH = ?";
        return this.selectBySql(SQL, makh);
    }
}
