document.addEventListener("DOMContentLoaded", () => {

    const params = new URLSearchParams(window.location.search);
    const msg = params.get("msg");

    if (msg === "M0010") {
        alert("出勤しました");
    }

    if (msg === "M0012") {
        alert("退勤しました");
    }

    if (msg === "M0011") {
        alert("勤務場所を選択してください");
    }

    const workPlaceSelect = document.getElementById("work_place");
    const clockInBtn = document.querySelector(".clockin");

    if (!workPlaceSelect || !clockInBtn) {
        console.error("要素取得失敗");
        return;
    }

    /* 出勤：未入力チェックだけ */
    clockInBtn.addEventListener("click", (e) => {
        if (workPlaceSelect.value === "") {
            e.preventDefault();
            alert("勤務場所を選択してください");
        }
    });

    /* 時計 */
    function updateClock() {
        const now = new Date();
        const wdays = ["日","月","火","水","木","金","土"];

        document.getElementById("today-date").textContent =
            `${now.getFullYear()}/${now.getMonth() + 1}/${now.getDate()}(${wdays[now.getDay()]})`;

        document.getElementById("current-time").textContent =
            now.toLocaleTimeString("ja-JP", { hour12: false });
    }

    setInterval(updateClock, 1000);
    updateClock();
});

function logoutConfirm() {
    if (confirm("ログアウトしますか？")) {
        location.href = "/api/login/logout";
    }
}
window.logoutConfirm = logoutConfirm;
