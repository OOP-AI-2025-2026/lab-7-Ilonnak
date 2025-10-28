package ua.opnu.lambda.fx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Comparator;

public class SortingListFX extends Application {

    private ObservableList<StudentFX> students;
    private boolean nameAsc = true;
    private boolean surnameAsc = true;
    private boolean gradeAsc = true;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Сортування студентів (Лямбда-версія)");

        students = fillList();
        ListView<StudentFX> listView = new ListView<>(students);
        listView.setPrefSize(440, 260);

        Button btnName    = new Button("Сортувати за ім'ям");
        Button btnSurname = new Button("Сортувати за прізвищем");
        Button btnGrade   = new Button("Сортувати за середнім балом");

        HBox.setHgrow(btnName, Priority.ALWAYS);
        HBox.setHgrow(btnSurname, Priority.ALWAYS);
        HBox.setHgrow(btnGrade, Priority.ALWAYS);
        btnName.setMaxWidth(Double.MAX_VALUE);
        btnSurname.setMaxWidth(Double.MAX_VALUE);
        btnGrade.setMaxWidth(Double.MAX_VALUE);

        // --- Лямбда-версії сортування ---
        btnName.setOnAction(e -> {
            students.sort(nameAsc ? Comparator.comparing(StudentFX::getName)
                    : Comparator.comparing(StudentFX::getName).reversed());
            nameAsc = !nameAsc;
        });

        btnSurname.setOnAction(e -> {
            students.sort(surnameAsc ? Comparator.comparing(StudentFX::getLastName)
                    : Comparator.comparing(StudentFX::getLastName).reversed());
            surnameAsc = !surnameAsc;
        });

        btnGrade.setOnAction(e -> {
            students.sort(gradeAsc ? Comparator.comparingDouble(StudentFX::getAvgMark)
                    : Comparator.comparingDouble(StudentFX::getAvgMark).reversed());
            gradeAsc = !gradeAsc;
        });

        HBox buttons = new HBox(5, btnName, btnSurname, btnGrade);
        buttons.setAlignment(Pos.CENTER);

        VBox root = new VBox(5, listView, buttons);
        root.setPadding(new Insets(5));
        root.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private ObservableList<StudentFX> fillList() {
        return FXCollections.observableArrayList(
                new StudentFX("Борис", "Іванов", 75),
                new StudentFX("Петро", "Петренко", 92),
                new StudentFX("Сергій", "Сергієнко", 61),
                new StudentFX("Григорій", "Сковорода", 88)
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
