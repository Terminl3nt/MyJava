package com.ecube.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cube.common.utils.FastDFSClient;
import com.cube.common.utils.JsonUtils;

@Controller
public class PictureUploadController {

	/**
	 * 上传图片
	 */
	@Value("${IMAGE_UPLOAD_URL}")
	private String IMAGE_UPLOAD_URL;
	
	@RequestMapping( value="/pic/upload" , produces=MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8")
	@ResponseBody
	public String uploadPic(MultipartFile uploadFile){
		
		try {
			//得到图片的地址和文件名
			String originalFilename = uploadFile.getOriginalFilename();
			String extention = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			
			//把图片上传到图片服务器中
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/piconf.properties");
			// 补充完整的url
			String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extention);
			String url = IMAGE_UPLOAD_URL + path;
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			return JsonUtils.objectToJson(result);
		}
	}
}
