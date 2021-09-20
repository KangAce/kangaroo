package ink.kangaroo.wms.service.impl;

import cn.org.atool.fluent.mybatis.ifs.Ifs;
import ink.kangaroo.wms.mapper.TemplateInstanceMapper;
import ink.kangaroo.wms.model.TemplateInstance;
import ink.kangaroo.wms.service.TemplateInstanceService;
import ink.kangaroo.wms.wrapper.TemplateInstanceQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
@Service("templateInstanceService")
public class TemplateInstanceServiceImpl implements TemplateInstanceService {

    @Resource
    private TemplateInstanceMapper templateInstanceMapper;

    @Override
    public List<TemplateInstance> queryDefList(Integer defId, String searchName) {
        TemplateInstanceQuery query = TemplateInstanceQuery.query();
        query.where.defId().eq(defId);
        if (!StringUtils.isEmpty(searchName)) {
            query.where.name().like("%" + searchName + "%");
        }
        query.where.isDeleted().eq((byte) 0);
        return templateInstanceMapper.listEntity(query);
    }

    @Override
    public Integer insert(TemplateInstance instance) {
        int affect = templateInstanceMapper.insert(instance);

        if (affect > 0) {
            List<TemplateInstance> insert = templateInstanceMapper.listEntity(TemplateInstanceQuery.query().where.code().eq(instance.getCode()).end());
            if (insert.size() > 0) {
                return insert.get(0).getId();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public Boolean update(TemplateInstance instance) {
        if (StringUtils.isEmpty(instance.getId()) || StringUtils.isEmpty(instance.getUserId())) {
            return false;
        }
        instance.setUpdated((int) (System.currentTimeMillis() * 0.001));
        int affect = templateInstanceMapper.updateById(instance);
        return affect > 0;
    }

    @Override
    public TemplateInstance detail(Integer id) {
        return templateInstanceMapper.findById(id);
    }

    @Override
    public Boolean delete(TemplateInstance instance) {
        TemplateInstance temp = new TemplateInstance();
        temp.setIsDeleted((byte) 1);
        temp.setUpdated((int) (System.currentTimeMillis() * 0.001));
        temp.setUserId(instance.getUserId());
        temp.setId(instance.getId());
        int affect = templateInstanceMapper.updateById(temp);
        return affect > 0;
    }

    @Override
    public Integer copy(Integer id, Integer userId, String code) {
        //获取instance详细信息
        TemplateInstance instance = templateInstanceMapper.findById(id);

        if (instance == null || instance.getIsDeleted().equals(1)) {
            return 0;
        }

        Integer now = (int) (System.currentTimeMillis() * 0.001);
        TemplateInstance temp = new TemplateInstance();
        temp.setUserId(userId);
        temp.setUpdated(now);
        temp.setCreated(now);
//        temp.setJsText(instance.getJsText());
//        temp.setPhpText(instance.getPhpText());
        temp.setCode(code);
        temp.setDefId(instance.getDefId());
        temp.setModuleId(instance.getModuleId());
        temp.setName(instance.getName());
        temp.setType(instance.getType());
        temp.setIsDeleted((byte) 0);
        temp.setIsPublish((byte) 0);
        return insert(temp);
    }
}
