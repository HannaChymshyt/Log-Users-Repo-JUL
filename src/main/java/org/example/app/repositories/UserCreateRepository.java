package org.example.app.repositories;

import org.example.app.database.DBConn;
import org.example.app.entities.User;
import org.example.app.utils.Constants;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserCreateRepository {

    public String createUser(User user) {
        String sql = "INSERT INTO " + Constants.TABLE_USERS + "(name, phone, email) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPhone());
            pstmt.setString(3, user.getEmail());
            pstmt.executeUpdate();
            return Constants.DATA_INSERT_MSG;
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}