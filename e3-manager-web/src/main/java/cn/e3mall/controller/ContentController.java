package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentController;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public DataGridResult getContentList(int page,int rows,long categoryId){
		
		DataGridResult result = contentController.getContentList(page,rows,categoryId);
		
		return result;
	}
	
	
	@RequestMapping("/content/save")
	@ResponseBody
	public E3Result addContent(TbContent content){
		
		E3Result result = contentController.addContent(content);
		
		return result;
	}
	
	
}
