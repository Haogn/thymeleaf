package rikkei.academy.model.dao;

import org.springframework.stereotype.Repository;
import rikkei.academy.model.entity.User;
import rikkei.academy.utl.ConnectionDB;

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
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("user_password"));
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ? ");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("user_password"));
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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(user_name,emai,  user_password) VALUES (?,?,)");
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE user_id= ?");
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
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET user_name = ? , email = ? ,  user_password = ? Where user_id = ? ");
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setInt(4,id);
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

    public Boolean login(User user) {
        if (user.getEmail().equals("user@gmail.com") && user.getPassword().equals("123")){
            return true;
        }
        return false ;
    }
}
