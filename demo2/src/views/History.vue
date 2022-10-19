<template>
  <div>
    <el-table :data="tableData" height="600" stripe style="width: 100%;margin-bottom: 20px;" row-key="id" border
      default-expand-all :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column prop="id" label="ID" v-if="false"></el-table-column>
      <el-table-column type="index" label="序号" :index="indexMethod" width="50"> </el-table-column>
      <el-table-column prop="orderTime" label="订单提交时间" sortable width="140"></el-table-column>
      <el-table-column prop="orderName" label="订单名称" sortable width="120"></el-table-column>
      <el-table-column prop="orderDescription" label="订单描述" width=""></el-table-column>
      <el-table-column prop="orderDataSet" label="模型训练所用数据集" sortable width="180"></el-table-column>
      <el-table-column prop="orderStatus" label="订单状态" sortable width="120"></el-table-column>
      <el-table-column prop="orderAlg" label="算法" sortable width="180"></el-table-column>
      <el-table-column label="操作" width="180" fixed="right">
        <template #default="scope">
          <el-button size="small" @click="handlePredict(scope.row.id)">预测</el-button>
          <el-button size="small" @click="handleShow(scope.row)">展示</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="预测" v-model="dialogFormVisible">
      <el-form :model="predictForm">
        <el-form-item label="订单编号" :label-width="formLabelWidth">
          <p>{{predictForm.id}}</p>
        </el-form-item>
        <el-form-item label="选择算法" :label-width="formLabelWidth">
          <el-select v-model="predictForm.alg" placeholder="请选择算法">
            <el-option label="决策树" value="100"></el-option>
            <el-option label="KNN" value="10"></el-option>
            <el-option label="逻辑回归" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上传CSV文件" :label-width="formLabelWidth">
          <el-upload class="upload-demo" ref="upload" :auto-upload="false" :file-list="uploadFiles"
            :on-change="loadJsonFromFile" accept=".csv">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传.csv文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitPredict">确 定</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog title="结果图表展示" v-model="dialogChartsVisible">
      <p>订单编号：{{showId}}</p>
    </el-dialog>
  </div>
</template>

<script>
import { selectAll } from '../utils/api'
import { predict } from '../utils/api'


export default {

  data() {
    return {
      uploadFiles: [],//上传后的文件列表

      dialogFormVisible: false,
      dialogChartsVisible: false,
      formLabelWidth: '120px',
      predictForm: {
        id: '',
        alg: '',
      },
      showId:'',
      tableData: [
        {
          id: 1,
          orderTime: '',
          orderName: '',
          orderDescription: '',
          orderDataSet: 'ASDF',
          orderStatus: '',
          orderAlg: '逻辑回归',

        },
        {
          id: 2,
          orderDataSet: 'QWER',
          orderAlg: '贝叶斯',

        },
        {
          id: 3,
          orderDataSet: 'BNM',
          orderAlg: '逻辑回归,KNN',

        },
        {
          id: 4,
          orderDataSet: 'ASDF',
          orderAlg: '贝叶斯',

        },
      ],
    }
  },
  methods: {

    //列表序号自增
    indexMethod(index) {
      return index + 1
    },
    //上传文件onChange钩子函数
    loadJsonFromFile(file, fileList) {
      this.uploadFiles = fileList
    },
    //预测按钮点击事件
    handlePredict(id) {
      // console.log(id)
      this.predictForm.id = id
      this.predictForm.alg = '' //清空之前的选择
      this.uploadFiles = '' //清空之前的选择
      this.dialogFormVisible = true
    },
    handleShow(row) {
      //console.log(id)
      //this.showId=id
      this.$router.push('/Show/'+row.id+'/'+row.orderName+'/'+row.orderDescription+'/'+row.orderDataSet+'/'+row.orderAlg)
      //this.dialogChartsVisible = true
    },

    //查询历史记录列表
    selectAllItems() {
      selectAll().then(res => {
        if (res.code == 200) {  //上传成功
          this.$message({
            showClose: true,
            message: res.message,
            type: 'success'
          })
          console.log(res)
          var obj = res.obj
          console.log(obj.length)
          var dataArr = new Array
          for (var key in obj) { //遍历数组中的对象
            var data = {
              id: obj[key].orderId,
              orderTime: obj[key].orderTime,
              orderName: obj[key].orderName,
              orderDescription: obj[key].orderDescription,
              orderDataSet: '',
              orderStatus: obj[key].orderStatus,
              orderAlg: '',
            }
            //orderDataSet项解析
            if (obj[key].orderData == 0) {
              data.orderDataSet = "数据集0"
            }
            if (obj[key].orderData == 1) {
              data.orderDataSet = "数据集1"
            }
            if (obj[key].orderData == 2) {
              data.orderDataSet = "数据集2"
            }
            if (obj[key].orderData == 3) {
              data.orderDataSet = "自定义数据集"
            }
            //orderAlg项解析
            if (Math.floor(obj[key].orderAlg / 100) > 0) {
              data.orderAlg += "决策树 "
            }
            if (Math.floor((obj[key].orderAlg % 100) / 10) > 0) {
              data.orderAlg += "KNN "
            }
            if (Math.floor(obj[key].orderAlg % 10) == 1) {
              data.orderAlg += "逻辑回归"
            }
            dataArr.push(data)
          }
          this.tableData = dataArr
        } else {
          this.$message({
            showClose: true,
            message: res.message,
            type: 'error'
          })
        }
      }).catch(err => {
        console.log(err.response)
      })
    },

    //提交预测表单
    submitPredict() {
      console.log("submit")
      this.$message('表单提交中........')
      let file = this.uploadFiles[0].raw;//这里获取上传的文件对象
      console.log(file)
      //上传文件的需要formdata类型;所以要转
      let formData = new FormData()
      formData.append('orderId', this.predictForm.id);
      formData.append('file', file);
      formData.append('alg', this.predictForm.alg);
      //上传
      predict(formData).then(res => {
        if (res.code == 200) {  //上传成功
          this.$message({
            showClose: true,
            message: res.message,
            type: 'success'
          })

          //隐藏弹窗
          dialogFormVisible = false
        } else {
          this.$message({
            showClose: true,
            message: res.message,
            type: 'error'
          })
        }
      }).catch(err => {
        console.log(err.response)
      })
    },
  },
  mounted() {
    this.selectAllItems()
  }


}
</script>
  