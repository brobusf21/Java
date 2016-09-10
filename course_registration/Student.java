/* Brandon Robinson
 * CS111B Project 2
 * 30 June 2016
 */

/** Description: Used to store information about a student including their name,
 *               student ID number, and whether or not they have paid tuition. 
 * @author blrobinson
 *
 */
public class Student {
	private String studentName; // Input
	private String studentID; // Input
	private boolean tuitionPaid; // Input
	
	public Student(String name, String id, boolean paid) {
		studentName = name;
		studentID = id;
		tuitionPaid = paid;
	}
	
	/* Getters and Setters */
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public boolean isTuitionPaid() {
		return tuitionPaid;
	}

	public void setTuitionPaid(boolean tuitionPaid) {
		this.tuitionPaid = tuitionPaid;
	}

	@Override
	public String toString() {
		return "Student Name: " + studentName + "\n" +
				"Student ID: " + studentID;
	}
}
