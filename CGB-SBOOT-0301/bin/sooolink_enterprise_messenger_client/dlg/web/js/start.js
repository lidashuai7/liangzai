window.onload = function () {
    setMRHeight();
    setLMRSize();
    setUserListHeight();
    setRightListHeight();
    setScollInfo();

};
window.onresize = function () {
    setLMRSize();
    setMRHeight();
    setUserListHeight();
    setRightListHeight();
    setScollInfo();

}

$(function () {
    //右侧展开按钮点击事件
    $('#chat-right').on('click', function () {
        var rightIsHidden = $('.right').is(":hidden");
        if (rightIsHidden) {
            $(this).addClass("layui-icon-spread-left");
            $(this).removeClass("layui-icon-shrink-right");
            $(".right").show();
            setLMRSize();
            setLMRSize();
        } else {
            $(this).addClass("layui-icon-shrink-right");
            $(this).removeClass("layui-icon-spread-left");
            $(".right").hide();
            setLMRSize();
            setLMRSize();
        }
    });


    //初始化聊天表情
    $('#chat-send-face').qqFace({
        id: 'facebox',
        assign: 'chat-input-editer',
        path: './enoji/arclist/'	//表情存放的路径
    });
    //初始化发送按钮
    $('#chat-send-msg').hover(
        function () {
            $(this).css("backgroundColor", "#f0f0f0");
        },
        function () {
            $(this).css("backgroundColor", "#ffffff");
        }
    );



    //点击发送消息事件
    $("#chat-send-msg").on('click', function () {
        var msg = $('#chat-input-editer').val();
        sendPostMsg(msg,'txt')
    });


    //监控ctrl+enter按钮
    $(window).keydown(function (event) {
        if (event.ctrlKey && event.keyCode == 13) {
            setTimeout(function () {
                $("#chat-send-msg").click();
            }, 100);
        }
    });
    //点击发送事件结束


    //给聊天消息中的图片绑定点击放大查看功能
    $('.chat-info-content').find("img").live('click', function () {
        //$(this).addClass("handclass");
        var imgImgWidth = $("body").width() - 200;
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            shade: [0.9, '#ffffff'],
            area: ["auto"],
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: "<img style='max-width:" + imgImgWidth + "px;' src='" + $(this).attr("src") + "' border=0 />"
        });
    });

    //头像颜色随机
    setFaceColor();
    setFaceColor(".my-chat-face");




    

    //默认加入系统用户
    var userData = {
        uid: 99999999,
        name: "系统",
        time: getNowDateTime('小时'),
        msg: "登录成功",
        type: 'sys'
    };
    chatMsg.addUser(userData);
    //选中默认第一个用户，如果有
    setTimeout(function () {
        $($(".user-item").eq(0)).click();
    }, 50);

    //获取服务器好友列表
    setTimeout(function(){
        // external.api("/api/user/getFriendList",{id:1},function(friendlist){
        //     friendlist = JSON.parse(friendlist);
        //     console.log(friendlist);
        //     if(friendlist.status){
        //         var allFriends = friendlist.data;
        //         if(allFriends.length>0){
        //             allFriends.forEach(function(item){
        //                 if(item.name && item.uid && item.type){
        //                     if(item.content) item.content = getFomartContent(item.type,item.content);
        //                     if(item.time) item.time = getDateTime(item.time,'小时');
        //                     chatMsg.addUser(item);
        //                 }
        //             });
        //         }
                
        //     }
        // });
        
        //取得离线聊天记录
        setTimeout(function(){ 
            var lastChatTime = store.get('last-msg-time');
            if(!lastChatTime) lastChatTime = '';
            external.api('/api/user/getHistoryChatLogs',{lastChatTime:lastChatTime},function(logs){
                logs = JSON.parse(logs);
                if(logs.status){
                    logs = logs.data;
                    //用户消息
                    var userlogs = logs.userlogs;
                    if(!userlogs) return;
                    //获取当前聊天对象
                    var chatObjInfo = $('#chat-user-info').data('info');
                    if(userlogs.length>0){
                        userlogs.reverse();
                        userlogs.forEach(function(item){
                            //私聊
                            var datainfo = {};
                            datainfo.name = item.name;
                            datainfo.uid  = item.from_user;
                            datainfo.face = item.face;
                            datainfo.time = item.from_time;
                            datainfo.type = item.type;
                            datainfo.content = item.content;

                            var place = 'left';
                            var msgid = 'user-' + item.from_user+'-'+chatMsg.me.uid;
                            if(item.from_user==chatMsg.me.uid) place = 'right';

                            datainfo.place = place;
                            if((chatObjInfo.type=="user") && (chatObjInfo.uid==item.from_user)){
                                //前端消息，收到的消息发送者是我正在聊天的对象
                                //if(item.from_user!=chatMsg.me.uid){
                                    chatMsg.createMsg(msgid,place,datainfo,true,true);
                                //}
                            }else{
                                //后端消息，收到的消息发送者不是我正在聊天的对象
                                //消息发送者不在聊天列表中
                                var chatFromUser = {
                                    type:"user",
                                    uid:item.from_user,
                                    name:item.name,
                                    time:getDateTime(item.from_time,'小时'),
                                    msg:getFomartContent(item.type,item.content),
                                    face:item.face
                                }
                                chatMsg.addUser(chatFromUser);
                                chatMsg.createMsg(msgid,place,datainfo,false,true);
                                //设置红点
                                var target = 'user';
                                //延时，避免打红点失败
                                setTimeout(function(){
                                    chatMsg.addHot(target,item.from_user);
                                },20);
                                
                                //任务栏闪动，播放收到消息提示语音
                            }
                        });
                    }
                    //群消息
                    var grouplogs = logs.grouplogs;
                    console.log('groups',grouplogs);
                    if(grouplogs.length>0){
                        grouplogs.reverse();
                        grouplogs.forEach(function(item){
                            var msgid = 'group-' + item.group_id+'-'+chatMsg.me.uid;
                            var place = 'left';
                            if(item.from_user==chatMsg.me.uid) place = 'right';

                            var datainfo = {};
                            datainfo.name = item.name;
                            datainfo.uid  = item.from_user;
                            datainfo.face = item.face;
                            datainfo.time = item.from_time;
                            datainfo.type = item.type;
                            datainfo.content = item.content;
                            datainfo.place = place;


                            if((chatObjInfo.type=='group') && (chatObjInfo.uid==item.group_id)){
                                //前端消息，收到的消息发送者是我正在聊天的对象
                                //判断接受到的消息是否是自己
                                //if(msg.fromUid!=chatMsg.me.uid){
                                    chatMsg.createMsg(msgid,place,datainfo,true,true);
                                //}
                            }else{
                                //后端消息，收到的消息发送者不是我正在聊天的对象
                                //消息发送者不在聊天列表中
                                var chatFromUser = {
                                    type:'group',
                                    uid:item.group_id,
                                    name:item.to_name,
                                    time:getDateTime(item.from_time,'小时'),
                                    msg:getFomartContent(item.type,item.content),
                                    face:item.face
                                }
                                chatMsg.addUser(chatFromUser);
                                chatMsg.createMsg(msgid,place,datainfo,false,true);
                                //设置红点
                                var target = 'group';
                                setTimeout(function(){
                                    chatMsg.addHot(target,item.group_id);
                                },20);
                                
                                //任务栏闪动，播放收到消息提示语音
                            }
                        });
                    }

                    //更新最后收取到消息的时间
                    store.set('last-msg-time',getNowDateTime(''));
                    
                }
            });
        },5000);
        
    },900);
   

    

    //监控搜索按钮
    $("#search-user-key").keydown(function (event) {
        if (event.keyCode == 13) {
            setTimeout(function () {
                searchChatUsers();
            }, 100);
        }
    });
});