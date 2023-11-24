package rikkei.academy.model.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikkei.academy.model.dao.UserDAO_IMPL;
import rikkei.academy.model.entity.User;

import java.util.List;
@Service
public class UserService_IMPL implements UserService_ITF{
    @Autowired
    private UserDAO_IMPL userDAOImpl ;
    @Override
    public List<User> findAll() {
        return userDAOImpl.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userDAOImpl.findById(id);
    }

    @Override
    public Boolean create(User user) {
        String BCryptPass = user.getPassword();
        BCryptPass = BCrypt.hashpw(BCryptPass,BCrypt.gensalt(12));
        user.setPassword(BCryptPass);
        return userDAOImpl.create(user);
    }

    @Override
    public Boolean delete(Integer id) {
        return userDAOImpl.delete(id);
    }

    @Override
    public Boolean update(User user, Integer id) {
        return userDAOImpl.update(user,id);
    }

    public User login(User user) {
        return userDAOImpl.login(user);
    }
    public User logon(User user) {
        return userDAOImpl.logon(user);
    }

}
