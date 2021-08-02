package bookingservFunctionalTests;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(
  classes = ApplicationContext.class)
@AutoConfigureMockMvc
public class IdempotenceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Test
    public void interfaceIdempotenceTest() throws Exception {
        //  initialization  MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //  Call get  Token  Interface 
        String token = UUID.randomUUID().toString();
        //log.info(" Acquired Token strand ：{}", token);
        //  Cycle call  5  Test for the first time 
        for (int i = 1; i <= 5; i++) {
          //  log.info(" The first {} Call the test interface one time ", i);
            //  Call the verification interface and print the results 
            String result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/booking")
                    .header("token", token)
                    .accept(MediaType.APPLICATION_JSON))
                    .andReturn().getResponse().getContentAsString();
            //log.info(result);
            //  The result asserts 
            System.out.println(result);
            if (i == 0) {
                Assert.assertEquals(result, " Normal call ");
            } else {
                Assert.assertEquals(result, " Repeated calls to ");
            }
        }
    }
}
