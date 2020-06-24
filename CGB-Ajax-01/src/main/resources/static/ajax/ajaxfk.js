(function(){
	var ajax=function(){}
	ajax.prototype={
		 doAjaxGet:function(url,params,callback){
				//1.创建xhr对象
				var xhr=new XMLHttpRequest();
				//2.注册Xhr对象的状态监听函数
				xhr.onreadystatechange=function(){//callback
					//readyState它的值可能
					console.log("xhr.readyState",xhr.readyState)
					//1) 0 此时还没执行xhr的open方法
					//2) 1 已执行open方法但还未执行send方法
					//3) 2 已执行send方法
					//4) 3 客户端已开始接收服务端响应的数据
					//5) 4 客户端已经接收完服务端响应的数据
					if(xhr.readyState==4){
						if(xhr.status==200){
							console.log("xhr.responseText="+xhr.responseText);
							//var jsonObj=JSON.parse(xhr.responseText);
							callback(xhr.responseText));//xhr.responseText为服务端返回的数据
						}
					}
				}
				//3.创建一个连接
				xhr.open("GET",url+"?"+params,true);//true表示异步执行
				//4.发送请求
				xhr.send(null) 
		 },
		 doAjaxPost:function(url,params,callback){
			//1.创建xhr对象
				var xhr=new XMLHttpRequest();
				//2.注册Xhr对象的状态监听函数
				xhr.onreadystatechange=function(){
					if(xhr.readyState==4){
						if(xhr.status==200){
							callback(xhr.responseText);
						}
					}
				}
				//3.创建一个连接
				xhr.open("POST",url,true);//true表示异步执行
				//对于post请求必须设置请求头
				xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
				//4.发送请求
				xhr.send(params)
		 }
	}
	window.Ajax=new ajax();
})()
