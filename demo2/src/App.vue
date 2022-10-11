<script setup>
</script>

<template>
    <div>
        <h1>Hello App</h1>
    <p>
        <router-link to="/">Go To Home</router-link>
        <router-link to="/login">Go To Login</router-link>
        <router-link to="/register">Go To Register</router-link>
        <router-link to="/applayout">Go To AppLayout</router-link>
    </p>
    <router-view></router-view>
    </div>
</template>

<style>
    @import './style/common.scss';
</style>