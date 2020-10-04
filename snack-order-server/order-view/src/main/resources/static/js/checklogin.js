let login = new Vue({
	el: "#header",
	data: {
		onlogin: false,
		nickName: "匿名",
		loginId: "",
		memberInfo: {},
		cartCount: 0,
		types: []
	},
	mounted: function() {
		/*axios.get("member/check").then(rt => {
			if (rt.status == 200) { // 说明请求成功
				if (rt.data.code == 200) { // 说明登录
					this.memberInfo = rt.data.data;
					this.nickName = rt.data.data.nickName;
					this.loginId = rt.data.data.mno;
					this.onlogin = true;
				}
			} else {
				this.onlogin = false;
			}
		})*/
		axios.get("product/types").then(rt => {
			if (rt.status == 200 && rt.data.code == 200) { // 说明请求成功
				this.types = rt.data.data;
			}
		})
	}
})

function showMsg(msg, callback) {
	$("#popup_info").text(msg);
	$('.popup_con').fadeIn('fast', function() {
		setTimeout(function(){
			$('.popup_con').fadeOut('fast', callback);	
		}, 2000)
	});
}