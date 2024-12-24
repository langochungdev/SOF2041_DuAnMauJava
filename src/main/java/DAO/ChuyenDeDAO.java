package DAO;
import Entity.ChuyenDe;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.JdbcHelper;

public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {
    String INSERT_SQL = "INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?";
    String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD=?";
    String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
    String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe WHERE MaCD=?";

    @Override
    public void insert(ChuyenDe e) {
        JdbcHelper.execUpdate(INSERT_SQL,
                e.getMaCD(),
                e.getTenCD(),
                e.getHocPhi(),
                e.getThoiLuong(),
                e.getHinh(),
                e.getMoTa());
    }

    @Override
    public void update(ChuyenDe e) {
        JdbcHelper.execUpdate(UPDATE_SQL,
                e.getTenCD(),
                e.getHocPhi(),
                e.getThoiLuong(),
                e.getHinh(),
                e.getMoTa(),
                e.getMaCD());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.execUpdate(DELETE_SQL, id);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> ds = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.execQuery(sql, args);
            while (rs.next()) {
                ChuyenDe e = new ChuyenDe();
                e.setMaCD(rs.getString("MaCD"));
                e.setTenCD(rs.getString("TenCD"));
                e.setHocPhi(rs.getDouble("HocPhi"));
                e.setThoiLuong(rs.getInt("ThoiLuong"));
                e.setHinh(rs.getString("Hinh"));
                e.setMoTa(rs.getString("MoTa"));
                ds.add(e);
            }
            return ds;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
