package nl.robinlaugs.dealership.serverless.handler.aws.car;

import lombok.Getter;
import nl.robinlaugs.dealership.domain.Car;
import nl.robinlaugs.dealership.serverless.handler.aws.AWSHandler;
import nl.robinlaugs.dealership.service.CarService;
import nl.robinlaugs.dealership.service.Service;

@Getter
abstract class AWSCarHandler extends AWSHandler {

    private Service<Car> service = new CarService();

}
