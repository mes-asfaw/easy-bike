# Easy-bike

This project creates simple information about Oslo city bikes. It consumes existing endpoints to generate a report that tells the number of available bikes and the number of available docks at each station at a given time.

### Prerequisites

Maven, java 8 and git

### Deployment

Follow the instruction below to build the project:

```
git clone https://github.com/mes-asfaw/easy-bike.git
cd /easy-bike 
mvn clean package
```

Following the above instruction enables to create a jar file in a targer folder which can be executed as follows:

```
cd /target 
java -jar easy-bike-0.0.1-jar-with-dependencies.jar
```

The expected output looks like:

```
[{
  "name" : "Borgenveien",
  "num_bikes_available" : 1,
  "num_docks_available" : 9
}, {
  "name" : "Enerhaugen",
  "num_bikes_available" : 13,
  "num_docks_available" : 12
}]
```

### Tests

No tests implemented.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Meskerem A. Hailemichael** - *Initial work*