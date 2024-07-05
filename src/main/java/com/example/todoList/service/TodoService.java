package com.example.todoList.service;

import com.example.todoList.todo.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todoList = new ArrayList<>();

    static {
        todoList.add(new Todo(1,"akshit","Learn Java 1", LocalDate.now().plusYears(1),true));
        todoList.add(new Todo(2, "akshit", "Python 1", LocalDate.now(), false));
        todoList.add(new Todo(3, "admin", "SpringBoot 1", LocalDate.now(), false));
    }

    public List<Todo> getByUserName(String username){
        return todoList;
    }
}
