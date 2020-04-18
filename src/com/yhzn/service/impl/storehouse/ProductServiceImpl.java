package com.yhzn.service.impl.storehouse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.storehouse.ProductDao;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.Product;
import com.yhzn.service.storehouse.ProductService;

/**
 * 产品信息实现类
 * @author liany
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	//产品信息管理dao
	@Resource
	private ProductDao productDao;

	/**
	 * 查询产品信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<Product> queryProductList(PageBounds bounds,Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return productDao.queryProductList(bounds, parameter);
	}

	/**
	 * 删除产品信息
	 * @param id
	 */
	@Override
	public void deleteProductById(String id) {
		// TODO Auto-generated method stub
		productDao.deleteProductById(id);
	}

	/**
	 * 新增产品信息
	 * @param person
	 * @param product
	 */
	@Override
	public void insertProductInfo(Product product, User user) {
		// TODO Auto-generated method stub
		product.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
		product.setCreateName(user.getTrueName());//创建人姓名
		productDao.insertProductInfo(product);
	}

	/**
	 * 修改产品信息
	 * @param person
	 * @param product
	 */
	@Override
	public void editProductInfo(Product product, User user) {
		// TODO Auto-generated method stub
		product.setModifyName(user.getTrueName());//修改人姓名
		productDao.editProductInfo(product);
	}


	/**
	 * map查询产品信息列表
	 * @param map
	 * @return
	 */
	@Override
	public List<Product> queryProductListByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return productDao.queryProductListByMap(map);
	}
	
	/**
	 * 根据ID查询产品信息
	 * @param id
	 * @return
	 */
	public Product queryProductInfoById(String id){
		return productDao.queryProductInfoById(id);
	}
}
