package nl.robinlaugs.dealership.serverless.handler.aws.car;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import nl.robinlaugs.dealership.util.APIGatewayProxyResponseEventBuilder;

public class AWSCarDeleteHandler extends AWSCarHandler {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        String id = input.getPathParameters().get("id");

        super.getService().delete(id);

        return APIGatewayProxyResponseEventBuilder.builder()
                .statusCode(200)
                .build();

    }

}
