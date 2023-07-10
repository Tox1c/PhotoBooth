# PhotoBooth

## Build
Uses [Maven](https://maven.apache.org/) as a build tool.

to compile the code and build jar and run tests
```
mvn clean install
```
to execute tests only
```
mvn test
```

### Important note
Only text mirror code could be used directly from command line. To check other code snippets
please see unit tests with different business cases.

### Text mirror

To run locally code for text mirroring use
```
java -jar ./PhotoBooth/target/PhotoBoothTest-1.0-SNAPSHOT.jar
```
it will ask you to enter the string line you want to mirror. After pressing Enter you'll see the mirrored text
Also to use it in your code you can simply create an object of 
```java
MirrorService mirrorService = new MirrorServiceImpl();
String result = mirrorService.mirrorText("your text goes here");
```

### Lucky orders

To check if user is lucky to have free packages use
create instance of [LuckyOrderProcessorImpl](com/gmail/maxkhrebtov/lucky/LuckyOrderProcessorImpl.java) of [OrderProcessor](other_file.md) interface
in your code. 
```java
OrderProcessor processor = new LuckyOrderProcessorImpl(new OrderProcessorImpl(), new OrderService())
processor.process(customer, order);
```
where OrderProcessorImpl.class is a default implementation of order processing and LuckyOrderProcessorImpl is a decorator for it.
OrderService.class is meant to be a service to load previous orders from database. 
Where [Order.java](/src/main/java/com/gmail/maxkhrebtov/order/Order.java) contains information about order time and packages.
Also [Package.java](/src/main/java/com/gmail/maxkhrebtov/order/Package.java)
As result the order object is augmented, and the price for free packages will be set to $0.0

### Taxes calculator
Use [TaxCalculatorService](/src/main/java/com/gmail/maxkhrebtov/taxes/TaxCalculatorService.java)
This service takes a list of [Orders](/src/main/java/com/gmail/maxkhrebtov/order/Order.java)
and as a result you will have [YearTaxReport](/src/main/java/com/gmail/maxkhrebtov/taxes/YearTaxReport.java) object
which contains a set of [MonthTaxReport](/src/main/java/com/gmail/maxkhrebtov/taxes/MonthTaxReport.java)
including calculated taxes for each month accordingly.
```java
List<Order> orders = new ArrayList<>();
        orders.add(new OrderImpl(packages..., date);
        orders.add(new OrderImpl(packages..., date);
        orders.add(new OrderImpl(packages..., date);
        ...
        var report = taxCalculatorService.calculateTaxesByMonths(orders);
```
