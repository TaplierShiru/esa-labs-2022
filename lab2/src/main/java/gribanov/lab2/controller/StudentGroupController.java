package gribanov.lab2.controller;

import gribanov.lab2.models.StudentGroup;
import gribanov.lab2.services.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student-groups")
public class StudentGroupController {
    @Autowired
    private StudentGroupService studentGroupService;

    @GetMapping
    public ResponseEntity<Object> getStudentGroupGroups() {
        List<StudentGroup> studentList = this.studentGroupService.getAll();
        return ResponseEntity.ok().body(studentList);
    }

    @PostMapping
    public ResponseEntity<Object> createStudentGroup(@RequestBody StudentGroup studentGroup) {
        this.studentGroupService.save(studentGroup);
        return ResponseEntity.ok("New student group successfully added");
    }

    @PutMapping("/{studentGroupId}")
    public ResponseEntity<Object> updateStudentGroup(@PathVariable("studentGroupId") String studentGroupId, @RequestBody StudentGroup updatedStudentGroup) {
        Optional<StudentGroup> optionalStudentGroup = this.studentGroupService.getById(Integer.valueOf(studentGroupId));
        if (!optionalStudentGroup.isPresent()) {
            return new ResponseEntity<>(
                    String.format("StudentGroup with id %s not found", studentGroupId), HttpStatus.NOT_FOUND
            );
        }
        StudentGroup studentGroup = optionalStudentGroup.get();
        if (!updatedStudentGroup.getCode().isEmpty()) {
            studentGroup.setCode(updatedStudentGroup.getCode());
        }
        if (!updatedStudentGroup.getCode().isEmpty()) {
            studentGroup.setCourse(updatedStudentGroup.getCourse());
        }
        this.studentGroupService.save(studentGroup);
        return ResponseEntity.ok("New StudentGroup successfully updated");
    }

    @DeleteMapping("/{studentGroupId}")
    public ResponseEntity<Object> deleteStudentGroup(@PathVariable("studentGroupId") String studentGroupId) {
        Optional<StudentGroup> optionalStudentGroup = this.studentGroupService.getById(Integer.valueOf(studentGroupId));
        if (!optionalStudentGroup.isPresent()) {
            return new ResponseEntity<>(
                    String.format("StudentGroup with id %s not found", studentGroupId), HttpStatus.NOT_FOUND
            );
        }
        this.studentGroupService.delete(optionalStudentGroup.get());
        return ResponseEntity.ok("New StudentGroup successfully deleted");
    }
}
