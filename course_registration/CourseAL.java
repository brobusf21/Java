/* Brandon Robinson
 * CS111B Project 2
 * 30 June 2016
 */

import java.util.ArrayList;

/** Description: This class utilizes an ArrayList to add and remove students.
 * @author Brandon Robinson
 */
public class CourseAL {
	private String courseName; // Input
	private int maxNumStudents; // Input
	private ArrayList<Student> roster;
	private int studentCount; // Tracked for number of students
	
	public CourseAL(String name, int num) {
		courseName = name;
		maxNumStudents = num;
		roster = new ArrayList<Student>();
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
			roster.add(s);
			studentCount++;
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
		if (roster.contains(s)) {
			roster.remove(s);
			studentCount--;
			return true;
		}
		return false;
	}
	
	/** Description: Prints the roster of students for this particular course.
	 */
	public void printRoster() {
		if (studentCount > 0) {
			System.out.println("Enrollment: " + studentCount);
			for (Student s: roster) {
				System.out.println(s.getStudentName() + " (" + s.getStudentID() + ")");
			}
		} else { // No students enrolled
			System.out.println("--No students enrolled");
		}
	}

	@Override
	public String toString() {
		return courseName + " (" + studentCount + " student capacity)";
	}

}
