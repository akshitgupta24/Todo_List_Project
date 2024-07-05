package com.example.todoList.service;

import com.example.todoList.todo.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todoList = new ArrayList<>();

    private static int count=0;

    static {
        todoList.add(new Todo(count++,"akshit","Learn Java 1", LocalDate.now().plusYears(1),false));
        todoList.add(new Todo(count++, "akshit", "Python 1", LocalDate.now(), false));
        todoList.add(new Todo(count++, "admin", "SpringBoot 1", LocalDate.now(), false));
    }

    public List<Todo> getByUserName(String username){
        return todoList;
    }

    public void addTodo(String username, String description, LocalDate date, boolean isDone){
        Todo todo = new Todo(count++,username, description, date, isDone);
        todoList.add(todo);
    }
}
