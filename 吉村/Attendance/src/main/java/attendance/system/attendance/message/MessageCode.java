package attendance.system.attendance.message;

public enum MessageCode {

    M0001("ログインIDまたはパスワードを入力してください"),
    M0002("ログインIDまたはパスワードが違います"),
    M0003("有効な文字を入力してください(半角数字のみ)"),
    M0004("有効な文字を入力してください(半角英数字のみ)"),

    M0006("入力された情報を保存しました"),

    M0009("未入力項目があります"),

    // 勤怠
    M0010("出勤しました"),
    M0011("勤務場所を選択してください"),
    M0012("退勤しました"),

    M0013("ログアウトしますか？");

    private final String message;

    MessageCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}