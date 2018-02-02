var selfLocation = window.location; 

/* JavaScript Date Update */

var timerJs = $.timer(function() {
    $("#timeJsDate").text(new Date().toLocaleTimeString());
});
timerJs.set({ time : 1000, autostart : true });

/* Servlet Update */

var urlServlet = window.location.toString().replace('/index.html','/') + "time/";

var timerServlet = $.timer(function() {
    $.get(urlServlet, function(responseText) {
        $('#timeServlet').text(responseText);
    })
    .fail(function(jqXHR, textStatus){
        timerServlet.stop();
        $('#timeServlet').text(textStatus);
    });
});
timerServlet.set({ time : 1000, autostart : true });

/* WebSocket Update */

var urlWebSocket = window.location.toString()
                         .replace('/index.html', '/')
                         .replace('http://', 'ws://')
                         .replace('https://', 'wss://')
                         + "time/";

function initWebSocket()
{
    $('#timeWebSocket').text(urlWebSocket);
    var wsTime = {
        connect : function() {
            try {
                this._ws = new WebSocket(urlWebSocket);
                this._ws.onopen = this._onopen;
                this._ws.onmessage = this._onmessage;
                this._ws.onclose = this._onclose;
                this._ws.onerror = this._onerror;
            } catch(exception) {
                
            }
        },
        
        _onopen : function() {
            $('#timeWebSocket').text("opened");
        },
        
        _onerror : function(evt) {
            $('#timeWebSocket').text("error: " + evt.data);
        },
        
        _onmessage : function(m) {
            if(m.data) {
                $('#timeWebSocket').text(m.data);
            }
        },
        
        _onclose : function(closeEvent) {
            var codeMap = {};
            codeMap[1000] = "(NORMAL)";
            codeMap[1001] = "(ENDPOINT_GOING_AWAY)";
            codeMap[1002] = "(PROTOCOL_ERROR)";
            codeMap[1003] = "(UNSUPPORTED_DATA)";
            codeMap[1004] = "(UNUSED/RESERVED)";
            codeMap[1005] = "(INTERNAL/NO_CODE_PRESENT)";
            codeMap[1006] = "(INTERNAL/ABNORMAL_CLOSE)";
            codeMap[1007] = "(BAD_DATA)";
            codeMap[1008] = "(POLICY_VIOLATION)";
            codeMap[1009] = "(MESSAGE_TOO_BIG)";
            codeMap[1010] = "(HANDSHAKE/EXT_FAILURE)";
            codeMap[1011] = "(SERVER/UNEXPECTED_CONDITION)";
            codeMap[1015] = "(INTERNAL/TLS_ERROR)";
            var codeStr = codeMap[closeEvent.code];
            $('#timeWebSocket').text("closed: " + closeEvent.code + " " + codeStr + " " + closeEvent.reason);
        }
    };
    
    wsTime.connect();
}

$(function(){
    window.setTimeout(initWebSocket, 1000);
})

