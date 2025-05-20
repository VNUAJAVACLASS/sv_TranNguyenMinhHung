package dev.backend.tinchi_db.dao;

import dev.backend.tinchi_db.entities.Human;
import dev.backend.tinchi_db.entities.Lecturer;
import dev.backend.tinchi_db.entities.Student;

import java.sql.*;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        try {
            String url = "jdbc:ucanaccess://lib/baitoantinchi.accdb";
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //lấy danh sách tất cả nhân sự
    public List<Human> getAllHuman() {
        List<Human> listHuman = new ArrayList<>();

        try {
            String sql = "select * from tbl_users";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String userCode = rs.getString("user_code");
                String fullName = rs.getString("fullname");
                String address = rs.getString("address");
                String _class = rs.getString("class");
                String password = rs.getString("password");
                int role = rs.getInt("role");

                Human hm;
                if (role == 0) {
                    hm = new Lecturer(userCode, fullName, address, password);
                } else {
                    hm = new Student(userCode, fullName, address, _class);
                }

                listHuman.add(hm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listHuman;
    }

    //lấy danh sách sinh viên
    public List<Human> getAllStudent() {
        List<Human> listHuman = new ArrayList<>();

        try {
            String sql = "select * from tbl_users where role = 1";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String userCode = rs.getString("user_code");
                String fullName = rs.getString("fullname");
                String address = rs.getString("address");
                String _class = rs.getString("class");
                String password = rs.getString("password");
                int role = rs.getInt("role");

                Human hm;
                if (role == 1) {
                    hm = new Student(userCode, fullName, address, _class);
                } else {
                    hm = new Lecturer(userCode, fullName, address, password);
                }

                listHuman.add(hm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listHuman;
    }

    //lấy danh sách giảng viên
    public List<Human> getAllLecturer() {
        List<Human> listHuman = new ArrayList<>();

        try {
            String sql = "select * from tbl_users where role = 0";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String userCode = rs.getString("user_code");
                String fullName = rs.getString("fullname");
                String address = rs.getString("address");
                String _class = rs.getString("class");
                String password = rs.getString("password");
                int role = rs.getInt("role");

                Human hm = null;
                if (role == 1) {
                    hm = new Student(userCode, fullName, address, _class);
                } else if (role == 0) {
                    hm = new Lecturer(userCode, fullName, address, password);
                }

                listHuman.add(hm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listHuman;
    }

    //tim kiem nhan su theo ma
    public Human getHumanByCode(String code) {
        Human hm = null;
        String sql = "select * from tbl_users where user_code = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String userCode = rs.getString("user_code");
                String fullName = rs.getString("fullname");
                String address = rs.getString("address");
                String _class = rs.getString("class");
                String password = rs.getString("password");
                int role = rs.getInt("role");

                hm = role == 1 ? new Student(userCode, fullName, address, _class)
                        : new Lecturer(userCode, fullName, address, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hm;
    }

    //Thêm nhân sự
    public boolean addHuman(Human human) {
        String sql = "insert into tbl_users (user_code, fullname, address, class, password, role) values (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, human.getCode());
            ps.setString(2, human.getFullname());
            ps.setString(3, human.getAddress());

            if(human instanceof Student) {
                ps.setString(4, ((Student) human).get_class());
                ps.setString(5,null);
                ps.setInt(6,1);
            }else if(human instanceof Lecturer) {
                ps.setString(4,null);
                ps.setString(5,((Lecturer)human).getPassword());
                ps.setInt(6,0);
            }

            int rowInserted = ps.executeUpdate();
            return rowInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Sua thong tin nhan su
    public boolean updateHuman(Human human) {
        String sql = "update tbl_users Set fullname = ?, address = ?, class = ?, password = ? where user_code = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, human.getFullname());
            ps.setString(2, human.getAddress());

            if(human instanceof Student) {
                ps.setString(3, ((Student) human).get_class());
                ps.setString(4,null);
            }else if(human instanceof Lecturer) {
                ps.setString(3,null);
                ps.setString(4,((Lecturer)human).getPassword());
            }

            ps.setString(5,human.getCode());

            int rowUpdated = ps.executeUpdate();
            return rowUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //Xoa nhan su
    public boolean deleteHumanByCode(String code) {
        String sql = "delete from tbl_users where user_code = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            int rowDeleted = ps.executeUpdate();
            return rowDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
