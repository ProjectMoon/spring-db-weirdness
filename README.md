This is a reproducible test case for Spring + MySQL JDBC connector not properly recognizing an output parameter (or me doing something wrong that I've missed!).

To run the test: `mvn clean test`

The scenario is:

* The stored procedure has 9 parameters. The last one is an OUT parameter.
* We have declared the stored procedure by extending Spring's StoredProcedure class.
* We get an error complaining about parameter number 9 not being an OUT parameter.
* ...However, parameter number 9 is indeed an OUT parameter.

MySQL default login info in UnitTest-Context.xml:

* Host: localhost
* Username: root
* Password: none
