//var websocketUrl = external.wsUrl();
var websocketUrl = 'ws://127.0.0.1:9982';

console.log(websocketUrl);

var ChatSocket = null;

startWebSocket();

function startWebSocket(){
    ChatSocket = new WebSocket(websocketUrl);

    //连接服务器
    ChatSocket.onopen = function () {
        //连接成功后，执行登录
        var uid = chatMsg.me.uid;
        if (!uid) layer.msg('uid为空，不能登录');
        /**
         {
            action:"login",
            uid:1
         }
         */
        var login = {};
        login.action = 'login';
        login.uid = uid;
        login = JSON.stringify(login);
    
        ChatSocket.send(login);
    };
    
    ChatSocket.onmessage = function (e) {
        console.log("client：接收到服务端的消息 " + e.data);
        var msg = JSON.parse(e.data);
        //如果存在执行指令
        if(msg.action){
            if(msg.action=='reconnect'){
                 //先退出
                 ChatSocket.close();
                 //重新登录，实现新建群的登录
                 startWebSocket();
                 //重新登录完毕
                 //+----------------------------------
            }
            return;
        }
        //对收到的消息进行渲染
        var sendUid = msg.fromUid;
        var target  = msg.target;
        var msgid   = '';
        if(target=='user') var msgid =  'user-' + msg.fromUid+'-'+chatMsg.me.uid;
        if(target=='group') var msgid = 'group-' + msg.fromUid+'-'+chatMsg.me.uid;
        if(target=='sys') var msgid =  'sys-' + msg.fromUid+'-'+chatMsg.me.uid;
        //获取当前聊天对象
        var chatObjInfo = $('#chat-user-info').data('info');
        
        /**
         * data = {
            name:'将海林',
            uid:1,
            face:'',
            time:'12:20',
            type:'txt',
            content:'你要，我还你。。',
            place:'left'
        }
         */
        var datainfo = {};
        datainfo.name = msg.fromName;
        datainfo.uid  = msg.fromUid;
        datainfo.face = msg.fromFace;
        datainfo.time = msg.fromTime;
        datainfo.type = msg.type;
        datainfo.content = msg.content;
        datainfo.place = 'left';
        console.log('正在聊天',chatObjInfo);
        console.log('收到消息',msg);
        //如果是群消息
        var place = 'left';
        if(msg.fromUid==chatMsg.me.uid) place = 'right';
        //群聊
        if(msg.target=='group'){
            msgid = 'group-' + msg.toUid+'-'+chatMsg.me.uid;
            if((chatObjInfo.type==msg.target) && (chatObjInfo.uid==msg.toUid)){
                //前端消息，收到的消息发送者是我正在聊天的对象
                //判断接受到的消息是否是自己
                if(msg.fromUid!=chatMsg.me.uid){
                    chatMsg.createMsg(msgid,place,datainfo,true,true);
                }
                //任务栏闪动，播放收到消息提示语音
                external.playding(false);
                external.playico(false);
            }else{
                //后端消息，收到的消息发送者不是我正在聊天的对象
                //消息发送者不在聊天列表中
                var chatFromUser = {
                    type:msg.target,
                    uid:msg.toUid,
                    name:msg.toName,
                    time:getDateTime(msg.fromTime,'小时'),
                    msg:getFomartContent(msg.type,msg.content),
                    face:msg.toFace
                }
                //console.log(chatFromUser);
                chatMsg.addUser(chatFromUser);
                chatMsg.createMsg(msgid,place,datainfo,false,true);
                //设置红点
                chatMsg.addHot(target,msg.toUid);
                //任务栏闪动，播放收到消息提示语音
                external.playding(true);
                external.playico(true);
            }
        }else{
            //私聊
            if((chatObjInfo.type==msg.target) && (chatObjInfo.uid==msg.fromUid)){
                //前端消息，收到的消息发送者是我正在聊天的对象
                if(msg.fromUid!=chatMsg.me.uid){
                    chatMsg.createMsg(msgid,place,datainfo,true,true);
                }
                //任务栏闪动，播放收到消息提示语音
                external.playding(false);
                external.playico(false);
            }else{
                //后端消息，收到的消息发送者不是我正在聊天的对象
                //消息发送者不在聊天列表中
                var chatFromUser = {
                    type:msg.target,
                    uid:msg.fromUid,
                    name:msg.fromName,
                    time:getDateTime(msg.fromTime,'小时'),
                    msg:getFomartContent(msg.type,msg.content),
                    face:msg.fromFace
                }
                chatMsg.addUser(chatFromUser);
                chatMsg.createMsg(msgid,place,datainfo,false,true);
                //设置红点
                chatMsg.addHot(target,msg.fromUid);
                //任务栏闪动，播放收到消息提示语音
                external.playding(true);
                external.playico(true);
            }
        }

        //更新最后收取到消息的时间
        var last_msg_time = getNowDateTime('');
        console.log(last_msg_time);
        store.set('last-msg-time',last_msg_time);
        
    };
}



//发送的消息体结构
var senddata = {
    action: "msg", msg: {
        fromUid: "",
        fromName: "",
        fromFace: "",
        fromTime: "",
        toUid: "",
        toName: "",
        toFace: "",
        type: "",//消息类型txt img file
        target: 'user',//消息对象 user group sys
        content: ""
    }
};

//接受到的消息体结构
var getdata = {
    fromUid: "",
    fromName: "",
    fromFace: "",
    fromTime: "",
    toUid: "",
    toName: "",
    toFace: "",
    type: "",//消息类型txt img file
    target: 'user',//消息对象 user group sys
    content: ""
}




