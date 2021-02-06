Vue.component('admin-frame', {
	template: `
		<div class="admin-frame">
			<div class="admin-frame-header">
				<div class="admin-frame-header-left text-2 text-weak-4  center">
					艾氪森配件商城管理平台
				</div>
				<div class="admin-frame-header-right text-2 text-weak-4 ">
					<span class=" m-l-12 pointer" :class="is_menu_opened ? 'el-icon-s-fold': 'el-icon-s-unfold' " @click="is_menu_opened = !is_menu_opened"></span>
				</div>
			</div>
			<div class="admin-frame-body">
				<div class="admin-frame-body-left" :class="{fold: !is_menu_opened}">
					<div class="admin-frame-body-left-info">
						<div class="admin-frame-body-left-info-left">
							<img src="/images/portrait.jpg" />
						</div>
						<div class="admin-frame-body-left-info-right p-l-12 text-weak-3">
							<div class="admin-frame-body-left-info-right-name">{{admin_account}}</div>
							<div class="admin-frame-body-left-info-right-status text-4 m-t-8"><span class="admin-frame-body-left-info-right-status-icon m-r-8"></span><span>在线</span></div>
						</div>
					</div>
					<el-menu
					  :default-active="active"
					  background-color="transparent"
					  text-color="#FFFFFF"
					  active-text-color = "#FFFFFF"
					  class="el-menu-vertical-demo">
					    <el-menu-item index="1" @click="window.location.href='./commodity.html'">商品管理</el-menu-item>
					    <el-menu-item index="2" @click="window.location.href='./type.html'">类别管理</el-menu-item>
					    <el-menu-item index="3" @click="window.location.href='./order.html'">订单管理</el-menu-item>
					    <el-menu-item index="4" @click="window.location.href='./user.html'">用户管理</el-menu-item>
					    <el-menu-item index="5" @click="logout">退出</el-menu-item>
					  </el-submenu>
					</el-menu>
				</div>
				<div class="admin-frame-body-right">
<!--					<div class="admin-frame-body-right-header">-->
<!--						<div class="text-1">{{title}}</div>-->
<!--						<div class="text-4 text-general-1">-->
<!--							<span class="el-icon-odometer m-r-8"></span>-->
<!--							<span class="m-r-4">首页</span>-->
<!--							<span class="text-general-2 m-r-8">&gt;</span>-->
<!--							<span>{{title}}</span>-->
<!--						</div>-->
<!--					</div>-->
					<div  class="admin-frame-body-right-body">
						<slot></slot>
					</div>
				</div>
			</div>
		</div>
	`,
	props: {
		active: {type: String, default: '1'},
		title: {type: String, default: '这是标题'}
	},
	data(){
		return{
			is_menu_opened: true,
			admin_account: '超级管理员'
		};
	},
	created(){
	    const user = JSON.parse(sessionStorage.getItem("user"));
	    if(user != null && user.role === 1){
            this.admin_account = user.account;
	    }
	},
	methods: {
	    logout(){

	        axios.post("/actionmall/user/do_logout.do").then(response=>{
	            if(response.data.status === 0){
                    sessionStorage.removeItem("user");
                    window.location.href = "/login.html";
	            }else{
	                if(response.data.msg){
	                    this.$message.error(response.data.msg);
	                }
	            }
	        }).catch(err=>{
	            this.$message.error("连接服务器异常");
	        });
	    }
	}
})