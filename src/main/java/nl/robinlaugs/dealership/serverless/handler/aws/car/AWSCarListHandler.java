package nl.robinlaugs.dealership.serverless.handler.aws.car;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import nl.robinlaugs.dealership.domain.Car;
import nl.robinlaugs.dealership.util.APIGatewayProxyResponseEventBuilder;

import java.util.Collection;

public class AWSCarListHandler extends AWSCarHandler {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        try {
            Collection<Car> cars = super.getService().list();

            return APIGatewayProxyResponseEventBuilder.builder()
                    .statusCode(200)
                    .body(super.getMapper().writeValueAsString(cars))
                    .build();

        } catch (Exception e) {
            return super.createApiGatewayException("An error occurred while retrieving all cars", e);

        }
    }

}
