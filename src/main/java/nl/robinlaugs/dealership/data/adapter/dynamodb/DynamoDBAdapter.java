package nl.robinlaugs.dealership.data.adapter.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

import static java.lang.System.getenv;
import static java.util.Objects.isNull;

public class DynamoDBAdapter {

    private static DynamoDBAdapter adapter;

    private final AmazonDynamoDB client;

    private DynamoDBAdapter() {
        client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(getenv("REGION"))
                .build();
    }

    public static DynamoDBAdapter getInstance() {
        if (isNull(adapter)) adapter = new DynamoDBAdapter();

        return adapter;
    }

    public DynamoDBMapper createMapper(DynamoDBMapperConfig config) {
        return new DynamoDBMapper(client, config);
    }

}
