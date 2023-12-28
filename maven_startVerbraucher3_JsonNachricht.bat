@echo.

@del LogDateien/VerbraucherJson.log 2>nul

mvnw clean spring-boot:run -Dspring-boot.run.profiles=verbraucher3