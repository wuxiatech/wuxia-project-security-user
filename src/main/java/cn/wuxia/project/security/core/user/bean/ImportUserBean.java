/*
* Created on :2017年10月13日
* Author     :songlin
* Change History
* Version       Date         Author           Reason
* <Ver.No>     <date>        <who modify>       <reason>
* Copyright 2014-2020 wuxia.gd.cn All right reserved.
*/
package cn.wuxia.project.security.core.user.bean;

import cn.wuxia.tools.excel.annotation.ExcelColumn;
import org.hibernate.validator.constraints.NotBlank;


public class ImportUserBean {

	@NotBlank
	@ExcelColumn(colunmIndex = 1)
	String name;

	@ExcelColumn(colunmIndex = 2)
	String title;

	@ExcelColumn(colunmIndex = 3)
	String mobile;

	@ExcelColumn(colunmIndex = 4)
	String type;

	@ExcelColumn(colunmIndex = 5)
	String goodat1;

	@ExcelColumn(colunmIndex = 6)
	String locationAt;

	@ExcelColumn(colunmIndex = 7)
	String goodat2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGoodat1() {
		return goodat1;
	}

	public void setGoodat1(String goodat1) {
		this.goodat1 = goodat1;
	}

	public String getLocationAt() {
		return locationAt;
	}

	public void setLocationAt(String locationAt) {
		this.locationAt = locationAt;
	}

	public String getGoodat2() {
		return goodat2;
	}

	public void setGoodat2(String goodat2) {
		this.goodat2 = goodat2;
	}

}
