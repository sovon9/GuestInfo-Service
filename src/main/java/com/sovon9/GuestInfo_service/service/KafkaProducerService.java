package com.sovon9.GuestInfo_service.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.sovon9.GuestInfo_service.dto.GuestCommInfo;

@Service
public class KafkaProducerService
{
	Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);
	public KafkaTemplate<String, Object> kafkaTemplate;
	
	@Autowired
	public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate)
	{
		this.kafkaTemplate=kafkaTemplate;
	}
	
	public void produce(GuestCommInfo commInfo)
	{
		CompletableFuture<SendResult<String, Object>>future =kafkaTemplate.send("PMS2", commInfo);
		future.whenComplete((result, exception)->{
			if(null==exception)
			{
				LOGGER.error("Sucessfully produced to partition: "+result.getRecordMetadata().partition());
			}
			else
			{
				LOGGER.error("Kafka exception: {}", exception.getMessage(), exception);
			}
		});
	}
}
