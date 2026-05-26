const USER_API = "http://localhost:8080/users";
const TRANSACTION_API = "http://localhost:8080/transaction";

async function createUser() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;

    const data = { name, email };

    try {
        const res = await fetch(USER_API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        const result = await res.json();
        alert("User Created!");
        console.log(result);

        loadUsers(); // refresh dropdown
    } catch (err) {
        console.error(err);
    }
}


async function loadUsers() {
    const res = await fetch(USER_API);
    const result = await res.json();

    const users = result.data;

    const select = document.getElementById("userSelect");
    select.innerHTML = "";

    users.forEach(user => {
        const option = document.createElement("option");
        option.value = user.id;
        option.textContent = user.name;
        select.appendChild(option);
    });
}


async function addTransaction() {
    const data = {
        amount: document.getElementById("amount").value,
        type: document.getElementById("type").value,
        category: document.getElementById("category").value,
        date: document.getElementById("date").value,
        userId: document.getElementById("userSelect").value
    };

    try {
        const res = await fetch(TRANSACTION_API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        const result = await res.json();
        alert("Transaction Added!");
        console.log(result);

        loadTransactions();
        getBalance();
    } catch (err) {
        console.error(err);
    }
}


async function loadTransactions() {
    const userId = document.getElementById("userSelect").value;

    const res = await fetch(`${TRANSACTION_API}/user/${userId}`);
    const result = await res.json();

    const list = document.getElementById("transactionList");
    list.innerHTML = "";

    result.data.forEach(t => {
        const li = document.createElement("li");
        li.textContent = `${t.type} - ₹${t.amount} - ${t.category}`;
        list.appendChild(li);
    });
}


async function getBalance() {
    const userId = document.getElementById("userSelect").value;

    const res = await fetch(`${TRANSACTION_API}/balance/${userId}`);
    const result = await res.json();

    document.getElementById("balance").innerText =
        "Balance: ₹" + result.data;
}


window.onload = function () {
    loadUsers();

    document.getElementById("userSelect").addEventListener("change", () => {
        loadTransactions();
        getBalance();
    });
};