package gribanov.lab2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import gribanov.lab2.models.Discipline;
import gribanov.lab2.services.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDiscipline() throws JsonProcessingException {
        List<Discipline> disciplineDataList = this.disciplineService.getAll();
        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(disciplineDataList));
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ModelAndView getDisciplineXml() throws JsonProcessingException {
        List<Discipline> disciplineDataList = this.disciplineService.getAll();
        ModelAndView modelAndView = new ModelAndView("disciplines");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(disciplineDataList)));
        modelAndView.addObject(source);
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<Object> createDiscipline(@RequestBody Discipline discipline) {
        this.disciplineService.save(discipline);
        return ResponseEntity.ok("New discipline successfully added");
    }


    @PutMapping("/{disciplineId}")
    public ResponseEntity<Object> updateDiscipline(@PathVariable("disciplineId") String disciplineId, @RequestBody Discipline updatedDiscipline) {
        Optional<Discipline> optionalDiscipline = this.disciplineService.getById(Integer.valueOf(disciplineId));
        if (!optionalDiscipline.isPresent()) {
            return new ResponseEntity<>(
                    String.format("Discipline with id %s not found", disciplineId), HttpStatus.NOT_FOUND
            );
        }
        Discipline disciplineData = optionalDiscipline.get();
        if (!updatedDiscipline.getCode().isEmpty()) {
            disciplineData.setCode(updatedDiscipline.getCode());
        }
        if (!updatedDiscipline.getName().isEmpty()) {
            disciplineData.setName(updatedDiscipline.getName());
        }if (!updatedDiscipline.getTypePass().isEmpty()) {
            disciplineData.setTypePass(updatedDiscipline.getTypePass());
        }
        this.disciplineService.save(disciplineData);
        return ResponseEntity.ok("New Discipline successfully updated");
    }

    @DeleteMapping("/{disciplineId}")
    public ResponseEntity<Object> deleteDiscipline(@PathVariable("disciplineId") String disciplineId) {
        Optional<Discipline> optionalDiscipline = this.disciplineService.getById(Integer.valueOf(disciplineId));
        if (!optionalDiscipline.isPresent()) {
            return new ResponseEntity<>(
                    String.format("Discipline with id %s not found", disciplineId), HttpStatus.NOT_FOUND
            );
        }
        this.disciplineService.delete(optionalDiscipline.get());
        return ResponseEntity.ok("New Discipline successfully deleted");
    }

}
