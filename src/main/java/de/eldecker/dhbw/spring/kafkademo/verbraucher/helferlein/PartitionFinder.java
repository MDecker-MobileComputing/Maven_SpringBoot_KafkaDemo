package de.eldecker.dhbw.spring.kafkademo.verbraucher.helferlein;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.core.ConsumerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import de.eldecker.dhbw.spring.kafkademo.verbraucher.KafkaVerbraucherFuerAktuelleNachrichten;

/**
 * Klasse mit Methode, die alle Partitionen für ein bestimmtes Kafka-Topic zurückliefert,
 * nach <a href="https://docs.spring.io/spring-kafka/reference/tips.html#tip-assign-all-parts">dieser Seite</a>.
 */
public class PartitionFinder {

private static Logger LOG = LoggerFactory.getLogger(KafkaVerbraucherFuerAktuelleNachrichten.class);

    private final ConsumerFactory<String, String> _consumerFactory;

    /**
     * Konstruktor für Übergabe einer Instanz der Klasse {@code ConsumerFactory}.
     */
    public PartitionFinder(ConsumerFactory<String, String> consumerFactory) {

        _consumerFactory = consumerFactory;
    }

    /**
     * Alle Partitionen für ein Topic zurückgeben.
     *
     * @param topic Topic, für das alle Partitionen gesucht werden sollen
     * @return String-Array mit alle Partitionen
     */
    public String[] partitions(String topic) {

        try (Consumer<String, String> consumer = _consumerFactory.createConsumer()) {

            String[] partitionenArray = consumer.partitionsFor(topic).stream()
                .map(partitionInfo -> "" + partitionInfo.partition())
                .toArray(String[]::new);

            LOG.info("Anzahl Partition(en) für Topic \"{}\" gefunden: {}", topic, partitionenArray.length);

            return partitionenArray;
        }
        catch(Exception ex) {

            LOG.error("Exception bei Versuch alle Partitionen für Topic \"{}\" zu erhalten.", topic, ex);
            return new String[0];
        }
    }

}
