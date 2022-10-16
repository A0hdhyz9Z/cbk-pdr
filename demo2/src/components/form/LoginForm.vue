<template>
    <div class="login">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-position="left" label-width="0px"
            class="login-form">
            <h3 class="title">欢迎使用软件缺陷预测系统</h3>
            <el-form-item prop="username">
                <el-input v-model="loginForm.username" type="text" auto-complete="on" placeholder="请输入用户名"
                    prefix-icon="User">
                </el-input>
            </el-form-item>
            <el-form-item prop="password" style="margin-top:30px">
                <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码"
                    prefix-icon="Lock" @keyup.enter.native="handleLogin">
                </el-input>
            </el-form-item>
            <el-form-item prop="code">
                <el-input v-model="loginForm.code" auto-complete="off" placeholder="验证码" style="width: 63%"
                    prefix-icon="Lock" @keyup.enter.native="handleLogin">
                </el-input>
                <div class="login-code">
                    <PicCode :width="120" :height="40" v-model:Code="Code" />
                </div>
            </el-form-item>
            <el-button :loading="loading" size="medium" type="primary" style="width: 40%;margin-top: -10px;"
                @click.native.prevent="handleLogin">
                <span v-if="!loading">登 录</span>
                <span v-else>登 录 中...</span>
            </el-button>
            <router-link to="/register">
                <el-button class="reg_btn" size="medium">
                    注册</el-button>
            </router-link>
            <!-- <el-form-item style="width: 100%">
                <router-link to="/register">注册</router-link>
            </el-form-item> -->
            <el-form-item style="width: 100%">
                <router-link to="/forgetPwd">忘记密码？</router-link>
            </el-form-item>
            <el-form-item style="width: 100%">
                <!-- <v-verify ></v-verify> -->
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
// 加密
import { login } from '../../utils/api'
import PicCode from "@/components/PicCode/PicCode.vue";
import slideVerify from "../form/slideForm.vue"
export default {
    name: 'Login',
    components: {
        PicCode,
        'v-verify': slideVerify
    },
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
            redirect: undefined,
            Code: ''
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
    methods: {
        handleLogin() {
            this.$refs.loginForm.validate((valid) => {
                const user = {
                    username: this.loginForm.username,
                    password: this.loginForm.password,
                    code: this.loginForm.code,
                    uuid: this.loginForm.uuid
                }
                console.log(valid)
                if (valid) {
                    if (user.code == this.Code) {
                        this.loading = true
                        //登录
                        login(user).then(res => {
                            if (res.code == 200) {
                                this.$message({
                                    showClose: true,
                                    message: res.message,
                                    type: 'success'
                                })
                                this.$router.push('/Order')
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
                            console.log(err.response)
                        })
                        //console.log('登录成功')
                        this.loading = false
                    } else {
                        this.$message({
                            showClose: true,
                            message: '验证码错误',
                            type: 'error'
                        })
                    }
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
    width: 30%;
    display: inline-block;
    height: 60px;
    margin-left: 8px;
    margin-top: 15px;

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

.reg_btn {
    color: #fff;
    background-color: rgb(21, 47, 72);
    border-color: rgb(21, 47, 72);
    width: 40%;
    text-align: center;
    margin-left: 60px;
    margin-top: -10px;
}

.reg_btn:hover,
.reg_btn:focus {
    background: var(--el-button-hover-color);
    border-color: var(--el-button-hover-color);
    color: var(--el-button-font-color);
}
</style>

