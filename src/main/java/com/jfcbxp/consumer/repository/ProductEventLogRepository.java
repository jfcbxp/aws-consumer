package com.jfcbxp.consumer.repository;

import com.jfcbxp.consumer.model.ProductEventKey;
import com.jfcbxp.consumer.model.ProductEventLog;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ProductEventLogRepository extends CrudRepository<ProductEventLog, ProductEventKey> {

    List<ProductEventLog> findAllByPk(String pk);

    List<ProductEventLog> findAllByPkAndSkStartsWith(String pk, String eventType);


}
