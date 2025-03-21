package com.hyfang.healthbasedonuser.controller;

import com.hyfang.healthbasedonuser.common.PageResultBean;
import com.hyfang.healthbasedonuser.common.Result;
import com.hyfang.healthbasedonuser.service.HealthInfoService;
import com.hyfang.healthbasedonuser.utils.dto.HealthInfoDTO;
import com.hyfang.healthbasedonuser.utils.query.HealthInfoQuery;
import com.hyfang.healthbasedonuser.utils.vo.HealthInfoVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hyfang
 * @since 2025-03-20
 */
@Controller
@RequestMapping("/healthInfo")
@RequiredArgsConstructor
public class HealthInfoController {
    private final HealthInfoService infoService;

    @PostMapping
    public Result<Void> publish(@RequestBody @Valid HealthInfoDTO dto,
                                @RequestAttribute Integer userId) {
        infoService.publishInfo(dto, userId);
        return Result.success();
    }

    @GetMapping
    public Result<PageResultBean<HealthInfoVo>> getPage(HealthInfoQuery query) {
        return Result.success(infoService.getInfoPage(query));
    }
}
