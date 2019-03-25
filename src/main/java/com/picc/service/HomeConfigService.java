package com.picc.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.picc.entity.HomeConfig;

/**
 *首页配置 服务接口
 * 
 * @author TripodFan
 * @date 2018/9/19
 */
public interface HomeConfigService {

	/**
     *通过主键删除
     * 
     * @param id 主键id
     * @return int 返回删除数据的count
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *添加数据
     * 
     * @param  record 数据值
     * @return int 
     */
    int insertSelective(HomeConfig record);

    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return HomeConfig 查询的数据 
     */
    HomeConfig selectByPrimaryKey(Integer id);

    /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKeySelective(HomeConfig record);
    
    /**
     *获取最新的主页配置信息
     * 
     * @return HomeConfig 
     */
    HomeConfig selectByNewest();
    
    /**
     * 主页信息 分页查询
     * 
     * @param record 
     * @return PageInfo
     * @throws Exception 格式转换等异常
     */
    PageInfo<Map<String, Object>> queryHomePageByParam(HomeConfig record) throws Exception;

}
