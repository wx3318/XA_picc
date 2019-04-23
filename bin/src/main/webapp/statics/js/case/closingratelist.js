
	

/*//根据机构查人员信息
$("#groupId").change(function(){
    	var groupId=$(this).val();
    	$.post("/PICCproject/picc/closing/getUserGroupId.ajax",{"groupId":groupId},function(data){
    		var $userId=$("#userId");
    		$userId.html("<option value=0>全部</option>");
    		$(data).each(function(i,n){
				// alert(n.cname);
				
				$userId.append("<option value='"+n.user_id+"'>"+n.name +"</option>");
			});
    		
    	},"json");
    	
    });*/
 //查询
$("#btn-search-pro").click( function(){
	   	$.post("/PICCproject/picc/closing/getClosingRateMessage",$("#search-form-usercase").serialize(), function(data){
	   	  var $countId1=$("#groupcaseTable1");
	      $countId1.html("<tr></tr>");
	   	  $(data).each(function(i,n){
	   	  $countId1.append("<tr>" +
	   	  		"<td style='vertical-align:middle'>"+n.groupName+"</td>"+
			"<td style='vertical-align:middle;text-align:left;'>"+n.registrationFiveNum+"</td>"+
			"<td style='vertical-align:middle;text-align:left'>"+n.closedFiveNum+"</td>"+
		    "<td style='vertical-align:middle;text-align:left;'>"+n.closedFiveRate+"</td>"+
			"<td style='vertical-align:middle;text-align:left;'>"+n.registrationOneDownNum+"</td>"+
			"<td style='vertical-align:middle;text-align:left;'>"+n.closedOneDownNum+"</td>"+
			"<td style='vertical-align:middle;text-align:center;'>"+n.closedOneDownRate+"</td>"+
			"<td style='vertical-align:middle;text-align:center;'>"+n.registrationOneUpNum+"</td>"+
			"<td style='vertical-align:middle;text-align:center;'>"+n.closedOneUpNum+"</td>" +
			"<td style='vertical-align:middle;text-align:center;'>"+n.closedOneUpRate+"</td></tr>"
					 );
			});
	},"json");

});

 //导出excel
$('#btn-excel-pro').click(function(){  
	 var $year=$("#year").val();
	 var $month=$("#month").val();
	 //var $moneyType=$("#moneyType").val();
	 //var $groupId=$("#groupId").val();
	 	if($month=='0'){
	 		 window.location = "/PICCproject/picc/closing/closingratelistexportexcel.ajax?year="+$year;
	 	}else{
	 		 window.location = "/PICCproject/picc/closing/closingratelistexportexcel.ajax?year="+$year+"&month="+$month;
	 	}
	
});

    
 