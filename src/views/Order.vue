<template >
    <div class="selectTitle">
        <span style="margin: auto">预 测 订 单</span>
    </div>
    <div class="orderSelect">
        <el-form-item label="订单名称" class="label">
            <el-input v-model="orderForm.orderName" style="width:300px" placeholder="请输入订单名称" />
        </el-form-item>

        <el-form-item label="订单描述" class="label">
            <el-input v-model="orderForm.orderDescription" style="width:300px;" type="textarea" clearable
                :autosize="{ minRows: 10, maxRows: 20}" maxlength="1000" placeholder="请输入订单描述" />
        </el-form-item>

        <el-form-item label="选择数据集" class="label">
            <el-radio-group v-model="radio" @change="agreeChange">
                <el-radio-button label="0">数据集0</el-radio-button>
                <el-radio-button label="1">数据集1</el-radio-button>
                <el-radio-button label="2">数据集2</el-radio-button>
                <el-radio-button label="3">自定义</el-radio-button>
            </el-radio-group>
        </el-form-item>

        <el-form-item v-if=upload_status label="上传数据集" class="label">
            <el-upload class="upload-demo" ref="upload" :auto-upload="false" :file-list="uploadFiles"
                :on-change="loadJsonFromFile" accept=".csv">
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器
                </el-button>
                <div slot="tip" class="el-upload__tip">只能上传.csv文件，且不超过500kb</div>
            </el-upload>
        </el-form-item>

        <el-form-item label="选择算法" class="label">
            <el-select v-model="orderForm.orderAlg" class="filter-item" placeholder="选择算法" multiple style="width:300px">
                <el-option v-for="item in algorithmOptions" :key="item.key" :label="item.label" :value="item.key" />
            </el-select>
        </el-form-item>

        <el-form-item class="submit_btn">
            <el-button :loading="loading" size="large" type="success" icon="Check" round
                style="width: 100% ;margin-left: 150px;margin-right: 150px;font-size: 20px;"
                @click.prevent="handleOrder">
                <span v-if="!loading">提 交 订 单</span>
                <span v-else>订 单 提 交 中...</span>
            </el-button>
        </el-form-item>
    </div>
</template>

<script>
import { upload } from '../utils/api'
import { order } from '../utils/api'
export default {
    data() {
        return {
            radio: "0",
            upload_status: false,
            loading: false,
            fileLimit: 1,
            uploadFiles: [],//上传后的文件列表
            fileType: ["csv"],
            fileSize: 2,
            headers: { "Content-Type": "multipart/form-data; boundary=<calculated when request is sent>" },
            algorithmSelect: "",
            algorithmOptions: [
                { label: "决策树", key: 100 },
                { label: "KNN", key: 10 },
                { label: "逻辑回归", key: 1 },
            ],
            orderForm: {
                orderName: "Default",
                orderDescription: "Default",
                orderDataset: 1,
                orderAlg: [],
            },
        };
    },
    methods: {
        agreeChange: function (val) {
            this.upload_status = (val === '3') ? true : false;
        },
        //上传文件onChange钩子函数
        loadJsonFromFile(file, fileList) {
            this.uploadFiles = fileList
        },
        submitUpload() {
            console.log("submit")
            this.$message('文件上传中........')
            let file = this.uploadFiles[0].raw;//这里获取上传的文件对象
            console.log(file)
            //上传文件的需要formdata类型;所以要转
            let formData = new FormData()
            formData.append('file', file);
            //上传
            upload(formData).then(res => {
                if (res.code == 200) {  //上传成功
                    this.$message({
                        showClose: true,
                        message: res.message,
                        type: 'success'
                    })
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
        handleOrder() {
            var s = 0;
            for (var i = this.orderForm.orderAlg.length - 1; i >= 0; i--) {
                s += this.orderForm.orderAlg[i];
            }
            const data = {
                orderName: this.orderForm.orderName,
                orderDescription: this.orderForm.orderDescription,
                orderDataset: this.radio,
                orderAlg: s
            }
            //提交表单
            order(data).then(res => {
                if (res.code == 200) {  //上传成功
                    this.$message({
                        showClose: true,
                        message: res.message,
                        type: 'success'
                    })
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
        }
    }
}
</script>

<style type="text/css">
.orderSelect {
    margin: 0px auto;
}
.label {
    font-size: 20px;
    margin-left: 60px;
    margin-top: 10px;
}
.submit_btn {
    width: 100%;
}
/* 预测订单 */
.selectTitle {
    color: #1b3366;
    display: flex;
    width: 100%;
    height: 50px;
    font-size: 35px;
    text-align: center;
    /* 控制元素水平居中， 居左居右分别为left,right */
    margin-top: 10px;
}
</style>