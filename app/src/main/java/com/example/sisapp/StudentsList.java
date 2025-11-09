package com.example.sisapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


public class StudentsList {
    private static final List<Student> students = new ArrayList<>();
    private DBHelper dbHelper;
    public StudentsList(Boolean fresh) {

        boolean active = (fresh != null) ? fresh : false; // default to false

        if (active) {
            students.clear();
            students.add(new Student(1 ,"200014368940", "Dinuka", "Rajapaksha", "Kasippu Appuhamilage Dinuka Rajapaksha", "K.A Dinuka Rajapaksha", "No 145/5 Puwakgas Gommana", "Ra Handiya", 10584));
            students.add(new Student(2 ,"200023458910", "Charitha", "Prabashwara", "Ranasinghe Mudiyanselage Charitha Prabashwara", "R.M Charitha Prabashwara", "No 22 Temple Road", "Labuduwa", 80000));
            students.add(new Student(3 ,"200065478320", "Nuwan", "Perera", "Samarasinghe Mudiyanselage Nuwan Perera", "S.M Nuwan Perera", "No 58 Palm Garden", "Kottawa", 10230));
            students.add(new Student(4 ,"200078945610", "Tharindu", "Bandara", "Ekanayake Mudiyanselage Tharindu Bandara", "E.M Tharindu Bandara", "No 45/2 Hill View", "Matale", 21000));
            students.add(new Student(5 ,"200098743210", "Sajith", "Fernando", "Fernando Arachchilage Sajith Fernando", "F.A Sajith Fernando", "No 10 Station Road", "Negombo", 11500));
            students.add(new Student(6 ,"200045612390", "Ishara", "Jayawardena", "Jayawardena Mudiyanselage Ishara Jayawardena", "J.M Ishara Jayawardena", "No 7 Green Park", "Nugegoda", 10250));
            students.add(new Student(7 ,"200012345670", "Kasun", "Lakmal", "Mudiyanselage Kasun Lakmal", "M. Kasun Lakmal", "No 9/1 Riverside", "Galle", 80010));
            students.add(new Student(8 ,"200054321890", "Rashmi", "Weerasinghe", "Appuhamilage Rashmi Weerasinghe", "A. Rashmi Weerasinghe", "No 14 Flower Road", "Colombo 7", 700));
            students.add(new Student(9 ,"200076543210", "Sanduni", "Silva", "De Silva Mudiyanselage Sanduni Silva", "D.M Sanduni Silva", "No 65 Lake View", "Kurunegala", 60000));
            students.add(new Student(10,"200087654320", "Malith", "Gunasekara", "Gunasekara Mudiyanselage Malith Gunasekara", "G.M Malith Gunasekara", "No 89 Beach Road", "Matara", 81000));
        }
    }

    public StudentsList(Context context){
        this.dbHelper = new DBHelper(context);
    }

    public List<Student> getStudents() {
        return this.dbHelper.getAllStudents();
    }

    public  Student getStudentById(int id) {
        return this.dbHelper.getStudentById(id);

    }

    public long addStudent(Student newStudent) {
        return this.dbHelper.insertStudent(newStudent);
    }

    public static boolean createStudent(int id, String nic, String firstName, String lastName, String fullName,
                                        String nameWithInitials, String addressLineOne, String addressLineTwo, int zipCode) {
        Student newStudent = new Student(id,nic, firstName, lastName, fullName, nameWithInitials, addressLineOne, addressLineTwo, zipCode);
        return students.add(newStudent);
    }




    public int deleteById(int id) {
        return this.dbHelper.deleteStudentById(id);
    }


    public void updateById(int id, Student updatedStudent) {
        this.dbHelper.updateStudentById(updatedStudent, id);

    }




}
