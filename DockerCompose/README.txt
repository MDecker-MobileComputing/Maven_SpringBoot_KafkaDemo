# Cluster mit zwei Kafka-Servern #

<br>

In diesem Verzeichnis gibt es die Datei [docker-compose_zweiKafkaServer.yml](./docker-compose_zweiKafkaServer.yml),
die einen Cluster mit zwei Kafka-Server startet.
Zusätzlich wird natürlich auch noch Zookeeper gestartet.

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