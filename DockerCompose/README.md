# Cluster mit zwei Kafka-Servern #

<br>

In diesem Verzeichnis gibt es die Datei [docker-compose_zweiKafkaServer.yml](./docker-compose_zweiKafkaServer.yml),
die einen Cluster mit zwei Kafka-Server startet.
Zusätzlich wird natürlich auch noch Zookeeper gestartet.

**Besonderheiten:**
* Für die Kafka-Container wird jetzt noch die Umgebungsvariable `KAFKA_BROKER_ID` gesetzt.
* Für den zweiten Kafka-Container wird der Port `9092` auf den Port `9093` von `localhost`
  abgebildet, weil der Port `9092` von `localhost` schon durch den Port von ersten Kafka-Container belegt ist.

<br>

Befehl, um die Konfiguration mit zwei Kafka-Servern zu starten:

```
docker-compose -f docker-compose_zweiKafkaServer.yml up
```

<br>

Befehl, um den Cluster zu stoppen:
```
docker-compose -f docker-compose_zweiKafkaServer.yml up
```

<br>

Befehl, um den Cluster herunterzufahren und die Container zu löschen (Daten gehen verloren!):
```
docker-compose -f docker-compose_zweiKafkaServer.yml up
```

<br>