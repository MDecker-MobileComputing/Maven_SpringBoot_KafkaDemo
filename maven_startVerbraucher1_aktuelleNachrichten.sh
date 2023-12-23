#!/bin/bash

del LogDateien/VerbraucherAktuelle.log 2>/dev/null

./mvnw clean spring-boot:run -Dspring-boot.run.profiles=verbraucher1



