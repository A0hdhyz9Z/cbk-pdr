<template>
  <div>
    <el-table :data="tableData" height="600" stripe style="width: 100%;margin-bottom: 20px;" row-key="id" border
      default-expand-all :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column type="index" label="序号" :index="indexMethod" width="50"> </el-table-column>
      <el-table-column prop="orderTime" label="订单提交时间" sortable width="140"></el-table-column>
      <el-table-column prop="orderName" label="订单名称" sortable width="120"></el-table-column>
      <el-table-column prop="orderDescription" label="订单描述" width="180"></el-table-column>
      <el-table-column prop="orderData" label="数据集" sortable width="100"></el-table-column>
      <el-table-column prop="orderstatus" label="订单状态" sortable width="180"></el-table-column>
      <el-table-column prop="orderAlg" label="算法" sortable width="180"></el-table-column>
      <el-table-column label="操作" width="180" fixed="right">
        <template #default>
          <!-- <el-button size="mini" @click="handlePredict(scope.$index, scope.row)">预测</el-button> 
          <el-button size="mini" @click="handleShow(scope.$index, scope.row)">展示</el-button>-->
          <el-button size="mini" @click="dialogFormVisible = true">预测</el-button>
          <!-- <el-button size="mini" @click="dialogChartsVisible  = true">展示</el-button> -->
          <el-button size="mini" @click="handleShow()">展示</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="预测" v-model="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="选择算法" :label-width="formLabelWidth">
          <el-select v-model="form.alg" placeholder="请选择算法">
            <el-option label="算法一" value="100"></el-option>
            <el-option label="算法二" value="10"></el-option>
            <el-option label="算法三" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上传CSV文件" :label-width="formLabelWidth">

        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- <el-dialog title="结果图表展示" v-model="dialogChartsVisible">
      <template>
        <div id="a" style="height:80px;width:100px" ref="b"></div>
      </template>
    </el-dialog> -->
  </div>
</template>


  
<script>
// import * as echarts from '../../node_modules/echarts';

// import { onMounted,toRefs, ref,reactive } from 'vue';
// import { defineComponent, getCurrentInstance, ref, onMounted } from 'vue';


// // 基于准备好的dom，初始化echarts实例
// //var myChart = echarts.init(document.getElementById('main'));
// var myChart = echarts.init(this.$refs.b);
// //var myChart = this.$echarts.init(this.$refs.b);
// // 绘制图表
// myChart.setOption({
//   title: {
//     text: 'ECharts 入门示例'
//   },
//   tooltip: {},
//   xAxis: {
//     data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
//   },
//   yAxis: {},
//   series: [
//     {
//       name: '销量',
//       type: 'bar',
//       data: [5, 20, 36, 10, 10, 20]
//     }
//   ]
// });

export default{
  data() {
    return {

      dialogFormVisible: false,
      dialogChartsVisible: false,
      formLabelWidth: '120px',
      form: {
        alg: '',
      },
      tableData: [
        {
          id: 1,
          index: 1,
          orderData: 'ASDF',
          orderAlg: '逻辑回归',

        },
        {
          id: 2,
          index: 2,
          orderData: 'QWER',
          orderAlg: '贝叶斯',

        },
        {
          id: 3,
          index: 3,
          orderData: 'BNM',
          orderAlg: '逻辑回归,KNN',

        },
        {
          id: 4,
          index: 4,
          orderData: 'ASDF',
          orderAlg: '贝叶斯',

        },
      ],
    }
  },

  methods: {

    indexMethod(index) {
      return index + 1
    },
    handlePredict(index, row) {
      console.log(index, row)
    },
    handleShow(index, row) {
      console.log(index, row)
    },
    handleShow(){
      this.$router.push('/Show')
    }
  },
}
</script>
  