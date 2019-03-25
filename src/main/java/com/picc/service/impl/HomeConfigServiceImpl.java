package com.picc.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.picc.dao.HomeConfigMapper;
import com.picc.entity.HomeConfig;
import com.picc.service.HomeConfigService;

/**
 *首页配置 服务接口实现
 * 
 * @author TripodFan
 * @date 2018/11/29
 */
@Transactional(readOnly = true, rollbackFor=Exception.class)
@Service
public class HomeConfigServiceImpl implements HomeConfigService {

	@Autowired
	private HomeConfigMapper  homeMapper;
	
	/**
     *通过主键删除
     * 
     * @param id 主键id
     * @return int 返回删除数据的count
     */
	@Transactional(readOnly = false, rollbackFor=Exception.class)
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return homeMapper.deleteByPrimaryKey(id);
	}

	   /**
     *添加数据
     * 
     * @param  record 数据值
     * @return int 
     */
	@Transactional(readOnly = false, rollbackFor=Exception.class)
	@Override
	public int insertSelective(HomeConfig record) {
		// TODO Auto-generated method stub
		return homeMapper.insertSelective(record);
	}

	  /**
     *通过主键查询
     * 
     * @param  id 主键id
     * @return HomeConfig 查询的数据 
     */
	@Override
	public HomeConfig selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return homeMapper.selectByPrimaryKey(id);
	}

	  /**
     *通过主键修改 会判断数据是否为空为null
     * 
     * @param  record 要修改的数据
     * @return int 修改结果
     */
	@Transactional(readOnly = false, rollbackFor=Exception.class)
	@Override
	public int updateByPrimaryKeySelective(HomeConfig record) {
		// TODO Auto-generated method stub
		return homeMapper.updateByPrimaryKeySelective(record);
	}

	/**
     *获取最新的主页配置信息
     * 
     * @return HomeConfig 
     */
	@Override
	public HomeConfig selectByNewest() {
		// TODO Auto-generated method stub
		return homeMapper.selectByNewest();
	}

	  /**
	 * 主页信息 分页查询
	 * 
	 * @param record 
	 * @return PageInfo
	 * @throws Exception 格式转换等异常
	 */
	@Override
	public PageInfo<Map<String, Object>> queryHomePageByParam(HomeConfig record) throws Exception {
		// TODO Auto-generated method stub
		
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 PageHelper.startPage(record.getPageNum(),record.getLength());
		 
		 List<Map<String, Object>> maplist = homeMapper.queryHomeConfigList(record);
		 
		  // 处理数据
	        for(Map<String, Object> mapEntity : maplist){   
	    	    // 格式化创建日期
	            mapEntity.put("createdDateName", sdf.format(sdf.parse(String.valueOf(mapEntity.get("createdDate")))));
	        }
	        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(maplist);
		return pageInfo;
	}

}
