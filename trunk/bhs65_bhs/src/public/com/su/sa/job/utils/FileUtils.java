package com.su.sa.job.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * 文件处理工具类<br>
 * 提供文件读取;文件拷贝;文件移动;文件删除等 <br>
 */
public class FileUtils {

	/**
     * 
     */
	public static final String excelpath = "\\exFile";

	/**
	 * 文件复制
	 * 
	 * @param srcFileName
	 *            源文件路经
	 * @param desFileName
	 *            目标文件路经
	 * @throws IOException
	 * @throws Exception
	 */
	public static void copy(String srcFileName, String desFileName) {
		java.io.File srcFile = new java.io.File(srcFileName);
		java.io.File desFile = new java.io.File(desFileName);
		copy(srcFile, desFile);
	}

	/**
	 * 文件拷贝
	 * 
	 * @param from
	 * @param to
	 */
	public static void copy(File from, File to) {
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			is = new FileInputStream(from);
			os = new FileOutputStream(to);
			int read = -1;
			byte[] buffer = new byte[10000];
			while ((read = is.read(buffer)) > 0) {
				os.write(buffer, 0, read);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
			try {
				os.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 文件移动
	 * 
	 * @param srcFileName
	 *            源文件路经
	 * @param desFileName
	 *            目标文件路经
	 * @throws IOException
	 * @throws Exception
	 */
	public static void moveFile(String srcFileName, String desFileName) {
		java.io.File srcFile = new java.io.File(srcFileName);
		java.io.File desFile = new java.io.File(desFileName);
		copy(srcFile, desFile);
		srcFile.delete();
	}

	/**
	 * 文件删除
	 * 
	 * @param file
	 * @return
	 */
	public static boolean delete(File file) {
		if (file.isDirectory()) {
			return deleteDirectory(file);
		} else {
			return file.delete();
		}
	}

	/**
	 * 文件夹删除
	 * 
	 * @param path
	 * @return
	 */
	public static boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	/**
	 * 取文件的全名
	 * 
	 * @param fileName
	 *            相对文件路经
	 * @return
	 */
	public static String getFullFileName(String fileName) {
		URL url = FileUtils.class.getResource(fileName);
		if (url == null) {
			return null;
		} else {
			return url.getFile();
		}
	}

	/**
	 * 取文件的全路径
	 * 
	 * @param fileName
	 *            相对文件路经
	 * @return
	 */
	public static String getFullFilePath(String fileName) {
		URL url = FileUtils.class.getResource(fileName);
		if (url == null) {
			return null;
		} else {
			return url.getPath();
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 *            相对文件名称
	 * @return
	 */
	public static boolean fileExist(String fileName) {
		return getFullFileName(fileName) != null;
	}

	/**
	 * 功能：获取文件的后缀（包含'.'）
	 * 
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public static String getFileSufFix(String fileName) {
		int index = fileName.lastIndexOf('.');
		return fileName.substring(index);
	}

	/**
	 * 功能：获取文件名（包括扩展名）
	 * 
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public static String getFileName(String fileName) {
		int start = fileName.lastIndexOf(File.separatorChar) + 1;
		return fileName.substring(start);
	}

	/**
	 * 功能：只获取文件名（不包括扩展名）
	 * 
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public static String getFileNameNotSufFix(String fileName) {
		int end = fileName.lastIndexOf('.') - 1;
		int start = fileName.lastIndexOf(File.separatorChar) + 1;
		return fileName.substring(start, end);
	}

	public static void makeDir(String dirName) {
		File file = new File(dirName);
		if (!file.exists()) {
			File parent = file.getParentFile();
			makeDir(parent.getPath());
			file.mkdir();
		}
	}
}
