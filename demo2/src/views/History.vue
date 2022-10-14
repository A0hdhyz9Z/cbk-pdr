<template>
    <div>
      <el-table
        :data="tableData"
        style="width: 100%;margin-bottom: 20px;"
        row-key="id"
        border
        default-expand-all
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      >
        <el-table-column prop="index" label="序号" sortable width="80"> </el-table-column>
        <el-table-column prop="dataset" label="数据集" sortable width="180">
        </el-table-column>
        <el-table-column prop="algorithm" label="算法" sortable width="180">
        </el-table-column>
        <el-table-column prop="evaluation" label="评级指标"> </el-table-column>
        <el-table-column prop="graph" label="展示图表"> </el-table-column>
        <el-table-column label="操作">
            <template #default="scope">
                <el-button size="mini" @click="handlePredict(scope.$index, scope.row)">预测</el-button>
                <el-button size="mini" @click="handleShow(scope.$index, scope.row)">展示</el-button>
                <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
        </el-table-column>
      </el-table>
  
      <!-- <el-table
        :data="tableData1"
        style="width: 100%"
        row-key="id"
        border
        lazy
        :load="load"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      >
        <el-table-column prop="date" label="日期" width="180"> </el-table-column>
        <el-table-column prop="name" label="姓名" width="180"> </el-table-column>
        <el-table-column prop="address" label="地址"> </el-table-column>
        
      </el-table> -->
    </div>
  </template>
  
  <script>
    export default {
      data() {
        return {
          tableData: [
            {
              id: 1,
              index: 1,
              dataset: 'ASDF',
              algorithm: '逻辑回归',
              evaluation: 'auc,recall',
              graph: '1',
            },
            {
              id: 2,
              index: 2,
              dataset: 'QWER',
              algorithm: '贝叶斯',
              evaluation: 'auc',
              graph:'2',
            },
            {
              id: 3,
              index: 3,
              dataset: 'BNM',
              algorithm: '逻辑回归',
              evaluation: 'rocAdress',
              graph: '3',
              children: [
                {
                  id: 31,
                  index: 31,
                  dataset: 'BNM',
                  algorithm: '贝叶斯',
                  evaluation: 'rocAdress',
                  graph: '31',
                },
                {
                  id: 32,
                  index: 32,
                  dataset: 'BNM',
                  algorithm: 'KNN',
                  evaluation: 'rocAdress',
                  graph: '32',
                },
              ],
            },
            {
              id: 4,
              index: 4,
              dataset: 'ASDF',
              algorithm: '贝叶斯',
              evaluation: 'f1Score',
              graph: '4',
            },
          ],
        //   tableData1: [
        //     {
        //       id: 1,
        //       date: '2016-05-02',
        //       name: '王小虎',
        //       address: '上海市普陀区金沙江路 1518 弄',
        //     },
        //     {
        //       id: 2,
        //       date: '2016-05-04',
        //       name: '王小虎',
        //       address: '上海市普陀区金沙江路 1517 弄',
        //     },
        //     {
        //       id: 3,
        //       date: '2016-05-01',
        //       name: '王小虎',
        //       address: '上海市普陀区金沙江路 1519 弄',
        //       hasChildren: true,
        //     },
        //     {
        //       id: 4,
        //       date: '2016-05-03',
        //       name: '王小虎',
        //       address: '上海市普陀区金沙江路 1516 弄',
        //     },
        //   ],
        }
      },
      methods: {
        // load(tree, treeNode, resolve) {
        //   setTimeout(() => {
        //     resolve([
        //       {
        //         id: 31,
        //         date: '2016-05-01',
        //         name: '王小虎',
        //         address: '上海市普陀区金沙江路 1519 弄',
        //       },
        //       {
        //         id: 32,
        //         date: '2016-05-01',
        //         name: '王小虎',
        //         address: '上海市普陀区金沙江路 1519 弄',
        //       },
        //     ])
        //   }, 1000)
        // },

        handlePredict(index, row) {
            console.log(index, row)
        },
        handleShow(index, row) {
            console.log(index, row)
        },
        handleDelete(index, row) {
            console.log(index, row)
        },
      },
    }
  </script>
  