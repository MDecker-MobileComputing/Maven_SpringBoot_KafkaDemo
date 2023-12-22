package de.eldecker.dhbw.spring.kafkademo.erzeuger;

import static de.eldecker.dhbw.spring.kafkademo.KafkaDemoApplication.TOPIC_NAME;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


/**
 * Diese Bean-Klasse wird nur instanziiert, wenn das Profil {@code sender1} aktiv ist.
 */
@Component
@Profile("sender1")
public class KafkaErzeuger implements ApplicationRunner {

    private static Logger LOG = LoggerFactory.getLogger(KafkaErzeuger.class);

    /** Formatierer für Ausgabe Datum+Zeit, z.B. "22.12.2023 (Fr.), 16:04" */
    private SimpleDateFormat _dateFormatter = new SimpleDateFormat("dd.MM.yyyy (E), HH:mm");

    private KafkaTemplate<String, String> _kafkaTemplate;

    /**
     * Konstruktor für Dependency Injection.
     */
    @Autowired
    public KafkaErzeuger(KafkaTemplate<String, String> template) {

        _kafkaTemplate = template;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {

        final String datumString = _dateFormatter.format(System.currentTimeMillis());
        final String nachricht = "Programmatisch erzeugte Nachricht: " + datumString;

        _kafkaTemplate.send(TOPIC_NAME, nachricht );
        LOG.info("Die folgende Nachricht wurde abgeschickt: " + nachricht);
    }

}
