package cn.itcast.test;




public class TestFastDFS {

	/*@Test
	public void testFastDFS() throws Exception{
//		1）把FastDFS的java客户端添加到工程中。
//		2）创建一个配置文件。需要配置tracker服务器的ip地址和端口号
//		3）加载配置文件
		ClientGlobal.init("G:/java就业班框架部分/e3-manager-web/src/main/resources/conf/client.conf");
		
//		4）创建一个TrackerClient对象，直接new就可以了
		TrackerClient trackerClient = new TrackerClient();
		
//		5）使用Trackerclient创建一个TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		
		//		6）创建一个StorageClient对象，构造参数TrackerServer、StorageServer（null）
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		
//		7）使用StorageClient上传文件    返回的为路径    两个数据   第一个表示在哪个组   第二个为具体路径
		//参数一 为文件的真实路径     参数二  表示文件的后缀名    参数三 表示元数据   即文件的各种参数
		String[] upload_appender_file = storageClient.upload_appender_file("G:/目标文件夹/复制文件夹/5.jpg", "jpg", null);
		
//		8）返回路径和文件名
		for (String string : upload_appender_file) {
			System.out.println(string);
		}
		
		
	}
	
	
	@Test
	public void testFastDFSClient() throws Exception{
		//在测试类中不能  用classpath
		FastDFSClient fastDFSClient = new FastDFSClient("G:/java就业班框架部分/e3-manager-web/src/main/resources/conf/client.conf");
		
		String string = fastDFSClient.uploadFile("G:/目标文件夹/复制文件夹/14.jpg");
		
		System.out.println(string);
		
	}*/
	
}
