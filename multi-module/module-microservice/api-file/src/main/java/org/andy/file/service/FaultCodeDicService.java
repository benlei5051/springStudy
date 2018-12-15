package org.andy.file.service;

import org.andy.file.dao.FaultCodeDicRepository;
import org.andy.file.repository.FaultCodeDic;
import org.andy.file.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: andy
 * @Date: 2018/4/20 18:39
 * @Description:
 */
@Service
public class FaultCodeDicService {

    @Autowired
    private FaultCodeDicRepository faultCodeDicRepository;

    private Specification<FaultCodeDic> querySpec(String faultCode, String faultType, String faultName, Integer level) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(faultCode)) {
                predicates.add(cb.like(root.get("faultCode"), "%" + faultCode + "%"));
            }
            if (StringUtils.isNotBlank(faultType)) {
                predicates.add(cb.like(root.get("faultType"), "%" + faultType + "%"));
            }
            if (StringUtils.isNotBlank(faultName)) {
                predicates.add(cb.like(root.get("faultName"), "%" + faultName + "%"));
            }
            if (null != level) {
                predicates.add(cb.equal(root.get("level"), level));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }

    public void importExcel(MultipartFile file) throws IOException {
        List<String[]> lists = ExcelUtil.readExcel(file);
        List<FaultCodeDic> faultCodeDics = lists.stream().map(this::transToFaultCodeDicPo).collect(Collectors.toList());
        faultCodeDicRepository.save(faultCodeDics);
    }

    private FaultCodeDic transToFaultCodeDicPo(String[] strs) {
        FaultCodeDic faultCodeDic = new FaultCodeDic();
        if (strs.length > 0) {
            int count = 2;
            faultCodeDic.setFaultName(strs[count++]);
            faultCodeDic.setLevel(Integer.valueOf(strs[count++]));
            faultCodeDic.setFaultCode(strs[count++]);
            faultCodeDic.setDtcHexHigh(strs[count++]);
            faultCodeDic.setDtcHexMiddle(strs[count++]);
            faultCodeDic.setDtcHexLower(strs[count++]);
            faultCodeDic.setTriggerCondition(strs[count++].replaceAll("\n", ""));
            faultCodeDic.setConfirmedTime(strs[count++]);
            faultCodeDic.setRecoverCondition(strs[count++].replaceAll("\n", ""));
            faultCodeDic.setRecoverTime(strs[count++]);
            faultCodeDic.setDetectionPeriod(strs[count++]);
            faultCodeDic.setFourteenClean("Y".equalsIgnoreCase(strs[count++]));
            faultCodeDic.setSelfClean("Y".equalsIgnoreCase(strs[count++]));
            faultCodeDic.setCleanPeriod(strs[count++]);
            faultCodeDic.setSystemImpact(strs[count++].replaceAll("\n", ""));
            faultCodeDic.setMaintainRemind(strs[count++].replaceAll("\n", ""));
            faultCodeDic.setRemarks(strs[count]);
        }
        return faultCodeDic;
    }

    public void exportToExcel(Integer page, Integer size, String faultCode, String faultType, String faultName, Integer level, HttpServletResponse resp) {
        Pageable pageable = new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, "createDate"));
        Page<FaultCodeDic> faultCodeDics = faultCodeDicRepository.findAll(querySpec(faultCode, faultType, faultName, level), pageable);
        List<FaultCodeDic> content = faultCodeDics.getContent();
        List<String[]> lists = content.stream().map(this::transToMap).collect(Collectors.toList());
        String[] columnNames = {"序号", "DTC含义", "级别", "DTC码", "高字节", "中字节", "低字节", "DTC产生条件", "DTC确认时间", "故障恢复条件", "故障恢复时间",
                "故障检测周期", "是否支持14服务清除", "是否支持自清除", "自清除周期", "故障对系统的影响", "维修建议", "标注"};
        lists.add(0, columnNames);
        ExcelUtil.createWorkBook(lists, "DTC故障库.xls", resp);
    }

    private String[] transToMap(FaultCodeDic faultCodeDic) {
        String[] sts = new String[18];
        int count = 0;
        sts[count++] = faultCodeDic.getSid();
        sts[count++] = faultCodeDic.getFaultName();
        sts[count++] = faultCodeDic.getLevel().toString();
        sts[count++] = faultCodeDic.getFaultCode();
        sts[count++] = faultCodeDic.getDtcHexHigh();
        sts[count++] = faultCodeDic.getDtcHexMiddle();
        sts[count++] = faultCodeDic.getDtcHexLower();
        sts[count++] = faultCodeDic.getTriggerCondition();
        sts[count++] = faultCodeDic.getConfirmedTime();
        sts[count++] = faultCodeDic.getRecoverCondition();
        sts[count++] = faultCodeDic.getRecoverTime();
        sts[count++] = faultCodeDic.getDetectionPeriod().toString();
        sts[count++] = faultCodeDic.getFourteenClean().toString();
        sts[count++] = faultCodeDic.getSelfClean().toString();
        sts[count++] = faultCodeDic.getCleanPeriod();
        sts[count++] = faultCodeDic.getSystemImpact();
        sts[count++] = faultCodeDic.getMaintainRemind();
        sts[count] = faultCodeDic.getRemarks();
        return sts;
    }
}
