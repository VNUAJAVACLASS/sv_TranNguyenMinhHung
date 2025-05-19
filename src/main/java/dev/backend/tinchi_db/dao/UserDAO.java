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

    public List<Human> getAllHuman(){
        List<Human> listHuman = new ArrayList<>();

        try {
            String sql  = "select * from tbl_users";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String userCode = rs.getString("user_code");
                String fullName = rs.getString("fullname");
                String address = rs.getString("address");
                String _class =  rs.getString("class");
                String password = rs.getString("password");
                int role = rs.getInt("role");

                Human hm;
                if(role == 0){
                    hm = new Lecturer(userCode, fullName, address, password);
                }else{
                    hm = new Student(userCode, fullName, address, _class);
                }

                listHuman.add(hm);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listHuman;
    }
}
