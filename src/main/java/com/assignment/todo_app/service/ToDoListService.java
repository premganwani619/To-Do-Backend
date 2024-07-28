package com.assignment.todo_app.service;

import com.assignment.todo_app.model.ToDo;
import com.assignment.todo_app.repository.ToDoListRepository;
import com.assignment.todo_app.exception.ToDoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ToDoListService {

    private static final Logger logger = LoggerFactory.getLogger(ToDoListService.class);

    @Autowired
    private ToDoListRepository toDoListRepository;

    public ToDo createToDo(ToDo toDo) {
        logger.info("Creating new ToDo: {}", toDo);
        return toDoListRepository.save(toDo);
    }

    public List<ToDo> getAllToDos() {
        return toDoListRepository.findAllByOrderByCreatedAtDesc();
    }

    public ToDo updateToDoStatus(Long id, String description, boolean completed) {
        ToDo toDo = toDoListRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo with ID " + id + " not found"));
        toDo.setDescription(description);
        toDo.setCompleted(completed);
        logger.info("Updating ToDo with ID {} to completed status: {}", id, completed);
        return toDoListRepository.save(toDo);
    }

    public ToDo getToDoById(Long id) {
        return toDoListRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo with ID " + id + " not found"));
    }

    public void deleteToDoById(Long id) {
        if (!toDoListRepository.existsById(id)) {
            throw new ToDoNotFoundException("ToDo with ID " + id + " not found");
        }
        logger.info("Deleting ToDo with ID {}", id);
        toDoListRepository.deleteById(id);
    }

    public ToDo updateToDoDescription(Long id, String description) {
        ToDo toDo = toDoListRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("ToDo with ID " + id + " not found"));
        toDo.setDescription(description);
        logger.info("Updating ToDo with ID {} to description: {}", id, description);
        return toDoListRepository.save(toDo);
    }

}
