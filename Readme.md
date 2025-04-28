# Data Warehouse For Bloomberg
#### By Abdallah Al-abed

## Description
Demo service to handle receiving customer FX deals, analyze them and 
persist them into database.

Technologies used :
- Java
- SpringBoot
- JPA (Database Management)
- Junit and Mockito (Testing)
- Docker (Handle Database Image)
- Maven (Project Management)

## How To Use
1. build docker image: using `docker compose up` command
   - this will start the container and build the network.
2. send a json object (that matches the DealDto) to the endpoint `/deal/add-deal` to validate 
your deal and persist it in the database.
3. to see data in the DBMS (like: DBeaver) connect to the 
database using the credentials in the docker compose file
using port 3310

## Features
The code will validate following against the following criteria:
- ISO Currencies (3 characters exactly)
- Amount larger than 0
- Will not persist deals with dealId already in database
- Will validate if the currencies are actual currencies or just a random 3 characters