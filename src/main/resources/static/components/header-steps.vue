<template>
    <div class="max-width header-step">
        <!--logo+title-->
        <div class="left">
            <div class="logo">
                <a href="index.html" class="logo">
                    <img src="https://res.vmallres.com/pimages//sale/2019-01/AF2GXbxrWmRqvyNfYzCl.png">
                </a>
                <span>{{document.title}}</span>
            </div>
        </div>
        <!--steps-->
        <div class="right">
            <div class="steps">
                <el-steps :active="active" finish-status="success" align-center>
                    <el-step title="我的购物车" v-if="cart"></el-step>
                    <el-step title="填写核对订单"></el-step>
                    <el-step title="成功提交订单"></el-step>
                </el-steps>
            </div>
        </div>
    </div>
</template>
<script>
    module.exports = {
        props: {
            active: {
                type: Number,
                default: 0
            },
            cart: {
                type: Boolean,
                default: true
            }
        },
        data() {
            return {
                active: 0
            }
        },
    }
</script>
<style>
    .left {
        float: left;
    }

    .right {
        float: right;
    }

    .header-step {
        height: 80px;
    }

    .logo {
        color: #3a3a3a;
        margin-top: 22px;
    }

    .logo a {}

    .logo a img {
        width: 120px;
        height: 36px;
    }

    .logo span {
        margin-left: 26px;
        font-size: 22px;
        line-height: 36px;
    }

    .steps {
        margin-top: 10px;
        width: 400px;
        height: 60px;
    }
</style>