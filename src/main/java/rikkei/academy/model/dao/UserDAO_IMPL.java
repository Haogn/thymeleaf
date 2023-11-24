package rikkei.academy.model.dao;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import rikkei.academy.model.entity.User;
import rikkei.academy.utl.ConnectionDB;
import rikkei.academy.utl.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDAO_IMPL implements UserDAO_ITF{
    @Override
    public List<User> findAll() {
        Connection connection = null ;
        List<User> list = new ArrayList<>();

        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user ");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(Role.valueOf(rs.getString("role")));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public User findById(Integer id) {
        Connection connection = null ;
        User user = new User();
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ? ");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(Role.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return user;
    }

    @Override
    public Boolean create(User user) {
        Boolean isCheck = false;
        Connection connection = null;
        try {
             connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(user_name,email, password, phone, role) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getPhone());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            int check = preparedStatement.executeUpdate();
            if (check > 0 ) {
                isCheck = true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return isCheck;
    }

    @Override
    public Boolean delete(Integer id) {
        Boolean isCheck = false;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id= ?");
            preparedStatement.setInt(1,id);
            int check = preparedStatement.executeUpdate();
            if (check > 0 ) {
                isCheck = true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return isCheck;
    }

    @Override
    public Boolean update(User user, Integer id) {
        Boolean isCheck = false;
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET user_name = ? , email = ? ,  user_password = ?, phone = ?  Where id = ? ");
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setInt(5,id);
            int check = preparedStatement.executeUpdate();
            if (check > 0 ) {
                isCheck = true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return isCheck;
    }


    public User login(User user) {
        List<User> list = findAll();
        String passBCrypt = null;
        for (User u : list) {
            if (u.getEmail().equals(user.getEmail())){
                passBCrypt=u.getPassword();
                if (BCrypt.checkpw(user.getPassword(), passBCrypt) && user.getRole() == Role.USER){
                    return u;
                }
            }
        }
        return null;
    }

    public User logon(User user) {
        List<User> list = findAll();
        String passBCrypt = null;
        for (User u : list) {
            if (u.getEmail().equals(user.getEmail())){
                passBCrypt=u.getPassword();
                if (BCrypt.checkpw(user.getPassword(), passBCrypt) && user.getRole() == Role.ADMIN){
                    return u;
                }
            }
        }
        return null;
    }

}
