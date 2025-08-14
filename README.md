Final Project Statement

Project Overview
The objective of this project is to implement a university personnel management system in Java.
This program will manage different types of university personnel, including Students, Faculty, and Staff, each with unique attributes.

For each type of personnel, the program will store relevant information such as ID, name, and additional specific data
(e.g., GPA for students, rank for faculty, department for staff).
Your delivered code should demonstrate concepts such as inheritance, abstract classes, abstract methods, and method overriding.

Class Design and Attributes

1. Student Class
   Attributes:
     fullName: String
     id: String (must follow the format: LetterLetterDigitDigitDigitDigit, e.g., ab1234)
     gpa: double
     creditHours: int (number of credit hours currently taken)
   Additional Requirements:
     Tuition is calculated at $236.45 per credit hour, with a $52 administrative fee.
     A 25% discount is applied if the student’s GPA is 3.85 or higher.
     The program must include getters, setters, and two constructors.

2. Faculty Class
   Attributes:
     fullName: String
     id: String
     department: String (choose from Mathematics, Engineering, English)
     rank: String (either Professor or Adjunct)
   Requirements:
     Faculty details should be printed with the appropriate department and rank.

3. Staff Class
   Attributes:
     fullName: String
     id: String
     department: String (choose from Mathematics, Engineering, English)
     status: String (Part-time or Full-time)

Inheritance and Abstract Classes
Person (abstract class):
  Both Student and Employee will inherit from the Person class, which contains common properties like fullName and id.
  The Person class must include:
    An abstract method: public abstract void print();
    The print() method will be overridden by Student, Faculty, and Staff to display respective details
    (e.g., tuition invoice for students, employee information for faculty/staff).

Employee (abstract class):
  This class will serve as a base for Faculty and Staff, containing shared properties and methods between a faculty and a staff member.

Program Requirements

1. Array or Collection:
   Outside of the classes mentioned above, use an array (or any suitable collection like ArrayList or LinkedList)
   to manage up to 100 personnel (of type Person). Ensure all objects of type Student, Faculty, and Staff are stored within the same collection.

2. Menu System:
   Provide the following options in a menu-driven program:
     1. Enter Faculty Information
     2. Enter Student Information
     3. Print Tuition Invoice for a Student
     4. Print Faculty Information
     5. Enter Staff Information
     6. Print Staff Information
     7. Delete a Person
     8. Exit Program
   Your code should be case insensitive. Example: Part-Time and PART-TiMe are the same.

3. Validation and Exception Handling:
   ID Format Validation: Ensure IDs follow the format: LetterLetterDigitDigitDigitDigit.
   Handle invalid input, including incorrect ID formats, GPA values, and department names.
   Handle duplicate IDs by checking for existing IDs when adding personnel.

4. Sorting:
   Allow sorting of students either by GPA (in descending order) or by name.
   Implement sorting using Java’s Comparable or Comparator interfaces.

5. Report:
   Upon choosing Exit Program, prompt the user to generate a report, which should be saved to report.txt.
   The report must include:
     Faculty (sorted by department)
     Staff
     Students (sorted by GPA or name, based on user preference)

6. Exceptions:
   Your program should be robust and handle run-time errors (Your program shouldn’t crash for any reason), such as:
     Invalid entries for faculty ranks, departments, or student GPAs.
     Duplicate ID entries.
     Input mismatch errors when entering data.

Sample Run
Below is an example of how your program should interact with the user.
Your code should allow the user to enter the right information/format up to three times.
After the third attempt, bring the main menu back up so the user can choose another option.

Welcome to the Personnel Management System
Choose one of the options:
1- Enter the information of a faculty
2- Enter the information of a student
3- Print tuition invoice for a student
4- Print faculty information
5- Enter the information of a staff member
6- Print the information of a staff member
7- Delete a person
8- Exit Program

Enter your selection: 2
Enter student info:
Name: Julia Alvarez
ID: ju1254
Invalid ID format. Must be LetterLetterDigitDigitDigitDigit
Try again!
Enter student info:
Name: Julia Alvarez
ID: ju1254
GPA: 3.26
Credit hours: 7
Student added!

Choose one of the options:
1- Enter the information of a faculty
2- Enter the information of a student
3- Print tuition invoice for a student
4- Print faculty information by id
5- Enter the information of a staff member
6- Print the information of a staff member
7- Delete a person
8- Exit Program

Enter your selection: 4
Enter the faculty ID: jx1250
Sorry no faculty with ID = jx1250

Choose one of the options:
1- Enter the information of a faculty
2- Enter the information of a student
3- Print tuition invoice for a student
4- Print faculty information by id
5- Enter the information of a staff member
6- Print the information of a staff member
7- Delete a person
8- Exit Program

Enter your selection: 3
Enter the student’s ID: ju1254
Tuition invoice for Julia Alvarez:
-----------------------------------------------------------------
Julia Alvarez ju1254
Credit Hours: 7 ($236.45/credit hour)
Fees: $52
Total payment (after discount): $1,707.15
------------------------------------------------------------------

Choose one of the options:
1- Enter the information of a faculty
2- Enter the information of a student
3- Print tuition invoice for a student
4- Print faculty information
5- Enter the information of a staff member
6- Print the information of a staff member
7- Delete a person
8- Exit Program

Enter your selection: 8
Would you like to create the report? (Y/N): y
Would you like to sort your students by GPA or name? (Enter 1 for GPA. Enter 2 for name): 1
Report created and saved to report.txt!
Goodbye!

Report (report.txt)
The final report should print the data in the following format:
Report created on 04/02/2025
*******************************
Faculty Members
-------------------------
1. John Miller
ID: jo7894
Professor, Engineering

Staff Members
-------------------
1. Jamal Kareem
ID: ja6980
English, Full Time

Students (Sorted by GPA)
--------------------------------------------
1. Julia Alvarez
ID: ju1254
GPA: 3.26
Credit hours: 7

2. Matt Jones
ID: ma0258
GPA: 2.78
Credit hours: 0
