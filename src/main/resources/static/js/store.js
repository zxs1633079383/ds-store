

    //页面加载完毕发送ajax请求
    $(function () {

        //首页默认选中全部文件,所以页面加载完毕之后发送ajax请求,加载数据库中所有数据并展示
        //获取ul对象
        var showUl = $("#admin").find("ul");
        //清空ul原有内容.
        showUl.html("");
        $.get("/Soft-All",function (data) {

            //定义需要拼接的HTML
            var html = "";
            console.log("json数组长度为:" + data.length);

            $.each(data,function (i,n) {
                // console.log("图片路径:" + n.softiconpath );
                // console.log("文件名字:" + n.softname);
                // console.log("文件大小:" + n.softlength);
                // console.log("文件描述信息:" + n.softtext);
                // console.log("****");
                html = "<li class=\"item\">\n" +
                    "\t\t\t\t\t\t\t<div class=\"item-left\">\n" +
                    "\t\t\t\t\t\t\t\t<a href=\"softmore/\"><img src=\"/store/image/"+n.softiconpath+"\"></a>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t<div class=\"item-right\">\n" +
                    "\t\t\t\t\t\t\t\t<a href=\"/Soft-SelectOne/"+n.softid+"\">\n" +
                    "\t\t\t\t\t\t\t\t\t<p class=\"item-into\">"+n.softname+"</p>\n" +
                    "\t\t\t\t\t\t\t\t\t<p class=\"item-other\">价格："+n.softprice+"￥</p>\n" +
                    "\t\t\t\t\t\t\t\t\t<p class=\"item-other\">"+n.softtext+"</p>\n" +
                    "\t\t\t\t\t\t\t\t</a>\n" +
                    "\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t</li>\t";
                //利用$.each动态增加li标签.
                showUl.append(html);

            })

        })

        //全部软件请求
        $("#home-tab").on('click',function () {
            //获取ul对象
            var showUl = $("#admin").find("ul");
            //清空ul原有内容.
            showUl.html("");
            $.get("/Soft-All",function (data) {
                //定义需要拼接的HTML
                var html = "";

                $.each(data,function (i,n) {
                    // console.log("图片路径:" + n.softiconpath );
                    // console.log("文件名字:" + n.softname);
                    // console.log("文件大小:" + n.softlength);
                    // console.log("文件描述信息:" + n.softtext);
                    // console.log("****");
                    html = "<li class=\"item\">\n" +
                        "\t\t\t\t\t\t\t<div class=\"item-left\">\n" +
                        "\t\t\t\t\t\t\t\t<a href=\"softmore/\"><img src=\"/store/image/"+n.softiconpath+"\"></a>\n" +
                        "\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t<div class=\"item-right\">\n" +
                        "\t\t\t\t\t\t\t\t<a href=\"softmore/\">\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-into\">"+n.softname+"</p>\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-other\">价格："+n.softprice+"￥</p>\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-other\">"+n.softtext+"</p>\n" +
                        "\t\t\t\t\t\t\t\t</a>\n" +
                        "\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t</li>\t";
                    //利用$.each动态增加li标签.
                    showUl.append(html);

                })

            })
        })

        //精品软件请求
        $("#profile-tab").on('click',function () {
            //获取ul对象
            var showUl = $("#edit").find("ul");
            //清空ul原有内容.
            showUl.html("");
            $.get("/Soft-Boutique",function (data) {
                //定义需要拼接的HTML
                var html = "";

                $.each(data,function (i,n) {
                    // console.log("图片路径:" + n.softiconpath );
                    // console.log("文件名字:" + n.softname);
                    // console.log("文件大小:" + n.softlength);
                    // console.log("文件描述信息:" + n.softtext);
                    // console.log("****");
                    html = "<li class=\"item\">\n" +
                        "\t\t\t\t\t\t\t<div class=\"item-left\">\n" +
                        "\t\t\t\t\t\t\t\t<a href=\"softmore/\"><img src=\"/store/image/"+n.softiconpath+"\"></a>\n" +
                        "\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t<div class=\"item-right\">\n" +
                        "\t\t\t\t\t\t\t\t<a href=\"softmore/\">\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-into\">"+n.softname+"</p>\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-other\">价格："+n.softprice+"￥</p>\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-other\">"+n.softtext+"</p>\n" +
                        "\t\t\t\t\t\t\t\t</a>\n" +
                        "\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t</li>\t";
                    //利用$.each动态增加li标签.
                    showUl.append(html);

                })

            })
        })

        //销量最高软件请求(热销)
        $("#contact-tab").on('click',function () {
            //获取ul对象
            var showUl = $("#audit").find("ul");
            //清空ul原有内容.
            showUl.html("");
            $.get("/Soft-ManyCount",function (data) {
                //定义需要拼接的HTML
                var html = "";

                $.each(data,function (i,n) {
                    // console.log("图片路径:" + n.softiconpath );
                    // console.log("文件名字:" + n.softname);
                    // console.log("文件大小:" + n.softlength);
                    // console.log("文件描述信息:" + n.softtext);
                    // console.log("****");
                    html = "<li class=\"item\">\n" +
                        "\t\t\t\t\t\t\t<div class=\"item-left\">\n" +
                        "\t\t\t\t\t\t\t\t<a href=\"softmore/\"><img src=\"/store/image/"+n.softiconpath+"\"></a>\n" +
                        "\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t<div class=\"item-right\">\n" +
                        "\t\t\t\t\t\t\t\t<a href=\"softmore/\">\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-into\">"+n.softname+"</p>\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-other\">价格："+n.softprice+"￥</p>\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-other\">"+n.softtext+"</p>\n" +
                        "\t\t\t\t\t\t\t\t</a>\n" +
                        "\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t</li>\t";
                    //利用$.each动态增加li标签.
                    showUl.append(html);

                })

            })
        })

        //免费软件请求
        $("#free-tab").on('click',function () {
            //获取ul对象
            var showUl = $("#contact").find("ul");
            //清空ul原有内容.
            showUl.html("");
            $.get("/Soft-Free",function (data) {
                //定义需要拼接的HTML
                var html = "";

                $.each(data,function (i,n) {
                    // console.log("图片路径:" + n.softiconpath );
                    // console.log("文件名字:" + n.softname);
                    // console.log("文件大小:" + n.softlength);
                    // console.log("文件描述信息:" + n.softtext);
                    // console.log("****");
                    html = "<li class=\"item\">\n" +
                        "\t\t\t\t\t\t\t<div class=\"item-left\">\n" +
                        "\t\t\t\t\t\t\t\t<a href=\"softmore/\"><img src=\"/store/image/"+n.softiconpath+"\"></a>\n" +
                        "\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t\t<div class=\"item-right\">\n" +
                        "\t\t\t\t\t\t\t\t<a href=\"softmore/\">\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-into\">"+n.softname+"</p>\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-other\">价格："+n.softprice+"￥</p>\n" +
                        "\t\t\t\t\t\t\t\t\t<p class=\"item-other\">"+n.softtext+"</p>\n" +
                        "\t\t\t\t\t\t\t\t</a>\n" +
                        "\t\t\t\t\t\t\t</div>\n" +
                        "\t\t\t\t\t\t</li>\t";
                    //利用$.each动态增加li标签.
                    showUl.append(html);

                })

            })
        })

        //普通用户购买按钮超链接单击事件
        $("#a-buy").on('click',function () {
            //获取当前购买软件的价格
            var price = $("#hid-softprice").val();
            alert('您购买的软件价格为:' + price + "\n" + '拥有本站账号,即可免费使用所有产品.');
        });

        //vip购买按钮超链接单击事件
        $("#vip-buy").on('click',function () {
            alert('您已是Vip,可免费使用站点所有产品' + "\n" + '点击下载按钮,即可自动下载');
        })



    })
