# 🧶 Batik Hub – RESTful API Service
(this readme file doesn't complete yet!!)

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED)

> A production-ready, decoupled RESTful API service built with Java Spring Boot and Spring Data JPA to power the **Batik Hub** Direct-to-Consumer (D2C) marketplace infrastructure.

---

## 📌 Project Overview

This repository represents the **decoupled backend API service** of the Batik Hub ecosystem. Originally designed as a monolithic server-side rendered (SSR) application, this service was refactored into a stateless REST API to support multi-client architectures (Single Page Applications, Mobile Apps, and Third-Party Services).

### 🛠️ Architecture & Key Engineering Highlights
- **Decoupled Architecture:** Utilizes `@RestController` returning standardized JSON payloads (`ResponseEntity<T>`).
- **Data Transfer Objects (DTOs):** Encapsulates payload schemas and leverages `Jakarta Validation` to sanitize incoming data without exposing internal JPA `@Entity` structures.
- **Custom JPA Repositories:** Reuses core data access logic for real-time sales aggregation (`jumlahOmset`) and dynamic inventory management (`reduceStock`).
- **Centralized Exception Handling:** Mapped global exception handlers via `@RestControllerAdvice` to ensure uniform JSON error schemas across all routes.
- **RDBMS Normalization:** Strict relational integrity powered by PostgreSQL.

---

## 🏗️ Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot 4.x
- **Database:** PostgreSQL
- **DevOps & Containerization:** Docker & Docker Compose
---

## 🔌 API Endpoints Summary

All API endpoints are versioned under the `/api/v1` namespace.

### 🛍️ Products (`/api/v1/products`)
| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/v1/products` | Retrieve list of all products | Public |
| `GET` | `/api/v1/products/{id}` | Get product details by ID | Public |
| `POST` | `/api/v1/products` | Create a new product listing | Staff/Admin |
| `PUT` | `/api/v1/products/{id}` | Update existing product details | Staff/Admin |
| `DELETE` | `/api/v1/products/{id}` | Remove a product listing | Staff/Admin |

### 💳 Transactions & Revenue (`/api/v1/transactions` & `/api/v1/omset`)
| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/transactions` | Process purchase & trigger auto `reduceStock` | Member |
| `GET` | `/api/v1/omset` | Fetch real-time revenue analytics (`jumlahOmset`) | Staff/Admin |

---

## 📄 Standardized API Response Format

All API endpoints return data wrapped in a uniform JSON structure:

```json
{
  "code": 200,
  "status": "OK",
  "data": { }
}