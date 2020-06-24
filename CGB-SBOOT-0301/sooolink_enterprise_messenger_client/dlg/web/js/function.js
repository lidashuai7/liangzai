//+--------------------------------------------------------------
//框架高宽计算方法
function setLMRSize() {
    var leftWidth = 250;
    var rightWidth = 300;
    var totalWidth = $("body").width();
    var rightIsHidden = $('.right').is(":hidden");
    //console.log(rightIsHidden);
    //console.log(totalWidth);
    //console.log(rightWidth);
    //console.log(leftWidth);
    if (rightIsHidden) {
        var middleWidth = totalWidth - leftWidth - 10;

    }
    else {
        var middleWidth = totalWidth - leftWidth - rightWidth - 14;
    }
    //console.log(middleWidth);
    $(".middle").width(middleWidth);

}

function setMRHeight() {
    var middelHeader = 30;
    var middelInput = 190;
    var middelTotal = $(".middle").height();


    var relMiddelHeight = middelTotal - middelHeader - middelInput - 18;
    $('#chat-body').height(relMiddelHeight);
}

function setUserListHeight() {
    var middelTotal = $(".middle").height();
    var realUserListHeight = middelTotal - 50;

    $(".user-list").height(realUserListHeight);
}

function setRightListHeight() {
    var rightTotal = $(".right").height();
    var realRightListHeight = rightTotal - 50;

    $("#right-show-scoll1").height(realRightListHeight);
    $("#right-show-scoll2").height(realRightListHeight);
}
//+--------------------------------------------------------------

function setScollInfo()
{
    //滚动条样式处理
    $('#chat-show-user-list').niceScroll({
        cursorcolor:"#009688",
        cursoropacitymax:0.6,
        cursorwidth:"2px"
    });
    $('#chat-body').niceScroll({
        cursorcolor:"#009688",
        cursoropacitymax:0.6,
        cursorwidth:"2px"
    });
    $('#chat-input-editer').niceScroll({
        cursorcolor:"#009688",
        cursoropacitymax:0.6,
        cursorwidth:"2px"
    });

    $('#right-show-scoll1').niceScroll({
        cursorcolor:"#009688",
        cursoropacitymax:0.6,
        cursorwidth:"2px"
    });
    $('#right-show-scoll2').niceScroll({
        cursorcolor:"#009688",
        cursoropacitymax:0.6,
        cursorwidth:"2px"
    });
    //聊天窗口滚动到最底部
    
    setTimeout(function(){
        $("#chat-body").animate({scrollTop:$("#chat-body").prop("scrollHeight")}, 400);
    },100);
}


//+--------------------------------------------------------------
//格式化处理聊天表情图片
function replaceEm(str) {
    if(!str) return str;
    str = str.replace(/\</g, '&lt;');
    str = str.replace(/\>/g, '&gt;');
    str = str.replace(/\n/g, '<br/>');
    str = str.replace(/\[em_([0-9]*)\]/g, '<img src="./enoji/arclist/$1.gif" border="0" />');
    return str;
}


//+--------------------------------------------------------------
//右侧群成员列表中显示某个用户的详细信息
function showUserInfo(obj) {
    
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        shadeClose: true,
        shade: [0.9, '#ffffff'],
        area: ['420px', '540px'], //宽高
        content: $('#win-show-user-info').html(),
        success:function(layero, index){
            
            var json = $(obj).data('json');
            var name = json.real_name ? json.real_name : (json.nick_name ? json.nick_name : json.name);
            $(layero).find('#show-nick-name').html(json.nick_name);
            $(layero).find('#show-real-name').html(json.real_name);
            $(layero).find('#show-depart-name').html(json.depart_name);
            $(layero).find('#show-sex').html(json.sex);
            $(layero).find('#show-tel').html(json.tel);
            
            $(layero).find('#chat-item').off();
            $(layero).find('#chat-item').on('click',function(){
                console.log(json);
                var chatFromUser = {
                    type:'user',
                    uid:json.id,
                    name:name,
                    time:'',
                    msg:'',
                    face:json.face
                }
                chatMsg.addUser(chatFromUser);
                layer.closeAll();
                setTimeout(function(){
                    $('#item-u-'+json.id).click();
                },20);
            
            });
        }
    })
}


//+--------------------------------------------------------------
//新加会话对象处理
//添加一个会话对象
function addChatObj() {
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        shadeClose: true,
        shade: [0.9, '#ffffff'],
        area: ['500px', '450px'], //宽高
        content: $('#win-choose-chat-user').html(),
        success:function(layero, index)
        {
             //获取可以聊天的用户列表
            external.api('/api/user/getDepartmentChatUsers',{},function(resdata){
                resdata = JSON.parse(resdata);
                var data = resdata.data;
                var chatAddData = new Array();
                data.forEach(function(item){
                    var temp = new Array();
                    temp.value = item.id;
                    temp.title = item.real_name ? item.real_name : (item.nick_name ? item.nick_name : item.name);
                    temp.uid   = item.id;
                    temp.name  = temp.title;
                    temp.time  = '';
                    temp.msg   = '';
                    chatAddData.push(temp);
                });
                layui.transfer.render({
                    elem: $(layero).find('#choose-chat-user')
                    , data: chatAddData
                    , title: ['全部成员', '聊天成员']
                    , showSearch: true,
                    id: "choosevalue"
                })
            });
        }
    })
}
//点击开始聊天后的调用方法
function startAddChatObj() {
    var data = layui.transfer.getData('choosevalue');
    console.log(data.length);
    if (data.length < 1) {
        layer.msg("没有选择成员！");
        return false;
    }
    console.log(data);
    //选择了多个人，创建群，并添加到好友列表
    if (data.length > 1) {
        layer.closeAll();
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            shadeClose: true,
            shade: [0.9, '#ffffff'],
            area: ['500px', '270px'], //宽高
            content: $('#win-choose-chat-group').html(),
            success:function(layero, index)
            {
                $(layero).find(".layui-btn-primary").click();

                var eext = 'create-group-1';
                layero.find('.layui-form').attr('lay-filter', eext);
                
                //绑定点击按钮
                layero.find('#check-create-group').on('click',function(){
                    var postData = layui.form.val(eext);
                    if(!postData.name){
                        layer.msg('群名称不能为空');
                        return false;
                    }
                    console.log(postData);
                    postData.users = data;
                    external.api('/api/user/addChatGroup',postData,function(resdata){
                        resdata = JSON.parse(resdata);
                        layer.msg(resdata.msg);
                        console.log(resdata);
                        if(resdata.status){
                            setTimeout(function(){
                                //添加到好友列表
                                var addFriendInfo = {};
                                addFriendInfo.type   = 2;
                                addFriendInfo.uid    = chatMsg.me.uid;
                                addFriendInfo.friend = resdata.data.groupid;
                                external.api('/api/user/addFriend',addFriendInfo,function(_rsdata){});

                                //本地添加
                                var addgroup = {};
                                addgroup.name = postData.name;
                                addgroup.uid  = resdata.data.groupid;
                                addgroup.time = '';
                                addgroup.face = postData.face ? postData.face : '';
                                addgroup.type = 'group'; 
                                addgroup.msg  = '';
                                console.log('group：',addgroup);
                                chatMsg.addUser(addgroup);
                                layer.closeAll();

                                //+---------------------------------
                                //通知其他好友登录
                                var friendlist = new Array();
                                data.forEach(function(item){
                                    friendlist.push(item.uid);
                                });
                                var addGroupInfo = {};
                                addGroupInfo.action = "addgroup";
                                addGroupInfo.friends= friendlist;
                                ChatSocket.send(JSON.stringify(addGroupInfo));

                                //退出
                                ChatSocket.close();
                                //重新登录，实现新建群的登录
                                startWebSocket();
                                //+----------------------------------
                                
                                $('#item-g-'+addgroup.uid).click();
                            },500);
                        }
                    });
                    
                });
                

            }
        });
    } else {
        //添加用户好友列表
        var item = data[0];
        item.type = 'user';

        //添加到好友列表
        var addFriendInfo = {};
        addFriendInfo.type   = 1;
        addFriendInfo.uid    = chatMsg.me.uid;
        addFriendInfo.friend = item.uid;
        external.api('/api/user/addFriend',addFriendInfo,function(_rsdata){});

        chatMsg.addUser(item);
        layer.closeAll();
        $('#item-u-'+item.uid).click();
    }
    
}
//添加会话对象处理结束
//+---------------------------------------------------

function execArrdioFunction() {
    external.getFileContent("dlg/web/addobj.html");
}

//+--------------------------------------------------
//发送聊天方法

//发送文件
function uploadFile()
{
    external.uploadFile(
        function(result){
            var json = JSON.parse(result);
            if(json.file){
                var msg = json.file;
                var ext = getType(msg);
                sendPostMsg(msg,'file');
            }
        });
}
//发送图片
function uploadImgFile()
{
    external.uploadImgFile(
        function(result){
            var json = JSON.parse(result);
            if(json.file){
                var msg = json.file;
                sendPostMsg(msg,'img');
            }
        });
}
//发送音频
function uploadSoundFile()
{
    external.uploadSoundFile(
        function(result){
            var json = JSON.parse(result);
            if(json.file){
                var msg = json.file;
                sendPostMsg(msg,'sound');
            }
        });
}
//发送视频
function uploadVideoFile()
{
    external.uploadVideoFile(
        function(result){
            var json = JSON.parse(result);
            if(json.file){
                var msg = json.file;
                sendPostMsg(msg,'video');
            }
        });
}


/**
 * 发送消息
 * @param {*} msg  消息内容
 * @param {*} msgtype  消息类型 txt file img sound video
 */
function sendPostMsg(msg,msgtype)
{
        msg = $.trim(msg);
        //取得发送对象信息
        var sendUserInfo = $('#chat-user-info').data('info');
        if (!sendUserInfo) {
            layer.msg('还没有选择发送对象呢？');
            $('#chat-input-editer').focus();
            return;
        }
        if (msg == '') {
            layer.msg('什么都没有填写呢？');
            $('#chat-input-editer').focus();
            return;
        }
        if(msg=='ding()')
        {
            external.playding();
            return;
        }
        console.log(sendUserInfo);
        //构建发送消息体
        var target = sendUserInfo.type;
        //文字发送action: "msg", msg: 
        var sendMsg = {
            action: "msg", "msg": {
                fromUid: chatMsg.me.uid,
                fromName: chatMsg.me.name,
                fromFace: chatMsg.me.face,
                fromTime: getNowDateTime(),
                toUid: sendUserInfo.uid,
                toName: sendUserInfo.name,
                toFace: sendUserInfo.face,
                type: msgtype,
                target: target,
                content: msg
            }
        }
        console.log(sendMsg);
        //通过socket发送给服务器，待完成
        sendMsg = JSON.stringify(sendMsg);
        ChatSocket.send(sendMsg);


        //构建本地消息
        var clientMsg = {
            name: chatMsg.me.name,
            uid: chatMsg.me.uid,
            face: chatMsg.me.face,
            time: getNowDateTime('天时'),
            type: msgtype,
            content: msg,
            place: 'right'
        }

        //发送到本地页面并保存
        var msgid = sendUserInfo.type + '-' + sendUserInfo.uid+'-'+chatMsg.me.uid;
        chatMsg.createMsg(msgid, 'right', clientMsg, true, true);
        $('#chat-input-editer').val('');
}

//上传群头像
function upGroupFace(obj)
{

    external.uploadImgFile(
        function(result){
            var json = JSON.parse(result);
            if(json.file){
                $(obj).parent().find('#face').val(json.file);
                $(obj).parent().find('#show-face').attr('src',json.file);
                $(obj).parent().find('#show-face').show();
            }else{
                layer.msg('头像上传失败！')
            }
        });
}

//+------------------------------

//+---------------------------------------------------
//随机产生一个颜色
function setFaceColor(query)
{
    if(query){
        $(query).each(function(){
            var item = $(this);
            var img  = item.css("backgroundImage");
            //聊天列表
            if(img=='none'){
                var color = getRGBColor();
                item.css({background:color});
            }else{
                item.html('');
                item.css('backgroundSize','100% 100%');
                item.css('backgroundRepeat','no-repeat');
            }
        });
    }
    else{
        $('.user-face').find('.img').each(function(){
            var item = $(this);
            var img  = item.css("backgroundImage");
            //聊天列表
            if(img=='none'){
                var color = getRGBColor();
                item.css({background:color});
            }else{
                item.html('');
                item.css('backgroundSize','100% 100%');
                item.css('backgroundRepeat','no-repeat');
            } 
        });
        
        $('.chat-show-face').find('.img').each(function(){
            var item = $(this);
            var img  = item.css("backgroundImage");
            //聊天列表
            if(img=='none'){
                var color = getRGBColor();
                item.css({background:color});
            }else{
                item.html('');
                item.css('backgroundSize','100% 100%');
                item.css('backgroundRepeat','no-repeat');
            } 
        });

        $('.group-info-list-img').each(function(){
            var item = $(this);
            var img  = item.css("backgroundImage");
            //聊天列表
            if(img=='none'){
                var color = getRGBColor();
                item.css({background:color});
            }else{
                item.html('');
                item.css('backgroundSize','100% 100%');
                item.css('backgroundRepeat','no-repeat');
            } 
        });
        
        
    }
}

//颜色生成
function getRGB(min, max) {
    return {
        r: min + Math.round(Math.random() * 1000) % (max - min),
        g: min + Math.round(Math.random() * 1000) % (max - min),
        b: min + Math.round(Math.random() * 1000) % (max - min)
    };
}
function toHex(val) {
    var hex = '00';
    if(val) {
        hex = parseInt(val).toString(16);
        if(hex.length == 1) {
            hex = '0' + hex;
        }
    }
    return hex;
}

function getRGBColor()
{
    var min = 580;
    var max = 720;
    var minHex = parseInt('99', 16);
    var maxHex = parseInt('DD', 16);
    while(true) {
        color = getRGB(minHex, maxHex);
        if((color.r + color.g + color.b) >= min && (color.r + color.g + color.b) <= max) {
            break;
        }
    }
    return '#' + toHex(color.r) + toHex(color.g) + toHex(color.b);
}
//+-------------------------
//获取当前的时间
function getNowDateTime(mod)
{
    var d = new Date()
    var year  = d.getFullYear();
    var month = d.getMonth();
    var day   = d.getDate();
    var hour  = d.getHours();	
    var minute= d.getMinutes();
    var second=d.getSeconds();
    month = month + 1;
    if(month<10) month = '0'+month;
    if(day<10) day = '0'+day;
    if(hour<10) hour = '0'+hour;
    if(minute<10) minute = '0'+minute;
    if(second<10) second = '0'+second;

    var datetime =  year+'-'+month+'-'+day + ' '+hour+':'+minute+':'+second;
    if(mod=='小时'){
        datetime = hour+':'+minute;
    }
    if(mod=='天'){
        datetime = year+'-'+month+'-'+day
    }
    if(mod=='天时'){
        datetime = day+'日 '+hour+':'+minute;
    }

    return datetime;
}

//搜索
function searchChatUsers(){
    var key = $('#search-user-key').val();
    if(!key){
        chatMsg.GetMyUsers();
    }else{
        //执行搜索
        chatMsg.GetMyUsers(key);
    }

}

function getDateTime(datetime,mod)
{
   try {
    var strs = datetime.split(' ');
    var days = strs[0].split('-');
    var times = strs[1].split(':');
    var datetimestrs = '';
    if(mod=='小时') datetimestrs = times[0]+':'+times[1];
    if(mod=='天') datetimestrs = days[0]+'-'+days[1]+'-'+days[2];
    if(mod=='天时') datetimestrs = days[2]+'日'+times[0]+':'+times[1];
    return datetimestrs;
   } catch (error) {
    return datetime;
   }
    
}
//格式化处理消息内容
function getFomartContent(type,content){
    var strs = '';
    if(type=='txt'){
        if(content.length>10) strs = content.substr(0,9);
        else strs = content;
    }
    if(type=='img'){
        strs = '[图片]';
    }
    if(type=='file'){
        strs = '[文件]';
    }
    if(type=='sound'){
        strs = '[语音]';
    }
    if(type=='video'){
        strs = '[视频]';
    }
    return strs;
}
//获取文件后缀
function getType(file){
    var filename=file;
    var index1=filename.lastIndexOf(".");
    var index2=filename.length;
    var type=filename.substring(index1,index2);
    return type;
}

function clearAll()
{  
    store.clearAll()
}

function downResources(obj)
{
    var url = $(obj).data('url');
    external.downloadfile(url);
}

function playVadio(obj)
{
    var url = $(obj).data('url');
    external.playvideo(url);
}

function playSound(obj)
{
    var url = $(obj).data('url');
    var isend = $(obj).hasClass('layui-icon-mute');
    if(isend){
        external.playsoundend(url);
        $(obj).removeClass('layui-icon-mute').addClass('layui-icon-speaker');
    }else{
        external.playsound(url);
        $(obj).removeClass('layui-icon-speaker').addClass('layui-icon-mute');
    }
    
}
