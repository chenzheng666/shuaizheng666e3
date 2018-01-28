package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNode;

public interface ContentCatService {

	public List<TreeNode> getContentCatList(long parentId);
	
	public E3Result addContentCat(long parentId,String name);

	public E3Result editContentCat(Long id, String name);

	public String deleteContentCat(Long id);
	
}
