package de.eldecker.dhbw.spring.kafkademo.verbraucher;

import static de.eldecker.dhbw.spring.kafkademo.KafkaDemoApplication.TOPIC_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
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


    /**
     * Default-Konstruktor, schreibt was auf Logger.
     */
    public KafkaVerbraucherFuerAlleNachrichten() {

        LOG.debug("Kafka-Verbraucher (alle Nachrichten) aktiv fuer Topic \"{}\".", TOPIC_NAME);
    }


    /**
     * Diese Methode empfängt auch Nachrichten, die in das Topic geschrieben wurden, bevor
     * dieses Programm gestartet wurde; siehe hierzu auch die Klassen im Paket
     * {@linkplain de.eldecker.dhbw.spring.kafkademo.verbraucher.helferlein}.
     * <br><br>
     *
     * Der Wert des Attributs {@code partitions} sagt, dass von einer Bean mit Namen
     * {@code partitionsfinder} die Methode {@code partitionen()} aufgerufen werden soll,
     * um alle Partitionen für das Topic mit dem als Argument übergebenen Namen zu erhalten.
     *
     * @param string Empfangene Nachricht
     */
    @KafkaListener(topicPartitions = @TopicPartition(topic = "mein-topic",
            partitions = "#{@partitionsfinder.partitionen('mein-topic')}",
            partitionOffsets = @PartitionOffset(partition = "*", initialOffset = "0")))
    public void onNachrichtEmpfangen(String string) {

        LOG.info("Kafka-Listener hat Nachricht empfangen: \"{}\"", string);
    }

}

