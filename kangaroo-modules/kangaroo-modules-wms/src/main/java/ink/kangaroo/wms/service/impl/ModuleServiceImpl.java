package ink.kangaroo.wms.service.impl;

import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.wms.mapper.ModuleMapper;
import ink.kangaroo.wms.model.Module;
import ink.kangaroo.wms.service.ModuleService;
import ink.kangaroo.wms.wrapper.ModuleQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {
    @Resource
    ModuleMapper moduleMapper;

    @Override
    public Boolean insert(Module mod) {
        int affect = moduleMapper.insert(mod);
        return affect > 0;
    }

    @Override
    public Boolean delete(Module mod) {

        if (StringUtils.isEmpty(mod.getId()) || StringUtils.isEmpty(mod.getUserId())) {
            return false;
        }
        Module temp = new Module();
        temp.setId(mod.getId());
        temp.setIsDeleted((byte) 1);
        temp.setUpdated((int) (System.currentTimeMillis() * 0.001));
        temp.setUserId(mod.getUserId());
        int affect = moduleMapper.updateById(temp);
//        int affect = moduleMapper.updateBy(moduleUpdate.set.isDeleted().is((byte) 1).updated().is((int) (System.currentTimeMillis() * 0.001)).userId().is(mod.getUserId()).end().where.id().eq(mod.getId()).end());
        return affect > 0;
    }

    @Override
    public Boolean update(Module mod) {
        if (StringUtils.isEmpty(mod.getId()) || StringUtils.isEmpty(mod.getUserId())) {
            return false;
        }
        int affect = moduleMapper.updateById(mod);
        return affect > 0;
    }

    @Override
    public Module detail(Integer id) {
        return moduleMapper.findById(id);
    }

    @Override
    public List<Module> queryAll(Module mod) {
        ModuleQuery query = ModuleQuery.query();
        if (!StringUtils.isEmpty(mod.getParentId())) {
            query.where.parentId().eq(mod.getParentId());
        }
        if (!StringUtils.isEmpty(mod.getName())) {
            query.where.name().like("%" + mod.getName() + "%");
        }
        if (!StringUtils.isEmpty(mod.getId())) {
            query.where.id().eq(mod.getId());
        }
        query.where.isDeleted().eq((byte) 0);
        return moduleMapper.listEntity(query);
    }

    @Override
    public Integer queryTotal(Module mod) {
        ModuleQuery query = ModuleQuery.query();
        if (!StringUtils.isEmpty(mod.getParentId())) {
            query.where.parentId().eq(mod.getParentId());
        }
        if (!StringUtils.isEmpty(mod.getName())) {
            query.where.name().like("%" + mod.getName() + "%");
        }
        if (!StringUtils.isEmpty(mod.getId())) {
            query.where.id().eq(mod.getId());
        }
        query.where.isDeleted().eq((byte) 0);
        return moduleMapper.count(query);
    }
}
