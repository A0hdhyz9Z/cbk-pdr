<template>
  <div class="page-register">
    <article class="header">
      <header>
        <el-avatar icon="el-icon-user-solid" shape="circle"></el-avatar>
        <span class="login">
          <em class="bold">已有账号？</em>
          <a href="/login">
            <el-button type="primary" size="small">登录</el-button>
          </a>
        </span>
      </header>
    </article>
    <el-steps :active="active" finish-status="success">
      <el-step title="步骤 1"></el-step>
      <el-step title="步骤 2"></el-step>
    </el-steps>

    <section>
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm"
        autocomplete="off">
        <div v-if="active == 0">
          <el-form-item prop="textarea">
            <el-input :value="ruleForm.textarea" type="textarea" :rows="10" :readonly="true">
            </el-input>
          </el-form-item>
          <el-form-item prop="agreed">
            <el-checkbox v-model="ruleForm.agreed">同意注册协议</el-checkbox>
          </el-form-item>
        </div>
        <div v-if="active == 1">
          <el-form-item label="用户名" prop="name">
            <el-input v-model="ruleForm.name" />
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="ruleForm.email" />
            <!-- <el-button :loading="codeLoading" :disabled="isDisable" size="mini" round @click="sendMsg">发送验证码</el-button> -->
            <span class="status">{{ statusMsg }}</span>
          </el-form-item>
          <!-- <el-form-item label="验证码" prop="code">
            <el-input v-model="ruleForm.code" maxlength="6" />
          </el-form-item> -->
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="ruleForm.phone" maxlength="11" />
          </el-form-item>
          <el-form-item label="密码" prop="pwd">
            <el-input v-model="ruleForm.pwd" type="password" />
          </el-form-item>
          <el-form-item label="确认密码" prop="cpwd">
            <el-input v-model="ruleForm.cpwd" type="password" />
          </el-form-item>
        </div>
      </el-form>
    </section>
    <div class="footer">
      <el-button v-if="active > 0" type="info" icon="el-icon-arrow-left" @click="prev">上一步</el-button>
      <el-button v-if="active < step - 1" type="primary" icon="el-icon-arrow-right" @click="next">下一步</el-button>
      <el-button v-if="active == step - 1" type="primary" @click="register">同意以下协议并注册</el-button>
      <div class="error">{{ error }}</div>
    </div>
  </div>
</template>

<script>
import { getEmailCode, register } from '../utils/api'
export default {
  data() {
    return {
      step: 2,
      active: 0,
      statusMsg: '',
      error: '',
      isDisable: false,
      codeLoading: false,
      ruleForm: {
        textarea: '请仔细阅读以下协议',
        agreed: false,
        name: '',
        // code: '',
        pwd: '',
        cpwd: '',
        email: ''
      },
      rules: {
        agreed: [{
          validator: (rule, value, callback) => {
            if (value !== true) {
              callback(new Error('请确认同意注册协议'))
            } else {
              callback()
            }
          },
          trigger: 'blur'
        }],
        name: [{
          required: true,
          type: 'string',
          message: '请输入用户名',
          trigger: 'blur'
        }],
        // code: [{
        //   required: true,
        //   type: 'string',
        //   message: '请输入验证码',
        //   trigger: 'blur'
        // }],
        email: [{
          required: true,
          type: 'email',
          message: '请输入邮箱',
          trigger: 'blur'
        }],
        phone: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          {
            validator: function (rule, value, callback) {
              if (/^1[34578]\d{9}$/.test(value) === false) {
                callback(new Error('请输入正确的手机号'))
              } else {
                callback()
              }
            }, trigger: 'blur'
          }
        ],
        pwd: [{
          required: true,
          message: '创建密码',
          trigger: 'blur'
        }, { pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/, message: '密码必须同时包含数字与字母,且长度为 8-20位' }],
        cpwd: [{
          required: true,
          message: '确认密码',
          trigger: 'blur'
        }, {
          validator: (rule, value, callback) => {
            if (value === '') {
              callback(new Error('请再次输入密码'))
            } else if (value !== this.ruleForm.pwd) {
              callback(new Error('两次输入密码不一致'))
            } else {
              callback()
            }
          },
          trigger: 'blur'
        }]
      }
    }
  },
  layout: 'blank',
  methods: {
    sendMsg: function () {
      const self = this
      let namePass
      let emailPass
      let timerid
      console.log(timerid)
      if (timerid) {
        return false
      }
      this.$refs['ruleForm'].validateField('name', (valid) => {
        namePass = valid
      })
      self.statusMsg = ''
      if (namePass) {
        return false
      }
      this.$refs['ruleForm'].validateField('email', (valid) => {
        emailPass = valid
      })
      // // 向后台API验证码发送
      // if (!namePass && !emailPass) {
      //   self.codeLoading = true
      //   self.statusMsg = '验证码发送中...'
      //   getEmailCode(self.ruleForm.email).then(res => {
      //     this.$message({
      //       showClose: true,
      //       message: '发送成功，验证码有效期5分钟',
      //       type: 'success'
      //     })
      //     let count = 60
      //     self.ruleForm.code = ''
      //     self.codeLoading = false
      //     self.isDisable = true
      //     self.statusMsg = `验证码已发送,${count--}秒后重新发送`
      //     timerid = window.setInterval(function () {
      //       self.statusMsg = `验证码已发送,${count--}秒后重新发送`
      //       if (count <= 0) {
      //         console.log('clear' + timerid)
      //         window.clearInterval(timerid)
      //         self.isDisable = false
      //         self.statusMsg = ''
      //       }
      //     }, 1000)
      //   }).catch(err => {
      //     this.isDisable = false
      //     this.statusMsg = ''
      //     this.codeLoading = false
      //     console.log(err.response.data.message)
      //   })
      // }
    },

    next: function () {
      if (this.active === 0) {
        this.$refs['ruleForm'].validateField('agreed', (valid) => {
          if (valid) {
            this.active++
          }
        })
      }
    },
    prev: function () {
      this.$refs['ruleForm'].clearValidate()
      if (--this.active < 0) this.active = 0
    },

    // 用户注册
    register: function () {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          console.log('register_valid')

          const user = {
            username: this.ruleForm.name,
            nickName: this.ruleForm.name,
            // code: this.ruleForm.code,
            password: this.ruleForm.pwd,
            email: this.ruleForm.email,
            phone: this.ruleForm.phone,
            enabled: true
          }

          register(user).then(res => {
            this.$message({
              showClose: true,
              message: '注册成功',
              type: 'success'
            })
            setTimeout(() => {
              this.$router.push('/')
            }, 2000)
          }).catch(err => {
            console.log(err.response.data.message)
          })
        }
      })
    }
  }
}
</script>

<style  rel="stylesheet/scss" lang="scss">
.page-register {
  .header {
    //border-bottom: 2px solid rgb(235, 232, 232);
    min-width: 980px;
    color: #666;

    header {
      margin: 0 auto;
      padding: 10px 0;
      width: 980px;

      .login {
        float: right;
      }

      .bold {
        font-style: normal;
      }
    }
  }

  .register {
    color: #1890ff;
  }

  a {
    color: #1890ff;
    text-decoration: none;
    background-color: transparent;
    outline: none;
    cursor: pointer;
    transition: color 0.3s;
  }

  >section {
    margin: 0 auto 30px;
    padding-top: 30px;
    width: 980px;
    min-height: 300px;
    padding-right: 550px;
    box-sizing: border-box;

    .status {
      font-size: 12px;
      margin-left: 20px;
      color: #e6a23c;
    }

    .error {
      color: red;
    }
  }

  .footer {
    text-align: center;
  }
}
</style>


