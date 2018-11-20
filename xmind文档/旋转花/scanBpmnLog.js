window.onerror=function(){return true;};
$().ready(function(){
    /**
     * 显示Loading图片，等待加载数据
     */

    var target = $("#preview")[0];
    var target1 = $("#subFlower")[0];
    var sp = new Spinner({
        lines: 30, 			// 花瓣数目
        length: 10, 		// 花瓣长度
        width: 1, 			// 花瓣宽度
        radius: 30, 		// 花瓣距中心半径
        corners: 1, 		// 花瓣圆滑度 (0-1)
        rotate: 0, 			// 花瓣旋转角度
        direction: 1, 		// 花瓣旋转方向 1: 顺时针, -1: 逆时针
        color: '#646464', 	// 花瓣颜色
        speed: 1,			// 花瓣旋转速度
        trail: 80, 			// 花瓣旋转时的拖影(百分比)
        shadow: true, 		// 花瓣是否显示阴影
        hwaccel: false, 	//spinner 是否启用硬件加速及高速旋转            
        className: 'spinner', // spinner css 样式名称
        zIndex: 2e9 		// spinner的z轴 (默认是2000000000)
    });
    // sp.spin(target);
    sp.spin(target1)
    $("#abc").click(function(){
        sp.spin();
    })





});




