package nl.robinlaugs.dealership.serverless.handler.aws.car;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import nl.robinlaugs.dealership.util.APIGatewayProxyResponseEventBuilder;

public class AWSCarDeleteHandler extends AWSCarHandler {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        String id = input.getPathParameters().get("id");

        boolean success = super.getService().delete(id);

        if (success) {
            return APIGatewayProxyResponseEventBuilder.builder()
                    .statusCode(200)
                    .build();
        }

        return APIGatewayProxyResponseEventBuilder.builder()
                .statusCode(500)
                .body("An error occurred while deleting a car")
                .build();
    }

}
