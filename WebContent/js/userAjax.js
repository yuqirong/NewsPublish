
/**
 * 获取并显示用户列表
 * @param pageIndex 当前页
 */
function showUserData(pageIndex) {
	var jsonData={};
	jsonData.method='list';
	
	if(pageIndex!=null ||pageIndex>0)
		jsonData.pageIndex=pageIndex;

	var userName=$('#userName').val();
	if(userName!=null &&userName!=''){
		jsonData.searchUser=userName;
	}
	$.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : 'UserAction',
			data : jsonData,
			dataType : 'html',
			success : function(data) {
				var result = JSON.parse(data);
				if (result.result_code == 1){
					$('#userList tbody tr').remove();//移除先前数据
				for ( var i = 0; i < result.data.length; i++) {
					$('#userList tbody').append('<tr><td><input type=\'checkbox\' id=\'checkUser\' value=\''+result.data[i].userId+'\'\></td><td>' 
							+ (i + 1).toString()+ '</td><td>'
						+ result.data[i].userName+ '</td><td>'
						+ result.data[i].birthFmt+ '</td><td>'
						+ result.data[i].roleName+ '</td><td>' 
						+ "<a href=\"javascript:JqueryDialog.Open('修改用户', 'UserAction?method=toUpdate&id="+result.data[i].userId+"&pageIndex="+result.page.pageIndex+"', 300, 300);\">编辑</a>|<a id='delete' href=\"javascript:deleteUser("+result.data[i].userId+","+result.page.pageIndex+")\">删除</a></td></tr>");
						}
					//显示分页
					$('#userList tbody').append(showPage('showUserData',result.page));
				}
			},
				error : function(data) {
					$('#userList tbody').append("获取数据失败！");
				}
			});
}

/**
 * 删除用户
 * @param id
 */
function deleteUser(id,pageIndex){
	$.ajax({
		type:'GET',
		contentType : 'application/json',
		url : 'UserAction',
		data : {method : 'delete',id:id},
		dataType : 'html',
		success : function(data) {
			var result=JSON.parse(data);
			if(result.result_code==1){
				alert('删除成功！');
				showUserData(pageIndex);
			}else
				alert('删除失败！');
		}
		
	});
}

$(function(){
		$("input[id='checkAll']").click(function(){
		$("input[id='checkUser']").attr("checked",this.checked);
	});
});
