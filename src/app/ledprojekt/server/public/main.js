async function main() {
    function handleLandingMessage(element) {
        window.currentPlayer = element.dataset.player;
        document.querySelector("#landing-message").remove();

        const controller = document.createElement("x-controller");
        document.body.appendChild(controller);

        const url = new URL(window.location.href + "players");
        const params = { command: "lock", player: window.currentPlayer };
        url.search = new URLSearchParams(params);
        fetch(url, { method: "POST" }).then(res => res.text());
    }

    class Controller extends HTMLElement {
        constructor() {
            super();

            this.shadow = this.attachShadow({
                mode: "open"
            });
            this.copyAndAttachTemplate();
        }
        copyAndAttachTemplate() {
            const template = document.querySelector("#controller-template").content.cloneNode(true);
            this.shadow.appendChild(template);
        }

        async sendCommandToServer(player, command) {
            const url = new URL(window.location.href + "command");

            const params = { player, command };
            url.search = new URLSearchParams(params);

            return await fetch(url, { method: "POST" }).then(res => res.text());
        }

        async handleTouchStart(element) {
            if (element.getAttribute("disabled") != "") {
                element.style.opacity = "0.1";
                let command = element.classList[1];

                if (element.classList[0] === "d-pad-button") {
                    if (command === "up") {
                        command = "jump";
                    }
                }

                if (element.classList[0] === "button") {
                    if (command === "b") {
                        command = "attack";
                    } else if (command === "a") {
                        command = "secondary-attack";
                    }
                }
                
                await this.sendCommandToServer(window.currentPlayer, command);
            }
        }
        
        handleTouchEnd(element) {
            if (element.getAttribute("disabled") != "") {
                element.style.opacity = "1";
            }
        }

        connectedCallback() {
            const buttons = this.shadow.querySelectorAll(".button, .d-pad-button");
            buttons.forEach(element => {
                element.addEventListener("touchstart", ({target}) => this.handleTouchStart(target));
                element.addEventListener("touchend", ({target}) => this.handleTouchEnd(target));
            });

            [this.shadow.querySelector(".x"),
            this.shadow.querySelector(".y"),
            this.shadow.querySelector(".down")]
                .forEach(el => el.setAttribute("disabled", ""));
        }
    }
    customElements.define("x-controller", Controller);

    const url = new URL(window.location.href + "players");
    const params = { command: "info" };
    url.search = new URLSearchParams(params);
    const lockedPlayers = await fetch(url, { method: "POST" }).then(res => res.text());
    console.log(lockedPlayers);

    const options = document.querySelectorAll(".option");
    options.forEach(option => {
        if (lockedPlayers.includes(option.dataset.player)) {
            option.style.opacity = "0.3";
            option.style.cursor = "default";
        } else {
            option.addEventListener("click", ({target}) => handleLandingMessage(target));
        }
    });
}
main();