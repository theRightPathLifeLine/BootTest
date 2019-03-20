var websocket = null;
var portId = 0;
var portOpen = false;
if('WebSocket' in window){
	websocket = new WebSocket('ws://127.0.0.1:7001/msgServer');
}else{
	alert("Not support webSocket");
}

websocket.onerror = function(){
	
}

websocket.onopen = function(){
	
}

websocket.onmessage = function(data){
	data = eval('(' + data.data + ')');
	if(data.msgType == "CONSOLE_MSG"){
		appendTextToConsole(data.data,data.color);
	}
}

websocket.onclose = function(){
	
}

window.onbeforeunload = function(){
	websocket.close();
}


