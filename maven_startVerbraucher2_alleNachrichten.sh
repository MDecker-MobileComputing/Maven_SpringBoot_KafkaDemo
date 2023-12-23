#!/bin/bash

del LogDateien/VerbraucherAlle.log 2>/dev/null

./mvnw clean spring-boot:run -Dspring-boot.run.profiles=verbraucher2



