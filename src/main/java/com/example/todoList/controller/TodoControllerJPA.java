package com.example.todoList.controller;

import com.example.todoList.todo.Todo;
import com.example.todoList.todo.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TodoControllerJPA {

    @Autowired
    TodoRepository todoRepository;

    @RequestMapping(value = "/listTodos")
    public String listOfTodos(ModelMap model){
        String username = getLoggedInUserName(model);
        List<Todo> todoList = todoRepository.findByUsername(username);
        model.addAttribute("todoList",todoList);
        return "listTodos";
    }

    @RequestMapping(value = "/addTodo", method = RequestMethod.GET)
    public String showNewTodo(ModelMap model){
        String username = getLoggedInUserName(model);
        Todo todo = new Todo(0, username, "", LocalDate.now(), false);
        model.put("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "/addTodo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "addTodo";
        }

        String username = getLoggedInUserName(model);
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:listTodos";
    }

    @RequestMapping(value = "/deleteTodo")
    public String deleteTodod(@RequestParam int id){
        todoRepository.deleteById(id);
        return "redirect:listTodos";
    }

    @RequestMapping(value = "updateTodo", method = RequestMethod.GET)
    public String showupdateTodopage(@RequestParam int id, ModelMap modelMap){
        Todo todo= todoRepository.findById(id).get();
        modelMap.addAttribute("todo", todo);

        return "addTodo";
    }

    @RequestMapping(value = "updateTodo", method = RequestMethod.POST)
    public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addTodo";
        }
        String username = getLoggedInUserName(modelMap);
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:listTodos";
    }

    @RequestMapping(value = "/logout")
    public String logout(){
        return "login";
    }

    private String getLoggedInUserName(ModelMap model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
