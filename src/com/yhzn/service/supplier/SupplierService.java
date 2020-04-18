package com.yhzn.service.supplier;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.supplier.SupplierDao;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.supplier.SupplierModel;

@Service
public class SupplierService {
	@Autowired
	private SupplierDao supplierDao;

	/**
	 * 查询所有的供应商 按条件查询 模糊查询
	 * 
	 * @param bounds
	 * @param name
	 * @param userName
	 * @param area
	 * @return
	 */
	public List<SupplierModel> findAll(PageBounds bounds, String name, String userName, String area) {
		List<SupplierModel> list = supplierDao.findAll(bounds, name, userName, area);
		return list;
	}

	/**
	 * 增加供应商
	 * 
	 * @param supplier
	 * @return
	 */
	public boolean addsupplier(SupplierModel supplier) {

		try {
			supplier.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			supplierDao.add(supplier);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 修改供应商
	 * 
	 * @param supplier
	 * @return
	 */
	public boolean updatesupplier(SupplierModel supplier) {
		try {
			supplierDao.update(supplier);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * 根据id查询供应商
	 * 
	 * @param id
	 * @return
	 */
	public SupplierModel findById(String id) {

		return supplierDao.findById(id);
	}

	/**
	 * 逻辑删除供应商
	 * 
	 * @param ids
	 * @return
	 */
	public boolean deletesupplier(String[] ids) {

		try {
			supplierDao.delete(ids);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public List<SysDict> supplierCombobox() {
		// TODO Auto-generated method stub
		return supplierDao.supplierCombobox();
	}
}
