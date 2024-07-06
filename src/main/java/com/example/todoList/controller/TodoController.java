package com.example.todoList.controller;

import com.example.todoList.service.TodoService;
import com.example.todoList.todo.Todo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        Todo todo = new Todo(0, username, "", LocalDate.now(), false);
        model.put("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "/addTodo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "addTodo";
        }

        String username = (String) model.get("name");
        todoService.addTodo(username, todo.getDescription(),todo.getTargetDate(), false);
        return "redirect:listTodos";
    }

    @RequestMapping(value = "/deleteTodo")
    public String deleteTodod(@RequestParam int id){
        todoService.deleteById(id);
        return "redirect:listTodos";
    }

    @RequestMapping(value = "updateTodo", method = RequestMethod.GET)
    public String showupdateTodopage(@RequestParam int id, ModelMap modelMap){
        Todo todo= todoService.updateTodoDescriptionById(id);
        modelMap.addAttribute("todo", todo);

        return "addTodo";
    }

    @RequestMapping(value = "updateTodo", method = RequestMethod.POST)
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addTodo";
        }
        String username = (String)modelMap.get("name");
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:listTodos";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        return "login";
    }


}
