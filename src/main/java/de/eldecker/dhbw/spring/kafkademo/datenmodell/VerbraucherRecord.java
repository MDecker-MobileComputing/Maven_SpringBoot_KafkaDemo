package de.eldecker.dhbw.spring.kafkademo.datenmodell;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Ein Objekt dieser Klasse soll vom Empfanger als Deserialisierungziel einer Kafka-Nachricht verwendet werden.
 * <br><br>
 *
 * Unterschiede zur Klasse {@link ErzeugerRecord}:
 * <ul>
 *   <li>Es fehlt das Attribut {@code text}; 
 *       es wird bei der Deserialisierung ignoriert, weil im {@code ObjectMapper} 
 *       {@code FAIL_ON_UNKNOWN_PROPERTIES} deaktiviert ist.</li>
 *       
 *   <li>Sie hat dafür das zusätzliche Attribute {@code zahl2}; 
 *       dies wird einfach leer gelassen.</li>
 *       
 *   <li>Das Datums-Attribut {@code date} heißt {@code datum}; 
 *       der andere Name wird mit der Annotation {@code JsonProperty} gesetzt.</li>
 * </ul>
 */
public record VerbraucherRecord( String schluessel,
                                 int zahl,
                                 int zahl2,
                                 @JsonProperty("datum") Date date,
                                 boolean bool ) {

}
