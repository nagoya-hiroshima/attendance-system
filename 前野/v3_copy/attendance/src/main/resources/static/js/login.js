document.addEventListener("DOMContentLoaded", function () {
    const toggleBtn = document.getElementById("togglePassword");
    const passwordField = document.getElementById("password");

    toggleBtn.addEventListener("click", function () {
        const currentType = passwordField.getAttribute("type");

        if (currentType === "password") {
            passwordField.setAttribute("type", "text");
        } else {
            passwordField.setAttribute("type", "password");
        }
    });
});
