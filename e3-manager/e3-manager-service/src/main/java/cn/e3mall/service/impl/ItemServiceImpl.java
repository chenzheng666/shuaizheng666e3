package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapperl;
	
	@Override
	public TbItem findById(Long id) {
		System.out.println("id 的值为"+id);
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
		System.out.println(tbItem.getTitle());
		return tbItem;
	}

	@Override
	public DataGridResult getItemList(int page, int rows) {
		PageHelper.startPage(page, rows);
		
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		
		long total = pageInfo.getTotal();
		
		DataGridResult result = new DataGridResult();
		
		result.setTotal(total);
		result.setRows(list);
		
		return result;
	}

	@Override
	public E3Result addItem(TbItem tbItem, String desc) {
//		1）生成商品id
		long itemId = IDUtils.genItemId();
		
//		2）补全item对象的属性
//		3）向商品表插入数据
		tbItem.setId(itemId);
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		
//		4）创建一个TbItemDesc对象
		TbItemDesc tbItemDesc = new TbItemDesc();

//		5）向对象中设置属性
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		
//		6）向商品描述表中插入数
		tbItemMapper.insert(tbItem);
		tbItemDescMapperl.insert(tbItemDesc);
		
//		7）返回添加成功
		E3Result e3Result = E3Result.ok();
		
		return e3Result;
	}

}
