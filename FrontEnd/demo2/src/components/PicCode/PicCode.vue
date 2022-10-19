<template>
  <div
    class="yzcode"
    @click="OnRefresh()"
    :style="{ width: width + 'px', height: height + 'px' }"
  >
    <p ref="picyzm"></p>
  </div>
</template>

<script lang='ts'>
import GVerify from "./piccode.js";
import { defineComponent, reactive, ref, onMounted } from "vue";

export default defineComponent({
  props: {
    width: {
      type: Number,
      default: 100,
    },
    height: {
      type: Number,
      default: 30,
    },
  },
  setup(props, context) {
    const picyzm = ref<HTMLElement | null>(null);
    let verifyCode: any = null;
    onMounted(() => {
      picyzm.value && picyzm.value.focus();
      console.log(picyzm, "内容");
      //初始化验证码
      verifyCode = new GVerify({
        type: "blend",
        height: props.height,
        con: picyzm.value,
      });

      //获取验证码内容
      const code = verifyCode.GetCode();
      console.log(code);
      context.emit("update:Code", code);
    });

    //点击图片主动刷新验证码
    const OnRefresh = () => {
      verifyCode.refresh();
      const code = verifyCode.GetCode();
      console.log(code);
      context.emit("update:Code", code);
    };

    return { picyzm, OnRefresh };
  },
});
</script>

<style>
#picyzm {
  width: 100px;
  height: 40px;
  display: inline-block;
  margin: 0 30px;
}
#verifyCodeDemo {
  width: 100%;
  display: flex;
  margin-top: 200px;
  justify-content: center;
}
#btn {
  margin: 30px auto;
  background-color: blue;
  color: #fff;
  border-radius: 5px;
  border: 0;
  width: 100px;
  height: 40px;
}
</style>