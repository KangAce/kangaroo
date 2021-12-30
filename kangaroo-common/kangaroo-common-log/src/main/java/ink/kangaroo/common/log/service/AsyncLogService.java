package ink.kangaroo.common.log.service;

import ink.kangaroo.common.core.utils.DecimalUtils;
import ink.kangaroo.system.api.RemoteLogService;
import ink.kangaroo.system.api.domain.SysOperLog;
import ink.kangaroo.common.core.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/5 14:18
 */
@Service
public class AsyncLogService {
    @Autowired
    private RemoteLogService remoteLogService;

    /**
     * 保存系统日志记录
     */
    @Async
    public void saveSysLog(SysOperLog sysOperLog) {
        remoteLogService.saveLog(sysOperLog, SecurityConstants.INNER + ":" + DecimalUtils._10_to_N(System.currentTimeMillis(), 62));
    }
}
