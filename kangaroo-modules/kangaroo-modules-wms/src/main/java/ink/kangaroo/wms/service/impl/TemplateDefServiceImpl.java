package ink.kangaroo.wms.service.impl;

import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.wms.mapper.TemplateDefMapper;
import ink.kangaroo.wms.model.TemplateDef;
import ink.kangaroo.wms.service.TemplateDefService;
import ink.kangaroo.wms.wrapper.TemplateDefQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("templateDefService")
public class TemplateDefServiceImpl implements TemplateDefService {
    @Resource
    private TemplateDefMapper templateDefMapper;

    @Override
    public List<TemplateDef> queryModuleList(Integer modId, String name) {
        TemplateDefQuery query = TemplateDefQuery.query();
        query.where().moduleId().eq(modId);
        if (!StringUtils.isEmpty(name)) {
            query.where.name().like("%" + name + "%");
        }
        query.where.isDeleted().eq((byte) 0);

        return templateDefMapper.listEntity(query);
    }

    @Override
    public Boolean insert(TemplateDef def) {
        int affect = templateDefMapper.insert(def);
        return affect > 0;
    }

    @Override
    public Boolean update(TemplateDef def) {
        if (StringUtils.isEmpty(def.getId()) || StringUtils.isEmpty(def.getUserId())) {
            return false;
        }
        def.setUpdated((int) (System.currentTimeMillis() * 0.001));
        int affect = templateDefMapper.updateById(def);
        return affect > 0;
    }

    @Override
    public TemplateDef detail(Integer id) {
        return templateDefMapper.findById(id);
    }

    @Override
    public Boolean delete(TemplateDef def) {
        def.setIsDeleted((byte) 1);
        return update(def);
    }

    @Override
    public List<TemplateDef> queryByIds(List<Integer> ids) {
        if (ids == null || ids.size() == 0) {
            return null;
        }
        ;
        return templateDefMapper.listEntity(TemplateDefQuery.query().where.id().in(ids).isDeleted().eq((byte) 0).end());
    }
}
