$(function() {
	$(".typeDelete").click(function() {
		var _text = $(this).parent().parent().find("td").eq(1).text();
		var _id = $(this).attr("id");
		var flag = confirm("确定要删除 " + _text + " 这个栏目吗");
		if (flag) {
			var $tr = $(this).parent().parent();
			var url = "NewsTypeAction_deleteNewsType?id=" + _id;
			var date = {
				"time" : new Date()
			};
			$.post(url, date, function(data) {
				if (data == "1") {
					$tr.remove();
					alert("删除成功");
				} else {
					alert("删除失败");
				}
			});
		}
		return false;
	});
	
	$(".newsDelete").click(function() {
		var _text = $(this).parent().parent().find("td").eq(1).text();
		var _id = $(this).attr("id");
		var flag = confirm("确定要删除 " + _text + " 这条新闻吗");
		if (flag) {
			var $tr = $(this).parent().parent();
			var url = "NewsAction_deleteNews?id=" + _id;
			var date = {
				"time" : new Date()
			};
			$.post(url, date, function(data) {
				if (data == "1") {
					$tr.remove();
					alert("删除成功");
				} else {
					alert("删除失败");
				}
			});
		}
		return false;
	});
	
	$(".userDelete").click(function() {
		var _text = $(this).parent().parent().find("td").eq(1).text();
		var _id = $(this).attr("id");
		var flag = confirm("确定要删除 " + _text + " 这个用户吗");
		if (flag) {
			var $tr = $(this).parent().parent();
			var url = "UserAction_deleteUser?id=" + _id;
			var date = {
				"time" : new Date()
			};
			$.post(url, date, function(data) {
				if (data == "1") {
					$tr.remove();
					alert("删除成功");
				} else {
					alert("删除失败");
				}
			});
		}
		return false;
	});
});