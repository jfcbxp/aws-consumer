package com.jfcbxp.consumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfcbxp.consumer.model.Envelope;
import com.jfcbxp.consumer.model.ProductEvent;
import com.jfcbxp.consumer.model.ProductEventLog;
import com.jfcbxp.consumer.model.SnsMessage;
import com.jfcbxp.consumer.repository.ProductEventLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Service
public class ProductEventConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(
            ProductEventConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductEventLogRepository productEventLogRepository;

    @JmsListener(destination = "${aws.sqs.queue.product.events.name}")
    public void receiveProductEvent(TextMessage textMessage)
            throws JMSException, IOException {

        SnsMessage snsMessage = objectMapper.readValue(textMessage.getText(),
                SnsMessage.class);

        Envelope envelope = objectMapper.readValue(snsMessage.getMessage(),
                Envelope.class);

        ProductEvent productEvent = objectMapper.readValue(
                envelope.getData(), ProductEvent.class);

        LOG.info("Product event received - Event: {} - ProductId: {} - MessageId: {}",
                envelope.getEventType(),
                productEvent.getProductId(),
                snsMessage.getMessageId());

        ProductEventLog productEventLog = buildProductEventLog(envelope, productEvent);
        productEventLogRepository.save(productEventLog);

    }

    private ProductEventLog buildProductEventLog(Envelope envelope, ProductEvent productEvent) {

        ProductEventLog productEventLog = new ProductEventLog();
        productEventLog.setPk(productEvent.getCode());
        productEventLog.setSk(envelope.getEventType() + "_" + Instant.now().toEpochMilli());
        productEventLog.setEventType(envelope.getEventType());
        productEventLog.setProductId(productEvent.getProductId());
        productEventLog.setUsername(productEvent.getUsername());
        productEventLog.setTimestamp(Instant.now().toEpochMilli());
        productEventLog.setTtl(Instant.now().plus(Duration.ofMinutes(10)).getEpochSecond());


        return productEventLog;
    }

}
