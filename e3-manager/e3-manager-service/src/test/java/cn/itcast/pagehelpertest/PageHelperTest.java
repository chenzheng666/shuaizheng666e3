package cn.itcast.pagehelpertest;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;

public class PageHelperTest {

	@Test
	public void pageHelperTest(){
		//设置分页信息
		PageHelper.startPage(1, 10);//从第一条数据开始查     工查询十条数据
		//执行查询
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-dao.xml");
		
		TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
		
		//执行查询
		TbItemExample example = new TbItemExample();
		//example只是查询的对象    当它为null时    拜师查询所有
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		//获取下旬的分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//打印数据
		System.out.println(pageInfo.getTotal());
		System.out.println(pageInfo.getPages());
		System.out.println(list.size());
		
		
	}
	
}
