let token = localStorage.getItem("token");

window.onload = function () {
  updateUIState();
};

function updateUIState() {
  const protectedBtns = document.querySelectorAll(".protected");
  const authBtns = document.querySelectorAll(".auth-btn");

  if (token) {
    protectedBtns.forEach((b) => (b.style.display = "inline-block"));
    authBtns.forEach((b) => (b.style.display = "none"));
  } else {
    protectedBtns.forEach((b) => (b.style.display = "none"));
    authBtns.forEach((b) => (b.style.display = "inline-block"));
  }
}

function toggle(id) {
  document.querySelectorAll(".content-section").forEach((s) => {
    s.style.display = "none";
  });

  document.querySelectorAll(".toggle-btn").forEach((b) => {
    b.classList.remove("active");
  });

  document.getElementById(id).style.display = "block";
  event.target.classList.add("active");
}

function showCategory(categoryId, btn) {
  document
    .querySelectorAll(".category")
    .forEach((c) => (c.style.display = "none"));
  document
    .querySelectorAll(".content-section")
    .forEach((s) => (s.style.display = "none"));
  document
    .querySelectorAll(".category-btn")
    .forEach((b) => b.classList.remove("active"));

  clearResponses();
  document.getElementById(categoryId).style.display = "block";
  btn.classList.add("active");
}

async function register() {
  const username = regUsername.value;
  const password = regPassword.value;

  const res = await fetch(`${API_BASE}/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password }),
  });

  response.innerText = await res.text();
}

async function login() {
  const res = await fetch(`${API_BASE}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      username: loginUsername.value,
      password: loginPassword.value,
    }),
  });

  if (!res.ok) throw new Error("Login failed");

  token = await res.text();
  localStorage.setItem("token", token);
  response.innerText = "Login Successful";
  loginSection.style.display = "none";

  updateUIState();
}

function logout() {
  token = null;
  localStorage.removeItem("token");

  document.querySelectorAll("input").forEach((i) => (i.value = ""));
  document
    .querySelectorAll(".content-section,.category")
    .forEach((e) => (e.style.display = "none"));
  document
    .querySelectorAll(".toggle-btn")
    .forEach((b) => b.classList.remove("active"));

  clearResponses();
  updateUIState();
  alert("Logged out");
}

async function createStudent() {
  ensureAuth();

  const res = await fetch(`${API_BASE}/student`, {
    method: "POST",
    headers: authHeaders(),
    body: JSON.stringify({
      name: createName.value,
      mobileNo: createMobile.value,
    }),
  });

  renderTable("createResponse", await res.json());
}

async function getStudentById() {
  ensureAuth();

  const res = await fetch(`${API_BASE}/student/${studentGet.value}`, {
    headers: authHeaders(false),
  });

  renderTable("studentResponse", await res.json());
}

async function assignFee() {
  ensureAuth();

  const res = await fetch(`${API_BASE}/fee/assign-fee`, {
    method: "POST",
    headers: authHeaders(),
    body: JSON.stringify({
      studentId: +assignStudentId.value,
      feeType: feeTypeSelect.value,
      assignedAmount: +assignedAmount.value,
    }),
  });

  renderTable("assignFeeResponse", await res.json());
}

async function payFee() {
  ensureAuth();

  const res = await fetch(`${API_BASE}/fee/pay`, {
    method: "POST",
    headers: authHeaders(),
    body: JSON.stringify({
      feeAssignmentId: +payFeeAssignmentId.value,
      paidAmount: +paidAmount.value,
    }),
  });

  const text = await res.text();
  try {
    renderTable("payFeeResponse", JSON.parse(text));
  } catch {
    payFeeResponse.innerText = text || "Payment successful";
  }
}

async function getFeePaymentById() {
  ensureAuth();

  const res = await fetch(`${API_BASE}/fee/payment/${feePaymentGet.value}`, {
    headers: authHeaders(false),
  });

  renderTable("feePaymentResponse", await res.json());
}

async function getPendingDues() {
  ensureAuth();

  const res = await fetch(`${API_BASE}/fee/dues/${duesStudentId.value}`, {
    headers: authHeaders(false),
  });

  renderTable("duesResponse", await res.json());
}

async function getPaymentDetails() {
  ensureAuth();

  const res = await fetch(
    `${API_BASE}/fee/payments/${paymentsStudentId.value}`,
    {
      headers: authHeaders(false),
    },
  );

  renderTable("paymentsResponse", await res.json());
}

async function getAllDues() {
  ensureAuth();

  const res = await fetch(`${API_BASE}/report/getAllDues`, {
    headers: authHeaders(false),
  });

  renderTable("allDuesResponse", await res.json());
}

async function getAllPayments() {
  ensureAuth();

  const res = await fetch(`${API_BASE}/report/getAllPayments`, {
    headers: authHeaders(false),
  });

  renderTable("allPaymentsResponse", await res.json());
}

async function getPaymentsByFeeAssignment() {
  ensureAuth();

  const res = await fetch(
    `${API_BASE}/fee/${assignmentPaymentsId.value}/payments`,
    { headers: authHeaders(false) },
  );

  renderTable("assignmentPaymentsResponse", await res.json());
}
