package ink.kangaroo.job.controller;

import ink.kangaroo.common.core.constant.Constants;
import ink.kangaroo.common.core.exception.job.TaskException;
import ink.kangaroo.common.core.utils.ExcelUtil;
import ink.kangaroo.common.core.utils.SecurityUtils;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.common.core.web.controller.BaseController;
import ink.kangaroo.common.core.web.domain.AjaxResult;
import ink.kangaroo.common.core.web.page.TableDataInfo;
import ink.kangaroo.common.log.annotation.Log;
import ink.kangaroo.common.log.enums.BusinessType;
import ink.kangaroo.job.domain.SysJob;
import ink.kangaroo.job.service.ISysJobService;
import ink.kangaroo.job.uitl.CronUtils;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/8/5 15:48
 */
@RestController
@RequestMapping("/job")
public class SysJobController extends BaseController {

    private final ISysJobService jobService;

    public SysJobController(ISysJobService jobService) {
        this.jobService = jobService;
    }

    /**
     * 查询定时任务列表
     */
//    @PreAuthorize(hasPermi = "monitor:job:list")
    @GetMapping("/list")
    public TableDataInfo list(SysJob sysJob) {
        startPage();
        List<SysJob> list = jobService.selectJobList(sysJob);
        return getDataTable(list);
    }

    /**
     * 导出定时任务列表
     */
//    @PreAuthorize(hasPermi = "monitor:job:export")
    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJob sysJob) throws IOException {
        List<SysJob> list = jobService.selectJobList(sysJob);
        ExcelUtil<SysJob> util = new ExcelUtil<>(SysJob.class);
        util.exportExcel(response, list, "定时任务");
    }

    /**
     * 获取定时任务详细信息
     */
//    @PreAuthorize(hasPermi = "monitor:job:query")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId) {
        return AjaxResult.createAjaxResult(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */
//    @PreAuthorize(hasPermi = "monitor:job:add")
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return error("新增任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi://'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)//'调用");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_LDAP)) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap://'调用");
        }
        job.setCreateBy(SecurityUtils.getUsername());
        return toAjax(jobService.insertJob(job));
    }

    /**
     * 修改定时任务
     */
//    @PreAuthorize(hasPermi = "monitor:job:edit")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return error("修改任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi://'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)//'调用");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_LDAP)) {
            return error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap://'调用");
        }
        job.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(jobService.updateJob(job));
    }

    /**
     * 定时任务状态修改
     */
//    @PreAuthorize(hasPermi = "monitor:job:changeStatus")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysJob job) throws SchedulerException {
        SysJob newJob = jobService.selectJobById(job.getJobId());
        newJob.setStatus(job.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * 定时任务立即执行一次
     */
//    @PreAuthorize(hasPermi = "monitor:job:changeStatus")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/run")
    public AjaxResult run(@RequestBody SysJob job) throws SchedulerException {
        jobService.run(job);
        return AjaxResult.success();
    }

    /**
     * 删除定时任务
     */
//    @PreAuthorize(hasPermi = "monitor:job:remove")
    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds) throws SchedulerException {
        jobService.deleteJobByIds(jobIds);
        return AjaxResult.success();
    }
}
