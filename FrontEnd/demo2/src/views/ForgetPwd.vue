<template>
    <div class="forget-wrapper">
        <FindPasswordForm class="form" />
        </div>
</template>
<script>
import FindPasswordForm from '../components/form/FindPasswordForm.vue'

export default {
    components:{
        FindPasswordForm
    }
};
</script>
<style scoped>
.forget-wrapper {
    height: 350px;
    display: flex;
    justify-content: center;
    align-content: center;
    margin-top: 200px;
}

</style>