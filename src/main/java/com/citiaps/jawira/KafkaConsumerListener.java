package com.citiaps.jawira;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class KafkaConsumerListener {
	@Autowired
    Consumer<Long,String> consumer;
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void run() {
		java.util.Date date = new java.util.Date();
	    System.out.println(date);
	    
		ConsumerRecords<Long, String> records=consumer.poll(Duration.of(1, ChronoUnit.SECONDS));
		for (ConsumerRecord<Long, String> record : records) {
			System.out.println(record.value());
		}
	}
}
