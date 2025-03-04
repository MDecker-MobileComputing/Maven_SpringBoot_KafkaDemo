package de.eldecker.dhbw.spring.kafkademo.verbraucher;

import static de.eldecker.dhbw.spring.kafkademo.KafkaDemoApplication.TOPIC_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * Diese Bean-Klasse wird nur instanziiert, wenn das Profil {@code verbraucher1} aktiv ist.
 * Es werden nur Nachrichten empfangen, die verschickt wurden, nachdem dieses Programm gestartet wurde.
 */
@Component
@Profile("verbraucher1")
public class KafkaVerbraucherFuerAktuelleNachrichten {

    private static Logger LOG = LoggerFactory.getLogger(KafkaVerbraucherFuerAktuelleNachrichten.class);


    /**
     * Default-Konstruktor, schreibt was auf Logger.
     */
    public KafkaVerbraucherFuerAktuelleNachrichten() {

        LOG.debug("Kafka-Verbraucher (nur aktuelle Nachrichten) aktiv fuer Topic \"{}\".", TOPIC_NAME);
    }


    /**
     * Diese Methode wird für jede empfangene Nachricht aufgerufen
     * (siehe Annotation {@code KafkaListener}.
     * Es werden aber nur Nachrichten empfangen, die verschickt wurden,
     * nachdem dieses Programm gestartet wurde.
     * <br><br>
     *
     * Wenn das vorhandene Topic noch nicht vorhanden ist, dann wird es
     * beim Start der Anwendung erzeugt.
     * <br><br>
     *
     * Über die Annotation könnte u.a. noch das Attribut {@code groupId}
     * gesetzt werden.
     *
     * @param string Empfangene Nachricht
     */
    @KafkaListener(id = "verbraucher-text-aktuelle", topics = TOPIC_NAME)
    public void onNachrichtEmpfangen(String string) {

        LOG.info("Kafka-Listener hat aktuelle Nachricht empfangen: \"{}\"", string);
    }

}
