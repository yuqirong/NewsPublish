/**
 * StringBuilder
 * 
 * 调用方法：
 * var sb = new StringBuilder(); //创建StringBuilder类的实例
sb.append('abc');
sb.append('def');
alert(sb.toString()); //输出'abcdef'
 */
function StringBuilder() {
    this._strings = new Array();
}

//append方法定义
StringBuilder.prototype.append = function (str) {
    this._strings.push(str);
}

//toString方法定义
StringBuilder.prototype.toString = function () {
    return this._strings.join('');
}

/**
 * 分页
 * @param showData 显示数据表的方法名
 * @param page 包含分页的JSON对象
 * @returns 分页导航字符串
 */
function showPage(showData,page){
	var sb = new StringBuilder();
	sb.append("<tr><td colspan='6'>");
	sb.append(page.pageIndex + "/"+ page.totalPages);

	if (page.pageIndex > 1) {
		sb.append("<a href=\"javascript:onclick="+showData+"(1)\">首页</a><a href=\"javascript:onclick="+showData+"("
				+ (page.pageIndex-1).toString()+ ")\">上页</a>");
	} else {
		sb.append("<a href='#' disabled='disabled'>首页</a><a href='#' disabled='disabled'>上页</a>");
	}

	if (page.pageIndex < page.totalPages) {
		sb.append("<a href=\"javascript:onclick="+showData+"("+ (page.pageIndex+1).toString()
				+ ")\">下页</a><a href=\"javascript:onclick="+showData+"("+page.totalPages+ ")\">尾页</a>");
	} else {
		sb.append("<a href='#' disabled='disabled'>下页</a><a href='#' disabled='disabled'>尾页</a>");
	}
	sb.append("</td></tr>");
	return sb.toString();
}

/**
 * 删除时给予提示
 */
$(function(){
	$("a[id='delete']").live("click",function(){
		return confirm('确认删除么？');
	});
});