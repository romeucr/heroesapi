package com.dio.HeroesAPI.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;
import static com.dio.HeroesAPI.constants.HeroConstant.ENDPOINT_DYNAMO;
import static com.dio.HeroesAPI.constants.HeroConstant.REGION_DYNAMO;

@Configuration
@EnableDynamoDBRepositories
public class HeroData {

  public static void main(String[] args) throws Exception {
    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
            .build();

    DynamoDB dynamoDB = new DynamoDB(client);

    Table table = dynamoDB.getTable("tb_hero");

    //creating items
    Item hero1 = new Item()
            .withPrimaryKey("id", "1")
            .withString("name", "Wonder Woman")
            .withString("universe", "DC Comics")
            .withInt("numberOfMovies", 3);

    Item hero2 = new Item()
            .withPrimaryKey("id", "2")
            .withString("name", "Superman")
            .withString("universe", "DC Comics")
            .withInt("numberOfMovies", 7);

    Item hero3 = new Item()
            .withPrimaryKey("id", "3")
            .withString("name", "Spiderman")
            .withString("universe", "Marvel")
            .withInt("numberOfMovies", 5);


    Item hero4 = new Item()
            .withPrimaryKey("id", "4")
            .withString("name", "Thor")
            .withString("universe", "Marvel")
            .withInt("numberOfMovies", 4);

    Item hero5 = new Item()
            .withPrimaryKey("id", "5")
            .withString("name", "Wolverine")
            .withString("universe", "Marvel")
            .withInt("numberOfMovies", 9);

    //saving items
    PutItemOutcome outcome1 = table.putItem(hero1);
    PutItemOutcome outcome2 = table.putItem(hero2);
    PutItemOutcome outcome3 = table.putItem(hero3);
    PutItemOutcome outcome4 = table.putItem(hero4);
    PutItemOutcome outcome5 = table.putItem(hero5);
  }
}
