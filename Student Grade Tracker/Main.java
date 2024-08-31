import java.util.ArrayList;
import java.util.Scanner;

class StudentGradeTracker {
    // Attribute to store grades
    private ArrayList<Double> grades;

    // Constructor to initialize the grades list
    public StudentGradeTracker() {
        grades = new ArrayList<>();
    }

    // Method to add a grade to the list
    public void addGrade(double grade) {
        grades.add(grade);
    }

    // Method to calculate and return the average grade
    public double getAverage() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    // Method to get the highest grade
    public double getHighest() {
        double highest = Double.MIN_VALUE;
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    // Method to get the lowest grade
    public double getLowest() {
        double lowest = Double.MAX_VALUE;
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    // Method to display all grades
    public void displayAllGrades() {
        System.out.println("All Grades:");
        for (double grade : grades) {
            System.out.println(grade);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StudentGradeTracker tracker = new StudentGradeTracker();
        Scanner scanner = new Scanner(System.in);

        // Menu loop for teacher input
        while (true) {
            System.out.println("1. Add Grade");
            System.out.println("2. Get Average Grade");
            System.out.println("3. Get Highest Grade");
            System.out.println("4. Get Lowest Grade");
            System.out.println("5. Display All Grades");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    System.out.print("Enter grade:");
                    double grade = scanner.nextDouble();
                    tracker.addGrade(grade);
                    System.out.println("Grade added successfully!");
                    break;
                case 2:
                    System.out.print("Average Grade: " + tracker.getAverage());
                    break;
                case 3:
                    System.out.print("Highest Grade: " + tracker.getHighest());
                    break;
                case 4:
                    System.out.print("Lowest Grade: " + tracker.getLowest());
                    break;
                case 5:
                    tracker.displayAllGrades();
                    break;
                case 6:
                    System.out.print("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
             System.out.println();
            System.out.println("------------------------------------------------");
        }
    }
}
