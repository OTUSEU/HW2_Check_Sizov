package data

/**
 * Приоритет присваивается задаче,
 * используется только при сортировке
 * И в тестах
 */

enum class Priority {
    LOW,
    MEDIUM,
    HIGH
}
/**
 * Сам класс задачи с полями
 */
data class Task(val id: Int? = null, val name: String, var priority: Priority, var completed: Boolean = false) {
    override fun toString(): String = ("$id. [${if (completed) "x" else " "}] $name : ${priority}")
}
/**
 * abstract class TasksRepository реализуется в TasksRepositoryMemory
 * Котлин рекомендует через interface
 * хорошо для тестирования на подмену Repository
 */
abstract class TasksRepository {
    abstract fun getTasks(completed: Boolean = true): List<Task>
    abstract fun addTask(task: Task): Int
    abstract fun deleteTask(id: Int)
    abstract fun completeTask(id: Int)
    abstract fun uncompleteTask(id: Int)
}
