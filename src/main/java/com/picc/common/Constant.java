package com.picc.common;

import java.util.HashMap;

/**
 * 常量类
 * 
 * @author Tripod Fan
 * @date 2018/08/24
 */

public class Constant {		
	    /**
	     * 用户角色->角色key
	     */
	    public abstract static interface USER_ROLE_KEY {
	    	 /**区域主管*/
	        public static final String KEY_AREA = "1";
	        /**员工*/
	        public static final String KEY_STAFF = "3";
	        /**组长*/
	        public static final String KEY_GROUP = "4";
	        /**管理员*/
	        public static final String KEY_ADMIN = "5";
	        /**人事*/
	        public static final String KEY_PERSONNEL = "6";
	        /**经理*/
	        public static final String KEY_MANAGER = "7";
	        /**客服*/
	        public static final String KEY_CSUTOMERS = "8";
	        /**北京配送*/
	        public static final String KEY_BJDISTRIBUTION = "9";
	        /**退保专员*/
	        public static final String KEY_QUIT_USER = "10";
	       
	    }
	    
	    /**
	     * 功能类型->角色名称
	     */
	    public static final HashMap<String, Object> USER_ROLE = new HashMap<String, Object>(){

	        private static final long serialVersionUID = 1L;

	        {
	        	put(USER_ROLE_KEY.KEY_AREA, "区域主管");
	            put(USER_ROLE_KEY.KEY_STAFF, "员工");
	            put(USER_ROLE_KEY.KEY_GROUP, "组长");
	            put(USER_ROLE_KEY.KEY_ADMIN, "管理员");
	            put(USER_ROLE_KEY.KEY_PERSONNEL, "人事");
	            put(USER_ROLE_KEY.KEY_MANAGER, "经理");
	            put(USER_ROLE_KEY.KEY_CSUTOMERS, "客服");
	            put(USER_ROLE_KEY.KEY_BJDISTRIBUTION, "北京配送");
	        }
	    };
	    
	    
	    /**
	     * 功能类型->系统功能用
	     */
	    public abstract static interface FUN_TYPE_KEY {
	        /**菜单*/
	        public static final String KEY_1 = "1";
	        /**按钮*/
	        public static final String KEY_2 = "2";
	    }

	    /**
	     * 功能类型->系统功能用
	     */
	    public static final HashMap<String, Object> FUN_TYPE = new HashMap<String, Object>(){

	        private static final long serialVersionUID = 1L;

	        {
	            put(FUN_TYPE_KEY.KEY_1, "菜单");
	            put(FUN_TYPE_KEY.KEY_2, "按钮");
	        }
	    };

	    /**
	     * 操作类型->操作记录用
	     */
	    public abstract static interface OPERATION_TYPE_KEY {
	        /**导入*/
	        public static final String IMPORT = "1";
	        /**导出*/
	        public static final String EXPORT = "2";
	    }

	    /**
	     * 操作类型->操作记录用
	     */
	    public static final HashMap<String, Object> OPERATION_TYPE = new HashMap<String, Object>(){

	        private static final long serialVersionUID = 1L;

	        {
	            put(OPERATION_TYPE_KEY.IMPORT, "导入");
	            put(OPERATION_TYPE_KEY.EXPORT, "导出");
	        }
	    };
	    
	    
	    /**
	     * 状态类型
	     */
	    public abstract static interface STATE_TYPE_KEY {
	        /**菜单*/
	        public static final String KEY_0 = "0";
	        /**按钮*/
	        public static final String KEY_1 = "1";
	    }

	    /**
	     * 状态类型
	     */
	    public static final HashMap<String, Object> STATE_TYPE = new HashMap<String, Object>(){

	        private static final long serialVersionUID = 1L;

	        {
	            put(STATE_TYPE_KEY.KEY_0, "删除");
	            put(STATE_TYPE_KEY.KEY_1, "正常");
	        }
	    };
	    
	    
	    /**
	     * 文章状态类型
	     */
	    public abstract static interface ARTICLE_STATE_KEY {
	        /**已删除*/
	        public static final String DELETED = "0";
	        /**待审核*/
	        public static final String NOT_VERIFY = "1";
	        /**已审核*/
	        public static final String ALREADY_VERIFY = "2";
	        /**已审核*/
	        public static final String FAIL_VERIFY = "3";
	    }
	    
	    /**
	     * 状态类型
	     */
	    public static final HashMap<String, Object> ARTICLE_STATE = new HashMap<String, Object>(){

	        private static final long serialVersionUID = 1L;

	        {
	            put(ARTICLE_STATE_KEY.DELETED, "已删除");
	            put(ARTICLE_STATE_KEY.NOT_VERIFY, "未审核");
	            put(ARTICLE_STATE_KEY.ALREADY_VERIFY, "已审核");
	            put(ARTICLE_STATE_KEY.FAIL_VERIFY, "审核未通过");
	        }
	    };
	    
	    
	    /**
	     * 业绩状态类型
	     */
	    public abstract static interface BUSINESS_STATE_KEY {
	        /**待确认*/
	        public static final String KEY_1 = "1";
	        /**已确认*/
	        public static final String KEY_2 = "2";
	    }

	    /**
	     * 业绩状态类型
	     */
	    public static final HashMap<String, Object> BUSINESS_STATE = new HashMap<String, Object>(){

	        private static final long serialVersionUID = 1L;

	        {
	            put(BUSINESS_STATE_KEY.KEY_1, "待确认");
	            put(BUSINESS_STATE_KEY.KEY_2, "已确认");
	        }
	    };
	    
	    
	    /**
	     * 业绩分类的类型
	     */
	    public abstract static interface BUSINESS_TYPE_KEY {
	        /**续保*/
	        public static final String KEY_1 = "1";
	        /**竞回*/
	        public static final String KEY_2 = "2";
	    }

	    /**
	     * 业绩分类的类型
	     */
	    public static final HashMap<String, Object> BUSINESS_TYPE = new HashMap<String, Object>(){

	        private static final long serialVersionUID = 1L;

	        {
	            put(BUSINESS_TYPE_KEY.KEY_1, "续保");
	            put(BUSINESS_TYPE_KEY.KEY_2, "竞回");
	        }
	    };
	    
	    /**
	     * 是否离职->key
	     */
	    public abstract static interface IS_QUIT_KEY {
	        /**否*/
	        public static final String KEY_0 = "0";
	        /**是*/
	        public static final String KEY_1 = "1";
	    }

	    /**
	     * 业绩分类的类型
	     */
	    public static final HashMap<String, Object> IS_QUIT = new HashMap<String, Object>(){

	        private static final long serialVersionUID = 1L;

	        {
	            put(IS_QUIT_KEY.KEY_1, "离职");
	            put(IS_QUIT_KEY.KEY_0, "在职");
	        }
	    };

}
