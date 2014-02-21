Student Information Form and Server
******************************************************************************************
The following application enables students to enter some common information about them. 
The application is accessible via 
http://localhost:8080/lab3Task2/welcome


The students are requested to enter their:
-> student id
-> first name
-> last name
-> computer languages they know
-> days of the week they are free
-> high school

Displaying the top 3 matches
******************************************************************************************
The website remembers students based on their id. When registered student 'A' visit the site,
it displays a sorted list of top 3 students who have similar interests as student 'A'.
The top 3 matches are based on the following priority:
1. Students who know the same language are free on the same day and are from the same
school are given the HIGHEST PRIORITY
2. Students who are free on the same day and either know the same languages or are from same
school are given the MEDIUM PRIORITY.
3. Students who know atleast the same language or are free on the set of days or are from the
school are given the LOWEST PRIORITY.

Accessing the static file
******************************************************************************************
The information entered via the students is stored on a physical file located at
${tomcat-home}/webapps/lab3Task2/resources/students.txt

Remembering your last visit
******************************************************************************************
The website remembers your visit until the session is completely closed (all tabs are closed)
The website also remembers students who are registered already based on their id.

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
http://localhost:8080/lab3Task2/welcome?languages=csharp


