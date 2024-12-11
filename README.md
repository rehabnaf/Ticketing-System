# Ticket Management System

This is a multi-threaded application that simulates real-time interaction between customers and vendors where vendors release tickets and customers purchase tickets based on ticket availability.

## Getting Started

### Prerequisites

* Java JDK 21
* IntelliJ IDEA

### Usage

1. Open the source code using IntelliJ IDEA
2. Add the Maven build tool to the project by using the "add framework support" option on IntelliJ.
3. Add the below xml to the generated pom.xml file so you can utilize the Gson library to support the serialization and deserialization of objects.
   ```
   <dependencies>
      <dependency>
      <groupId>com.google.code.gson</groupId>
      <artifactId>gson</artifactId>
      <version>2.11.0</version>
      </dependency>
    </dependencies>
   
    ```
