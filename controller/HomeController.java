package kz.bitlab.techordaB2.springproject.controller;

import ch.qos.logback.core.net.server.ServerRunner;
import org.springframework.ui.Model;
import kz.bitlab.techordaB2.springproject.db.DBManager;
import kz.bitlab.techordaB2.springproject.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static kz.bitlab.techordaB2.springproject.db.DBManager.studentList;

@Controller
public class HomeController {
    @GetMapping(value = "/index")
    public String indexPage(Model model) {
        ArrayList<Student> allStudents = DBManager.getAllStudents();
        model.addAttribute("allStudentsAttribute", allStudents);
        return "index";
    }

    @GetMapping(value = "/addStudentPage")
    public String addStudentPage() {
        return "addStudent";
    }

    @PostMapping(value = "/addStudent")
    public String addStudent(@RequestParam(name = "name") String studentName,
                             @RequestParam(name = "surname") String studentSurname,
                             @RequestParam(name = "exam") int exam) {
        Student student = new Student();
        student.setName(studentName);
        student.setSurname(studentSurname);
        student.setExam(exam);
        DBManager.addStudent(student);
        return "redirect:/index";
    }

    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name = "id") Long id, Model model) {
        Student student = DBManager.getStudentById(id);
        model.addAttribute("studentAtt", student);
        return "details";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteStudent(@PathVariable(name = "id") Long id) {
        DBManager.deleteStudent(id);
        return "redirect:/index";
    }


    @GetMapping(value = "/update/{id}")
    public String updateStudentPage(@PathVariable(name = "id") Long id, Model model) {
        Student student = DBManager.getStudentById(id);
        model.addAttribute("studentAtt", student);
        return "updateStudent";
    }

    @PostMapping(value = "/update/{id}")
    public String updateStudent(@PathVariable(name = "id") Long id,
                              @RequestParam(name = "name") String studentName,
                              @RequestParam(name = "surname") String studentSurname,
                              @RequestParam(name = "exam") int exam) {
        Student student = DBManager.getStudentById(id);
        if (student != null) {
            student.setName(studentName);
            student.setSurname(studentSurname);
            student.setExam(exam);
            DBManager.updateStudent(student);
        }
        return "redirect:/index";
    }
}




