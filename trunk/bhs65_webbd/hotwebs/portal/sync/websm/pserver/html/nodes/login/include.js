var showRanImg = false;
window.p_keepstate = "0";
function iptFocus(obj){
	var ipt = findIptBox(obj);
	var className = ipt.className;
	ipt.className = className + " inputf";
}
function iptBlur(obj){
	var ipt = findIptBox(obj);
	var className = ipt.className;
	ipt.className = className.replace("inputf", "");
	var inputs = obj.getElementsByTagName("INPUT");
	if(obj.id == 'userid' && inputs && inputs.length >0 && inputs[0].value.length == 0){
		document.getElementById('usernamelab').innerHTML = trans("login_000001");
	}else if(obj.id == 'password' && inputs && inputs.length >0 && inputs[0].value.length == 0){
		document.getElementById('passwordlab').innerHTML = trans("login_000002");
	}else if(obj.id == 'randimg' && inputs && inputs.length >0 && inputs[0].value.length == 0){
		document.getElementById('randimglab').innerHTML = trans("login_000006");
	}
}
function iptKeydown(obj){
	var inputs = obj.getElementsByTagName("INPUT");
	if(obj.id == 'userid'){
		if(inputs[0].value.length > 0){
			document.getElementById('usernamelab').innerHTML = '';
		}else{
			document.getElementById('usernamelab').innerHTML = trans("login_000001");
		}
	}else if(obj.id == 'password'){
		if(inputs[0].value.length > 0){
			document.getElementById('passwordlab').innerHTML = '';
		}else{
			document.getElementById('passwordlab').innerHTML = trans("login_000002");
		}
	}else if(obj.id == 'randimg'){
		if(inputs[0].value.length > 0){
			document.getElementById('randimglab').innerHTML = '';
		}else{
			document.getElementById('randimglab').innerHTML = trans("login_000006");
		}
	}
}

function findIptBox(obj){
	var t = obj;
	for(var i = 0 ;i< 15; i++){
		t = t.parentNode;
		if(t.tagName == "TABLE"){
			return t;
		}
	}
	return false;
}
function changeLoginStatusCK(){
	var ck = document.getElementById('loginStatusCK');
	if(ck.className == ''){
		ck.className = 'login_status_ck_checked';
		window.p_keepstate = "1";
	}else{
		ck.className = '';
		window.p_keepstate = "0";
	}
	afterLoginStatus();
}
/**
 * 自定义加载提示条
 */
function showLoginLoadingBar() {
	window.p_signdata = "";
	window.p_challdata = "";
	var btns = document.getElementById('submitBtn').getElementsByTagName("button");
	if(btns && btns.length > 0){
		btns[0].className = 'btn_loading';
	}
};
function beforeCallServer(proxy, loginListener, onclick,submitBtn){
	proxy.addParam("p_signdata",window.p_signdata);
	proxy.addParam("p_sn",window.p_sn);
	proxy.addParam("p_challdata",window.p_challdata);
	proxy.addParam("p_keepstate",window.p_keepstate);
	proxy.addParam("p_tz",(new Date()).getTimezoneOffset());
}

function doLogin(challdata, signdata,sn){
	hideDefaultLoadingBar();
	window.p_signdata = signdata;
	window.p_challdata = challdata;
	window.p_sn = sn;
	var comp = pageUI.getWidget("main").getComponent("submitBtn");
	comp.currentEventName = null;
	if (IS_IE) {
			comp.Div_gen.click();
	} else {
	var evt = document.createEvent("MouseEvents");
		evt.initEvent("click", true, true);
		comp.Div_gen.dispatchEvent(evt);
	}
	 
}
function calogin(challdata,usercode){
 	var ifm = $ge("caiframe");
 	if(ifm == null){
 		ifm = $ce("iframe");
 		ifm.id = "caiframe";
 		ifm.style.width='0px';
 		ifm.style.height='0px';
 		ifm.style.border='none';
		document.body.appendChild(ifm);
 	}
 	ifm.src = '/portal/pt/calogin/singer?challdata=' + challdata + '&usercode=' + usercode;
 	showDefaultLoadingBar();
}

//点击输入框提示文字的处理，聚焦到输入框
function loginTipWordClick(obj){
	var parentDiv = obj.parentNode;
	var inputs = parentDiv.getElementsByTagName("INPUT");
	if(inputs && inputs.length>0){
		inputs[0].focus();
	}
	e = EventUtil.getEvent();
 	stopAll(e);
 	clearEventSimply(e);
 	if(obj.id=="randimglab" && obj.style.left=="6px"){
 		obj.style.fontSize="12px";
 	}
}

function callLoginFunc(){
	var comp = pageUI.getWidget("main").getComponent("submitBtn");
	// 触发登录按钮
	if (IS_IE) {
		comp.Div_gen.click();
	} else {
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click", true, true);
		comp.Div_gen.dispatchEvent(evt);
	}
	//var clfEvent=EventUtil.getEvent();
	//stopAll(clfEvent);
}
//针对IE7特殊修改，极力不推荐，应从控件方面修改
if(IS_IE7){
	window.setTimeout(function(){
		try{
			var languageCombox = document.getElementById("multiLanguageCombocomb_data_div");
			var comboDiv = languageCombox.children[0];
			var comboCenterDiv = comboDiv.children[4];
			var leftCenterDiv = comboDiv.children[3];
			var rightCenterDiv = comboDiv.children[5];
			var length = comboCenterDiv.children.length;
			leftCenterDiv.style.height=length*20+"px";
			rightCenterDiv.style.height=length*20+"px";
			for(var i=0;i<length;i++){
				comboCenterDiv.children[i].style.width="70px";
			}
			var multiLanguageCombo_textdiv = document.getElementsByName("multiLanguageCombo")[0];
			multiLanguageCombo_textdiv.style.top="-5";
			document.getElementById("multiLanguageCombo$comb_sel_button").style.top = "6px";
		}catch(e){
		}
	},200);
}

function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
}
function delCookie(name)
{
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null)
	document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
function afterLoginStatus()
{
	if (typeof(window.p_keepstate)=="undefined" || window.p_keepstate==null || window.p_keepstate=="0")
	{
		delCookie("p_userId");
		delCookie("p_security");
	}

}
$(function(){
	$("span.login_remeberpwd_bt").html(logintrans("ml_remeberpwd"));
	$("a.login_forgotpwd_bt").html(logintrans("ml_forgopwd"));
	$(".login_forgotpwd_bt").click(function()
	{
		if ($("input[name='userid']").val().length>0 && confirm(logintrans("ml_resetpwdconfirm")))
		{
			if ($(this).attr("process")==null  || !$(this).attr("process"))
			{
				$(this).attr("process",true).html(logintrans("ml_resetpwdprocess"));
				$.ajax({
					url : "/portal/app/eca",
					type : 'post',
					data:{action:"nc.uap.lfw.action.controller.RetrievePwdAction",method:"retrievePwd",userId:$("input[name='userid']").val()},
					async : true,
					datatype : 'text',
					success : function(data) {
							if (data.indexOf("success")>=0)
							{
								$("#tiplabel").html(logintrans("ml_resetpwdsuccess"));
							}
							else
							{
								$("#tiplabel").html(logintrans("ml_resetpwderror"));
							}
							$(".login_forgotpwd_bt").removeAttr("process").html(logintrans("ml_forgopwd"));
					},
					error:function()
					{
						$(".login_forgotpwd_bt").removeAttr("process").html(logintrans("ml_forgopwd"));
					}
				});
				/*$.post("/portal/app/eca",{action:"nc.uap.lfw.action.controller.RetrievePwdAction",method:"retrievePwd",userId:$("input[name='userid']").val()})
					.done(function(data){
							$(".login_forgotpwd_bt").removeAttr("process").html("忘记密码");
							alert(data.oper);
							if (data.oper && data.oper=="success")
							{
								$("#tiplabel").html(logintrans("ml_resetpwdsuccess"));
							}
							else
							{
								$("#tiplabel").html(logintrans("ml_resetpwderror"));
							}
						})
					.fail(function(){$(".login_forgotpwd_bt").removeAttr("process").html("忘记密码");alert(logintrans("ml_resetpwderror"));})*/
			}
						
		}
	});
	var u = getCookie("p_userId");
	var s = getCookie("p_security");
	var md=getCookie("p_auth");


	function renderReady(callback)
	{
		if (window.renderDone)
		{
			callback.call(null);
		}
		else
		{
			setTimeout(function(){renderReady(callback);},100);
		}
	}
	renderReady(function(){
			$("input[name='userid']").blur(function()
			{
				if (s!=null && u!=null)
				{
					if ($(this).val()!=u && $("input[name='password']").val()==s
						&& ($(this).attr("data-clear")==null || !$(this).attr("data-clear")))
					{
						//$("input[type='password']").val("").attr("value","").removeAttr("data-s");
								pageUI.getWidget('main').getComponent('password').setValue("");
									$("#passwordlab").html(trans("login_000002"));
							$(this).attr("data-clear",true);
					}
				}
			});
		
		if (typeof(u)!="undefined" && typeof(s)!="undefined"  && u!=null && s!=null && u.length>0 && s.length>0)
		{
			changeLoginStatusCK();
			/**var pws = $("input[name='password']").clone();
			var div = $("input[name='password']").parent();
			/**$("input[name='password']").remove();
			div.append(pws);
			pws.blur(function()
			{
				if ($(this).val().length<1)
				{
					document.getElementById('passwordlab').innerHTML = trans("login_000002");
				}
			});**/
			//$("input[name='userid']").val(u);
			//$("input[name='password']").val(s).attr("data-s",s);
			pageUI.getWidget('main').getComponent('password').setValue(s);
			pageUI.getWidget('main').getComponent('userid').setValue(u);
			$("#passwordlab").html("");
		}
	});
	
});