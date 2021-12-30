package ink.kangaroo.douyin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.kangaroo.douyin.domain.PlatformAuthorization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname DyOpenService
 * @Description TODO
 * @Date 2021/11/29 5:39
 * @Created by Kangaroo
 */
@Mapper
public interface PlatformAuthorizationMapper extends BaseMapper<PlatformAuthorization> {
    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    public List<PlatformAuthorization> selectPlatformAuthorizationList(PlatformAuthorization dictType);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    public List<PlatformAuthorization> selectPlatformAuthorizationAll();

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    public PlatformAuthorization selectPlatformAuthorizationById(Long dictId);

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    public PlatformAuthorization selectPlatformAuthorizationByType(String dictType);

    /**
     * 通过字典ID删除字典信息
     *
     * @param dictId 字典ID
     * @return 结果
     */
    public int deletePlatformAuthorizationById(Long dictId);

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    public int deletePlatformAuthorizationByIds(Long[] dictIds);

    /**
     * 新增字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertPlatformAuthorization(PlatformAuthorization dictType);

    /**
     * 修改字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int updatePlatformAuthorization(PlatformAuthorization dictType);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    public PlatformAuthorization checkPlatformAuthorizationUnique(String dictType);

}
