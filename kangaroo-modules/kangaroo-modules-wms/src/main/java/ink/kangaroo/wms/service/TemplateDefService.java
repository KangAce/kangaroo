package ink.kangaroo.wms.service;

import ink.kangaroo.wms.model.TemplateDef;

import java.util.List;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/3 18:25
 */
public interface TemplateDefService {
    public List<TemplateDef> queryModuleList(Integer modId, String name);

    public Boolean insert(TemplateDef def);

    public Boolean update(TemplateDef def);

    public TemplateDef detail(Integer id);

    public Boolean delete(TemplateDef def);

    public List<TemplateDef> queryByIds(List<Integer> ids);
}
