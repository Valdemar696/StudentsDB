package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import StudentsModel.Student;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.KEY_SURNAME + " TEXT, "
                + Util.KEY_NAME + " TEXT ,"
                + Util.KEY_FACULTY + " TEXT ,"
                + Util.KEY_AVERAGE_SCORE + " TEXT " + ");";

        sqLiteDatabase.execSQL(CREATE_STUDENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addStudent(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_SURNAME, student.getSurname());
        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_FACULTY, student.getFaculty());
        contentValues.put(Util.KEY_AVERAGE_SCORE, student.getAverageScore());

        sqLiteDatabase.insert(Util.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public Student getStudent (int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(Util.TABLE_NAME, new String[]
                {Util.KEY_ID, Util.KEY_SURNAME, Util.KEY_NAME, Util.KEY_FACULTY, Util.KEY_AVERAGE_SCORE}, Util.KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null );

        Student student = new Student();
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                student = new Student(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getDouble(4));
            } finally {
                cursor.close();
            }
        }
        return student;
    }

    public List<Student> getAllStudents() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        List<Student> studentList = new ArrayList<>();

        String selectAllCars = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(selectAllCars, null);
        if (cursor.moveToFirst()) {
            try {
                do {
                    Student student = new Student();
                    student.setId(Integer.parseInt(cursor.getString(0)));
                    student.setSurname(cursor.getString(1));
                    student.setName(cursor.getString(2));
                    student.setFaculty(cursor.getString(3));
                    student.setAverageScore(cursor.getDouble(4));

                    studentList.add(student);
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
            }

        }

        return studentList;
    }

    public int updateStudent (Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_SURNAME, student.getSurname());
        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_FACULTY, student.getFaculty());
        contentValues.put(Util.KEY_AVERAGE_SCORE, student.getAverageScore());

        return sqLiteDatabase.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String [] {String.valueOf(student.getId())});

    }

    public void deleteStudent (Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String [] {String.valueOf(student.getId())});

        sqLiteDatabase.close();
    }

    public int getStudentsCount () {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        return cursor.getCount();
    }

}
