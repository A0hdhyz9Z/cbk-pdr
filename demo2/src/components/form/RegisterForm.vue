<template>
	<el-card shadow="always">
		<template #header>
			<div class="card-header">
				<span>注册</span>
				<el-button @click="goBack" size="small">
					<el-icon>
						<Back />
					</el-icon>返回
				</el-button>
			</div>
		</template>
		<el-form ref="formRef" :model="form" :rules="rules" label-position="left" label-width="130px">
			<el-form-item label="用户名" prop="name">
				<el-input v-model="form.name" placeholder="请输入用户名" :prefix-icon="User"></el-input>
			</el-form-item>

			<el-form-item label="密码" prop="password">
				<el-input v-model="form.password" placeholder="请输入密码" :prefix-icon="Lock"></el-input>
			</el-form-item>

			<el-form-item label="职责" prop="is_staff">
				<el-radio-group v-model="form.is_staff">
					<el-radio label="0">普通用户</el-radio>
					<el-radio label="1">管理员</el-radio>
				</el-radio-group>
			</el-form-item>

			<el-form-item label="图片验证码" prop="img_code">
				<div class="inline-form-item">
					<el-input v-model="form.img_code" placeholder="请输入验证码" :prefix-icon="Lock"></el-input>
					<img :src="img_url" @click="getImg" class="inline-form-item-suffix" />
				</div>
			</el-form-item>

			<el-form-item label="邮箱" prop="email">
				<el-input v-model="form.email" placeholder="请输入邮箱" :prefix-icon="User"></el-input>
			</el-form-item>

			<el-form-item label="邮箱验证码" prop="validator">
				<div class="inline-form-item">
					<el-input v-model="form.validator" placeholder="请输入验证码" :prefix-icon="Lock"></el-input>
					<el-button @click="getEmailValidator" class="inline-form-item-suffix inline-button">发送验证码
					</el-button>
				</div>
			</el-form-item>

			<el-form-item>
				<el-button type="primary" @click="submitForm(formRef)">注册</el-button>
			</el-form-item>
		</el-form>
	</el-card>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from "vue";
import { FormInstance, FormRules } from "element-plus";
import { Lock, User } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { throttle } from "lodash";

const router = useRouter();

const formRef = ref<FormInstance>();
// 表单数据
const form = reactive({
	name: "", // 姓名
	password: "", // 密码
	email: "", // 邮箱
	img_code: "", // 用户输入的图片验证码
	validator: "", // 用户输入的邮箱验证码
	is_staff: "0", // （0，普通用户；1，管理员）
});

const rules = reactive<FormRules>({
	name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
	password: [{ required: true, message: "请输入密码", trigger: "blur" }],
	email: [{ required: true, message: "请输入邮箱", trigger: "blur" },
	{ type: 'email', message: '请输入正确邮箱格式', trigger: ['blur', 'change'], },],
	role: [{ required: true, message: "请选择职责", trigger: "blur" }],
	img_code: [{ required: true, message: "请输入验证码", trigger: "blur" }],
	validator: [{ required: true, message: "请输入验证码", trigger: "blur" }],
});

const img_url = ref("")
const arrayBufferToBase64 = (buffer: Iterable<number>) => {
	var binary = ''
	var bytes = new Uint8Array(buffer)
	var len = bytes.byteLength
	for (var i = 0; i < len; i++) {
		binary += String.fromCharCode(bytes[i])
	}
	return window.btoa(binary)
}
const getImg = throttle(function () {
	axios
		.get('/api/user/former/generate/', {
			responseType: 'arraybuffer'
		})
		.then(function (res) {
			img_url.value = 'data:image/png;base64,' + arrayBufferToBase64(res.data)
		})
		.catch(function (err) {
			console.log(err)
		})
}, 2000)
onMounted(() => {
	getImg()
})

const getEmailValidator = throttle(function () {
	if (form.email !== "") {
		axios
			.post('/api/user/former/send/', {
				to_email: form.email
			})
			.then(function (res) {
				console.log(res)
			})
			.catch(function (err) {
				console.log(err)
			})
	}
}, 5000);

const submitForm = throttle(async (fromEl: FormInstance | undefined) => {
	if (!fromEl) return;
	console.log(form)
	// send username and pwd
	axios
		.post('/api/user/former/register/', form,)
		.then(function (res) {
			console.log(res)
			if (res.data.code == 1) {
				// redirect
				alert('注册成功，请登录');
				router.push('/');
			}
			else {
				alert(res.data.msg);
			}
		})
		.catch(function (err) {
			console.log(err)
			alert('服务器错误，请稍后尝试');
		})
}, 2000);

const goBack = () => {
	router.go(-1);
}

</script>

<style scoped>
.ep-card {
	height: 600px;
	width: 800px;
	padding: 0 80px;
}

.card-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.inline-form-item {
	display: flex;
}

.inline-form-item-suffix {
	margin-left: 20px;
}

.inline-button {
	width: 120px;
}
</style>
