package nl.robinlaugs.dealership.data.dao.dynamodb;

import nl.robinlaugs.dealership.data.orm.dynamodb.DynamoDBCar;
import nl.robinlaugs.dealership.domain.Car;

public class DynamoDBCarDao extends DynamoDBDao<Car, DynamoDBCar> {

    public DynamoDBCarDao() {
        super(DynamoDBCar.class, "TABLE_CARS");
    }

    @Override
    public Car save(Car data) {
        Car car = new DynamoDBCar(data);

        super.getMapper().save(car);

        return car;
    }

    @Override
    public Car update(Car data) {
        Car car = new DynamoDBCar(data);

        // TODO update car

        return null;
    }

}
