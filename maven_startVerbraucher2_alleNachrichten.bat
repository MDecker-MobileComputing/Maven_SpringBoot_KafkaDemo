@echo.

del LogDateien\VerbraucherAlle.log

mvnw clean spring-boot:run spring-boot:run -Dspring-boot.run.profiles=verbraucher2

