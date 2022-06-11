package com.jfcbxp.consumer.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.jfcbxp.consumer.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "product-events")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class ProductEventLog {

    @Id
    private ProductEventKey productEventKey;

    @Getter
    @Setter
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "eventType")
    private EventType eventType;

    @Getter
    @Setter
    @DynamoDBAttribute(attributeName = "productId")
    private Integer productId;

    @Getter
    @Setter
    @DynamoDBAttribute(attributeName = "username")
    private String username;

    @Getter
    @Setter
    @DynamoDBAttribute(attributeName = "timestamp")
    private long timestamp;

    @Getter
    @Setter
    @DynamoDBAttribute(attributeName = "ttl")
    private long ttl;

    @Getter
    @Setter
    @DynamoDBAttribute(attributeName = "messageId")
    private String messageId;

    @DynamoDBHashKey(attributeName = "pk")
    public String getPk() {
        return this.productEventKey != null ? this.productEventKey.getPk() : null;
    }

    public void setPk(String pk) {
        if (this.productEventKey == null) {
            this.productEventKey = new ProductEventKey();
        }
        this.productEventKey.setPk(pk);
    }

    @DynamoDBHashKey(attributeName = "sk")
    public String getSk() {
        return this.productEventKey != null ? this.productEventKey.getSk() : null;
    }

    public void setSk(String sk) {
        if (this.productEventKey == null) {
            this.productEventKey = new ProductEventKey();
        }
        this.productEventKey.setSk(sk);
    }


}
