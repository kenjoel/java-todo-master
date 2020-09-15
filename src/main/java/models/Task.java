package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Task {

    private String description;
    private static ArrayList<Task> instances = new ArrayList<>();
    private boolean completed;
    private LocalDateTime createdAt;
    private int id;
    private int categoryId;

    public Task(String description, int categoryId) {
        this.description = description;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
        this.categoryId = categoryId;
    }

    ;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return completed == task.completed &&
                id == task.id &&
                categoryId == task.categoryId &&
                Objects.equals(description, task.description) &&
                Objects.equals(createdAt, task.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, completed, createdAt, id, categoryId);
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void setCompleted(boolean completed){
//        this.completed = completed;
//    }


    public int setId(int id) {
        this.id = id;
        return id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }


    public static void setInstances(ArrayList<Task> instances) {
        Task.instances = instances;
    }

    public String getDescription() {
        return description;
    }


    public boolean getCompleted() {
        return this.completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }
}



//    public static Task findById(int id){
//        return instances.get(id-1); //why minus 1? See if you can figure it out.
//    }
//
//    public void update(String content) {
//        this.description = content;
//    }
//
//    public void deleteTask(){
//        instances.remove(id-1); //same reason
//    }

