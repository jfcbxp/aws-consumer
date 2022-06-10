package com.jfcbxp.consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class SnsMessage {

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("TopicArn")
    private String topicArn;

    @JsonProperty("Timestamp")
    private String timestamp;

    @JsonProperty("MessageId")
    private String messageId;
}
