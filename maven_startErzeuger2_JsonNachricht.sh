#!/bin/bash

del LogDateien/Erzeuger2.log 2>/dev/null

./mvnw clean spring-boot:run -Dspring-boot.run.profiles=erzeuger2


