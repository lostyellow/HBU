package com.hyfang.healthbasedonuser.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyfang.healthbasedonuser.entity.HealthInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyfang.healthbasedonuser.utils.query.HealthInfoQuery;
import com.hyfang.healthbasedonuser.utils.vo.HealthInfoVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 健康信息 Mapper 接口
 * </p>
 *
 * @author hyfang
 * @since 2025-03-20
 */
public interface HealthInfoMapper extends BaseMapper<HealthInfo> {
    // 自定义分页查询
    IPage<HealthInfoVo> selectPageVo(IPage<HealthInfo> page, @Param("query") HealthInfoQuery query);
}
