package andrey019.controller;

import andrey019.configuration.AppConfig;
import andrey019.configuration.TestContext;
import andrey019.model.dao.Product;
import andrey019.repository.ProductRepo;
import andrey019.util.Json;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestContext.class})
@WebAppConfiguration
public class MainControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    @Qualifier("productRepoMock")
    private ProductRepo productRepoMock;

    @Before
    public void setUp() {
        Mockito.reset(productRepoMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getTestModel1Page() throws Exception {
        MvcResult result = mockMvc.perform(get("/testModel1"))
                .andExpect(status().isOk())
                //.andExpect(content().string("\"testModel1 page\""))
                .andReturn();
    }

    @Test
    public void uploadProduct() throws Exception {
        Product product = new Product();
        product.setCode(55L);

        Product response = new Product();
        product.setCode(5L);

        when(productRepoMock.save(isA(Product.class))).thenReturn(response);
        mockMvc.perform(post("/uploadProduct")
                .contentType(Json.APPLICATION_JSON_UTF8)
                .content(Json.objectToJsonBytes(product))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(Json.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code", is(5)));
    }
}
