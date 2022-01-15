package com.example.studentsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DatabaseHandler;
import StudentsModel.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);

        Log.d("Students:", String.valueOf(databaseHandler.getStudentsCount()));

        databaseHandler.addStudent(new Student("Ivanov", "Ivan", "KSIS", 6.2));
        databaseHandler.addStudent(new Student("Greckova", "Natal'ya", "IEF", 7.9));
        databaseHandler.addStudent(new Student("Soroka", "Alexey", "UYT", 9.1));
        databaseHandler.addStudent(new Student("Ushkurov", "Andrey", "KSIS", 4.7));
        databaseHandler.addStudent(new Student("Kirpichov", "Georgiy", "KSIS", 8.9));

        List<Student> studentList = databaseHandler.getAllStudents();

        for (Student student : studentList) {
            Log.d("Students:", "ID: " + student.getId() +
                    "; surname: " + student.getSurname() +
                    "; name: " + student.getName() +
                    "; faculty: " + student.getFaculty() +
                    "; average score: " + student.getAverageScore() + ".");
        }

        Student deletingStudent = databaseHandler.getStudent(0);
        databaseHandler.deleteStudent(deletingStudent);

        Student student = databaseHandler.getStudent(5);
        Log.d("Students:", "ID: " + student.getId() +
                "; surname: " + student.getSurname() +
                "; name: " + student.getName() +
                "; faculty: " + student.getFaculty() +
                "; average score: " + student.getAverageScore() + ".");

        student.setName("Grzhegozh");
        student.setSurname("Brzhentschischtschikewitsch");

        int updatedStudentId = databaseHandler.updateStudent(student);

        Log.d("Students:", "ID: " + student.getId() +
                "; surname: " + student.getSurname() +
                "; name: " + student.getName() +
                "; faculty: " + student.getFaculty() +
                "; average score: " + student.getAverageScore() + ", updatedCarId " + updatedStudentId);
    }
}