//package dao;
//
//import models.Task;
//import org.sql2o.*;
//import org.junit.*;
//import static org.junit.Assert.*;
//
//public class Sql2oTaskDaoTest {
//    private Sql2oTaskDao taskDao; //ignore me for now. We'll create this soon.
//    private Connection conn; //must be sql2o class conn
//
//    @Before
//    public void setUp() throws Exception {
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        taskDao = new Sql2oTaskDao(sql2o); //ignore me for now
//        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        conn.close();
//    }
//
//    @Test
//    public void addingSetsNewId()throws Exception{
//        Task task = new Task("go to school");
//        int originalTaskId = task.getId();
//        taskDao.create(task);
//        assertNotEquals(originalTaskId, task.getId());
//
//    }
//
//    @Test
//    public void existingTasksCanBeFoundById() throws Exception {
//        Task task = new Task ("mow the lawn");
//        taskDao.create(task); //add to dao (takes care of saving)
//        Task foundTask = taskDao.findById(task.getId()); //retrieve
//        assertEquals(task, foundTask); //should be the same
//    }
//
//    @Test
//    public void getAllTasks() throws Exception{
//        Task firstAttempt = new Task("There are this tasks here");
//        taskDao.create(firstAttempt);
//        assertEquals(1, taskDao.getAll().size());
//    }
//
//    @Test
//    public void noTasksReturnsEmptyList() throws Exception {
//        assertEquals(0, taskDao.getAll().size());
//    }
//}
package dao;

import models.Task;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oTaskDaoTest {
    private Sql2oTaskDao taskDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        taskDao = new Sql2oTaskDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCourseSetsId() throws Exception {
        Task task = setupNewTask();
        int originalTaskId = task.getId();
        taskDao.create(task);
        assertNotEquals(originalTaskId, task.getId()); //how does this work?
    }

    @Test
    public void existingTasksCanBeFoundById() throws Exception {
        Task task = setupNewTask();
        int foundTask = task.getCategoryId(); //retrieve
        taskDao.create(task); //add to dao (takes care of saving)
        assertEquals(foundTask, taskDao.findById(task.getId()).getCategoryId()); //should be the same
    }

    @Test
    public void addedTasksAreReturnedFromgetAll() throws Exception {
        Task task = setupNewTask();
        taskDao.create(task);
        assertEquals(1, taskDao.getAll().size());
    }

    @Test
    public void noTasksReturnsEmptyList() throws Exception {
        assertEquals(0, taskDao.getAll().size());
    }

    @Test
    public void updateChangesTaskContent() throws Exception {
        String initialDescription = "mow the lawn";
        Task task = new Task (initialDescription, 3);
        taskDao.create(task);
        taskDao.update(task.getId(),"welcome the raster youth", 1);
        Task updatedTask = taskDao.findById(task.getId()); //why do I need to refind this?
        assertNotEquals(initialDescription, updatedTask.getDescription());
    }

    @Test
    public void deleteByIdDeletesCorrectTask() throws Exception {
        Task task = setupNewTask();
        taskDao.create(task);
        taskDao.deleteTask(task.getId());
        assertEquals(0, taskDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Task task = setupNewTask();
        Task otherTask = setupNewTask();
        taskDao.create(task);
        taskDao.create(otherTask);
        int daoSize = taskDao.getAll().size();
        taskDao.clearAllTasks();
        assertTrue(daoSize > 0 && daoSize > taskDao.getAll().size()); //this is a little overcomplicated, but illustrates well how we might use `assertTrue` in a different way.
    }

    //helper method for the tasks

    public Task setupNewTask(){
        return new Task("Mow the lawn", 1);
    }

}
