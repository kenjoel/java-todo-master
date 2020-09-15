package dao;

import models.Category;
import models.Task;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oCategoryDaoTest {
    private Sql2oTaskDao taskDao; //ignore me for now. We'll create this soon. and my brother
    private Sql2oCategoryDao catDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        catDao = new Sql2oCategoryDao(sql2o); //ignore me for now
        taskDao = new Sql2oTaskDao(sql2o);
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingCategorySetsId() throws Exception {
        Category category = new Category ("mow the lawn");
        int originalCatId = category.getId();
        catDao.add(category);
        assertNotEquals(originalCatId, category.getId()); //how does this work?
    }

    @Test
    public void existingTasksCanBeFoundById() throws Exception {
        Category category1 = new Category ("mow the lawn");
        catDao.add(category1); //add to dao (takes care of saving)
        Category foundTask = catDao.findById(category1.getId()); //retrieve
        assertEquals(category1, foundTask); //should be the same
    }

    @Test
    public void addedTasksAreReturnedFromgetAll() throws Exception {
        Category category2 = new Category ("mow the lawn");
        catDao.add(category2);
        assertEquals(1, catDao.getAll().size());
    }

    @Test
    public void noTasksReturnsEmptyList() throws Exception {
        assertEquals(0, catDao.getAll().size());
    }

    @Test
    public void getAllTasksByCategoryReturnsTasksCorrectly() throws Exception {
        Category category = new Category("Caters");
        catDao.add(category);
        int categoryId = category.getId();
        Task newTask = new Task("mow the lawn", categoryId);
        Task otherTask = new Task("pull weeds", categoryId);
        Task thirdTask = new Task("trim hedge", categoryId);
        taskDao.create(newTask);
        taskDao.create(otherTask); //we are not adding task 3 so we can test things precisely.
        assertEquals(2, catDao.getAllTasksByCategory(categoryId));
        assertTrue(catDao.getAllTasksByCategory(categoryId).contains(newTask));
        assertTrue(catDao.getAllTasksByCategory(categoryId).contains(otherTask));
        assertFalse(catDao.getAllTasksByCategory(categoryId).contains(thirdTask)); //things are accurate!
    }


//    @Test
//    public void updateChangesTaskContent() throws Exception {
//        String initialDescription = "mow the lawn";
//        Category category3 = new Category (initialDescription);
//        catDao.add(category3);
//
//        catDao.update(category3.getId(),"brush the cat");
//        Category updatedCat = catDao.findById(category3.getId()); //why do I need to refind this?
//        assertNotEquals(initialDescription, updatedCat.getId());
//    }

    @Test
    public void deleteByIdDeletesCorrectTask() throws Exception {
        Category category4 = new Category ("mow the lawn");
        catDao.add(category4);
        catDao.deleteById(category4.getId());
        assertEquals(0, catDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Category lastCat  = new Category ("mow the lawn");
        Category otherCat = new Category("brush the cat");
        catDao.add(lastCat);
        catDao.add(otherCat);
        int daoSize = catDao.getAll().size();
        catDao.clearAllCategories();
        assertTrue(daoSize > 0 && daoSize > catDao.getAll().size()); //this is a little overcomplicated, but illustrates well how we might use `assertTrue` in a different way.
    }

}