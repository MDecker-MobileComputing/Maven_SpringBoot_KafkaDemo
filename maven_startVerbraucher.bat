@echo.

del LogDateien\Verbraucher.log

mvnw clean spring-boot:run spring-boot:run -Dspring-boot.run.profiles=verbraucher

