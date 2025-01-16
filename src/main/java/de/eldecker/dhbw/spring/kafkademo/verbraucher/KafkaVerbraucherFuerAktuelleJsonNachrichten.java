package de.eldecker.dhbw.spring.kafkademo.verbraucher;

import static de.eldecker.dhbw.spring.kafkademo.KafkaDemoApplication.TOPIC_NAME;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.eldecker.dhbw.spring.kafkademo.datenmodell.VerbraucherRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * Diese Bean-Klasse wird nur instanziiert, wenn das Profil {@code verbraucher3} aktiv ist.
 * Es werden nur Nachrichten empfangen, die verschickt wurden, nachdem dieses Programm gestartet wurde.
 * Die empfangenen Nachrichten sollten JSON-Objekte sein, die in Objekte der Klasse {@link VerbraucherRecord}
 * umgewandelt werden.
 */
@Component
@Profile("verbraucher3")
public class KafkaVerbraucherFuerAktuelleJsonNachrichten {

    private static Logger LOG = LoggerFactory.getLogger(KafkaVerbraucherFuerAktuelleJsonNachrichten.class);


    /** Objekt für Deserialisierung von JSON-String. */
    private ObjectMapper _objectMapper;


    /**
     * Konstruktor für Dependency Injection
     */
    public KafkaVerbraucherFuerAktuelleJsonNachrichten(ObjectMapper objectMapper) {

        LOG.debug("Kafka-Verbraucher (nur aktuelle JSON-Nachrichten) aktiv fuer Topic \"{}\".", TOPIC_NAME);

        _objectMapper = objectMapper;
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
     * @param string Empfangene Nachricht (JSON-String, wird nach 
     *               {@link VerbraucherRecord} deserialisiert)
     */
    @KafkaListener(id = "verbraucher-json-aktuelle", topics = TOPIC_NAME)
    public void onNachrichtEmpfangen(String jsonString) {

        try {

            VerbraucherRecord ergebnisObjekt = _objectMapper.readValue(jsonString, VerbraucherRecord.class);
            LOG.info("Kafka-Listener hat aktuelles Objekt empfangen: {}", ergebnisObjekt);
        }
        catch (JsonProcessingException ex) {

            LOG.error("Fehler beim Deserialisieren des JSON-Strings \"{}\" nach VerbraucherRecord.",
                      jsonString, ex);
        }
    }

}
