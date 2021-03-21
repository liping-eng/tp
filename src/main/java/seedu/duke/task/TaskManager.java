package seedu.duke.task;

import seedu.duke.Ui;

public class TaskManager {

    public static void execute() {
        while (true) {
            Ui.printTaskManagerMenu();
            String command = Ui.readCommand();
            Ui.printHorizontalLine();
            try {
                int taskNumber = Integer.parseInt(command);
                switch (taskNumber) {
                case 1:
                    addNewTask();
                    break;
                case 2:
                    markOrUnmarkTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    viewAllTasks();
                    break;
                case 5:
                    pinTask();
                    break;
                case 6:
                    return;
                default:
                    Ui.printInvalidIntegerMessage();
                }
            } catch (NumberFormatException e) {
                Ui.printInvalidIntegerMessage();
                Ui.printHorizontalLine();
            }
        }
    }

    public static void addNewTask() {
        Ui.printAddTaskMenu();
        int taskTypeNumber = TaskList.getTaskNumber();
        Ui.printHorizontalLine();

        TaskList.addNewTask(taskTypeNumber);
    }

    private static void markOrUnmarkTask() {
        Ui.printMarkTaskMenu();
        int taskTypeNumber = TaskList.getTaskNumber();
        Ui.printHorizontalLine();

        TaskList.markOrUnmarkTask(taskTypeNumber);

    }

    private static void viewAllTasks() {
        Ui.printPinnedTaskList(TaskList.pinnedTasks);
        Ui.printEmptyLine();
        Ui.printTaskList(TaskList.tasks);
        Ui.printEmptyLine();
        Ui.printAssignmentList(TaskList.assignments);
        Ui.printEmptyLine();
        Ui.printMidtermList(TaskList.midterms);
        Ui.printEmptyLine();
        Ui.printFinalExamList(TaskList.finalExams);
        Ui.printEmptyLine();
        Ui.printHorizontalLine();
    }

    private static void pinTask() {
        Ui.printPinTaskMenu();
        int taskTypeNumber = TaskList.getTaskNumber();
        Ui.printHorizontalLine();

        TaskList.pinTask(taskTypeNumber);
    }

    public static void deleteTask() {
        Ui.printDeleteTaskMenu();
        int taskTypeNumber = TaskList.getTaskNumber();
        Ui.printHorizontalLine();

        TaskList.deleteTask(taskTypeNumber);
    }
}
