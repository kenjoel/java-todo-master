package dao;

import models.Category;
import org.sql2o.*;
import java.util.List;

import models.Task;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;


public class Sql2oCategoryDao implements CategoryDao {

    private final Sql2o sql2o;

    public Sql2oCategoryDao(Sql2o sql2o) {
        this.sql2o = sql2o; //making the sql2o object available everywhere so we can call methods in it
    }

    @Override
    public void add(Category category) {
        String sql = "INSERT INTO categories (name) VALUES (:name)"; //raw sql
        try(Connection con = sql2o.open()){ //try to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
                    .bind(category) //map my argument onto the query so we can use information from it
                    .executeUpdate() //run it all
                    .getKey(); //int id is now the row number (row “key”) of db
            category.setId(id); //update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }


    @Override
    public List<Category> getAll() {
        try (Connection con = sql2o.open()) {
            Query query = con.createQuery("SELECT * FROM categories"); //raw sql
            return query.executeAndFetch(Category.class); //fetch a list
        }
    }

    @Override
    public Category findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM categories WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Category.class); //fetch an individual item
        }
    }

    @Override
    public void update(int id, String name) {
        String sql = "UPDATE categories SET name =:name WHERE  id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deleteById(int id) {
        try (Connection con = sql2o.open()) {
            String sql = "DELETE FROM categories WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Task> getAllTasksByCategory(int categoryId) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM tasks WHERE categoryId = :categoryId")
                    .addParameter("categoryId", categoryId)
                    .executeAndFetch(Task.class);
        }
    }



    @Override
    public void clearAllCategories() {
        String sql = "DELETE FROM categories";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ey) {
            System.out.println(ey);
        }


    }
}
