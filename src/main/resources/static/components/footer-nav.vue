<template>
    <div class="footer-nav">
        <!--服务-->
        <div class="content_box max-width" id="product_service">
            <ul>
                <li class="item l">
                    <p class="item_icon icon1 l"></p>
                    <div class="item_text l">
                        <p class="text_tit">真实货源</p>
                        <p class="text_info">一手货源&nbsp真实存在</p>
                    </div>
                </li>
                <li class="item l">
                    <p class="item_icon icon2 l"></p>
                    <div class="item_text l">
                        <p class="text_tit">品质保障</p>
                        <p class="text_info">垂直专业&nbsp优选厂商</p>
                    </div>
                </li>
                <li class="item l">
                    <p class="item_icon icon3 l"></p>
                    <div class="item_text l">
                        <p class="text_tit">服务保障</p>
                        <p class="text_info">专业服务团队</p>
                    </div>
                </li>
                <li class="item l">
                    <p class="item_icon icon4 l"></p>
                    <div class="item_text l">
                        <p class="text_tit">交易透明</p>
                        <p class="text_info">买卖双方面谈价格</p>
                    </div>
                </li>
            </ul>
        </div>
        <!--指引及copyright-->
        <div class="footerbox">
            <div class="footerinbox">
                <div class="content_box">
                    <div class="footerinside">
                        <h1>新手指南</h1>
                        <a href="#">免费注册</a>
                        <a href="#">个人资料及密码</a>
                        <a href="#">商品关注</a>
                        <a href="#">查询订单</a>
                        <a href="#">购物流程</a>
                    </div>
                    <div class="footerinside">
                        <h1>付款/退款</h1>
                        <a href="#">在线支付</a>
                        <a href="#">银行转账</a>
                        <a href="#">发票办理</a>
                    </div>
                    <div class="footerinside">
                        <h1>配送方式</h1>
                        <a href="#">快递配送</a>
                        <a href="#">掌控物流</a>
                    </div>
                    <div class="footerinside">
                        <h1>售后服务</h1>
                        <a href="#">退货流程</a>
                        <a href="#">退货政策</a>
                    </div>
                    <div class="footerinside">
                        <h1>帮助中心</h1>
                        <a href="#">常见热点问题</a>
                        <a href="#">联系我们</a>
                        <a href="#">投诉与建议</a>
                    </div>
                </div>
            </div>
            <div class="copyright_bgcolor">
                <div class="copyright">
                    Copyright © 2020 实践是检验真理的唯一标准 版权所有 保留一切权力 京ICPXXXXXXX号-X
                </div>
            </div>
        </div>
    </div>

</template>
<script>
    module.exports = {}
</script>
<style>
    .el-footer {
        padding: 0;
    }

    .footer-nav {
        background: #f9f9f9;
        /* position: fixed;
        width: 100%;
        bottom: 0; */
    }

    .content_box {
        height: 98px;
    }

    #product_service {
        height: 98px;
    }

    #product_service .item {
        width: 25%;
    }

    #product_service .item_icon {
        width: 51px;
        height: 56px;
        margin: 22px 17px 0 25px;
        background-image: url(../images/common/css_sprites.png);
    }

    #product_service .item .icon1 {
        background-position: -590px -246px;
    }

    #product_service .item .icon2 {
        background-position: -590px -170px;
    }

    #product_service .item .icon3 {
        background-position: -590px -94px;
    }

    #product_service .item .icon4 {
        background-position: -590px -16px;
    }

    #product_service .text_tit {
        margin-top: 26px;
        font-size: 16px;
        font-weight: bold;
        color: #1f1f1f;
        line-height: 26px;
    }

    #product_service .text_info {
        font-size: 12px;
        color: #424242;
    }

    .footerbox {
        border-top: 1px solid #dcdcdc;
        max-width: 1200px;
        margin: 0 auto;
    }

    .footerinbox {
        padding-top: 20px;
        padding-bottom: 20px;
        padding-left: 50px;
        overflow: hidden;
    }

    .footerinside {
        float: left;
        padding-left: 20px;
        padding-right: 80px;
    }

    .footerinside h1 {
        font-size: 16px;
        line-height: 30px;
    }

    .footerinside a {
        font-size: 12px;
        display: block;
        padding: 4px 0;
    }

    #currentfootInside {
        float: left;
        padding-left: 80px;
        padding-right: 46px;
    }

    .copyright_bgcolor {
        border-top: 1px solid #dcdcdc;
    }

    .copyright {
        color: #4c4c4c;
        text-align: center;
        padding: 20px 0;
    }
</style>