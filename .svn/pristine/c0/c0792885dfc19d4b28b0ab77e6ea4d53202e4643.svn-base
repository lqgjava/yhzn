package com.yhzn.dao.supplier;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.supplier.SupplierModel;

@Mapper
public interface SupplierDao {
	/**
	 * 查询所有和模糊查询
	 * 
	 * @param name
	 * @param userName
	 * @param area
	 * @return
	 */
	public List<SupplierModel> findAll(PageBounds bounds,@Param("name") String name, @Param("userName") String userName,
			@Param("area") String area);

	/**
	 * 添加供应商
	 * 
	 * @param supplier
	 */
	public void add(SupplierModel supplier);

	/**
	 * 修改
	 * 
	 * @param supplier
	 */
	public void update(SupplierModel supplier);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public SupplierModel findById(@Param("id") String id);

	/**
	 * 删除供应商
	 * @param ids
	 */
	public void delete(@Param("ids") String[] ids);

	public List<SysDict> supplierCombobox();
}
