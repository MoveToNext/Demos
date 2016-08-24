package liu.myapplication.utils;


import android.os.SystemClock;

import java.util.UUID;

/**
 * 上传类
 * 
 * @author djx
 * 
 */
public class FileUpload {

	private static final String BOUNDARY =  UUID.randomUUID().toString(); // 边界标识 随机生成
	private static final String PREFIX = "--";
	private static final String LINE_END = "\r\n";
	private static final String CONTENT_TYPE = "multipart/form-data"; // 内容类型
	private static int readTimeOut = 10 * 1000; // 读取超时
	private static int connectTimeout = 10 * 1000; // 超时时间
	
	/***
	 * 请求使用多长时间
	 */
	private static int requestTime = 0;
	
	private static final String CHARSET = "utf-8"; // 设置编码

	/***
	 * 上传成功
	 */
	public static final int UPLOAD_SUCCESS_CODE = 1;
	
	/**
	 * 文件不存在
	 */
	public static final int UPLOAD_FILE_NOT_EXISTS_CODE = 2;
	
	/**
	 * 服务器出错
	 */
	public static final int UPLOAD_SERVER_ERROR_CODE = 3;
	protected static final int WHAT_TO_UPLOAD = 1;
	protected static final int WHAT_UPLOAD_DONE = 2;
	
	public static synchronized void toUploadFile(final String fileKey, final MyListener listener) {
		SystemClock.sleep(3000);
		listener.to("上传成功啦");

	}
	public interface MyListener{
		void to(String s);
	}
}
