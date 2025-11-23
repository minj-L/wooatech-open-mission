const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/minj-L/realTimeStock'
});

let labels = [];
let buyData = [];
let sellData = [];

// Chart.js 초기화
const ctx = document.getElementById('stockChart').getContext('2d');
const stockChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: labels,
        datasets: [
            {label: '매수', data: buyData, borderColor: 'red', fill: false, spanGaps: true},
            {label: '매도', data: sellData, borderColor: 'blue', fill: false, spanGaps: true}
        ]
    },
    options: {
        responsive: true,
        animation: false,
        scales: {
            x: {title: {display: true, text: '시간'}},
            y: {title: {display: true, text: '가격'}}
        }
    }
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);

    stompClient.subscribe('/stock/buyStock', (message) => showBuyingResult(JSON.parse(message.body)));
    stompClient.subscribe('/stock/sellStock', (message) => showSellingResult(JSON.parse(message.body)));
};

stompClient.onWebSocketError = console.error;
stompClient.onStompError = (frame) => {
    console.error('Broker error:', frame.headers['message'], frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
}

// WebSocket 연결/해제
function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
}

// 매수/매도 요청 보내기
function sendBuyingStock() {
    stompClient.publish({
        destination: "/app/trade/buyingStock",
        body: JSON.stringify({stockName: $("#stockName").val(), quantity: Number($("#quantity").val())})
    });
}

function sendSellingStock() {
    stompClient.publish({
        destination: "/app/trade/sellingStock",
        body: JSON.stringify({stockName: $("#stockName").val(), quantity: Number($("#quantity").val())})
    });
}

// 거래 결과 화면 출력
function showBuyingResult(result) {
    appendTable(result, 'buying');
    updateChart(result, 'buy');
}

function showSellingResult(result) {
    appendTable(result, 'selling');
    updateChart(result, 'sell');
}

function appendTable(result, type) {
    $("#buyingResult").append(
        `<tr>
            <td>${result.stockName}</td>
            <td class="${type}">${result.finalPrice}</td>
            <td class="${type}">${result.quantity}</td>
        </tr>`
    );
}

// 실시간 그래프 업데이트
function updateChart(result, type) {
    const now = new Date().toLocaleTimeString();
    labels.push(now);

    if (type === 'buy') {
        buyData.push(result.finalPrice);
        sellData.push(null);
    } else {
        buyData.push(null);
        sellData.push(result.finalPrice);
    }

    // 최근 20개만
    if (labels.length > 20) {
        labels.shift();
        buyData.shift();
        sellData.shift();
    }

    stockChart.update();
}

// 버튼 이벤트
$(function () {
    $("#connect").click(connect);
    $("#disconnect").click(disconnect);
    $("#buyStockBtn").click(sendBuyingStock);
    $("#sellStockBtn").click(sendSellingStock);
});
