console.log("Index")
listaPizze()

function listaPizze() {
    axios.get("http://localhost:8080/api/pizze").then((res) => {
        console.log(res.data)

        res.data.forEach(p => {
            document.querySelector("#lista").innerHTML += `<div class="col">
            <div class="card">
                <img src="${p.foto}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 id="title" class="card-title">${p.nome}</h5>
                    <p class="card-text" > ${p.prezzo}€</p>
                    <a class="btn btn-primary" href="./show.html?id=${p.id}">Dettaglio <i class="fa-solid fa-circle-info"></i></a>
                    <a class="btn btn-primary" href="./edit.html?id=${p.id}">Modifica <i class="fa-regular fa-pen-to-square"></i> </a>
                    <a class="btn btn-danger" onclick="cancellaPizza(${p.id})">Elimina<i class="fas fa-trash-alt"></i> </a>
                       

                </div>
            </div>`



            console.log(document.querySelector("#lista"))
        });
    }).catch((res) => {
        console.log("not Ok", res)
    })
}


function cancellaPizza(pizzaId) {
    const risposta = confirm("sei sicuro?");

    if (risposta) {
        axios.delete("http://localhost:8080/api/pizze/" + pizzaId).then((res) => {
            //da eseguire se va a buon fine
            //se va a buon fine elimino e ricarico
            console.log("ELIMINAZIONE ok", res);
            location.reload();
        }).catch((err) => {
            //se da errore
            console.log("ELIMINAZIONE NON ok", err);
        })
    }

}
