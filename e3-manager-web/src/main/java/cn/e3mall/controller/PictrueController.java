package cn.e3mall.controller;

import java.awt.Dialog.ModalityType;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;

@Controller
public class PictrueController {
	@Value("${image.server.url}")
	private String imageServerUrl;
	

	/*produces=MediaType.APPLICATION_JSON_UTF8_VALUE   也可以写为： produces="text/plain;charset=utf-8" */
	@RequestMapping(value="/pic/upload",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String uploadPictrue(MultipartFile uploadFile){
		try {
			
		
//		1、接收上传的文件
//		2、取文件的原始名称
		String originalFilename = uploadFile.getOriginalFilename();
//		3、截取文件的扩展名
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
//		4、使用FastDFSClient把文件上传到图片服务器
		FastDFSClient fastDFSClient = new FastDFSClient("classpath:/conf/client.conf");
		
//		5、服务器返回一个路径，把路径拼接成一个完整的url
		String url = fastDFSClient.uploadFile(uploadFile.getBytes(),extName);
		
		url = imageServerUrl + url;
		
//		6、返回一个map
		Map map = new HashMap<>();
		
		map.put("error", 0);
		map.put("url", url);
		
		String result = JsonUtils.objectToJson(map);
		
		System.out.println(url);
		
		return result;
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Map map = new HashMap<>();
			
			map.put("error", 1);
			map.put("message", "图片上传失败");
			
			String result = JsonUtils.objectToJson(map);
			
			return result;
		}
	}
	
}
