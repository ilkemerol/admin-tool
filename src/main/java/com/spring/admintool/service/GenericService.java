package com.spring.admintool.service;

import java.util.List;

import com.spring.admintool.entity.StockEntity;
import com.spring.admintool.model.StockCountModel;
import com.spring.admintool.model.StockTypeDateModel;

public interface GenericService {

	public void saveNewStock(String name, String type, Integer unit_price, Integer count, Integer price, Integer commission, Integer selling_price, String operation_type);
	
	public void deleteStock(String name, String type, Integer unit_price, Integer count, Integer price, Integer commission, Integer selling_price, String operation_type);
	
	public List<StockEntity> getAllStock();
	
	public List<StockCountModel> getAllStockCount();
	
	public List<StockTypeDateModel> getSelectedStockType(String name);
	
	public List<StockEntity> getSelectedStockData(String name, String type);
	
	public void updateStock(Integer id, String name, String type, Integer unit_price, Integer count, Integer price, Integer commission, Integer selling_price, String operation_type);
	
}
