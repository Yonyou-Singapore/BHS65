function logintrans(key){
	window.loginTransMap = {};
	window.loginTransMap.ml_resetpwdsuccess="您的密碼已重置,請重新登録";
	window.loginTransMap.ml_resetpwderror="密碼重置失敗";
	window.loginTransMap.ml_resetpwdconfirm="該操作將會重置密碼，請確定是否繼續?";
	window.loginTransMap.ml_resetpwdprocess="密碼重置中...";
		window.loginTransMap.ml_forgopwd="忘記密碼";
	window.loginTransMap.ml_remeberpwd="記住密碼";
	return window.loginTransMap[key];
}