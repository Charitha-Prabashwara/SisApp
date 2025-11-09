package com.example.sisapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
public class DBHelper extends SQLiteOpenHelper {

    // Database info
    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_STUDENT = "student";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NIC = "nic";
    private static final String COLUMN_FIRSTNAME = "firstName";
    private static final String COLUMN_LASTNAME = "lastName";
    private static final String COLUMN_FULLNAME = "fullName";
    private static final String COLUMN_INITIALS = "nameWithInitials";
    private static final String COLUMN_ADDRESS1 = "addressLineOne";
    private static final String COLUMN_ADDRESS2 = "addressLineTwo";
    private static final String COLUMN_ZIP = "zipCode";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_STUDENT + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NIC + " TEXT, "
                + COLUMN_FIRSTNAME + " TEXT NOT NULL, "
                + COLUMN_LASTNAME + " TEXT NOT NULL, "
                + COLUMN_FULLNAME + " TEXT NOT NULL, "
                + COLUMN_INITIALS + " TEXT NOT NULL, "
                + COLUMN_ADDRESS1 + " TEXT NOT NULL, "
                + COLUMN_ADDRESS2 + " TEXT, "
                + COLUMN_ZIP + " INTEGER NOT NULL"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    // Insert a student
    public long insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NIC, student.getNic());
        values.put(COLUMN_FIRSTNAME, student.getFirstName());
        values.put(COLUMN_LASTNAME, student.getLastName());
        values.put(COLUMN_FULLNAME, student.getFullName());
        values.put(COLUMN_INITIALS, student.getNameWithInitials());
        values.put(COLUMN_ADDRESS1, student.getAddressLineOne());
        values.put(COLUMN_ADDRESS2, student.getAddressLineTwo());
        values.put(COLUMN_ZIP, student.getZipCode());

        long id = db.insert(TABLE_STUDENT, null, values);
        db.close();
        return id;
    }

    // Get a student by ID
    public Student getStudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT,
                null,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Student student = new Student(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIC)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRSTNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LASTNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULLNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INITIALS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS1)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS2)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ZIP))
            );
            cursor.close();
            db.close();
            return student;
        }

        if (cursor != null)
            cursor.close();
        db.close();
        return null;
    }

    // Update a student by ID
    public int updateStudentById(Student student, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NIC, student.getNic());
        values.put(COLUMN_FIRSTNAME, student.getFirstName());
        values.put(COLUMN_LASTNAME, student.getLastName());
        values.put(COLUMN_FULLNAME, student.getFullName());
        values.put(COLUMN_INITIALS, student.getNameWithInitials());
        values.put(COLUMN_ADDRESS1, student.getAddressLineOne());
        values.put(COLUMN_ADDRESS2, student.getAddressLineTwo());
        values.put(COLUMN_ZIP, student.getZipCode());

        int rowsAffected = db.update(TABLE_STUDENT, values,
                COLUMN_ID + "=?", new String[]{String.valueOf(id)});

        db.close();
        return rowsAffected;
    }

    // Delete a student by ID
    public int deleteStudentById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_STUDENT, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    // Get all students
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIC)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRSTNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LASTNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FULLNAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INITIALS)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS1)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS2)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ZIP))
                );
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;
    }
}
