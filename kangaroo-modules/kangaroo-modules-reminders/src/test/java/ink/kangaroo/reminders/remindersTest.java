package ink.kangaroo.reminders;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/20 17:29
 */
@SpringBootTest
@Rollback(value = true)
@Transactional
public class remindersTest {

    public final static String SERVICE_UUID = UUID.randomUUID().toString().replace("-", "");
    @Test
    void contextLoad(){
        System.out.println(SERVICE_UUID);
    }
}
