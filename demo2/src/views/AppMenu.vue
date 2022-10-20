<template>
  <div class="logo">
    <img :src="`src/assets/images/${url}`" style="width: 100%;height:100%;object-fit: cover;">
  </div>
  <el-menu default-active="2" :collapse="props.isCollapse" :collapse-transition="false" @open="handleOpen"
    @close="handleClose" background-color="#ebf1f5" text-color="#606266" active-text-color="#2F74FF" class="menu"
    router>
    <!-- <el-sub-menu index="1">
        <template #title>
          <el-icon><DocumentAdd /></el-icon>
          <span>提交任务</span>
        </template>
        <el-menu-item-group>
          <template #title><span>Group One</span></template>
          <el-menu-item index="1-1">item one</el-menu-item>
          <el-menu-item index="1-2">item two</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="Group Two">
          <el-menu-item index="1-3">item three</el-menu-item>
        </el-menu-item-group>
        <el-sub-menu index="1-4">
          <template #title><span>item four</span></template>
          <el-menu-item index="1-4-1">item one</el-menu-item>
        </el-sub-menu>
      </el-sub-menu> -->
    <el-menu-item index="/Order">
      <el-icon>
        <DocumentAdd />
      </el-icon>
      <template #title>提交预测任务</template>
    </el-menu-item>

    <el-menu-item index="/History">
      <el-icon>
        <Files />
      </el-icon>
      <template #title>查看历史任务</template>
    </el-menu-item>
    <el-menu-item index="/">
      <el-icon>
        <User />
      </el-icon>
      <template #title>退出登录</template>
    </el-menu-item>
  </el-menu>
  
</template>
  
<script lang="ts" setup>
import { ref, watch } from 'vue'

var url = ref("logo3.png")
const props = defineProps(['isCollapse'])
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
// watch(props.isCollapse, (newVal, oldVal) => {
//   console.log({ newVal, oldVal })
// })


</script>
  
<style scoped>
.logo {
  height: 80px;
  background: #e1eaf4;
}

.menu {
  min-height: calc(100vh - 80px);
}
</style>