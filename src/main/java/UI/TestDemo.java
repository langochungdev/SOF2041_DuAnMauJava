package UI;

import DAO.NhanVienDAO;
import DAO.ThongKeDAO;
import Entity.NhanVien;
import java.util.List;

public class TestDemo {
    public static void main(String[] args) {
//        NhanVienDAO dao = new NhanVienDAO();
//        dao.insert(new NhanVien("hung123", "admin123", "la ngoc hung", true));

        ThongKeDAO tk = new ThongKeDAO();
        List<Object[]> list = tk.getBangDiem(2);
        for(Object[] obj :list){
            System.out.println("=>"+obj[0]+"-"+obj[1]);
        }
    }
}
