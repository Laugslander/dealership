package nl.robinlaugs.dealership.serverless.handler.aws.car;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import nl.robinlaugs.dealership.domain.Car;
import nl.robinlaugs.dealership.util.APIGatewayProxyResponseEventBuilder;

public class AWSCarGetHandler extends AWSCarHandler {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        try {
            String id = input.getPathParameters().get("id");

            Car car = super.getService().get(id);

            return APIGatewayProxyResponseEventBuilder.builder()
                    .statusCode(200)
                    .body(super.getMapper().writeValueAsString(car))
                    .build();

        } catch (Exception e) {
            return super.createApiGatewayException("An error occurred while retrieving a car", e);
        }
    }

}
