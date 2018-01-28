package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<TreeNode> getItemCatList(long parentId) {
		
		//创建查询对象
		TbItemCatExample example = new TbItemCatExample();
		//定义查询条件 
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		//开始查询
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example );
		
		//准备返回的数据
		List<TreeNode> treeNodes = new ArrayList<>();       
		for (TbItemCat tbItemCat : list) {
			TreeNode treeNode = new TreeNode();
			
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());
			treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			
			treeNodes.add(treeNode);
		}
		
		return treeNodes;
	}

}
