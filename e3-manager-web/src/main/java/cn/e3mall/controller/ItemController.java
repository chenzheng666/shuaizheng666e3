package cn.e3mall.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem findItemById(@PathVariable Long itemId,HttpServletResponse resp) throws Exception{
		TbItem tbItem = itemService.findById(itemId);
		System.out.println("返回数据"+tbItem.getTitle());
		/*Gson gson =new Gson();
		String json = gson.toJson(tbItem);
		resp.getWriter().println(json);*/
		return tbItem;
	}
	
	
	/**
	 * 跳转首页
	 */
	@RequestMapping("/")
	public String indexController(){
		return "index";
	}
	
	/**
	 * 点击菜单跳转到相应的页面
	 */
	@RequestMapping("/{page}")
	public String pageController(@PathVariable String page){
		
		return page;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public DataGridResult getItemList(int page,int rows){
		DataGridResult result = itemService.getItemList(page,rows);
		
		return result;
	}
	
	
	/**
	 * 增加商品
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public E3Result addItem(TbItem tbItem,String desc){
		
		E3Result e3Result = itemService.addItem(tbItem,desc);
		
		return e3Result;
	}
	
	
}
