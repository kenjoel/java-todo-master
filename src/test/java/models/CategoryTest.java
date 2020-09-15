//package models;
//
//import dao.Sql2oTaskDao;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
//
//import static org.junit.Assert.*;
//
//public class CategoryTest {
//
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
//
//    @Test
//    void testEquals() {
//    }
//
//    @Test
//    public void existingTasksCanBeFoundById() throws Exception {
//        Category category = new Category ("mow the lawn");
//        taskDao.add(category); //add to dao (takes care of saving)
//        Task foundTask = taskDao.findById(task.getId()); //retrieve
//        assertEquals(task, foundTask); //should be the same
//    }
//
//    @Test
//    public void addedTasksAreReturnedFromgetAll() throws Exception {
//        Task task = new Task ("mow the lawn");
//        taskDao.create(task);
//        assertEquals(1, taskDao.getAll().size());
//    }
//
//    @Test
//    public void noTasksReturnsEmptyList() throws Exception {
//        assertEquals(0, taskDao.getAll().size());
//    }
//}