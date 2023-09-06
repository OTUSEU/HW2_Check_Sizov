import data.Priority
import data.Task
import data.TasksRepositoryMemory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Test1 {


    @Test
    //Сделать тест добавления задачи и появления ее в списке
    fun addTaskTest(){
        val testRepository = TasksRepositoryMemory()
        val nameOfTestTask = "test_task_name"
        val sizeTaskListBefore = testRepository.getTasks().size
        val  _priority = Priority.values().random()
        val taskID = testRepository.addTask(Task(name = nameOfTestTask, priority = _priority))
        Assertions.assertTrue(testRepository.getTasks().contains(Task(id = taskID, name = nameOfTestTask, priority = _priority)))
        Assertions.assertEquals(sizeTaskListBefore + 1, testRepository.getTasks().size)
        Assertions.assertTrue(testRepository.getTasks().filter { it.name == nameOfTestTask }.size ==1)
    }

    @Test
    //Сделать тест добавления задачи и появления ее в списке ????????????????? Зачем повтор теста 1
    fun addTaskTest2(){
        val testRepository = TasksRepositoryMemory()
        var task1 = Task(name = "test_task_name", priority = Priority.values().random())
        val sizeTaskListBefore = testRepository.getTasks().size
        task1 = task1.copy(id = testRepository.addTask(task1))
        Assertions.assertTrue(testRepository.getTasks().contains(task1))
        Assertions.assertEquals(sizeTaskListBefore + 1, testRepository.getTasks().size)
        Assertions.assertTrue(testRepository.getTasks().filter { it.name == task1.name }.size ==1)
    }

    @Test
    //Завершить задачу и проверить корректность работы фильтра по завершенным задачам
    //но без ТЗ тяжело судить о корректности реализации фильтра, но наименования LIST_TASKS и LIST_NON_COMPLETED_TASKS подсказывают,
    // что фильтр подразумевается только по НЕзавершенным задачам
    fun taskToCompleteTest(){
        val testRepository = TasksRepositoryMemory()
        var taskToComplete = Task(name = "taskToComplete", priority = Priority.values().random())
        var taskToNotComplete = Task(name = "taskToNotComplete", priority = Priority.values().random())
        taskToComplete = taskToComplete.copy(id = testRepository.addTask(taskToComplete))
        taskToNotComplete = taskToNotComplete.copy(id = testRepository.addTask(taskToNotComplete))
        taskToComplete.id?.let { testRepository.completeTask(it) }
        taskToComplete.completed = true
        Assertions.assertTrue(testRepository.getTasks(true).contains(taskToComplete))
        Assertions.assertTrue(testRepository.getTasks(true).contains(taskToNotComplete))
        Assertions.assertFalse(testRepository.getTasks(false).contains(taskToComplete))
        Assertions.assertTrue(testRepository.getTasks(false).contains(taskToNotComplete))


        

    }




}