package com.bbs.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
/**
 * response������
 * @author Zhao Yundi
 * @date 2015��5��16�� ����12:01:46
 */

public class ResponseUtil {
	/**
	 * �����
	 * @param response
	 * @param o
	 * @throws Exception
	 */
	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}