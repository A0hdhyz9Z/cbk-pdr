<template >
    <div class="selectTitle">
        <span style="margin: auto">预 测 订 单</span>
    </div>
    <div class="orderSelect">
    <el-form-item label="上传文件" style="font-size: 20px;margin-left: 62px;margin-top: 10px;">
        <el-upload
            :action="action"
            :file-list="modeList"
            :http-request="modeUpload"
        >
        <el-button size="medium" type="primary" style="margin-left:6px">选择文件</el-button>
        </el-upload>
    </el-form-item>

    <el-form-item label="算法" style="font-size: 20px;margin-left: 101px;margin-top: 40px;">
        <el-select
            v-model="nextProjectForm.algorithm"
            class="filter-item"
            placeholder="选择算法"
            multiple
            style="width:300px"
        >
        <el-option
            v-for="item in algorithmOptions"
            :key="item.key"
            :label="item.label"
            :value="item.key"
            
        />
        </el-select>
    </el-form-item>

    <el-form-item label="评价指标" style="font-size: 20px;margin-left: 62px;margin-top: 40px;">
        <el-select
            v-model="nextProjectForm.evaluation"
            class="filter-item"
            placeholder="选择评价指标"
            multiple
            style="width: 300px;"
        >
        <el-option
            v-for="item in evaluationOptions"
            :key="item.key"
            :label="item.label"
            :value="item.key"
        />
        </el-select>
    </el-form-item>

    <el-form-item label="展示图表" style="font-size: 20px;margin-left: 62px;margin-top: 40px;">
        <el-select
            v-model="nextProjectForm.graph"
            class="filter-item"
            placeholder="选择展示图表"
            multiple
            style="width: 300px;"
        >
        <el-option
            v-for="item in graphOptions"
            :key="item.key"
            :label="item.label"
            :value="item.key"
        />
        </el-select>
    </el-form-item>
    <el-form-item style="width: 100%">
        <el-button :loading="loading" size="large" type="primary" style="width: 100% ;margin-left: 150px;margin-right: 150px;font-size: 20px;"
            @click.prevent="handleLogin">
            <span v-if="!loading">提  交  订  单</span> 
            <span v-else>订 单 提 交 中...</span>
        </el-button>
    </el-form-item>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                action: 'https://jsonplaceholder.typicode.com/posts/',
                mode: {},
                modeList: [],
                algorithmSelect: "",
                evaluationSelect: "",
                graphSelect:"",
                algorithmOptions: [
                    { label: "KNN", key: "kinderGarten" },
                    { label: "1111", key: "firstGrade" },
                    { label: "2222", key: "twoGrade" },
                    { label: "333", key: "threeGrade" },
                    { label: "444", key: "fourGrade" },
                    { label: "5555", key: "fiveGrade" },
                    { label: "6", key: "sixGrade" },
                ],
                evaluationOptions: [
                    { label: "auc", key: "1" },
                    { label: "rocAdress", key: "2" },
                    { label: "f1Score", key: "3" },
                    { label: "precission", key: "4" },
                    { label: "recall", key: "5" },
                ],
                graphOptions: [
                    { label: "柱状图", key: "1" },
                    { label: "折线图", key: "2" },
                ],
                nextProjectForm: {
                    algorithm: [],
                    evaluation: [],
                    graph:[],
                },
            };
        },
    
        methods: {
          modeUpload: function(item) {
            // console.log(item.file);
            this.mode = item.file
          },
    
        }
    }
    
</script>
    
<style type="text/css">
    .orderSelect{
        margin:0px auto;
    }
    /* 预测订单 */
    .selectTitle{
        color: #1b3366;
        display: flex;
        width: 100%;
        height: 50px;
        font-size: 35px;
        text-align: center;  /* 控制元素水平居中， 居左居右分别为left,right */
        margin-top: 10px;
    }

</style>