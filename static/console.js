/**
 * 往控制台添加数据
 * @param {Object} data 后台传来的打印数据
 * @param {Object} color 需打印的数据颜色
 */
function appendTextToConsole(data, color) {
	$("#console_div").append("<p style='color:" + color + ";'>" + data + "</p>");
	var plist = $('#console_div').find('p');
	if(plist.length > 100) { //大于100时从上往下删除
		$("#console_div p:first").remove();
	}
	//每次添加完后，将滚动条滚到最底下
    $("#console_div").scrollTop($("#console_div")[0].scrollHeight);
}

/**
	 * 控制台鼠标右键方法事件
	 */
	function mousedownClick(event) {
		var y = event.clientY;
		var x = event.clientX;
		var offsetHeight = document.documentElement.clientHeight || document.body.clientHeight;
		var off = offsetHeight - y;
		if(off < 75) {
			$("#mousewindow").css("top", (offsetHeight - 75) + "px");
		} else {
			$("#mousewindow").css("top", y + "px");
		}
		$("#mousewindow").css("left", x + "px");
		$("#mousewindow").css("display", "block");
	}

/**
 * 控制台鼠标右键复制按钮事件
 */
function copytext() {
	
	var text = document.getElementById("console_div");
        if (document.body.createTextRange) {
            var range = document.body.createTextRange();
            range.moveToElementText(text);
            range.select();
        } else if (window.getSelection) {
            var selection = window.getSelection();
            var range = document.createRange();
            range.selectNodeContents(text);
            selection.removeAllRanges();
            selection.addRange(range);
        } else {
            alert("none");
        }
        document.execCommand('Copy','false',null);
	//事件处理完毕后隐藏弹出框
	$("#mousewindow").css("display", "none");
}

/**
 * 控制台鼠标右键清空按钮事件
 */
function cleartext() {
	$("#console_div p").remove();
	//事件处理完毕后隐藏弹出框
	$("#mousewindow").css("display", "none");
}