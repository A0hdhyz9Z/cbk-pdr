<script setup lang="ts">
import { reactive, ref } from "vue";
import type { FormInstance, FormRules } from "element-plus";
import { useRouter } from "vue-router";
import axios from "axios";

const router = useRouter();
// const store = useAuthStore();

const formRef = ref<FormInstance>();
// 表单数据
const form = reactive({
	email: "",
	password: "",
	validator: "",
});

const rules = reactive<FormRules>({
	email: [
		{ required: true, message: "请输入邮箱", trigger: "blur" },
		{ type: 'email', message: '请输入正确邮箱格式', trigger: ['blur', 'change'], },
	],
	password: [{ required: true, message: "请输入新密码", trigger: "blur" }],
	validator: [{ required: true, message: "请输入验证码", trigger: "blur" }],
});

const getEmailValidator = () => {
	if (form.email !== "") {
		axios
			.post('/api/user/former/send/', {
				to_email: form.email
			})
			.then(function (res) {
				console.log(res);
				alert(res.data.msg);
			})
			.catch(function (err) {
				console.log(err);
				alert('服务器错误，请稍后尝试');
			})
	}
	else {
		alert('请填写邮箱');
	}
}

const submitForm = async (fromEl: FormInstance | undefined) => {
	if (!fromEl) return;
	axios
		.post('/api/user/former/reset/', {
			email: form.email,
			password: form.password,
			validator: form.validator,
		})
		.then(function (res) {
			console.log(res);
			if (res.data.code == 1) {
				alert('成功修改，请重新登录');
				router.push('/login');
			}
			else {
				alert(res.data.msg);
			}
		})
		.catch(function (err) {
			console.log(err);
			alert('服务器错误，请稍后尝试');
		})
};

const goBack = () => {
	router.go(-1);
}
</script>

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
			<el-form-item label="邮箱验证码" prop="validator">
				<div class="inline-form-item">
					<el-input v-model="form.validator" placeholder="请输入验证码" prefix-icon="Lock"></el-input>
					<el-button @click="getEmailValidator" class="inline-form-item-suffix inline-button">发送验证码
					</el-button>
				</div>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="submitForm(formRef)">修改密码</el-button>
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
