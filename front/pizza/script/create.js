console.log("create")



function creaPizza() {

    let pizza = {
        "nome": document.querySelector('#nome').value,
        "descrizione": document.querySelector('#descrizione').value,
        "foto": "https://picsum.photos/200",
        "prezzo": document.querySelector('#prezzo').value,
        "ingredienti": []
    }
    axios.post("http://localhost:8080/api/pizze/create", pizza).then(res => {
        console.log(res, "andata")
        window.location.href = "./index.html";

    }).catch((err) => {
        console.log("errore", err)
    })
};





