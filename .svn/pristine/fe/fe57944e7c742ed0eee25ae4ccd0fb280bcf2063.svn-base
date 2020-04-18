package com.yhzn.dao.storehouse;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.storehouse.Product;

/**
 * 产品信息DAO
 * @author liany
 *
 */
public interface ProductDao {
	
	/**
	 * 查询产品信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	List<Product> queryProductList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 删除产品信息
	 * @param id
	 */
	public void deleteProductById(String id);
	
	/**
	 * 新增产品信息
	 * @param person
	 * @param product
	 */
	public void insertProductInfo(Product product);
	
	/**
	 * 修改产品信息
	 * @param person
	 * @param product
	 */
	public void editProductInfo(Product product);
	
	/**
	 * map查询产品信息列表
	 * @param map
	 * @return
	 */
	public List<Product> queryProductListByMap( Map<String, Object> map);
	
	/**
	 * 根据ID查询产品信息
	 * @param id
	 * @return
	 */
	public Product queryProductInfoById(String id);
}
