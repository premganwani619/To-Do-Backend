package com.assignment.todo_app.controller;

import com.assignment.todo_app.model.ToDo;
import com.assignment.todo_app.service.ToDoListService;
import com.assignment.todo_app.exception.ToDoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/todos")
@Validated
public class ToDoListController {

    @Autowired
    private ToDoListService toDoListService;

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo toDo) {
        ToDo createdToDo = toDoListService.createToDo(toDo);
        return new ResponseEntity<>(createdToDo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ToDo>> getAllToDos() {
        List<ToDo> toDos = toDoListService.getAllToDos();
        return new ResponseEntity<>(toDos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateToDoStatus(@PathVariable Long id, @RequestParam String description, @RequestParam boolean completed) {
        try {
            ToDo updatedToDo = toDoListService.updateToDoStatus(id,description, completed);
            return new ResponseEntity<>(updatedToDo, HttpStatus.OK);
        } catch (ToDoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable Long id) {
        try {
            ToDo toDo = toDoListService.getToDoById(id);
            return new ResponseEntity<>(toDo, HttpStatus.OK);
        } catch (ToDoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoById(@PathVariable Long id) {
        try {
            toDoListService.deleteToDoById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ToDoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
