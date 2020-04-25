# calculator-test
Calculator Test Application

Requires user manual input, uses polish notation.

Supports decimals, negatives and real numbers.

### Prerequisites
In order to build and run application it is required to download and install two additional software packages:
* [Java 8 or greater](https://www.java.com/download/) 
* [Apache Maven](https://maven.apache.org/download.cgi)

### Build
From project root directory run previously installed maven:
```
mvn clean package
```

### Run
From project root directory execute:
```
java -jar target/calculator-jar-with-dependencies.jar
```

### Notes:
Decimals and negative numbers are supported.

Division by zero gives infinity.

Unknown characters are ignored.

Error handling requires improvement.