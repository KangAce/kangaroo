package ink.kangaroo.wms.service;

import ink.kangaroo.wms.model.TemplateData;
import ink.kangaroo.wms.model.TemplateDataWithBLOBs;

public interface TemplateDataService {

    public TemplateData detail(Integer id);

    public Integer insert(TemplateData data);

    public Boolean update(TemplateData data);

    public TemplateData detailByInstanceId(Integer id);

}
