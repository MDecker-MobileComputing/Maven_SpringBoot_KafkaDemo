package de.eldecker.dhbw.spring.kafkademo.datenmodell;

import java.util.Date;


/**
 * Ein Objekt dieser Klasse soll vom Empfanger als Deserialisierungziel einer Kafka-Nachricht verwendet werden.
 * <br><br>
 *
 * Im Vergleich zur Klasse {@link ErzeugerRecord} fehlt hier das Attribut {@code text}, sie hat dafür
 * aber das Attribut {@code zahl2}.
 */
public record VerbraucherRecord(String schluessel, int zahl, int zahl2, Date datum, boolean bool) {

}
