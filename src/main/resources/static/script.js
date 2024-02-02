document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll(".nav-link").forEach((link) => {
        if (window.location.href.includes(link.href)) {
            link.classList.add("active");
            link.setAttribute("aria-current", "page");
        }
    });
}, false);

function openModal(modall) {
    modall.style.display = "block"
    modall.classList.add("show")
}

function closeModal(modall) {
    modall.style.display = "none"
    modall.classList.remove("show")
}

function closeAuthorModal() {
    let authorModal = document.getElementById('authorModal');
    closeModal(authorModal);
}

function closePublisherModal() {
    let publisherModal = document.getElementById('publisherModal');
    closeModal(publisherModal);
}

function addAuthor() {
            fetch('/authors/add')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(html => {
                    // Insert the dialog content into the container
                    document.getElementById('dialogContainer').innerHTML = html;
                })
                .then(() => {
                    let authorModal = document.getElementById('authorModal');
                    openModal(authorModal);
                 })
                .catch(error => console.error('Error fetching dialog content:', error));

        }

function addPublisher() {
            fetch('/publishers/add')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(html => {
                    // Insert the dialog content into the container
                    document.getElementById('dialogContainer').innerHTML = html;
                })
                .then(() => {
                    let publisherModal = document.getElementById('publisherModal');
                    openModal(publisherModal);
                })
                .then(openPublisherModal)
                .catch(error => console.error('Error fetching dialog content:', error));

        }

    function onlyNumberKey(evt) {
        let ASCIICode = (evt.which) ? evt.which : evt.keyCode
        if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
            return false;
        return true;
    }


    function openMessageModal() {
        let messageModal = document.getElementById('messageModal');
        let title = document.getElementById('messageModalLabel').innerText;
        let messageModalContent = document.getElementById("messageModalContent");
        if(title.includes("Error")) {
            messageModalContent.classList.add("modal-error");
        } else {
            messageModalContent.classList.add("modal-success");
        }
        openModal(messageModal);
    }

    function closeMessageModal() {
        messageModal.style.display = "none"
        messageModal.classList.remove("show")
    }
document.addEventListener('DOMContentLoaded', function() {
    let message = document.getElementById("message").innerText.trim();
        if (message.length > 0) {
            openMessageModal();
        }
}, false);
