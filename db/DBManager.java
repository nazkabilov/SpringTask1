package kz.bitlab.techordaB2.springproject.db;

import kz.bitlab.techordaB2.springproject.model.Student;

import java.util.ArrayList;

public class DBManager {
    public static ArrayList<Student> studentList = new ArrayList<>();
    private static Long id = 3L;

    static {
        studentList.add(new Student(1L, "Ainur", "Akhmadiyeva", 80, "B"));
        studentList.add(new Student(2L, "Nazar", "Kabylov", 90, "A"));
    }

    public static ArrayList<Student> getAllStudents() {
        return studentList;
    }

    public static void addStudent(Student student) {
        student.setId(id);
        studentList.add(student);
        id++;
        if (student.getExam() > 60 && student.getExam() < 75) {
            student.setMark("C");
        } else if (student.getExam() > 90) {
            student.setMark("A");
        } else if (student.getExam() > 75 && student.getExam() < 90) {
            student.setMark("B");
        } else if (student.getExam() > 50 && student.getExam() < 60) {
            student.setMark("D");
        } else {
            student.setMark("F");

        }
    }

    public static Student getStudentById(Long id) {
        for (Student stud : studentList) {
            if (stud.getId() == id) {
                return stud;
            }
        }
        return null;
    }

    public static void updateStudent(Student student) {
        for (int i = 0; i < studentList.size(); i++) {
            student = studentList.get(i);
            if (student.getId().equals(student.getId())) {
                studentList.set(i, student);
                return;
            }
        }
    }

    public static void deleteStudent(Long id) {
        for (Student stud : studentList) {
            if (stud.getId() == id) {
                studentList.remove(stud);
                break;
            }
        }
    }
}
