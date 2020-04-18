package com.yhzn.service.impl.storehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.storehouse.EntryStockDao;
import com.yhzn.dao.storehouse.OutStockDao;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.EntryStock;
import com.yhzn.model.storehouse.OutBillModel;
import com.yhzn.model.storehouse.OutStock;
import com.yhzn.model.storehouse.OutStockModel;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.model.storehouse.Purchase;
import com.yhzn.service.common.SysSequenceService;
import com.yhzn.service.storehouse.OutStockService;

/**
 * 出库管理实现类
 * @author liany
 *
 */
@Service
public class OutStockServiceImpl implements OutStockService {

	//出库管理dao
	@Resource
	private OutStockDao outStockDao;
	@Autowired
	private SysSequenceService sysSequenceService;
	
	/**
	 * 出库清单信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<OutStock> queryOutStockList(PageBounds bounds, Map<String, Object> parameter){
		return outStockDao.queryOutStockList(bounds, parameter);
	}
	
	/**
	 * 删除出库清单信息
	 * @param id
	 */
	public void deleteOutStockById(String id){
		outStockDao.deleteOutStockById(id);
	}
	
	/**
	 * 新增出库清单信息
	 * @param entryStock
	 * @param user
	 * @return
	 */
	public void insertOutStock(OutStock outStock,User user){
		outStock.setOutNo("CK"+sysSequenceService.getNextNo("OUT_STOCK", user));
		outStock.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
		outStock.setCreateName(user.getTrueName());//创建人姓名
		
		outStockDao.insertOutStock(outStock);
	}
	
	/**
	 * 修改出库清单信息
	 * @param entryStock
	 * @param user
	 * @return
	 */
	public void editOutStock(OutStock outStock,User user){
		// TODO Auto-generated method stub
		outStock.setModifyName(user.getTrueName());//修改人姓名
						
		outStockDao.editOutStock(outStock);
	}
	
	/**
	 * 根据ID查询出库清单信息
	 * @param id
	 */
	public OutStock queryOutStockById(String id){
		return outStockDao.queryOutStockById(id);
	}
	
	/**
	 * 查询出库清单产品详细信息
	 * @param id
	 * @return
	 */
	public List<OutBillModel> queryOutStockBillList(String id){
		return outStockDao.queryOutStockBillList(id);
	}
	
	/**
	 * 新增出库清单产品详细信息
	 * @param map
	 * @param list
	 */
	public void addOutStockBill(List<OutBillModel> list,Map map,User user){
		
		OutStock outStock = new OutStock();
		if(map.get("projNo")!=null && map.get("projNo")!="") {//当项目编号不为空时，项目编号就是出库编号
			outStock.setOutNo(map.get("projNo").toString());
		}else {
			outStock.setOutNo("CK"+sysSequenceService.getNextNo("OUTBILL", user));//出库编号
		}
		outStock.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
		outStock.setCreateName(user.getTrueName());//创建人姓名
		outStock.setUserId(user.getId());//创建人id
		outStock.setStatus(map.get("status").toString());//清单状态
		outStock.setOutStatus("0");//出库状态默认 未出库
		outStock.setProjNo(map.get("projNo").toString());
		outStock.setCompanyName(map.get("companyName").toString());
		outStockDao.insertOutStock(outStock);
		
		if(list!=null && list.size()>0){
			//先删除原来的采购清单信息
			//outStockDao.deleteOutStockBillByOutId(map.get("id").toString());
			
			for(int i=0;i<list.size();i++){
				OutBillModel outBillModel = list.get(i);
				outBillModel.setSerialNumber(String.valueOf(i+1));//序号
				outBillModel.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
				outBillModel.setCreateName(map.get("modifyName").toString());//创建人姓名
				outBillModel.setOutNo(outStock.getOutNo());//出库ID
				outBillModel.setOutId(outStock.getId());//出库编号
				outStockDao.addOutStockBill(outBillModel);//插入入库清单信息
			}
				
				
			//更新入库清单状态 1-未提交，2-已提交
			//outStockDao.updateOutStockStatus(map);
		}
	}

	/**
	 * 审核出库清单
	 * @param outStock
	 * @param user
	 */
	@Override
	public void checkOutStock(OutStock outStock, User user) {
		// TODO Auto-generated method stub
		outStock.setModifyName(user.getTrueName());//修改人姓名
		outStock.setCheckUser(user.getTrueName());//审核人
		outStock.setCheckUserId(user.getId());//审核人id
		outStockDao.editOutStock(outStock);
	}
	
	/**
	 * 新增出库清单产品详细信息
	 * @param map
	 * @param list
	 */
	public void editOutStockBill(List<OutBillModel> list,Map map,User user){
		
		
		if(list!=null && list.size()>0){
			//先删除原来的采购清单信息
			outStockDao.deleteOutStockBillByOutId(map.get("id").toString());
			
			for(int i=0;i<list.size();i++){
				OutBillModel outBillModel = list.get(i);
				outBillModel.setSerialNumber(String.valueOf(i+1));
				outBillModel.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
				outBillModel.setCreateName(map.get("modifyName").toString());//创建人姓名
				outBillModel.setOutId(map.get("id").toString());
				outBillModel.setOutNo(map.get("outNo").toString());
				
				outStockDao.addOutStockBill(outBillModel);//插入入库清单信息
			}
				
			//更新入库清单状态 1-未提交，2-已提交
			outStockDao.updateOutStockStatus(map);
		}
	}

	/**
	 * 出库清单
	 * @param map
	 * @param user
	 * @throws Exception 
	 */
	public Map<String,String> updateOutBill(Map map,User user){
		Map<String,String> map2=new HashMap<String,String>();
		//查询出库列表和产品信息
		List<OutBillModel> list= outStockDao.queryOutBillStockInfo(map.get("id").toString());
		int count=0;
		if(null != list && list.size()>0){
			for(int i=0;i<list.size();i++){
				OutBillModel obm = list.get(i);
				//总库存数-出库数=当前库存数
				if(obm.getAmount()==null||Integer.parseInt(obm.getAmount())==0) {
					//总库存数为空
					map2.put("result", "总库存数为0");
					return map2;
				}else if(Integer.parseInt(obm.getOutAmount())==0||obm.getOutAmount()==null) {
					//出库数为空
					map2.put("result", "出库数为空为0");
					return map2;
					
				}
				count = Integer.parseInt(obm.getAmount())-Integer.parseInt(obm.getOutAmount());
				if(count>=0){
					map.put("amount",count+"");
					map.put("pId",obm.getProductId());
					//更新产品出库数量
					outStockDao.updateOutProductAmount(map);
				}else{
					map2.put("result", obm.getName()+"库存数量不足！");
					return map2;
				}
			}
		}
		//更新产品出库状态
		outStockDao.updateOutStockStatus(map);
		map2.put("result", "1");
		return map2;
	}

	@Override
	public OutStockModel findCompanyNameByOutId(String outNo) {
		return outStockDao.findCompanyNameByOutId(outNo);
	}

	@Override
	public List<OutBillModel> queryOutStockBillListByOutNo(String outNo) {
		return outStockDao.queryOutStockBillListByOutNo(outNo);
	}
}
