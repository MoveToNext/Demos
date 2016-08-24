package liu.myapplication.utils;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * 上传类
 * 
 * @author djx
 * 
 */
public class FileUploadTest {

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
	
	public static synchronized void toUploadFile(File file, String fileKey, String RequestURL, Map<String, String> param,MyListener listener) {
		String result = null;
		long requestTime = System.currentTimeMillis();
		long responseTime = 0;

		try {
			URL url = new URL(RequestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(readTimeOut);
			conn.setConnectTimeout(connectTimeout);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Charset", CHARSET); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
			
			/**
			 * 当文件不为空，把文件包装并且上传
			 */
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			StringBuffer sb = null;
			String params = "";
			
			/***
			 * 以下是用于上传参数
			 */
			if (param != null && param.size() > 0) {
				Iterator<String> it = param.keySet().iterator();
				while (it.hasNext()) {
					sb = null;
					sb = new StringBuffer();
					String key = it.next();
					String value = param.get(key);
					sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
					sb.append("Content-Disposition: form-data; name=\"").append(key).append("\"").append(LINE_END).append(LINE_END);
					sb.append(value).append(LINE_END);
					sb.append("Content-Type: text/plain; charset=" + CHARSET + LINE_END);   
					params = sb.toString();
					System.out.println(params);
					dos.write(params.getBytes());
				}
			}
			
			sb = null;
			params = null;
			sb = new StringBuffer();
			/**
			 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
			 * filename是文件的名字，包含后缀名的 比如:abc.png
			 */
			sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
			sb.append("Content-Disposition:form-data; name=\"" + fileKey
					+ "\"; filename=\"" + file.getName() + "\"" + LINE_END);
			sb.append("Content-Type:image/pjpeg" + LINE_END); // 这里配置的Content-type很重要的 ，用于服务器端辨别文件的类型的
			sb.append(LINE_END);
			params = sb.toString();
			sb = null;
			
			dos.write(params.getBytes());
			/**上传文件*/
			InputStream is = new FileInputStream(file);
			byte[] bytes = new byte[1024];
			int len = 0;
			int curLen = 0;
			while ((len = is.read(bytes)) != -1) {
				curLen += len;
				dos.write(bytes, 0, len);
			}
			is.close();
			
			dos.write(LINE_END.getBytes());
			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
			dos.write(end_data);
			dos.flush();


			/**
			 * 获取响应码 200=成功 当响应成功，获取响应的流
			 */
			int res = conn.getResponseCode();
			responseTime = System.currentTimeMillis();
			if (res == 200) {
				result = readStrByCode(conn.getInputStream(), "UTF-8");
				System.out.println("result="+result);
				listener.to(result);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String readStrByCode(InputStream is, String code) {
	    StringBuilder builder = new StringBuilder();
	    BufferedReader reader = null;
	    String line="";
	    try
	    {
	      reader = new BufferedReader(new InputStreamReader(is, code));
	      while ((line = reader.readLine()) != null)
	      {

	        builder.append(line);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      try
	      {
	        reader.close();
	      }
	      catch (IOException e1) {
	        e1.printStackTrace();
	      }
	    }
	    finally
	    {
	      try
	      {
	        reader.close();
	      }
	      catch (IOException e) {
	        e.printStackTrace();
	      }
	    }
	    return builder.toString();
	  }
//
//	public static void main(String[] args) {
//		File file = new File("d://2.jpg");
//		String fileKey="pic";
//		String RequestURL="http://mapi.159cai.com/upload.php?user=159ceshi&password=911d430ee303815d753125cc9a435e70";
//		Map<String, String> param = new HashMap<String, String>();
//		toUploadFile(file, fileKey, RequestURL, param);
//	}

	public interface MyListener{
		void to(String s);
	}
}
