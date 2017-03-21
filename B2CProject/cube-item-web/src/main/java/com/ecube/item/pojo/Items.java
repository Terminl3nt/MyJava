package com.ecube.item.pojo;


import com.ecube.pojo.TbItem;

public class Items extends TbItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Items(TbItem tbItem){
		  this.setId(tbItem.getId());
		  this.setTitle(tbItem.getTitle());
		  this.setSellPoint(tbItem.getSellPoint());
		  this.setPrice(tbItem.getPrice());
		  this.setNum(tbItem.getNum());
		  this.setBarcode(tbItem.getBarcode());
		  this.setImage(tbItem.getImage());
		  this.setCid(tbItem.getCid());
		  this.setStatus(tbItem.getStatus());
		  this.setCreated(tbItem.getCreated());
		  this.setUpdated(tbItem.getUpdated());
	}
	
	public String[] getImages(){
		String image2 = this.getImage();
		if (image2!=null && !image2.equals("")) {
			String[] images = image2.split(",");
			return images;
		}
		return null;
	}
}
