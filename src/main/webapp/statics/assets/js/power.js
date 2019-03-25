$(function() {
	var role= $("#role_select").attr("user_role")
	if(role == "员工" || role == "组长"){
		$("#user_change").hide()
		$("#import_bd").hide()
	}	
	if(role == "员工" || role == "组长"){

		$("#talk_save").hide()
		$("#talk_save_list").hide()
		
		$("#setmes").hide()

	}
	
})