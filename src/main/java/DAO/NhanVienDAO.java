package DAO;
import Entity.NhanVien;
import Utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends EduSysDAO<NhanVien, String>{
    String INSERT_SQL = "insert into NhanVien values (?, ?, ?, ?)";
    String UPDATE_SQL = "update NhanVien set MatKhau=?, HoTen=?, VaiTro=? where MaNV=?";
    String DELETE_SQL = "delete from NhanVien where MaNV=?";
    String SELECT_ALL_SQL = "select*from NhanVien";
    String SELECT_BY_ID_SQL = "select*from NhanVien where MaNV=?";
    
    @Override
    public void insert(NhanVien e){
        JdbcHelper.execUpdate(INSERT_SQL, e.getMaNV(),
                e.getMatKhau(),
                e.getHoTen(),
                e.isVaiTro());
    }

    @Override
    public void update(NhanVien e){
        JdbcHelper.execUpdate(UPDATE_SQL, e.getMaNV(),
                e.getMatKhau(),
                e.getHoTen(),
                e.isVaiTro());
    }

    @Override
    public void delete(String id){
        JdbcHelper.execUpdate(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll(){
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id){
        List<NhanVien> ds = selectBySql(SELECT_BY_ID_SQL, id);
        if (ds.isEmpty()) {
            return null;
        }
        return ds.get(0);
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object...args){
        List<NhanVien> ds = new ArrayList<>();
        ResultSet rs = JdbcHelper.execQuery(sql, args);
        try {
            while(rs.next()){
                NhanVien e = new NhanVien();
                e.setMaNV(rs.getString("MaNV"));
                e.setMatKhau(rs.getString("MatKhau"));
                e.setHoTen(rs.getString("HoTen"));
                e.setVaiTro(rs.getBoolean("VaiTro"));
                ds.add(e);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        };
        return ds;
    }
    
}
