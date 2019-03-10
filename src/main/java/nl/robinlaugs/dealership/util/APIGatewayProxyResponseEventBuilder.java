package nl.robinlaugs.dealership.util;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import lombok.Builder;

public class APIGatewayProxyResponseEventBuilder extends APIGatewayProxyResponseEvent {

    @Builder
    public APIGatewayProxyResponseEventBuilder(int statusCode, String body) {
        super.setStatusCode(statusCode);
        super.setBody(body);
    }

}
