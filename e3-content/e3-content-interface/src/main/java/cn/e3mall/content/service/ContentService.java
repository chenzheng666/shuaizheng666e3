package cn.e3mall.content.service;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbContent;

public interface ContentService {

	public DataGridResult getContentList(int page, int rows, long categoryId);
	public E3Result addContent(TbContent content);

}
