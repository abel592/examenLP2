package pe.asistencia.Asistencia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pe.asistencia.Asistencia.model.Estudiante;
import pe.asistencia.Asistencia.service.IEstudianteService;


import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    private final IEstudianteService estudianteService;
    @GetMapping
    public ResponseEntity<List<Estudiante>> findAll() {
        List<Estudiante> list = estudianteService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> findById(@PathVariable("id") Long
                                                    id) {
        Estudiante obj = estudianteService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Estudiante dto) {
        Estudiante obj = estudianteService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdEstudiante()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable("id") Long
                                                  id, @RequestBody
    Estudiante dto) {
        dto.setIdEstudiante(id);
        Estudiante obj = estudianteService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        estudianteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}