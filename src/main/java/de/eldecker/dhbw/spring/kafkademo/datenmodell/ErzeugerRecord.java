package de.eldecker.dhbw.spring.kafkademo.datenmodell;

import java.util.Date;

/**
 * Ein Objekt dieser Klasse soll vom Erzeuger als Kafka-Nachricht verschickt werden.
 */
public record ErzeugerRecord(String schluessel, int zahl, Date datum, String text, boolean bool) {

}
