package com.qugenx.sendmail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("qa")
@RunWith(SpringRunner.class)
class SpringBootSendEmailApplicationTests {

    @Test
    void contextLoads() {
    }

}
