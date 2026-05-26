
#  Finance Tracker API

A Spring Boot-based REST API to manage personal finances — track income, expenses, and analyze spending patterns.

##  Features
-  **Add Transactions** (Income / Expense)
-  **View All Transactions**
-  **Filter by Category / Date**
-  **Update Transaction**
-  **Delete Transaction**
-  **Calculate Balance** (Income - Expense)
-  **Monthly Summary** *(Coming Soon)*

## Tech Stack
-  **Java 17+**
-  **Spring Boot**
-  **Spring Data JPA (Hibernate)**
-  **MySQL / H2 Database**
-  **Maven**
-  **REST API** (Postman Tested)

## 📁 Project Structure
```text
finance-tracker
│── controller        # REST Controllers
│── service           # Business Logic
│── repository        # JPA Repositories
│── entity            # Database Entities
│── dto               # Data Transfer Objects
│── exception         # Global Exception Handling
│── config            # Configurations


##  Setup Instructions

###  Clone Repository
```bash
git clone https://github.com/Amitmourya8085/Finance-Tracker.git
cd Finance-Tracker
## Configure Database

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
## Run Application
mvn spring-boot:run

App runs on:

http://localhost:8080
📬 API Endpoints

➕ Add Transaction
POST /api/transactions

📄 Get All Transactions
GET /api/transactions

🔍 Get by ID
GET /api/transactions/{id}

✏️ Update Transaction
PUT /api/transactions/{id}

❌ Delete Transaction
DELETE /api/transactions/{id}

🧾 Sample Request
{
  "title": "Salary",
  "amount": 50000,
  "type": "INCOME",
  "category": "JOB",
  "date": "2026-05-24"
}
📌 Future Improvements

🔐 JWT Authentication

👤 User-based accounts

📊 Dashboard with charts

🌐 Frontend (HTML/CSS/JS or React)

🤖 AI Insights (Spending Analysis)

🙋‍♂️ Author
Amit Mourya
🚀 Backend Developer (Spring Boot Learner)

⭐ Contribution

Feel free to fork and contribute!
1️⃣ Clone Repository
Bash
git clone [https://github.com/Amitmourya8085/Finance-Tracker.git](https://github.com/Amitmourya8085/Finance-Tracker.git)
cd Finance-Tracker
