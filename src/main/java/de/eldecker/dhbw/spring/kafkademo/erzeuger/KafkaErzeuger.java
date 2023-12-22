package de.eldecker.dhbw.spring.kafkademo.erzeuger;

import static de.eldecker.dhbw.spring.kafkademo.KafkaDemoApplication.TOPIC_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


/**
 * Diese Bean-Klasse wird nur instanziiert, wenn das Profil {@code sender1} aktiv ist.
 */
@Component
@Profile("sender1")
public class KafkaErzeuger implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger LOG = LoggerFactory.getLogger(KafkaErzeuger.class);
    
    private KafkaTemplate<String, String> _kafkaTemplate;
    
    /**
     * Konstruktor für Dependency Injection.
     */
    @Autowired
    public KafkaErzeuger(KafkaTemplate<String, String> template) {
        
        _kafkaTemplate = template;
    }
    
    /**
     * Diese Methode wird aufgerufen, wenn die Anwendung gestartet wurde.
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        _kafkaTemplate.send(TOPIC_NAME, "Programmatisch erzeugte Nachricht.");
        LOG.info("Nachricht verschickt.");
    }

}
