package ink.kangaroo.wms.service;

import java.util.List;import ink.kangaroo.wms.model.Module;

/**
 * @author kbw
 * @version 1.0
 * @date 2021/9/3 18:26
 */
public interface ModuleService {

    public Boolean insert(Module mod);

    public Boolean delete(Module mod);

    public Boolean update(Module mod);

    public Module detail(Integer id);

    public List<Module> queryAll(Module mod);

    public Integer queryTotal(Module mod);

}
