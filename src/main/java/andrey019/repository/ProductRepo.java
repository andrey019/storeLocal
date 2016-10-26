package andrey019.repository;


import andrey019.model.dao.Product;

public interface ProductRepo extends BaseRepository<Product, Long> {

    Product findByCode(long code);
}
