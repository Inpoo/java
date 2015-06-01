package com.bbs.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.bbs.entity.PageBean;
import com.bbs.entity.Section;
import com.bbs.entity.User;
import com.bbs.service.SectionService;
import com.bbs.service.UserService;
import com.bbs.util.DateUtil;
import com.bbs.util.NavUtil;
import com.bbs.util.PageUtil;
import com.bbs.util.ResponseUtil;
import com.bbs.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
/**
 * User相关的Action，注册、登陆、修改信息等
 * @author Zhao Yundi
 * @date 2015年5月15日 下午4:42:13
 */
@Controller
public class UserAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserService userService;
	
	@Resource
	private SectionService sectionService;
	
	private HttpServletRequest request;
	
	private String nickName;
	private User user;
	private String error;
	
	private String imageCode;
	
	private String navCode;
	private String mainPage;
	
	private String ids;

	private User s_user;
	
	private String page;
	private String rows;
	private String pageCode;
	
	private String crumb1;

	private File face;
	private String faceFileName;
	
	private int userId;
	
	private List<User> userList=new ArrayList<User>();
	private List<Section> sectionList=new ArrayList<Section>();
	
	public String getNickName() {
		return nickName;
	}



	public void setNickName(String nickName) {
		this.nickName = nickName;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}



	public String getImageCode() {
		return imageCode;
	}



	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}



	public String getNavCode() {
		return navCode;
	}



	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}



	public String getMainPage() {
		return mainPage;
	}



	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}



	public String getIds() {
		return ids;
	}



	public void setIds(String ids) {
		this.ids = ids;
	}



	public User getS_user() {
		return s_user;
	}



	public void setS_user(User s_user) {
		this.s_user = s_user;
	}



	public String getPage() {
		return page;
	}



	public void setPage(String page) {
		this.page = page;
	}



	public String getRows() {
		return rows;
	}



	public void setRows(String rows) {
		this.rows = rows;
	}
	
	
	public File getFace() {
		return face;
	}



	public void setFace(File face) {
		this.face = face;
	}


	public String getFaceFileName() {
		return faceFileName;
	}



	public void setFaceFileName(String faceFileName) {
		this.faceFileName = faceFileName;
	}


	public String getPageCode() {
		return pageCode;
	}



	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}



	public String getCrumb1() {
		return crumb1;
	}



	public void setCrumb1(String crumb1) {
		this.crumb1 = crumb1;
	}


	public List<User> getUserList() {
		return userList;
	}



	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}


	public List<Section> getSectionList() {
		return sectionList;
	}



	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}


/**
 * 用户注册，把头像文件重命名为yyyyMMddhhmmss + 后缀名保存到服务器
 * user保存头像face路径，当前时间
 * 注册后把用户信息保存到session
 * @return
 * @throws Exception
 */
	public String register()throws Exception{
		if (face!=null) {
			String imageName=DateUtil.getCurrentDateStr();
			String realPath=ServletActionContext.getServletContext().getRealPath("/images/user");
			String imageFile=imageName+"."+faceFileName.split("\\.")[1];
			File saveFile=new File(realPath,imageFile);
			FileUtils.copyFile(face, saveFile);
			user.setFace("images/user/"+imageFile);
		}else{
			user.setFace("");
		}
		user.setRegTime(new Date());
		userService.saveUser(user);
		User currentUser=userService.getUserByNickName(user.getNickName());
		request.getSession().setAttribute("currentUser", currentUser);
		return "register_success";
	}
/**
 * 提交修改用户信息与register类似
 * @return
 * @throws Exception
 */
	public String modify()throws Exception{
		if (face!=null) {
			String imageName=DateUtil.getCurrentDateStr();
			String realPath=ServletActionContext.getServletContext().getRealPath("/images/user");
			String imageFile=imageName+"."+faceFileName.split("\\.")[1];
			File saveFile=new File(realPath,imageFile);
			FileUtils.copyFile(face, saveFile);
			user.setFace("images/user/"+imageFile);
		}else{
			
		}
		userService.saveUser(user);
		//request.getSession().invalidate();原先的修改后重新登陆改为重置session
		User currentUser=userService.getUserByNickName(user.getNickName());
		request.getSession().setAttribute("currentUser", currentUser);
		return "modifySuccess";
	}
	/**
	 * ajax检查昵称是否存在，注册，修改时
	 * @return
	 * @throws Exception
	 */
	public String existUserWithUserName() throws Exception{
		boolean exist=userService.existUserWithNickName(nickName);
		JSONObject result=new JSONObject();
		if (exist) {
			result.put("exist", true);
		} else {
			result.put("exist", false);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	/**
	 * 用户登陆
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception{
		HttpSession session=request.getSession();
		HttpServletResponse response=ServletActionContext.getResponse();
		User currentUser=userService.login(user);
		if(!imageCode.equals(session.getAttribute("sRand"))){
			error="验证码错误！";
			session.setAttribute("error", error);
			/*if (user.getType()==3) {
				return "adminError";
			} else {
				return ERROR;
			}*/
		}else if(currentUser==null){
			error="用户名或密码错误！";
			session.setAttribute("error", error);
			/*if (user.getType()==3) {
				return "adminError";
			} else {
				return ERROR;
			}*/
		}else{
			session.setAttribute("currentUser", currentUser);
			session.removeAttribute("error");
		}
		//区别于普通用户和版主的，暂时不用
		//if (user.getType()==3) {
		//	return "adminLogin";
		//} else {
			return "login";
		//}
	}
	/**
	 * 管理员登陆
	 * @return
	 * @throws Exception
	 */
	public String loginAdmin()throws Exception{
		HttpSession session=request.getSession();
		User currentUser=userService.login(user);
		if (currentUser!=null&&currentUser.getType()==2) {
			session.setAttribute("currentUser", currentUser);
		}else {
			error="用户名或密码错误！";
			return "errorAdmin";
		}
		return "loginAdmin";
	}
	/**
	 * 注销，直接清除Session
	 * @return
	 * @throws Exception
	 */
	public String logout()throws Exception{
		request.getSession().invalidate();
		return "logout";
	}
	//多余功能，暂时不用
	//public String logout2()throws Exception{
	//	request.getSession().invalidate();
	//	return "logout2";
	//}
	/**
	 * 修改个人资料，进入修改页面
	 * @return
	 * @throws Exception
	 */
	public String  preSave() throws Exception{
		HttpSession session=request.getSession();
		user=(User) session.getAttribute("currentUser");
		navCode=NavUtil.genNavCode("个人中心");
		return "modify";
	}
	/**
	 * 进入个人中心
	 * @return
	 * @throws Exception
	 */
	public String userCenter()throws Exception{
		//navCode=NavUtil.genNavCode("个人中心");
		//mainPage="userCenter/ucDefault.jsp";
		return "userCenter";
	}
	
	//public String getUserInfo()throws Exception{
	//	navCode=NavUtil.genNavCode("个人中心");
	//	mainPage="userCenter/userInfo.jsp";
	//	return "userCenter";
	//}
	/**
	 * 后台管理员修改用户信息
	 * TODO 未保存成功
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		HttpSession session=request.getSession();
		userService.saveUser(user);
		//session.setAttribute("currentUser", user);
		/*navCode=NavUtil.genNavCode("个人中心");
		mainPage="userCenter/userInfo.jsp";*/
		return SUCCESS;
	}
	
	public String list()throws Exception{
		HttpSession session=request.getSession();
		if (StringUtil.isEmpty(page)) {
			page="1";
		}
		if (s_user==null) {
			Object o=session.getAttribute("s_user");
			if(o!=null){
				s_user=(User)o;
			}else{
				s_user=new User();				
			}
		}else{
			session.setAttribute("s_user", s_user);
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),6);
		userList=userService.findUserList(s_user, pageBean);
		sectionList=sectionService.findSectionList(null, null);
		long total=userService.getUserCount(s_user);
		pageCode=PageUtil.genPagination(request.getContextPath()+"/admin/User_list.action", total, Integer.parseInt(page), 6,null);
		mainPage="user.jsp";
		crumb1="用户管理";
		return SUCCESS;
	}
	
	public String deleteUsers()throws Exception{
		JSONObject result=new JSONObject();
		String[] idsStr=ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			User u=userService.getUserById(Integer.parseInt(idsStr[i]));
			if (u.getSectionList().size()>0) {
				result.put("info", u.getNickName()+"是版主，不能删除！");
				ResponseUtil.write(ServletActionContext.getResponse(), result);
				return SUCCESS;
			}
		}
		for (int i = 0; i < idsStr.length; i++) {
			User u=userService.getUserById(Integer.parseInt(idsStr[i]));
			userService.delete(u);
		}
		result.put("info", "删除成功！");
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return SUCCESS;
	}
	
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		User e=userService.getUserById(userId);
		if(e.getSectionList().size()>0){
			result.put("info", "此用户是版主，不能删除！");
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return SUCCESS;
		}else {
			userService.delete(e);
			result.put("info", "删除成功！");
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return SUCCESS;
		}
	}
	
	public String saveUser()throws Exception{
		userService.saveUser(user);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String modifyPassword()throws Exception{
		User u=userService.getUserById(user.getId());
		u.setPassword(user.getPassword());
		userService.saveUser(u);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
