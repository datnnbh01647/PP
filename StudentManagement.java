import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String firstName;
    String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

public class StudentManagement {
    private static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("STUDENT MANAGEMENT SYSTEM");
            System.out.println("1. Enter student list");
            System.out.println("2. Find students by last name");
            System.out.println("3. Find and edit students by full name");
            System.out.println("4. End");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    enterStudentList(scanner);
                    break;
                case 2:
                    findStudentsByLastName(scanner);
                    break;
                case 3:
                    findAndEditStudentByFullName(scanner);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);
    }

    private static void enterStudentList(Scanner scanner) {
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < n; i++) {
            System.out.println("Student " + (i + 1));
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            studentList.add(new Student(firstName, lastName));
        }

        System.out.println("Student list:");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println("Student " + (i + 1) + ": " + studentList.get(i));
        }
    }

    private static void findStudentsByLastName(Scanner scanner) {
        System.out.print("Enter last name to search: ");
        String lastName = scanner.nextLine();
        boolean found = false;

        for (Student student : studentList) {
            if (student.lastName.equalsIgnoreCase(lastName)) {
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found with the last name: " + lastName);
        }
    }

    private static void findAndEditStudentByFullName(Scanner scanner) {
        System.out.print("Enter full name to search (First Last): ");
        String fullName = scanner.nextLine();
        String[] nameParts = fullName.split(" ");
        if (nameParts.length != 2) {
            System.out.println("Please enter a valid full name.");
            return;
        }
        String firstName = nameParts[0];
        String lastName = nameParts[1];
        boolean found = false;
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.firstName.equalsIgnoreCase(firstName) && student.lastName.equalsIgnoreCase(lastName)) {
                found = true;
                System.out.println("Student found: " + student);
                System.out.println("Do you want to edit the student's name? (Y/N): ");
                String editChoice = scanner.nextLine();
                if (editChoice.equalsIgnoreCase("Y")) {
                    System.out.print("Enter new first name: ");
                    String newFirstName = scanner.nextLine();
                    System.out.print("Enter new last name: ");
                    String newLastName = scanner.nextLine();
                    student.setFirstName(newFirstName);
                    student.setLastName(newLastName);
                    System.out.println("Student information updated: " + student);
                }
                break;
            }
        }

        if (!found) {
            System.out.println("No student found with the full name: " + fullName);
        }
    }
}
