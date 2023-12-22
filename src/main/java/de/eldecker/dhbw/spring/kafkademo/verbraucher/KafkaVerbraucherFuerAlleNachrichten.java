package de.eldecker.dhbw.spring.kafkademo.verbraucher;

import static de.eldecker.dhbw.spring.kafkademo.KafkaDemoApplication.TOPIC_NAME;

import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

/**
 * Diese Bean-Klasse wird nur instanziiert, wenn das Profil {@code verbraucher2} aktiv ist.
 * Es werden nicht nur aktuelle Nachrichten empfangen, sondern auch Nachrichten, die gesendet wurden,
 * bevor dieses Programm gestartet wurde.
 */
@Component
@Profile("verbraucher2")
public class KafkaVerbraucherFuerAlleNachrichten {

    private static Logger LOG = LoggerFactory.getLogger(KafkaVerbraucherFuerAlleNachrichten.class);
    
    public KafkaVerbraucherFuerAlleNachrichten() {

        LOG.debug("Kafka-Verbraucher (alle Nachrichten) aktiv fuer Topic \"{}\".", TOPIC_NAME);
    }
    
    @KafkaListener(topicPartitions = @TopicPartition(topic = "mein-topic",
            partitions = "#{@finder.partitions('mein-topic')}",
            partitionOffsets = @PartitionOffset(partition = "*", initialOffset = "0")))            
    public void onNachrichtEmpfangen(String string) {

        LOG.info("Kafka-Listener hat Nachricht empfangen: \"{}\"", string);        
    }
    
}

