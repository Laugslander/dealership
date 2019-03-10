package nl.robinlaugs.dealership.data.dao.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.Getter;
import nl.robinlaugs.dealership.data.adapter.dynamodb.DynamoDBAdapter;
import nl.robinlaugs.dealership.data.dao.Dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.getenv;
import static java.util.Objects.nonNull;

public abstract class DynamoDBDao<T, DynamoDBT extends T> implements Dao<T> {

    private final Class<DynamoDBT> type;

    @Getter
    private final DynamoDBMapper mapper;

    DynamoDBDao(Class<DynamoDBT> type, String table) {
        this.type = type;

        TableNameOverride name = new TableNameOverride(getenv(table));

        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
                .withTableNameOverride(name)
                .build();

        this.mapper = DynamoDBAdapter.getInstance().createMapper(config);
    }

    @Override
    public Collection<T> list() {
        DynamoDBScanExpression expression = new DynamoDBScanExpression();

        PaginatedScanList<DynamoDBT> result = mapper.scan(type, expression);

        return new ArrayList<>(result);
    }

    @Override
    public T get(String id) {
        Map<String, AttributeValue> attributes = new HashMap<>();
        attributes.put(":v1", new AttributeValue().withS(id));

        DynamoDBQueryExpression<DynamoDBT> expression = new DynamoDBQueryExpression<DynamoDBT>()
                .withKeyConditionExpression("id = :v1")
                .withExpressionAttributeValues(attributes);

        PaginatedQueryList<DynamoDBT> result = mapper.query(type, expression);

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public abstract T save(T object);

    @Override
    public abstract T update(T object);

    @Override
    public boolean delete(String id) {
        T object = get(id);

        if (nonNull(object)) {
            mapper.delete(object);

            return true;
        }

        return false;
    }

}
