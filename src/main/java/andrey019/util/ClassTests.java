package andrey019.util;


import andrey019.model.dao.Motherboard;
import andrey019.model.dao.Product;

public class ClassTests {

    public static void main(String[] args) {
        Product product = new Product();
        System.out.println(product.getClass());
        product = new Motherboard();
        System.out.println(product.getClass());
    }
}
