package de.eldecker.dhbw.spring.kafkademo.verbraucher.helferlein;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;


/**
 * Diese Klasse ist mit {@code Configuration} annotiert, weil sie eine mit {@code Bean}
 * annotierte Methode enthält.
 */
@Configuration 
public class PartitionFinderConfig {
    
    @Bean
    public PartitionFinder finder(ConsumerFactory<String, String> consumerFactory) {
        return new PartitionFinder(consumerFactory);
    }
        
    public class PartitionFinder {

        private final ConsumerFactory<String, String> consumerFactory;
        
        public PartitionFinder(ConsumerFactory<String, String> consumerFactory) {
            this.consumerFactory = consumerFactory;
        }

        public String[] partitions(String topic) {
            
            try (Consumer<String, String> consumer = consumerFactory.createConsumer()) {
                
                return consumer.partitionsFor(topic).stream()
                    .map(pi -> "" + pi.partition())
                    .toArray(String[]::new);
            }
        }
    }

}
