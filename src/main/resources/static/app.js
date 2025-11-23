const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/minj-L/realTimeStock'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);

    stompClient.subscribe('/stock/buyStock', (message) => {
        showBuyingResult(JSON.parse(message.body));
    });

    stompClient.subscribe('/stock/sellStock', (message) => {
        showSellingResult(JSON.parse(message.body));
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

function sendSellingStock() {
    const request = {
        stockName: $("#stockName").val(),
        quantity: Number($("#quantity").val())
    };
    stompClient.publish({
        destination: "/app/trade/sellingStock",
        body: JSON.stringify(request)
    });
}

function showBuyingResult(result) {
    $("#buyingResult").append(
        `<tr>
            <td>${result.stockName}</td>
            <td class="buying">${result.finalPrice}</td>
            <td class="buying">${result.quantity}</td>
        </tr>`
    );
}

function showSellingResult(result) {
    $("#buyingResult").append(
        `<tr>
            <td>${result.stockName}</td>
            <td class="selling">${result.finalPrice}</td>
            <td class="selling">${result.quantity}</td>
        </tr>`
    );
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $("#connect").click(() => connect());
    $("#disconnect").click(() => disconnect());
    $("#buyStockBtn").click(() => sendBuyingStock());
    $("#sellStockBtn").click(() => sendSellingStock());
});
