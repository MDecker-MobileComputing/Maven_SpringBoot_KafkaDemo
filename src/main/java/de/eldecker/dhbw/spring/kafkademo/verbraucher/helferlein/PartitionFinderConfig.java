package de.eldecker.dhbw.spring.kafkademo.verbraucher.helferlein;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;


/**
 * Diese Klasse ist mit {@code Configuration} annotiert, weil sie eine mit {@code Bean}
 * annotierte Methode enthält.
 */
@Configuration
public class PartitionFinderConfig {

    /**
     * Diese Methode erzeugt eine Instanz der Klasse {@code PartitionFinder}.
     *
     * @param consumerFactory Die Factory, die die Kafka-Consumer erzeugt.
     * @return Eine Instanz der Klasse {@code PartitionFinder}.
     */
    @Bean
    public PartitionFinder finder(ConsumerFactory<String, String> consumerFactory) {

        return new PartitionFinder(consumerFactory);
    }

}
