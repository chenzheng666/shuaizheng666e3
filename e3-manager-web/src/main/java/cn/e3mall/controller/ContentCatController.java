package cn.e3mall.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.content.service.ContentCatService;

@Controller
public class ContentCatController {

	@Autowired
	private ContentCatService contentCatService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<TreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
		
		List<TreeNode> treeNodes = contentCatService.getContentCatList(parentId);
		
		return treeNodes;
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public E3Result addContentCat(long parentId,String name){
		
		E3Result e3Result = contentCatService.addContentCat(parentId, name);
		
		return e3Result;
	}
	
	@RequestMapping("/content/category/update")
	@ResponseBody
	public E3Result editContentCat(Long id,String name){
		
		E3Result e3Result = contentCatService.editContentCat(id,name);
		
		return e3Result;
	}
	
	
	@RequestMapping("/content/category/delete/")
	public void deleteContentCat(Long id,HttpServletResponse response){
		
		String result = contentCatService.deleteContentCat(id);
		
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
