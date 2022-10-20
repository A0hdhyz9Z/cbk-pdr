<template >
    <div class="selectTitle">
        <span style="margin: auto">提 交 预 测 任 务</span>
    </div>
    <div class="orderSelect">
        <el-form-item label="任务名称" class="label">
            <el-input v-model="orderForm.orderName" style="width:300px" placeholder="请输入任务名称" />
        </el-form-item>

        <el-form-item label="任务描述" class="label">
            <el-input v-model="orderForm.orderDescription" style="width:300px;" type="textarea" clearable
                :autosize="{ minRows: 10, maxRows: 20}" maxlength="1000" placeholder="请输入任务描述" />
        </el-form-item>

        <el-form-item label="选择数据集" class="label">
            <el-radio-group v-model="radio" @change="agreeChange">
                <el-radio-button label="0">JDT</el-radio-button>
                <el-radio-button label="1">PDE</el-radio-button>
                <el-radio-button label="2">Lucene</el-radio-button>
                <el-radio-button label="3">自定义</el-radio-button>
            </el-radio-group>
        </el-form-item>

        <el-form-item v-if=upload_status label="上传数据集" class="label">
            <el-upload :disabled="elUploadDisabled" class="upload-demo" ref="upload" :auto-upload="false"
                :file-list="uploadFiles" :on-change="loadJsonFromFile" accept=".csv">
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button slot="tip" style="margin-left: 10px;" size="small" type="success" @click="submitUpload">
                    上传到服务器
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
            <!-- <el-button :loading="loading" size="large" type="success" icon="Check" round
                style="width: 100% ;margin-left: 150px;margin-right: 150px;font-size: 20px;"
                @click.prevent="handleOrder">
                <span v-if="!loading">提 交 任 务</span>
                <span v-else>任 务 提 交 中...</span>
            </el-button> -->
            <button class="submit_btn123" @click.prevent="handleOrder">
                <div class="svg-wrapper-1">
                    <div class="svg-wrapper">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
                            <path fill="none" d="M0 0h24v24H0z"></path>
                            <path fill="currentColor"
                                d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z">
                            </path>
                        </svg>
                    </div>
                </div>
                <span>提交任务</span>
            </button>
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
            elUploadDisabled: false,
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
                orderName: "",
                orderDescription: "",
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
            this.elUploadDisabled = true
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

<style type="text/css" lang="scss" scoped>
.orderSelect {
    margin: 0px auto;

    background-color: rgba(255, 255, 255, 0.45);
    backdrop-filter: blur(11px);
    -webkit-backdrop-filter: blur(11px);
    border: 1px solid rgba(255, 255, 255, 0.18);
    box-shadow: rgba(142, 142, 142, 0.19) 0px 6px 15px 0px;
    -webkit-box-shadow: rgba(142, 142, 142, 0.19) 0px 6px 15px 0px;
    border-radius: 12px;
    -webkit-border-radius: 12px;
    color: rgb(255, 255, 255);
}

.label {
    font-size: 18px;
    margin-left: 60px;
    margin-top: 10px;
    font-weight: bold;
}

:deep(.el-input__wrapper) {
    background-color: rgba(255, 255, 255, 0.247);
}

:deep(.el-textarea__inner) {
    background-color: rgba(255, 255, 255, 0.247);
}

:deep(.el-radio-button__inner) {
    background-color: rgba(255, 255, 255, 0.247);
}



/* 预测任务 */
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

.submit_btn123 {
    margin-left: 60px;
    font-family: inherit;
    font-size: 20px;
    background: royalblue;
    color: white;
    padding: 0.7em 1em;
    padding-left: 0.9em;
    display: flex;
    align-items: center;
    border: none;
    border-radius: 16px;
    overflow: hidden;
    transition: all 0.2s;
}

.submit_btn123 span {
    display: block;
    margin-left: 0.3em;
    transition: all 0.3s ease-in-out;
}

.submit_btn123 svg {
    display: block;
    transform-origin: center center;
    transition: transform 0.3s ease-in-out;
}

.submit_btn123:hover .svg-wrapper {
    animation: fly-1 0.6s ease-in-out infinite alternate;
}

.submit_btn123:hover svg {
    transform: translateX(2.1em) rotate(45deg) scale(1.1);
}

.submit_btn123:hover span {
    transform: translateX(6em);
}

.submit_btn123:active {
    transform: scale(0.75);
}

@keyframes fly-1 {
    from {
        transform: translateY(0.25em);
    }

    to {
        transform: translateY(-0.25em);
    }
}
</style>