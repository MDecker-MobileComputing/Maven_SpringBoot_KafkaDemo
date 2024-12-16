@echo.

@del LogDateien\Erzeuger1.log 2>nul

mvnw clean spring-boot:run -Dspring-boot.run.profiles=erzeuger2

