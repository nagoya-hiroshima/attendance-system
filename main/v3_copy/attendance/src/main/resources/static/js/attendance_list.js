const checkItems = document.querySelectorAll(".check-item");
const checkAll = document.getElementById("check-all");

// 初期状態：名前のみ
hideAllColumns();

// 個別チェックボックス
checkItems.forEach(check => {
    check.addEventListener("change", () => {

        // 「全て」を解除
        checkAll.checked = false;

        updateColumns();
    });
});

// 全て
checkAll.addEventListener("change", () => {
    if (checkAll.checked) {

        // 他チェックを全解除
        checkItems.forEach(c => c.checked = false);

        // 全列表示
        document.querySelectorAll(
            ".col-work, .col-mail, .col-tel, .col-emergency"
        ).forEach(el => el.classList.remove("hidden"));

    } else {
        hideAllColumns();
    }
});

function updateColumns() {
    hideAllColumns();

    checkItems.forEach(check => {
        if (check.checked) {
            const col = check.dataset.col;
            document.querySelectorAll(`.col-${col}`)
                .forEach(el => el.classList.remove("hidden"));
        }
    });
}

function hideAllColumns() {
    document.querySelectorAll(
        ".col-work, .col-mail, .col-tel, .col-emergency"
    ).forEach(el => el.classList.add("hidden"));
}
