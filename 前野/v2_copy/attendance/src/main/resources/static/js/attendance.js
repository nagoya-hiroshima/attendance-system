document.addEventListener("DOMContentLoaded", () => {

    /* ===== メッセージ判定 ===== */
    const params = new URLSearchParams(window.location.search);
    const msg = params.get("msg");

    let isClockedIn = IS_CLOCKED_IN;

    if (msg === "IN") {
        alert(MESSAGE_IN);
        isClockedIn = true;
    }

    if (msg === "OUT") {
        alert(MESSAGE_OUT);
        isClockedIn = false;
    }

    /* ===== 要素取得 ===== */
    const workPlaceSelect = document.getElementById("work_place");
    const clockInBtn = document.querySelector(".clockin");
    const clockOutBtn = document.querySelector(".clockout");
    const workPlaceHidden = document.getElementById("work_place_in");

    if (!workPlaceSelect || !clockInBtn || !clockOutBtn) {
        console.error("勤怠画面の要素取得に失敗");
        return;
    }

    /* ===== 初期状態制御 ===== */
    if (isClockedIn) {
        clockInBtn.disabled = true;
        clockOutBtn.disabled = false;
        workPlaceSelect.disabled = true;
    } else {
        clockInBtn.disabled = true;
        clockOutBtn.disabled = true;
        workPlaceSelect.disabled = false;
    }

    /* ===== 勤務場所選択 ===== */
    workPlaceSelect.addEventListener("change", () => {
        if (!isClockedIn && workPlaceSelect.value !== "") {
            clockInBtn.disabled = false;
        }
    });

    /* ===== 出勤 ===== */
    clockInBtn.addEventListener("click", (e) => {
        if (workPlaceSelect.value === "") {
            e.preventDefault();
            alert("勤務場所を選択してください");
            return;
        }
        workPlaceHidden.value = workPlaceSelect.value;
    });

    /* ===== 退勤 ===== */
    clockOutBtn.addEventListener("click", (e) => {
        if (!isClockedIn) {
            e.preventDefault();
        }
    });

    /* ===== 時計 ===== */
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

/* ===== ログアウト ===== */
function logoutConfirm() {
    if (confirm(LOGOUT_CONFIRM_MESSAGE)) {
        location.href = "/api/login/logout";
    }
}
window.logoutConfirm = logoutConfirm;
