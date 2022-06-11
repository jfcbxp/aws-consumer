package com.jfcbxp.consumer.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class ProductEventKey {

    @DynamoDBHashKey(attributeName = "pk")
    private String pk;

    @DynamoDBRangeKey(attributeName = "sk")
    private String sk;

}
