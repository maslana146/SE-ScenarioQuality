const textInput = document.getElementById("inp");
const textOutput = document.getElementById("out");


function request(input, api) {
    let xhr = new XMLHttpRequest();
    let url = `http://localhost:8080/api/${api}`;
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            textOutput.value = JSON.stringify(xhr.responseText);
            textOutput.value = xhr.responseText;

        }
    };
    return xhr.send(JSON.parse(JSON.stringify(input)));
}

document.getElementById("steps_counter").addEventListener("click", () => {
    request(textInput.value, "steps");
});

document.getElementById("simplified_requirements_obtainer").addEventListener("click", () => {
    let param = "/simplified?level=" + document.querySelector("#simplified_requirements_obtainer + input").value;
    request(textInput.value, param);
});
