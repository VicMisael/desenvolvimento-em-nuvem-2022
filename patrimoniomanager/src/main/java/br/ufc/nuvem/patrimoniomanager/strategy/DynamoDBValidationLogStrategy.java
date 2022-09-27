package br.ufc.nuvem.patrimoniomanager.strategy;

import br.ufc.nuvem.patrimoniomanager.model.Validation;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DynamoDBValidationLogStrategy implements ValidationLogStrategy {


    private AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    public DynamoDBValidationLogStrategy() {
        ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (C:\\Users\\Misael\\.aws\\credentials), and is in valid format.",
                    e);
        }
        client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.US_EAST_1)
                .build();

        mapper = new DynamoDBMapper(client);
        TableUtils.createTableIfNotExists(client, mapper.generateCreateTableRequest(Validation.class)
                .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L)));
    }

    @Override
    public Validation uploadValidation(Validation validation) {
        mapper.save(validation);
        return validation;
    }

    @Override
    public List<Validation> getValidationListByBemId(Long id) {
        Map<String, AttributeValue> query = new HashMap<>();
        query.put(":v1", new AttributeValue().withS(id.toString()));
        DynamoDBScanExpression queryExpression = new DynamoDBScanExpression().withFilterExpression(":v1=idBem")
                .withExpressionAttributeValues(query);
        return mapper.scan(Validation.class, queryExpression);
    }

}
