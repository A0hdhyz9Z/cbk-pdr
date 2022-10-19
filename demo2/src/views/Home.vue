<template>
    <div class="wrapper">
        <section class="left" style="background-image: url('https://images.pexels.com/photos/225250/pexels-photo-225250.jpeg?cs=srgb&dl=pexels-markus-spiske-225250.jpg&fm=jpg');
        "></section>
        <section class="right">
            <div class="index_box">
                <div class="index_txt">
                    <img src="src\assets\images\logo.jpg" alt="" />
                    <h1>软件缺陷预测系统</h1>
                    <p>cbk-pdr</p>
                    <div class="zuobiao">
                        <el-icon style="vertical-align: middle;">
                            <map-location />
                        </el-icon> Beijing Jiaotong University
                    </div>
                    <div class="menu-%e8%8f%9c%e5%8d%951-container">
                        <ul id="menu-%e8%8f%9c%e5%8d%951" class="index_nav">
                            <li id="menu-item-1"
                                class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-1">
                                <router-link to="/login">登录</router-link>
                            </li>
                            <li id="menu-item-2"
                                class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2">
                                <router-link to="/register">注册</router-link>
                            </li>
                            <li id="menu-item-3"
                                class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-3">
                                <router-link to="/register">关于我们</router-link>
                            </li>
                            <li id="menu-item-4"
                                class="menu-item menu-item-type-post_type menu-item-object-page menu-item-4">
                                <router-link to="/register">联系方式</router-link>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <footer class="right_foot">
                <div class="right_f_l">
                    <p>
                        <span>©
                            <a href="https://www.bjtu.edu.cn/" target="_blank"> cbk-pdr</a></span>
                    </p>
                </div>
            </footer>
        </section>
        <!--right-->
    </div>
</template>

<style>
.left {
    width: 50%;
}

.right {
    width: 50%;
}

* {
    box-sizing: border-box;
}

html {
    font-family: sans-serif;
    -ms-text-size-adjust: 100%;
    -webkit-text-size-adjust: 100%;
}

body {
    margin: 0;
    font-family: microsoft yahei;
    color: #333;
    font-size: 14px;
}

article,
aside,
details,
figcaption,
figure,
footer,
header,
hgroup,
main,
nav,
section,
summary {
    display: block;
}

audio,
canvas,
progress,
video {
    display: inline-block;
    vertical-align: baseline;
}

audio:not([controls]) {
    display: none;
    height: 0;
}

[hidden],
template {
    display: none;
}

a {
    background: 0 0;
    color: #000;
}

a:active,
a:hover {
    color: #cb3737;
    outline: 0;
    text-decoration: none;
}

a:link {
    text-decoration: none;
}

b,
strong {
    font-weight: 700;
}

p {
    margin: 0 0 10px;
}

h1 {
    font-size: 2em;
    margin: 0.67em 0;
}





textarea:focus,
input:focus {
    box-shadow: none;
    outline: 0;
}

fieldset {
    border: 1px solid silver;
    margin: 0 2px;
    padding: 0.35em 0.625em 0.75em;
}

legend {
    border: 0;
    padding: 0;
}

textarea {
    overflow: auto;
}

optgroup {
    font-weight: 700;
}

table {
    border-collapse: collapse;
    border-spacing: 0;
}

td,
th {
    padding: 0;
}

ul,
ol {
    list-style: none;
    padding: 0;
    margin: 0;
}

.wrapper {
    position: relative;
}

.left {
    position: fixed;
    top: 0;
    left: 0;
    bottom: 0;
    background-size: cover;
    display: block;
    background-position: 50% 0;
    background-color: #000;
}

.right {
    float: right;
}

@media (max-width: 768px) {
    .left {
        display: none;
    }

    .right {
        width: 100%;
    }
}

.right_box {
    padding: 80px 50px;
}

.right_foot {
    background: #f5f5f5;
    padding: 20px 50px;
    font-size: 12px;
    color: #555;
    display: table;
    width: 100%;
}

.right_foot a {
    color: #555;
}

.right_f_l {
    width: 70%;
    float: left;
}

.right_f_l p {
    margin: 0;
    color: #555;
}

.right_f_l p span {
    margin-right: 5px;
}

.right_f_r {
    width: 30%;
    float: right;
    text-align: right;
}

@media (max-width: 768px) {
    .right_f_l {
        width: 100%;
        float: none;
        text-align: center;
    }

    .right_f_r {
        width: 100%;
        float: none;
        text-align: center;
        margin-top: 5px;
    }
}

.index_box {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.index_txt {
    text-align: center;
    font-weight: 300;
}

.index_txt img {
    border-radius: 50%;
    width: 140px;
    height: 140px;
}

.index_txt h1 {
    font-size: 30px;
    margin: 20px 0;
}

.index_txt p {
    font-size: 16px;
    font-weight: inherit;
    line-height: 30px;
    width: 70%;
    margin: 20px auto;
}

.index_txt .zuobiao {
    color: #3c3c3c;
    line-height: 1.8;
    font-size: 14px;
}

.index_txt .zuobiao i {
    margin-right: 6px;
    vertical-align: middle;
    background-size: 100%;
    width: 16px;
    height: 16px;
    display: inline-block;
    margin-top: -2px;
}

@media (max-width: 768px) {
    .index_txt p {
        width: 100%;
    }

    .index_txt {
        width: 90%;
    }
}

.index_nav {
    margin-top: 20px;
}

.index_nav li {
    display: inline-block;
    margin: 6px;
    text-decoration: underline;
}
</style>