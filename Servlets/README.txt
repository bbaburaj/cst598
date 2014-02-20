Student Information Form and Server
******************************************************************************************
The following application enables students to enter some common information about them. 
The application is accessible via 
http://localhost:8080/lab3Task1
OR
http://<ip>:8080/<context>
<context> = lab3Task1

The students are requested to enter their:
-> student id
-> first name
-> last name
-> computer languages they know
-> days of the week they are free
-> high school

Accessing the form
******************************************************************************************
The information entered via the students is stored on a physical file located at
${tomcat-home}/webapps/lab3Task1/resources/students.txt

Viewing the information
******************************************************************************************
All student info can be viewed via
http://<ip>:8080/lab3Task1/studentServlet
http://<ip>:8080/<context>/<servlet_name>
<context> = lab3Task1
<servlet_name> = studentServlet

Searching the information
******************************************************************************************
Now the servlet allows us to search for information.
http://<ip>:8080/<context>/<servlet_name>?searchTerm1=value1&searchTerm2=value2+value3
where in the searchTerms are:
-> firstname
-> lastname
-> languages
-> days
-> school

Since metacharachters like # are + cause some issues within the values we request users to
enter the following values 
cpp instead of c++
csharp instead of c#
jsharp instead of j#
E.g:
http://localhost:8080/lab3Task1/studentServlet?languages=csharp
