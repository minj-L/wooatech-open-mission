const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/minj-L/realTimeStock'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);

    stompClient.subscribe('/stock/buyStock', (message) => {
        showBuyingResult(JSON.parse(message.body));
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

// ⭐ 매수 요청 보내기
function sendBuyingStock() {
    const request = {
        stockName: $("#stockName").val(),
        quantity: Number($("#quantity").val())
    };

    stompClient.publish({
        destination: "/app/trade/buyingStock",
        body: JSON.stringify(request)
    });
}

// ⭐ 매수 결과를 화면에 출력
function showBuyingResult(result) {
    $("#buyingResult").append(
        `<tr><td>${result.stockName}</td><td>${result.finalPrice}</td><td>${result.quantity}</td></tr>`
    );
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    // ⭐ 매수 버튼
    $("#buyStockBtn").click(() => sendBuyingStock());
});
