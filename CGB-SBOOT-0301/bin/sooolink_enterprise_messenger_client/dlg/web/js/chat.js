
//+----------------------------------------------------
//聊天处理模块
var chatMsg = {};

//显示聊天好友列表
chatMsg.GetMyUsers= function(key){

    // <div class="user-item">
    //     <div class="user-face">
    //         <div class="img" style="background-image: url(img/blue.jpg);">
                
    //         </div>
    //     </div>
    //     <div class="user-info">
    //         <div class="user-header">
    //             <div class="user-name">张小盒</div>
    //             <div class="user-date">12:12</div>
    //         </div>
    //         <div style="clear: both"></div>
    //         <div class="user-dis">你什么时候过来的？</div>
    //     </div>
    // </div>
    // <div style="clear: both"></div>
    var myuserlist = "userlist-"+chatMsg.me.uid;//我的好友列表
    var userLists = store.get(myuserlist);
   
    //支持搜索
    if(key){
        var datalist = new Array();
        userLists.forEach(function(item){
            var vname = item.name;
            if(vname.indexOf(key)!=-1){
                datalist.push(item);
            }
        });
    }else{
        var datalist = userLists;
    }

    var len = datalist ? datalist.length : 0;
    var strs = '';
    for(var i=0;i<len;i++)
    {
        var item = datalist[i];
        var itemStrs = JSON.stringify(item);
        var type     = item.type;
        var uidStr   = '';
        var signStr  = '';
        if(type=='user'){
            uidStr = 'item-u-'+item.uid;
            signStr = '<i class="layui-icon " style="font-size: 10px;color:#5FB878;">[私]</i>';
        }
        if(type=='group'){
            uidStr = 'item-g-'+item.uid;
            signStr = '<i class="layui-icon " style="font-size: 10px;color:#5FB878;">[群]</i>';
        }
        if(type=='sys'){
            uidStr = 'item-s-'+item.uid;
        }
        strs += '<div class="user-item" id="'+uidStr+'" data-user='+itemStrs+' >';
        
        strs += '<div class="user-face">';
        
        if(item.face){
            strs += '    <div class="img set-my-face-color" style="background-image: url('+item.face+');">';
        }else{
            strs += '    <div class="img set-my-face-color" style="background-image:;">';
        }      
        var sortName = item.name;
        if(sortName.length>2)  sortName = sortName.substr(-2);
        strs += sortName;
        strs += '    </div>';
        strs += '</div>';
        strs += '<div class="user-info">';
        strs += '    <div class="user-header">';
        
        strs += '        <div class="user-name">'+item.name+'</div>';
        var ttime = item.time ? item.time : '';
        strs += '        <div class="user-date">'+ttime;
        strs += '           <span class="layui-badge-dot" style="display:none"></span>';
        strs += '</div>';
        strs += '    </div>';
        strs += '    <div style="clear: both"></div>';
        var tmsg =  item.msg ? item.msg : '';
        strs += '    <div class="user-dis">'+signStr+tmsg+'</div>';
        strs += '</div>';
        strs += '</div>';
        strs += '<div style="clear: both"></div>';
        
    }

    $('#chat-show-user-list').html(strs);
    //执行头像颜色渲染
    setFaceColor(".set-my-face-color");
    //点击事件
    $('.user-item').each(function () {
        var item = $(this);
        $(this).on('click', function () {
            $('.user-item').removeClass("active");
            item.addClass("active");
            var userInfo = $(this).data('user');
            chatMsg.showChatWindow(userInfo);
        });
    });
}
/**
 * 添加一个聊天用户到列表
 * {
    type:'user/group',
    uid：1，
    name："张小盒",
    time:"12:02",
    msg:"dddd",
    face:"img/blue.jpg"
 * }
 */
chatMsg.addUser = function (data) {
    var myuserlist = "userlist-"+chatMsg.me.uid;//我的好友列表
    var userLists = store.get(myuserlist);
    var len = userLists ? userLists.length : 0;
    var index = null;
    if(len>0){
        for(var i=0;i<len;i++)
        {
            var item = userLists[i];
            if((item.uid==data.uid) && (item.type==data.type)){
                index = i;
                break;
            }
        }
    }else{
        userLists = new Array();
    }
    if(index!=null){
        userLists.splice(index,1);
    }
    //console.log(userLists);
    
    userLists.unshift(data);
    store.set(myuserlist,userLists);
    chatMsg.GetMyUsers();
}
/**
 * 给用户渲染红点
 */
chatMsg.addHot = function(target,uid){
    if(target=='user'){
        var sid = 'item-u-'+uid;
    }
    if(target=='group'){
        var sid = 'item-g-'+uid;
    }
    if(target=='sys'){
        var sid = 'item-s-'+uid;
    }
    $('#'+sid).find('.layui-badge-dot').show();
}
chatMsg.delHot = function(target,uid){
    var sid = 'item-u-'+uid;
    if(target=='user'){
        var sid = 'item-u-'+uid;
    }
    if(target=='group'){
        var sid = 'item-g-'+uid;
    }
    if(target=='sys'){
        var sid = 'item-s-'+uid;
    }

    $('#'+sid).find('.layui-badge-dot').hide();
   
}

/**
 * 渲染聊天窗口
 data = {
    type:'user/group',
    uid：1，
    name："张小盒",
    time:"12:02",
    msg:"dddd",
    face:"img/blue.jpg"
 }
 */
chatMsg.showChatWindow = function(data){
    var chatUserDom = $('#chat-user-info');
    //保存聊天对象的信息
    chatUserDom.data('info',data);
    console.log(data);
    var uid = data.uid;
    //清除红点
    chatMsg.delHot(data.type,uid);
    //渲染聊天对象头像，名称
    chatUserDom.find('.chat-header-name').html(data.name);
    var shortName = data.name;
    if(shortName.length>2) shortName = shortName.substr(-2);
    chatUserDom.find('.my-chat-face').html(shortName);
    //头像处理
    if(data.face){
        chatUserDom.find('.my-chat-face').css('background-image','url('+data.face+')');
    }else{
        chatUserDom.find('.my-chat-face').css('background-image',''); 
    }
    //执行头像颜色渲染
    setFaceColor(".my-chat-face");
    //消息渲染
    chatMsg.showMsg(data);

    //右侧信息显示渲染
    chatMsg.showRightInfo(data);

}
/**
 * 渲染和指定对象的聊天消息
 * 
 {type:'user/group',
    uid：1，
    name："张小盒",
    time:"12:02",
    msg:"dddd",
    face:"img/blue.jpg"
  }
 */
chatMsg.showMsg = function(data){
    var uid = data.uid;
    var type = data.type;
    var msgid = type + '-' + uid + '-' + chatMsg.me.uid;
    /**
     * 本地存储的消息格式
     * 
      [
        {
            name:'将海林',
            uid:1,
            face:'',
            time:'12:20',
            type:'txt',
            msg:'你要，我还你。。'，
            place:'left'
        }
      ]
     */
    var msg = store.get(msgid);
    var len = msg ? msg.length : 0;
    var strs = '';
    if(len>0){
        for(var i=0;i<len;i++)
        {
            var msgMe = msg[i];
            strs += chatMsg.createMsg(msgid,msgMe.place,msgMe);
        }
    }
    //批量渲染

    $('#chat-body').html(strs);
    setFaceColor(".show-chat-msg");
    //滚动到最底部
    setTimeout(function(){
        $("#chat-body").animate({scrollTop:$("#chat-body").prop("scrollHeight")}, 400);
    },100);
    
    


}
/**
 * 渲染出一条消息
 * msgid 聊天对象 user_1/group_1/sys_1
 * type left/right  位置（左侧，右侧）
 * msg              消息内容
 *  var data = {
        name:'将海林',
        uid:1,
        face:'',
        time:'12:20',
        type:'txt',
        content:'你要，我还你。。',
        place:'left'
    }
    isAppend 是否直接插入到当前聊天窗口
    isSave 是否保存到本地缓存
 */

chatMsg.createMsg = function(msgid,type,msg,isAppend,isSave){
    var maxLen = 1000;
    var allmsg = store.get(msgid);
    var len = allmsg ? allmsg.length : 0;
    if(isSave){
        if(len>0){
            if(len>maxLen){
                allmsg.shift();
                allmsg.push(msg);
            }else{
                allmsg.push(msg);
            }
        }else{
            allmsg = new Array();
            allmsg.push(msg);
        }
        store.set(msgid,allmsg);
    }
    

    //right msg
    // <div class="chat-show-item">
    //     <div class="chat-show-right">
    //         <div class="chat-show-face">
    //             <div class="img" style="background-image: '';">
    //                     张三
    //             </div>
    //         </div>
    //         <div class="chat-info">
    //             <div class="chat-info-name">
    //                 张三
    //                 <span>12:10</span>
    //             </div>
    //             <div class="chat-info-content">
    //                 <img src="img/red.jpg">
    //             </div>
    //         </div>
    //     </div>
    //     <div style="clear:both;"></div>
    // </div>

    //left msg
    // <div class="chat-show-item">
    //     <div class="chat-show-left">
    //         <div class="chat-show-face">
    //             <div class="img" style="background-image: '';">
    //                     王旭
    //             </div>
    //         </div>
    //         <div class="chat-info">
    //             <div class="chat-info-name">
    //                 王小虎
    //                 <span>12:10</span>
    //             </div>
    //             <div class="chat-info-content">
    //                 CSS Border(边框) | 菜鸟教程
    //                 CSS 边框 CSS 边框属性 CSS边框属性允许你指定一个元素边框的样式和颜色。 在四边都有边框 红色底部边框 圆角边框 左侧边框带宽度,颜色为蓝色 边框样式 边框样式...
    //                 https://www.runoob.com/css/css... - 百度快照
    //             </div>
    //         </div>
    //     </div>
    //     <div style="clear:both;"></div>
    // </div>
    var name = msg.name;
    var shortName = msg.name;
    if(shortName.length>2) shortName = shortName.substr(-2);
    var face = msg.face;
    if(face) face = 'url('+face+')';
    else face = '';

    var mtime  = getDateTime(msg.time,"小时");
    var content= msg.content;
    //console.log('msg:',msg);
    var msgType = msg.type;
    //console.log("msgType:",msgType);

    if(msgType=='txt')  content = replaceEm(content);//文字图片+表情
    
    if(msgType=='img')  content = '<img src="'+content+'">';
    if(msgType=='file') content = '<i class="layui-icon layui-icon-download-circle" data-url="'+content+'" onclick="downResources(this)" style="font-size:28px;"><div style="font-size:14px;">下载</div></i>';
    if(msgType=='sound') content = '<i class="layui-icon layui-icon-speaker" data-url='+content+' style="font-size:35px;" onclick="playSound(this)"></i>';
    if(msgType=='video') content = '<i class="layui-icon layui-icon-play" style="font-size:28px;" onclick="playVadio(this)" data-url="'+content+'"></i>';

   

    if(type=='right')
    {
        var msgStr = '';
        msgStr += '<div class="chat-show-item">';
        msgStr += '<div class="chat-show-right">';
        msgStr += '    <div class="chat-show-face">';

        msgStr += '        <div class="img show-chat-msg" style="background-image: '+face+';">';
        msgStr += '                ' + shortName;
        msgStr += '        </div>';

        msgStr += '    </div>';
        msgStr += '    <div class="chat-info">';
        msgStr += '        <div class="chat-info-name">';
        msgStr += '            <span style="font-size: 12px;color:#cccccc;">'+mtime+'</span>';
        msgStr += '       '+name;
        msgStr += '        </div>';
        msgStr += '        <div class="chat-info-content">';
        msgStr += '            '+content;
        msgStr += '        </div>';
        msgStr += '    </div>';
        msgStr += '</div>';
        msgStr += '<div style="clear: both;"></div>';
        msgStr += '</div>';
    }

    if(type=='left')
    {
        var msgStr = '';
        msgStr += '<div class="chat-show-item">';
        msgStr += '<div class="chat-show-left">';
        msgStr += '    <div class="chat-show-face">';
        msgStr += '        <div class="img show-chat-msg" style="background-image:'+face+';">';
        msgStr += '                '+shortName;
        msgStr += '        </div>';
        msgStr += '    </div>';
        msgStr += '    <div class="chat-info">';
        msgStr += '        <div class="chat-info-name">';
        msgStr += '            '+name;
        msgStr += '            <span>'+mtime+'</span>';
        msgStr += '        </div>';
        msgStr += '        <div class="chat-info-content">';
        msgStr += '           '+content;
        msgStr += '        </div>';
        msgStr += '    </div>';
        msgStr += '</div>';
        msgStr += '<div style="clear:both;"></div>';
        msgStr += '</div>';
    }

    if(isAppend){
        $('#chat-body').append(msgStr);
        setFaceColor(".show-chat-msg");
        //滚动到最底部
        
        setTimeout(function(){
            $("#chat-body").animate({scrollTop:$("#chat-body").prop("scrollHeight")}, 400);
        },100);
    }
    else{
        return msgStr;
    }
    
}


chatMsg.showRightInfo = function(data){
    console.log("right:");
    console.log(data)

    setTimeout(function(){
        
        external.api("/api/user/getChatUserInfo",{id:data.uid,type:data.type},function(resdata){
            resdata = JSON.parse(resdata);
            // var resdata = {
            //     "data":{
            //         "files":{
            
            //         },
            //         "info":{
            //             "create_time":"2020-03-21 23:42:53",
            //             "create_user":2,
            //             "id":1,
            //             "name":"考研战斗群",
            //             "type":1
            //         },
            //         "users":[
            //             {
            //                 "company_id":1,
            //                 "create_user":0,
            //                 "depart_name":"信息中心",
            //                 "department_id":1,
            //                 "email":"hailingr@foxmail.com",
            //                 "face":"http://127.0.0.1:9981/data/upload/20200314194144101126.jpg",
            //                 "id":1,
            //                 "name":"hailin.com",
            //                 "nick_name":"海林",
            //                 "pwd":"A139502F801C7181EC9E51C6EA89F7D7",
            //                 "qq":"123",
            //                 "real_name":"蒋海林",
            //                 "sex":"无",
            //                 "status":1,
            //                 "tel":"13540633386",
            //                 "weixin":"hailingr "
            //             },
            //             {
            //                 "company_id":2,
            //                 "create_user":0,
            //                 "depart_name":"成都工厂",
            //                 "department_id":9,
            //                 "face":"http://127.0.0.1:9981/data/upload/20200321162226101126.JPG",
            //                 "id":2,
            //                 "name":"hailin.net",
            //                 "nick_name":"Hailin.net",
            //                 "pwd":"51BCE94010F053383221C70A482A21CF",
            //                 "real_name":"Hailin.net",
            //                 "sex":"女",
            //                 "status":1,
            //                 "tel":"13540633386"
            //             },
            //             {
            //                 "create_user":0,
            //                 "department_id":0,
            //                 "id":3,
            //                 "name":"11111",
            //                 "sex":"男",
            //                 "status":1
            //             },
            //             {
            //                 "create_user":0,
            //                 "department_id":0,
            //                 "id":4,
            //                 "name":"222",
            //                 "nick_name":"红花",
            //                 "real_name":"红花",
            //                 "sex":"男",
            //                 "status":1
            //             }
            //         ]
            //     },
            //     "msg":"聊天对象信息",
            //     "status":1
            // };
            //console.log(resdata);
            if(resdata.status){
                resdata = resdata.data;
                var info = resdata.info;
                var users= resdata.users;
                var files= resdata.files;
                var infoStrs = '';
                var userStrs = '';
                var fileStrs = '';
                if(data.type=='group'){
                    var typeArr = ['','私有','公开'];
                    infoStrs += '<tr>';
                    infoStrs += '<td>群名</td>';
                    infoStrs += '<td>'+info.name+'</td>';
                    infoStrs += '</tr>';
                    infoStrs += '<tr>';
                    infoStrs += '<td>创建</td>';
                    infoStrs += '<td>'+info.create_time+'</td>';
                    infoStrs += '</tr>';
                    infoStrs += '<tr>';
                    infoStrs += '<td>类型</td>';
                    infoStrs += '<td>'+typeArr[info.type]+'</td>';
                    infoStrs += '</tr>';
                    infoStrs += '<tr>';
                    infoStrs += '<td>成员</td>';
                    infoStrs += '<td>'+'</td>';
                    infoStrs += '</tr>';
                }
                if(data.type=='user'){
                    infoStrs += '<tr>\
									<td>昵称</td>\
									<td>'+info.nick_name+'</td>\
								</tr>\
								<tr>\
									<td>姓名</td>\
									<td>'+info.real_name+'</td>\
								</tr>\
								<tr>\
									<td>部门</td>\
									<td>'+info.depart_name+'</td>\
								</tr>\
								<tr>\
									<td>性别</td>\
									<td>'+info.sex+'</td>\
								</tr>\
								<tr>\
									<td>电话</td>\
									<td>'+info.tel+'</td>\
								</tr>';
                }
                //用户
                if(users.length>0){
                    users.forEach(function(item){
                        var name = item.real_name ? item.real_name : (item.nick_name ? item.nick_name : item.name);
                        var sortName = name;
                        if(sortName.length>2)  sortName = sortName.substr(-2);

                       
                        var img = 'style="background-image:;"';
                        if(item.face){
                            img = 'style="background-image: url('+item.face+');"';
                        }
                        userStrs += '<tr class="handclass" onclick="showUserInfo(this)" data-json=\''+JSON.stringify(item)+'\'>\
                                        <td>\
                                            <div class="group-info-list-img" '+img+'>\
                                                '+sortName+'\
                                            </div>\
                                            '+name+'\
                                        </td>\
                                    </tr>'; 
                    });
                }
                if(files.length>0){
                    files.forEach(function(item){
                        var icoClassName = 'layui-icon-file';
                        if(item.type=='img') icoClassName = 'layui-icon-picture';
                        if(item.type=='sound') icoClassName = 'layui-icon-headset';
                        if(item.type=='video') icoClassName = 'layui-icon-video';
                       
                        fileStrs += '<tr>\
                                    <td><i class="layui-icon '+icoClassName+'"></i></td>\
                                    <td>'+item.user_name+'</td>\
                                    <td>'+item.download+'</td>\
                                    <td><i class="layui-icon layui-icon-download-circle" data-url="'+item.url+'" onclick="downResources(this)"></i></td>\
                                </tr>';
                        
                    });
                }


                $('#user-group-info').html(infoStrs);
                $('#right-group-users').html(userStrs);
                $('#right-user-group-files').html(fileStrs);
                //头像渲染
                setFaceColor(".group-info-list-img");
            }
            
        });
    },80);
    
}







