package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public DataGridResult getContentList(int page, int rows, long categoryId) {
		
		//创建查询条件
		TbContentExample example = new TbContentExample();
		
		Criteria criteria = example.createCriteria();
		
		criteria.andCategoryIdEqualTo(categoryId);
		
		//创建分页条件
		PageHelper.startPage(page, rows);
		
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//获取分页数据
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		
		long total = pageInfo.getTotal();
		
		//封装返回数据
		DataGridResult result = new DataGridResult();
		
		result.setTotal(total);
		
		result.setRows(list);
		
		return result;
	}

	@Override
	public E3Result addContent(TbContent content) {
		
		content.setCreated(new Date());
		content.setUpdated(new Date());
		
		contentMapper.insert(content);
		
		return E3Result.ok();
	}

}
