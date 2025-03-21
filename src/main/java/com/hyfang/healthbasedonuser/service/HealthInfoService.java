package com.hyfang.healthbasedonuser.service;

import com.hyfang.healthbasedonuser.common.PageResultBean;
import com.hyfang.healthbasedonuser.entity.HealthInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyfang.healthbasedonuser.utils.dto.AuditDTO;
import com.hyfang.healthbasedonuser.utils.dto.HealthInfoDTO;
import com.hyfang.healthbasedonuser.utils.query.HealthInfoQuery;
import com.hyfang.healthbasedonuser.utils.vo.HealthInfoVo;

/**
 * <p>
 * 健康信息 服务类
 * </p>
 *
 * @author hyfang
 * @since 2025-03-20
 */
public interface HealthInfoService extends IService<HealthInfo> {
    void publishInfo(HealthInfoDTO dto, Integer userId);
    void auditInfo(Long infoId, AuditDTO auditDTO);
    PageResultBean<HealthInfoVo> getInfoPage(HealthInfoQuery query);
}
