<template>
    <div class="forget-wrapper">
        <FindPwdForm class="form" />
        </div>
</template>
<script>
import FindPwdForm from '../components/form/FindPwdForm.vue'
export default {
    components:{
        FindPwdForm
    }
};
</script>
<style scoped>
.forget-wrapper {
    height: 300px;
    display: flex;
    justify-content: center;
    align-content: center;
    margin-top: 200px;
}

</style>