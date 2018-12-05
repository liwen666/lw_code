window.onerror=function(){return true;};
$().ready(function(){
    /**
     * 显示Loading图片，等待加载数据
     */

    var target = $("#preview")[0];
    var sp = new Spinner({
        lines: 60, 			// 花瓣数目
        length: 20, 		// 花瓣长度
        width: 2, 			// 花瓣宽度
        radius: 150, 		// 花瓣距中心半径
        corners: 1, 		// 花瓣圆滑度 (0-1)
        rotate: 0, 			// 花瓣旋转角度
        direction: 1, 		// 花瓣旋转方向 1: 顺时针, -1: 逆时针
        color: '#5060f3', 	// 花瓣颜色
        speed: 1,			// 花瓣旋转速度
        trail: 80, 			// 花瓣旋转时的拖影(百分比)
        shadow: false, 		// 花瓣是否显示阴影
        hwaccel: false, 	//spinner 是否启用硬件加速及高速旋转            
        className: 'spinner', // spinner css 样式名称
        zIndex: 2e9 		// spinner的z轴 (默认是2000000000)
    });
    sp.spin(target)
    $("#abc").click(function(){
        sp.spin();
    })
    setTimeout(function(){sp.spin();alert("关闭花");$(".loading").hide()},4000)




});




