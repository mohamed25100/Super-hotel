# Angular

ng new angular-hotel  

? Would you like to add Angular routing? Yes

? Which stylesheet format would you like to use? CSS

ng add @angular/material

ng g c components/hotels

ng g s services/hotel

ng g c components/hotel-details

ng g c components/login

ng g s services/authenticate

ng g s services/token

# Spring

## Dependencies

PostgreSQL Driver

Lombok 

Spring Data JPA

Spring Web

Spring Security

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
    </dependency>

# git

    git init
    git add .
    git commit -m "first commit"
    git branch -M main
    git remote add origin https://github.com/mohamed25100/Super-hotel.git
    git push -u origin main


# jwt-decode

## Install the jwt-decode package:

    npm install jwt-decode

## Install the type declarations for jwt-decode:

    npm install --save-dev @types/jwt-decode


## Import the module correctly:

    import jwtDecode from 'jwt-decode';

## If you still encounter issues, ensure that your tsconfig.json file includes the following settings to properly resolve modules:

    {
        "compilerOptions": {
        "moduleResolution": "node",
        "esModuleInterop": true,
        "allowSyntheticDefaultImports": true
        }
    }