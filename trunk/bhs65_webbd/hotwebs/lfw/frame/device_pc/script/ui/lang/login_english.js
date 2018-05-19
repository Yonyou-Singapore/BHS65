function logintrans(key){
	window.loginTransMap = {};
	window.loginTransMap.ml_resetpwdsuccess="Password has been reset,please login";
	window.loginTransMap.ml_resetpwderror="Password reset failure";
	window.loginTransMap.ml_resetpwdconfirm="Your password will be reset,continue?";
	window.loginTransMap.ml_resetpwdprocess="Password reseting...";
	window.loginTransMap.ml_forgopwd="Forgot?";
	window.loginTransMap.ml_remeberpwd="Remember";
	return window.loginTransMap[key];
}