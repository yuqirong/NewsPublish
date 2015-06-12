function ongetkey(id) {
	var _id = id;
	var key = $('#q').val();
	var which = $('#p').val();
	var jData = {
		"page" : _id,
		"date" : new Date(),
		"keywords" : key,
		"searchWhich" : which
	};

	$.ajax({
		type : "POST",
		url : "NewsAction_getSearchedNews",
		dataType : "json",
		data : jData,
		success : function(data) {
			location.reload();
		}
	});

}