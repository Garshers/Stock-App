# Stock Market Application with Alpha Vantage API

This Java application will enable users to retrieve and analyze stock market data using the free version of the Alpha Vantage API. It uses a backend (Java/Spring Boot) to fetch data from a financial API (e.g., Alpha Vantage) and a frontend (React) to display the information in a user-friendly way.

---

## Prerequisites

Before you can run the application, ensure you have the following installed:

**Backend (Java/Spring Boot):**

- **JDK** (Java Development Kit) 17 or later - [Download](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven** 3.6 or later - [Download](https://maven.apache.org/download.cgi)
- **Database:** (e.g., MySQL, PostgreSQL, H2 for development) - Instructions for setup will depend on your chosen database.
- **IDE:** IntelliJ IDEA, Eclipse, VS Code, or any other Java IDE.

**Frontend (React):**

- **Node.js** 16 or later (LTS recommended) - [Download](https://nodejs.org/)
- **npm** (Node Package Manager) 8 or later (comes with Node.js) or **yarn** - [Download](https://yarnpkg.com/)
- **IDE:** VS Code (recommended), or any other IDE suitable for JavaScript/React development.
- **Web Browser:** Any modern browser (e.g., Google Chrome, Firefox, Safari).

**API Key:**

- You'll need an API key from a financial data provider.  Alpha Vantage is a common choice, but others may be used.  Sign up with your chosen provider to get a free or paid API key.


---

## Features

**Backend:**

- Fetches stock price data from a financial API.
- Validates and processes JSON responses from the API.
- [Not implemented] Stores fetched and user data in a database.
- Provides REST endpoints for the frontend to access data.

**Frontend:**

- Displays stock information in a graphical user interface (GUI) using charts and tables.
- [Not implemented] Allows users to search for stocks by symbol.
- Creates charts of historical stock prices.
- Displays key financial metrics.
- [Not implemented] Provides a dashboard for portfolio display. (If implemented)
- [Not implemented] Manages user authentication and authorization.

---

## **Getting Started**

### **1. Clone the Repository**

```bash
git clone https://github.com/your-repo/stock-market-app.git
cd stock-market-app
```

### **2. Configure the API Key**

- Add your API key to the application configuration. Update the `URLCreator` file:

```java
private static final String API_KEY = "demo";
```

### **3. Backend Setup**

#### Using Maven:

```bash
mvn clean install
mvn spring-boot:run
```

#### Using an IDE:

- Open the project in IntelliJ IDEA or Eclipse.
- Run the `StockAppApplication` class.

---

### **4. Frontend Setup**

- Navigate to the frontend directory
- Start the development server:

```bash
   npm start
```

---

## **API Integration**

- Example of an API request to retrieve daily time series data:

```
https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=YOUR_API_KEY
```

---

## Generating Documentation (Javadoc)
This project uses Maven for building and dependency management. To generate the project documentation, including Javadoc, follow these steps:

1. Open a terminal: Navigate to the project's root directory in the terminal.
2. Run the command: Execute the following Maven command:

   ```bash
   mvn clean javadoc:javadoc
   ```

(`target` file is excluded from commits by `.gitignore`)

---

## "Port 8080 Already in Use" error

### How to Find and Kill a Process Using Port 8080:

#### On macOS or Linux:
1. Open a terminal (as administrator) and run the following command to find the process using port 8080:

    ```bash
   lsof -i :8080
   ```
This will show the process ID (PID) of the application using port 8080.

1. To kill the process, use the kill command with the PID obtained from the previous step:
    ```bash
   kill -9 <PID>
   ```
Replace <PID> with the process ID.

#### On Windows:
1. Open a terminal (as administrator) and run the following command to find the process using port 8080:

    ```bash
   netstat -ano | findstr :8080
   ```
This will show the process ID (PID) of the application using port 8080.

1. To kill the process, use the kill command with the PID obtained from the previous step:
    ```bash
   taskkill /PID <PID> /F
   ```
Replace <PID> with the process ID.

---

## License

This project is licensed under the MIT License.

---

## **Contributions**

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

