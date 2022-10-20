<template>
	<div class="page-register">
		<article class="header">
			<header>
				<el-avatar icon="User-filled" shape="circle"></el-avatar>
				<span class="login">
					<em class="bold">已有账号？</em>
					<router-link to="/login">
						<el-button type="primary" size="small">登录</el-button>
					</router-link>
				</span>
			</header>
		</article>
		<el-steps :active="active" finish-status="success" style="width: 500px;margin:0 auto" align-center>
			<el-step title="用户协议"></el-step>
			<el-step title="注册信息"></el-step>
		</el-steps>

		<section>
			<el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm"
				autocomplete="off">
				<div v-if="active == 0">
					<el-form-item prop="textarea">
						<!-- <el-input :value="ruleForm.textarea" type="textarea" :rows="10" :readonly="true">
						</el-input> -->
						<div id="container">
							<h1 style="text-align:center">用户协议</h1>
							<strong>
								隐私政策
							</strong>
							<p>
								cbk-pdr开发了该免费的应用程序软件缺陷预测系统。该服务由cbk-pdr提供，旨在安装并使用。
							</p>
							<p>
								我们对您使用软件缺陷预测系统的隐私政策做出规定，并解释了我们如何收集，保护和披露您因使用我们的服务而产生的信息。
							</p>
							<p>
								如果您选择使用我们提供的服务，则表示您同意收集和使用与此政策相关的信息。我们收集的个人信息用于提供和改进服务。除非本隐私政策中另有说明，否则我们不会与任何人一起使用或分享您的信息。
							</p>
							<p>
								除非本隐私政策另有规定，否则本隐私政策中使用的术语与我们的条款和条件具有相同的含义，可在软件缺陷预测系统中访问。
							</p>
							<p><strong>
									信息收集与使用
								</strong></p>
							<p>
								为了获得更好的体验，在使用我们的服务时，我们可能会要求您向我们提供某些个人身份信息。 我们请求的信息将由我们保留并按照本隐私政策的规定使用。
							</p>
							<!---->
							<p><strong>
									记录数据
								</strong></p>
							<p>
								当您使用我们的服务时，如果应用程序出现错误，我们会通过第三方数据统计在您的手机上收集数据和信息。该日志数据可能包含以下信息，例如：您的设备Internet协议（
								IP）地址，设备名称，操作系统版本，使用我们的服务时应用的配置，您使用该服务的时间和日期以及其他统计信息。
							</p>
							<p><strong>
									Cookies
								</strong></p>
							<p>
								Cookies是具有少量数据的文件，通常用作匿名唯一标识符。 这些信息将从您访问的网站发送到浏览器，并存储在设备的内存中。
							</p>
							<p>
								本应用未明确使用这些Cookies。但是，该应用程序可能使用第三方代码和使用带有Cookies的库来收集信息并改善其服务。
								您可以选择接受还是拒绝这些Cookies，并知道何时将Cookies发送到您的设备。
								如果您选择拒绝我们的Cookies，则可能无法使用本服务的部分功能。
							</p>
							<p><strong>
									服务供应商
								</strong></p>
							<p>
								我们可能由于以下原因雇用第三方公司和个人：
							</p>
							<ul>
								<li>
									促进我们的服务质量；
								</li>
								<li>
									代表我们提供服务；
								</li>
								<li>
									提供与服务相关的服务；
								</li>
								<li>
									帮助我们分析如何使用我们的服务。
								</li>
							</ul>
							<p>
								我们想通知此服务的用户，这些第三方有权访问您的个人信息。原因是代表我们执行分配给他们的任务。但是，他们有义务不出于任何其他目的披露或使用该信息。
							</p>
							<p><strong>
									安全
								</strong></p>
							<p>
								我们很重视您对我们提供您的个人信息的信任，因此我们正在努力使用商业上可接受的方法来保护它。但是请记住，没有一种通过互联网传输的方法，或者说电子存储的方法是100%安全可靠的，我们不能保证它的绝对安全性。
							</p>
							<p><strong>
									到其他网站的链接
								</strong></p>
							<p>
								此服务可能包含指向其他网站的链接。如果你点击第三方链接，你将被引导到该网站。注意，这些外部站点不是由我们操作的。因此，我们强烈建议您查看这些网站的隐私策略。我们对任何第三方网站或服务的内容、隐私政策或做法没有控制权，也不承担任何责任。
							</p>
							<p><strong>
									儿童隐私
								</strong></p>
							<p>
								这些服务不面向13岁以下的青少年。我们不会有意收集13岁以下儿童的个人身份信息。如果我们发现13岁以下的儿童向我们提供了个人信息，我们会立即从我们的服务器中删除这些信息。
								如果您是父母或监护人，并且知道您的孩子向我们提供了个人信息，请与我联系，以便我们能够采取必要的措施。
							</p>
							<p><strong>
									本隐私政策的变更
								</strong></p>
							<p>
								我们可能会不时更新我们的隐私政策。因此，建议您定期查看此页以了解更改。如有任何更改，我们会在本页公布新的隐私政策。
							</p>
							<p>
								该政策自2022-10-13起生效。
							</p>
							<p><strong>
									联系我们
								</strong></p>
							<p>
								如果您对我们的隐私政策有任何疑问或建议，请随时通过20301077@bjtu.edu.cn与我们联系。
							</p>
						</div>
					</el-form-item>
					<el-form-item prop="agreed">
						<el-checkbox v-model="ruleForm.agreed">同意用户协议</el-checkbox>
					</el-form-item>
				</div>
				<div v-if="active == 1" class="input1">
					<el-form-item label="用户名" prop="name">
						<el-input v-model="ruleForm.name" placeholder="请输入用户名" prefix-icon="User" />
					</el-form-item>
					<el-form-item label="邮箱" prop="email">
						<el-input v-model="ruleForm.email" placeholder="请输入邮箱" prefix-icon="message-box" />
					</el-form-item>
					<el-form-item label="密码" prop="pwd">
						<el-input v-model="ruleForm.pwd" type="password" placeholder="请输入密码" prefix-icon="lock" />
					</el-form-item>
					<el-form-item label="确认密码" prop="cpwd">
						<el-input v-model="ruleForm.cpwd" type="password" placeholder="请再次输入密码" prefix-icon="lock" />
					</el-form-item>
				</div>
			</el-form>
		</section>
		<div class="footer">
			<el-button v-if="active > 0" type="info" icon="Arrow-left" @click="prev">上一步</el-button>
			<el-button v-if="active < step - 1" type="primary" icon="Arrow-right" @click="next">下一步</el-button>
			<el-button v-if="active == step - 1" type="primary" @click="register">注册</el-button>
			<div class="error">{{ error }}</div>
		</div>
	</div>
</template>
  
<script>
import { register } from '../../utils/api'
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
				textarea: '请仔细阅读以下协议《用户服务协议和隐私政策》（以下简称“协议”）及其条款，系您下载、安装及使用“软件缺陷预测系统”（简称：cbk-pdr）软件（以下简称“本软件”）所订立的、描述您与本软件之间权利义务的协议。在注册前务必认真阅读本协议的内容、充分理解各条款内容，如有异议，您可选择不进入本软件。一旦您确认本用户注册协议后，本协议即在您和本软件之间产生法律效力，意味着您完全同意并接受协议的全部条款。请您审慎阅读并选择接受或不接受协议（未成年人应在法定监护人陪同下阅读）。',
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
							callback(new Error('请确认同意用户协议'))
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
				// phone: [
				//   { required: true, message: '请输入手机号码', trigger: 'blur' },
				//   {
				//     validator: function (rule, value, callback) {
				//       if (/^1[34578]\d{9}$/.test(value) === false) {
				//         callback(new Error('请输入正确的手机号'))
				//       } else {
				//         callback()
				//       }
				//     }, trigger: 'blur'
				//   }
				// ],
				pwd: [{
					required: true,
					message: '创建密码',
					trigger: 'blur'
				}, { pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,20}$/, message: '密码必须包含数字与字母，长度为 8-20位' }],
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
						//phone: this.ruleForm.phone,
						enabled: true
					}

					register(user).then(res => {
						if (res.code == 200) {
							this.$message({
								showClose: true,
								message: res.message,
								type: 'success'
							})
							setTimeout(() => {
								this.$router.push('/')
							}, 2000)
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
			})
		}
	}
}
</script>
  
<style  rel="stylesheet/scss" lang="scss" scoped>
.page-register {
	.header {
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
		// padding-right: 550px;
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

	.input1 {
		width: 400px;
		text-align: center;
		margin: 0 auto;
	}

	#container {
		margin: 0 auto;
		box-shadow: 0 0 8px #D0D0D0;
		-webkit-box-shadow: 0 0 8px #D0D0D0;
		-moz-box-shadow: 0 0 8px #D0D0D0;
		padding: 20px;
		
		
	}
}
</style>
  
  
  