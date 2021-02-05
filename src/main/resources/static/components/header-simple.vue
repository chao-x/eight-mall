<template>
    <el-header class="header-simple">
        <el-container class="header-simple-container max-width">
            <div class="logo">
                <img :src="logo" class="adHeadPic" style="height: 24px;width:108px;margin-top: 0px;">
            </div>
            <div>&nbsp;&nbsp;|&nbsp;&nbsp;</div>
            <div class="name">华为商城</div>
        </el-container>
    </el-header>
</template>
<script>
    module.exports = {
        data() {
            return {
                logo: "https://id1.cloud.huawei.com/CAS/up/idmw_rss_66/logos/HUAWEI.svg"
            }
        },
        methods: {

        },
    }
</script>
<style>
    .el-header{
        padding: 0;
    }
    
    .header-simple {
        padding: 0;
        background: rgba(0, 0, 0, 0.05);
        height: 60px;
    }

    .header-simple-container {
        padding-top: 18px;
    }
    
    .logo{
        line-height: 60px;
    }

    .name{
    }
</style>