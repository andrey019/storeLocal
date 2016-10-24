package andrey019.model.json;


import java.util.Set;

public class JsonSearchResult {

    private long id;

    private String name;

    private int todoAmount;

    public JsonSearchResult() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTodoAmount() {
        return todoAmount;
    }

    public void setTodoAmount(int todoAmount) {
        this.todoAmount = todoAmount;
    }
}
