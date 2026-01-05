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

        const form = document.getElementById("userForm");

        const body = {
            name: form.name.value,
            deployId: form.deployId.value || null,
            workPlace: form.workPlace.value || null,
            mailAddress: form.mailAddress.value,
            telephoneNum: form.telephoneNum.value,
            emergencyNum: form.emergencyNum.value
        };

        fetch("/api/user/update", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body)
        })
        .then(async res => {
            const message = await res.text();

            if (!res.ok) {
                throw new Error(message);
            }

            // UI戻す
            const target = saveBtn.dataset.save;
            const field = document.querySelector(`[name='${target}']`);

            field.disabled = true;
            saveBtn.classList.add("hidden");
            editButtons.forEach(b => b.disabled = false);

            window.isEditing = false;
            window.currentEditingTarget = null;

            alert(message);
        })
        .catch(err => {
            alert(err.message);
        });
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
