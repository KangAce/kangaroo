package ink.kangaroo.common.mongodb.listener.autoid;

import ink.kangaroo.common.mongodb.model.SequenceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.persistence.GeneratedValue;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/2 10:30
 */
@Component
public class SaveMongoEventListener  extends AbstractMongoEventListener<Object> {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SaveMongoEventListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {

        // 获取事件最初发生的对象
        final Object source = event.getSource();
        if (source != null) {
            // 使用反射工具类，实现回调接口的方法，对成员进行操作
            ReflectionUtils.doWithFields(source.getClass(), field -> {
                // 使操作的成员可访问
                ReflectionUtils.makeAccessible(field);
                // 如果是带有@AutoIncrement注解的，成员调用getter方法返回的类型是Number类型的，返回的类型都是0的(没有赋值，默认为0)
                if (field.isAnnotationPresent(GeneratedValue.class) && field.get(source) instanceof Number
                        && field.getLong(source) == 0) {
                    String collName = source.getClass().getSimpleName().substring(0, 1).toLowerCase()
                            + source.getClass().getSimpleName().substring(1);
                    // setter方法的调用，使ID成员属性，进行自增
                    field.set(source, getNextId(collName));
                }
            });
        }
    }

    /**
     * 获取下一个自增ID
     *
     * @param collName 集合名
     * @return Long
     */
    private Long getNextId(String collName) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        SequenceId seqId = mongoTemplate.findAndModify(query, update, options, SequenceId.class);
        assert seqId != null;
        return seqId.getSeqId();
    }
}
