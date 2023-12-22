# Demo für Kafka mit Java #

<br>

Dieses Repo enthält Java-Programme, um das Schreiben und Lesen auf ein [Kafka](https://kafka.apache.org/)-Topic zu demonstrieren.

Es ist auch eine Datei [docker-compose.yml](./docker-compose.yml) enthalten, mit der ein lokaler Kafka-Server als Docker-Container gestartet werden kann.

<br>

## Kafka in Docker-Container ##

<br>

Kafka benötigt Zookeeper für die Koordination der einzelnen Kafka-Broker.


Start von Kafka und Zookeeper wie in Datei [docker-compose.yml](./docker-compose.yml) definiert:
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

### Topic anlegen ###

Nach dem ersten Start muss ein Kafka-Topic angelegt werden, auf das die Beispiel-Applikation Nachrichten schreiben kann.

Öffnen Sie hierzu eine Terminal-Verbindung zu Kafka-Container und geben Sie den folgenden Befehl ein:
```
kafka-topics --create --topic mein-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
```
Da wir über `docker-compose.yml` nur einen Kafka-Server starten, muss `--replication-factor` den Wert `1` haben.

Der folgende Befehl gibt alle bekannten Topics aus:
```
kafka-topics --list --bootstrap-server localhost:9092
```