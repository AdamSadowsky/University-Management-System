import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Handler;

public class FinalProject  {

	private static Scanner myScanner = new Scanner(System.in);
	static ArrayList<Person> list = new ArrayList<>(100);
	
	public static void main(String[] args) {
	boolean complete = false;
	int option = 0;
	System.out.println("Welcome to the Personnel Management System");
	System.out.println("Choose one of the options:");
	do {
	System.out.println("1. Enter Faculty Information");
	System.out.println("2. Enter Student Information");
	System.out.println("3. Print Tuition Invoice for a Student");
	System.out.println("4. Print Faculty Information");
	System.out.println("5. Enter Staff Information");
	System.out.println("6. Print Staff Information");
	System.out.println("7. Delete a Person");
	System.out.println("8. Exit Program");
	try {
	option = myScanner.nextInt();
	myScanner.nextLine();
	} catch (InputMismatchException e) {
		System.out.println("You must enter a number between 1-8");
		myScanner.nextLine();
		continue;
		}
	switch (option) {
	
	case 1: {
		System.out.println("Enter Faculty Info: ");
		String name = validateName();
		if(name == null) {
			break;
		} 
		
		String id = validateId();
		if(id == null) {
			break;
		}  
		
		String department = validateDepartment();
		if(department == null) {
			break;
		} 
		
		String rank = validateRank();
		if(rank == null) {
			break;
		} else {
			list.add(new Faculty(name, id, department, rank));
		System.out.println("Faculty added!");
		}
		break;
		}
	
	
	case 2: {
		System.out.println("Enter Student Info: ");
		String name = validateName();
		if(name == null) {
			break;
		} 
		
		String id = validateId();
		if(id == null) {
			break;
		}  
		
		double gpa = validateGpa();
		if(gpa == -1) {
			break;
		} 
		
		int credits = validateCredits();
		if(credits == -1) {
			break;
		} else {
			list.add(new Student(name, id, gpa, credits));
		System.out.println("Student added!");
		}
		break;
		}
	
	case 3: {
		studentSearch();
		break;

		}
		
	case 4: {
		facultySearch();
		break;
		}	
	case 5: {
			System.out.println("Enter Staff info");
			String name = validateName();
			if(name == null) {
				break;
			} 
			
			String id = validateId();
			if(id == null) {
				break;
			}  
			
			String department = validateDepartment();
			if(department == null) {
				break;
			} 
			
			String status = validateStatus();
			if(status == null) {
				break;
			} else {
			list.add(new Staff(name, id, department, status));
			System.out.println("Staff added!");
			}
			break;
			}
	
	case 6: {
		staffSearch();
		break;
		}	
	case 7: {
		removePerson();
		break;
	}
	
	case 8: {
		exit();
		break;
		}
	default: {
		System.out.println("Not an option");
	}
	}
	
	} while(option != 8);

	myScanner.close();
	}
	
	private static String validateName() {
		int tries = 3;
		String name = "";
		while(tries > 0) {
			System.out.print("Name: ");
			name = myScanner.nextLine().trim();
			if(name.isEmpty()) {
				System.out.println("Name cannot be empty");
				System.out.println("Try again");
				tries--;
				} else if(!name.matches("^[A-Za-z ]+$")) {
					System.out.println("Name must only contain letters");
					tries--;
				} else {
					return name;
				}
			}
		return null;
		}
	
	
	private static String validateId() {
		int tries = 3;
		String id = "";
		while(tries > 0) {
			System.out.print("Id(Must be LetterLetterDigitDigitDigitDigit): ");
			id = myScanner.nextLine().trim();
			try {
			if(id.isEmpty()) {
				System.out.println("Id cannot be left empty");
				System.out.println("Try again");
				tries--;
				continue;
				} else if(!id.substring(0, 2).matches("[A-Za-z]{2}") || !id.substring(2).matches("[0-9]{4}")) {
				System.out.println("Invalid ID format. Must be LetterLetterDigitDigitDigitDigit");
				tries--;
				continue;
				} else {
					boolean dup = false;
					for(Person p: list) {
						if(p.getId().equalsIgnoreCase(id)) {
							System.out.println("Id has already been used");
							dup = true;
							tries--;
							break;
						}
					}
				if(dup == true) {
					continue;
					} else {
						break;
					}
				}
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("Id must be of length 6");
				tries--;
			}
		}
		if(tries > 0) {
		return id;
		} else {
			return null;
			}
		}
	
	private static float validateGpa() {
		int tries = 3;
		float gpa = 0;
		while(tries > 0) {
			System.out.print("Gpa: ");
			String line = myScanner.nextLine().trim();
			try {
				gpa = Float.parseFloat(line);
			} catch(NumberFormatException e) {
				System.out.println("Wrong input");
				System.out.println("Try again");
				tries--;
				continue;
			}
			if(gpa > 4.0 || gpa < 0.0) {
				System.out.println("Invalid gpa");
				System.out.println("Try again");
				tries--;
				} else {
					break;
				}
			
			}
		if(tries > 0) {
			return gpa;
		} else {
			return -1;
			}
		}
	
	private static int validateCredits() {
		int tries = 3;
		int credit = 0;
		while(tries > 0) {
			System.out.print("Credits: ");
			String line = myScanner.nextLine().trim();
		try {
			credit = Integer.parseInt(line);
		} catch (NumberFormatException e) {
			System.out.println("Wrong input");
			System.out.println("Try again");
			tries--;
			continue;
			}
		if(credit < 0) {
			System.out.println("Must be greater than or equal to 0");
			System.out.println("Try again");
			tries--;
			} else {
				break;
			}
	}
		if(tries > 0) {
		return credit;
		} else {
			return -1;
		}
	}
	
	private static String validateDepartment() {
		int tries = 3;
		String department = "";
		while(tries > 0) {
			System.out.print("Department: ");
			department = myScanner.nextLine().trim();
			if(!department.equalsIgnoreCase("Mathematics") && !department.equalsIgnoreCase("English") && !department.equalsIgnoreCase("Engineering"))  {
				System.out.println("Invalid department");
				System.out.println("Try again");
				tries--;
				} else {
					break;
					}
		}
		if(tries > 0) {
		return department;
		} else {
			return null;
		}
	}
	
	private static String validateRank() {
		int tries = 3;
		String rank = "";
		while(tries > 0) {
			System.out.print("Rank: ");
			rank = myScanner.nextLine().trim();
			if(!rank.equalsIgnoreCase("Professor") && !rank.equalsIgnoreCase("Adjunct")) {
				System.out.println("Invalid rank");
				System.out.println("Try again");
				tries--;
				} else {
					break;
				}
			}
		if(tries > 0) {
			return rank;
		} else {
			return null;
		}
			}
	
	private static String validateStatus() {
		int tries = 3;
		String status = "";
		while(tries > 0) {
			System.out.print("Status: ");
			status = myScanner.nextLine().trim();
			if(!status.equalsIgnoreCase("Part-Time") && !status.equalsIgnoreCase("Full-Time")) {
				System.out.println("Invalid status");
				System.out.println("Try again");
				tries--;
			} else {
				break;
				}
			}
		if(tries > 0) {
		return status;
		} else {
			return null;
			}
		}
	
	private static void studentSearch() {
		System.out.print("Enter the student ID: ");
		String studentId = myScanner.nextLine().trim();
		for(Person s: list) {
			if(s instanceof Student && s.getId().equalsIgnoreCase(studentId)) {
					((Student)s).print();
					return;
				}
			}
		System.out.println("Sorry no student with ID = " +studentId);
	}
	
	private static void facultySearch() {
		System.out.print("Enter the faculty ID: ");
		String facultyId = myScanner.nextLine().trim();
		for(Person s: list) {
			if(s instanceof Faculty && s.getId().equalsIgnoreCase(facultyId)) {
					((Faculty)s).print();
					return;
				}
			}
		System.out.println("Sorry no faculty with ID = " +facultyId);
	}
	
	private static void staffSearch() {
		System.out.print("Enter the staff ID: ");
		String staffId = myScanner.nextLine().trim();
		for(Person s: list) {
			if(s instanceof Staff && s.getId().equalsIgnoreCase(staffId)) {
					((Staff)s).print();
					return;
				}
			}
		System.out.println("Sorry no staff with ID = " +staffId);
	}
	
	private static void removePerson() {
		System.out.println("Enter the id of the person you would like to remove");
		String id = myScanner.nextLine().trim();
		for(Person p: list) {
			if(p.getId().equalsIgnoreCase(id)) {
				list.remove(p);
				System.out.println("Removed");
				return;
			}
		}
		System.out.println(id+ " was not found");
	}
	
	private static void exit() {
		int choice = 0;
		while(true) {
			System.out.println("Would you like to create the report? (Y/N):");
			String Report = myScanner.nextLine().trim();
			if(Report.equalsIgnoreCase("y")) {
				System.out.println("Would you like to sort your students by GPA or name? (Enter 1 for GPA. Enter 2 for name): ");
				try {
					choice = myScanner.nextInt();
					myScanner.nextLine();
					} catch (InputMismatchException e) {
						System.out.println("Must enter either 1 or 2");
					}
				ArrayList<Student> studList = new ArrayList<>(100);
				ArrayList<Faculty> facList = new ArrayList<>(100);
				ArrayList<Staff> staffList = new ArrayList<>(100);
				for(Person p: list) {
					if(p instanceof Student) {
						studList.add((Student) p);
						} else if(p instanceof Faculty) {
						facList.add((Faculty) p);
						} else {
						staffList.add((Staff) p);
						}
					}
				File myFile = new File("report.txt");
				if(choice == 1) {
					try {
						PrintWriter printWriter = new PrintWriter(myFile);	
						DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
						LocalDate localDate = LocalDate.now();
						String date = localDate.format(dateTimeFormatter);
						printWriter.println("Report created on " +date);
						printWriter.println("*******************************");
						printWriter.println();
						Faculty.facReport(facList, printWriter);
						Staff.staffReport(staffList, printWriter);
						Student.studGpaReport(studList, printWriter);
						printWriter.close();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					System.out.println("Report created and saved to report.txt");
					System.out.println("Goodbye!");
					break;
				} else if(choice == 2) {
					try {
						PrintWriter printWriter = new PrintWriter(myFile);	
						Faculty.facReport(facList, printWriter);
						Staff.staffReport(staffList, printWriter);
						Student.studNameReport(studList, printWriter);
						printWriter.close();
						} catch (Exception e) {
							System.out.println(e.getMessage());
							return;
						}
					System.out.println("Report created and saved to report.txt");
					System.out.println("Goodbye!");
					break;
				} else {
					System.out.println("Invalid input");
					}
				} else if(Report.equalsIgnoreCase("n")) {
					System.out.println("Goodbye!");
					break;
				} else {
					System.out.println("Invalid input");
					}
		}
	}
}

abstract class Person {
	private String fullName;
	private String id;
	public abstract void print();
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Person(String fullName, String id) {
		this.fullName = fullName;
		this.id = id;
	}

	public Person() {
		this.fullName = "";
		this.id = "";
	}
}

abstract class Employee extends Person{
	private String department;
	
	public Employee(String fullName, String id, String department) {
		super(fullName, id);
		this.department = department;
	}
	
	public Employee() {
		this.department = "";
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}

}

class Student extends Person {
	
	private double gpa;
	private int creditHours;
	private double total;

	public Student(String fullName, String id, double gpa, int creditHours) {
		super(fullName, id);
		this.gpa = gpa;
		this.creditHours = creditHours;
		if(gpa >= 3.85) {
			this.total = ((creditHours * 236.45) * 0.75) + 52;
		} else {
			this.total = (creditHours * 236.45) + 52;
		}
	}

	public Student() {
		super();
		this.gpa = 0;
		this.creditHours = 0;
		this.total = 0;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public void print() {
		System.out.println("Tuition invoice for " +getFullName()+ ":");
		System.out.println("-----------------------------------------------------------------");
		System.out.println(""+getFullName()+ "   " +getId());
		System.out.printf("GPA: %.2f", gpa);
		System.out.println("\nCredit Hours: "+creditHours+ " ($236.45/credit hour)");
		System.out.println("Fees: $52");
		System.out.printf("Total payment (after discount): $%.2f\n" ,total);
		System.out.println("-----------------------------------------------------------------");
	}
	
	public static int compareGpa(Student s1, Student s2) {
		if(s1.getGpa() == s2.getGpa()) {
			return 0;
		} else if(s1.getGpa() > s2.getGpa()) {
			return 1;
		}
			return -1;
	}
	
	public static int compareName(Student s1, Student s2) {
		return (s1.getFullName().compareTo(s2.getFullName()));
	}
	
	public static void studNameReport(ArrayList<Student> studList, PrintWriter out) {
		out.println("Students (Sorted by Name)");
		out.println("--------------------------------------------");
		out.println();
		
		Collections.sort(studList, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				
				return compareName(o1, o2);
			}
			
		});
		
		for(int i = 0; i < studList.size(); i++) {
			Student s = studList.get(i);
			out.println((i+1)+ ". "+s.getFullName());
			out.println("   ID: "+s.getId());
			out.printf("   GPA: %.2f", s.getGpa());
			out.println("\n   Credit Hours: "+s.getCreditHours());
			out.println();
		}
	}
	
	public static void studGpaReport(ArrayList<Student> studList, PrintWriter out){
		out.println("Students (Sorted by GPA)");
		out.println("--------------------------------------------");
		out.println();
		
		Collections.sort(studList, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				
				return compareGpa(o2, o1);
			}
			
		});
		
		for(int i = 0; i < studList.size(); i++) {
			Student s = studList.get(i);
			out.println((i+1)+ ". "+s.getFullName());
			out.println("   ID: "+s.getId());
			out.printf("   GPA: %.2f", s.getGpa());
			out.println("\n   Credit Hours: "+s.getCreditHours());
			out.println();
		}
		}
	}

class Faculty extends Employee{

	private String rank;
	
	public Faculty(String fullName, String id, String department, String rank) {
		super(fullName, id, department);
		this.rank = rank;
	}
	
	public Faculty() {
		super();
		this.rank = "";
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
	public void print() {
		System.out.println("Faculty information for " +getFullName()+ ":");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("" +getFullName()+ "   " +getId());
		System.out.println("Department: "+getDepartment());
		System.out.println("Rank: " +rank);
		System.out.println("-----------------------------------------------------------------");
	}
	
	public static void facReport(ArrayList<Faculty> facList, PrintWriter out) {
		out.println("Faculty Members");
		out.println("--------------------------------------------");
		
		Collections.sort(facList, new Comparator<Faculty>() {

			@Override
			public int compare(Faculty o1, Faculty o2) {
				// TODO Auto-generated method stub
				return o1.getDepartment().compareTo(o2.getDepartment());
			}
			
		});
		
		for(int i = 0; i < facList.size(); i++) {
			Faculty f = facList.get(i);
				out.println((i+1)+ ". "+f.getFullName());
				out.println("   ID: "+f.getId());
				out.println("   " +f.getRank()+ ", "+f.getDepartment());
				out.println();
			}
	}
	
	public static int compareName(Faculty f1, Faculty f2) {
		return (f1.getDepartment().compareTo(f2.getDepartment()));
	}
	
}

class Staff extends Employee{

	private String status;

	public Staff(String fullName, String id, String department, String status) {
		super(fullName, id, department);
		this.status = status;
	}
	
	public Staff() {
		super();
		this.status = "";
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void print() {
		System.out.println("Staff information for " +getFullName()+ ":");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("" +getFullName()+ "   " +getId());
		System.out.println("Department: "+getDepartment());
		System.out.println("Status: " +status);
		System.out.println("-----------------------------------------------------------------");
	}
	
	public static void staffReport(ArrayList<Staff> staffList, PrintWriter out) {
		out.println("Staff Members");
		out.println("--------------------------------------------");
		for(int i = 0; i < staffList.size(); i++) {
			Staff s = staffList.get(i);
				out.println((i+1)+ ". "+s.getFullName());
				out.println("   ID:"+s.getId());
				out.println("   " +s.getStatus()+ ", "+s.getDepartment());
				out.println();
		}
		
	}
	
}


