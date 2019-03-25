package com.picc.dao;

import java.util.List;
import java.util.Map;

import com.picc.entity.HomeConfig;
/**
 * 首页配置
 * 
 * @author TripodFan
 * @date 2018/11/29
 */
public interface HomeConfigMapper {
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
    int insert(HomeConfig record);

    /**
     *添加数据  会判断数据值是否为空为null
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
     *通过主键修改 
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKeyWithBLOBs(HomeConfig record);

    /**
     *通过主键修改 不会修改文章内容
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
    int updateByPrimaryKey(HomeConfig record);
    
    /**获取符合查询条件的首页信息列表
     * @param config
     * @return 首页信息列表
     */
    List<Map<String, Object>> queryHomeConfigList(HomeConfig config);
    
    
    /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return HomeConfig 查询的数据 
     */
    HomeConfig selectByNewest();
}