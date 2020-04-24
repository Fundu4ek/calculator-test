# calculator-test
Calculator Test Application

Requires user manual input, uses polish notation

### Prerequisites
In order to build and run application it is required to download and install two additional software pckages [Java JRE](https://www.java.com/download/) and [Apache Maven](https://maven.apache.org/download.cgi)

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
Division by zero gives infinity.

Unknown characters are ignored.

Error handling is poor.