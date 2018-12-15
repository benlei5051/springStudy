package org.andy.file.controller;

import lombok.extern.slf4j.Slf4j;
import org.andy.file.service.FaultCodeDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: andy
 * @Date: 2018/4/20 18:22
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/faultcode/v1")
public class FaultCodeDicController {

    @Autowired
    private FaultCodeDicService faultCodeDicService;

    /**
     * 导出文件
     * @param faultCode
     * @param faultType
     * @param faultName
     * @param level
     * @param page
     * @param size
     * @param response
     */
    @GetMapping(path = "/batchExportFaultCode")
    public void exportFaultCodeToExcel(@RequestParam(required = false) String faultCode,
                                       @RequestParam(required = false) String faultType,
                                       @RequestParam(required = false) String faultName,
                                       @RequestParam(required = false) Integer level,
                                       @RequestParam Integer page,
                                       @RequestParam Integer size,
                                       HttpServletResponse response) {
        try {
            log.info("Enter FaultCodeDicController.exportFaultCodeToExcel()");
            faultCodeDicService.exportToExcel(page, size, faultCode, faultType, faultName, level, response);
        } catch (Exception e) {
            log.error("can't export fault code record!", e);
        }
    }

    /**
     * 导入文件
     * @param file
     * @throws IOException
     */
    @PostMapping("/batchImportFaultCode")
    public void faultCodeBatchImport(
            @RequestParam("file") MultipartFile file) throws IOException {
        log.info("Enter FaultCodeDicController.deviceBatchInventoryIn()");
        faultCodeDicService.importExcel(file);
    }
}
