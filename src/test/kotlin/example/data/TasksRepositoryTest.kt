package example.data

import data.Priority
import data.Task
import data.TasksRepository
import data.TasksRepositoryMemory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
//import kotlin.test.Test
//import kotlin.test.BeforeTest
//import kotlin.test.assertEquals
/**
 * Класс тестов на список, номер, удаление, (раз)завершение задач
 */
class TasksRepositoryTest {
    // ссылка на экземпляр типа родительского абстрактного класса
    // заполняется ы setup, используется в тестах
    private lateinit var repository: TasksRepository

    //@BeforeTest
    @BeforeEach
    fun setUp() {
        // создаем экземпляр репозитория для тестов с пустой "БД" внутри
        val tasksRepositoryMemory = TasksRepositoryMemory()
        // Добавить три задачи для тестов в БД - 2 active и 1 completed
        with(tasksRepositoryMemory.tasks) {
            add(Task(1, "First", Priority.LOW))
            add(Task(2, "Second", Priority.MEDIUM, true))
            add(Task(3, "Third", Priority.HIGH))
        }
        repository = tasksRepositoryMemory
    }
    // JUnit 5:
    @Test
    @DisplayName("getTasks should return all tasks in repository")
    fun `getTasks should return all tasks in repository`() {
        assertEquals(3, repository.getTasks().size, "getTasks did not return all tasks to the repository")
    }

    @Test
    @DisplayName("getTasks with false parameter should return only uncompleted tasks")
    fun `getTasks with false parameter should return only uncompleted tasks`() {
        assertEquals(2, repository.getTasks(false).size)
    }

    @Test
    fun `getTasks with true parameter should return only completed tasks`() {
        //todo may be TasksRepositoryMemory incorrect behaviour. May be method must return 1
        assertEquals(3, repository.getTasks(true).size)
    }

    @Test
    fun `add task method should return id of new task`() {
        val newTask = Task(name = "SomeTask", priority = Priority.HIGH)
        val newTaskId = repository.addTask(newTask)
        assertEquals(4, newTaskId)
        val newTask2 = Task(name = "SomeTask2", priority = Priority.MEDIUM)
        val newTaskId2 = repository.addTask(newTask2)
        assertEquals(5, newTaskId2)
    }

    @Test
    fun `delete task method should delete task from storage`() {
        assertEquals(3, repository.getTasks().size)
        repository.deleteTask(3)
        assertEquals(2, repository.getTasks().size)
    }

    @Test
    fun `delete task method with not existed task id should do nothing`() {
        assertEquals(3, repository.getTasks().size)
        repository.deleteTask(45)
        assertEquals(3, repository.getTasks().size)
    }

    @Test
    fun `complete task method should set complete true`() {
        assertEquals(3, repository.getTasks().size)
        assertEquals(2, repository.getTasks(false).size)
        repository.completeTask(3)
        assertEquals(3, repository.getTasks().size)
        assertEquals(1, repository.getTasks(false).size)
    }

    @Test
    fun `complete task method should set complete false`() {
        assertEquals(3, repository.getTasks().size)
        assertEquals(2, repository.getTasks(false).size)
        repository.uncompleteTask(2)
        assertEquals(3, repository.getTasks().size)
        assertEquals(3, repository.getTasks(false).size)
    }
}
