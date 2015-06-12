	$(function() {
		$("input[name^=r_]").parent().click(function() {
			$(this).children("input[name^=r_]").removeAttr("disabled");
			if ($(this).children("input").attr("value") == "未命名") {
				$(this).children("input").attr("value", "");
			}
			$(this).children("input[name^=r_]").focus();
		});
		$("input[name^=r_]").attr("disabled", "disabled");
	});

	//全选
	$(function() {
		$('#cbSelectAll').click(function() {
			var v = $(this).attr("checked");
			if (v == "checked") {
				$("input[type=checkbox]").attr("checked", "checked");
			} else {
				$("input[type=checkbox]").removeAttr("checked");
			}
		});

		$('#inverseSelectAll').click(function(index) {
			$("input[type=checkbox]").each(function() {
				var v = $(this).attr("checked");
				if ("checked" == v) {
					$(this).removeAttr("checked");
				} else {
					$(this).attr("checked", "checked");
				}
			});
		});
	});