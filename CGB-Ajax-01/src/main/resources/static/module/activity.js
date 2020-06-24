$(function(){//页面加载完成以后执行
	// debugger
	//页面加载完成以后去获取活动数据
	doGetActivitys();
});
//获取所有活动数据
function doGetActivitys(){
	//1.定义url
	var url="doFindActivitys";
	//2.定义params
	var params="";
	//3.发送异步请求获取数据并更新到页面上
	Ajax.doAjaxGet(url,params,function(result){//List<Activity>
		doHandleQueryResult(result);
	})
}
function doHandleQueryResult(result){
	var jsonObj=JSON.parse(result);
	var tBody=$("tbody");
	tBody.empty();//清空tbody中原有内容
	for(var i=0;i<jsonObj.length;i++){
		tBody.append(doCreateRow(jsonObj[i]));
	}
}
function doInitEditFormData(aty){
	$("#atyId").val(aty.id);
	$("#titleId").val(aty.title);
	$("#categoryId").val(aty.category);
	$("#startTimeId").val(aty.startTime);
	$("#endTimeId").val(aty.endTime);
	$("#remarkId").text(aty.remark);
	//显示模态框
	$('#myModal').modal('show'); 
}
//呈现添加模态框
function doShowAddDialog(){
	$("#atyId").val('');
	$("#titleId").val('');
	$("#categoryId").val('');
	$("#startTimeId").val('');
	$("#endTimeId").val('');
	$("#remarkId").text('');
	//显示模态框
	$('#myModal').modal('show'); 
}
function doLoadById(id){
	//debugger
	// location.href="doFindById?id="+id;
	var url="doFindById";
	var params="id="+id;
	Ajax.doAjaxGet(url,params,function(result){
		var jsonObj=JSON.parse(result);
		doInitEditFormData(jsonObj);
	})
}
function doSaveActivity(){
	//1.定义url
	var url="doSaveActivity";
	//2.定义请求参数
	var params="";
	var id=$("#atyId").val();//获取绑定的数据
	var title=$("#titleId").val();
	var category=$("#categoryId").val();
	var startTime=$("#startTimeId").val();
	var endTime=$("#endTimeId").val();
	var remark=$("#remarkId").val();
	var params="id="+id+"&title="+title+"&category="+category+"&startTime="+startTime+"&endTime="+endTime+"&remark="+remark;
	console.log("params",params);
	//3.发送异步请求
	Ajax.doAjaxPost(url,params,function(result){//--->Activity
		console.log("result",result);
		debugger
		var jsonObj=JSON.parse(result);
		$('#myModal').modal('hide'); 
		//刷新页面(查询所有或者页面上局部刷新)
		//doGetActivitys(); 再次查询所有
		//$("tbody").append(doCreateRow(jsonObj));
		//$("tbody").prepend(doCreateRow(jsonObj));
		if(id){//id有值说明是更新
			var tr=$("#"+id).parents("tr");
			//方法1：
			//tr.before(doCreateRow(jsonObj));
			//tr.remove();
			//方法2：
			tr.find("td:eq(1)").text(jsonObj.title);
			tr.find("td:eq(2)").text(jsonObj.category);
			tr.find("td:eq(3)").text(jsonObj.startTime);
			tr.find("td:eq(4)").text(jsonObj.endTime);
		}else{
			$("tbody").find("tr:eq(0)").before(doCreateRow(jsonObj));
		}
	});
}
function doCreateRow(obj){
	return `<tr>
	<td>${obj.id}</td>
	<td>${obj.title}</td>
	<td>${obj.category}</td>
	<td>${obj.startTime}</td>
	<td>${obj.endTime}</td>
	<td>${obj.state}</td>
	<td>${obj.createdTime}</td>
	<td>
	<button class="btn btn-danger btn-sm" id=${obj.id} onclick=doDeleteById(${obj.id})>删除</button>
	<button class="btn btn-warning btn-sm" onclick=doLoadById(${obj.id})>更新</button> 
	</td>
	</tr>`;
}
//基于ajax方式执行删除操作
function doDeleteById(id){
	if(!confirm("确定删除吗"))return;
	//location.href="doDeleteById?id="+id;
	var url="doDeleteById";
	var params="id="+id;
	//var obj=$(this);
	Ajax.doAjaxGet(url,params,function(result){
		alert(result);
		//思考刷新的方式?...
		var obj=$("#"+id);
		console.log("obj",obj);
		$("#"+id).parents("tr").remove();
	});
}