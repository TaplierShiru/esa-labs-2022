package gribanov.lab2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import gribanov.lab2.models.Discipline;
import gribanov.lab2.models.Teacher;
import gribanov.lab2.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTeachersJson() {
        List<Teacher> teacherList = this.teacherService.getAll();
        return ResponseEntity.ok().body(teacherList);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ModelAndView getTeachersXml() throws JsonProcessingException {
        List<Teacher> teacherList = this.teacherService.getAll();
        ModelAndView modelAndView = new ModelAndView("teachers");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(teacherList)));
        modelAndView.addObject(source);
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<Object> createTeacher(@RequestBody Teacher teacher) {
        this.teacherService.save(teacher);
        return ResponseEntity.ok().body(teacher);
    }

    @PutMapping("/{teacherId}")
    public ResponseEntity<Object> updateTeacher(@PathVariable("teacherId") String teacherId, @RequestBody Teacher updatedTeacher) {
        Optional<Teacher> optionalTeacher = this.teacherService.getById(Integer.valueOf(teacherId));
        if (!optionalTeacher.isPresent()) {
            return new ResponseEntity<>(
                    String.format("Teacher with id %s not found", teacherId), HttpStatus.NOT_FOUND
            );
        }
        Teacher teacher = optionalTeacher.get();
        if (!updatedTeacher.getFio().isEmpty()) {
            teacher.setFio(updatedTeacher.getFio());
        }
        teacher.setAge(updatedTeacher.getAge());
        if (!updatedTeacher.getGender().isEmpty()) {
            teacher.setGender(updatedTeacher.getGender());
        }
        this.teacherService.save(teacher);
        return ResponseEntity.ok("New Teacher successfully updated");
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable("teacherId") String teacherId) {
        Optional<Teacher> optionalTeacher = this.teacherService.getById(Integer.valueOf(teacherId));
        if (!optionalTeacher.isPresent()) {
            return new ResponseEntity<>(
                    String.format("Teacher with id %s not found", teacherId), HttpStatus.NOT_FOUND
            );
        }
        this.teacherService.delete(optionalTeacher.get());
        return ResponseEntity.ok("New Teacher successfully deleted");
    }
}
