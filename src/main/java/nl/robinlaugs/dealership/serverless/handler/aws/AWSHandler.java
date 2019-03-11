package nl.robinlaugs.dealership.serverless.handler.aws;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import nl.robinlaugs.dealership.util.APIGatewayProxyResponseEventBuilder;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public abstract class AWSHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Getter
    private final ObjectMapper mapper = new ObjectMapper();

    protected APIGatewayProxyResponseEvent createApiGatewayException(String message, Exception exception) {
        Map<String, String> body = new HashMap<String, String>() {{
            put("message", format("%s: %s", message, exception));
        }};

        return APIGatewayProxyResponseEventBuilder.builder()
                .statusCode(500)
                .body(body.toString())
                .build();
    }

}
