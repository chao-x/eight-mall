<template>
    <el-header class="max-width">
        <el-menu class="header-nav" :default-active="activeIndex" mode="horizontal" @select="handleSelect"
            background-color="#545C64" text-color="#ffffff" active-text-color="#41b6e7">
            <el-menu-item index="1"><a href="index.html">首页</a></el-menu-item>
            <el-menu-item index="2"><a href="index.html">官网</a></el-menu-item>
            <el-container class="right">
                <template v-if="user == null">
                    <el-menu-item index="3"><a href="login.html">登录</a></el-menu-item>
                    <el-menu-item index="4"><a href="register.html">注册</a></el-menu-item>
                </template>
                <template v-else>
                    <el-menu-item index="3">{{user.account}}</el-menu-item>
                    <el-menu-item index="4" @click="logout">登出</el-menu-item>
                </template>
                <el-menu-item index="5" @click="toOrder">我的订单</el-menu-item>
                <el-menu-item index="6" @click="toCart" v-show="showCart">购物车({{cartCount}})</el-menu-item>
            </el-container>
        </el-menu>
    </el-header>
</template>

<script>
    module.exports = {
        props: {
            showCart: {
                type: Boolean,
                default: true
            }
        },
        data() {
            return {
                user: null,
                activeIndex: 0,
                cartCount: 0
            };
        },
        methods: {
            //点击选项
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            //登出
            logout() {
                //this.$message("登出");
                axios.post(SERVER_BASE_URL + '/actionmall/user/do_logout.do').then(res => {
                    console.log("logout", res.data)
                    if (res.data.status == 0) {
                        localStorage.removeItem('user')
                        this.user = null
                        window.location.href = "index.html"
                    } else {
                        //this.$message(res.data.msg)
                        console.log(res.data.msg)
                    }
                }, err => {
                    this.$message("连接服务器失败！")
                })

            },
            toOrder() {
                //this.$message("订单");
                // var temp = window.open("_blank")
                //window.location = 'confirm-order.html'
            },
            toCart() {
                //this.$message("购物车");
                // var temp = window.open("_blank")
                window.location = 'cart.html'
            },
            //获取购物车数量
            getCartCount() {
                let user = JSON.parse(localStorage.getItem('user'))
                console.log('header-nav-user', user)
                if (user != null) {
                    axios.post(SERVER_BASE_URL + '/actionmall/cart/getcartcount.do').then(res => {
                        console.log("cartNum", res.data)
                        if (res.data.status == 0) {
                            this.cartCount = res.data.data
                        } else {
                            //this.$message(res.data.msg)
                            console.log(res.data.msg)
                        }
                    }, err => {
                        this.$message("连接服务器失败！")
                    })
                } else {
                    let cartItems = JSON.parse(localStorage.getItem('cartItems'))
                    console.log('header-nav-cartItems', cartItems)
                    if (cartItems != null) {
                        let count = 0
                        for (var i in cartItems) {
                            count += cartItems[i].quantity
                        }
                        this.cartCount = count
                    }
                }
            },
            //获取用户信息
            getUserInfo() {
                //console.log(SERVER_BASE_URL)
                axios.get(SERVER_BASE_URL + '/actionmall/user/getuserinfo.do').then(res => {
                    console.log("userInfo", res.data)
                    if (res.data.status == 0) {
                        this.user = res.data.data[0]
                        localStorage.setItem('user', JSON.stringify(this.user))
                    } else {
                        localStorage.removeItem('user')
                        //this.$message(res.data.msg)
                        console.log(res.data.msg)
                    }
                    //请求购物车数量
                    this.getCartCount()
                }, err => {
                    this.$message("连接服务器失败！")
                })
            },
            //模拟注册
            register() {
                let user = {
                    account: "test_user",
                    password: "123"
                }
                axios.post(SERVER_BASE_URL + '/actionmall/user/do_register.do', user).then(res => {
                    console.log(res.data)
                }, err => {})
            },
            //模拟登录
            login() {
                const data = {
                    account: "test_user",
                    password: "123"
                }
                axios.post(SERVER_BASE_URL + '/actionmall/user/do_login.do', data).then(res => {
                    console.log("login", res.data)
                    //this.user.account = res.data.data.account
                    //console.log(res.data.data)
                    let user = res.data.data
                    localStorage.setItem("user", JSON.stringify(user))
                }, err => {})
            }
        },
        created() {
            //this.register()
            //this.login()
            this.getUserInfo()
            //console.log(SERVER_BASE_URL)
        },
    }
</script>

<style>
    .el-header {
        padding: 0;
        background: #545C64;
        /* background: #f5f5f5; */
    }

    .header-nav {}

    .right {
        float: right;
    }
</style>