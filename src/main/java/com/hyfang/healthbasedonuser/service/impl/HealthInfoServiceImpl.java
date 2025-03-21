package com.hyfang.healthbasedonuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyfang.healthbasedonuser.common.PageResultBean;
import com.hyfang.healthbasedonuser.entity.HealthCategory;
import com.hyfang.healthbasedonuser.entity.HealthInfo;
import com.hyfang.healthbasedonuser.entity.User;
import com.hyfang.healthbasedonuser.mapper.HealthCategoryMapper;
import com.hyfang.healthbasedonuser.mapper.HealthInfoMapper;
import com.hyfang.healthbasedonuser.mapper.UserMapper;
import com.hyfang.healthbasedonuser.service.HealthInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyfang.healthbasedonuser.utils.SensitiveWordFilter;
import com.hyfang.healthbasedonuser.utils.dto.AuditDTO;
import com.hyfang.healthbasedonuser.utils.dto.HealthInfoDTO;
import com.hyfang.healthbasedonuser.utils.query.HealthInfoQuery;
import com.hyfang.healthbasedonuser.utils.vo.HealthInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 健康信息 服务实现类
 * </p>
 *
 * @author hyfang
 * @since 2025-03-20
 */
@Service
@RequiredArgsConstructor
public class HealthInfoServiceImpl extends ServiceImpl<HealthInfoMapper, HealthInfo> implements HealthInfoService {
    private final HealthInfoMapper infoMapper;
    private final HealthCategoryMapper categoryMapper;
    private final UserMapper userMapper;
    private final SensitiveWordFilter wordFilter;

    @Override
    @Transactional
    public void publishInfo(HealthInfoDTO dto, Integer userId) {
        // 验证用户存在
//        if (!userMapper.existsById(userId)) {
        if (!userMapper.exists(new QueryWrapper<User>().eq("id", userId))) {
            throw new RuntimeException("用户不存在");
        }

        // 验证分类存在
//        if (!categoryMapper.existsById(dto.getCategoryId())) {
        if (!categoryMapper.exists(new QueryWrapper<HealthCategory>().eq("id", dto.getCategoryId()))) {
            throw new RuntimeException("分类不存在");
        }

        // 敏感词过滤
        String filteredContent = wordFilter.filter(dto.getContent());

        HealthInfo info = new HealthInfo();
        BeanUtils.copyProperties(dto, info);
        info.setAuthorId(userId);
        info.setContent(filteredContent);
        info.setAuditStatus((byte) (needManualAudit(filteredContent) ? 0 : 1));

        infoMapper.insert(info);
    }

    @Override
    public void auditInfo(Long infoId, AuditDTO auditDTO) {
        HealthInfo info = infoMapper.selectById(infoId);
        if (info == null) {
            throw new RuntimeException("信息不存在");
        }

        info.setAuditStatus(Byte.valueOf(String.valueOf(auditDTO.getStatus())));
        info.setAuditRemark(auditDTO.getRemark());
        info.setUpdateTime(LocalDateTime.now());
        infoMapper.updateById(info);
    }

    @Override
    public PageResultBean<HealthInfoVo> getInfoPage(HealthInfoQuery query) {
        Page<HealthInfo> page = new Page<>(query.getPage(), query.getSize());
        IPage<HealthInfoVo> result = infoMapper.selectPageVo(page, query);
        return new PageResultBean<>(result.getTotal(), result.getRecords());
    }

    private boolean needManualAudit(String content) {
        // 实现自动审核逻辑，例如：
        return wordFilter.containsSensitiveWord(content)
                || content.length() > 5000;
    }
}
