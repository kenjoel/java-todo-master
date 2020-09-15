package dao;
import models.Task;
import org.sql2o.*;
import java.util.List;

public class Sql2oTaskDao implements storyDao { //implementing our interface

    private final Sql2o sql2o;

    public Sql2oTaskDao(Sql2o sql2o){
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }

    @Override
    public void create(Task task) {
        String sql = "INSERT INTO tasks (description, categoryId) VALUES (:description, :categoryId)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(task) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            task.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }

    @Override
    public List<Task> getAll() {
        try(Connection con = sql2o.open()){
            Query query = con.createQuery("SELECT * FROM tasks"); //raw sql
                    return query.executeAndFetch(Task.class); //fetch a list
        }
    }

    @Override
    public Task findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM tasks WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Task.class); //fetch an individual item
        }
    }

    @Override
    public void update(int id, String description, int categoryId){
        String sql = "UPDATE tasks SET (description, categoryId) = (:description, :categoryId) WHERE  id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("description", description)
                    .addParameter( "categoryId", categoryId)
                    .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deleteTask(int id){
        try(Connection con = sql2o.open()){
            String sql = "DELETE FROM tasks WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllTasks(){
        String sql = "DELETE FROM tasks";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException ey){
            System.out.println(ey);
        }

    }

}