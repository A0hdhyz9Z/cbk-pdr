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
                    <div class="list">
                        <ul id="ulist" class="index_nav">
                            <li id="menu-item-1"
                                class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-1">
                                <router-link to="/login">
                                    <button class="cssbuttons-io-button" id="login_btn1"> 登 录
                                        <div class="icon">
                                            <svg height="24" width="24" viewBox="0 0 24 24"
                                                xmlns="http://www.w3.org/2000/svg">
                                                <path d="M0 0h24v24H0z" fill="none"></path>
                                                <path
                                                    d="M16.172 11l-5.364-5.364 1.414-1.414L20 12l-7.778 7.778-1.414-1.414L16.172 13H4v-2z"
                                                    fill="currentColor"></path>
                                            </svg>
                                        </div>
                                    </button>
                                </router-link>
                            </li>
                            <li id="menu-item-2"
                                class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2">
                                <router-link to="/register">
                                    <button class="cssbuttons-io-button_2" id="register_btn1"> 注 册
                                        <div class="icon">
                                            <svg height="24" width="24" viewBox="0 0 24 24"
                                                xmlns="http://www.w3.org/2000/svg">
                                                <path d="M0 0h24v24H0z" fill="none"></path>
                                                <path
                                                    d="M16.172 11l-5.364-5.364 1.414-1.414L20 12l-7.778 7.778-1.414-1.414L16.172 13H4v-2z"
                                                    fill="currentColor"></path>
                                            </svg>
                                        </div>
                                    </button>
                                </router-link>
                            </li>
                            <li id="menu-item-3"
                                class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-3">
                                <router-link to="/login">
                                    <button class="cssbuttons-io-button_2"> 关 于
                                        <div class="icon">
                                            <svg height="24" width="24" viewBox="0 0 24 24"
                                                xmlns="http://www.w3.org/2000/svg">
                                                <path d="M0 0h24v24H0z" fill="none"></path>
                                                <path
                                                    d="M16.172 11l-5.364-5.364 1.414-1.414L20 12l-7.778 7.778-1.414-1.414L16.172 13H4v-2z"
                                                    fill="currentColor"></path>
                                            </svg>
                                        </div>
                                    </button>
                                </router-link>
                            </li>
                            <li id="menu-item-4"
                                class="menu-item menu-item-type-post_type menu-item-object-page menu-item-4">
                                <router-link to="/login">
                                    <button class="cssbuttons-io-button_2"> 联 系
                                        <div class="icon">
                                            <svg height="24" width="24" viewBox="0 0 24 24"
                                                xmlns="http://www.w3.org/2000/svg">
                                                <path d="M0 0h24v24H0z" fill="none"></path>
                                                <path
                                                    d="M16.172 11l-5.364-5.364 1.414-1.414L20 12l-7.778 7.778-1.414-1.414L16.172 13H4v-2z"
                                                    fill="currentColor"></path>
                                            </svg>
                                        </div>
                                    </button>
                                </router-link>
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

<style scoped>
.cssbuttons-io-button {
    background: #7072f0;
    color: white;
    padding: 0.35em;
    padding-left: 1.2em;
    font-size: 17px;
    font-weight: 900;
    border-radius: 0.9em;
    border: none;
    letter-spacing: 0.05em;
    display: flex;
    align-items: center;
    box-shadow: inset 0 0 1.6em -0.6em #4f4da6;
    overflow: hidden;
    position: relative;
    height: 2.8em;
    padding-right: 3.3em;
    margin-top: 30px;
    width:200px
}

.cssbuttons-io-button .icon {
    background: white;
    margin-left: 1em;
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 2.2em;
    width: 2.2em;
    border-radius: 0.7em;
    box-shadow: 0.1em 0.1em 0.6em 0.2em #5452b9;
    right: 0.3em;
    transition: all 0.3s;
}

.cssbuttons-io-button:hover .icon {
    width: calc(100% - 0.6em);
}

.cssbuttons-io-button .icon svg {
    width: 1.1em;
    transition: transform 0.3s;
    color: #5452b9;
}

.cssbuttons-io-button:hover .icon svg {
    transform: translateX(0.1em);
}

.cssbuttons-io-button:active .icon {
    transform: scale(0.95);
}

.cssbuttons-io-button_2 {
    background: #a1e6ec;
    color: white;
    padding: 0.35em;
    padding-left: 1.2em;
    font-size: 17px;
    font-weight:  900;
    border-radius: 0.9em;
    border: none;
    letter-spacing: 0.05em;
    display: flex;
    align-items: center;
    box-shadow: inset 0 0 1.6em -0.6em #58d8e9;
    overflow: hidden;
    position: relative;
    height: 2.8em;
    padding-right: 3.3em;
    margin-top: 30px;
    width:200px;
}

.cssbuttons-io-button_2 .icon {
    background: white;
    margin-left: 1em;
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 2.2em;
    width: 2.2em;
    border-radius: 0.7em;
    box-shadow: 0.1em 0.1em 0.6em 0.2em #58d8e9;
    right: 0.3em;
    transition: all 0.3s;
}

.cssbuttons-io-button_2:hover .icon {
    width: calc(100% - 0.6em);
}

.cssbuttons-io-button_2 .icon svg {
    width: 1.1em;
    transition: transform 0.3s;
    color: #c7e8eb;
}

.cssbuttons-io-button_2:hover .icon svg {
    transform: translateX(0.1em);
}

.cssbuttons-io-button_2:active .icon {
    transform: scale(0.95);
}

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
    text-decoration: none;
}

.router-link-active {
  text-decoration: none;
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

.list{
    display: flex; /**/
            justify-content: center; /*水平居中*/
            align-items: Center; /*垂直居中*/
    text-align: center;
}

.index_nav {
    margin-top: 20px;
}

.index_nav li {
    /* display: inline-block; */
    margin: 6px;
    text-decoration: none;
}
</style>