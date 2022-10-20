
<template>
        <div class="login-wrapper">
            <LoginForm class="form" />
            <ImageSwiper class="swiper" />
        </div>
</template>

<script>
import LoginForm from '../components/form/LoginForm.vue'
import ImageSwiper from '../components/static/ImageSwiper.vue'
export default {
    // 2. 注册导入的组件
	components:{
		LoginForm,ImageSwiper
	}
};
</script>

<style scoped>
.login-wrapper {
    height: 300px;
    display: flex;
    justify-content: center;
    align-content: center;
    margin-top: 200px;
}

.swiper {
    margin-top: 50px;
    margin-left: 50px;
    width: 300px;
    height: 300px;
}
</style>
