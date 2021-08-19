package ink.kangaroo.pixiv;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/15 21:59
 */
@SpringBootTest
@Rollback(value = true)
@Transactional
public class KangarooPixivApplicationTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    void contextLoads() {
    }
}
