
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
    components: {
        LoginForm, ImageSwiper
    }
};
</script>

<style scoped>
.login-wrapper {
    /* flex布局，高度要设置100vh，才能实现竖直居中 */
    height: 100vh;
    display: flex;
    justify-content: center;
    align-content: center;
}

.swiper {
    /* margin-top: 40px;
    margin-left: 10px; */
    margin: auto 0px;
    width: 440px;
    height: 300px;
}
</style>
