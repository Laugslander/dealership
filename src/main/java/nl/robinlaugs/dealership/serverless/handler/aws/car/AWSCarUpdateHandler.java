package nl.robinlaugs.dealership.serverless.handler.aws.car;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import nl.robinlaugs.dealership.domain.Car;
import nl.robinlaugs.dealership.util.APIGatewayProxyResponseEventBuilder;

import static java.lang.String.format;

public class AWSCarUpdateHandler extends AWSCarHandler {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        try {
            String body = input.getBody();
            Car data = super.getMapper().readValue(body, Car.class);

            Car car = super.getService().update(data);

            return APIGatewayProxyResponseEventBuilder.builder()
                    .statusCode(201)
                    .body(super.getMapper().writeValueAsString(car))
                    .build();

        } catch (Exception e) {
            return APIGatewayProxyResponseEventBuilder.builder()
                    .statusCode(500)
                    .body(format("An error occurred while updating a car: %s", e))
                    .build();
        }
    }

}
