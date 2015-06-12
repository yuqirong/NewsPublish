//**********************************************
//描述：Javascript常用函数库
//**********************************************
//替换字符串中所有的字符
String.prototype.ReplaceAll = stringReplaceAll;
function  stringReplaceAll(AFindText,ARepText){
  raRegExp = new RegExp(AFindText,"g");
  return this.replace(raRegExp,ARepText)
}
//过滤字符串左右空格
function Trim(str)
{
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

//判断是否为数字
function IsNumber(str)
{
    var regExpress = /[^0-9]/;
    var flag;
    flag = str.search(regExpress);
    if(flag == -1){
    	return true;
    }else{
    	return false;
    }
}
//判断是否是日期,返回true,否则返回false
//该函数是调用Javascript 内建的 Date对象来判断日期，它还能判断闰年，日期格式为yyyy-mm-dd
function IsDate(str)
{
	var r = str.split("-");
	if(r==null)return false; 
	var d= new Date(r[0], r[1]-1, r[2]); 
	return (d.getFullYear()==r[0]&&(d.getMonth()+1)==r[1]&&d.getDate()==r[2]);
}

//返回汉字字符串长度
function StrChnlength(str)
{
  return str.replace(/[^\x00-\xff]/g,'aa').length;
}

//验证textarea,输入超长时，自动截掉
function MaxInputLimit(formctrl,maxLen) {	
	var strInput;
	strInput = formctrl.value;
	if(strInput.length>maxLen)
	{
		formctrl.value = strInput.substring(0, maxLen);
	}
}
//验证是否为空或NULL
function IsNotNull(pObj,msg)
{
  var obj = eval(pObj);
  if (obj == null || Trim(obj.value) == "")
  {
	alert("【"+msg+"】不能为空！");
	//obj.focus();
	return false;
  }
  return true;
}

//验证是否有特殊字符
function StrangeCode(code,msg)
{  
	var regExpress = /[\'\"\\<>;&=#]/;
    var flag;
   
    flag = code.search(regExpress);
    if(flag == -1)
    	return false;
    else {
    	alert("【"+msg+"】中存在 \' \"\\<>;&=# 非法字符， 请更正或以中文符号替换 ！");
    	return true;
    }
}
//验证是否有特殊字符,并替换成中文字符
function StrangeCodeReplace(code)
{  
	var regExpress = /[<>]/;
    var flag;
   
    flag = code.search(regExpress);
    //alert(code+flag);
    if(flag != -1)
    {
    	code = code.ReplaceAll("<","＜");
    	code = code.ReplaceAll(">","＞");
    }
    
    return code;
}
//特殊字符验证，一般用于查询条件输入框的验证
function StrangeCodeQuery(code,msg)
{  
	var regExpress = /[~!@#$%^&*-+\\\'\"<>]/;
    var flag;
   
    flag = code.search(regExpress);
    if(flag == -1)
    	return false;
    else {
    	alert("【"+msg+"】中存在 ~!@#$%^&*-+\\\' \"<> 非法字符，请更正或以中文符号替换 ！");
    	return true;
    }
}
//判断手机号码是否输入正确
function IsMobTel(str){
    var regMobTel = /^0?13[0,1,2,3,4,5,6,7,8,9]\d{8}$/;
    var regMobTel1 = /^0?15[0,1,2,3,4,5,6,7,8,9]\d{8}$/;
    var regMobTel2 = /^0?18[8,9]\d{8}$/;
    var flag=str.search(regMobTel);
    var flag1=str.search(regMobTel1);
    var flag2=str.search(regMobTel2);
    if(flag == -1&&flag1==-1&&flag2==-1) 
	{ 
		return false; 
	} 
	else 
	{ 
		return true; 
	}
}

//判断电话号码是否输入正确，正确返回true,错误返回false
function IsTel(str){
    var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
    if (!patrn.exec(str)) return false
    return true
}
//判断一个控件的值是空或NULL
function IsNullOrEmpty(obj, ctlname)
{
    obj.value = Trim(obj.value);
    if ( obj.value == null || obj.value == "" )
    {
        alert(ctlname + "不能为空！");
        obj.focus();
        return false;
    }
    return true;
}

//判断是否为正确的电子邮件地址
function CheckEmail(obj)
{
    obj.value = Trim(obj.value);
    var regExpression=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
    var flag = obj.value.search(regExpression);
    if ( flag == -1 )
    {
        alert("邮件格式不正确，请重新填写！");
        obj.focus();
        return false;
    }
    return true;
}             
//比较两个控件的值，如果不同返回false相同则true
function CompareText(sObj, dObj, msg)
{
    if ( sObj.value != dObj.value )
    {
        alert(msg);
        dObj.focus();
        return false;
    }
    return true;
}
//文本框只能由字母和数字组成
function CheckText(obj, msg)
{
    var regExpression = /^[a-zA-Z0-9]+$/;
    var flag = obj.value.search(regExpression);
    if ( flag == -1 )
    {
        alert(msg + "只能由字母和数字组成！");
        obj.focus();
        return false;
    }
    return true;
}
//检测长度
function CheckLength(obj, msg, min, max)
{
    if (obj.value.length < min || obj.value.length > max)
    {
        alert(msg + "的长度必须大于" + min + "位小于" + max + "位");
        obj.focus();
        return false;
    }
    return true;
}
//限定文本框输入长度
function CheckTXTLength(obj, msg, max)
{
    if (obj.value.length > max)
    {
        alert(msg + "的长度必须小于" + max + "位");
        obj.focus();
        return false;
    }
    return true;
}
//检测时间格式是否正确
function CheckTime(obj, msg)
{
    var regExpression = /^[0-9:]+$/;
    var flag = obj.value.search(regExpression);
    if ( flag == -1 )
    {
        alert(msg + "时间格式不正确，请重新输入，格式为：mm:ss！");
        obj.focus();
        return false;
    }
    return true;
} 

//比较两个日期的大小
function CompareDate(d1,d2,e)
{
    return eval("CDate('"+d1+"')"+e+"CDate('"+d2+"')");
}
//转换为日期格式
function CDate(str)
{
    var arr = str.split("-");
    arr[1] = (arr[1].length==1?"0"+arr[1]:arr[1]);
    arr[2] = (arr[2].length==1?"0"+arr[2]:arr[2]);
    return arr.join("-")
}


//将光标聚焦在第一个输入框	
function initPage()
{
	for( var i=0;i<document.forms[0].elements.length;i++)
	{
 		if( document.forms[0].elements[i].type == "text")
 		{
 			document.forms[0].elements[i].focus();
 			 var r = document.forms[0].elements[i].createTextRange(); 
			 r.collapse(false); 
			 r.select(); 
 			break;
 		}
	}
}