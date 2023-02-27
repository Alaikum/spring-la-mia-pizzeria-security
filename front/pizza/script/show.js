console.log("Show")
const URLParams = new URLSearchParams(window.location.search);
console.log('id della pizza:', URLParams.get("id"))
const pizzaId = URLParams.get("id");



axios.get("http://localhost:8080/api/pizze/" + pizzaId).then((res) => {
    console.log("ok")
    pizza = res.data


    document.querySelector("#foto_pizza").src = pizza.foto
    document.querySelector("#pizza_nome").innerHTML += pizza.nome
    document.querySelector("#pizza_descrizione").innerHTML += pizza.descrizione
    if (pizza.ingredienti.length > 0) {
        document.querySelector("#if_ingredienti").innerHTML = "Ingredienti:"
        pizza.ingredienti.forEach(i => {
            document.querySelector("#i_nome").innerHTML += i.nome + " "
        });
    } else {
        document.querySelector("#if_ingredienti").innerHTML = "Non ci sono ingredienti"
    }
    document.querySelector("#pizza_prezzo").innerHTML += pizza.prezzo + " €"
    if (pizza.offerte.length > 0) {
        document.querySelector("#pizza_offerte").innerHTML = "Offerte Attive:"
        pizza.offerte.forEach(o => {
            document.querySelector("#dettaglio_offerta").innerHTML+=o.titolo+" "+o.inizioOfferta+" "+o.fineOfferta+"</br>"
            console.log(o.titolo)
            console.log(o.inizioOfferta)
            console.log(o.fineOfferta)

        });

    }else{
        document.querySelector("#pizza_offerte").innerHTML = "Nessuna Offerta Attiva:"
    }


}).catch((res) => {
    console.log("not ok")
})



function cancellaPizza() {
    const risposta = confirm("sei sicuro?");

    if (risposta) {
        axios.delete("http://localhost:8080/api/pizze/" + pizzaId).then((res) => {
            //da eseguire se va a buon fine
            //se va a buon fine elimino e ricarico
            console.log("ELIMINAZIONE ok", res);
            window.location.href = "./index.html";
        }).catch((err) => {
            //se da errore
            console.log("ELIMINAZIONE NON ok", err);
        })
    }

}