package andrey019.model.json;


import andrey019.model.dao.tests.TestModel1;

import java.util.List;

public class JsonTestModel1 {

    private List<TestModel1> records;

    public List<TestModel1> getRecords() {
        return records;
    }

    public void setRecords(List<TestModel1> records) {
        this.records = records;
    }
}
