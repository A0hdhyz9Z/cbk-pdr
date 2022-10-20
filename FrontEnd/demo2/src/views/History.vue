<template>
  <div class="selectTitle">
    <span style="margin: auto">历 史 任 务</span>
  </div>
  <div class="tbc">
    <el-table :data="tableData" height="62vh" stripe="false" style="width: 100%;margin-bottom: 20px;" row-key="id"
      border default-expand-all :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column prop="id" label="ID" v-if="false"></el-table-column>
      <el-table-column type="index" label="序号" :index="indexMethod" width="50"> </el-table-column>
      <el-table-column prop="orderTime" label="任务提交时间" sortable width="140"></el-table-column>
      <el-table-column prop="orderName" label="任务名称" sortable width="120"></el-table-column>
      <el-table-column prop="orderDescription" label="任务描述" width=""></el-table-column>
      <el-table-column prop="orderDataSet" label="模型训练所用数据集" sortable width="180"></el-table-column>
      <el-table-column prop="orderStatus" label="任务状态" sortable width="120"></el-table-column>
      <el-table-column prop="orderAlg" label="算法" sortable width="180"></el-table-column>
      <el-table-column label="操作" width="200" fixed="right">
        <template #default="scope">
          <el-button class="per_btn" size="small" @click="handlePredict(scope.row)">预测</el-button>
          <el-button class="show_btn" size="small" @click="handleShow(scope.row)">展示</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="预测" v-model="dialogFormVisible">
      <el-form :model="predictForm">
        <el-form-item label="任务编号" :label-width="formLabelWidth">
          <p>{{predictForm.id}}</p>
        </el-form-item>
        <el-form-item label="选择算法" :label-width="formLabelWidth">
          <el-select v-model="predictForm.alg" placeholder="请选择算法">
            <el-option v-for="item in selectAlg" :key="item.id" :value="item.id" :label="item.label">
            </el-option>
            <!-- <el-option label="决策树" value="100"></el-option>
            <el-option label="KNN" value="10"></el-option>
            <el-option label="逻辑回归" value="1"></el-option> -->
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
      <p>任务编号：{{showId}}</p>
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
      currentRowAlg: '',
      selectAlg: [],//预测功能算法选择框选项
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
    handlePredict(row) {
      this.predictForm.id = row.id
      this.currentRowAlg = row.orderAlg
      //预测功能算法选择框选项的限制
      this.selectAlg = []
      if (row.orderAlg.indexOf("决策树") != -1) {
        this.selectAlg.push({ id: 3, label: "决策树" })
      }
      if (row.orderAlg.indexOf("KNN") != -1) {
        this.selectAlg.push({ id: 2, label: "KNN" })
      }
      if (row.orderAlg.indexOf("逻辑回归") != -1) {
        this.selectAlg.push({ id: 1, label: "逻辑回归" })
      }
      this.predictForm.alg = '' //清空之前的选择
      this.uploadFiles = '' //清空之前的选择
      this.dialogFormVisible = true
    },
    handleShow(row) {
      //console.log(id)
      this.$router.push('/Show/' + row.id + '/' + row.orderName + '/' + row.orderDescription + '/' + row.orderDataSet + '/' + row.orderAlg)
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
              orderStatus: '',
              orderAlg: '',
            }
            //orderDataSet项解析
            if (obj[key].orderData == 0) {
              data.orderDataSet = "JDT"
            }
            if (obj[key].orderData == 1) {
              data.orderDataSet = "PDE"
            }
            if (obj[key].orderData == 2) {
              data.orderDataSet = "Lucene"
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
            //orderStatus项解析
            if (obj[key].orderStatus == 0) {
              data.orderStatus = "预测中"
            }
            if (obj[key].orderStatus == 1) {
              data.orderStatus = "已完成"
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
        var msg
        if (res.obj == 1) {
          msg = "预测结果为Clean"
        } else {
          msg = "预测结果为Buggy"
        }
        if (res.code == 200) {  //上传成功
          this.$confirm(msg, '预测完成').then(() => {
            done()
          }).catch(() => {
          })

          //隐藏弹窗
          dialogFormVisible = false
        } else {
          this.$confirm(msg, '预测完成').then(() => {
            done()
          }).catch(() => {
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
  
<style lang="scss" scoped>
.selectTitle {
  color: #1b3366;
  display: flex;
  width: 100%;
  height: 50px;
  font-size: 35px;
  text-align: center;
  /* 控制元素水平居中， 居左居右分别为left,right */
  margin-top: -15px;
  margin-bottom: 15px;
  font-weight: bold;
}

.tbc {
  padding: 20px;
  background-color: rgba(255, 255, 255, 0.25);
  backdrop-filter: blur(6px);
  -webkit-backdrop-filter: blur(6px);
  border: 1px solid rgba(255, 255, 255, 0.18);
  box-shadow: rgba(142, 142, 142, 0.19) 0px 6px 15px 0px;
  -webkit-box-shadow: rgba(142, 142, 142, 0.19) 0px 6px 15px 0px;
  border-radius: 12px;
  -webkit-border-radius: 12px;
  color: rgba(255, 255, 255, 0.75);
}

.per_btn {
 font-size: 10px;
 letter-spacing: 2px;
 text-transform: uppercase;
 display: inline-block;
 text-align: center;
 font-weight: bold;
 padding: 0em 2em;
 border: 3px solid #2f90b9;
 border-radius: 2px;
 position: relative;
 box-shadow: 0 2px 10px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.1);
 color: #2f90b9;
 text-decoration: none;
 transition: 0.3s ease all;
 z-index: 1;
}

.per_btn:before {
 transition: 0.5s all ease;
 position: absolute;
 top: 0;
 left: 50%;
 right: 50%;
 bottom: 0;
 opacity: 0;
 content: '';
 background-color: #2f90b9;
 z-index: -1;
}

.per_btn:hover, .per_btn:focus {
 color: white;
}

.per_btn:hover:before, .per_btn:focus:before {
 transition: 0.5s all ease;
 left: 0;
 right: 0;
 opacity: 1;
}

.per_btn:active {
 transform: scale(0.9);
}

.show_btn {
 font-size: 10px;
 letter-spacing: 2px;
 text-transform: uppercase;
 display: inline-block;
 text-align: center;
 font-weight: bold;
 padding: 0em 2em;
 border: 3px solid #f07c82;
 border-radius: 2px;
 position: relative;
 box-shadow: 0 2px 10px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.1);
 color: #f07c82;
 text-decoration: none;
 transition: 0.3s ease all;
 z-index: 1;
}

.show_btn:before {
 transition: 0.5s all ease;
 position: absolute;
 top: 0;
 left: 50%;
 right: 50%;
 bottom: 0;
 opacity: 0;
 content: '';
 background-color: #f07c82;
 z-index: -1;
}

.show_btn:hover, .show_btn:focus {
 color: white;
}

.show_btn:hover:before, .show_btn:focus:before {
 transition: 0.5s all ease;
 left: 0;
 right: 0;
 opacity: 1;
}

.show_btn:active {
 transform: scale(0.9);
}

</style>