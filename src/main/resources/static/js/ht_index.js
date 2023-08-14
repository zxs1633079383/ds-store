$(function () {
    $.get("/createPvAndUv",function (data) {
        var pvAddr = data.pvAddr;
        var pv_Num = data.pvNum;
        var uv_Num = data.uvNum;

        var chartDom = document.getElementById('chart_widget_13');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
            title: {
                text: 'PV-UV统计',

            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['PV','UV']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: pvAddr
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: 'PV',
                    type: 'line',
                    stack: 'PV',
                    data: pv_Num
                },
                {
                    name: 'UV',
                    type: 'line',
                    stack: 'UV',
                    data: uv_Num
                }
            ]
        };
        option && myChart.setOption(option);
    })
})