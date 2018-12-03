# BBW Stickershop Demo
A demo webshop to show a possible Datatrans integration.

[Stickershop](shop.png)

##  Prerequisites
1. Java 11 installed on your local dev system
2. Maven installed on your local dev system
3. An IDE of your choice (preferably IntelliJ)
4. Some basic experience with [Spring Boot](https://spring.io/projects/spring-boot)
5. A [Datatrans merchantId](https://www.datatrans.ch/en/technics/test-account)

## Run the demo webshop
1. Clone the repository
    ```zsh
    $ git clone git@github.com:datatrans/bbw-techtalk.git
    $ cd bbw-techtalk
    ```
2. Package & run the application
    ```
    $ mvn package
    $ java -jar -DdatatransUsername=<merchantId> -DdatatransPassword=<password> target/bbw-stickershop.jar
    ```
3. Open http://localhost:8080 with your favourite browser