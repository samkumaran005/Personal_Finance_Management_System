document.addEventListener('DOMContentLoaded', function () {
    // ===================== EXPENSE HANDLING =====================
    const expenseForm = document.getElementById('expenseForm');
    const expenseCategoryTotals = {
        Food: 0,
        Bills: 0,
        Transportation: 0,
        Subscriptions: 0,
        Shopping: 0
    };

    if (expenseForm) {
        expenseForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const description = document.getElementById('description').value;
            const amount = parseFloat(document.getElementById('amount').value);
            const date = document.getElementById('date').value;
            const category = document.getElementById('category').value;

            const expense = {
                id: Date.now(),
                description,
                amount,
                date,
                category,
                type: 'expense'
            };

            saveData('expenses', expense);
            addRowToTable('expensesTable', expense);
            updateExpenseTotals();
            expenseForm.reset();
            updateDashboard();
        });

        loadData('expenses', 'expensesTable');
        updateExpenseTotals();
    }

    // ===================== INCOME HANDLING =====================
    const incomeForm = document.getElementById('incomeForm');
    const incomeCategoryTotals = {
        Salary: 0,
        Bonus: 0,
        Freelance: 0,
        Investments: 0,
        Other: 0
    };

    if (incomeForm) {
        incomeForm.addEventListener('submit', function (e) {
            e.preventDefault();

            const description = document.getElementById('description').value;
            const amount = parseFloat(document.getElementById('amount').value);
            const date = document.getElementById('date').value;
            const category = document.getElementById('category').value;

            const income = {
                id: Date.now(),
                description,
                amount,
                date,
                category,
                type: 'income'
            };

            saveData('incomes', income);
            addRowToTable('incomeTable', income);
            updateIncomeTotals();
            incomeForm.reset();
            updateDashboard();
        });

        loadData('incomes', 'incomeTable');
        updateIncomeTotals();
    }

    // ===================== COMMON FUNCTIONS =====================
    function saveData(key, item) {
        const data = JSON.parse(localStorage.getItem(key)) || [];
        data.push(item);
        localStorage.setItem(key, JSON.stringify(data));
    }

    function loadData(key, tableId) {
        const tableBody = document.querySelector(`#${tableId} tbody`);
        const data = JSON.parse(localStorage.getItem(key)) || [];
        data.forEach(item => addRowToTable(tableId, item));
    }

    function addRowToTable(tableId, item) {
        const tableBody = document.querySelector(`#${tableId} tbody`);
        if (!tableBody) return;

        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${formatDate(item.date)}</td>
            <td>$${item.amount.toFixed(2)}</td>
            <td>${item.type}</td>
            <td>${item.category}</td>
            <td>${item.description}</td>
        `;
        tableBody.appendChild(row);
    }

    // ===================== EXPENSE TOTALS =====================
    function updateExpenseTotals() {
        const expenses = JSON.parse(localStorage.getItem('expenses')) || [];
        let totalExpenses = 0;

        // Reset category totals
        for (let cat in expenseCategoryTotals) expenseCategoryTotals[cat] = 0;

        expenses.forEach(e => {
            totalExpenses += e.amount;
            if (expenseCategoryTotals[e.category] !== undefined) {
                expenseCategoryTotals[e.category] += e.amount;
            }
        });

        // Update category total UI
        for (let cat in expenseCategoryTotals) {
            const el = document.getElementById(`${cat.toLowerCase()}-total`);
            if (el) el.textContent = `${cat}: $${expenseCategoryTotals[cat].toFixed(2)}`;
        }

        const totalEl = document.getElementById('total-expenses');
        if (totalEl) totalEl.textContent = `Total Expenses: $${totalExpenses.toFixed(2)}`;
    }

    // ===================== INCOME TOTALS =====================
    function updateIncomeTotals() {
        const incomes = JSON.parse(localStorage.getItem('incomes')) || [];
        let totalIncome = 0;

        // Reset category totals
        for (let cat in incomeCategoryTotals) incomeCategoryTotals[cat] = 0;

        incomes.forEach(i => {
            totalIncome += i.amount;
            if (incomeCategoryTotals[i.category] !== undefined) {
                incomeCategoryTotals[i.category] += i.amount;
            }
        });

        // Update category total UI
        for (let cat in incomeCategoryTotals) {
            const el = document.getElementById(`${cat.toLowerCase()}-total`);
            if (el) el.textContent = `${cat}: $${incomeCategoryTotals[cat].toFixed(2)}`;
        }

        const totalEl = document.getElementById('total-income');
        if (totalEl) totalEl.textContent = `Total Income: $${totalIncome.toFixed(2)}`;
    }

    // ===================== DASHBOARD =====================
    function updateDashboard() {
        const incomes = JSON.parse(localStorage.getItem('incomes')) || [];
        const expenses = JSON.parse(localStorage.getItem('expenses')) || [];

        const totalIncome = incomes.reduce((sum, i) => sum + i.amount, 0);
        const totalExpenses = expenses.reduce((sum, e) => sum + e.amount, 0);

        // Top spending category
        const categoryTotals = {};
        expenses.forEach(e => {
            categoryTotals[e.category] = (categoryTotals[e.category] || 0) + e.amount;
        });
        const topCategory = Object.entries(categoryTotals).sort((a, b) => b[1] - a[1])[0]?.[0] || 'N/A';

        // Update summary
        const totalIncomeEl = document.getElementById('total-income');
        const totalExpensesEl = document.getElementById('total-expenses');
        const topCategoryEl = document.getElementById('top-category');

        if (totalIncomeEl) totalIncomeEl.textContent = `Total Income: $${totalIncome.toFixed(2)}`;
        if (totalExpensesEl) totalExpensesEl.textContent = `Total Expenses: $${totalExpenses.toFixed(2)}`;
        if (topCategoryEl) topCategoryEl.textContent = `Top Spending Category: ${topCategory}`;

        // Merge transactions table
        const allTransactions = [...incomes, ...expenses].sort((a, b) => new Date(b.date) - new Date(a.date));
        const tableBody = document.querySelector('#transactionsTable tbody');
        if (!tableBody) return;
        tableBody.innerHTML = '';

        allTransactions.forEach(t => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${formatDate(t.date)}</td>
                <td>$${t.amount.toFixed(2)}</td>
                <td>${t.type}</td>
                <td>${t.category}</td>
                <td>${t.description}</td>
            `;
            tableBody.appendChild(row);
        });
    }

    function formatDate(dateString) {
        const date = new Date(dateString);
        if (isNaN(date)) return dateString;
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const year = date.getFullYear();
        return `${month}-${day}-${year}`;
    }

    // Initial dashboard update
    updateDashboard();
	document.getElementById('resetStorage').addEventListener('click', () => {
	    localStorage.clear(); // Or removeItem('expenses') and removeItem('incomes')
	    location.reload();    // Reload the page to reflect changes
	});

});
