package nl.robinlaugs.dealership.service;

import nl.robinlaugs.dealership.data.dao.Dao;
import nl.robinlaugs.dealership.data.dao.dynamodb.DynamoDBCarDao;
import nl.robinlaugs.dealership.domain.Car;

import java.util.Collection;

public class CarService implements Service<Car> {

    private final Dao<Car> carDao;

    public CarService(Dao<Car> carDao) {
        this.carDao = carDao;
    }

    public CarService() {
        carDao = new DynamoDBCarDao();
    }

    @Override
    public Collection<Car> list() {
        return carDao.list();
    }

    @Override
    public Car get(String id) {
        return carDao.get(id);
    }

    @Override
    public Car create(Car car) {
        return carDao.save(car);
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public void delete(String id) {
        carDao.delete(id);
    }

}
