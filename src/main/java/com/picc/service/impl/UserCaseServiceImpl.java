package com.picc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.picc.dao.UserCaseMapper;
import com.picc.entity.UserCase;
import com.picc.service.UserCaseService;
/**
 * 个人未决任务接口实现
 * @author wangXi
 * @date 2019/1/2
 *
 */
@Service
public class UserCaseServiceImpl implements UserCaseService {
	@Autowired
	private UserCaseMapper userCaseMapper;
	
	@Override
	public int saveUserCase(UserCase usercase) {
		return userCaseMapper.saveUserCase(usercase);
	}

	@Override
	public List<Map<String, Object>> getUserCaseListByParam(UserCase usercase) {
		
		
		return null;
	}

	@Override
	public PageInfo<Map<String, Object>> getPageUserCaseList(UserCase usercase) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		PageHelper.startPage(usercase.getPageNum(),usercase.getLength());
		List<Map<String, Object>> groupCaseList = userCaseMapper.getUserCaseListByParam(usercase);
		for (Map<String, Object> mapEntity : groupCaseList) {
			try {
				mapEntity.put("create_date", sdf.format(sdf.parse(mapEntity.get("create_date").toString())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(groupCaseList);
		return pageInfo;
	}

	@Override
	public int updateUserCase(UserCase usercase) {
		return userCaseMapper.updateUserCase(usercase);
	}

	@Override
	public UserCase getUserCaseById(UserCase usercase) {
		return userCaseMapper.getUserCaseById(usercase);
	}

	@Override
	public int deleteUserCaseById(UserCase usercase) {
		return userCaseMapper.deleteUserCaseById(usercase);
	}

	@Override
	public List<Map<String, Object>> getMonthUserCase(UserCase usercase) {
		List<Map<String, Object>> monthUserCase = userCaseMapper.getMonthUserCase(usercase);
		int relt= 1 ;
		for (Map<String, Object> mapEntity : monthUserCase) {
			if(mapEntity.get("dayNewCase")==null) {
				mapEntity.put("dayNewCase", 0);
			}
			if(mapEntity.get("dayEndCase")==null) {
				mapEntity.put("dayEndCase", 0);
			}
			if(mapEntity.get("endAllCase")==null) {
				mapEntity.put("endAllCase", 0);
			}
			mapEntity.put("relt", relt++);
		}
		return monthUserCase;
	}

}
