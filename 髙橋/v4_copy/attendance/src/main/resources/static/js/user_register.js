document.addEventListener("DOMContentLoaded", () => {

    const params = new URLSearchParams(window.location.search);
    const error = params.get("error");

    if (error === "M0007") {
        alert(ERROR_M0007);
    }
    if (error === "M0008") {
        alert(ERROR_M0008);
    }
    if (error === "M0009") {
        alert(ERROR_M0009);
    }
});
