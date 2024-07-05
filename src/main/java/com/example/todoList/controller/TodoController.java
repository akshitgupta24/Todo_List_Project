package com.example.todoList.controller;

import com.example.todoList.service.TodoService;
import com.example.todoList.todo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    @RequestMapping(value = "/listTodos")
    public String listOfTodos(ModelMap model){
        List<Todo> todoList = todoService.getByUserName("Akshit");
        model.addAttribute("todoList",todoList);
        return "listTodos";
    }

    @RequestMapping(value = "/addTodo")
    public String addTodo(ModelMap model){
        String username = (String)model.get("name");
        Todo todo = new Todo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "addTodo";
    }
}
