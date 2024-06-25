# <img src="https://github.com/MarcMartz/books-api-challenge/assets/88258260/80f7f492-6674-4ae2-b344-e6c32edea5cc" alt="logo-conversion-de-moneda" width="100" height="100"> LiterAlura


<p align="center">
  <img src="https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green">
  <img src="https://img.shields.io/badge/JAVA-17-orange">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen">
  <img src="https://img.shields.io/badge/PostgreSQL-16-blue">  
</p>

## Descripción del proyecto
Catálogo de Libros desarrollada en Java que interactúa con la API [Gutendex](https://gutendex.com/) para obtener 
metadatos de libros electrónicos del Proyecto Gutenberg. Esta aplicación ofrece interacción textual (vía consola) con 
los usuarios.

<p align="center">
  <img src="https://github.com/MarcMartz/books-api-challenge/assets/88258260/6221d425-c578-49e7-b884-3fc1dae734f1">
</p>

<h4 align="center">
:construction: Proyecto en construcción :construction:
</h4>

## :hammer:Funcionalidades

- Obtiene información detallada sobre cada libro, como título, autor, idioma,
- Busca libros por título y los almacena en una base de datos, 
- Lista los libros registrados,
- Lista los autores registrados,
- Lista los autores vivos en un determinado año,
- Muestra la cantidad de libros almacenados por idioma inglés o español.

## Aplicación

<p align="center">
  <img src="https://github.com/MarcMartz/books-api-challenge/assets/88258260/b83db915-fe42-4404-b3a4-48d4a29fe939">
</p>

## 📁 Acceso al proyecto

Sigue estos pasos para configurar el proyecto en tu entorno local:

1. Clona el repositorio
    ```sh
    git clone https://github.com/MarcMartz/books-api-challenge.git
    ```
2. Navega al directorio del proyecto
    ```sh
    cd books-api-challenge
    ```
3. Configura la base de datos PostgreSQL
    - Crea una nueva base de datos llamada `literalura`.
    - Actualiza el archivo `application.properties` con tus credenciales de PostgreSQL.

4. Instala las dependencias y compila el proyecto
    ```sh
    mvn install
    ```

## 🛠️ Abre y ejecuta el proyecto

Ejecuta el método main de la clase `LiteraluraApplication.java`

## Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Maven
- Postgresql
- Gson
- IntelliJ IDEA
