# Building the Property and Asset Rental Management Platform Backend

Backend API for a rental business: tenants, property managers, rental contracts with a status workflow, and rent payments. Spring Boot, Spring Data JPA, PostgreSQL.

## Student

- Names: ISHIMWE Christian
- Student ID: 27248
- Course: INSY 8322 Web Technology and Internet, AUCA, Mid-Term, Academic Year 2026-2027 (1)
- Project name: propertyand-asset-rental-management-platform

## Prerequisites

1. Java 17 (JDK) - verified using ‘java -version’
2. PostgreSQL - the database engine the project connects to
3. Maven wrapper (included in the project as ‘mvnw’ / ‘mvnw.cmd’)
4. Git - to clone the repository
5. Postman (for testing the API)

## Create the database

1. Open pgAdmin and connect to your local PostgreSQL server.
2. Right-click Databases -> Create -> Database.
3. Name it ‘rentaldb’ and click Save.
4. Confirm the new database appears in the tree with zero tables. The tables are created automatically the first time the application starts.

## Configure

The database login is kept in a local file that is not pushed to GitHub.

1. Open the project folder ‘propertyand-asset-rental-management-platform’ (the one that contains ‘pom.xml’).
2. Create a file named ‘local.properties’ in that folder.
3. Add your own PostgreSQL username and password:

	properties
spring.datasource.username=your_username
spring.datasource.password=your_password

4. If your PostgreSQL does not run on ‘localhost:5432’, or your database is not called ‘rentaldb’, update ‘spring.datasource.url’ in ‘src/main/resources/application.properties’.

## Run the API

1. Clone the repository:

git clone <repository URL>

2. Move into the project folder:

cd propertyand-asset-rental-management-platform-backend/propertyand-asset-rental-management-platform

3. Start the application (‘./mvnw’ on macOS or Linux):

.\mvnw spring-boot:run

4. Wait until you see ‘Started ...Application’. The API is now available at ‘http://localhost:8080’.
5. Stop it with ‘Ctrl+C’.

If it does not start, check the first error line:

- ‘password authentication failed’ - the username or password in ‘local.properties’ is wrong.
- ‘database "rentaldb" does not exist’ - create the database as described above.
- ‘Port 8080 was already in use’ - close the other program that is using the port.

## Test with Postman

1. Start the API as described above.
2. In Postman, click Import and choose ‘propertyand-asset-rental-management-platform-midterm.postman\_collection.json’ from the root of this repository.
3. Each request is named after its requirement ID (A2, B1, C4, D2 and so on). Run one, then try its failing case (404, 400 or 409).
4. The base URL is ‘http://localhost:8080’.

## Seed data
Test data was entered through the application's own save endpoints (Postman):
4 property types, 4 properties, 3 property managers, 5 tenants and 10 rental contracts,
covering all four contract statuses.

