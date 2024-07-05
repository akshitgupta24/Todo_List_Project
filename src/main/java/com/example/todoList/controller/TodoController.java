package com.example.todoList.controller;

import com.example.todoList.service.TodoService;
import com.example.todoList.todo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/addTodo", method = RequestMethod.GET)
    public String showNewTodo(ModelMap model){
        String username = (String)model.get("name");
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "/addTodo", method = RequestMethod.POST)
    public String addNewTodo(@RequestParam String description, ModelMap model){
        String username = (String) model.get("name");
        todoService.addTodo(username, description,LocalDate.now().plusDays(10), false);
        return "redirect:listTodos";
    }
}
