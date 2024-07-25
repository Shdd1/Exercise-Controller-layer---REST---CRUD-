package com.example.exercisecrud1.Controller;

import com.example.exercisecrud1.model.Task;
import com.example.exercisecrud1.response.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskTrackerSystem {
    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponses addTask(@RequestBody Task task) {
        tasks.add(task);
        return new ApiResponses("Task added");
    }

    @PutMapping("/update/{index}")
    public ApiResponses updateTask(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);
        return new ApiResponses("Task is update");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponses deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return new ApiResponses("Success");
    }

    @PutMapping("/change/{index}")
    public ApiResponses checkStatus(@PathVariable int index) {
        if(tasks.get(index).getStatus().equalsIgnoreCase("not done")){
            tasks.get(index).setStatus("done");
            return new ApiResponses("success");
        }else
            return new ApiResponses("it is already done");
    }
   @GetMapping("/search/{t}")
    public Task searchTask(@PathVariable String t) {
       for (Task task : tasks) {
           if (task.getTitle().equals(t)) {
               return task;
           }
       }
       return null;
   }

}
