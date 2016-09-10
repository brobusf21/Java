/* Brandon Robinson
 * CS111B Project 2
 * 30 June 2016
 */

/** Description: Driver class. Utilizes both the regular Course objects and 
 *               the CoureAL object (Extra Credit). 6 students are initialized 
 *               and used to create a roster for each Course object.
 * @author Brandon Robinson
 */
public class Driver {

	public static void main(String[] args) {
		
		// Creating 6 students
		Student s1 = new Student("Tony Mitchel", "20263245", false);
		Student s2 = new Student("Sarah Parker", "24567843", true);
		Student s3 = new Student("Jessica Michelle", "20243575", true);
		Student s4 = new Student("Mike Lee", "1235245", true);
		Student s5 = new Student("James Blake", "56734523", true);
		Student s6 = new Student("Isabel Schultz", "2023488", true);
		
		// Creates a course that holds 5 students
		Course c1 = new Course("Intro to Architecture", 5);
		
		// Printing course
		System.out.println(c1.toString());
		
		// Printing roster
		c1.printRoster();
		c1.addStudent(s1);
		c1.addStudent(s2);
		c1.addStudent(s3);
		c1.addStudent(s4);
		c1.addStudent(s5);
		
		// Attempts to add 6th student --> Should print error
		c1.addStudent(s6);
		
		// Printing roster
		c1.printRoster();
		
		// Drop 3rd student from course
		c1.dropStudent(s3);
		
		// Printing roster
		c1.printRoster();
		
		// Tries to add the 6th student again
		c1.addStudent(s6);
		
		// Printing roster 
		c1.printRoster();
		
		System.out.println("\n");
		
/* -------------------EXTRA CREDIT--------------------------- */		
		// Creates a course that holds 5 students using an ArrayList 
		CourseAL c2 = new CourseAL("Intro to Calculus", 5);
		
		// Printing course
		System.out.println(c2.toString());
		
		// Printing roster
		c2.printRoster();
		c2.addStudent(s1);
		c2.addStudent(s2);
		c2.addStudent(s3);
		c2.addStudent(s4);
		c2.addStudent(s5);
		
		// Attempts to add 6th student --> Should print error
		c2.addStudent(s6);
		
		// Printing roster
		c2.printRoster();
		
		// Drop 3rd student from course
		c2.dropStudent(s3);
		
		// Printing roster
		c2.printRoster();
		
		// Tries to add the 6th student again
		c2.addStudent(s6);
		
		// Printing roster 
		c2.printRoster();
	}

}
