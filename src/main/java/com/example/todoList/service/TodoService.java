package com.example.todoList.service;

import com.example.todoList.todo.Todo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class TodoService {

    private static List<Todo> todoList = new ArrayList<>();

    private static int count=0;

    static {
        todoList.add(new Todo(count++,"akshit","Learn Java", LocalDate.now().plusYears(1),false));
        todoList.add(new Todo(count++, "akshit", "Python", LocalDate.now().plusDays(20), false));
        todoList.add(new Todo(count++, "admin", "SpringBoot", LocalDate.now().plusDays(15), false));
    }

    public List<Todo> getByUserName(String username){
        return todoList;
    }

    public void addTodo(String username, String description, LocalDate date, boolean isDone){
        Todo todo = new Todo(count++,username, description, date, isDone);
        todoList.add(todo);
    }

    public void deleteById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId()==id;
        todoList.removeIf(predicate);
    }


    public Todo updateTodoDescriptionById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todoList.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todoList.add(todo);
    }
}
