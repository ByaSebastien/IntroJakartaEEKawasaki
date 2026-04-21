package be.bstorm.introjakartaee.dao;

import be.bstorm.introjakartaee.models.User;

public interface UserDao {
    void save(User user);
    User findByEmail(String email);
}
