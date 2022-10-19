package gribanov.lab2.controller;

import gribanov.lab2.models.Student;
import gribanov.lab2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Object> getStudents() {
        List<Student> studentList = this.studentService.getAll();
        return ResponseEntity.ok().body(studentList);
    }

    @PostMapping
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        this.studentService.save(student);
        return ResponseEntity.ok("New student successfully added");
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Object> updateStudent(@PathVariable("studentId") String studentId, @RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = this.studentService.getById(Integer.valueOf(studentId));
        if (!optionalStudent.isPresent()) {
            return new ResponseEntity<>(
                    String.format("Student with id %s not found", studentId), HttpStatus.NOT_FOUND
            );
        }
        Student student = optionalStudent.get();
        if (!updatedStudent.getFio().isEmpty()) {
            student.setFio(updatedStudent.getFio());
        }
        if (!updatedStudent.getNumberRecordBook().isEmpty()) {
            student.setNumberRecordBook(updatedStudent.getNumberRecordBook());
        }
        this.studentService.save(student);
        return ResponseEntity.ok("New student successfully updated");
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Object> updateStudent(@PathVariable("studentId") String studentId) {
        Optional<Student> optionalStudent = this.studentService.getById(Integer.valueOf(studentId));
        if (!optionalStudent.isPresent()) {
            return new ResponseEntity<>(
                    String.format("Student with id %s not found", studentId), HttpStatus.NOT_FOUND
            );
        }
        this.studentService.delete(optionalStudent.get());
        return ResponseEntity.ok("New student successfully deleted");
    }
}
