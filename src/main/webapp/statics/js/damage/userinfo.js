/**
 * 
 */





//模拟一段JSON数据，实际要从数据库中读取
  var per = "";
  			 
  //导出
  $('#btn-excel-day').on('click', function() {
	var username = $("#secondTypeId").val();
    var param = $("#param").val();
	var damageDate = $("#damageDate").val(); 
	if(""!=username && ""!=param && ""!=damageDate){
		location.href="/PICCproject/picc/damage/damageuserexcel.ajax?userName="+username+"&param="+param+"&damageDate="+damageDate;
	}else{
		bkyyAlert("参数不能为空！！");
	}
  	
  });

  $("#btn-search-day").on("click",function () {
	 $("#userTable tbody").html("");
	 var username = $("#secondTypeId").val();
	 var param = $("#param").val();
	 var damageDate = $("#damageDate").val();
	 if(""!=username && ""!=param && ""!=damageDate){
		 $.ajax({  
	         type:"post",         //请求方式  
	         url:"/PICCproject/picc/damage/userdamagelist.ajax",      //连接  
	         data:"username="+username+"&&param="+param+"&&damageDate="+damageDate,  //数据加载  
	         dataType:"json", //数据类型  
	         success:function(data){ //data表示服务器端返回的数据 
	        	 arr=damageDate.split('-'); 
	        	 if(1==param){
	        		 $("#time").html(arr[0]+"年"+arr[1]+"月") 
	        	 }else if(2==param){
	        		 $("#time").html(arr[0]+"年") 
	        	 }        	
	        	 if(data==''){
	        		 $("#userTable tbody").html("表中数据为空");
	        	 }else{
	        		 var tbody = document.getElementById('tbMain');
	   	       	  for(var i = 0;i < data.length; i++){ //遍历一下json数据
	   	       		  var trow = getDataRow(data[i]); //定义一个方法,返回tr数据
	   	       	  	  tbody.appendChild(trow);
	   	       		}
	        	 }
	         
	         }  
	     });
	 }else if(username==""){
		 bkyyAlert("姓名不能为空！！");
	 }else if(param==""){
		 bkyyAlert("类型不能为空！！");
	 }else if(damageDate==""){
		 bkyyAlert("日期不能为空！！");
	 }else{
		 bkyyAlert("参数不能为空！！");
	 }	
	
	  
  });
   //重置按钮
  $("#btn-reset-day").on("click",function () {
	  window.location.reload()
  });
  
  
  function getDataRow(h){
	 var row = document.createElement('tr'); //创建行
	 
	/* var idCell = document.createElement('td'); //创建第一列
	 idCell.innerHTML = ""; //填充数据
	 row.appendChild(idCell); //加入行  ，下面类似
*/	 
	 var nameCell = document.createElement('td');//创建第二列
	 nameCell.innerHTML = h.repair_name;
	 row.appendChild(nameCell);
	 
	 var jobCell = document.createElement('td');//创建第三列
	 jobCell.innerHTML = h.milo;
	 row.appendChild(jobCell);
	 
	 var allsizeCell = document.createElement('td');//创建第四列
		 if(undefined==h.allmoney){ 
			 allsizeCell.innerHTML = 0;
		 }else{
			allsizeCell.innerHTML = h.allsize;
		 }
	
	 row.appendChild(allsizeCell);
	 var allmoneyCell = document.createElement('td');//创建第五列
		if(undefined==h.allmoney){ 
			allmoneyCell.innerHTML = 0; 
		}else{
			allmoneyCell.innerHTML = h.allmoney;
		}
	 row.appendChild(allmoneyCell);
	 var lownumberCell = document.createElement('td');//创建第六列
		if(undefined==h.lownumber){ 
			lownumberCell.innerHTML = 0; 
		}else{
			lownumberCell.innerHTML = h.lownumber;
		}
	 row.appendChild(lownumberCell);
	 
	 var lowmoneyCell = document.createElement('td');//创建第七列
		if(undefined==h.lowmoney){ 
			lowmoneyCell.innerHTML = 0; 
		}else{
			lowmoneyCell.innerHTML = h.lowmoney;
		}
	 row.appendChild(lowmoneyCell);
	 
	 var lownumbersCell = document.createElement('td');//创建第八列
		if(undefined==h.lownumbers){ 
			lownumbersCell.innerHTML = 0; 
		}else{
			lownumbersCell.innerHTML = h.lownumbers;
		}
	 row.appendChild(lownumbersCell);
	 
	 var lowmoneysCell = document.createElement('td');//创建第九列
		if(undefined==h.lowmoneys){ 
			lowmoneysCell.innerHTML = 0; 
		}else{
			lowmoneysCell.innerHTML = h.lowmoneys;
		}
	 row.appendChild(lowmoneysCell);
	 
	 var eqmoneyCell = document.createElement('td');//创建第十列
		if(undefined==h.eqmoney){ 
			eqmoneyCell.innerHTML = 0; 
		}else{
			eqmoneyCell.innerHTML = h.eqmoney;
		}
	 row.appendChild(eqmoneyCell);
	 
	 var moneyCell = document.createElement('td');//创建第十一列
		if(undefined==h.money){ 
			moneyCell.innerHTML = 0; 
		}else{
			moneyCell.innerHTML = h.money;
		}
	 row.appendChild(moneyCell);
	 
	 var sizeCell = document.createElement('td');//创建第十二列
		if(undefined==h.size){ 
			sizeCell.innerHTML = 0; 
		}else{
			sizeCell.innerHTML = h.size;
		}
	row.appendChild(sizeCell);	
	var eqsizeCell = document.createElement('td');//创建第十三列
		if(undefined==h.eqsize){ 
			eqsizeCell.innerHTML = 0; 
		}else{
			eqsizeCell.innerHTML = h.eqsize;
		}
	row.appendChild(eqsizeCell);	
	var timemoneyCell = document.createElement('td');//创建第十四列
		if(undefined==h.timemoney){ 
			timemoneyCell.innerHTML = 0; 
		}else{
			timemoneyCell.innerHTML = h.timemoney;
		}
	row.appendChild(timemoneyCell);	
	var eqtimemoneyCell = document.createElement('td');//创建第十四列
		if(undefined==h.eqtimemoney){ 
			eqtimemoneyCell.innerHTML = 0; 
		}else{
			eqtimemoneyCell.innerHTML = h.eqtimemoney;
		}		
	row.appendChild(eqtimemoneyCell);		 
	 return row; //返回tr数据	 
	 }	  