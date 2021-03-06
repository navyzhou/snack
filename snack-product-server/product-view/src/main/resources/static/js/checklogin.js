let login = new Vue({
	el: "#header",
	data: {
		onlogin: false,
		nickName: "匿名",
		loginId: "",
		memberInfo: {},
		types: [],
		cartCount: 0,
		carts: []
	},
	mounted: function() {
		axios.get("login/check").then(rt => {
			if (rt.status == 200 && rt.data.code == 200) {
				this.memberInfo = rt.data.data;
				this.nickName = rt.data.data.nickName;
				this.loginId = rt.data.data.mno;
				this.onlogin = true;
			} else {
				this.onlogin = false;
				this.nickName = "匿名";
			}
		})
		
		axios.get("types/finds").then(result => { 
			if (result.status == 200 && result.data.code == 200) {
				this.types  = result.data.data;
			}
		}) 
		
		axios.get("cart/info").then(result => {
			if (result.status == 200 && result.data.code == 200) { 
				this.cartCount = result.data.data.length;
				this.carts = result.data.data;
			}
		})
	},
	methods: {
		loginout: function() {
			if (confirm("您确定要退出登录吗?")) {
				axios.post("login/loginout").then( result => {
					if (result.status == 200 && result.data.code == 200) {
						this.onlogin = false;
						this.nickName = "匿名";
						this.loginId = "";
						this.memberInfo = {};
						this.cartCount = 0;
						this.carts = [];
					} else {
						showMsg("噢，注销失败了，我们重新来吧...", "red", function() {
							location.href = "/user/login.html";
						})
					}
				})
			}
		},
	}
})

function showMsg(msg, color, callback) {
	$("#popup_info").css("color", color).text(msg);
	$('.popup_con').fadeIn('fast', function() {
		setTimeout(function(){
			$('.popup_con').fadeOut('fast', callback);	
		}, 2000)
	});
}