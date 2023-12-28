package de.eldecker.dhbw.spring.kafkademo.erzeuger;

import static de.eldecker.dhbw.spring.kafkademo.KafkaDemoApplication.TOPIC_NAME;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


/**
 * Diese Bean-Klasse wird nur instanziiert, wenn das Profil {@code sender1} aktiv ist.
 * Diese Bean versendet einen String als Kafka-Nachricht.
 */
@Component
@Profile("erzeuger1")
public class KafkaErzeuger1 implements CommandLineRunner  {

    private static Logger LOG = LoggerFactory.getLogger(KafkaErzeuger1.class);

    /** Formatierer für Ausgabe Datum+Zeit, z.B. "22.12.2023 (Fr.), 16:04:23" */
    private SimpleDateFormat _dateFormatter = new SimpleDateFormat("dd.MM.yyyy (E), HH:mm:ss");

    /** Bean zum Versenden von Kafka-Nachrichten. */
    private KafkaTemplate<String, String> _kafkaTemplate;


    /**
     * Konstruktor für Dependency Injection.
     */
    @Autowired
    public KafkaErzeuger1(KafkaTemplate<String, String> template) {

        _kafkaTemplate = template;
    }


    /**
     * Diese Methode wird nach dem Start der Anwendung ausgeführt.
     * Sie erzeugt eine Nachricht, die das aktuelle Datum+Zeit enthält,
     * und schickt sie an das Kafka-Topic.
     *
     * @param args Wird nicht ausgewertet
     * @throws Exception Wird nicht geworfen
     */
    @Override
    public void run(String... args) throws Exception {

        final String datumString = _dateFormatter.format( System.currentTimeMillis() );
        final String nachricht = "Programmatisch erzeugte Nachricht: " + datumString;

        _kafkaTemplate.send( TOPIC_NAME, nachricht );
        //_kafkaTemplate.send(TOPIC_NAME, key, nachricht ); // alle Nachrichten mit selbem Key kommen in selbe Partition
        LOG.info( "Die folgende Nachricht wurde abgeschickt: {}", nachricht);
    }

}
