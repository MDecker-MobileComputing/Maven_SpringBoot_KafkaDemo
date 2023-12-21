

## Kafka in Docker-Container ##

<br>

Kafka benötigt Zookeeper für die Koordination der einzelnen Kafka-Broker.


Start von Kafka und Zookeeper wie in Datei [docker-compose.yml](./docker-compose.yml) definiert:
```
docker-compose up
```

<br>

Beenden:
```
docker-compose down
```

<br>

### Topic anlegen ###

Nach dem ersten Start muss ein Kafka-Topic angelegt werden.

Öffnen Sie hierzu eine Terminal-Verbindung zu Kafka-Container und geben Sie den folgenden Befehl ein:
```
kafka-topics --create --topic mein-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
```
