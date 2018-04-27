/**
 *
 */
package org.andy.authority.support;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sean
 *
 */
@Setter
@Getter
public class ResourceInfo {

	/**
	 * 资源id
	 */
	private String id;

	/**
	 * 资源名称
	 */
	private String name;

	/**
	 * 资源编码 如user_save
	 */
	private String code;

	/**
	 * 组件的路径
	 */
	private String link;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 资源类型 0=菜单 1=按钮 2=路由
	 */
	private ResourceType type;

	/**
	 * 详情
	 */
	private String remark;

	/**
	 * 父资源
	 */
	private String parentId;

	/**
	 * 字节点
	 */
	private List<ResourceInfo> children = new ArrayList<>();

}
