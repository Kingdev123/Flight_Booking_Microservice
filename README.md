# Floght_Booking_Microservice
# ✈️ Flight Booking Microservices System

A **microservices-based flight ticket booking system** built using **Spring Boot and MongoDB**, designed to demonstrate **real-world business workflows**, **clear service boundaries**, and **synchronous inter-service communication**.

This project intentionally focuses on **business logic and orchestration**, not just CRUD APIs.

---

## 📌 High-Level Overview

The system consists of **two independent Spring Boot microservices**:

1. **Flight Service** – Owns flight data, seat availability, and dynamic pricing logic
2. **Booking Service** – Orchestrates booking workflow and manages booking lifecycle

Both services communicate **synchronously over REST** using **Feign Client** and share a **single MongoDB database** (kept simple for local development and interview demonstration).

---

## 🧠 Core Business Idea

1. Customer requests a **price quote**
2. System calculates **dynamic price** based on demand
3. Customer reviews the price
4. Only after confirmation, seats are **actually booked**


---

## 🧩 Architecture Diagram (Logical)

```
Client (Postman)
      │
      ▼
Booking Service  ────Feign REST────▶  Flight Service
      │                               │
      │                               ▼
      │                         MongoDB (Flights)
      │
      ▼
MongoDB (Bookings & Quotes)
```

---

## 🛠️ Technology Stack

| Layer              | Technology             |
| ------------------ | ---------------------- |
| Language           | Java                   |
| Framework          | Spring Boot            |
| REST Communication | OpenFeign              |
| Database           | MongoDB                |
| Build Tool         | Maven                  |
| API Testing        | Postman                |
| Configuration      | application.properties |

---

## 📦 Services Breakdown

---

# ✈️ Flight Service

### Responsibility

Flight Service is the **single source of truth** for:

* Flight inventory
* Seat availability
* Dynamic ticket pricing

Booking Service **never** calculates price or availability.

---

### MongoDB Collection

**Collection:** `flights`

### Flight Service APIs

#### 1️⃣ Get Seat Quote (Decision-Oriented API)

**Endpoint**

```
POST /flights/{flightId}/quote
```

**Business Logic**

* Validates flight existence
* Validates seat availability
* Calculates dynamic price based on occupancy
* Does **NOT** book seats


# 🎟️ Booking Service

### Responsibility

Booking Service **orchestrates the booking workflow**:

* Initiates quote requests
* Stores quotes
* Confirms bookings
* Manages booking lifecycle

It **never** calculates price or seat availability on its own.

---

### MongoDB Collections

* `booking_quotes`
* `bookings`

---

### Booking Service APIs

#### 1️⃣ Create Booking Quote

**Endpoint**

```
POST /bookings/quote
```

**Flow**

* Calls Flight Service for quote
* Stores quote with status `PENDING`
* Returns price details to client

---

#### 2️⃣ Confirm Booking

**Endpoint**

```
POST /bookings/{quoteId}/confirm
```

**Flow**

* Validates quote
* Confirms seats with Flight Service
* Creates booking
* Marks quote as `CONFIRMED`


## 🔄 End-to-End Flow

1. Client requests quote
2. Booking Service → Flight Service (price & availability)
3. Quote returned and stored
4. Client confirms booking
5. Seats are consumed
6. Booking finalized


---

## ▶️ How to Run Locally

1. Start MongoDB (`localhost:27017`)
2. Run **Flight Service**
3. Run **Booking Service**
4. Use Postman to test APIs
