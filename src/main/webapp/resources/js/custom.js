function onAddFile(file, callback) {
    if (file.name === "primefaces.pdf") {
        // this callback adds the file to the list
        callback.call(this, file);
    }
}

function showStatus() {
    PF('statusDialog').show();
}
function hideStatus() {
    PF('statusDialog').hide();
}