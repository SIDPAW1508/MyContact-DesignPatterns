# MyContacts App

## Overview

**MyContacts App** is a Java-based console application that allows users to manage their contacts efficiently.
It is built using **object-oriented programming (OOP)** principles and demonstrates multiple **design patterns** through real-world use cases.

The application supports user registration, contact management, search, filtering, tagging, and more in a structured and modular way. 

---

## Features

* User Registration & Login
* Add, View, Edit, Delete Contacts
* Bulk Delete Contacts
* Search Contacts (Name, Phone, Email)
* Advanced Filtering & Sorting
* Tag Management (create & apply tags)
* Contact Limits for Free Users
* Observer-based logging for actions

---

## Tech Stack

* Java (Core Java, OOP)
* Collections Framework
* Java Time API
* Design Patterns
* Console-based UI

---

## Project Structure

```id="k2m9fd"
com.seveneleven.mycontactapp
│
├── user/
├── contact/
│   ├── builder/
│   ├── factory/
│   ├── filter/
│   ├── search/
│   ├── service/
│   ├── tag/
│   └── model/
│
└── Main.java
```

---

## Key Functionalities

### 1. User Management

* Register with name, email, password
* Login with authentication strategy
* Password hashing for security

### 2. Contact Management

* Add contacts (Person / Organization)
* Multiple phone numbers & emails
* Edit and delete contacts
* Bulk delete by prefix

### 3. Search & Filter

* Search using:

  * Name
  * Phone
  * Email
* Advanced filtering:

  * Keyword filter
  * Date filter
* Sorting:

  * By Name
  * By Date

### 4. Tag Management

* Create reusable tags
* Apply tags to contacts
* Observer updates on tagging

---

## Design Patterns Used

* **Factory Pattern** – Create User and Contact objects
* **Builder Pattern** – Build complex objects
* **Strategy Pattern** – Authentication, Sorting
* **Observer Pattern** – Logging & UI updates
* **Composite Pattern** – Filters and bulk operations
* **Specification Pattern** – Search functionality
* **Decorator Pattern** – Contact display enhancements
* **Command Pattern** – Edit operations

---

## How to Run

1. Compile the project:

```id="v9d3pl"
javac Main.java
```

2. Run the application:

```id="g7k2nx"
java com.seveneleven.mycontactapp.Main
```

---

## Sample Flow

1. Register a new user
2. Login with credentials
3. Add contacts (phone + email)
4. Search or filter contacts
5. Apply tags or edit details

---

## Limitations

* Console-based UI (no GUI)
* Free users limited to 5 contacts
* No database (in-memory storage)

---

## Concepts Covered

* OOP (Encapsulation, Inheritance, Polymorphism, Abstraction)
* Java Collections & Streams
* Exception Handling
* Design Patterns (multiple)
* Modular architecture

---

## Author

Java OOP Contact Management Project
