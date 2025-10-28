package ua.opnu;

import java.util.Arrays;

public class Student {
    private String firstName;   // ім'я
    private String lastName;    // прізвище
    private String group;
    private int[] marks;        // масив оцінок (0..100)

    public Student(String firstName, String lastName, String group, int[] marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.marks = marks;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGroup() { return group; }
    public int[] getMarks() { return marks; }

    public boolean hasDebt() {
        // заборгованість = є хоча б одна оцінка < 60
        for (int m : marks) if (m < 60) return true;
        return false;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " (" + group + ") " + Arrays.toString(marks);
    }
}
