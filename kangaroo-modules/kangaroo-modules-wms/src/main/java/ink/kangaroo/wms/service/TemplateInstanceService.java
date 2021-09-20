package ink.kangaroo.wms.service;


import ink.kangaroo.wms.model.TemplateInstance;

import java.util.List;

public interface TemplateInstanceService {

    public List<TemplateInstance> queryDefList(Integer defId, String searchName);

    public Integer insert(TemplateInstance instance);

    public Boolean update(TemplateInstance instance);

    public TemplateInstance detail(Integer id);

    public Boolean delete(TemplateInstance instance);

    public Integer copy(Integer id, Integer userId, String code);
}
