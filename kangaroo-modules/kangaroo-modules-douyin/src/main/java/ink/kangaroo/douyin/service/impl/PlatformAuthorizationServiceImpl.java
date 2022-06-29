package ink.kangaroo.douyin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.kangaroo.common.core.constant.UserConstants;
import ink.kangaroo.common.core.utils.StringUtils;
import ink.kangaroo.common.security.utils.DictUtils;
import ink.kangaroo.douyin.domain.PlatformAuthorizationEntity;
import ink.kangaroo.douyin.mapper.PlatformAuthorizationMapper;
import ink.kangaroo.douyin.service.PlatformAuthorizationService;
import ink.kangaroo.system.api.domain.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 字典 业务层处理
 *
 * @author ruoyi
 */
@Service
public class PlatformAuthorizationServiceImpl extends ServiceImpl<PlatformAuthorizationMapper, PlatformAuthorizationEntity> implements PlatformAuthorizationService {

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        loadingDictCache();
    }

    /**
     * 根据条件分页查询字典类型
     *
     * @param platformAuthorizationEntity 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<PlatformAuthorizationEntity> selectPlatformAuthorizationList(PlatformAuthorizationEntity platformAuthorizationEntity) {
        return baseMapper.selectPlatformAuthorizationList(platformAuthorizationEntity);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<PlatformAuthorizationEntity> selectPlatformAuthorizationAll() {
        return baseMapper.selectPlatformAuthorizationAll();
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
//        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas)) {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public PlatformAuthorizationEntity selectPlatformAuthorizationById(Long dictId) {
        return baseMapper.selectPlatformAuthorizationById(dictId);
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public PlatformAuthorizationEntity selectDictTypeByType(String dictType) {
        return baseMapper.selectPlatformAuthorizationByType(dictType);
    }

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    @Override
    public void deleteDictTypeByIds(Long[] dictIds) {
        for (Long dictId : dictIds) {
            PlatformAuthorizationEntity dictType = selectPlatformAuthorizationById(dictId);
//            if (dictDataMapper.countDictDataByType(dictType.getAuthType()) > 0) {
//                throw new ServiceException(String.format("%1$s已分配,不能删除", dictType.getAuthName()));
//            }
            baseMapper.deletePlatformAuthorizationById(dictId);
//            DictUtils.removeDictCache(dictType.getAuthType());
        }
    }

    /**
     * 加载字典缓存数据
     */
    @Override
    public void loadingDictCache() {
        List<PlatformAuthorizationEntity> dictTypeList = baseMapper.selectPlatformAuthorizationAll();
        for (PlatformAuthorizationEntity dictType : dictTypeList) {
//            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType.getDictType());
//            DictUtils.setDictCache(dictType.getDictType(), dictDatas);
        }
    }

    /**
     * 清空字典缓存数据
     */
    @Override
    public void clearDictCache() {
        DictUtils.clearDictCache();
    }

    /**
     * 重置字典缓存数据
     */
    @Override
    public void resetAuthorizationache() {
        clearDictCache();
        loadingDictCache();
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(PlatformAuthorizationEntity dict) {
        int row = baseMapper.insert(dict);
//        if (row > 0) {
//            DictUtils.setDictCache(dict.getAuthType(), null);
//        }
        return row;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePlatformAuthorization(PlatformAuthorizationEntity dict) {
        PlatformAuthorizationEntity oldDict = baseMapper.selectPlatformAuthorizationById(dict.getAuthId());
//        dictDataMapper.updateDictDataType(oldDict.getDictType(), dict.getDictType());
        int row = baseMapper.updatePlatformAuthorization(dict);
        if (row > 0) {
//            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dict.getDictType());
//            DictUtils.setDictCache(dict.getDictType(), dictDatas);
        }
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(PlatformAuthorizationEntity dict) {
        Long dictId = StringUtils.isNull(dict.getAuthId()) ? -1L : dict.getAuthId();
        PlatformAuthorizationEntity dictType = baseMapper.checkPlatformAuthorizationUnique(dict.getAuthType());
        if (StringUtils.isNotNull(dictType) && dictType.getAuthId().longValue() != dictId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
