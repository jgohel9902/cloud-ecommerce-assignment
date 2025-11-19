# Cloud Native E-Commerce Web Application  
### PROG3350 – Cloud Computing & Application Development  
### Assignment 3 – Cloud Native Web Development  

## Team Members
- **Jenil Gohel**
- **Nirali Sathvara**
- **Samson Ikilama**

---

## Project Overview
This project is a cloud-native e-commerce web application built as part of Assignment 3.  
The application includes:

- A **Product Service** (Spring Boot)
- A **Cart Service** (Spring Boot)
- A **Frontend Website** (HTML, CSS, JavaScript)
- A shared **PostgreSQL database**
- Fully deployed using **Docker Hub** and **Render**

---

## Features
### Product Service
- Stores shoe products
- Supports CRUD operations
- Provides JSON APIs for the frontend

### Cart Service
- Adds items to cart
- Lists cart items
- Removes items
- Empties the cart

### Frontend
- Displays all products in a grid
- Allows “Add to Cart”
- Shows cart page with total cost
- Allows removing items and clearing the cart

---

## Tech Stack
- **Java 17**, Spring Boot  
- **PostgreSQL**  
- **HTML, CSS, JavaScript**  
- **Docker / Docker Hub**  
- **Render (cloud deployment)**  
- **Maven**  

---

## Folder Structure
cloud-ecommerce-assignment/
│── frontend/
│── productService/
│── cartService/
└── README.md


---

## How to Run (Local)
### Backend
1. Navigate into each service folder  
2. Run:
mvn clean package
java -jar target/*.jar


### Frontend
Open:frontend/index.html


---

## Notes
- All services use one shared PostgreSQL database.
- Backend services are containerized and deployed using Docker images.
- Frontend is hosted as a static site.

---

## Presentation
A short video demonstrates:
- Application functionality  
- Code explanation  
- Cloud deployment setup  

