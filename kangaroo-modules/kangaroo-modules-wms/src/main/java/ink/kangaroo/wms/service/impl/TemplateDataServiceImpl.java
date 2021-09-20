package ink.kangaroo.wms.service.impl;

import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.wms.mapper.TemplateDataMapper;
import ink.kangaroo.wms.model.TemplateData;
import ink.kangaroo.wms.model.TemplateDataWithBLOBs;
import ink.kangaroo.wms.service.TemplateDataService;
import ink.kangaroo.wms.wrapper.TemplateDataQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("templateDataService")
public class TemplateDataServiceImpl implements TemplateDataService {

    @Resource
    private TemplateDataMapper templateDataMapper;
    @Override
    public TemplateData detail(Integer id) {
        return templateDataMapper.findById(id);
    }

    @Override
    public Integer insert(TemplateData data) {
        //获取当前instanceId下是否已经有数据
        TemplateData old = detailByInstanceId(data.getInstanceId());
        if (old == null) {
            return templateDataMapper.insert(data);
        } else {
            data.setId(old.getId());
            update(data);
            return old.getId();
        }
    }

    @Override
    public Boolean update(TemplateData data) {
        if (StringUtils.isEmpty(data.getId()) || StringUtils.isEmpty(data.getUserId()) || StringUtils.isEmpty(data.getInstanceId())) {
            return false;
        }
        int affect = templateDataMapper.updateById(data);
        return affect > 0;
    }

    @Override
    public TemplateData detailByInstanceId(Integer id) {
        TemplateDataQuery end = TemplateDataQuery.query().where.id().eq(id).end();
        List<TemplateData> datas = templateDataMapper.listEntity(end);
        if (datas == null || datas.size() == 0) {
            return null;
        } else {
            return datas.get(0);
        }
    }
}
