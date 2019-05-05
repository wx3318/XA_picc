package com.picc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.picc.dao.PendingMapper;
import com.picc.entity.Pending;
import com.picc.service.PendingService;
/**
 *未决案件接口实现 
 * @author wangXi
 * @date 2018/12/05
 */
@Service
public class PendingServiceImpl implements PendingService {
	@Autowired
	private PendingMapper pendingMapper;	
	/**
	 *批量新增
	 * @param Penging List集合
	 * @return int 新增数量
	 */
	@Override
	public int savePendingList(List<Pending> pendingList) {
		//数据处理
		
		return pendingMapper.savePendingList(pendingList);
	}

	@Override
	public List<Pending> getPendingListParam(Pending pending) {
		List<Pending> pendingListParam = pendingMapper.getPendingListParam(pending);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(Pending p:pendingListParam) {
			if(p.getRiskDate()!=null) {
				try {
					p.setRiskDate(sdf.parse(sdf.format(p.getRiskDate())));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}		
		return pendingListParam;
	}

	@Override
	public int updatePending(List<Pending> pendingList) {
		return pendingMapper.updatePending(pendingList);
	}


	@Override
	public List<Map<String, Object>> getPendingListByAreaType(Pending pending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<Map<String, Object>> getPagePendingListByAreaType(Pending pending) {
		PageHelper.startPage(pending.getPageNum(),pending.getLength());
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 List<Map<String, Object>> pendingListStation = pendingMapper.getPendingListByAreaType(pending);
		 for(Map<String, Object> mapEntity : pendingListStation) {
			 if(null!=mapEntity.get("risk_date")) {
	           		if(!String.valueOf(mapEntity.get("risk_date")).equals("") || null!=String.valueOf(mapEntity.get("risk_date"))){
	           			try {
							mapEntity.put("risk_date", sdf.format(sdf.parse(String.valueOf(mapEntity.get("risk_date")))));
						} catch (ParseException e) {
							e.printStackTrace();
						}   	 
		            }
	           }else {
	           		mapEntity.put("risk_date", "");
	           }
			 	if(null==mapEntity.get("insurance_code")) {
			 		mapEntity.put("insurance_code", "");
			 	}			 	
			 	if(null==mapEntity.get("insurance_name")) {
			 		mapEntity.put("insurance_name", "");
			 	}
			 	if(null==mapEntity.get("damage_code")) {
			 		mapEntity.put("damage_code", "");
			 	}
			 	if(null==mapEntity.get("damage_name")) {
			 		mapEntity.put("damage_name", "");
			 	}
			 	if(null==mapEntity.get("adjuster_code")) {
			 		mapEntity.put("adjuster_code", "");
			 	}
			 	if(null==mapEntity.get("adjuster_name")) {
			 		mapEntity.put("adjuster_name", "");
			 	}
			 	if(null==mapEntity.get("group_id")) {
			 		mapEntity.put("group_id", "");
			 	}
		 }
		 PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pendingListStation);
		return pageInfo;
	}

	@Override
	public List<Pending> getPendingListOnShengEntry(Pending pending) {
		List<Pending> pendingListOnShengEntry = pendingMapper.getPendingListOnShengEntry(pending);
		for(Pending p:pendingListOnShengEntry) {
			p.setAreaType("1");
		}
		if(!"-1".equals(pending.getGroupId())) {
			int groupSize = pendingListOnShengEntry.size();
			if(groupSize%4==0) {
				int size=groupSize/4;
				for(int i=0;i<size;i++) {
					pendingListOnShengEntry.get(i).setGroupId("1");
				}
				for(int i=size;i<size*2;i++) {
					pendingListOnShengEntry.get(i).setGroupId("2");
				}
				if(pendingListOnShengEntry.get(size).getReportNumber().equals(pendingListOnShengEntry.get(size-1).getReportNumber())) {
					for(int i=size;i>2;i--) {
						if(!pendingListOnShengEntry.get(size-1).getReportNumber().equals(pendingListOnShengEntry.get(size-2).getReportNumber())) {
							pendingListOnShengEntry.get(i).setGroupId("2");
							break;
						}
					}
					pendingListOnShengEntry.get(size).setGroupId("1");
				}
				for(int i=size*2;i<size*3;i++) {
					pendingListOnShengEntry.get(i).setGroupId("3");
				}
				for(int i=size*3;i<size*4;i++) {
					pendingListOnShengEntry.get(i).setGroupId("4");
				}
				if(pendingListOnShengEntry.get(size*3).getReportNumber().equals(pendingListOnShengEntry.get(size*3-1).getReportNumber())) {
					for(int i=size*4;i>size*3+2;i--) {
						if(!pendingListOnShengEntry.get(size*3-1).getReportNumber().equals(pendingListOnShengEntry.get(size*3-2).getReportNumber())) {
							pendingListOnShengEntry.get(i).setGroupId("4");
							break;
						}
					}
					pendingListOnShengEntry.get(size*3).setGroupId("3");
				}
			}else {
				int sizeleft= groupSize%4;
				int size=groupSize/4;
				for(int i=0;i<size;i++) {
					pendingListOnShengEntry.get(i).setGroupId("1");
				}
				for(int i=size;i<size*2;i++) {
					pendingListOnShengEntry.get(i).setGroupId("2");
				}
				if(pendingListOnShengEntry.get(size).getReportNumber().equals(pendingListOnShengEntry.get(size-1).getReportNumber())) {
					for(int i=size;i>2;i--) {
						if(!pendingListOnShengEntry.get(size-1).getReportNumber().equals(pendingListOnShengEntry.get(size-2).getReportNumber())) {
							pendingListOnShengEntry.get(i).setGroupId("2");
							break;
						}
					}
					pendingListOnShengEntry.get(size).setGroupId("1");
				}
				for(int i=size*2;i<size*3;i++) {
					pendingListOnShengEntry.get(i).setGroupId("3");
				}
				for(int i=size*3;i<size*4;i++) {
					pendingListOnShengEntry.get(i).setGroupId("4");
				}
				if(pendingListOnShengEntry.get(size*3).getReportNumber().equals(pendingListOnShengEntry.get(size*3-1).getReportNumber())) {
					for(int i=size*4;i>size*3+2;i--) {
						if(!pendingListOnShengEntry.get(size*3-1).getReportNumber().equals(pendingListOnShengEntry.get(size*3-2).getReportNumber())) {
							pendingListOnShengEntry.get(i).setGroupId("4");
							break;
						}
					}
					pendingListOnShengEntry.get(size*3).setGroupId("3");
				}
				if(sizeleft==1) {
					if(pendingListOnShengEntry.get(size*4).getReportNumber().equals(pendingListOnShengEntry.get(size*4-1).getReportNumber())) {
						pendingListOnShengEntry.get(size*4).setGroupId("4");
					}else {
						int num=1;
						do {
							num=(int)(Math.random()*5);
							if(num!=4){
								break;
							}
						}while(true);
						if(num==0) {
							num=num+1;
						}
						pendingListOnShengEntry.get(size*4).setGroupId(String.valueOf(num));
					}					
				}else if(sizeleft==2) {
					
					if(pendingListOnShengEntry.get(size*4).getReportNumber().equals(pendingListOnShengEntry.get(size*4-1).getReportNumber())) {
						pendingListOnShengEntry.get(size*4).setGroupId("4");
						int num=2;
						do {
							num=(int)(Math.random()*5);
							if(num!=4){
								break;
							}
						}while(true);
						if(num==0) {
							num=num+1;
						}
						pendingListOnShengEntry.get(size*4+1).setGroupId(String.valueOf(num));
					}else {										
						if(pendingListOnShengEntry.get(size*4).getReportNumber().equals(pendingListOnShengEntry.get(size*4+1).getReportNumber())) {
							int num=(int)(Math.random()*5);
							if(num==0) {
								num=num+1;
							}
							pendingListOnShengEntry.get(size*4).setGroupId(String.valueOf(num));
							pendingListOnShengEntry.get(size*4+1).setGroupId(String.valueOf(num));
						}else {
							if(pendingListOnShengEntry.get(size*4).getReportNumber().equals(pendingListOnShengEntry.get(size*4-1).getReportNumber())) {
								pendingListOnShengEntry.get(size*4).setGroupId("4");
								int num=(int)(Math.random()*5);
								pendingListOnShengEntry.get(size*4+1).setGroupId(String.valueOf(num));
							}else {
														
								int num=(int)(Math.random()*5);
								if(num==0) {
									num=num+1;
								}
								if(num-1==0) {
									pendingListOnShengEntry.get(size*4).setGroupId(String.valueOf(num));
									pendingListOnShengEntry.get(size*4+1).setGroupId(String.valueOf(num+2));
								}else{
									pendingListOnShengEntry.get(size*4).setGroupId(String.valueOf(num));
									pendingListOnShengEntry.get(size*4+1).setGroupId(String.valueOf(num-1));
								}	
							}
						}	
					}
				}else if(sizeleft==3) {
					if(pendingListOnShengEntry.get(size*4).getReportNumber().equals(pendingListOnShengEntry.get(size*4-1).getReportNumber())) {
						pendingListOnShengEntry.get(size*4).setGroupId("4");
						int num=3;
						do {
							num=(int)(Math.random()*5);
							if(num!=4){
								break;
							}
						}while(true);
						//随机数0处理
						if(num==0) {
							num=num+1;
						}
						if(pendingListOnShengEntry.get(size*4+1).getReportNumber().equals(pendingListOnShengEntry.get(size*4+2).getReportNumber())) {
							pendingListOnShengEntry.get(size*4+1).setGroupId(String.valueOf(num));
							pendingListOnShengEntry.get(size*4+2).setGroupId(String.valueOf(num));
						}else {													
							pendingListOnShengEntry.get(size*4+1).setGroupId(String.valueOf(num));
							if(num-1==0) {
								pendingListOnShengEntry.get(size*4+2).setGroupId(String.valueOf(num+1));
							}else{
								pendingListOnShengEntry.get(size*4+2).setGroupId(String.valueOf(num-1));
							}
						}
					}else {					
						if(pendingListOnShengEntry.get(size*4).getReportNumber().equals(pendingListOnShengEntry.get(size*4+1).getReportNumber())) {
							int num=(int)(Math.random()*5);
							if(num==0) {
								num=num+1;
							}
							pendingListOnShengEntry.get(size*4).setGroupId(String.valueOf(num));
							pendingListOnShengEntry.get(size*4+1).setGroupId(String.valueOf(num));
							
							if(num-1==0) {
								pendingListOnShengEntry.get(size*4+2).setGroupId(String.valueOf(num+1));
							}else{
								pendingListOnShengEntry.get(size*4+2).setGroupId(String.valueOf(num-1));
							}
						}else {
							if(pendingListOnShengEntry.get(size*4+1).getReportNumber().equals(pendingListOnShengEntry.get(size*4+2).getReportNumber())) {
								int num=(int)(Math.random()*5);
								if(num==0) {
									num=num+1;
								}
								pendingListOnShengEntry.get(size*4+1).setGroupId(String.valueOf(num));
								pendingListOnShengEntry.get(size*4+2).setGroupId(String.valueOf(num));
								if(num-1==0) {
									pendingListOnShengEntry.get(size*4).setGroupId(String.valueOf(num+1));
								}else{
									pendingListOnShengEntry.get(size*4).setGroupId(String.valueOf(num-1));
								}
							}else {
								pendingListOnShengEntry.get(size*4).setGroupId("1");
								pendingListOnShengEntry.get(size*4+1).setGroupId("3");							
								pendingListOnShengEntry.get(size*4+2).setGroupId("4");
							}						
						}
					}
				}
			}
		}
		
		return pendingListOnShengEntry;
	}

	@Override
	public List<Pending> getPendingListOnjiaoEntry(Pending pending) {
		List<Pending> pendingListOnjiaoEntry = pendingMapper.getPendingListOnjiaoEntry(pending);
		String[] str= new String[7];
		str[0]="周至支公司理赔分部"; str[1]="长安支公司理赔分部"; str[2]="阎良支公司理赔分部";
		str[3]="临潼支公司理赔分部"; str[4]="蓝田支公司理赔分部"; str[5]="户县支公司理赔分部"; str[6]="高陵支公司理赔分部";
		for(Pending p:pendingListOnjiaoEntry) {
			p.setAreaType("2");
			if(!"-1".equals(pending.getGroupId())) {
				for(int i=0;i<str.length;i++) {
					if(str[i].equals(p.getGroupName())) {
						int groupId = i+5;
						p.setGroupId(String.valueOf(groupId));
						break;
					}
				}
			}			
		}
		return pendingListOnjiaoEntry;
	}

	@Override
	public List<Pending> getPendingListOntpEntry(Pending pending) {
		List<Pending> pendingListOntpEntry = pendingMapper.getPendingListOntpEntry(pending);
		for(Pending p:pendingListOntpEntry) {
			p.setAreaType("3");
			if(!"-1".equals(pending.getGroupId())) {
				p.setGroupId("13");
			}
			
		}
		return pendingListOntpEntry;
	}

	@Override
	public List<Pending> getPendingListOncqEntry(Pending pending) {
		List<Pending> pendingListOncqEntry = pendingMapper.getPendingListOncqEntry(pending);
		for(Pending p:pendingListOncqEntry) {
			p.setAreaType("4");
		}
		return pendingListOncqEntry;
	}

	@Override
	public List<Pending> getPendingListOnCqXnEntry(Pending pending) {
		List<Pending> pendingListOnCqXnEntry = pendingMapper.getPendingListOnCqXnEntry(pending);
		List<Pending> pendingListOncqEntry = pendingMapper.getPendingListOncqEntry(pending);
		for(Pending p:pendingListOncqEntry) {
			p.setGroupId(null);
		}
		pendingListOnCqXnEntry.removeAll(pendingListOncqEntry);
		for(Pending p:pendingListOnCqXnEntry) {
			p.setAreaType("5");
		}
		if(!"-1".equals(pending.getGroupId())) {
			int groupSize = pendingListOnCqXnEntry.size();
			if(groupSize%4==0) {
				int size=groupSize/4;
				for(int i=0;i<size;i++) {
					pendingListOnCqXnEntry.get(i).setGroupId("1");
				}
				for(int i=size;i<size*2;i++) {
					pendingListOnCqXnEntry.get(i).setGroupId("2");
				}
				if(pendingListOnCqXnEntry.get(size).getReportNumber().equals(pendingListOnCqXnEntry.get(size-1).getReportNumber())) {
					for(int i=size;i>2;i--) {
						if(!pendingListOnCqXnEntry.get(size-1).getReportNumber().equals(pendingListOnCqXnEntry.get(size-2).getReportNumber())) {
							pendingListOnCqXnEntry.get(i).setGroupId("2");
							break;
						}
					}
					pendingListOnCqXnEntry.get(size).setGroupId("1");
				}
				for(int i=size*2;i<size*3;i++) {
					pendingListOnCqXnEntry.get(i).setGroupId("3");
				}
				for(int i=size*3;i<size*4;i++) {
					pendingListOnCqXnEntry.get(i).setGroupId("4");
				}
				if(pendingListOnCqXnEntry.get(size*3).getReportNumber().equals(pendingListOnCqXnEntry.get(size*3-1).getReportNumber())) {
					for(int i=size*4;i>size*3+2;i--) {
						if(!pendingListOnCqXnEntry.get(size*3-1).getReportNumber().equals(pendingListOnCqXnEntry.get(size*3-2).getReportNumber())) {
							pendingListOnCqXnEntry.get(i).setGroupId("4");
							break;
						}
					}
					pendingListOnCqXnEntry.get(size*3).setGroupId("3");
				}
			}else {
				int sizeleft= groupSize%4;
				int size=groupSize/4;
				for(int i=0;i<size;i++) {
					pendingListOnCqXnEntry.get(i).setGroupId("1");
				}
				for(int i=size;i<size*2;i++) {
					pendingListOnCqXnEntry.get(i).setGroupId("2");
				}
				if(pendingListOnCqXnEntry.get(size).getReportNumber().equals(pendingListOnCqXnEntry.get(size-1).getReportNumber())) {
					for(int i=size;i>2;i--) {
						if(!pendingListOnCqXnEntry.get(size-1).getReportNumber().equals(pendingListOnCqXnEntry.get(size-2).getReportNumber())) {
							pendingListOnCqXnEntry.get(i).setGroupId("2");
							break;
						}
					}
					pendingListOnCqXnEntry.get(size).setGroupId("1");
				}
				for(int i=size*2;i<size*3;i++) {
					pendingListOnCqXnEntry.get(i).setGroupId("3");
				}
				for(int i=size*3;i<size*4;i++) {
					pendingListOnCqXnEntry.get(i).setGroupId("4");
				}
				if(pendingListOnCqXnEntry.get(size*3).getReportNumber().equals(pendingListOnCqXnEntry.get(size*3-1).getReportNumber())) {
					for(int i=size*4;i>size*3+2;i--) {
						if(!pendingListOnCqXnEntry.get(size*3-1).getReportNumber().equals(pendingListOnCqXnEntry.get(size*3-2).getReportNumber())) {
							pendingListOnCqXnEntry.get(i).setGroupId("4");
							break;
						}
					}
					pendingListOnCqXnEntry.get(size*3).setGroupId("3");
				}
				if(sizeleft==1) {
					if(pendingListOnCqXnEntry.get(size*4).getReportNumber().equals(pendingListOnCqXnEntry.get(size*4-1).getReportNumber())) {
						pendingListOnCqXnEntry.get(size*4).setGroupId("4");
					}else {
						int num=2;
						do {
							num=(int)(Math.random()*5);
							if(num!=4){
								break;
							}
						}while(true);
						if(num==0) {
							num=num+1;
						}
						pendingListOnCqXnEntry.get(size*4).setGroupId(String.valueOf(num));
					}
				}else if(sizeleft==2) {
					if(pendingListOnCqXnEntry.get(size*4).getReportNumber().equals(pendingListOnCqXnEntry.get(size*4-1).getReportNumber())) {
						pendingListOnCqXnEntry.get(size*4).setGroupId("4");
						int num=1;
						do {
							num=(int)(Math.random()*5);
							if(num!=4){
								break;
							}
						}while(true);	
						if(num==0) {
							num=num+1;
						}
						pendingListOnCqXnEntry.get(size*4+1).setGroupId(String.valueOf(num));
					}else {
						if(pendingListOnCqXnEntry.get(size*4).getReportNumber().equals(pendingListOnCqXnEntry.get(size*4+1).getReportNumber())) {
							int num=(int)(Math.random()*5);
							if(num==0) {
								num=num+1;
							}
							pendingListOnCqXnEntry.get(size*4).setGroupId(String.valueOf(num));
							pendingListOnCqXnEntry.get(size*4+1).setGroupId(String.valueOf(num));
						}else {
							int num=(int)(Math.random()*5);
							if(num==0) {
								num=num+1;
							}
							if(num-1==0) {
								pendingListOnCqXnEntry.get(size*4).setGroupId(String.valueOf(num));
								pendingListOnCqXnEntry.get(size*4+1).setGroupId(String.valueOf(num+1));
							}else{
								pendingListOnCqXnEntry.get(size*4).setGroupId(String.valueOf(num));
								pendingListOnCqXnEntry.get(size*4+1).setGroupId(String.valueOf(num-1));
							}						
						}	
					}									
				}else if(sizeleft==3) {
										
					if(pendingListOnCqXnEntry.get(size*4).getReportNumber().equals(pendingListOnCqXnEntry.get(size*4-1).getReportNumber())) {
						pendingListOnCqXnEntry.get(size*4).setGroupId("4");
						int num=3;
						do {
							num=(int)(Math.random()*5);
							if(num!=4){
								break;
							}
						}while(true);		
						if(num==0) {
							num=num+1;
						}
						if(pendingListOnCqXnEntry.get(size*4+1).getReportNumber().equals(pendingListOnCqXnEntry.get(size*4+2).getReportNumber())) {
							pendingListOnCqXnEntry.get(size*4+1).setGroupId(String.valueOf(num));
							pendingListOnCqXnEntry.get(size*4+2).setGroupId(String.valueOf(num));
						}else {													
							pendingListOnCqXnEntry.get(size*4+1).setGroupId(String.valueOf(num));
							if(num-1==0) {
								pendingListOnCqXnEntry.get(size*4+2).setGroupId(String.valueOf(num+1));
							}else{
								pendingListOnCqXnEntry.get(size*4+2).setGroupId(String.valueOf(num-1));
							}
						}
					}else {					
					
						if(pendingListOnCqXnEntry.get(size*4).getReportNumber().equals(pendingListOnCqXnEntry.get(size*4+1).getReportNumber())) {
							int num=(int)(Math.random()*5);
							if(num==0) {
								num=num+1;
							}
							pendingListOnCqXnEntry.get(size*4).setGroupId(String.valueOf(num));
							pendingListOnCqXnEntry.get(size*4+1).setGroupId(String.valueOf(num));
							if(num-1==0) {
								pendingListOnCqXnEntry.get(size*4+2).setGroupId(String.valueOf(num+1));
							}else{
								pendingListOnCqXnEntry.get(size*4+2).setGroupId(String.valueOf(num-1));
							}
						}else {
							if(pendingListOnCqXnEntry.get(size*4+1).getReportNumber().equals(pendingListOnCqXnEntry.get(size*4+2).getReportNumber())) {
								int num=(int)(Math.random()*5);
								if(num==0) {
									num=num+1;
								}
								pendingListOnCqXnEntry.get(size*4+1).setGroupId(String.valueOf(num));
								pendingListOnCqXnEntry.get(size*4+2).setGroupId(String.valueOf(num));
								if(num-1==0) {
									pendingListOnCqXnEntry.get(size*4).setGroupId(String.valueOf(num+1));
								}else{
									pendingListOnCqXnEntry.get(size*4).setGroupId(String.valueOf(num-1));
								}
							}else {
								pendingListOnCqXnEntry.get(size*4).setGroupId("1");
								pendingListOnCqXnEntry.get(size*4+1).setGroupId("3");							
								pendingListOnCqXnEntry.get(size*4+2).setGroupId("4");
							}						
						}
					}
				}
			}
		}		
		return pendingListOnCqXnEntry;
	}

	@Override
	public List<Pending> getPendingListOntpNoEntry(Pending pending) {
		List<Pending> pendingListOntpNoEntry = pendingMapper.getPendingListOntpNoEntry(pending);
		for(Pending p:pendingListOntpNoEntry) {
			p.setAreaType("6");
			if(!"-1".equals(pending.getGroupId())) {
				p.setGroupId("13");
			}		
		}
		return pendingListOntpNoEntry;
	}

	@Override
	public List<Pending> getPendingListOnjiaoNoEntry(Pending pending) {
		List<Pending> pendingListOnjiaoNoEntry = pendingMapper.getPendingListOnjiaoNoEntry(pending);
		String[] str= new String[7];
		str[0]="周至支公司理赔分部"; str[1]="长安支公司理赔分部"; str[2]="阎良支公司理赔分部";
		str[3]="临潼支公司理赔分部"; str[4]="蓝田支公司理赔分部"; str[5]="户县支公司理赔分部"; str[6]="高陵支公司理赔分部";
		for(Pending p:pendingListOnjiaoNoEntry) {
			p.setAreaType("7");
			if(!"-1".equals(pending.getGroupId())) {
				for(int i=0;i<str.length;i++) {
					if(str[i].equals(p.getGroupName())) {
						int groupId = i+5;
						p.setGroupId(String.valueOf(groupId));
						break;
					}
				}
			}			
		}
		return pendingListOnjiaoNoEntry;
	}
	@Override
	public List<Pending> getPendingListOnchengNoEntry(Pending pending) {
		//所有未结案
		List<Pending> pendingListOnchengNoEntry = pendingMapper.getPendingListOnchengNoEntry(pending);
		List<Pending> pendingListre = new ArrayList<Pending>();
		for(Pending p:pendingListOnchengNoEntry) {
			p.setAreaType("8");
			if(!"-1".equals(pending.getGroupId())) {
				if("1".equals(p.getGroupId()) || "2".equals(p.getGroupId()) || "3".equals(p.getGroupId()) || "4".equals(p.getGroupId()) || "12".equals(p.getGroupId()) ) {
							pendingListre.add(p);
				}
			}
			
		}
		if(!"-1".equals(pending.getGroupId())) {
			pendingListOnchengNoEntry.removeAll(pendingListre);
			int groupSize = pendingListOnchengNoEntry.size();
			if(groupSize>0) {
				if(groupSize%4==0) {
					int size=groupSize/4;
					for(int i=0;i<size;i++) {
						pendingListOnchengNoEntry.get(i).setGroupId("1");
					}
					for(int i=size;i<size*2;i++) {
						pendingListOnchengNoEntry.get(i).setGroupId("2");
					}
					if(pendingListOnchengNoEntry.get(size).getReportNumber().equals(pendingListOnchengNoEntry.get(size-1).getReportNumber())) {
						for(int i=size;i>2;i--) {
							if(!pendingListOnchengNoEntry.get(size-1).getReportNumber().equals(pendingListOnchengNoEntry.get(size-2).getReportNumber())) {
								pendingListOnchengNoEntry.get(i).setGroupId("2");
								break;
							}
						}
						pendingListOnchengNoEntry.get(size).setGroupId("1");
					}
					for(int i=size*2;i<size*3;i++) {
						pendingListOnchengNoEntry.get(i).setGroupId("3");
					}
					for(int i=size*3;i<size*4;i++) {
						pendingListOnchengNoEntry.get(i).setGroupId("4");
					}
					if(pendingListOnchengNoEntry.get(size*3).getReportNumber().equals(pendingListOnchengNoEntry.get(size*3-1).getReportNumber())) {
						for(int i=size*4;i>size*3+2;i--) {
							if(!pendingListOnchengNoEntry.get(size*3-1).getReportNumber().equals(pendingListOnchengNoEntry.get(size*3-2).getReportNumber())) {
								pendingListOnchengNoEntry.get(i).setGroupId("4");
								break;
							}
						}
						pendingListOnchengNoEntry.get(size*3).setGroupId("3");
					}
				}else {
					int sizeleft= groupSize%4;
					int size=groupSize/4;
					if(size>0) {				
						for(int i=0;i<size;i++) {
							pendingListOnchengNoEntry.get(i).setGroupId("1");
						}
						for(int i=size;i<size*2;i++) {
							pendingListOnchengNoEntry.get(i).setGroupId("2");
						}
						if(pendingListOnchengNoEntry.get(size).getReportNumber().equals(pendingListOnchengNoEntry.get(size-1).getReportNumber())) {
							for(int i=size;i>2;i--) {
								if(!pendingListOnchengNoEntry.get(size-1).getReportNumber().equals(pendingListOnchengNoEntry.get(size-2).getReportNumber())) {
									pendingListOnchengNoEntry.get(i).setGroupId("2");
									break;
								}
							}
							pendingListOnchengNoEntry.get(size).setGroupId("1");
						}
						for(int i=size*2;i<size*3;i++) {
							pendingListOnchengNoEntry.get(i).setGroupId("3");
						}
						for(int i=size*3;i<size*4;i++) {
							pendingListOnchengNoEntry.get(i).setGroupId("4");
						}
						if(pendingListOnchengNoEntry.get(size*3).getReportNumber().equals(pendingListOnchengNoEntry.get(size*3-1).getReportNumber())) {
							for(int i=size*4;i>size*3+2;i--) {
								if(!pendingListOnchengNoEntry.get(size*3-1).getReportNumber().equals(pendingListOnchengNoEntry.get(size*3-2).getReportNumber())) {
									pendingListOnchengNoEntry.get(i).setGroupId("4");
									break;
								}
							}
							pendingListOnchengNoEntry.get(size*3).setGroupId("3");
						}
						
						if(sizeleft==1) {
							if(pendingListOnchengNoEntry.get(size*4).getReportNumber().equals(pendingListOnchengNoEntry.get(size*4-1).getReportNumber())) {
								pendingListOnchengNoEntry.get(size*4).setGroupId("4");
							}else {
								int num=1;
								do {
									num=(int)(Math.random()*5);
									if(num!=4){
										break;
									}
								}while(true);
								if(num==0) {
									num=num+1;
								}
								pendingListOnchengNoEntry.get(size*4).setGroupId(String.valueOf(num));
							}						
						}else if(sizeleft==2) {
							if(pendingListOnchengNoEntry.get(size*4).getReportNumber().equals(pendingListOnchengNoEntry.get(size*4-1).getReportNumber())) {
								pendingListOnchengNoEntry.get(size*4).setGroupId("4");
								int num=2;
								do {
									num=(int)(Math.random()*5);
									if(num!=4){
										break;
									}
								}while(true);		
								if(num==0) {
									num=num+1;
								}
								pendingListOnchengNoEntry.get(size*4+1).setGroupId(String.valueOf(num));
							}else {
								if(pendingListOnchengNoEntry.get(size*4).getReportNumber().equals(pendingListOnchengNoEntry.get(size*4+1).getReportNumber())) {
									int num=(int)(Math.random()*5);
									if(num==0) {
										num=num+1;
									}
									pendingListOnchengNoEntry.get(size*4).setGroupId(String.valueOf(num));
									pendingListOnchengNoEntry.get(size*4+1).setGroupId(String.valueOf(num));
								}else {
									int num=(int)(Math.random()*5);
									if(num==0) {
										num=num+1;
									}
									if(num-1==0) {
										pendingListOnchengNoEntry.get(size*4).setGroupId(String.valueOf(num));
										pendingListOnchengNoEntry.get(size*4+1).setGroupId(String.valueOf(num+1));
									}else{
										pendingListOnchengNoEntry.get(size*4).setGroupId(String.valueOf(num));
										pendingListOnchengNoEntry.get(size*4+1).setGroupId(String.valueOf(num-1));
									}						
								}
							}															
						}else if(sizeleft==3) {
							
							if(pendingListOnchengNoEntry.get(size*4).getReportNumber().equals(pendingListOnchengNoEntry.get(size*4-1).getReportNumber())) {
								pendingListOnchengNoEntry.get(size*4).setGroupId("4");
								int num=3;
								do {
									num=(int)(Math.random()*5);
									if(num!=4 ){
										break;
									}
								}while(true);
								if(num==0) {
									num=num+1;
								}
								if(pendingListOnchengNoEntry.get(size*4+1).getReportNumber().equals(pendingListOnchengNoEntry.get(size*4+2).getReportNumber())) {
									pendingListOnchengNoEntry.get(size*4+1).setGroupId(String.valueOf(num));
									pendingListOnchengNoEntry.get(size*4+2).setGroupId(String.valueOf(num));
								}else {													
									pendingListOnchengNoEntry.get(size*4+1).setGroupId(String.valueOf(num));
									if(num-1==0) {
										pendingListOnchengNoEntry.get(size*4+2).setGroupId(String.valueOf(num+1));
									}else{
										pendingListOnchengNoEntry.get(size*4+2).setGroupId(String.valueOf(num-1));
									}
								}
							}else {
								if(pendingListOnchengNoEntry.get(size*4).getReportNumber().equals(pendingListOnchengNoEntry.get(size*4+1).getReportNumber())) {
									int num=(int)(Math.random()*5);
									if(num==0) {
										num=num+1;
									}
									pendingListOnchengNoEntry.get(size*4).setGroupId(String.valueOf(num));
									pendingListOnchengNoEntry.get(size*4+1).setGroupId(String.valueOf(num));
									if(num-1==0) {
										pendingListOnchengNoEntry.get(size*4+2).setGroupId(String.valueOf(num+1));
									}else{
										pendingListOnchengNoEntry.get(size*4+2).setGroupId(String.valueOf(num-1));
									}
								}else {
									if(pendingListOnchengNoEntry.get(size*4+1).getReportNumber().equals(pendingListOnchengNoEntry.get(size*4+2).getReportNumber())) {
										int num=(int)(Math.random()*5);
										if(num==0) {
											num=num+1;
										}
										pendingListOnchengNoEntry.get(size*4+1).setGroupId(String.valueOf(num));
										pendingListOnchengNoEntry.get(size*4+2).setGroupId(String.valueOf(num));
										if(num-1==0) {
											pendingListOnchengNoEntry.get(size*4).setGroupId(String.valueOf(num+1));
										}else{
											pendingListOnchengNoEntry.get(size*4).setGroupId(String.valueOf(num-1));
										}
									}else {
										pendingListOnchengNoEntry.get(size*4).setGroupId("1");
										pendingListOnchengNoEntry.get(size*4+1).setGroupId("3");							
										pendingListOnchengNoEntry.get(size*4+2).setGroupId("4");
									}						
								}
							}				
							
						}
					}else {
						if(sizeleft==1) {
							int  num=(int)(Math.random()*5);
							if(num==0) {
								num=num+1;
							}
							pendingListOnchengNoEntry.get(0).setGroupId(String.valueOf(num));
						}else if(sizeleft==2) {
							if(pendingListOnchengNoEntry.get(0).getReportNumber().equals(pendingListOnchengNoEntry.get(1).getReportNumber())) {
								int  num=(int)(Math.random()*5);
								if(num==0) {
									num=num+1;
								}
								pendingListOnchengNoEntry.get(0).setGroupId(String.valueOf(num));
								pendingListOnchengNoEntry.get(1).setGroupId(String.valueOf(num));
							}else {
								int  num=(int)(Math.random()*5);
								if(num==0) {
									num=num+1;
								}
								if(num-1==0) {
									pendingListOnchengNoEntry.get(0).setGroupId(String.valueOf(num));
									pendingListOnchengNoEntry.get(1).setGroupId(String.valueOf(num+1));
								}else {
									pendingListOnchengNoEntry.get(0).setGroupId(String.valueOf(num));
									pendingListOnchengNoEntry.get(1).setGroupId(String.valueOf(num-1));
								}
								
							}
						}else {
							if(pendingListOnchengNoEntry.get(0).getReportNumber().equals(pendingListOnchengNoEntry.get(1).getReportNumber())) {
								int  num=(int)(Math.random()*5);
								if(num==0) {
									num=num+1;
								}
								pendingListOnchengNoEntry.get(0).setGroupId(String.valueOf(num));
								pendingListOnchengNoEntry.get(1).setGroupId(String.valueOf(num));
								if(num-1==0) {
									pendingListOnchengNoEntry.get(2).setGroupId(String.valueOf(num+1));
								}else {
									pendingListOnchengNoEntry.get(2).setGroupId(String.valueOf(num-1));
								}
								
							}else if (pendingListOnchengNoEntry.get(1).getReportNumber().equals(pendingListOnchengNoEntry.get(2).getReportNumber())){
								int  num=(int)(Math.random()*5);
								if(num==0) {
									num=num+1;
								}
								pendingListOnchengNoEntry.get(1).setGroupId(String.valueOf(num));
								pendingListOnchengNoEntry.get(2).setGroupId(String.valueOf(num));
								if(num-1==0) {
									pendingListOnchengNoEntry.get(0).setGroupId(String.valueOf(num+1));
								}else {
									pendingListOnchengNoEntry.get(0).setGroupId(String.valueOf(num-1));
								}
								
							}else {
								
								do {
									int  num0=(int)(Math.random()*5);
									if(num0!=0 && num0!=2){
										pendingListOnchengNoEntry.get(0).setGroupId(String.valueOf(num0));
										break;
									}
								}while(true);
								do {
									int  num=(int)(Math.random()*5);
									if(num!=0 && num!=2){									
										pendingListOnchengNoEntry.get(1).setGroupId(String.valueOf(num));
										break;
									}
								}while(true);
								
								do {
									int  num1=(int)(Math.random()*5);
									if(num1!=0 && num1!=2){									
										pendingListOnchengNoEntry.get(2).setGroupId(String.valueOf(num1));
										break;
									}
								}while(true);
								
								
							}
						}
						
						
					}
				}
			}
			pendingListOnchengNoEntry.addAll(pendingListre);
		}
		return pendingListOnchengNoEntry;
	}

	@Override
	public int updatePendingAreaGroupType(List<Pending> pendingList) {
		return pendingMapper.updatePendingAreaGroupType(pendingList);
	}

	@Override
	public List<Map<String, Object>> getPengdingGroup(Pending pending) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String, Object>> pendingListStation = pendingMapper.getPengdingGroup(pending);
		 for(Map<String, Object> mapEntity : pendingListStation) {
			 if(null!=mapEntity.get("risk_date")) {
	           		if(!String.valueOf(mapEntity.get("risk_date")).equals("") || null!=String.valueOf(mapEntity.get("risk_date"))){
	           			try {
							mapEntity.put("risk_date", sdf.format(sdf.parse(String.valueOf(mapEntity.get("risk_date")))));
						} catch (ParseException e) {
							e.printStackTrace();
						}   	 
		            }
	           }else {
	           		mapEntity.put("risk_date", "");
	           }
			 if(null==mapEntity.get("name")) {
				 mapEntity.put("name", "");
			 }
			 if(null==mapEntity.get("damage_code")) {
				 mapEntity.put("damage_code", "");
			 }
			 if(null==mapEntity.get("damage_name")) {
				 mapEntity.put("damage_name", "");
			 }
			 if(null==mapEntity.get("adjuster_code")) {
				 mapEntity.put("adjuster_code", "");
			 }
			 if(null==mapEntity.get("adjuster_name")) {
				 mapEntity.put("adjuster_name", "");
			 }
			 if(null==mapEntity.get("loss")) {
				 mapEntity.put("loss", "");
			 }
 			if(null==mapEntity.get("user_info")) {
 				mapEntity.put("user_info", "");
 			}
		 } 
		return pendingListStation;
	}

	@Override
	public PageInfo<Map<String, Object>> getPagePengdingGroup(Pending pending) {
		PageHelper.startPage(pending.getPageNum(),pending.getLength());
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 List<Map<String, Object>> pendingListStation = pendingMapper.getPengdingGroup(pending);
		 for(Map<String, Object> mapEntity : pendingListStation) {
			 if(null!=mapEntity.get("risk_date")) {
	           		if(!String.valueOf(mapEntity.get("risk_date")).equals("") || null!=String.valueOf(mapEntity.get("risk_date"))){
	           			try {
							mapEntity.put("risk_date", sdf.format(sdf.parse(String.valueOf(mapEntity.get("risk_date")))));
						} catch (ParseException e) {
							e.printStackTrace();
						}   	 
		            }
	           }else {
	           		mapEntity.put("risk_date", "");
	           }
			 if(null==mapEntity.get("name")) {
				 mapEntity.put("name", "");
			 }
		 } 
		 PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(pendingListStation);
		return pageInfo;
	}

	@Override
	public int updatePendingInfoById(Pending pending) {
		return pendingMapper.updatePendingInfoById(pending);
	}

	@Override
	public int updatePendingInfoList(List<Pending> pendingList) {
		return pendingMapper.updatePendingInfoList(pendingList);
	}

	@Override
	public int updateAllCaseUserInfo(Pending pending) {
		return pendingMapper.updateAllCaseUserInfo(pending);
	}

	@Override
	public int updateCaseUseredInfo(Pending pending) {
		return pendingMapper.updateCaseUseredInfo(pending);
	}

	@Override
	public int updateCaseUserGroup(Pending pending) {
		return pendingMapper.updateCaseUserGroup(pending);
	}

	@Override
	public List<Pending> getPendingListGroupUser(Pending pending) {
		// 获取
		List<Pending> pendingListGroupUser = pendingMapper.getPendingListGroupUser(pending);
		//平均分案件
		int groupSize = pendingListGroupUser.size();
		if(groupSize>0) {
			if(groupSize%4==0) {
				int size=groupSize/4;
				for(int i=0;i<size;i++) {
					pendingListGroupUser.get(i).setGroupId("1");
				}
				for(int i=size;i<size*2;i++) {
					pendingListGroupUser.get(i).setGroupId("2");
				}
				if(pendingListGroupUser.get(size).getReportNumber().equals(pendingListGroupUser.get(size-1).getReportNumber())) {
					for(int i=size;i>2;i--) {
						if(!pendingListGroupUser.get(size-1).getReportNumber().equals(pendingListGroupUser.get(size-2).getReportNumber())) {
							pendingListGroupUser.get(i).setGroupId("2");
							break;
						}
					}
					pendingListGroupUser.get(size).setGroupId("1");
				}
				for(int i=size*2;i<size*3;i++) {
					pendingListGroupUser.get(i).setGroupId("3");
				}
				for(int i=size*3;i<size*4;i++) {
					pendingListGroupUser.get(i).setGroupId("4");
				}
				if(pendingListGroupUser.get(size*3).getReportNumber().equals(pendingListGroupUser.get(size*3-1).getReportNumber())) {
					for(int i=size*4;i>size*3+2;i--) {
						if(!pendingListGroupUser.get(size*3-1).getReportNumber().equals(pendingListGroupUser.get(size*3-2).getReportNumber())) {
							pendingListGroupUser.get(i).setGroupId("4");
							break;
						}
					}
					pendingListGroupUser.get(size*3).setGroupId("3");
				}
			}else {
				int sizeleft= groupSize%4;
				int size=groupSize/4;
				if(size>0) {				
					for(int i=0;i<size;i++) {
						pendingListGroupUser.get(i).setGroupId("1");
					}
					for(int i=size;i<size*2;i++) {
						pendingListGroupUser.get(i).setGroupId("2");
					}
					if(pendingListGroupUser.get(size).getReportNumber().equals(pendingListGroupUser.get(size-1).getReportNumber())) {
						for(int i=size;i>2;i--) {
							if(!pendingListGroupUser.get(size-1).getReportNumber().equals(pendingListGroupUser.get(size-2).getReportNumber())) {
								pendingListGroupUser.get(i).setGroupId("2");
								break;
							}
						}
						pendingListGroupUser.get(size).setGroupId("1");
					}
					for(int i=size*2;i<size*3;i++) {
						pendingListGroupUser.get(i).setGroupId("3");
					}
					for(int i=size*3;i<size*4;i++) {
						pendingListGroupUser.get(i).setGroupId("4");
					}
					if(pendingListGroupUser.get(size*3).getReportNumber().equals(pendingListGroupUser.get(size*3-1).getReportNumber())) {
						for(int i=size*4;i>size*3+2;i--) {
							if(!pendingListGroupUser.get(size*3-1).getReportNumber().equals(pendingListGroupUser.get(size*3-2).getReportNumber())) {
								pendingListGroupUser.get(i).setGroupId("4");
								break;
							}
						}
						pendingListGroupUser.get(size*3).setGroupId("3");
					}
					
					if(sizeleft==1) {
						if(pendingListGroupUser.get(size*4).getReportNumber().equals(pendingListGroupUser.get(size*4-1).getReportNumber())) {
							pendingListGroupUser.get(size*4).setGroupId("4");
						}else {
							int num=1;
							do {
								num=(int)(Math.random()*5);
								if(num!=4){
									break;
								}
							}while(true);
							if(num==0) {
								num=num+1;
							}
							pendingListGroupUser.get(size*4).setGroupId(String.valueOf(num));
						}						
					}else if(sizeleft==2) {
						if(pendingListGroupUser.get(size*4).getReportNumber().equals(pendingListGroupUser.get(size*4-1).getReportNumber())) {
							pendingListGroupUser.get(size*4).setGroupId("4");
							int num=2;
							do {
								num=(int)(Math.random()*5);
								if(num!=4){
									break;
								}
							}while(true);		
							if(num==0) {
								num=num+1;
							}
							pendingListGroupUser.get(size*4+1).setGroupId(String.valueOf(num));
						}else {
							if(pendingListGroupUser.get(size*4).getReportNumber().equals(pendingListGroupUser.get(size*4+1).getReportNumber())) {
								int num=(int)(Math.random()*5);
								if(num==0) {
									num=num+1;
								}
								pendingListGroupUser.get(size*4).setGroupId(String.valueOf(num));
								pendingListGroupUser.get(size*4+1).setGroupId(String.valueOf(num));
							}else {
								int num=(int)(Math.random()*5);
								if(num==0) {
									num=num+1;
								}
								if(num-1==0) {
									pendingListGroupUser.get(size*4).setGroupId(String.valueOf(num));
									pendingListGroupUser.get(size*4+1).setGroupId(String.valueOf(num+1));
								}else{
									pendingListGroupUser.get(size*4).setGroupId(String.valueOf(num));
									pendingListGroupUser.get(size*4+1).setGroupId(String.valueOf(num-1));
								}						
							}
						}															
					}else if(sizeleft==3) {
						
						if(pendingListGroupUser.get(size*4).getReportNumber().equals(pendingListGroupUser.get(size*4-1).getReportNumber())) {
							pendingListGroupUser.get(size*4).setGroupId("4");
							int num=3;
							do {
								num=(int)(Math.random()*5);
								if(num!=4 ){
									break;
								}
							}while(true);
							if(num==0) {
								num=num+1;
							}
							if(pendingListGroupUser.get(size*4+1).getReportNumber().equals(pendingListGroupUser.get(size*4+2).getReportNumber())) {
								pendingListGroupUser.get(size*4+1).setGroupId(String.valueOf(num));
								pendingListGroupUser.get(size*4+2).setGroupId(String.valueOf(num));
							}else {													
								pendingListGroupUser.get(size*4+1).setGroupId(String.valueOf(num));
								if(num-1==0) {
									pendingListGroupUser.get(size*4+2).setGroupId(String.valueOf(num+1));
								}else{
									pendingListGroupUser.get(size*4+2).setGroupId(String.valueOf(num-1));
								}
							}
						}else {
							if(pendingListGroupUser.get(size*4).getReportNumber().equals(pendingListGroupUser.get(size*4+1).getReportNumber())) {
								int num=(int)(Math.random()*5);
								if(num==0) {
									num=num+1;
								}
								pendingListGroupUser.get(size*4).setGroupId(String.valueOf(num));
								pendingListGroupUser.get(size*4+1).setGroupId(String.valueOf(num));
								if(num-1==0) {
									pendingListGroupUser.get(size*4+2).setGroupId(String.valueOf(num+1));
								}else{
									pendingListGroupUser.get(size*4+2).setGroupId(String.valueOf(num-1));
								}
							}else {
								if(pendingListGroupUser.get(size*4+1).getReportNumber().equals(pendingListGroupUser.get(size*4+2).getReportNumber())) {
									int num=(int)(Math.random()*5);
									if(num==0) {
										num=num+1;
									}
									pendingListGroupUser.get(size*4+1).setGroupId(String.valueOf(num));
									pendingListGroupUser.get(size*4+2).setGroupId(String.valueOf(num));
									if(num-1==0) {
										pendingListGroupUser.get(size*4).setGroupId(String.valueOf(num+1));
									}else{
										pendingListGroupUser.get(size*4).setGroupId(String.valueOf(num-1));
									}
								}else {
									pendingListGroupUser.get(size*4).setGroupId("1");
									pendingListGroupUser.get(size*4+1).setGroupId("3");							
									pendingListGroupUser.get(size*4+2).setGroupId("4");
								}						
							}
						}				
						
					}
				}else {
					if(sizeleft==1) {
						int  num=(int)(Math.random()*5);
						if(num==0) {
							num=num+1;
						}else if(num==2){
							num=num+1;
						}
						pendingListGroupUser.get(0).setGroupId(String.valueOf(num));
					}else if(sizeleft==2) {
						if(pendingListGroupUser.get(0).getReportNumber().equals(pendingListGroupUser.get(1).getReportNumber())) {
							int  num=(int)(Math.random()*5);
							if(num==0) {
								num=num+1;
							}else if(num==2){
								num=num+1;
							}
							pendingListGroupUser.get(0).setGroupId(String.valueOf(num));
							pendingListGroupUser.get(1).setGroupId(String.valueOf(num));
						}else {
							int  num=(int)(Math.random()*5);
							if(num==0) {
								num=num+1;
							}else if(num==2){
								num=num+2;
							}
							if(num-1==0) {
								pendingListGroupUser.get(0).setGroupId(String.valueOf(num));
								pendingListGroupUser.get(1).setGroupId(String.valueOf(num+1));
							}else {
								pendingListGroupUser.get(0).setGroupId(String.valueOf(num));
								pendingListGroupUser.get(1).setGroupId(String.valueOf(num-1));
							}
							
						}
					}else {
						if(pendingListGroupUser.get(0).getReportNumber().equals(pendingListGroupUser.get(1).getReportNumber())) {
							int  num=(int)(Math.random()*5);
							if(num==0) {
								num=num+1;
							}else if(num==2){
								num=num+2;
							}
							pendingListGroupUser.get(0).setGroupId(String.valueOf(num));
							pendingListGroupUser.get(1).setGroupId(String.valueOf(num));
							if(num-1==0) {
								pendingListGroupUser.get(2).setGroupId(String.valueOf(num+1));
							}else {
								pendingListGroupUser.get(2).setGroupId(String.valueOf(num-1));
							}
							
						}else if (pendingListGroupUser.get(1).getReportNumber().equals(pendingListGroupUser.get(2).getReportNumber())){
							int  num=(int)(Math.random()*5);
							if(num==0) {
								num=num+1;
							}else if(num==2){
								num=num+2;
							}
							pendingListGroupUser.get(1).setGroupId(String.valueOf(num));
							pendingListGroupUser.get(2).setGroupId(String.valueOf(num));
							if(num-1==0) {
								pendingListGroupUser.get(0).setGroupId(String.valueOf(num+1));
							}else {
								pendingListGroupUser.get(0).setGroupId(String.valueOf(num-1));
							}
							
						}else {
							
							do {
								int  num0=(int)(Math.random()*5);
								if(num0!=0 && num0!=2){
									pendingListGroupUser.get(0).setGroupId(String.valueOf(num0));
									break;
								}
							}while(true);
							do {
								int  num=(int)(Math.random()*5);
								if(num!=0 && num!=2){									
									pendingListGroupUser.get(1).setGroupId(String.valueOf(num));
									break;
								}
							}while(true);
							
							do {
								int  num1=(int)(Math.random()*5);
								if(num1!=0 && num1!=2){									
									pendingListGroupUser.get(2).setGroupId(String.valueOf(num1));
									break;
								}
							}while(true);
							
							
						}
					}
					
					
				}
			}
		}
		return pendingListGroupUser;
	}

}
