package com.ecube.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.quartz.spi.InstanceIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cube.common.jedis.JedisClient;
import com.cube.common.utils.CookieUtils;
import com.cube.common.utils.CubeResult;
import com.cube.common.utils.JsonUtils;
import com.ecube.pojo.TbItem;
import com.ecube.service.ItemService;

@Controller
public class CartController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable Long itemId , @RequestParam(defaultValue = "1") Integer  num,HttpServletRequest request,HttpServletResponse response){
//	1. 从cookie中查询商品列表（设置为公共方法）
		List<TbItem> cartList = cartListFromCookie(request);
//		2.  判断商品列表中是否存在
		boolean flage = false;
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId.longValue()) {
				flage = true;
//		3.  如果存在，商品数量增加
				tbItem.setNum(tbItem.getNum() + num);
				break;
			}
		}
//		4.  不存在，根据商品id查询商品信息
		if (!flage) {
			TbItem tbItem = itemService.getItemById(itemId);
			tbItem.setNum(num);
			//取一张图片
			String image = tbItem.getImage();
			if (StringUtils.isNotBlank(image)) {
				tbItem.setImage(image.split(",")[0]);		
			}
//		5.  把商品填加到购物车列表
			cartList.add(tbItem);
		}
//		6.  把购物车商品列表写入cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList));
//		7.  返回值：逻辑视图，添加成功购物车页面
		return "cartSuccess";
	}
	/**
	 * 从cookie中取购物车列表的处理
	 * @param token
	 * @return
	 */
	private List<TbItem> cartListFromCookie(HttpServletRequest request){
		String json = CookieUtils.getCookieValue(request, "cart", true);
		if (StringUtils.isBlank(json)) {
			return new ArrayList<TbItem>();
		}
		List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
		return list;
	}

	//展示购物车列表
	@RequestMapping("/cart/cart")
	public String cartList(HttpServletRequest request, Model model){
//		1.  从cookie中取购物车列表
		List<TbItem> list = cartListFromCookie(request);
//		2.  传递给购物车页面
		model.addAttribute("cartList", list);
		
//		3.  返回逻辑视图
		return "cart";
	}

	/**
	 * 更新商品的小计和商品数量
	 * @return
	 */
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public CubeResult updateCount(HttpServletResponse response,HttpServletRequest request, @PathVariable Long itemId , @PathVariable Integer num){
//		1.  从cookie取对应的商品
		List<TbItem> list = cartListFromCookie(request);
//		2.  便利商品，跳出循环`break;`
		for (TbItem tbItem : list) {
			if (tbItem.getId() == itemId.longValue()) {
				tbItem.setNum(num);
				break;
			}
		}
//		3. //把购物车列表写回cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(list));
//		4.   返回成功
		return CubeResult.ok();
	}
	
	/**
	 * 删除购物车中的商品
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/cart/delete/{itemId}")
	public String deletCart(@PathVariable Long itemId,Model model,HttpServletRequest request,HttpServletResponse response){
//		1.  从cookie中取购物车列表
		List<TbItem> list = cartListFromCookie(request);
//		2.  遍历列表，找到要删除的商品
		for (TbItem tbItem : list) {
			if (tbItem.getId() == itemId.longValue()) {
//		3.  删除商品
				list.remove(tbItem);
				break;
			}
		}
//		4.  把购物车列表写入cookie
		CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(list));
//		5.  返回视图 。因为还要跳转到`cart/cart.html`的页面，所以使用redirect。重定向到上个页面
		return "redirect:/cart/cart.html";
	}
}
