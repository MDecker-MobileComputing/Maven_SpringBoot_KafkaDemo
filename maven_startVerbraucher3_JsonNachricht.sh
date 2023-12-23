#!/bin/bash

del LogDateien/VerbraucherJson.log 2>/dev/null

./mvnw clean spring-boot:run -Dspring-boot.run.profiles=verbraucher3




