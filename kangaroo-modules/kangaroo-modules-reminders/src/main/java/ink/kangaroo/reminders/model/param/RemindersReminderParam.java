package ink.kangaroo.reminders.model.param;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 提醒事项参数
 *
 * @author kbw
 * @version 1.0
 * @date 2021/8/21 2:15
 */
@Data
public class RemindersReminderParam {

    private Long id;
    @NotNull
    private String title;
    private String notes;
    private String url;
    private Date reminderDate;
    private Date reminderTime;
    private Boolean flag = false;
    private Integer priority = 0;
    @NotNull
    private Long listId;

}
