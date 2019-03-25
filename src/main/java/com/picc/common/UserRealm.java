package com.picc.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.picc.entity.BsePlatRole;
import com.picc.entity.User;
import com.picc.service.BsePlatFunctionService;
import com.picc.service.BsePlatRoleService;
import com.picc.service.UserService;


public class UserRealm extends AuthorizingRealm {

	private final static Logger logger = Logger.getLogger(UserRealm.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BsePlatRoleService roleService;
	
	@Autowired
	private BsePlatFunctionService functionService;
	
	 /**
     *  为当前登录的Subject授予角色和权限
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// TODO Auto-generated method stub
		logger.info("======用户授权认证======");
		String userName = principalCollection.getPrimaryPrincipal().toString();
		List<String> roleList = new ArrayList<String>();
	    List<String> permissionList = new ArrayList<String>();	    
	    try {
			User user = userService.getUserByuname(Integer.parseInt(userName));
			if (null != user) {
				 // 实体类User中包含有用户角色的实体类信息
				if (null != user.getRoleId()) {
					 // 获取当前登录用户的角色
					BsePlatRole role = new BsePlatRole();
					role = roleService.selectByPrimaryKey(user.getRoleId());
					roleList.add(role.getName());
					// 实体类Role中包含有角色权限的实体类信息
					 List<Map<String, Object>> functionList = functionService.queryFunctionByRoleId(user.getRoleId());
					 if (null != functionList) {
						 for (int i = 0; i < functionList.size(); i++) {
							String funFlag = functionList.get(i).get("funFlag").toString();
							permissionList.add(funFlag);
						}
					 }					 
					ShiroUtils.setSessionByKey("menus", functionList);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}	    
	    // 为当前用户设置角色和权限
	    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
	    simpleAuthorizationInfo.addRoles(roleList);
	    simpleAuthorizationInfo.addStringPermissions(permissionList);
		return simpleAuthorizationInfo;
	}

	  /**
     * 验证当前登录的Subject
     * 
     * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		 logger.info("======用户登陆认证======");
		// 获取基于用户名和密码的令牌
	        // 实际上这个authcToken是从AdminController里面currentUser.login(token)传过来的
	        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;	        
	    	try {
				User user = userService.getUserByuname(Integer.parseInt(token.getUsername()));
				if (null != user) {
				    AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), String.valueOf(user.getUser_id()));
				    this.setSession("currentUser", user);
				    return authcInfo;
				} else {
				    return null;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	
	    /**
	     * 将一些数据放到ShiroSession中,以便于其它地方使用
	     * 
	     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	     */
	    private void setSession(Object key, Object value) {
	        Subject currentUser = SecurityUtils.getSubject();
	        if (null != currentUser) {
	            Session session = currentUser.getSession();
	            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
	            if (null != session) {
	                session.setAttribute(key, value);
	            }
	        }
	    }
}
