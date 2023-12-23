#!/bin/bash

rm LogDateien/Erzeuger1.log 2> /dev/null

./mvnw clean spring-boot:run -Dspring-boot.run.profiles=erzeuger1

