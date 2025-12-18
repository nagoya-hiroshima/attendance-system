const editButtons = document.querySelectorAll(".edit-btn");
const saveButtons = document.querySelectorAll(".save-btn-inline");
window.isEditing = false;
window.currentEditingTarget = null; // ← 今編集している項目名を保持

// 編集ボタン押下
editButtons.forEach(btn => {
    btn.addEventListener("click", () => {

        const target = btn.dataset.target;
        const field = document.querySelector(`[name='${target}']`);
        const saveBtn = document.querySelector(`[data-save='${target}']`);

        // 対象フィールド編集可
        field.disabled = false;

        // 他編集ボタンは無効化
        editButtons.forEach(b => b.disabled = true);

        // 保存ボタン表示
        saveBtn.classList.remove("hidden");

        // 編集状態記録
        window.isEditing = true;
        window.currentEditingTarget = target;
    });
});

// 保存ボタン押下
saveButtons.forEach(saveBtn => {
    saveBtn.addEventListener("click", () => {

        const target = saveBtn.dataset.save;
        const field = document.querySelector(`[name='${target}']`);

        // 再度無効化
        field.disabled = true;

        // 保存ボタン非表示
        saveBtn.classList.add("hidden");

        // 編集ボタン再度有効化
        editButtons.forEach(b => b.disabled = false);

        window.isEditing = false;
        window.currentEditingTarget = null;

        alert("保存しました");
    });
});

// 戻るボタン
function goBack() {

    // 編集中 → 編集解除だけして画面遷移しない
    if (window.isEditing && window.currentEditingTarget) {

        const target = window.currentEditingTarget;
        const field = document.querySelector(`[name='${target}']`);
        const saveBtn = document.querySelector(`[data-save='${target}']`);

        // フィールド無効化
        field.disabled = true;

        // 保存ボタン非表示
        saveBtn.classList.add("hidden");

        // 編集ボタンすべて有効化
        editButtons.forEach(b => b.disabled = false);

        window.isEditing = false;
        window.currentEditingTarget = null;

        return;
    }

    // 編集モードでなければ → 勤怠画面へ遷移
    window.location.href = "/api/attendance/auth";
}
