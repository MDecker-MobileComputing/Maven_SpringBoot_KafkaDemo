package de.eldecker.dhbw.spring.kafkademo.konsument;

import static de.eldecker.dhbw.spring.kafkademo.KafkaDemoApplication.TOPIC_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * Diese Bean-Klasse wird nur instanziiert, wenn das Profil {@code verbraucher} aktiv ist.
 */
@Component
@Profile("verbraucher")
public class KafkaVerbraucher {
    
    private static Logger LOG = LoggerFactory.getLogger(KafkaVerbraucher.class);
    
    /**
     * Default-Konstruktor, schreibt was auf Logger.
     */
    public KafkaVerbraucher() {
        
        LOG.debug("Kafka-Verbraucher aktiv fuer Topic \"{}\".", TOPIC_NAME);
    }

    
    /**
     * Diese Methode wird für jede empfangene Nachricht aufgerufen.
     * Es werden aber nur Nachrichten empfangen, die verschickt wurden,
     * nachdem dieses Programm gestartet wurde.
     * 
     * @param string Empfangene Nachricht
     */
    @KafkaListener(id = "mein-kafka-listener", topics = TOPIC_NAME)
    public void listen(String string) {
        
        LOG.info("Kafka-Listener hat Nachricht empfangen: \"{}\"", string);
    }
}
