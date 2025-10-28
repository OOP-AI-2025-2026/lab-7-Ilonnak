package ua.opnu.lambda.fx;

public class StudentFX {
    private String name;
    private String lastName;
    private double avgMark;

    public StudentFX(String name, String lastName, double avgMark) {
        this.name = name;
        this.lastName = lastName;
        this.avgMark = avgMark;
    }

    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public double getAvgMark() { return avgMark; }

    @Override
    public String toString() {
        return lastName + " " + name + ", середній бал: " + avgMark;
    }
}
