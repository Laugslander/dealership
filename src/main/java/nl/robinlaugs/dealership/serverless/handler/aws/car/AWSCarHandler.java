package nl.robinlaugs.dealership.serverless.handler.aws.car;

import lombok.Getter;
import nl.robinlaugs.dealership.domain.Car;
import nl.robinlaugs.dealership.serverless.handler.aws.AWSHandler;
import nl.robinlaugs.dealership.service.CarService;
import nl.robinlaugs.dealership.service.Service;

abstract class AWSCarHandler extends AWSHandler {

    @Getter
    private final Service<Car> service;

    public AWSCarHandler(Service<Car> service) {
        this.service = service;
    }

    public AWSCarHandler() {
        service = new CarService();
    }

}
