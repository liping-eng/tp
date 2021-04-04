package seedu.duke.task.command;

import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskManager;

import java.util.ArrayList;

public class PinTask {

    private static final int PIN_TASK_COMMAND = 1;
    private static final int PIN_ASSIGNMENT_COMMAND = 2;
    private static final int PIN_MIDTERM_COMMAND = 3;
    private static final int PIN_FINAL_EXAM_COMMAND = 4;
    private static final String TASK_TYPE = "[Task]";
    private static final String ASSIGNMENT_TYPE = "[Assignment]";
    private static final String MIDTERM_TYPE = "[Midterm]";
    private static final String FINAL_EXAM_TYPE = "[Final Exam]";

    public static void execute(int taskTypeNumber) {
        if (TaskManager.taskListIsEmpty(taskTypeNumber)) {
            Ui.printTaskListIsEmptyMessage();
            return;
        }
        Ui.printSelectTaskNumberToPin(taskTypeNumber);
        while (true) {
            try {
                int taskNumber = Integer.parseInt(Ui.readCommand());
                switch (taskTypeNumber) {
                case PIN_TASK_COMMAND:
                    addTaskToPinnedTasks(TaskManager.tasks.get(taskNumber - 1), TASK_TYPE);
                    break;
                case PIN_ASSIGNMENT_COMMAND:
                    addTaskToPinnedTasks(TaskManager.assignments.get(taskNumber - 1), ASSIGNMENT_TYPE);
                    break;
                case PIN_MIDTERM_COMMAND:
                    addTaskToPinnedTasks(TaskManager.midterms.get(taskNumber - 1), MIDTERM_TYPE);
                    break;
                case PIN_FINAL_EXAM_COMMAND:
                    addTaskToPinnedTasks(TaskManager.finalExams.get(taskNumber - 1), FINAL_EXAM_TYPE);
                    break;
                default:
                    Ui.printInvalidIntegerMessage();
                }
                return;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                Ui.printInvalidIntegerMessage();
            }
        }
    }

    public static void addTaskToPinnedTasks(Task task, String taskTypeName) {
        TaskManager.pinnedTasks.computeIfAbsent(taskTypeName, k -> new ArrayList<>());
        if (TaskManager.pinnedTasks.get(taskTypeName).contains((task))) {
            Ui.printTaskAlreadyPinnedMessage();
            return;
        }
        TaskManager.pinnedTasks.get(taskTypeName).add(task);
        assert TaskManager.pinnedTasks.get(taskTypeName).contains(task) : "Task was not added to pinned list";
        Ui.printPinnedTaskMessage(task);
    }
}
