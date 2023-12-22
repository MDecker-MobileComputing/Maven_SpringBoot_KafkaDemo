# Demo für Kafka mit Java #

<br>

Dieses Repo enthält ein Maven-Projekt mit Spring-Boot, um das Schreiben und Lesen auf ein [Kafka](https://kafka.apache.org/)-Topic zu demonstrieren.
Das Maven-Projekt wurde mit [dieser Konfiguration](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.2.1&packaging=jar&jvmVersion=17&groupId=de.eldecker.dhbw.spring&artifactId=kafkademo&name=kafka-demo&description=Demo%20f%C3%BCr%20Verwendung%20von%20Kafka%20mit%20Spring-Boot&packageName=de.eldecker.dhbw.spring.kafkademo&dependencies=kafka) von *Spring Initializr* erzeugt.

<br>

Es ist auch eine Datei [docker-compose.yml](./DockerCompose/docker-compose.yml) enthalten, mit der ein lokaler Kafka-Server als Docker-Container gestartet werden kann.

<br>

## Kafka in Docker-Container ##

### Starten, pausieren und löschen ###

Kafka benötigt [Zookeeper](https://zookeeper.apache.org/) für die Koordination der einzelnen Kafka-Broker.


Befehl zum Start von Kafka und Zookeeper wie in Datei [docker-compose.yml](./DockerCompose/docker-compose.yml) definiert:
```
docker-compose up
```

<br>

Container stoppen und neu starten:
```
docker-compose stop
docker-compose start
```

<br>

Container löschen (Topics gehen verloren!)
```
docker-compose down
```

<br>

### Kafka-Server-Konfiguration ###

Die Konfigurationsdateien für den Kafka-Server befinden sich im Docker-Image im Verzeichnis `/etc/kafka/`. In der Datei `/etc/kafka/server.properties` ist
bspw. der folgende Eintrag enthalten:
```
log.retention.hours=168
```
Die ist die Default-Retention-Zeit für Nachrichten, also die Mindestzeit, bevor Nachrichten gelöscht werden (soweit für das Topic nichts anderes definiert ist).
Der angegebene Wert von `168` Stunden entspricht entspricht 7 Tagen.

<br>

### Topic anlegen ###

Nach dem ersten Start muss ein Kafka-Topic angelegt werden, auf das die Beispiel-Applikation Nachrichten schreiben kann.

Öffnen Sie hierzu eine Terminal-Verbindung zu Kafka-Container und geben Sie den folgenden Befehl ein:
```
kafka-topics --create --topic mein-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --config retention.ms=864000000
```
Anmerkungen zu den einzelnen Argumenten:
* Der mit `--replication-factor` angegebene Wert darf nicht höher als die Anzahl der Kafka-Server sein.
  Da in der Datei `docker-compose.yml` nur ein Kafka-Server definiert wird, können wir hier keinen höheren Wert verwenden.
* Mit `--bootstrap-server` können auch mehrere Kafka-Server angegeben werden, z.B. `--bootstrap-server server-1:9092,server-2:9092`
* Der Parameter `--config retention.ms` ist optional.
  Mit dem Wert `864000000` wird die Retention-Period (also die Zeit, nach der eine Nachricht gelöscht werden kann), auf 10 Tage.
  Der Default-Wert ist 7 Tage.
  Um die Nachrichten im Topic nie zu löschen ist die Retention-Period uaf `-1` zu setzen.

<br>

Der folgende Befehl gibt alle bekannten Topics aus:
```
kafka-topics --list --bootstrap-server localhost:9092
```

<br>

### Topic löschen ###

Mit dem folgenden Befehl kann das Topic wieder gelöscht werden:
```
kafka-topics --delete --bootstrap-server localhost:9092 --topic mein-topic
```

<br>

----

## License ##

<br>

See the [LICENSE file](LICENSE.md) for license rights and limitations (BSD 3-Clause License).

<br>