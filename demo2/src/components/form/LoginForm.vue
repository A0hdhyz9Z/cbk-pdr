<template>
    <div class="login">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-position="left" label-width="0px"
            class="login-form">
            <h5 class="title" id="title1">欢迎使用</h5>
            <h5 class="title" id="title2">软件缺陷预测系统</h5>
            <el-form-item prop="username">
                <el-input class="inputPlace" v-model="loginForm.username" type="text" auto-complete="on"
                    placeholder="请输入用户名" prefix-icon="User">
                </el-input>
            </el-form-item>
            <el-form-item prop="password" style="margin-top:30px">
                <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码"
                    prefix-icon="Lock" @keyup.enter.native="handleLogin">
                </el-input>
            </el-form-item>
            <el-form-item prop="code" style="margin-top:30px">
                <el-input class="inputPlace" v-model="loginForm.code" auto-complete="off" placeholder="验证码" style="width: 63%"
                    prefix-icon="Lock" @keyup.enter.native="handleLogin">
                </el-input>
                <PicCode :width="120" :height="40" v-model:Code="Code" style="margin-top: -30px;margin-left: 3px;" />
            </el-form-item>
            <button class="login_btn" :loading="loading" size="medium" @click.native.prevent="handleLogin">
                <span v-if="!loading">登 录</span>
                <span v-else>登 录 中...</span>
            </button>
            <router-link to="/register">
                <button class="reg_btn" size="medium">
                    注册</button>
            </router-link>
            <el-form-item style="width: 100%">
                <router-link to="/forgetPwd">忘记密码？</router-link>
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

<style rel="stylesheet/scss" lang="scss" scoped>
.login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    background-size: cover;
}

.title {
    display: flex;
    width: 100%;
    height: 25px;
    font-size: 30px;
    text-align: center;
    /* 控制元素水平居中， 居左居右分别为left,right */
    margin-top: 10px;
}

#title1 {
    margin: 0 auto;
    margin-bottom: 40px;
    margin-right: 30px;
    text-align: center;
    color: #2775B6;
    font-size: 40px;

}

#title2 {
    margin: 0 auto;
    margin-bottom: 30px;
    margin-right: 30px;
    text-align: center;
    color: #115395;
}

.login-form {
    border-radius: 6px;
    // background: #ffffff;
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
    // color: #bfbfbf;
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


// 设置el-input_inner
:deep(.el-input__wrapper) {
    background-color: rgba(255, 255, 255, 0.247);
}



.login_btn {
    padding: 1em;
    width: 45%;
    margin-top: 10px;
    font-size: 12px;
    text-align: center;
    text-transform: uppercase;
    letter-spacing: 2.5px;
    font-weight: 500;
    color: #fff;
    background-color: #1890ff;
    border: none;
    border-radius: 45px;
    -webkit-box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
    box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
    -webkit-transition: all 0.3s ease 0s;
    transition: all 0.3s ease 0s;
    cursor: pointer;
    outline: none;
}

.login_btn:hover {
    background-color: #2EE59D;
    -webkit-box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
    box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
    color: #fff;
    -webkit-transform: translateY(-7px);
    -ms-transform: translateY(-7px);
    transform: translateY(-7px);
}

.login_btn:active {
    -webkit-transform: translateY(-1px);
    -ms-transform: translateY(-1px);
    transform: translateY(-1px);
}

.reg_btn {
    padding: 1.3em 3em;
    margin-left: 30px;
    width: 45%;
    margin-top: 10px;
    font-size: 12px;
    text-transform: uppercase;
    letter-spacing: 2.5px;
    font-weight: 500;
    color: #000;
    background-color: #fff;
    border: none;
    border-radius: 45px;
    -webkit-box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
    box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
    -webkit-transition: all 0.3s ease 0s;
    transition: all 0.3s ease 0s;
    cursor: pointer;
    outline: none;
}

.reg_btn:hover {
    background-color: #2EE59D;
    -webkit-box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
    box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
    color: #fff;
    -webkit-transform: translateY(-7px);
    -ms-transform: translateY(-7px);
    transform: translateY(-7px);
}

.reg_btn:active {
    -webkit-transform: translateY(-1px);
    -ms-transform: translateY(-1px);
    transform: translateY(-1px);
}
</style>

