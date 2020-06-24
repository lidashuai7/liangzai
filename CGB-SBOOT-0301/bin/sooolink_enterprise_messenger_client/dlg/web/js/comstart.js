window.onload = function () {
    setLMRSize();
    var totalWidth = $("body").width();
    var userWidth = totalWidth - 517;
    $('#show-depart-users').width(userWidth);

};
window.onresize = function () {
    setLMRSize();
    var totalWidth = $("body").width();
    var userWidth = totalWidth - 520;
    $('#show-depart-users').width(userWidth);
}

$(function () {
    //菜单点击事件
    $('.user-item').each(function () {
        var item = $(this);
        $(this).on('click', function () {
            $('.middle-item').hide();
            $('.user-item').removeClass("active");
            item.addClass("active");


        });
    });


    //公司信息保存
    layui.form.on('submit(form-cominfo)', function (data) {
        console.log(data);
        var fileds = data.field;
        if (fileds.name == '') {
            layer.msg('公司名称没有填写');
            $('#name').focus();
            return false;
        }
        if (fileds.tel == '') {
            layer.msg('公司电话没有填写');
            $('#tel').focus();
            return false;
        }
        if (fileds.mobile_tel == '') {
            layer.msg('公司负责人手机没有填写');
            $('#mobile_tel').focus();
            return false;
        }
        if (fileds.addr == '') {
            layer.msg('公司地址没有填写');
            $('#addr').focus();
            return false;
        }
        try {
            external.api('/api/com/saveComInfo', fileds, function (rsdata) {
                console.log(rsdata);
                rsdata = JSON.parse(rsdata);
                if (rsdata.status) {
                    layer.msg(rsdata.msg);
                }
            });
        } catch (error) {

        }


        return false;
    });

    //选中第一个菜单
    setTimeout(function () {
        $($(".user-item").eq(0)).click();
        try {
            //请求公司现在的资料
            var id = $('#id').val();
            external.api('/api/com/getComInfo', { id: id }, function (rsdata) {
                console.log(rsdata);
                rsdata = JSON.parse(rsdata);
                console.log(rsdata);
                if (rsdata.status) {
                    var formData = rsdata.data;
                    layui.form.val("form-cominfo", formData);
                }
            });
        } catch (error) {

        }
    }, 20);




    //加载完毕
    try {
        external.closeLoad();
    } catch (error) {

    }

});

function setComInfo() {
    setTimeout(function () { $('#com-info').show(); }, 10);

}

//+------------------------------------------------------
//递归处理数组信息,显示用
function sonsTree(arr, id) {
    var temp = [], lev = 0;
    var forFn = function (arr, id, lev) {
        for (var i = 0; i < arr.length; i++) {
            var item = arr[i];
            if (item.parent_id == id) {
                item.lev = lev;
                temp.push(item);
                forFn(arr, item.id, lev + 1);
            }
        }
    };
    forFn(arr, id, lev);
    return temp;
}
//递归处理数组，构建成一颗树
function toTreeData(data, pid) {
    function tree(id) {
        var arr = [];
        data.filter(function (item) {
            return item.parent_id === id;
        }).forEach(function (item) {
            arr.push({
                spread: true,
                id: item.id,
                title: item.name,
                children: tree(item.id)
            });
        });
        return arr;
    }
    return tree(pid); // 第一级节点的父id，是null或者0，视情况传入
}

function getChildIds(data) {
    var ids = new Array();
    var datalist = new Array();
    console.log(data);
    datalist.push(data);
    console.log(datalist);
    function tree(datalist) {
        for (var i = 0; i < datalist.length; i++) {
            var item = datalist[i];
            ids.push(item.id);
            if (item.children.length > 0) {
                tree(item.children);
            }
        }
    }
    tree(datalist);
    var idstrs = ids.join(',');
    return idstrs;
}

//+------------------------------------------------------
var allDepartmentArray = null; //全部部门信息
//显示部门信息
function setDepartInfo() {
    setTimeout(function () { $('#depart-info').show(); }, 10);

    //向接口请求全部数据
    external.api('/api/com/getAllDepartment', {}, function (rsdata) {
        rsdata = JSON.parse(rsdata);
        if (rsdata.data.length < 0) return;

        // var rsdata = {}
        // rsdata.data = [
        //     {
        //         "id": 1,
        //         "name": "技术部",
        //         "parent_id": 0,
        //         "status": 1
        //     },
        //     {
        //         "id": 2,
        //         "name": "行政部",
        //         "parent_id": 0,
        //         "status": 1
        //     },
        //     {
        //         "id": 3,
        //         "name": "技术部子1",
        //         "parent_id": 1,
        //         "status": 1
        //     }
        // ];
        allDepartmentArray = rsdata.data;

        var data = toTreeData(rsdata.data, 0);

        layui.tree.render({
            elem: '#depart-list'
            , data: data
            , edit: ['update', 'del'] //操作节点的图标
            , accordion: true
            , onlyIconControl: true
            , click: function (obj) {
                var data = obj.data;
                console.log(data);
                var ids = getChildIds(data);
                console.log(ids);
                //显示用户
                showDepartmentUsers(ids);
                //console.log(ids);
            }
            , operate: function (obj) {
                var type = obj.type; //得到操作类型：add、edit、del
                var data = obj.data; //得到当前节点的数据
                var elem = obj.elem; //得到当前节点元素

                var id = data.id;
                if (type === 'add') {
                } else if (type === 'update') { //修改节点
                    var name = elem.find('.layui-tree-txt').html();
                    external.api('/api/com/saveDepartment', { id: id, name: name }, function (rsdata) {
                        rsdata = JSON.parse(rsdata);
                        layer.msg(rsdata.msg);
                        if (rsdata.status) {
                            setTimeout(function () {
                                layer.closeAll();
                                //刷新部门列表
                                setDepartInfo();
                            }, 500);
                        }
                    });

                } else if (type === 'del') { //删除节点
                    external.api('/api/com/delDepartment', { id: id }, function (rsdata) {
                        rsdata = JSON.parse(rsdata);
                        layer.msg(rsdata.msg);
                        if (rsdata.status) {
                            setTimeout(function () {
                                layer.closeAll();
                                //刷新部门列表
                                setDepartInfo();
                            }, 500);
                        }
                    });

                };
            }
        });

        setTimeout(function () {
            $('#depart-list').niceScroll({
                cursorcolor: "#009688",
                cursoropacitymax: 0.6,
                cursorwidth: "2px",
                autohidemode: false
            });
            $('#depart-user-lists').niceScroll({
                cursorcolor: "#009688",
                cursoropacitymax: 0.6,
                cursorwidth: "2px",
                autohidemode: false
            });
            $('.layui-tree-txt').eq(0).click();
            
        }, 500);

    });




}


//添加一个部门
function addTopDepart() {
    var content = $('#tpl-depart-add-info').html();
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        shadeClose: true,
        shade: [0.9, '#ffffff'],
        area: ['400px', '250px'], //宽高
        content: content,
        success: function (layero, index) {
            var data = sonsTree(allDepartmentArray, 0);
            var seldl = '  <option value="0">顶级</option>';
            var seldd = ' <dd lay-value="0" class="layui-select-tips">顶级</dd>';
            for (var i = 0; i < data.length; i++) {
                seldl += '<option value="' + data[i].id + '">' + showLevLine(data[i].lev) + data[i].name + '</option>';
                seldd += '<dd lay-value="' + data[i].id + '" >' + showLevLine(data[i].lev) + data[i].name + '</dd>';
            }
            layero.find(".depatment-choose-list").html(seldl);
            layero.find(".depatment-choose-list-dd").html(seldd);
            //console.log(layero, index);
            setTimeout(function () {
                layero.find('.reset').click();
            }, 100);

            layero.find('#submit-save-form').on('click', function () {
                var parent_id = layero.find('#parent_id').val();
                var name = layero.find('#name').val();
                if (!name) {
                    layer.msg('部门名称没有填写');
                    layero.find('#name').focus();
                    return;
                }
                external.api('/api/com/saveDepartment', { id: 0, parent_id: parent_id, name: name }, function (rsdata) {
                    rsdata = JSON.parse(rsdata);
                    layer.msg(rsdata.msg);
                    if (rsdata.status) {
                        setTimeout(function () {
                            layer.closeAll();
                            //刷新部门列表
                            setDepartInfo();
                        }, 500);
                    }
                });
            });

        }
    })
}

function showLevLine(lev) {
    var line = '';
    for (var i = 0; i < lev; i++) {
        line = line + '|--';
    }
    return line;
}
//+--------------------------------------------------------------

//显示用户列表
var globalIds = null;
function showDepartmentUsers(ids) {
    globalIds = ids;
    //console.log(ids);
    external.api('/api/user/getDepartmentUsers', { ids: ids }, function (rsdata) {
        rsdata = JSON.parse(rsdata);
        // var rsdata = {
        //     "data": [
        //         {
        //             "company_id": 1,
        //             "create_user": 0,
        //             "depart_name": "信息中心",
        //             "department_id": 1,
        //             "email": "hailingr@foxmail.com",
        //             "face": "http://127.0.0.1:9981/data/face/blue-2.jpg",
        //             "id": 1,
        //             "name": "hailin.com",
        //             "nick_name": "海林",
        //             "pwd": "A139502F801C7181EC9E51C6EA89F7D7",
        //             "real_name": "蒋海林",
        //             "sex": "无",
        //             "status": 1,
        //             "tel": "13540633386",
        //             "weixin": "hailingr "
        //         }
        //     ],
        //     "msg": "用户列表",
        //     "status": 1
        // };
        var data = rsdata.data;
        var listStrs = '';
        if (data.length > 0) {
            data.forEach(function (item) {
                var zhi = new Array();
                zhi = ['', '<span style="color:green">在职</span>', '<span style="color:red">离职</span>'];
                listStrs += '<tr>';
                listStrs += '<td>' + item.depart_name + '</td>';
                listStrs += '<td>' + item.real_name + '</td>';
                listStrs += '<td>' + item.nick_name + '</td>';
                listStrs += '<td>' + item.name + '</td>';
                listStrs += '<td>' + item.sex + '</td>';
                listStrs += '<td>' + item.tel + '</td>';
                listStrs += '<td>' + zhi[item.status] + '</td>';
                listStrs += '<td>';
                listStrs += '    <div onclick="managerUser(this)" class="handclass" style="color:#009688" data-json=\'' + JSON.stringify(item) + '\'>操作</div>';
                listStrs += '</td>';
                listStrs += '</tr>';
            });
        }
        $('#depart-user-list').html(listStrs);

    });
}

/**
 * 用户详情
 * @param {*} obj 
 */
function managerUser(obj) {
    var json = $(obj).data('json');
    var strs = '<div data-json=\'' + JSON.stringify(json) + '\'><div class="handclass" onclick="userShow(this);">详情</div>';
    strs += '<div class="handclass" onclick="userEdit(this);">编辑</div>';
    strs += '<div class="handclass" onclick="userDel(this);">删除</div>';
    strs += '<div class="handclass" onclick="userOut(this);">离职</div></div>';
    layer.tips(strs, obj, {
        tips: [3, '#009688']
    });
}

function userShow(obj) {
    var json = $(obj).parent().data('json');
    addDepartUser('show', json);
    console.log(json);
}

function userEdit(obj) {
    var json = $(obj).parent().data('json');
    addDepartUser('edit', json);
    console.log(json);
}

function userDel(obj) {
    var json = $(obj).parent().data('json');
    layer.confirm('确定要删除【' + json.real_name + '】？删除后不可恢复！', function (index) {
        //do something
        external.api('/api/user/delDepartmentUser', { id: json.id }, function (rsdata) {
            rsdata = JSON.parse(rsdata);
            layer.msg(rsdata.msg);
            if (rsdata.status) {
                setTimeout(function () {
                    layer.closeAll();
                    //刷新用户列表
                    showDepartmentUsers(globalIds);
                }, 500);
            }
        });
    });
    console.log(json);
}

function userOut(obj) {
    var json = $(obj).parent().data('json');
    layer.confirm('确定要将【' + json.real_name + '】离职？', function (index) {
        external.api('/api/user/setDepartmentUserOut', { id: json.id }, function (rsdata) {
            rsdata = JSON.parse(rsdata);
            layer.msg(rsdata.msg);
            if (rsdata.status) {
                setTimeout(function () {
                    layer.closeAll();
                    //刷新用户列表
                    showDepartmentUsers(globalIds);
                }, 500);
            }
        });
    });
    console.log(json);
}


//添加部门用户
function addDepartUser(type, json) {
    var content = $('#tpl-depart-user-info').html();
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        shadeClose: true,
        shade: [0.9, '#ffffff'],
        area: ['480px', '500px'], //宽高
        content: content,
        success: function (layero, index) {
            setTimeout(function () {
                var eext = 'edit-depart-user-mod-1';
                layero.find('.layui-form').attr('lay-filter', eext);

                layero.find('.reset').click();
                layero.find('.layui-anim-upbit').height(220);

                var data = sonsTree(allDepartmentArray, 0);
                var seldl = '';
                var seldd = '';
                for (var i = 0; i < data.length; i++) {
                    seldl += '<option value="' + data[i].id + '">' + showLevLine(data[i].lev) + data[i].name + '</option>';
                    seldd += '<dd lay-value="' + data[i].id + '" >' + showLevLine(data[i].lev) + data[i].name + '</dd>';
                }
                layero.find(".depatment-choose-list").html(seldl);
                layero.find(".depatment-choose-list-dd").html(seldd);

                //类型
                if ((type == 'show') || (type == 'edit')) {
                    layui.form.val(eext, json);
                    if (type == 'show') {
                        layero.find("input").attr("disabled", true);
                        layero.find("select").attr("disabled", true);
                        layero.find("reset").addClass("layui-btn-disabled");
                        layero.find("button").addClass("layui-btn-disabled");
                    }

                    if (type == 'edit') {
                        layero.find("#pwd").val('');
                    }
                }

                //按钮绑定
                if ((type == 'add') || (type == 'edit')) {
                    layero.find('.check-save-form').on('click', function () {
                        postData = layui.form.val(eext);
                        postData.uid = postData.id;
                        delete postData.id;
                        if (!postData.name) {
                            layer.msg("用户名不能为空");
                            return false;
                        }
                        if (!postData.real_name) {
                            layer.msg("姓名不能为空");
                            return false;
                        }
                        if (!postData.tel) {
                            layer.msg("电话不能为空");
                            return false;
                        }

                        external.api('/api/user/edit', postData, function (rsdata) {
                            rsdata = JSON.parse(rsdata);
                            layer.msg(rsdata.msg);
                            if (rsdata.status) {
                                setTimeout(function () {
                                    layer.closeAll();
                                    //刷新用户列表
                                    showDepartmentUsers(globalIds);
                                }, 500);
                            }

                        });
                    });
                }

            }, 100);
        }
    })
}

