<template>
    <!--头部搜索-->
    <div class="action_search_box max-width">
        <!--logo-->
        <a class="logo" href="index.html"></a>
        <!--search-->
        <div class="inputbox">
            <input type="text" placeholder="请输入您感兴趣的内容" v-model="keyword">
            <a class="searchbtn" @click="search(keyword)">搜&nbsp;索</a>
            <!-- <div class="hotword">
                热销：
                <a href="#" target="_blank">&nbsp;&nbsp;&nbsp;闸板泵&nbsp;&nbsp;&nbsp;</a>
            </div> -->
        </div>
    </div>
</template>
<script>
    module.exports = {
        props: {
            keyword: {
                type: String,
                default: ''
            },
            //是否已打开搜索页面
            open: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                keyword: ""
            }
        },
        methods: {
            search(keyword) {
                const params = new URLSearchParams()
                params.append('keyword', keyword)
                if (!this.open) {
                    let tempWindow = window.open("_blank")
                    tempWindow.location.href = 'commodity-search.html?' + params
                } else {
                    window.location.href = 'commodity-search.html?' + params
                }
            }
        }
    }
</script>
<style>
    .action_search_box {
        background-color: #ffffff;
    }

    .logo {
        float: left;
        background-image: url('../images/common/logo.png');
        /* background: url('https://res.vmallres.com/pimages//common/config/logo/SXppnESYv4K11DBxDFc2.png'); */
        background-repeat: no-repeat;
        width: 199px;
        height: 61px;
        margin-right: 461px;
        margin-top: 7px;
    }

    .inputbox {
        float: right;
        height: 58px;
        padding-top: 20px;
        width: 540px;
        line-height: 58px;
        border-radius: 20px;
    }

    .inputbox input {
        line-height: 35px;
        width: 418px;
        height: 35px;
        border: none;
        border-radius: 20px;
        background-color: #f5f5f5;
        font-size: 14px;
        padding-left: 4px;
        outline: none;
        float: left;
    }

    .inputbox input:hover {
        border: 1px solid #dcdcdc;
    }

    .searchbtn {
        width: 84px;
        /* background-color: #41b6e7; */
        font-size: 16px;
        display: block;
        line-height: 35px;
        float: left;
        text-align: center;
        /* color: #333333; */
    }

    .searchbtn:hover {
        color: #41b6e7;
    }

    .hotword {
        float: none;
        line-height: 19px;
        clear: both;
        clear: #999999;
        width: 524px;
        padding-top: 4px;
    }

    .hotword a {
        color: #999999;
        border-right: 1px #c2c1c1 solid;
    }
</style>