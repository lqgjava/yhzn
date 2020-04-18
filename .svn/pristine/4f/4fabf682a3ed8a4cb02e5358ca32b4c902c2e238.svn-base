
jQuery(document).ready(function() {
	
    /*
     *   全屏背景图
    */
    $.backstretch("resource/plugins1/assets/img/backgrounds/33.jpg");
    
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	//验证用户名密码
    	var username= $('.login-form input[type="text"]').val();
    	var password = $('.login-form input[type="password"]').val();
    	if(username == ""){
    		e.preventDefault();
    		$('#form-username').addClass('input-error');
    		alert("请输入用户名！");
			return false;
    	}
    	if(password == ""){
    		e.preventDefault();
    		$('#form-password').addClass('input-error');
    		alert("请输入密码！");
			return false;
    	}
    	$.ajax({  
        	 type : "post", 
            url : "/yhzn/shiroLogin",  
            data : {"username":username,"password":password},  
            async : false,   
            success : function(result) {
	            var obj = JSON.parse(result);
	            	if(obj.status == "y"){
	            		location.href="${ctx}/home";
					}else{
						alert(obj.info);
						return false;
					}
	            },
	            error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert("请联系管理员，错误信息error:"+errorThrown);
                 }  
        });  
    	/*$(this).find('input[type="text"], input[type="password"], textarea').each(function(index){
    		
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    			if(index == 0){
    				alert("请输入用户名！");
    				return false;
    			}
    			if(index == 1){
    				alert("请输入密码！");
    				return false;
    			}
    		}else {
    			$(this).removeClass('input-error');
    			
    		}
    	});*/
    	
    });
    
    
});
