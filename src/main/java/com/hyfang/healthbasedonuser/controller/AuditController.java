package com.hyfang.healthbasedonuser.controller;

import com.hyfang.healthbasedonuser.common.Result;
import com.hyfang.healthbasedonuser.service.HealthInfoService;
import com.hyfang.healthbasedonuser.utils.dto.AuditDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/healthInfo")
@RequiredArgsConstructor
public class AuditController {
    private final HealthInfoService infoService;

    @PutMapping("/{id}/audit")
    public Result<Void> audit(@PathVariable Long id,
                              @RequestBody @Valid AuditDTO dto) {
        infoService.auditInfo(id, dto);
        return Result.success();
    }
}
