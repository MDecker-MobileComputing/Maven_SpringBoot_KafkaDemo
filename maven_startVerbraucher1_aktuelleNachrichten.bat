@echo.

@del LogDateien\VerbraucherAktuelle.log 2>nul

mvnw clean spring-boot:run -Dspring-boot.run.profiles=verbraucher1

