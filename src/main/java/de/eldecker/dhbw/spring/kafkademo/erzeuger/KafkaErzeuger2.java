package de.eldecker.dhbw.spring.kafkademo.erzeuger;

import com.fasterxml.jackson.databind.ObjectMapper;

import static de.eldecker.dhbw.spring.kafkademo.KafkaDemoApplication.TOPIC_NAME;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import de.eldecker.dhbw.spring.kafkademo.datenmodell.ErzeugerRecord;

/**
 * Diese Bean-Klasse wird nur instanziiert, wenn das Profil {@code sender1} aktiv ist.
 * Diese Bean versendet ein Objekt der klasse {@link ErzeugerRecord} als Kafka-Nachricht.
 */
@Component
@Profile("erzeuger2")
public class KafkaErzeuger2 implements CommandLineRunner {
    
    private static Logger LOG = LoggerFactory.getLogger(KafkaErzeuger2.class);

    /** Bean zum Versenden von Kafka-Nachrichten. */
    private KafkaTemplate<String, String> _kafkaTemplate;

    /** Object, welche die Serialisierung (Java-Objekt nach JSON) vornimmt. */ 
    private ObjectMapper _objectMapper; 
    
    /** Zufallsgenerator. */
    private Random _random;
    
    
    /**
     * Konstruktor für Dependency Injection.
     */
    @Autowired
    public KafkaErzeuger2(KafkaTemplate<String, String> template,
                          ObjectMapper objectMapper,
                          Random random) {

        _kafkaTemplate = template;
        _objectMapper = objectMapper;
        _random = random;
    }

    /**
     * Diese Methode wird nach dem Start der Anwendung ausgeführt.
     * Sie verschickt ein Objekt der Klasse {@code ErzeugerRecord}
     * als JSON-String. 
     *
     * @param args Wird nicht ausgewertet
     * @throws Exception Wird nicht geworfen
     */
    @Override
    public void run(String... args) throws Exception {
        
        ErzeugerRecord er = erzeugeRecord();
        
        final String jsonStr = _objectMapper.writeValueAsString(er);
        
        _kafkaTemplate.send(TOPIC_NAME, "abc", jsonStr );
        
        LOG.info("Java-Objekt im JSON-Format als Kafka-Nachricht verschickt: " + jsonStr);
    }
    
    
    /**
     * Methode erzeugt Objekte, die nach JSON serialisiert werden sollen, damit
     * sie dann als Kafka-Nachricht verschickt werden können.
     * 
     * @return Objekt mit zufälligen Werten
     */
    private ErzeugerRecord erzeugeRecord() {

        final String  schluessel     = "mein-key-" + _random.nextInt(10);
        final int     zahl           = _random.nextInt(1000);
        final Date    datumZeitJetzt = new Date();
        final String  text           = "Mein Text " + _random.nextInt(1000);
        final boolean bool           = _random.nextBoolean();
                
        return new ErzeugerRecord(schluessel, zahl, datumZeitJetzt, text, bool);  
    }
    
}
