package spek

import data.Priority
import data.Task
import data.TasksRepositoryMemory
import org.junit.jupiter.api.Assertions
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import renderMenu
import kotlin.test.assertEquals

class MyTestClass: Spek({

    describe("MainKtTest") {
        it("renderMenu chosen 3 when System InputStream contains 3"){
            System.setIn("3".byteInputStream())
            assertEquals(3, renderMenu())
        }
    }

    describe("Перенос написанных ранее тестов на спек"){
        it("Проверка добавлнения задачи"){
            val testRepository = TasksRepositoryMemory()
            val nameOfTestTask = "test_task_name"
            val sizeTaskListBefore = testRepository.getTasks().size
            val  _priority = Priority.values().random()
            val taskID = testRepository.addTask(Task(name = nameOfTestTask, priority = _priority))
            Assertions.assertTrue(testRepository.getTasks().contains(Task(id = taskID, name = nameOfTestTask, priority = _priority)))
            Assertions.assertEquals(sizeTaskListBefore + 1, testRepository.getTasks().size)
            Assertions.assertTrue(testRepository.getTasks().filter { it.name == nameOfTestTask }.size ==1)
        }
        it("Проверка выполнения задачи"){
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

})