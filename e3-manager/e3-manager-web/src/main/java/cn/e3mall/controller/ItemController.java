package cn.e3mall.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

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
	
}
