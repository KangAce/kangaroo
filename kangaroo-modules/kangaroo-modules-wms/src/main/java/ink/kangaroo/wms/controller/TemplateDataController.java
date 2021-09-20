package ink.kangaroo.wms.controller;

import ink.kangaroo.wms.model.TemplateData;
import ink.kangaroo.wms.service.TemplateDataService;
import ink.kangaroo.wms.util.PrintUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/data")
public class TemplateDataController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TemplateDataService templateDataService;

    @RequestMapping("/update/insert/{instanceid}")
    @ResponseBody
    public Map<String, String> insert(@PathVariable Integer instanceid, HttpServletRequest request) {
        Map<String, String> result = new HashMap<String, String>();

        Long workId = 1L;
        Integer now = (int) (System.currentTimeMillis() * 0.001);
        String dataText = request.getParameter("data");
        String htmlText = request.getParameter("htmlText");

        //特殊字符处理
        dataText = PrintUtil.specialChar(dataText);
        htmlText = PrintUtil.specialChar(htmlText);

        TemplateData data = new TemplateData();
        data.setUserId(workId);
        data.setData(dataText);
        data.setHtmlText(htmlText);
        data.setInstanceId(instanceid);
        Integer insertId = templateDataService.insert(data);

        //日志记录
        logger.info("[{}@workId]:{} (TemplateData) id[{}]", workId, "insert", insertId);

        if (insertId > 0) {
            result.put("status", "true");
            result.put("id", insertId.toString());
        } else {
            result.put("status", "false");
        }
        return result;
    }

    @RequestMapping("/update/update/{dataid}")
    @ResponseBody
    public Map<String, String> update(@PathVariable Integer dataid, HttpServletRequest request) {
        Map<String, String> result = new HashMap<String, String>();

        Long workId = 1L;
        Integer now = (int) (System.currentTimeMillis() * 0.001);
        String dataText = request.getParameter("data");
        String htmlText = request.getParameter("htmlText");
        String instanceId = request.getParameter("instanceId");

        //特殊字符处理
        dataText = PrintUtil.specialChar(dataText);
        htmlText = PrintUtil.specialChar(htmlText);

        TemplateData data = new TemplateData();
        data.setUserId(workId);
        data.setData(dataText);
        data.setHtmlText(htmlText);
        data.setId(dataid);
        data.setInstanceId(Integer.parseInt(instanceId));
        Boolean r = templateDataService.update(data);

        logger.info("[{}@workId]:{} (TemplateData) id[{}]", workId, "update", dataid);

        result.put("status", r.toString());
        return result;
    }
}
