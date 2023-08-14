$(function () {
    $.get("/Axax-orderLine", function (data) {
        var name = data.name;
        var price = data.price;
        var chartDom = document.getElementById('chart_widget_13');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
            title:{
                text:'已成交订单分析'
            },
            xAxis: {
                type: 'category',
                data: name
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    data: price,
                    type: 'line'
                }
            ]
        };

        option && myChart.setOption(option);
    })
})