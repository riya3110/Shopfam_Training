# Shopfam_Training 
## Table of Content
- [Features]()
- [Demo](https://github.com/riya3110/Shopfam_Training/blob/main/README.md#demo)
- [Overview](https://github.com/riya3110/Shopfam_Training/blob/main/README.md#overview)
- [Technical Aspects](https://github.com/riya3110/Shopfam_Training/blob/main/README.md#technical-aspects)
- [Installation](https://github.com/riya3110/Shopfam_Training/blob/main/README.md#Installation)
- [Technologies used](https://github.com/riya3110/Shopfam_Training/blob/main/README.md#technologies-used)
    
  ## Demo
  ### Frontend 
  **Login Page**
  ![fronend_0](https://github.com/riya3110/Shopfam_Training/assets/69893597/f57f7b1f-1dc9-49b4-b60b-c7d8b5cb8401)

  **Home Page**
  ![frontend_1](https://github.com/riya3110/Shopfam_Training/assets/69893597/3e318df4-4d5a-4097-a454-b2cef2b0f697)

  **Categories Page**
  ![frontend_2](https://github.com/riya3110/Shopfam_Training/assets/69893597/ffbfd9f6-4acc-4365-b069-7015912755fb)

  **Categories Page**
  ![frontend_3](https://github.com/riya3110/Shopfam_Training/assets/69893597/2ff68747-b299-4bee-95fd-25ba310544d1)

  **Product Details Page**
  ![frontend_4](https://github.com/riya3110/Shopfam_Training/assets/69893597/ef57d15f-4f40-418a-a372-859b69e0d3c0)

  **Shopping Cart Page**
  ![frontend_5](https://github.com/riya3110/Shopfam_Training/assets/69893597/9d595957-805f-4632-8766-27d1567d31b8)

  **Email Handler Page**
  ![frontend_6](https://github.com/riya3110/Shopfam_Training/assets/69893597/28ccf5d0-7bf2-4387-a934-3df9ef87ac33)

  ### Backend
  **Login Page**
  ![backend_1](https://github.com/riya3110/Shopfam_Training/assets/69893597/1d4abf06-3bcb-4436-b65e-c887b4bf84a8)

  **Users Page**
  ![backend_2](https://github.com/riya3110/Shopfam_Training/assets/69893597/b91a6f9e-01b4-456d-bb08-fdd1977cbe69)

  **Manage Categories Page**
  ![backend_3](https://github.com/riya3110/Shopfam_Training/assets/69893597/792dda6f-dd91-4f4a-bfbd-4093d0ba968c)

  **Manage Brands Page**
  ![backend_4](https://github.com/riya3110/Shopfam_Training/assets/69893597/c3b87c9a-1012-4a99-b6d3-d7ca2da95539)

  **Manage Products Page**
  ![backend_5](https://github.com/riya3110/Shopfam_Training/assets/69893597/12a57fa7-b25d-4f24-b15d-32444d37ccac)

  **Manage Customers Page**
  ![backend_6](https://github.com/riya3110/Shopfam_Training/assets/69893597/5c234e9f-4d66-4bb7-9c65-f34d3cd7b0a0)

  **Manage Shipping Rates Page**
  ![backend_7](https://github.com/riya3110/Shopfam_Training/assets/69893597/e8373ef0-8af2-4daf-9e42-95e62e3a2e13)

  **Manage Orders Page**
  ![backend_8](https://github.com/riya3110/Shopfam_Training/assets/69893597/8c76b8f9-19b6-4761-aba6-9378a3775c0d)

  **Manage Settings Page**
  ![backend_9](https://github.com/riya3110/Shopfam_Training/assets/69893597/cf7dd89b-f30c-4f18-a3ee-7ea5d5e95379)

  **Create User Page**
  ![backend_10](https://github.com/riya3110/Shopfam_Training/assets/69893597/e4a15afe-af5b-4825-8535-f719d4f7fa2c)

  **Edit User Page**
  ![backend_11](https://github.com/riya3110/Shopfam_Training/assets/69893597/7d9440a0-e234-4e9d-9ea2-35f76a1ffafd)

  **Edit Categories Page**
  ![backend_12](https://github.com/riya3110/Shopfam_Training/assets/69893597/06af9004-e710-4586-afa0-2ff34dd97dba)

  **Edit Products Page**
  ![backend_13](https://github.com/riya3110/Shopfam_Training/assets/69893597/dd7b24ad-ef26-4126-889b-8469611202a8)
  
  ## Overview
  Developed a complete shopping website using technologies such as Java , Spring Boot , Hibernate , Thymeleaf , Bootstrap , JQuery , HTML , RESTful Webservices.... putting all these 
  peices together built a WebApp named **Shopfam - An E-Commerce Web Application for Shopping Electronics**

  ## Features
  - Register a new account and log in as an existing user
  - Register using Google Account log in
  - Search Products using keyword and/or Category
  - Display products with details about them(name , picture , image , price)
  - Add Produt to shopping cart and ability to remove it
  - See all products in user's shopping cart
  - Sign out or delete user's account
  - Update Password
  - Update the user's Shipping Address and/or Remove the address
  - Get the Google mail for customer registration and Verification
  - Track status of user's orders

  ## Technical Aspects
  The working of the project has 2 project directories which are explained one after other in sequence
  - **ShopfamWebParent**
    the spring starter project , a subdirectory under the directory of Shopfam project by creating a new folder as a module 2 of shopfamProject
    the purpose of this project is to have the common dependencies between the two sub projects ( **backend** and **frontend**)

  - **Backend - This is an admin App**
    - In the Backend their are 7 type of users which will have acces to application-
      - **Admin** - manages everything on websites including manage settings and manage users.
      - **Editor**  -   manage categories , manage articles , manage brands , manage menus, manage products
      - **Salesperson** - manage orders, customers, update product price , view products , manage shipping rates.
      - **SHIPPER**-  view products , view orders , update order status
      - **Assistant** -  manage questions , reviews
      
  - **FrontEnd - This is Shopping App (The Shopping UI for Customers)**
    - In the Front end customers can-
       - Manage Shopping cart
       - View products
       - View articles
       - Manage addresses
       - Checkout
         
  - **ShopfamCommon**
    The spring starter project , a subdirectory under the directory of Shopfam Project by creating a new folder .
    The project shopfam Common is for developing the shared lib or shared code between the backend and frontend
    the shopfam common project will have entity classes which are based on spring data JPA 
      - Use packaging as JAR
      - Build type is Maven
      - Use dependency Spring Data JPA

  ## Installation
  The code is written in Java 17 , Spring Boot 3.2.0 

  ## Technologies Used
  - Spring Framework development with Spingboot
  - Spring Data JPA and Hibernate Framework
  - Spring Security for Authentication and Authorization
  - Spring OAuth for social login with Google
  - Thymeleaf template engine for creating dynamic web pages
  - MySQL database for database Management of the project
  - Spring Mail for sending customer Registration confirmation and order cofirmation
  - JUnit ,Spring Test, AssertJ and Mockito in unit tests and Integration tests
  - Spring RESTful WebServices
  - Bootstrap 4 , HTML5 and jQuery 3
  - Java 17 , JSP/Servlets and JavaScript
