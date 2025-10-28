package ua.opnu;

import java.util.*;
import java.util.function.*;

public class Main {

    static int[] filter(int[] input, Predicate<Integer> p) {
        int[] tmp = new int[input.length];
        int k = 0;
        for (int v : input) {
            if (p.test(v)) tmp[k++] = v;
        }
        return Arrays.copyOf(tmp, k);
    }

    static int[] filter(int[] input, Predicate<Integer> p1, Predicate<Integer> p2) {
        return filter(input, x -> p1.test(x) && p2.test(x));
    }

    static void doIf(int[] input, Predicate<Integer> cond, Consumer<Integer> action) {
        for (int v : input) if (cond.test(v)) action.accept(v);
    }

    static int[] mapInt(int[] input, Function<Integer, Integer> f) {
        int[] out = new int[input.length];
        for (int i = 0; i < input.length; i++) out[i] = f.apply(input[i]);
        return out;
    }

    static String[] stringify(int[] input, Function<Integer, String> f) {
        String[] out = new String[input.length];
        for (int i = 0; i < input.length; i++) out[i] = f.apply(input[i]);
        return out;
    }

    static Student[] filterStudents(Student[] input, Predicate<Student> p) {
        Student[] tmp = new Student[input.length];
        int k = 0;
        for (Student s : input) if (p.test(s)) tmp[k++] = s;
        return Arrays.copyOf(tmp, k);
    }

    static void forEach(Student[] students, Consumer<Student> c) {
        for (Student s : students) c.accept(s);
    }

    static boolean isPrimeInt(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        int r = (int) Math.sqrt(n);
        for (int d = 3; d <= r; d += 2) {
            if (n % d == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Демодані
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        Student[] students = {
                new Student("Ірина", "Ткач", "AI-244", new int[]{95, 88, 91, 100}),
                new Student("Максим", "Іванов",     "AI-244", new int[]{70, 59, 82, 64}), // має борг (59)
                new Student("Олена", "Сидоренко",   "AI-244", new int[]{60, 60, 60, 61}),
                new Student("Андрій", "Петренко",   "AI-244", new int[]{58, 77, 90, 100}) // має борг (58)
        };

        // -------------------- Завдання 1 --------------------
        Predicate<Integer> isPrime = Main::isPrimeInt;
        System.out.println("Z1 (прості): " + Arrays.toString(filter(nums, isPrime)));

        // -------------------- Завдання 2 --------------------
        Predicate<Student> hasDebt = Student::hasDebt;
        Student[] debtors = filterStudents(students, hasDebt);
        System.out.println("Z2 (із заборгованістю): " + Arrays.toString(debtors));

        // -------------------- Завдання 3 --------------------
        Predicate<Integer> greater5 = n -> n > 5;
        System.out.println("Z3 (прості & >5): " + Arrays.toString(filter(nums, isPrime, greater5)));

        // -------------------- Завдання 4 --------------------
        Consumer<Student> printFull = s -> System.out.println(s.getLastName() + " " + s.getFirstName());
        System.out.println("Z4 (ПРІЗВИЩЕ + ІМ'Я):");
        forEach(students, printFull);

        // -------------------- Завдання 5 --------------------
        System.out.print("Z5 (дія тільки для парних): ");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Consumer<Integer> printVal = v -> System.out.print(v + " ");
        doIf(nums, isEven, printVal);
        System.out.println(); // новий рядок

        // -------------------- Завдання 6 --------------------
        Function<Integer, Integer> pow2 = n -> (int) Math.round(Math.pow(2, n));
        int[] mapped = mapInt(Arrays.copyOfRange(nums, 0, 10), pow2); // беремо 10 чисел
        System.out.println("Z6 (2^n для перших 10): " + Arrays.toString(mapped));

        // -------------------- Завдання 7 --------------------
        Function<Integer, String> numToWord = n -> {
            String[] words = {"нуль","один","два","три","чотири","п'ять","шість","сім","вісім","дев'ять"};
            if (n < 0 || n > 9) return "невідомо";
            return words[n];
        };
        int[] ten = {0,1,2,3,4,5,6,7,8,9};
        String[] str = stringify(ten, numToWord);
        System.out.println("Z7 (словами 0..9): " + Arrays.toString(str));
    }
}

