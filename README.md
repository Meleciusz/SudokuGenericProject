# Table of contents
* [General info](#general-info)
* [Input](#input)
* [Technologies](#technologies)

# General info
SudokuGenericProject is an application that returns a solved Sudoku board. The project is built on a Docker container. The application is primarily built with Spring Boot and RabbitMQ. The purpose of this project was to create a web application with Consumer and Producer modules that communicate with each other through a message broker. The Consumer repeatedly takes user inputs and sends them to a queue, where a finite number of Producer instances continuously listen to the queue, fetch data, and calculate the Sudoku board using a generic algorithm.

# Input
Input: String with partially filled sudoku board (e.g.   7..25..98..6....1....61.3..9....1.......8.4.9..75.28.1.94..3.......4923.61.....4.)

# Technologies 
Project is created with:
* Intelji
* RabbitMQ
* Spring Boot
* Docker
* Postman 
