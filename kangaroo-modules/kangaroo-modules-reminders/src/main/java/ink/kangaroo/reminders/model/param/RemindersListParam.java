package ink.kangaroo.reminders.model.param;

import lombok.Data;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/21 2:15
 */
@Data
public class RemindersListParam {
    private Long id;

    private String listName;

    private Long iconId;

    private Long groupId;

}
