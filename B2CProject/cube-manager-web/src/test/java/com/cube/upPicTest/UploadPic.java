package com.cube.upPicTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.cube.common.utils.FastDFSClient;

public class UploadPic {

	@Test
	public void uploadPic() throws Exception{
		//加载配置文件，配置文件中的内容就是tracker服务的地址
		ClientGlobal.init("D:/ECube/cube-manager/cube-manager-web/src/main/resources/conf/piconf.properties");
		//创建一个TranckerClient对象，
		TrackerClient trackerClient = new TrackerClient();
		//使用TrackerClient对象创建连接，获得一个Tracker Service对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//创建一个Storage Service的引用，值为null
		StorageServer storageServer = null;
		//创建一个Storage Client对象，需要两个参数Tracker Service对象，Storage Service的引用
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//使用StorageClient对象上传图片
		//不带扩展名
		String[] strings = storageClient.upload_file("C:/Users/Administrator/Downloads/20140326140303_4EAFA.thumb.600_0.jpeg", "jpeg", null);
		//返回数组。包含祖名和图片的路径
			for (String string : strings) {
				System.out.println(string);
			}
	}
	
	@Test
	public void UpPicUtils() throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("D:/ECube/cube-manager/cube-manager-web/src/main/resources/conf/piconf.properties");
		String file = fastDFSClient.uploadFile("C:/Users/Administrator/Downloads/20140326140303_4EAFA.thumb.600_0.jpeg");
		System.out.println(file);
	}
}
