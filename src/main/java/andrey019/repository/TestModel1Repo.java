package andrey019.repository;


import andrey019.model.dao.tests.TestModel1;

public interface TestModel1Repo extends BaseRepository<TestModel1, Long> {

    TestModel1 findByProductId(String productId);
}
