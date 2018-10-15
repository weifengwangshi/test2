package com.basefrm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.aspectj.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtils {
	public static File multipartToFile(MultipartFile multfile) throws IOException {  
        CommonsMultipartFile cf = (CommonsMultipartFile)multfile;   
        //这个myfile是MultipartFile的  
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();  
        File file = fi.getStoreLocation();  
        //手动创建临时文件  
        if(file.length() < 10240){
            File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +   
                    file.getName());  
            multfile.transferTo(tmpFile);  
            return tmpFile;  
        }
        return file;  
    }
	
	/**
     * 此类实现了一个输出流，其中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。 可使用 toByteArray() 和
     * toString() 获取数据。 关闭 ByteArrayOutputStream 无效。此类中的方法在关闭此流后仍可被调用，而不会产生任何
     * IOException。
     * 
     * @param filename 文件路径名称
     * @param buffSize 文件读取流ByteArrayOutputStream， 缓冲区大小
     * @return
     */
    public static ByteArrayOutputStream getOutStreamByte(String filename , int buffSize) {
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filename));
            out = new ByteArrayOutputStream(buffSize);
            byte[] temp = new byte[buffSize];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(in != null){
                try {
                    in.close();
//                    LogUtil.debug(FileUtil.class, "关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(out != null){
                try {
//                	LogUtil.debug(FileUtil.class, "关闭");
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
             
        }
        return out;
    }
	
	/**
     * 下载文件
     * @param fileName
     * @param prefix
     * @param response
     */
    public static void downloadFile(String fileName,String prefix,HttpServletResponse response ) {
        ByteArrayOutputStream os = FileUtils.getOutStreamByte(fileName, 1024);
        ByteArrayInputStream inStream = null;
        try {
            inStream = new ByteArrayInputStream(os.toByteArray());
//            LogUtil.debug(FileUtils.class, "get instream size :"+inStream.available());
            if (os != null && os.size() > 0) {
                long filelength = os.size();
                // 设置输出的格式
                response.reset();
                response.setContentType("application/x-msdownload");
                response.setContentLength((int) filelength);
                response.setContentType("text/html;charset=UTF-8");
                response.addHeader(
                        "Content-Disposition",
                        "attachment; filename=\""
                                + new String(prefix.getBytes("GBK"),
                                        "ISO8859_1") + "\"");
                // 循环取出流中的数据
                byte[] b = new byte[4];
                int len;
//              os.flush();
                ServletOutputStream out =  response.getOutputStream();
                while ((len = inStream.read(b)) != -1){
//                  LOG.debug("write byte: "+Arrays.toString(b));
                    out.write(b, 0, len);
//                  response.getWriter().write(b.toString().toCharArray(), 0, len);
                }
                out.flush();
                out.close();
                response.flushBuffer();
            }
 
        } catch (FileNotFoundException e) {
//            LogUtil.error(e,FileUtils.class,"要发送的文件不存在");
        } catch (UnsupportedEncodingException e) {
//        	LogUtil.error(e,FileUtils.class,"编码格式不支持");
        } catch (IOException e) {
//            LogUtil.error(e,FileUtils.class,"文件流异常");
        }finally{
            if(null != inStream){
                try {
                	os.flush();
                    os.close();
                    inStream.close();
                } catch (IOException e) {
//                	LogUtil.error(e,FileUtils.class,"文件流关闭异常");
                }
            }
         
        }
    }
    
    /**
	 * 压缩文件
	 * @param srcfile File[] 需要压缩的文件列表
	 * @param zipfile File 压缩后的文件
	 */
	public static void ZipFiles(File[] srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				String str = srcfile[i].getName();
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
			System.out.println("压缩完成.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下 
     * @param sourceFilePath :待压缩的文件路径 
     * @param zipFilePath :压缩后存放路径 
     * @param fileName :压缩后文件的名称 
     * @return 
     */  
    public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){  
        boolean flag = false;  
        File sourceFile = new File(sourceFilePath);  
        FileInputStream fis = null;  
        BufferedInputStream bis = null;  
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  
          
        if(sourceFile.exists() == false){  
            System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");  
        }else{  
            try {  
                File zipFile = new File(zipFilePath + "/" + fileName +".zip");  
                if(zipFile.exists()){  
                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");  
                }else{  
                    File[] sourceFiles = sourceFile.listFiles();  
                    if(null == sourceFiles || sourceFiles.length<1){  
                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");  
                    }else{  
                        fos = new FileOutputStream(zipFile);  
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));  
                        byte[] bufs = new byte[1024*10];  
                        for(int i=0;i<sourceFiles.length;i++){  
                            //创建ZIP实体，并添加进压缩包  
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());  
                            zos.putNextEntry(zipEntry);  
                            //读取待压缩的文件并写进压缩包里  
                            fis = new FileInputStream(sourceFiles[i]);  
                            bis = new BufferedInputStream(fis, 1024*10);  
                            int read = 0;  
                            while((read=bis.read(bufs, 0, 1024*10)) != -1){  
                                zos.write(bufs,0,read);  
                            }  
                        }  
                        flag = true;  
                    }  
                }  
            } catch (FileNotFoundException e) {
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } catch (IOException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } finally{
                //关闭流  
                try {  
                    if(null != bis) bis.close();  
                    if(null != zos) zos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    throw new RuntimeException(e);  
                }  
            }
        }  
        return flag;  
    }
    
    
    /**
	 * @功能说明：删除文件或文件夹，如果是文件，则直接删除，如果是文件夹，则删除该文件夹；
	 * @param filepath
	 * @throws IOException
	 */
	public static void deleteFile(String filepath) throws IOException {
		File f = new File(filepath);// 定义文件路径
		if (f.exists()) {
			if (f.isDirectory()) {// 判断是文件还是目录
				if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
					f.delete();
				} else {// 若有则把文件放进数组，并判断是否有下级目录
					File delFile[] = f.listFiles();
					int i = f.listFiles().length;
					for (int j = 0; j < i; j++) {
						if (delFile[j].isDirectory()) {
							deleteFile(delFile[j].getAbsolutePath());// 递归调用del方法并取得子目录路径
						}
						delFile[j].delete();// 删除文件
					}
					f.delete();
				}
			} else {
				f.delete();
			}
		}
	}
	
	/**
	 * @author chenan
	 * @time 2013-3-21
	 * @判断是否存在该目录，不存在则创建
	 */
	public static void createDir(String path) {
		File dir = new File(path);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}
	}
    
    /**
	 * @判断是否存在该目录，存在删除
	 * @param path 文件路径
	 */
	public static void deleteDir(String path) throws IOException {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			deleteFile(path);
		}
	}
	
	
	/**
	 * @移动文件
	 * @param filepath源文件路径
	 * @param path 文件路径
	 */
	public static void ydwj(String filepath,String path) {
		 File afile = new File(filepath);  
		 afile.renameTo(new File(path+ afile.getName()));
	}
    
	public static void moveFile(String sourcePath,String targetPath) {
		 File afile = new File(sourcePath);  
		 afile.renameTo(new File(targetPath));
	}
	
    public static void main(String[] args) {
    	String sourceFilePath = "F:\\a";  
        String zipFilePath = "F:\\b";  
        String fileName = "aa";  
        boolean flag = FileUtils.fileToZip(sourceFilePath, zipFilePath, fileName);  
        if(flag){  
            System.out.println("文件打包成功!");  
        }else{  
            System.out.println("文件打包失败!");  
        }  
	}
}
