
//页面加载完毕执行
$(function () {

    //定义定时器变量
    console.log("已创建定时器变量");
    var timer;
    var num=1 ;
    //两秒调用一次方法,查询订单状态
    timer=setInterval(timeOrder,2000);
    //存储文件路径
    var path;

    function timeOrder() {
        //获取当前页面的微信支付订单号
        var orderId = $("#wx-img").attr('src');
        orderId = orderId.substring(14,orderId.length);
        console.log("src:-->" + orderId);

        //异步发送轮询请求. 每隔2秒查询订单状态
        $.get("/orderquery",{"order":orderId},function (data) {
            console.log("data:" + data);
            console.log("num:" +num);
            // 订单完成支付,清除定时器发送请求,跳转到文件下载链接.

            // 真实环境
            if (data=="支付成功"){
                console.log("清除定时器");
                clearInterval(timer);
                //测试环境,跳转软件详情页面
                $.get("/wxPayDown/"+orderId,function (data) {
                    console.log("发送请求成功,即将跳转界面.");
                    console.log(data.filePath);
                    //文件路径 = filepath.
                    path = data.filePath;
                    // 测试环境
                    //下载文件
                    document.location.href="http://localhost/toDown/" + path;
                    console.log("执行到此处. 运行中--->");

                    //线上环境
                    //下载文件    path = 随机16位字符串 + 文件名字  .然后后端进行字符串截取.
                    // document.location.href="http://purpleairs.com/toDown/" + path;
                    // console.log("执行到此处. 运行中--->");
                });
                //跳转到软件下载页面.
                //测试环境
                // document.location.href="http://www.baidu.com";

                //ajax请求下载软件
            }

            //测试环境
            num++
            // if (num==10){
            //     console.log("清除定时器");
            //     clearInterval(timer);
            //     //测试环境,跳转软件详情页面
            //     $.get("/wxPayDown/"+orderId,function (data) {
            //         console.log("发送请求成功,即将跳转界面.");
            //         console.log(data.filePath);
            //         //文件路径 = filepath.
            //         path = data.filePath;
            //         document.location.href="http://localhost/toDown/" + path;
            //         console.log("执行到此处. 运行中--->");
            //     });
            //     //跳转到软件下载页面.
            //     //测试环境
            //     // document.location.href="http://www.baidu.com";
            //
            //     //ajax请求下载软件
            // }
        })
    }
})