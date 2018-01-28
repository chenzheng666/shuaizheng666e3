package cn.e3mall.service;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbItem;

public interface ItemService {

	
	public TbItem findById(Long id);

	public DataGridResult getItemList(int page,int rows);

	public E3Result addItem(TbItem tbItem, String desc);
}
