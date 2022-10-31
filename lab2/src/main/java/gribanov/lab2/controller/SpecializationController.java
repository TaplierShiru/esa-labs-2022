package gribanov.lab2.controller;

import gribanov.lab2.models.Specialization;
import gribanov.lab2.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/specializations")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @GetMapping
    public ResponseEntity<Object> getSpecializations() {
        List<Specialization> specializationList = this.specializationService.getAll();
        return ResponseEntity.ok().body(specializationList);
    }

    @PostMapping
    public ResponseEntity<Object> createSpecialization(@RequestBody Specialization specialization) {
        this.specializationService.save(specialization);
        return ResponseEntity.ok("New specialization successfully added");
    }

    @PutMapping("/{specializationId}")
    public ResponseEntity<Object> updateSpecialization(@PathVariable("specializationId") String specializationId, @RequestBody Specialization updatedSpecialization) {
        Optional<Specialization> optionalSpecialization = this.specializationService.getById(Integer.valueOf(specializationId));
        if (!optionalSpecialization.isPresent()) {
            return new ResponseEntity<>(
                    String.format("Specialization with id %s not found", specializationId), HttpStatus.NOT_FOUND
            );
        }
        Specialization specialization = optionalSpecialization.get();
        if (!updatedSpecialization.getCode().isEmpty()) {
            specialization.setCode(updatedSpecialization.getCode());
        }
        if (!updatedSpecialization.getName().isEmpty()) {
            specialization.setName(updatedSpecialization.getName());
        }
        this.specializationService.save(specialization);
        return ResponseEntity.ok("New specialization successfully updated");
    }

    @DeleteMapping("/{specializationId}")
    public ResponseEntity<Object> deleteSpecialization(@PathVariable("specializationId") String specializationId) {
        Optional<Specialization> optionalSpecialization = this.specializationService.getById(Integer.valueOf(specializationId));
        if (!optionalSpecialization.isPresent()) {
            return new ResponseEntity<>(
                    String.format("SessionData with id %s not found", specializationId), HttpStatus.NOT_FOUND
            );
        }
        this.specializationService.delete(optionalSpecialization.get());
        return ResponseEntity.ok("New specialization successfully deleted");
    }

}
