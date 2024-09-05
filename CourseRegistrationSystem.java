import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    private final String code;
    private final String title;
    private final int capacity;
    public final String schedule;
    private int enrolledStudents;

    public Course(String code, String title, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }

    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        } else {
            return false;
        }
    }

    public void removeStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
        }
    }

    public String toString() {
        return code + " - " + title + " (" + schedule + ") | Capacity: " + capacity + " | Available Slots: " + getAvailableSlots();
    }
}

// Student class to represent individual students
class Student {
    private final String studentID;
    private final ArrayList<Course> registeredCourses;

    public Student(String studentID) {
        this.studentID = studentID;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public void registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for the course: " + course.getTitle());
        } else {
            System.out.println("Registration failed: Course capacity is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent();
            System.out.println("Successfully dropped the course: " + course.getTitle());
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void listRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println("Registered Courses:");
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

// CourseRegistrationSystem class to manage the system
public class CourseRegistrationSystem {
    private final HashMap<String, Course> courseDatabase;
    private final HashMap<String, Student> studentDatabase;

    public CourseRegistrationSystem() {
        courseDatabase = new HashMap<>();
        studentDatabase = new HashMap<>();
    }

    // Add a new course to the database
    public void addCourse(Course course) {
        courseDatabase.put(course.getCode(), course);
    }

    // Add a new student to the database
    public void addStudent(Student student) {
        studentDatabase.put(student.getStudentID(), student);
    }

    // Display available courses
    public void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println(course);
        }
    }

    // Find a course by code
    public Course findCourse(String code) {
        return courseDatabase.get(code);
    }

    // Find a student by ID
    public Student findStudent(String studentID) {
        return studentDatabase.get(studentID);
    }

    // Display a student's registered courses
    public void displayStudentCourses(String studentID) {
        Student student = findStudent(studentID);
        if (student != null) {
            student.listRegisteredCourses();
        } else {
            System.out.println("Student not found.");
        }
    }

    // Register a student for a course
    public void registerStudentForCourse(String studentID, String courseCode) {
        Student student = findStudent(studentID);
        Course course = findCourse(courseCode);

        if (student != null && course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    // Drop a course for a student
    public void dropStudentCourse(String studentID, String courseCode) {
        Student student = findStudent(studentID);
        Course course = findCourse(courseCode);

        if (student != null && course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Add some sample courses to the course database
        system.addCourse(new Course("CS101", "Introduction to Computer Science", 30, "Mon-Wed 9:00-10:30"));
        system.addCourse(new Course("MA101", "Calculus I", 40, "Tue-Thu 11:00-12:30"));
        system.addCourse(new Course("PHY101", "Physics I", 35, "Mon-Wed 2:00-3:30"));

        // Add some sample students to the student database
        system.addStudent(new Student("S1001"));
        system.addStudent(new Student("S1002"));

        boolean exit = false;

        while (!exit) {
            System.out.println("\nCourse Registration System");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    system.displayAvailableCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.next();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.next();
                    system.registerStudentForCourse(studentID, courseCode);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.next();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.next();
                    system.dropStudentCourse(studentID, courseCode);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.next();
                    system.displayStudentCourses(studentID);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }

        scanner.close();
    }
}
