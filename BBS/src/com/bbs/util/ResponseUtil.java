package com.bbs.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
/**
 * response辅助类
 * @author Zhao Yundi
 * @date 2015年5月16日 上午12:01:46
 */

public class ResponseUtil {
	/**
	 * 输出流
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
