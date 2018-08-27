package com.su.sa.job.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * �ļ���������<br>
 * �ṩ�ļ���ȡ;�ļ�����;�ļ��ƶ�;�ļ�ɾ���� <br>
 */
public class FileUtils {

	/**
     * 
     */
	public static final String excelpath = "\\exFile";

	/**
	 * �ļ�����
	 * 
	 * @param srcFileName
	 *            Դ�ļ�·��
	 * @param desFileName
	 *            Ŀ���ļ�·��
	 * @throws IOException
	 * @throws Exception
	 */
	public static void copy(String srcFileName, String desFileName) {
		java.io.File srcFile = new java.io.File(srcFileName);
		java.io.File desFile = new java.io.File(desFileName);
		copy(srcFile, desFile);
	}

	/**
	 * �ļ�����
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
	 * �ļ��ƶ�
	 * 
	 * @param srcFileName
	 *            Դ�ļ�·��
	 * @param desFileName
	 *            Ŀ���ļ�·��
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
	 * �ļ�ɾ��
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
	 * �ļ���ɾ��
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
	 * ȡ�ļ���ȫ��
	 * 
	 * @param fileName
	 *            ����ļ�·��
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
	 * ȡ�ļ���ȫ·��
	 * 
	 * @param fileName
	 *            ����ļ�·��
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
	 * �ж��ļ��Ƿ����
	 * 
	 * @param fileName
	 *            ����ļ�����
	 * @return
	 */
	public static boolean fileExist(String fileName) {
		return getFullFileName(fileName) != null;
	}

	/**
	 * ���ܣ���ȡ�ļ��ĺ�׺������'.'��
	 * 
	 * @param fileName
	 *            �ļ�����
	 * @return
	 */
	public static String getFileSufFix(String fileName) {
		int index = fileName.lastIndexOf('.');
		return fileName.substring(index);
	}

	/**
	 * ���ܣ���ȡ�ļ�����������չ����
	 * 
	 * @param fileName
	 *            �ļ�����
	 * @return
	 */
	public static String getFileName(String fileName) {
		int start = fileName.lastIndexOf(File.separatorChar) + 1;
		return fileName.substring(start);
	}

	/**
	 * ���ܣ�ֻ��ȡ�ļ�������������չ����
	 * 
	 * @param fileName
	 *            �ļ�����
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
