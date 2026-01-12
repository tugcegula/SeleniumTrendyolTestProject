# Trendyol Selenium Test Automation Project

This project is developed to automate user scenarios on the **Trendyol website**, such as login, adding products to the cart, and using filters while searching for products.  
The project uses **Java**, **Selenium**, **TestNG**, and **Maven** technologies.

---

## Technologies Used

- **Java 17**
- **Selenium WebDriver 4.35.0**
- **TestNG 7.9.0**
- **Maven**
- **WebDriverManager**
- **Log4j2**
- **Extent Reports**


---

##  Proje Yapısı

```text
selenium-trendyol-test-project
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── trendyol
│   │   │           ├── pages
│   │   │           │   ├── AddToCart.java
│   │   │           │   ├── BasePage.java
│   │   │           │   ├── FilterByCost.java
│   │   │           │   ├── HomePage.java
│   │   │           │   └── LoginPage.java
│   │   │           └── utils
│   │   │               ├── ConfigReader.java
│   │   │               ├── ElementUtil.java
│   │   │               └── ExtentReporter.java
│   │   └── resources
│   │       └── log4j2.xml
│   └── test
│       ├── java
│       │   └── com
│       │       └── trendyol
│       │           └── tests
│       │               ├── AddToCardTest.java
│       │               ├── BasePageTest.java
│       │               ├── FilterByCostTest.java
│       │               ├── HomePageTest.java
│       │               └── LoginPageTest.java
│       └── resources
│           └── config.properties
├── test-output
│   └── ExtentReport.html
├── pom.xml
├── testng.xml

```
---

## Installation

1. Clone the project:
git clone https://github.com/tugcegula/SeleniumTrendyolTestProject.git

2. Open the project in IntelliJ IDEA or Eclipse

3. Install Maven dependencies:
mvn clean install

---

## Running Tests

To run all tests:
mvn test

To run tests via IDE:
- Right-click on the test class
- Select **Run 'TestNG Test'**

---

## Reporting and Logging

- Test results are reported using **Extent Reports**
- Reports are generated automatically after tests are executed
- Logging is done using **Log4j2**
  
 Extent Reports

- After running tests, Extent Reports automatically generates HTML reports.  
- By default, reports are saved in the `test-output/extent-report/` directory of the project.  
- To view the report, open the `index.html` file in a web browser.  
 
<img width="964" height="548" alt="image" src="https://github.com/user-attachments/assets/3c0e3823-0300-4e3f-a420-1852e9a0c0a1" />

---
## config.properties Usage

The config.properties file is used to store environment-specific configurations such as browser type, base URL, and login credentials. This allows tests to be more flexible and maintainable.

Example config.properties:

- baseUrl=https://www.trendyol.com
- browser=chrome
- username=your_email@example.com
- password=your_password

---
## Maven Dependencies

- Selenium Java
- TestNG
- WebDriverManager
- Log4j2
- Extent Reports

---

## Notes

- Browser drivers are managed automatically via **WebDriverManager**
- Tests are written according to **TestNG framework** structure
- The project is compatible with **Java 17**

---

