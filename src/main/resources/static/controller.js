const textInput = document.getElementById("inp");
const textOutput = document.getElementById("out");

let example = textInput.value = "{\n" +
    "  \"header\": {\n" +
    "    \"title\": \"Book addition\",\n" +
    "    \"actors\": [\n" +
    "      \"Librarian\"\n" +
    "    ],\n" +
    "    \"systemActor\": \"System\"\n" +
    "  },\n" +
    "  \"steps\": [\n" +
    "    {\n" +
    "      \"action\": \"Librarian selects options to add a new book item\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"action\": \"A form is displayed\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"action\": \"Librarian provides the details of the book\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"action\": \"IF: Librarian wishes to add copies of the book\",\n" +
    "      \"steps\": [\n" +
    "        {\n" +
    "          \"action\": \"Librarian chooses to define instances\"\n" +
    "        },\n" +
    "        {\n" +
    "          \"action\": \"System presents defined instances\"\n" +
    "        },\n" +
    "        {\n" +
    "          \"action\": \"FOR EACH: instance:\",\n" +
    "          \"steps\": [\n" +
    "            {\n" +
    "              \"action\": \"Librarian chooses to add an instance\"\n" +
    "            },\n" +
    "            {\n" +
    "              \"action\": \"System prompts to enter the instance details\"\n" +
    "            },\n" +
    "            {\n" +
    "              \"action\": \"Librarian enters the instance details and confirms them\"\n" +
    "            },\n" +
    "            {\n" +
    "              \"action\": \"System informs about the correct addition of an instance and presents the updated list of instances\"\n" +
    "            }\n" +
    "          ]\n" +
    "        }\n" +
    "      ]\n" +
    "    },\n" +
    "    {\n" +
    "      \"action\": \"Librarian confirms book addition\"\n" +
    "    },\n" +
    "    {\n" +
    "      \"action\": \"System informs about the correct addition of the book\"\n" +
    "    }\n" +
    "  ]\n" +
    "}";

textInput.value = example;

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
    let param = "simplified?level=" + document.querySelector("#simplified_requirements_obtainer + input").value;
    request(textInput.value, param);
});

document.getElementById("keywords_counter").addEventListener("click", () => {
    let param = "keywords?keywords=" + document.querySelector("#keywords_counter + input").value;
    request(textInput.value, param);
});

document.getElementById("steps_without_actor").addEventListener("click", () => {
    request(textInput.value, "no-actor");
});

document.getElementById("text_with_step_numbers_downloader").addEventListener("click", () => {
    request(textInput.value, "download");
});

document.getElementById("default").addEventListener("click", () => {
    textInput.value = example;
});

document.getElementById("clear").addEventListener("click", () => {
    textInput.value = "";
});