# Kafka im Docker-Container #

<br>

Dieses Verzeichnis enthält zwei Konfigurationsdateien für [Docker Compose](https://docs.docker.com/compose/),
mit denen ein Kafka-Server gestartet werden kann:
* `docker-compose.yml`: Kafka mit separatem [ZooKeeper](https://zookeeper.apache.org/)-Server (alte Variante)
* `docker-compose-KraftMode.yml`: Kafka ohne separate ZooKeeper-Server im KRaft-Modus (neue Variante)

<br>

Ab Vesion 4.0 wird Kafka nur noch den KRaft-Modus unterstützen, siehe [hier](https://heise.de/-10009773).

<br>

## Lifecycle Container ##

<br>

Die folgenden Aufrufe von `docker-compose` müssen im Ordner mit den `.yml`-Dateien für *Docker Compose* ausgeführt werden:

* Kafka-Server erzeugen: `docker-compose up`
* Kafka-Server anhalten: `docker-compose stop`
* Angehaltenen Kafka-Server starten: `docker-compose start`
* Kafka-Server herunterfahren und löschen: `docker-compose down`

<br>

Für die Variante mit dem KRaft-Modus ist nach `docker-compose` die folgende Option hinzuzufügen: `-f docker-compose-KraftMode.yml`

<br>