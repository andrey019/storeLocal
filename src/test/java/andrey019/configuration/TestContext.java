package andrey019.configuration;

import andrey019.repository.ProductRepo;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    @Bean(name = "productRepoMock")
    public ProductRepo productRepo() {
        return Mockito.mock(ProductRepo.class);
    }
}
