function logintrans(key){
	window.loginTransMap = {};
	window.loginTransMap.ml_resetpwdsuccess="您的密码已重置，请重新登录";
	window.loginTransMap.ml_resetpwderror="密码重置失败";
	window.loginTransMap.ml_resetpwdconfirm="该操作将会重置密码,请确定是否继续?";
	window.loginTransMap.ml_resetpwdprocess="密码重置中...";
	window.loginTransMap.ml_forgopwd="忘记密码";
	window.loginTransMap.ml_remeberpwd="记住密码";
	return window.loginTransMap[key];
}