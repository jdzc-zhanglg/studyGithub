<html>
<head>
    <meta charset="gbk">
    <!-- ���� ECharts �ļ� -->
    <script src="echarts.js"></script>
</head>
</html>
<body>
    <!-- Ϊ ECharts ׼��һ���߱���С����ߣ��� DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
     <div id="main2" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
        // ����׼���õ�dom����ʼ��echartsʵ��
        var myChart = echarts.init(document.getElementById('main'));
		 var myChart2 = echarts.init(document.getElementById('main2'));
        // ָ��ͼ��������������
        var option = {
            title: {
                text: '�ҵ�echart����'
            },
            tooltip: {},
            legend: {
                data:['����']
            },
            xAxis: {
                data: ["����","��ë��","ѩ����","����","�߸�Ь","����"]
            },
            yAxis: {},
            series: [{
                name: '����',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // ʹ�ø�ָ�����������������ʾͼ��
        
    var    option2 = {
    backgroundColor: '#2c343c',
    visualMap: {
        show: false,
        min: 80,
        max: 600,
        inRange: {
            colorLightness: [0, 1]
        }
    },
    series : [
        {
            name: '������Դ',
            type: 'pie',
            radius: '55%',
            data:[
                {value:235, name:'��Ƶ���'},
                {value:274, name:'���˹��'},
                {value:310, name:'�ʼ�Ӫ��'},
                {value:335, name:'ֱ�ӷ���'},
                {value:400, name:'��������'}
            ],
            roseType: 'angle',
            label: {
                normal: {
                    textStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            itemStyle: {
                normal: {
                    color: '#c23531',
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
        myChart.setOption(option);
        myChart2.setOption(option2);
    </script>
</body>