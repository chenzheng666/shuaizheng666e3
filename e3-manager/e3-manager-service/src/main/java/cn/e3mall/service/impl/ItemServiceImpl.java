package cn.e3mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Override
	public TbItem findById(Long id) {
		System.out.println("id 的值为"+id);
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
		System.out.println(tbItem.getTitle());
		return tbItem;
	}

}
