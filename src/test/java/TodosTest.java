import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

    String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
    Epic epic = new Epic(55, subtasks);

    Meeting meeting = new Meeting(
            555,
            "Выкатка 3й версии приложения",
            "Приложение НетоБанка",
            "Во вторник после обеда"
    );

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchingNotExistingQueryInSimpleTask() {
        simpleTask.matches("Написать");

        boolean expected = false;
        boolean actual = simpleTask.matches("Написать");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchingExistingQueryInSimpleTask() {
        simpleTask.matches("Позвонить");

        boolean expected = true;
        boolean actual = simpleTask.matches("Позвонить");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchingExistingQueryInMeetingForTopic() {
        meeting.matches("Выкатка");

        boolean expected = true;
        boolean actual = meeting.matches("Выкатка");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchingExistingQueryInMeetingForProject() {
        meeting.matches("Нето");

        boolean expected = true;
        boolean actual = meeting.matches("Нето");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchingNotExistingQueryInMeetingForProject() {
        meeting.matches("Релиз");

        boolean expected = false;
        boolean actual = meeting.matches("Релиз");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchingNotExistingQueryInEpic() {
        epic.matches("Пиво");

        boolean expected = false;
        boolean actual = epic.matches("Пиво");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void searchingExistingQueryInEpic() {
        epic.matches("Яйца");

        boolean expected = true;
        boolean actual = epic.matches("Яйца");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchingOnThreeTasksOfDifferentTypeInMeeting() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        todos.search("Выкатка");


        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchingOnThreeTasksOfDifferentTypeInSimpleTask() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        todos.search("Позвонить");


        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchingOnThreeTasksOfDifferentTypeInEpic() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        todos.search("Молоко");


        Task[] expected = {epic};
        Task[] actual = todos.search("Молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchingOnThreeTasksOfDifferentTypeInAllTypes() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        todos.search("о");


        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.search("о");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchingNotExistingQueryOnThreeTasksOfDifferentType() {
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        todos.search("1");


        Task[] expected = {};
        Task[] actual = todos.search("1");
        Assertions.assertArrayEquals(expected, actual);
    }


}
