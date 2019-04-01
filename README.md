# SpringJPA_Log4J_H2_Example

注意：需安裝 H2 Database + Log4j 的版本需為 2.10 。 

Log4j 2.11 的版本還在實驗。


此 Demo 為利用 Spring 架構，透過 JPA 存取資料，並用 Log4j 把 log 記錄到 H2 db。

因為 Log4j 的 DB Connection 在 Spring 建 Connection 之前，若要將 Log 記錄到 DB ，需在 Spring initial 後，重新指定 Logger 才行。

見範例，同時可參考 Repository JDBCAppender 及 Log4j2Test。

[REF]：
https://stackoverflow.com/questions/48104672/spring-boot-configure-log4j2-to-use-spring-boot-datasource
http://smasue.github.io/log4j2-spring-database-appender
http://www.andreagirardi.it/blog/how-to-use-spring-datasource-bean-as-data-source-for-log4j-2-jdbc-appender/
