# CityConnect Project 

This is a Spring Boot Rest Project to determine if two cities are connected . 

# About this project 
> This goal of the project is to see if two 
>cities are connected and returns "YES" or "NO" 

This project has been written in Java .The tech stack used for the project 
   -  Java 1.7 or Higher
  -  Maven
  - Spring boot 
  - Swagger 
  - Junit
  - 
# Building this project 
This project can be imported as a Maven or Spring project . 
- Build the project 
- Run the project as Spring boot 

This project after compiling can be either called directly using the url 
http://localhost:8080/connected?city1=city1&city2=city2
or we can also use swagger 

# Given When Then :
We are given a text file which tells us the cities that are connected to each other . 
We use Breadth first apppoach to read and determine if the cities are connected and return the answer 

For eg:
Given the text file 
-->cities.text is:
Boston, New York
Philadelphia, Newark
Newark, Boston
Trenton, Albany

When 
http://localhost:8080/connected?city1=Boston&city2=Newark

Then 
Should return yes