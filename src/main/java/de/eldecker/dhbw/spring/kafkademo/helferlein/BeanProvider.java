package de.eldecker.dhbw.spring.kafkademo.helferlein;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import java.util.Random;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;


/**
 * Die Klasse ist mit {@code Configuration} annotiert, weil sie mit {@code @Bean} annotierte Methoden hat,
 * als Beans bereitstellen kann.
 */
@Configuration
public class BeanProvider {

    /**
     * Liefert konfiguriertes ObjectMapper-Objekt zurück, welches für Object-nach-JSON (Serialisierung)
     * oder JSON-nach-Objekt (Deserialisierung) benötigt wird.
     * <br><br>
     *
     * Konfiguration:
     * <ul>
     * <li>Kein Fehler, wenn beim Deserialisierung ein Feld im JSON gefunden wird, das nicht in der Zielklasse
     *     definiert ist</li>
     *  <li>Zeitstempelwerte werden im ISO-8601-Format im JSON abgelegt.</li>
     *  <li>Das erzeugte JSOn wird für bessere Lesbarkeit durch Einrückungen formatiert.</li>
     * </ul>
     *
     * @return Konfigurierter Object-Mapper
     */
    @Bean
    public ObjectMapper holeObjectMapper() {

        return JsonMapper.builder()
                .disable(FAIL_ON_UNKNOWN_PROPERTIES) // Ignoriert unbekannte JSON-Felder beim Deserialisieren
                .disable(WRITE_DATES_AS_TIMESTAMPS) // Schreibt Datum und Zeit im ISO-8601-Format
                .enable(INDENT_OUTPUT) // Schöne Ausgabe beim Serialisieren von Objekten in JSON
                .build();
    }

    /**
     * Zufallsgeneratorobjekt wie eine Bean für Dependency Injection bereitstellen.
     *
     * @return Zufallsgenerator mit Systemzeit als Initialwert für Zufallserzeugung,
     *         z.B. Zufallszahlen (int) oder zufällige Boolean-Werte
     */
    @Bean
    public Random holeRandom() {

        return new Random();
    }

}
