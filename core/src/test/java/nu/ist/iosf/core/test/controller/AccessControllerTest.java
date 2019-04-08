package nu.ist.iosf.core.test.controller;

import nu.ist.iosf.core.service.AccessService;
import nu.ist.iosf.core.test.config.TestConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class AccessControllerTest {

    @Autowired private AccessService accessService;

    @BeforeAll
    public static void setUp() {
        System.out.println("TESTING BEGUNS: AccessControllerTest");
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("TESTING ENDS: AccessControllerTest");
    }

    @Test
    @DisplayName("Testing access service")
    public void testBeans() {
        Assertions.assertEquals(1, accessService.getOne());
    }

}
