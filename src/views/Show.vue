<template>
    <div class="title">
        <span style="margin: auto">结 果 图 表 展 示</span>
    </div>

    <el-card class="box-card" style="margin-bottom: 50px;width: 1200px;">
        <el-form-item label="订单编号:" style="width:550px;height: 30px;display: inline-block;">
            <!-- <p>{{predictForm.id}}</p> -->
        </el-form-item>
        <el-form-item label="订单名称:" style="width:550px;height: 30px;display: inline-block;">
            <!-- <p>{{predictForm.id}}</p> -->
        </el-form-item>
        <el-form-item label="订单描述:" style="width:550px;height: 30px;display: inline-block;">
            <!-- <p>{{predictForm.id}}</p> -->
        </el-form-item>
        <el-form-item label="订单数据集:" style="width:550px;height: 30px;display: inline-block;">
            <!-- <p>{{predictForm.id}}</p> -->
        </el-form-item>
    </el-card>


    <div>
        <div id="acc" style="width: 550px; height: 280px;float:left;"></div>
    </div>

    <div>
        <div id="f1Score" style="width: 550px; height: 280px;float:right;"></div>
    </div>

    <el-form-item label="根据不同算法10次评价指标ACC数据的平均值，算法效果最好的是：" style="width:550px;float:left;">
        <!-- <p>{{predictForm.id}}</p> -->
    </el-form-item>

    <el-form-item label="根据不同算法10次评价指标f1Score数据的平均值，算法效果最好的是：" style="width:550px;float:right;">
        <!-- <p>{{predictForm.id}}</p> -->
    </el-form-item>


    <div>
        <div id="precission" style="width: 550px; height: 280px;float:left;margin-top: 30px;"></div>
    </div>

    <div>
        <div id="recall" style="width: 550px; height: 280px;float:right;margin-top: 30px;"></div>
    </div>

    <el-form-item label="根据不同算法10次评价指标precission数据的平均值，算法效果最好的是：" style="width:550px;float:left;">
        <!-- <p>{{predictForm.id}}</p> -->
    </el-form-item>

    <el-form-item label="根据不同算法10次评价指标recall数据的平均值，算法效果最好的是：" style="width:550px;float:right;">
        <!-- <p>{{predictForm.id}}</p> -->
    </el-form-item>


    <el-card class="box-card" style="margin-bottom: 50px;width: 1000px;margin: auto;font-size: 20px;">
        <el-form-item label="综上，根据不同算法四种评价指标的平均值，算法效果最好的是：" style="width:700px;height: 30px;margin: auto;">
            <!-- <p>{{predictForm.id}}</p> -->
        </el-form-item>
    </el-card>


</template>


<script setup>
//引入echarts
import * as echarts from "echarts";
//注意这里引入了onMounted这个钩子，等会有用
import { onMounted } from "vue";


// echarts标准格式
const echartInit = () => {
    //获取DOM元素并初始化
    var accChart = echarts.init(document.getElementById("acc"));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ACC'
        },
        tooltip: {
            trigger: 'axis'
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
        legend: {
            data: ['算法一', '算法二', '算法三']
        },
        xAxis: {
            data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
        },
        yAxis: {},
        series: [
            {
                name: '算法一',
                data: [10, 22, 28, 43, 49, 89, 45, 67, 15, 20],
                type: 'line',
                stack: 'x'
            },
            {
                name: '算法二',
                data: [5, 4, 3, 5, 10, 5, 16, 24, 36, 49],
                type: 'line',
                stack: 'x'
            },
            {
                name: '算法三',
                data: [45, 63, 26, 45, 46, 31, 25, 14, 54, 47],
                type: 'line',
                stack: 'x'
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    accChart.setOption(option);

    //获取DOM元素并初始化
    var f1ScoreChart = echarts.init(document.getElementById("f1Score"));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'f1Score'
        },
        tooltip: {
            trigger: 'axis'
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
        legend: {
            data: ['算法一', '算法二', '算法三']
        },
        xAxis: {
            data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
        },
        yAxis: {},
        series: [
            {
                name: '算法一',
                data: [10, 22, 28, 43, 49, 89, 45, 67, 15, 20],
                type: 'line',
                stack: 'x'
            },
            {
                name: '算法二',
                data: [5, 4, 3, 5, 10, 5, 16, 24, 36, 49],
                type: 'line',
                stack: 'x'
            },
            {
                name: '算法三',
                data: [45, 63, 26, 45, 46, 31, 25, 14, 54, 47],
                type: 'line',
                stack: 'x'
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    f1ScoreChart.setOption(option);

    var precissionChart = echarts.init(document.getElementById("precission"));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'precission'
        },
        tooltip: {
            trigger: 'axis'
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
        legend: {
            data: ['算法一', '算法二', '算法三']
        },
        xAxis: {
            data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
        },
        yAxis: {},
        series: [
            {
                name: '算法一',
                data: [10, 22, 28, 43, 49, 89, 45, 67, 15, 20],
                type: 'line',
                stack: 'x'
            },
            {
                name: '算法二',
                data: [5, 4, 3, 5, 10, 5, 16, 24, 36, 49],
                type: 'line',
                stack: 'x'
            },
            {
                name: '算法三',
                data: [45, 63, 26, 45, 46, 31, 25, 14, 54, 47],
                type: 'line',
                stack: 'x'
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    precissionChart.setOption(option);

    var recallChart = echarts.init(document.getElementById("recall"));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'recall'
        },
        tooltip: {
            trigger: 'axis'
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
        legend: {
            data: ['算法一', '算法二', '算法三']
        },
        xAxis: {
            data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
        },
        yAxis: {},
        series: [
            {
                name: '算法一',
                data: [10, 22, 28, 43, 49, 89, 45, 67, 15, 20],
                type: 'line',
                stack: 'x'
            },
            {
                name: '算法二',
                data: [5, 4, 3, 5, 10, 5, 16, 24, 36, 49],
                type: 'line',
                stack: 'x'
            },
            {
                name: '算法三',
                data: [45, 63, 26, 45, 46, 31, 25, 14, 54, 47],
                type: 'line',
                stack: 'x'
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    recallChart.setOption(option);

};

// 这里需要注意，不能在setup内执行getElementById操作，因为setup是在文档加载完毕之前执行，那么
// 取到的一定是null导致init失败，所以需要在onMounted中执行
onMounted(() => {
    echartInit();
});
</script>

<style>
.title {
    color: #1b3366;
    display: flex;
    width: 100%;
    height: 25px;
    font-size: 30px;
    text-align: center;
    /* 控制元素水平居中， 居左居右分别为left,right */
    margin-top: 10px;
}
</style>