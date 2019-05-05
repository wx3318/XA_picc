// 绑定重置查询事件
    $('#btn-reset-pro').on('click', function() {
        // 重置table查询
    	
    	window.location = "/PICCproject/picc/closing/closinglist.html";
    });
	

//根据机构查人员信息
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
    	
    });
 //查询
$("#btn-search-pro").click( function(){
	   	$.post("/PICCproject/picc/closing/getClosingListMessage",$("#search-form-usercase").serialize(), function(data){
	   	  var $countId1=$("#groupcaseTable1");
	      $countId1.html("<tr></tr>");
	   	  $(data).each(function(i,n){
	   	  $countId1.append("<tr>" +
	   			"<td style='vertical-align:middle'>"+n.myCaseType+"</td>"+
	   	  		"<td style='vertical-align:middle'>"+n.totalCount+"</td>"+
			"<td style='vertical-align:middle;text-align:center;'>"+n.sumMoney+"</td>"+
			"<td style='vertical-align:middle;text-align:center'>"+n.nowCount+"</td>"+
		    "<td style='vertical-align:middle;text-align:right;'>"+n.countYearDay+"</td>"+
			"<td style='vertical-align:middle;text-align:right;'>"+n.countYearDayLast+"</td>"+
			"<td style='vertical-align:middle;text-align:right;'>"+n.countMonthDay+"</td>"+
			"<td style='vertical-align:middle;text-align:right;'>"+n.countMonthDayLast+"</td>"+
			"<td style='vertical-align:middle;text-align:right;'>"+n.countYearMonthDay+"</td>"+
			"<td style='vertical-align:middle;text-align:center;'>"+n.countYearMonthDayLast+"</td></tr>"
					 );
			});
	},"json");

});
 //导出excel
$('#btn-excel-pro').click(function(){  
	 var $year=$("#year").val();
	 var $month=$("#month").val();
	 var $moneyType=$("#moneyType").val();
	 var $groupId=$("#groupId").val();
	 	if($month=='0'){
	 		 window.location = "/PICCproject/picc/closing/closinglistexportexcel.ajax?year="+$year+"&moneyType="+$moneyType+"&groupId="+$groupId;
	 	}else{
	 		 window.location = "/PICCproject/picc/closing/closinglistexportexcel.ajax?year="+$year+"&month="+$month+"&moneyType="+$moneyType+"&groupId="+$groupId;
	 	}
	
});

    
 