package com.vladimirpandurov.noteAppB.controller;

import com.vladimirpandurov.noteAppB.domain.HttpResponse;
import com.vladimirpandurov.noteAppB.domain.Note;
import com.vladimirpandurov.noteAppB.enumeration.Level;
import com.vladimirpandurov.noteAppB.exception.NoteNotFoundException;
import com.vladimirpandurov.noteAppB.service.NoteService;
import com.vladimirpandurov.noteAppB.util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
@CrossOrigin
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<HttpResponse<Note>> getNotes() {
        return ResponseEntity.ok().body(this.noteService.getNotes());
    }
    @PostMapping
    public ResponseEntity<HttpResponse<Note>> saveNote(@RequestBody @Valid Note note){
        return ResponseEntity.created(
                URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/notes").toUriString())).body(this.noteService.saveNote(note)
        );
    }
    @GetMapping("/filter")
    public ResponseEntity<HttpResponse<Note>> filterNotes(@RequestParam(value="level") Level level) {
        return ResponseEntity.ok().body(this.noteService.filterNotes(level));
    }
    @PutMapping
    public ResponseEntity<HttpResponse<Note>> updateNote(@RequestBody @Valid Note note) throws NoteNotFoundException {
        return ResponseEntity.ok().body(this.noteService.updateNote(note));
    }
    @DeleteMapping
    public ResponseEntity<HttpResponse<Note>> deleteNote(@PathVariable(value = "noteId") Long id) throws NoteNotFoundException {
        return ResponseEntity.ok().body(this.noteService.deleteNote(id));
    }
    @RequestMapping("/error")
    public ResponseEntity<HttpResponse<?>> handleError(HttpServletRequest request) {
        return new ResponseEntity<>(
                HttpResponse.builder()
                        .reason("There is no mapping for a " + request.getMethod() + " request for this path on the server")
                        .developerMessage("There is no mapping for a " + request.getMethod() + " request for this path on the server")
                        .status(HttpStatus.NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .timeStamp(LocalDateTime.now().format(DateUtil.dateTimeFormatter()))
                        .build(), HttpStatus.NOT_FOUND
        );
    }

}
