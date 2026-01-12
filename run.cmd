@echo off
REM Build the application
call mvnw.cmd clean package -DskipTests

REM Start the application
java -jar target/room-allocation-1.0.0.jar