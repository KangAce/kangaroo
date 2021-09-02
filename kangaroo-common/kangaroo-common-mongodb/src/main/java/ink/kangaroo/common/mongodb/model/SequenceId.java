package ink.kangaroo.common.mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/2 10:32
 */
@Data
@Document(collection = "sequence")
public class SequenceId {

    @Id
    private String id;

    @Field("seq_id")
    private long seqId;

    @Field("coll_name")
    private String collName;

}