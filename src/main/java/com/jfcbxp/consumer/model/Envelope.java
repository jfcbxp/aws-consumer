package com.jfcbxp.consumer.model;

import com.jfcbxp.consumer.enums.EventType;
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
public class Envelope {
    private EventType eventType;
    private String data;
}
