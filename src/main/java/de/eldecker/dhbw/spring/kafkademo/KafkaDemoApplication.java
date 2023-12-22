package de.eldecker.dhbw.spring.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class KafkaDemoApplication {
    
    /** 
     * Name für Kafka-Topic. Ein Topic kann mal als Rundfunk-Kanal
     * für die Verteilung von Informationen zu einem bestimmten
     * Thema veranschualichen.
     */
    public static final String TOPIC_NAME = "mein-topic"; 
    

    /**
     * Einsteigsmethode mit Spring-Boot-App.
     */
	public static void main(String[] args) {
	    
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

}
