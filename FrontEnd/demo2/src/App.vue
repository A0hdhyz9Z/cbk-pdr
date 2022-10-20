<script setup>
</script>

<template>
    <router-view></router-view>

</template>

<style>
body {
    overflow-y: auto;
    overflow-x: hidden;
    margin: 0;
    height: 100%;
    width: 100%;
    /* background-color: aliceblue; */
    background: url("../src/assets/images/svg.svg");
    background: rgb(198, 230, 232);
    background: linear-gradient(90deg, rgba(198, 230, 232, 0.7609418767507002) 0%, rgba(101, 164, 194, 0.6937149859943977) 100%);
}

* {
    box-sizing: border-box;
}

#app {
    height: 100%;
}


</style>