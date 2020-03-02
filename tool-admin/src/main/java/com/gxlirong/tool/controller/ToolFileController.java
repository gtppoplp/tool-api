package com.gxlirong.tool.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.gxlirong.tool.common.api.CommonResult;
import com.gxlirong.tool.config.FileUploadConfig;
import com.gxlirong.tool.domain.vo.ToolFileUploadVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Api(tags = "组件功能-上传文件")
@RestController
@RequestMapping("/module/file")
public class ToolFileController {
    @Autowired
    private FileUploadConfig fileUploadConfig;

    @PostMapping("/upload")
    @ResponseBody
    public CommonResult<ToolFileUploadVo> fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                return CommonResult.failed("上传失败，请选择文件");
            }
            String fileName = file.getOriginalFilename();
            if (fileName == null) {
                return CommonResult.failed("上传失败，请选择文件");
            }
            String saveFileName = IdWorker.getTimeId() + fileName.substring(fileName.lastIndexOf("."));
            String path = fileUploadConfig.getFilePath() + saveFileName;
            File dest = new File(path);
            if (!dest.getParentFile().exists()) {
                if (!dest.getParentFile().mkdir()) {
                    return CommonResult.failed("上传失败，文件创建失败");
                }
            }
            file.transferTo(dest);
            ToolFileUploadVo toolFileUploadVo = new ToolFileUploadVo();
            toolFileUploadVo.setPath(path);
            return CommonResult.success(toolFileUploadVo);
        } catch (IOException e) {
            return CommonResult.failed("上传失败" + e.getMessage());
        }
    }
}
