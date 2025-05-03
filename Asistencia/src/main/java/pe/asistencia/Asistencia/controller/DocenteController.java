package pe.asistencia.Asistencia.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.asistencia.Asistencia.model.Docente;
import pe.asistencia.Asistencia.service.IDocenteService;


import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/docente")
public class DocenteController {
    private final IDocenteService docenteService;
    @GetMapping
    public ResponseEntity<List<Docente>> findAll() {
        List<Docente> list = docenteService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Docente> findById(@PathVariable("id") Long
                                                  id) {
        Docente obj = docenteService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Docente dto) {
        Docente obj = docenteService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdDocente()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Docente> update(@PathVariable("id") Long
                                                id, @RequestBody
    Docente dto) {
        dto.setIdDocente(id);
        Docente obj = docenteService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        docenteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}