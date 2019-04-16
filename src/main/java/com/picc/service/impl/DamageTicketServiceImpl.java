package com.picc.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picc.dao.DamageTicketMapper;
import com.picc.entity.DamageTicket;
import com.picc.service.DamageTicketService;
/**
 * 功效 损核 数据处理
 * @author wangXi
 * @date 2019/03/04
 */
@Service
public class DamageTicketServiceImpl implements DamageTicketService {
	
	@Autowired
	private DamageTicketMapper damageTicketMapper;
	
	@Override
	public int saveDamageTicketList(List<DamageTicket> damageTicketList) {
		return damageTicketMapper.saveDamageTicketList(damageTicketList);
	}

	@Override
	public List<Map<String, Object>> getUserDamageMonthOrYearList(Map<String, Object> entryMap) {
		//返回集合
		List<Map<String,Object>> allList  =new ArrayList<Map<String,Object>>();
		// 参数	
		entryMap.put("repair", "1"); //修理厂 4s店  区分
		entryMap.put("money", "3"); //所有4s点
		List<Map<String, Object>> allmiloDamageList = damageTicketMapper.getUserDamageList(entryMap);  //合计4S店   
		entryMap.put("money", "2"); //万元以下下
		List<Map<String, Object>> miloDamageLists = damageTicketMapper.getUserDamageList(entryMap);  //万元以下 4S店 		
		entryMap.put("money", "1"); //万元以上
		List<Map<String, Object>> miloeDamageLists = damageTicketMapper.getUserDamageList(entryMap);  //万元以上 4S店
							
		List<Map<String,Object>> allLists  =new ArrayList<Map<String,Object>>();//4s店统计
		//4S店
		for(Map<String, Object> str:allmiloDamageList) {
			//找出万元以下
			for(Map<String, Object> entry:miloDamageLists) {
				if(entry.get("repair_name").equals(str.get("repair_name"))) {
					entry.put("milo", "万元以下");
					entry.put("type", "4S店");
					allLists.add(entry);
					break;
				}
			}
			for(Map<String, Object> entry:miloeDamageLists) {
				if(entry.get("repair_name").equals(str.get("repair_name"))) {
					entry.put("milo", "万元以上");
					entry.put("type", "4S店");
					entry.put("lownumber", "0");
					entry.put("lowmoney", "0");
					allLists.add(entry);
					break;
				}
			}
		//4S某点店合计
		str.put("milo", "合计");
		allLists.add(str);	
		}
		//合计统计 querySum
		entryMap.put("money", "2"); //所有4s点 万元以下
		Map<String, Object> querySumDamage = damageTicketMapper.querySumDamage(entryMap);//4s合计万元以下
		if(null!=querySumDamage) {
			querySumDamage.put("repair_name", "4s店合计");
			querySumDamage.put("milo", "万元以下");
			querySumDamage.put("type", "4S店");
			allLists.add(querySumDamage);
		}
		
		entryMap.put("money", "1"); //所有4s店 万元 以上
		Map<String, Object> querySumDamage2 = damageTicketMapper.querySumDamage(entryMap);//4s合计万元以上
		if(null!=querySumDamage2) {
			querySumDamage2.put("repair_name", "4s店合计");
			querySumDamage2.put("milo","万元以上");
			querySumDamage2.put("type", "4S店");
			querySumDamage2.put("lownumber", "0");
			querySumDamage2.put("lowmoney", "0");
			allLists.add(querySumDamage2);
		}		
		entryMap.put("money", "3"); //所有4s店 合计
		Map<String, Object> querySumDamage3 = damageTicketMapper.querySumDamage(entryMap);
		if(null!=querySumDamage3) {
			querySumDamage3.put("repair_name", "4s店合计");
			querySumDamage3.put("type", "4S店");
			querySumDamage3.put("milo","合计");
			allLists.add(querySumDamage3);
		}
		
		//修理厂		
		entryMap.put("repair", "0"); //修理厂 4s店  区分  参数0代表修理厂
		entryMap.put("money", "3"); //所有修理厂
		List<Map<String, Object>> userDamageList3 = damageTicketMapper.getUserDamageList(entryMap);  //合计4S店   
		entryMap.put("money", "2"); //万元以下
		List<Map<String, Object>> userDamageList = damageTicketMapper.getUserDamageList(entryMap); //修理厂的万元以下
		entryMap.put("money", "1"); //万元以以上
		List<Map<String, Object>> userDamageList1 = damageTicketMapper.getUserDamageList(entryMap); //修理厂的万元以上
		
		//修理厂
				for(Map<String, Object> str:userDamageList3) {
					//找出万元以下
					for(Map<String, Object> entry:userDamageList) {
						if(entry.get("repair_name").equals(str.get("repair_name"))) {
							entry.put("milo", "万元以下");
							entry.put("type", "维修厂");
							allLists.add(entry);
							break;
						}
					}
					//找出万元以上的
					for(Map<String, Object> entry:userDamageList1) {
						if(entry.get("repair_name").equals(str.get("repair_name"))) {
							entry.put("milo", "万元以上");
							entry.put("type", "维修厂");
							allLists.add(entry);
							break;
						}
					}
				//修理厂合计合计
				str.put("milo", "合计");
				allLists.add(str);	
				}
				//合计统计 querySum
				entryMap.put("money", "2"); //所有修理厂点 万元以下
				Map<String, Object> querySumDamage11 = damageTicketMapper.querySumDamage(entryMap);//4s合计万元以下
				if(null!=querySumDamage11) {
					querySumDamage11.put("repair_name", "维修厂合计");
					querySumDamage11.put("type", "维修厂");
					querySumDamage11.put("milo","万元以下");
					allLists.add(querySumDamage11);
				}	
				entryMap.put("money", "1"); //所有修理厂 万元 以上
				Map<String, Object> querySumDamage12 = damageTicketMapper.querySumDamage(entryMap);//4s合计万元以上
				if(null!=querySumDamage12) {
					querySumDamage12.put("repair_name", "维修厂合计");
					querySumDamage12.put("type", "维修厂");
					querySumDamage12.put("milo","万元以上");
					allLists.add(querySumDamage12);
				}			
				entryMap.put("money", "3"); //所有修理厂  合计
				Map<String, Object> querySumDamage13 = damageTicketMapper.querySumDamage(entryMap);
				if(null!=querySumDamage13) {
					querySumDamage13.put("repair_name", "维修厂合计");
					querySumDamage13.put("type", "维修厂");
					querySumDamage13.put("milo","合计");
					allLists.add(querySumDamage13);
				}				
				//所有统计
				entryMap.put("repair", null); 
				entryMap.put("money", "2"); //所有 万元以下
				Map<String, Object> querySumDamageAll1 = damageTicketMapper.querySumDamage(entryMap);//所有万元以下
				if(null!=querySumDamageAll1) {
					querySumDamageAll1.put("repair_name", "合计");
					
					querySumDamageAll1.put("milo","万元以下");
					allLists.add(querySumDamageAll1);
				}
				
				entryMap.put("money", "1"); //所有 万元 以上
				Map<String, Object> querySumDamageAll2 = damageTicketMapper.querySumDamage(entryMap);//所有万元以上
				if(null!=querySumDamageAll2) {
					querySumDamageAll2.put("repair_name", "合计");
					querySumDamageAll2.put("milo","万元以上");
					querySumDamageAll2.put("lownumber", "0");
					querySumDamageAll2.put("lowmoney", "0");
					allLists.add(querySumDamageAll2);
				}
				
				entryMap.put("money", "3"); // 合计
				Map<String, Object> querySumDamageAll3 = damageTicketMapper.querySumDamage(entryMap);//所有万元以上
				if(null!=querySumDamageAll3) {
					querySumDamageAll3.put("repair_name", "合计");
					querySumDamageAll3.put("milo","合计");
					allLists.add(querySumDamageAll3);
				}
									
		return allLists;
	}

	
	@Override
	public List<Map<String, Object>> getGroupDamageMonthOrYearList(Map<String, Object> entryMap) {
		//返回集合
				List<Map<String,Object>> allList  =new ArrayList<Map<String,Object>>();
				// 参数	
				entryMap.put("repair", "1"); //修理厂 4s店  区分
				entryMap.put("money", "3"); //所有4s点
				List<Map<String, Object>> allmiloDamageList = damageTicketMapper.getUserDamageList(entryMap);  //合计4S店   
				entryMap.put("money", "2"); //万元以下下
				List<Map<String, Object>> miloDamageLists = damageTicketMapper.getUserDamageList(entryMap);  //万元以下 4S店 		
				entryMap.put("money", "1"); //万元以上
				List<Map<String, Object>> miloeDamageLists = damageTicketMapper.getUserDamageList(entryMap);  //万元以上 4S店
									
				List<Map<String,Object>> allLists  =new ArrayList<Map<String,Object>>();//4s店统计
				//4S店
				for(Map<String, Object> str:allmiloDamageList) {
					//找出万元以下
					for(Map<String, Object> entry:miloDamageLists) {
						if(entry.get("repair_name").equals(str.get("repair_name"))) {
							entry.put("milo", "万元以下");
							entry.put("type", "4S店");
							entry.put("lownumbers", "0");
							entry.put("lowmoneys", "0");
							allLists.add(entry);
							break;
						}
					}
					for(Map<String, Object> entry:miloeDamageLists) {
						if(entry.get("repair_name").equals(str.get("repair_name"))) {
							entry.put("milo", "万元以上");
							entry.put("type", "4S店");
							entry.put("lownumber", "0");
							entry.put("lowmoney", "0");
							allLists.add(entry);
							break;
						}
					}
				//4S某点店合计
				str.put("milo", "合计");
				allLists.add(str);	
				}
				//合计统计 querySum
				entryMap.put("money", "2"); //所有4s点 万元以下
				Map<String, Object> querySumDamage = damageTicketMapper.querySumDamage(entryMap);//4s合计万元以下
				if(null!=querySumDamage) {
					querySumDamage.put("repair_name", "4s店合计");
					querySumDamage.put("type", "4S店");
					querySumDamage.put("milo", "万元以下");
					querySumDamage.put("lownumbers", "0");
					querySumDamage.put("lowmoneys", "0");
					allLists.add(querySumDamage);
				}
				
				entryMap.put("money", "1"); //所有4s店 万元 以上
				Map<String, Object> querySumDamage2 = damageTicketMapper.querySumDamage(entryMap);//4s合计万元以上
				if(null!=querySumDamage2) {
					querySumDamage2.put("repair_name", "4s店合计");
					querySumDamage2.put("milo","万元以上");
					querySumDamage2.put("type", "4S店");
					querySumDamage2.put("lownumber", "0");
					querySumDamage2.put("lowmoney", "0");
					allLists.add(querySumDamage2);
				}		
				entryMap.put("money", "3"); //所有4s店 合计
				Map<String, Object> querySumDamage3 = damageTicketMapper.querySumDamage(entryMap);
				if(null!=querySumDamage3) {
					querySumDamage3.put("repair_name", "4s店合计");
					querySumDamage3.put("type", "4S店");
					querySumDamage3.put("milo","合计");
					allLists.add(querySumDamage3);
				}
				
				//修理厂		
				entryMap.put("repair", "0"); //修理厂 4s店  区分  参数0代表修理厂
				entryMap.put("money", "3"); //所有修理厂
				List<Map<String, Object>> userDamageList3 = damageTicketMapper.getUserDamageList(entryMap);  //合计4S店   
				entryMap.put("money", "2"); //万元以下
				List<Map<String, Object>> userDamageList = damageTicketMapper.getUserDamageList(entryMap); //万元以下
				entryMap.put("money", "1"); //万元以以上
				List<Map<String, Object>> userDamageList1 = damageTicketMapper.getUserDamageList(entryMap); //万元以上
				
				//修理厂
						for(Map<String, Object> str:userDamageList3) {
							//找出万元以下
							for(Map<String, Object> entry:userDamageList) {
								if(entry.get("repair_name").equals(str.get("repair_name"))) {
									entry.put("milo", "万元以下");
									entry.put("type", "维修厂");
									entry.put("lownumbers", "0");
									entry.put("lowmoneys", "0");
									allLists.add(entry);
									break;
								}
							}
							//找出万元以上的
							for(Map<String, Object> entry:userDamageList1) {
								if(entry.get("repair_name").equals(str.get("repair_name"))) {
									entry.put("milo", "万元以上");
									entry.put("type", "维修厂");
									entry.put("lownumber", "0");
									entry.put("lowmoney", "0");
									allLists.add(entry);
									break;
								}
							}
						//修理厂合计合计
						str.put("milo", "合计");
						allLists.add(str);	
						}
						//合计统计 querySum
						entryMap.put("money", "2"); //所有修理厂点 万元以下
						Map<String, Object> querySumDamage11 = damageTicketMapper.querySumDamage(entryMap);//合计万元以下
						if(null!=querySumDamage11) {
							querySumDamage11.put("repair_name", "维修厂合计");
							querySumDamage11.put("type", "维修厂");
							querySumDamage11.put("milo","万元以下");
							allLists.add(querySumDamage11);
						}	
						entryMap.put("money", "1"); //所有修理厂 万元 以上
						Map<String, Object> querySumDamage12 = damageTicketMapper.querySumDamage(entryMap);//合计万元以上
						if(null!=querySumDamage12) {
							querySumDamage12.put("repair_name", "维修厂合计");
							querySumDamage12.put("type", "维修厂");
							querySumDamage12.put("milo","万元以上");
							allLists.add(querySumDamage12);
						}			
						entryMap.put("money", "3"); //所有修理厂  合计
						Map<String, Object> querySumDamage13 = damageTicketMapper.querySumDamage(entryMap);
						if(null!=querySumDamage13) {
							querySumDamage13.put("repair_name", "维修厂合计");
							querySumDamage13.put("milo","合计");
							allLists.add(querySumDamage13);
						}				
						//所有统计
						entryMap.put("repair", null); 
						entryMap.put("money", "2"); //所有 万元以下
						Map<String, Object> querySumDamageAll1 = damageTicketMapper.querySumDamage(entryMap);//所有万元以下
						if(null!=querySumDamageAll1) {
							querySumDamageAll1.put("repair_name", "合计");
							querySumDamageAll1.put("milo","万元以下");
							allLists.add(querySumDamageAll1);
						}
						
						entryMap.put("money", "1"); //所有 万元 以上
						Map<String, Object> querySumDamageAll2 = damageTicketMapper.querySumDamage(entryMap);//所有万元以上
						if(null!=querySumDamageAll2) {
							querySumDamageAll2.put("repair_name", "合计");
							querySumDamageAll2.put("milo","万元以上");
							querySumDamageAll2.put("lownumber", "0");
							querySumDamageAll2.put("lowmoney", "0");
							allLists.add(querySumDamageAll2);
						}
						
						entryMap.put("money", "3"); // 合计
						Map<String, Object> querySumDamageAll3 = damageTicketMapper.querySumDamage(entryMap);//所有万元以上
						if(null!=querySumDamageAll3) {
							querySumDamageAll3.put("repair_name", "合计");
							querySumDamageAll3.put("milo","合计");
							allLists.add(querySumDamageAll3);
						}
						//比例万元以下
						Map<String, Object> mapquery= new HashMap<String, Object>();
						mapquery.put("repair_name", "4s/总修");
						mapquery.put("milo","万元以下");
						if(querySumDamage!=null && querySumDamage11!=null) {
							Map<String, Object> querymap = querymap(querySumDamage,querySumDamage11,mapquery);
							allLists.add(querymap);	
						}					
						//比例万元以上
						Map<String, Object> mapquerys= new HashMap<String, Object>();
						mapquerys.put("repair_name", "4s/总修");
						mapquerys.put("milo","万元以上");
						if(querySumDamage2!=null && querySumDamage12!=null) {
						Map<String, Object> querymap2 = querymap(querySumDamage2,querySumDamage12,mapquerys);
						allLists.add(querymap2);
						}
						//比例合计
						Map<String, Object> mapqueryall= new HashMap<String, Object>();
						mapqueryall.put("repair_name", "4s/总修");
						mapqueryall.put("milo","万元以上");
						if(querySumDamage3!=null && querySumDamage13!=null) {
						Map<String, Object> querymap3 = querymap(querySumDamage3,querySumDamage13,mapqueryall);
						allLists.add(querymap3);
						}
				return allLists;
	}
	
	
	public static Map<String,Object> querymap(Map<String,Object> maps,Map<String,Object> mapz,Map<String, Object> mapquery) {
		DecimalFormat    df   = new DecimalFormat("######0.00"); //小数后两位
		//4s
		double allsize = Double.parseDouble(maps.get("allsize").toString());
		//修理厂
		 double allsizes = Double.parseDouble(mapz.get("allsize").toString());						
		double allsizez = allsize/allsizes;
		mapquery.put("allsize",df.format(allsizez));
		//4s
		double allmoney = Double.parseDouble(maps.get("allmoney").toString());
		//修理厂
		 double allmoneys = Double.parseDouble(mapz.get("allmoney").toString());
		 double allmoneyz = allmoney/allmoneys;
		 mapquery.put("allmoney",df.format(allmoneyz));
		//4s
		double eqmoney = Double.parseDouble(maps.get("eqmoney").toString());
		//修理厂
		double eqmoneys = Double.parseDouble(mapz.get("eqmoney").toString());
	    double eqmoneyz = eqmoney/eqmoneys;
	    mapquery.put("eqmoney",df.format(eqmoneyz));
	  //4s
	  double money = Double.parseDouble(maps.get("money").toString());
	  //修理厂
	  double moneys = Double.parseDouble(mapz.get("money").toString());
	  double moneyz = money/moneys;
	  mapquery.put("money",df.format(moneyz));
	  //4s
	  double size = Double.parseDouble(maps.get("size").toString());
	  //修理厂
	  double sizes = Double.parseDouble(mapz.get("size").toString());
	  double sizez = size/sizes;
	  mapquery.put("size",df.format(sizez));
	  //4s
	  double eqsize = Double.parseDouble(maps.get("eqsize").toString());
	  //修理厂
	  double eqsizes = Double.parseDouble(mapz.get("eqsize").toString());
	  double eqsizez = eqsize/eqsizes;
	  mapquery.put("eqsize",df.format(eqsizez));
	//4s
	  double timemoney = Double.parseDouble(maps.get("timemoney").toString());
	  //修理厂
	  double timemoneys = Double.parseDouble(mapz.get("timemoney").toString());
	  double timemoneyz = timemoney/timemoneys;
	  mapquery.put("timemoney",df.format(timemoneyz));
	//4s
	  double eqtimemoney = Double.parseDouble(maps.get("eqtimemoney").toString());
	  //修理厂
	  double eqtimemoneys = Double.parseDouble(mapz.get("eqtimemoney").toString());
	  double eqtimemoneyz = eqtimemoney/eqtimemoneys;
	  mapquery.put("eqtimemoney",df.format(eqtimemoneyz));
	  mapquery.put("lownumber", "");
	  mapquery.put("lownumbers", "");
	  mapquery.put("lowmoney", "");
	  mapquery.put("lowmoneys", "");
	 return mapquery;
	}
}
