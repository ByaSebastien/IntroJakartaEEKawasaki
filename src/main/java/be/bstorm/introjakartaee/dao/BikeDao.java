package be.bstorm.introjakartaee.dao;

import be.bstorm.introjakartaee.models.Bike;
import java.util.List;

public interface BikeDao {
    List<Bike> findAll();
    Bike findById(int id);
    void save(Bike bike);
    void update(Bike bike);
    void delete(int id);
}

