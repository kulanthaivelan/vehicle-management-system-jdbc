
# 🚗 Vehicle Management System (JDBC + Spring MVC)

This is a simple web-based **Vehicle Management System** built using **Java**, **Spring MVC**, **JDBC**, and **MySQL**. The application allows users to perform CRUD operations (Create, Read, Update, Delete) on vehicle records with features like pagination, filtering, sorting, and CSV export.

---

## 📁 Project Structure

```
com.example.VehicleManagementusingJDBC
│
├── Config
│   └── JDBCUtil.java                 # JDBC connection utility class
│
├── Controller
│   └── VehicleController.java        # Handles HTTP requests and routes
│
├── DAO
│   └── VehicleDAO.java               # Handles database operations
│
├── Entity
│   └── Vehicle.java                  # POJO representing the Vehicle entity
```

---

## 🛠 Technologies Used

* **Java 17+**
* **Spring MVC**
* **JDBC**
* **MySQL**
* **Thymeleaf (assumed for view layer)**
* **Maven** or **Gradle** for dependency management
* **Tomcat** or any Servlet container

---

## 📦 Features

* Add new vehicles
* Edit existing vehicles
* Delete vehicles
* List all vehicles with:

  * Pagination
  * Keyword filtering (search by make, model, or registration no)
  * Sorting (by ID, make, model, etc.)
* Export all vehicles as a CSV file
* Basic validation (empty field check, duplicate registration number check)

---

## 🧱 Database Schema

```sql
CREATE TABLE vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    registration_no VARCHAR(255) UNIQUE NOT NULL,
    make VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    year INT NOT NULL
);
```

---

## ▶️ Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/vehicle-management-jdbc.git
cd vehicle-management-jdbc
```

### 2. Configure the database

Ensure MySQL is running and update your credentials in:

📄 `Config/JDBCUtil.java`

```java
private static final String URL = "jdbc:mysql://localhost:3306/DemoDatabase";
private static final String USER = "root";
private static final String PASSWORD = ""; // set your MySQL password
```

Then create the database and table using the SQL provided above.

### 3. Build & Run

Using Maven:

```bash
mvn clean install
```

Run on Tomcat or any Servlet container. Access it via:

```
http://localhost:8080/vehicles
```

