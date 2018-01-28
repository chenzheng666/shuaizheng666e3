package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.content.service.ContentCatService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCatServiceImpl implements ContentCatService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<TreeNode> getContentCatList(long parentId) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria createCriteria = example.createCriteria();
		Criteria criteria = createCriteria.andParentIdEqualTo(parentId);
		
		List<TbContentCategory> tbContentCategorys = tbContentCategoryMapper.selectByExample(example);
		
		
		List<TreeNode> list = new ArrayList<>();
		
		for (TbContentCategory tbContentCategory : tbContentCategorys) {
			
			TreeNode node = new TreeNode();
			
			node.setId(tbContentCategory.getId());
			
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			
			node.setText(tbContentCategory.getName());
			
			list.add(node);
			
			
		}
		
		
		return list;
	}

	@Override
	public E3Result addContentCat(long parentId, String name) {
		TbContentCategory tbContentCategory = new TbContentCategory();
		
		tbContentCategory.setName(name);
		
		//插入是否为父节点    刚创建的菜单，跟的为叶节点，为false
		tbContentCategory.setIsParent(false);
		tbContentCategory.setParentId(parentId);
		//设置排序方式，默认为1 
		tbContentCategory.setSortOrder(1);
		//设置菜单的状态1正常   0删除
		tbContentCategory.setStatus(1);
		tbContentCategory.setUpdated(new Date());
		tbContentCategory.setCreated(new Date());
		
		//插入数据
		tbContentCategoryMapper.insert(tbContentCategory);
		
		System.out.println("插入内容菜单的id为："+tbContentCategory.getId());
		//查询父节点，如果父节点原来为子节点，需要改为父节点
		TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		
		//若为叶节点   修改
		if(!parent.getIsParent()){
			parent.setIsParent(true);
			tbContentCategoryMapper.updateByPrimaryKey(parent);
		}
		
		
		return E3Result.ok(tbContentCategory);
	}

	@Override
	public E3Result editContentCat(Long id, String name) {
		
		TbContentCategory contentCategory = new TbContentCategory();
		
		contentCategory.setId(id);
		
		contentCategory.setName(name);
		
		tbContentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
		
		return E3Result.ok();
	}

	@Override
	public String deleteContentCat(Long id) {
		
		//根据id查出眼删除的分类是否为父节点
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		
		System.out.println("删除操作传过来的id为："+id);
		System.out.println(contentCategory);
		
		//如果是    返回0   前台提示不能删除父节点    如果不是则删除 并返回1 
		if(contentCategory.getIsParent()){
			
			return "0";
			
		}else{
			
			//执行删除分类
			tbContentCategoryMapper.deleteByPrimaryKey(id);
			
			//创建查询条件
			TbContentCategoryExample example =  new TbContentCategoryExample();
			
			Criteria createCriteria = example.createCriteria();
			
			Criteria criteria = createCriteria.andParentIdEqualTo(contentCategory.getParentId());
			
			//查询父节点下换有没有其他的叶节点
			List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
			
			//如果没有    把会否为父节点改为否
			if(list.size() == 0){
				
				TbContentCategory contentCategory2 = tbContentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
				
				contentCategory2.setIsParent(false);
				
				tbContentCategoryMapper.updateByPrimaryKey(contentCategory2);
				
			}
			
			return "1";
			
		}
		
	}

}
