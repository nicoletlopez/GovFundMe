# GovFundMe


A project-donations system website project implemented in JSF with JPA - Hibernate as the persistence manager 

This project was created as a final project requirement for the class Enterprise Java


Requisites:
1. An upload directory (C:/Users/userName/upload)
2. Database (MySQL)
3. Server Environment (Tomcat)

Setup: 
1) Create a local MySQL database named "hb" (port 3306)
2) Locate and run the file Application/src/tk.govfundme/models/SampleInserts.java
3) Run the project inside a Tomcat server environment

Optional:
  In order to insert sample data into the database:
  1) Navigate to GovFundMe/Application/src/META-INF/context.xml
  2) From \<property name="hibernate.hbm2ddl.auto" value="update"/\> change the value to
      \<property name="hibernate.hbm2ddl.auto" value="create"/\>
  3) Navigate to GovFundMe/Application/src/models/SampleInserts.java
  4) Run the program
  5) Revert the change you made to the context.xml file 

Troubleshooting:
1) To get around the error report stating that required port number 1099 is in use, do the following:
  a) Go to task manager
  b) Terminate all tasks with the designation of "Java SE Platform Binary (TM) "
