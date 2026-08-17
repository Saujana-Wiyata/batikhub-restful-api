# ⋆˚✿˖° Batik Hub Marketplace ˏ༻❁༺ˎ

**Batik Hub** is a platform for buying batik products such as fabric🧵, clothing👕, and accessories☂️. I created this platform because no company or MSME (Micro, Small and Medium Enterprises or in Indonesia called UMKM) in Indonesia has a website for purchasing batik-patterned products without involving a third party, making it difficult for people, especially international customers🌏, to purchase batik-patterned products. In fact, this issue has also been raised by Instagram user David Alfa Sunarna (`@davidalfasunarna`), who encouraged MSMEs to create their own marketplaces to sell their products. This aligns with the international trend of selling products on their own websites without relying entirely on third-party marketplaces🛍️.

Although I **haven't published** Batik Hub, I hope this idea can be realized by myself or others in the future. My goal in creating this project is to improve my programming logic 🚀 and **as a portfolio** for the industrial world 🏬.

## 🛠️ Tech Stack & Dependencies
The technologies and tools I used to build this platform include:

### 🚀 Core Technologies
- **Backend Framework:** Java with Spring Boot 4.x
- **Database:** PostgreSQL (Relational Database Management System)
- **Frontend Styling:** Tailwind CSS (for responsive and modern web styling)

### 🧰 Utilities & Libraries
- **Spring Data JPA & Hibernate:** For Object-Relational Mapping (ORM) and seamless database communication.
- **Mustache:** As the server-side template engine for rendering dynamic HTML views.
- **Lombok:** To eliminate boilerplate code.
- **Validation (Jakarta Validation):** For robust data constraints and request payload validation.
- **Spring Configuration Processor:** For generating metadata for custom configuration properties.

### 📦 Project Dependencies
When initializing the project on Spring Initializr [start.spring.io](http://start.spring.io/), the following baseline dependencies were selected:
- `Spring Data JPA` — Robust data persistence layer.
- `Spring Web` — Build RESTful APIs and MVC applications using Spring MVC.
- `Mustache` — Lightweight and logic-less templating framework.
- `Lombok` — Developer productivity tool to reduce boilerplate Java code.
- `Validation` — Java Bean Validation support using Hibernate Validator.
- `Spring Configuration Processor` — Generating metadata for developer.

## 🗄 Database Architecture (ERD)
<p align="center">
  <img src="batikhub-erd.png" width="700"/>
</p>
There are 3 main entities :

- `Member`
- `Produk`
- `Omset`

The `Member`👥 entity has a **Many-to-Many** relationship with the `Produk`📦 entity because many buyers can buy many products at once. However, I need additional data between these two entities, namely the purchase date📅 and the date the products arrived🚚, so I added a transaction to the ERD, namely `Transaksi`💳. In addition, the relationship between `Produk` and `Omset`📊 is **One-to-One** because one product can only have one in the `Omset` table. If there is a new transaction, the `Omset` table will automatically update through the `Produk` table and there is no need to add new data to the `Omset` table, just update the `jumlah_penjualan` column.

Additionally, I also added a `Staff`👔 table, which isn't related to any other entities. There's no specific reason to add this table. However, I want this application to run according to industry standards, as there will definitely be employees using the application, so a `Staff`👔 table is necessary. The reason this table doesn't have a relationship with any other entities is because there's no corresponding table to relate it to, so I decided to leave this table as a standalone table without any relationships.

## 🧬 Entity Operations & Data Access
Here is the list of database operations (Spring Data JPA Repository methods) implemented and utilized for each entity in this project:

### 👤 Member Entity
Besides the usual CRUD method provided by Spring Jpa, I used the custom method:
- `findByEmailAndPassword` — Find data member in database by their email and password for authenticating during login. 

### 👕 Produk Entity
Besides the usual CRUD method provided by Spring Jpa, I used the custom method:
- `reduceStock` — Reducing the stock of products that have been successfully sold.
- `findByNameLike` — Search for products by name.

### 💳 Transaksi Entity
Use CRUD method that provided by Spring Jpa without any custom method.

### 📊 Omset Entity
Besides the usual CRUD method provided by Spring Jpa (except regular update), I used the custom method:
- `tambahJumlahPenjualan` — Increase the sales volume of each product.
- `jumlahHargaPerProduk` — Summarize the revenue for each product.
- `jumlahOmset` — Summarize the revenue for all products.
- `jumlahProdukTerjual` — Summarize total quantity sold for all products.

### 👔 Staff Entity
Besides the usual CRUD method provided by Spring Jpa, I used the custom method:
- `findByEmailAndPassword` — Find data staff in database by their email and password for authenticating during login.

## 🌟 Key Features
- **Dual-Role Authentication :** Integrated custom authentication for both `Members` (customers) and `Staff` (employees) using secure `HandlerInterceptor` and Session Management.
- **Product Catalog & Advanced Search :** 
Dynamic product exploration allowing users to filter and find batik by names or specific attributes.
- **Automated Sales & Revenue Tracker :** 
Real-time data synchronization between `Transaksi` and the `Omset` table, utilizing Spring Data JPA to automatically update sales metrics upon order placement.
- **Secure Route Whitelisting :** 
Strict URL access control ensuring only public endpoints (like `/promo` and `/findproduct`) are open, while sensitive views like `/profile` require authentication.


## 🚀 Getting Started
Follow these steps to run the project locally:

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- PostgreSQL
- Maven

### Installation & Setup
1. **Clone the repository :**
   ```Bash
   git clone https://github.com/Saujana-Wiyata/batik-hub.git
   cd batik-hub
   ```

2. **Database Configuration :**
    Create a database for this project in your PostgreSQL. Then, configure your `src/main/resources/application.properties`.

    **minimum configuration :**

    ```Properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/[database_name]
    spring.datasource.username=your_postgresql_username
    spring.datasource.password=your_postgresql_password
    ```
3. **Run the Application :**
    Run this command in your project terminal.
    ```Bash
    mvn spring-boot:run
    ```
    Open http://localhost:8080 in your browser.