<template>
    <div class="title">
        <span style="margin: auto">结 果 图 表 展 示</span>
    </div>

    <el-card class="box-card" style="margin-bottom: 50px;width: 1200px;">
        <el-form-item :label="'订单编号：'+(route.params.id)" style="width:550px;height: 30px;display: inline-block;">
        </el-form-item>
        <el-form-item :label="'订单名称：'+(route.params.name)" style="width:550px;height: 30px;display: inline-block;">
        </el-form-item>
        <el-form-item :label="'订单描述：'+(route.params.description)"
            style="width:550px;height: 30px;display: inline-block;">
        </el-form-item>
        <el-form-item :label="'订单数据集：'+(route.params.dataSet)" style="width:550px;height: 30px;display: inline-block;">
        </el-form-item>
    </el-card>


    <div>
        <div id="acc" style="width: 550px; height: 280px;float:left;"></div>
    </div>

    <div>
        <div id="f1Score" style="width: 550px; height: 280px;float:right;"></div>
    </div>

    <el-form-item :label="'根据不同算法10次评价指标ACC数据的平均值，算法效果最好的是：'+(accWinner)" style="width:550px;float:left;">
    </el-form-item>

    <el-form-item :label="'根据不同算法10次评价指标f1Score数据的平均值，算法效果最好的是：'+(f1ScoreWinner)" style="width:550px;float:right;">
    </el-form-item>


    <div>
        <div id="precision" style="width: 550px; height: 280px;float:left;margin-top: 30px;"></div>
    </div>

    <div>
        <div id="recall" style="width: 550px; height: 280px;float:right;margin-top: 30px;"></div>
    </div>

    <el-form-item :label="'根据不同算法10次评价指标precision数据的平均值，算法效果最好的是：'+(precisionWinner)" style="width:550px;float:left;">
    </el-form-item>

    <el-form-item :label="'根据不同算法10次评价指标recall数据的平均值，算法效果最好的是：'+(recallWinner)" style="width:550px;float:right;">
    </el-form-item>


    <el-card class="box-card" style="margin-bottom: 50px;width: 1000px;margin: auto;font-size: 20px;">
        <el-form-item :label="'综上，根据不同算法四种评价指标的平均值，算法效果最好的是：'+(finalWinner)"
            style="width:700px;height: 30px;margin: auto;">
        </el-form-item>
    </el-card>


</template>


<script setup>
import { selectById } from '../utils/api'
import { avg, winner, final_Winner } from '../utils/math'
import { useRouter, useRoute } from 'vue-router'
import { ElMessageBox } from 'element-plus';
import { ElMessage } from 'element-plus';
//引入echarts
import * as echarts from "echarts";
//注意这里引入了onMounted这个钩子，等会有用
import { onMounted, ref } from "vue";


const router = useRouter()
const route = useRoute()
var dataObj = ref()

//获取
async function initData() {
    const res = await selectById(route.params.id)
    if (res.code == 200) {  //获取成功
        ElMessage.success({
            showClose: true,
            message: res.message,
        })
        dataObj.value = res.obj
        console.log(dataObj.value)
    } else {
        ElMessage.error({
            showClose: true,
            message: res.message,
        })
    }
}


var accOption
var f1ScoreOption
var precisionOption
var recallOption

var accWinner = ref()
var f1ScoreWinner = ref()
var precisionWinner = ref()
var recallWinner = ref()
var finalWinner = ref()

function initOption() {
    var algNum = dataObj.value.length

    //处理算法标签
    var algLabel = []
    for (var key in dataObj.value) {
        if (dataObj.value[key].id % 100 == 1) {
            algLabel.push("决策树")
        }
        if (dataObj.value[key].id % 100 == 10) {
            algLabel.push("KNN")
        }
        if (dataObj.value[key].id % 100 == 0) {
            algLabel.push("逻辑回归")
        }
    }
    console.log(algLabel)

    //acc数据
    var accSeriesData = []
    var accMeans = []
    for (var key in dataObj.value) {
        var _name
        var _data = []
        //判断当前算法
        if (dataObj.value[key].id % 100 == 1) {
            _name = "决策树"
        }
        if (dataObj.value[key].id % 100 == 10) {
            _name = "KNN"
        }
        if (dataObj.value[key].id % 100 == 0) {
            _name = "逻辑回归"
        }
        //当前算法的acc
        _data.push(dataObj.value[key].accuracy1)
        _data.push(dataObj.value[key].accuracy2)
        _data.push(dataObj.value[key].accuracy3)
        _data.push(dataObj.value[key].accuracy4)
        _data.push(dataObj.value[key].accuracy5)
        _data.push(dataObj.value[key].accuracy6)
        _data.push(dataObj.value[key].accuracy7)
        _data.push(dataObj.value[key].accuracy8)
        _data.push(dataObj.value[key].accuracy9)
        _data.push(dataObj.value[key].accuracy10)

        var obj = {
            name: _name,
            data: _data,
            type: 'line',
        }
        //当前算法的acc均值
        accMeans.push({ name: _name, means: avg(_data) })

        accSeriesData.push(obj)
    }
    //acc图表配置
    accOption = {
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
            data: algLabel
        },
        xAxis: {
            name: "样本序号",
            data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
        },
        yAxis: {},
        series: accSeriesData
    }

    //f1Score数据
    var f1ScoreSeriesData = []
    var f1ScoreMeans = []
    for (var key in dataObj.value) {
        var _name
        var _data = []
        //判断当前算法
        if (dataObj.value[key].id % 100 == 1) {
            _name = "决策树"
        }
        if (dataObj.value[key].id % 100 == 10) {
            _name = "KNN"
        }
        if (dataObj.value[key].id % 100 == 0) {
            _name = "逻辑回归"
        }
        //当前算法的f1Score
        _data.push(dataObj.value[key].f1Score1)
        _data.push(dataObj.value[key].f1Score2)
        _data.push(dataObj.value[key].f1Score3)
        _data.push(dataObj.value[key].f1Score4)
        _data.push(dataObj.value[key].f1Score5)
        _data.push(dataObj.value[key].f1Score6)
        _data.push(dataObj.value[key].f1Score7)
        _data.push(dataObj.value[key].f1Score8)
        _data.push(dataObj.value[key].f1Score9)
        _data.push(dataObj.value[key].f1Score10)

        var obj = {
            name: _name,
            data: _data,
            type: 'line',
        }
        //当前算法的f1Score均值
        f1ScoreMeans.push({ name: _name, means: avg(_data) })

        f1ScoreSeriesData.push(obj)
    }
    //f1Score图表配置
    f1ScoreOption = {
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
            data: algLabel
        },
        xAxis: {
            name: "样本序号",
            data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
        },
        yAxis: {},
        series: f1ScoreSeriesData
    }

    //precision数据
    var precisionSeriesData = []
    var precisionMeans = []
    for (var key in dataObj.value) {
        var _name
        var _data = []
        if (dataObj.value[key].id % 100 == 1) {
            _name = "决策树"
        }
        if (dataObj.value[key].id % 100 == 10) {
            _name = "KNN"
        }
        if (dataObj.value[key].id % 100 == 0) {
            _name = "逻辑回归"
        }

        _data.push(dataObj.value[key].precission1)
        _data.push(dataObj.value[key].precission2)
        _data.push(dataObj.value[key].precission3)
        _data.push(dataObj.value[key].precission4)
        _data.push(dataObj.value[key].precission5)
        _data.push(dataObj.value[key].precission6)
        _data.push(dataObj.value[key].precission7)
        _data.push(dataObj.value[key].precission8)
        _data.push(dataObj.value[key].precission9)
        _data.push(dataObj.value[key].precission10)

        var obj = {
            name: _name,
            data: _data,
            type: 'line',
        }
        //当前算法的precision均值
        precisionMeans.push({ name: _name, means: avg(_data) })

        precisionSeriesData.push(obj)
    }
    //precision图表配置
    precisionOption = {
        title: {
            text: 'precision'
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
            data: algLabel
        },
        xAxis: {
            name: "样本序号",
            data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
        },
        yAxis: {},
        series: precisionSeriesData
    }

    //recall数据
    var recallSeriesData = []
    var recallMeans = []
    for (var key in dataObj.value) {
        var _name
        var _data = []
        if (dataObj.value[key].id % 100 == 1) {
            _name = "决策树"
        }
        if (dataObj.value[key].id % 100 == 10) {
            _name = "KNN"
        }
        if (dataObj.value[key].id % 100 == 0) {
            _name = "逻辑回归"
        }

        _data.push(dataObj.value[key].recall1)
        _data.push(dataObj.value[key].recall2)
        _data.push(dataObj.value[key].recall3)
        _data.push(dataObj.value[key].recall4)
        _data.push(dataObj.value[key].recall5)
        _data.push(dataObj.value[key].recall6)
        _data.push(dataObj.value[key].recall7)
        _data.push(dataObj.value[key].recall8)
        _data.push(dataObj.value[key].recall9)
        _data.push(dataObj.value[key].recall10)

        var obj = {
            name: _name,
            data: _data,
            type: 'line',
        }
        //当前算法的recall均值
        recallMeans.push({ name: _name, means: avg(_data) })

        recallSeriesData.push(obj)
    }
    //recall图表配置
    recallOption = {
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
            data: algLabel
        },
        xAxis: {
            name: "样本序号",
            data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
        },
        yAxis: {},
        series: recallSeriesData
    }

    //进行均值的比较
    accWinner.value = winner(accMeans)
    f1ScoreWinner.value = winner(f1ScoreMeans)
    precisionWinner.value = winner(precisionMeans)
    recallWinner.value = winner(recallMeans)
    finalWinner.value = final_Winner(algNum, accMeans, f1ScoreMeans, precisionMeans, recallMeans)
}






// echarts标准格式
const echartInit = () => {

    //获取DOM元素并初始化
    var accChart = echarts.init(document.getElementById("acc"));
    // 使用刚指定的配置项和数据显示图表。
    accChart.setOption(accOption);

    //获取DOM元素并初始化
    var f1ScoreChart = echarts.init(document.getElementById("f1Score"));
    // 使用刚指定的配置项和数据显示图表。
    f1ScoreChart.setOption(f1ScoreOption);

    var precisionChart = echarts.init(document.getElementById("precision"));
    // 使用刚指定的配置项和数据显示图表。
    precisionChart.setOption(precisionOption);

    var recallChart = echarts.init(document.getElementById("recall"));
    // 使用刚指定的配置项和数据显示图表。
    recallChart.setOption(recallOption);
};
console.log(route.params.id)
console.log(route.params.name)
console.log(route.params.description)
console.log(route.params.dataSet)
console.log(route.params.alg)

// 这里需要注意，不能在setup内执行getElementById操作，因为setup是在文档加载完毕之前执行，那么
// 取到的一定是null导致init失败，所以需要在onMounted中执行
onMounted(async () => {
    await initData();
    initOption();
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