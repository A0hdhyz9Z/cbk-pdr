<template>
    <div class="login">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-position="left" label-width="0px"
            class="login-form">
            <h3 class="title">欢迎使用</h3>
            <el-form-item prop="username">
                <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="请输入用户名" prefix-icon="User">
                </el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码" prefix-icon="Lock"
                    @keyup.enter.native="handleLogin">
                </el-input>
            </el-form-item>
            <el-form-item prop="code">
                <el-input v-model="loginForm.code" auto-complete="off" placeholder="验证码" style="width: 63%" prefix-icon="Lock"
                    @keyup.enter.native="handleLogin">
                </el-input>
                <div class="login-code">
                    <PicCode :width="200" :height="60" v-model:Code="Code" />
                </div>
            </el-form-item>
            <el-form-item style="width: 100%">
                <el-button :loading="loading" size="medium" type="primary" style="width: 100%"
                    @click.native.prevent="handleLogin">
                    <span v-if="!loading">登 录</span>
                    <span v-else>登 录 中...</span>
                </el-button>
            </el-form-item>
            <el-form-item style="width: 100%">
                <router-link to="/register">注册</router-link>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
// 加密
import { login } from '../../utils/api'
import PicCode from "@/components/PicCode/PicCode.vue";
export default {
    name: 'Login',
	components: { PicCode },
    data() {
        return {
            codeUrl: '',
            loginForm: {
                username: '',
                password: '',
                code: '',
                uuid: ''
            },
            loginRules: {
                username: [
                    { required: true, trigger: 'blur', message: '用户名不能为空' }
                ],
                password: [
                    { required: true, trigger: 'blur', message: '密码不能为空' }
                ],
                code: [{ required: true, trigger: 'change', message: '验证码不能为空' }]
            },
            loading: false,
            redirect: undefined
        }
    },
    watch: {
        $route: {
            handler: function (route) {
                this.redirect = route.query && route.query.redirect
            },
            immediate: true
        }
    },
    created() {
        // // 获取验证码
        // this.getCode()

    },
    methods: {
        getCode() {
            // 模拟返回验证码图片
            //this.codeUrl = 'http://www.demodashi.com/ueditor/jsp/upload/image/20170802/1501642847473057707.jpeg'
            //this.loginForm.uuid = '111'

        },
        handleLogin() {
            this.$refs.loginForm.validate((valid) => {
                const user = {
                    username: this.loginForm.username,
                    password: this.loginForm.password,
                    code: this.loginForm.code,
                    uuid: this.loginForm.uuid
                }
                if (valid) {
                    this.loading = true
                    //登录
                    login(user).then(res => {
                        if (res.code == 200) {
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
                        // setTimeout(() => {
                        //     this.$router.push('/')
                        // }, 2000)
                    }).catch(err => {
                        console.log(err.response.data.message)
                    })
                    //console.log('登录成功')
                    this.loading = false
                } else {
                    console.log('error submit!!')
                    this.loading = false
                    return false
                }
            })
        },
    }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-size: cover;
}

.title {
    margin: 0 auto 30px auto;
    text-align: center;
    color: #707070;
}

.login-form {
    border-radius: 6px;
    background: #ffffff;
    width: 385px;
    padding: 25px 25px 5px 25px;

    .el-input {
        height: 38px;

        input {
            height: 38px;
        }
    }

    .input-icon {
        height: 39px;
        width: 14px;
        margin-left: 2px;
    }
}

.login-tip {
    font-size: 13px;
    text-align: center;
    color: #bfbfbf;
}

.login-code {
    width: 33%;
    display: inline-block;
    height: 38px;

    //float: right;
    img {
        cursor: pointer;
        vertical-align: middle;
    }
}

.register {
    float: right;
    color: rgb(24, 144, 255);
}

a {
    color: #1890ff;
    text-decoration: none;
    background-color: transparent;
    outline: none;
    cursor: pointer;
    transition: color 0.3s;
}
</style>

