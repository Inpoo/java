package com.bbs.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.bbs.entity.Reply;
import com.bbs.entity.Topic;
import com.bbs.entity.User;
import com.bbs.service.ReplyService;
import com.bbs.service.TopicService;
import com.bbs.service.UserService;
import com.bbs.util.ConstantConfiguration;
import com.bbs.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class ReplyAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	@Resource
	private UserService userService;
	
	@Resource
	private ReplyService replyService;
	
	@Resource
	private TopicService topicService;
	
	private String page;
	private String rows;
	private Long total;
	private String pageCode;
	
	private String ids;
	
	private String mainPage;

	private String crumb1;
	
	private Reply reply;
	
	private int topicId;
	
	private int replyId;
	
	
	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
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

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getCrumb1() {
		return crumb1;
	}

	public void setCrumb1(String crumb1) {
		this.crumb1 = crumb1;
	}
/**
 * 保存恢复信息
 * modify by zhaoyundi 增加回复经验
 * @return
 * @throws Exception
 */
	public String save()throws Exception{
		reply.setPublishTime(new Date());
		Topic topic=topicService.findTopicById(reply.getTopic().getId());
		topic.setModifyTime(new Date());
		topicService.saveTopic(topic);
		replyService.saveReply(reply);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		
		//增加经验
		//User replyUser = reply.getUser();
		//未知原因不能获取reply.getUser()，换个方式
		HttpSession session=request.getSession();
		User replyUser=(User) session.getAttribute("currentUser");
		User topicUser = topic.getUser();
		//回复自己发表的主题不加经验，回复别人主题，经验各加1
		if(replyUser.getId()!=topicUser.getId()){
			replyUser.setExperience(replyUser.getExperience() + ConstantConfiguration.PUBLISHT_REPLY);
			topicUser.setExperience(topicUser.getExperience() + ConstantConfiguration.TOPIC_REPLY);
			userService.saveUser(replyUser);//保存到数据库
			session.setAttribute("currentUser", replyUser);//保存到session
			userService.saveUser(topicUser);
		}
		return null;
	}
	
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		Reply reply=replyService.findReplyById(replyId);
		replyService.deleteReply(reply);
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
