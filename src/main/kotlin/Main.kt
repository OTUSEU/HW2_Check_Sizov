import data.TasksRepositoryMemory
import menu.Actions
import menu.taskActions

/**
 * Функция, которая выводит на консоль примитивное меню.
 * Получает ответ 1-7 и возвращает его,
 * вызывается ниже из  main()
 */
fun renderMenu(): Int {
    println("=========================================================")
    val actions = listOf(
        "Add task", "Delete task", "List all tasks", "List non-completed tasks",
        "Complete task", "Uncomplete task", "Quit"
    )
    actions.forEachIndexed { index, action ->
        println("${index + 1}. $action")
    }
    print("Make your choice: ")
    return readln().toIntOrNull() ?: 0
}

fun main() {
    println("Otus Todo List\n")
    val repository = TasksRepositoryMemory()

    while (true) {
        val action = renderMenu()
        try {
            val func = taskActions[Actions.values()[action - 1]]
            func?.call(repository)
        } catch (e: Exception) {
            //just skip
        }
    }
}