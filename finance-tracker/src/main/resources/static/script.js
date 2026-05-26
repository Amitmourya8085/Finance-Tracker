const USER_API = "http://localhost:8080/users";
const TRANSACTION_API = "http://localhost:8080/transaction";


window.createUser = async function () {
    try {
        const name = document.getElementById("name").value;
        const email = document.getElementById("email").value;

        if (!name || !email) {
            alert("Please enter name and email");
            return;
        }

        const res = await fetch(USER_API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name, email })
        });

        if (!res.ok) {
            const err = await res.text();
            throw new Error(err);
        }

        alert("User Created ");

        document.getElementById("name").value = "";
        document.getElementById("email").value = "";

        loadUsers();

    } catch (err) {
        console.error(err);
        alert("Error creating user ");
    }
};

async function loadUsers() {
    try {
        const res = await fetch(USER_API);
        const result = await res.json();

        const users = result.data || result;

        const select = document.getElementById("userSelect");
        select.innerHTML = "";

        users.forEach(user => {
            const option = document.createElement("option");
            option.value = user.id;
            option.textContent = user.name;
            select.appendChild(option);
        });

        // Auto load data for first user
        if (users.length > 0) {
            loadTransactions();
            getBalance();
        }

    } catch (err) {
        console.error(err);
        alert("Failed to load users ");
    }
}


window.addTransaction = async function () {
    try {
        const amount = document.getElementById("amount").value;
        const type = document.getElementById("type").value;
        const category = document.getElementById("category").value;
        const date = document.getElementById("date").value;
        const userId = document.getElementById("userSelect").value;

        if (!amount || !type || !category || !date || !userId) {
            alert("Fill all fields ️");
            return;
        }

        // IMPORTANT: date must be yyyy-MM-dd
        const data = {
            amount: parseFloat(amount),
            type,
            category,
            date, // must come from <input type="date">
            userId: parseInt(userId)
        };

        console.log("Sending:", data);

        const res = await fetch(TRANSACTION_API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!res.ok) {
            const err = await res.text();
            throw new Error(err);
        }

        const result = await res.json().catch(() => null);
        console.log("Response:", result);

        alert("Transaction Added ");

        // clear form
        document.getElementById("amount").value = "";
        document.getElementById("category").value = "";
        document.getElementById("date").value = "";

        loadTransactions();
        getBalance();

    } catch (err) {
        console.error(err);
        alert("Error adding transaction " + err.message);
    }
};

async function loadTransactions() {
    try {
        const userId = document.getElementById("userSelect").value;

        if (!userId) return;

        const res = await fetch(`${TRANSACTION_API}/user/${userId}`);

        if (!res.ok) {
            throw new Error("Failed to fetch transactions");
        }

        const result = await res.json();
        const transactions = result.data || result;

        const list = document.getElementById("transactionList");
        list.innerHTML = "";

        if (transactions.length === 0) {
            list.innerHTML = "<li>No transactions found</li>";
            return;
        }

        transactions.forEach(t => {
            const li = document.createElement("li");
            li.innerText = `${t.type} - ₹${t.amount} - ${t.category}`;
            list.appendChild(li);
        });

    } catch (err) {
        console.error(err);
        alert("Error loading transactions ");
    }
}


async function getBalance() {
    try {
        const userId = document.getElementById("userSelect").value;

        if (!userId) return;

        const res = await fetch(`${TRANSACTION_API}/balance/${userId}`);

        if (!res.ok) {
            throw new Error("Failed to fetch balance");
        }

        const result = await res.json();
        const balance = result.data ?? result;

        document.getElementById("balance").innerText =
            "Balance: ₹" + balance;

    } catch (err) {
        console.error(err);
        alert("Error fetching balance ");
    }
}

window.onload = function () {
    loadUsers();

    document.getElementById("userSelect").addEventListener("change", () => {
        loadTransactions();
        getBalance();
    });
};