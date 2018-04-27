package org.andy.authority.domain;

import lombok.Getter;
import lombok.Setter;
import org.andy.authority.support.BaseEntity;
import org.andy.authority.support.ResourceInfo;
import org.andy.authority.support.ResourceType;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sean
 * 2017/11/2.
 */
@Entity
@Table(name = "opt_resource")
@Getter
@Setter
@DynamicUpdate
public class Resource extends BaseEntity<String> {

    /**
     * 资源名称 如xx菜单，xx按钮
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 资源编码
     */
    @Column(name = "code", length = 50)
    private String code;

    /**
     * 是否是系统菜单
     */
    private boolean business;

    /**
     * 资源类型
     */
    @NotNull
    private ResourceType type;

    /**
     * /**
     * 父资源
     */
    @ManyToOne
    private Resource parent;

    /**
     * 序号
     */
    private int sort;

    /**
     * 子资源
     */
    @OneToMany(mappedBy = "parent")
    @OrderBy("sort ASC")
    private List<Resource> childs = new ArrayList<>();

    /**
     * 角色拥有权限的资源集合
     */
    @OneToMany(mappedBy = "resource")
    private Set<RoleResource> roleResourceSet = new HashSet<>();

    //树状图形
    public ResourceInfo roletoTree(List<String> ids) {
        ResourceInfo result = new ResourceInfo();
        BeanUtils.copyProperties(this, result);
        List<ResourceInfo> children = new ArrayList();
        for (Resource child : getChilds()) {
            if (ids.contains(child.getId())) {
                children.add(child.roletoTree(ids));
            }
        }
        result.setChildren(children);
        return result;
    }

    /**
     * 图标
     */
    private String icon;

    /**
     * 简介
     */
    @Column(name = "remark", length = 1000)
    private String remark;

    public void addChild(Resource child) {
        childs.add(child);
        child.setParent(this);
    }
}
