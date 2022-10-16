<template>
    <el-card shadow="always">
        <template #header>
            <div class="card-header">
                <span>修改密码</span>
                <el-button @click="goBack" size="small">
                    <el-icon>
                        <Back />
                    </el-icon>返回
                </el-button>
            </div>
        </template>
        <el-form ref="formRef" :model="form" :rules="rules" label-position="right" label-width="95px">
            <el-form-item label="用户名" prop="username">
                <div class="inline-form-item">
                    <el-input v-model="form.username" placeholder="请输入用户名" prefix-icon="User"></el-input>
                </div>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <div class="inline-form-item">
                    <el-input v-model="form.email" placeholder="请输入邮箱" prefix-icon="Message"></el-input>
                </div>
            </el-form-item>
            <el-form-item label="新密码" prop="password">
                <div class="inline-form-item">
                    <el-input v-model="form.password" type="password" placeholder="请输入密码" prefix-icon="Lock"
                        show-password></el-input>
                </div>
            </el-form-item>
            <el-form-item label="验证码" prop="validator">
                <div class="inline-form-item">
                    <el-input v-model="form.validator" placeholder="请输入验证码" prefix-icon="Lock"></el-input>
                    <el-button @click="getEmailValidator" class="inline-form-item-suffix inline-button">发送验证码
                    </el-button>
                </div>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="modifyPwd">修改密码</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<style scoped>
.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.inline-form-item {
    display: flex;
}

.inline-form-item-suffix {
    margin-left: 10px;
}

.inline-button {
    width: 90px;
}
</style>

<script>
import { modifyPwd, sendCode } from '../../utils/api'
import { checkCode } from '../../utils/api'


export default {
    name: 'Login',
    components: {
    },
    data() {
        return {
            form: {
                username: '',
                password: '',
                email: '',
                validator: ''
            },
            rules: {
                username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
                email: [
                    { required: true, message: "请输入邮箱", trigger: "blur" },
                    { type: 'email', message: '请输入正确邮箱格式', trigger: ['blur', 'change'], },
                ],
                password: [{ required: true, message: "请输入新密码", trigger: "blur" }],
                validator: [{ required: true, message: "请输入验证码", trigger: "blur" }],
            },
        }
    },
    watch: {
    },
    methods: {
        getEmailValidator() {
            const data = {
                username: this.form.username,
                email: this.form.email,
            }
            //调用请求api
            sendCode(data).then(res => {
                console.log(res)
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
            }).catch(err => {
                console.log(err)
            })
        },

        modifyPwd() {
            const data = {
                username: this.form.username,
                email: this.form.email,
                password: this.form.password,
                code: this.form.validator
            }
            //调用验证api
            checkCode(data).then(res => {
                console.log(res)
                //验证码验证成功
                if (res.code == 200) {
                    this.$message({
                        showClose: true,
                        message: res.message,
                        type: 'success'
                    })
                    //调用修改密码api
                    modifyPwd(data).then(res => {
                        console.log(res)
                        //修改成功
                        if (res.code == 200) {
                            this.$message({
                                showClose: true,
                                message: res.message,
                                type: 'success'
                            })
                            setTimeout(() => {
                                this.$router.push('/login')
                            }, 2000)
                        }
                        //修改失败
                        else {
                            this.$message({
                                showClose: true,
                                message: res.message,
                                type: 'error'
                            })
                        }
                    }).catch(err => {
                        console.log(err)
                    })
                    //验证码验证失败
                } else {
                    this.$message({
                        showClose: true,
                        message: res.message,
                        type: 'error'
                    })
                }
            }).catch(err => {
                console.log(err)
            })
        },

        goBack() {
            this.$router.go(-1);
        }
    }
}
</script>