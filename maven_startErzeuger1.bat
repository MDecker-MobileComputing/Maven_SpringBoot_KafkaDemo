@echo.

del LogDateien\Erzeuger1.log 2>nul

mvnw clean spring-boot:run spring-boot:run -Dspring-boot.run.profiles=sender1

