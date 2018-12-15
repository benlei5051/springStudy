package org.andy.file.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.andy.file.repository.CommonEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: andy
 * @Date: 2018/4/20 15:57
 * @Description:
 */
@Entity
@Table(name = "alarm_fault_code_dic")
@Getter
@Setter
@ToString
@DynamicUpdate
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "del_flag = 0")
public class FaultCodeDic extends CommonEntity implements Serializable {

    /**
     * 故障编码
     */
    @Column(name = "fault_code", length = 50)
    private String faultCode;

    /**
     * 故障名称
     */
    @Column(name = "fault_name", length = 32)
    private String faultName;

    /**
     * 故障描述
     */
    @Column(name = "fault_desc")
    private String faultDesc;

    /**
     * 故障类型
     */
    @Column(name = "fault_type", length = 32)
    private String faultType;

    /**
     * 故障严重等级
     * 1级
     * 2级
     * 3级
     */
    @Column(name = "level")
    private Integer level;

    /**
     * 维修建议
     */
    @Column(name = "maintain_remind")
    private String maintainRemind;


    /**
     * DTC数据HEX高字节
     */
    @Column(name = "dtc_Hex_high", length = 2)
    private String dtcHexHigh;

    /**
     * DTC数据HEX中字节
     */
    @Column(name = "dtc_Hex_middle", length = 2)
    private String dtcHexMiddle;

    /**
     * DTC数据HEX低字节
     */
    @Column(name = "dtc_Hex_lower", length = 2)
    private String dtcHexLower;

    /**
     * DTC产生条件
     */
    @Column(name = "trigger_condition")
    private String triggerCondition;

    /**
     * DTC确认时间
     */
    @Column(name = "confirmed_time")
    private String confirmedTime;

    /**
     * DTC恢复条件
     */
    @Column(name = "recover_condition")
    private String recoverCondition;

    /**
     * 故障恢复时间
     */
    @Column(name = "recover_time")
    private String recoverTime;

    /**
     * 检测周期(ms)
     */
    @Column(name = "detection_period")
    private String detectionPeriod;

    /**
     * 是否支持14服务清除
     */
    @Column(name = "fourteen_clean")
    private Boolean fourteenClean;

    /**
     * 是否支持自我清除
     */
    @Column(name = "self_clean")
    private Boolean selfClean;

    /**
     * 自清除周期
     */
    @Column(name = "clean_period")
    private String CleanPeriod;

    /**
     * 故障对系统的影响
     */
    @Column(name = "system_impact")
    private String systemImpact;

    /**
     * 报警开关
     */
    @Column(name = "active")
    private Boolean active = true;


}


