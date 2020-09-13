package dao;

import models.Task;

import java.util.List;

public interface storyDao {
    //List all stories
    List<Task> getAll();

    //Create stories
    void create(Task a);

    //List one story
    Task findById(int id);

    //update Story
    void update(int a, String b );

    //delete
    void deleteTask(int a);
    void clearAllTasks();
}
