/* Brandon Robinson
 * CS111B Project 2
 * 30 June 2016
 */

/** Description: Course object. Does not use an Arraylist. 
 * @author Brandon Robinson
 */
public class Course {
	private String courseName; // Input
	private int maxNumStudents; // Input
	private Student[] roster;  // Array of Student objects
	private int studentCount; // Tracked number of students
	private int index; // Tracked for adding and removing students
	
	public Course(String name, int num) {
		courseName = name;
		maxNumStudents = num;
		roster = new Student[maxNumStudents];
		studentCount = 0;
		index = 0;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getMaxNumStudents() {
		return maxNumStudents;
	}

	public void setMaxNumStudents(int maxNumStudents) {
		this.maxNumStudents = maxNumStudents;
	}
	
	/** Description: Adds student if there is room in the class.
	 * @param s Student to be added.
	 * @return True if there is room and the student was added. False otherwise.
	 */
	public boolean addStudent(Student s) {
		if (studentCount < maxNumStudents) {
			studentCount++;
			roster[index] = s;
			index++;
			return true;
		} else {
			System.out.println(s.getStudentName() + " (" + s.getStudentID() + 
					") could not be added." );
			return false;
		}
	}
	
	/** Description: Drops student if they are in the class.
	 * @param s Student to be dropped.
	 * @return True if student is dropped. False otherwise.
	 */
	public boolean dropStudent(Student s) {
		if (studentExists(s)) {
			studentCount--;
			return true;
		}
		return false;
	}
	
	/** Description: Helper method; iterates through list to see if student is in the class. 
	 * 	If the student is found in the array, they will be removed from array.
	 * @param s Student
	 * @return True if the student exists and is successfully deleted from the array. False otherwise
	 */
	private boolean studentExists(Student s) {
		for (int i=0; i<roster.length; i++) {
			if (roster[i] == s) { // Student found!
				removeStudent(s, i);
				return true;
			}
		}
		return false;
	}
	
	/** Description: Helper method; removes students from the array.
	 * @param s Student to be dropped.
	 * @param loc Index of the deleted element/student.
	 */
	private void removeStudent(Student s, int loc) {
		// To shift any elements down <--
		for (int i = loc; i<roster.length - 1; i++) {
			roster[i] = roster[i+1];
		}
		// Attempting to remove the duplicate at the end
		roster[roster.length-1] = null;
		index--; // Decrement index for next removal
	}
	
	/** Description: Prints the roster of students for this particular course.
	 */
	public void printRoster() {
		if (studentCount > 0) {
			System.out.println("Enrollment: " + studentCount);
			for (int i=0; i<studentCount; i++) {
				System.out.println(roster[i].getStudentName() + " (" + roster[i].getStudentID() + ")");
			}
		} else { // No students enrolled
			System.out.println("--No student enrolled--");
		}
	}

	@Override
	public String toString() {
		return courseName + " (" + studentCount + " student capacity)";
	}

}
