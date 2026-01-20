let token = localStorage.getItem("token");

window.onload = function () {
  updateUIState();
};

function updateUIState() {
  const protectedBtns = document.querySelectorAll(".protected");

  if (token) {
    protectedBtns.forEach((btn) => (btn.style.display = "inline-block"));
  } else {
    protectedBtns.forEach((btn) => (btn.style.display = "none"));
  }
}

function toggle(id) {
  const x = document.getElementById(id);
  document.querySelectorAll(".content-section").forEach((div) => {
    if (div.id !== id) div.style.display = "none";
  });
  x.style.display = x.style.display === "none" ? "block" : "none";
}

async function register() {
  try {
    const username = document.getElementById("regUsername").value;
    const password = document.getElementById("regPassword").value;
    const response = await fetch("http://localhost:8080/api/auth/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });
    const data = await response.text();
    document.getElementById("response").innerText = data;
  } catch (error) {
    document.getElementById("response").innerText = error.message;
  }
}

async function login() {
  try {
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;
    const response = await fetch("http://localhost:8080/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    if (!response.ok) throw new Error("Login failed");

    const data = await response.text();
    token = data;
    localStorage.setItem("token", token);

    document.getElementById("response").innerText = "Login Successful";
    document.getElementById("loginSection").style.display = "none";

    document.getElementById("loginUsername").value = "";
    document.getElementById("loginPassword").value = "";

    updateUIState();
  } catch (error) {
    document.getElementById("response").innerText = error.message;
  }
}

function logout() {
  token = null;
  localStorage.removeItem("token");

  document.querySelectorAll("input").forEach((input) => (input.value = ""));

  const responseIds = [
    "response",
    "createResponse",
    "studentResponse",
    "assignFeeResponse",
    "assignedFeeResponse",
    "payFeeResponse",
    "feePaymentResponse",
    "duesResponse",
    "paymentsResponse",
    "allDuesResponse",
  ];
  responseIds.forEach((id) => {
    const el = document.getElementById(id);
    if (el) el.innerText = "";
  });

  document
    .querySelectorAll(".content-section")
    .forEach((el) => (el.style.display = "none"));

  updateUIState();

  alert("Logged out");
}

async function createStudent() {
  try {
    if (!token) throw new Error("Not logged in");

    const name = document.getElementById("createName").value;
    const mobileNo = document.getElementById("createMobile").value;

    const response = await fetch("http://localhost:8080/api/student", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, mobileNo }),
    });

    const data = await response.text();
    document.getElementById("createResponse").innerText = data;
  } catch (error) {
    document.getElementById("createResponse").innerText = error.message;
  }
}

async function getStudentById() {
  try {
    if (!token) throw new Error("Not logged in");

    const studentId = document.getElementById("studentGet").value;
    const response = await fetch(
      `http://localhost:8080/api/student/${studentId}`,
      {
        method: "GET",
        headers: { Authorization: "Bearer " + token },
      },
    );
    const data = await response.text();
    document.getElementById("studentResponse").innerText = data;
  } catch (error) {
    document.getElementById("studentResponse").innerText = error.message;
  }
}

async function assignFee() {
  try {
    if (!token) throw new Error("Not logged in");

    const studentId = document.getElementById("assignStudentId").value;
    const feeType = document.getElementById("feeTypeSelect").value;
    const assignedAmount = document.getElementById("assignedAmount").value;

    const response = await fetch("http://localhost:8080/api/fee/assign-fee", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        studentId: parseInt(studentId),
        feeType: feeType,
        assignedAmount: parseFloat(assignedAmount),
      }),
    });

    const data = await response.text();
    document.getElementById("assignFeeResponse").innerText = data;
  } catch (error) {
    document.getElementById("assignFeeResponse").innerText = error.message;
  }
}

async function getAssignedFeeById() {
  try {
    if (!token) throw new Error("Not logged in");

    const assignedFeeId = document.getElementById("assignedFeeGet").value;
    const response = await fetch(
      `http://localhost:8080/api/fee/get-fee/${assignedFeeId}`,
      {
        method: "GET",
        headers: { Authorization: "Bearer " + token },
      },
    );
    const data = await response.text();
    document.getElementById("assignedFeeResponse").innerText = data;
  } catch (error) {
    document.getElementById("assignedFeeResponse").innerText = error.message;
  }
}

async function payFee() {
  try {
    if (!token) throw new Error("Not logged in");

    const feeAssignmentId = document.getElementById("payFeeAssignmentId").value;
    const paidAmount = document.getElementById("paidAmount").value;

    const response = await fetch("http://localhost:8080/api/fee/pay", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ feeAssignmentId, paidAmount }),
    });

    const data = await response.text();
    document.getElementById("payFeeResponse").innerText = data;
  } catch (error) {
    document.getElementById("payFeeResponse").innerText = error.message;
  }
}

async function getFeePaymentById() {
  try {
    if (!token) throw new Error("Not logged in");

    const paymentId = document.getElementById("feePaymentGet").value;
    const response = await fetch(
      `http://localhost:8080/api/fee/payment/${paymentId}`,
      {
        method: "GET",
        headers: { Authorization: "Bearer " + token },
      },
    );
    const data = await response.text();
    document.getElementById("feePaymentResponse").innerText = data;
  } catch (error) {
    document.getElementById("feePaymentResponse").innerText = error.message;
  }
}

async function getPendingDues() {
  try {
    if (!token) throw new Error("Not logged in");

    const studentId = document.getElementById("duesStudentId").value;
    const response = await fetch(
      `http://localhost:8080/api/fee/dues/${studentId}`,
      {
        method: "GET",
        headers: { Authorization: "Bearer " + token },
      },
    );
    const data = await response.text();
    document.getElementById("duesResponse").innerText = data;
  } catch (error) {
    document.getElementById("duesResponse").innerText = error.message;
  }
}

async function getPaymentDetails() {
  try {
    if (!token) throw new Error("Not logged in");

    const studentId = document.getElementById("paymentsStudentId").value;
    const response = await fetch(
      `http://localhost:8080/api/fee/payments/${studentId}`,
      {
        method: "GET",
        headers: { Authorization: "Bearer " + token },
      },
    );
    const data = await response.text();
    document.getElementById("paymentsResponse").innerText = data;
  } catch (error) {
    document.getElementById("paymentsResponse").innerText = error.message;
  }
}

async function getAllDues() {
  try {
    if (!token) throw new Error("Not logged in");

    const response = await fetch(
      "http://localhost:8080/api/report/getAllDues",
      {
        method: "GET",
        headers: { Authorization: "Bearer " + token },
      },
    );
    const data = await response.text();
    document.getElementById("allDuesResponse").innerText = data;
  } catch (error) {
    document.getElementById("allDuesResponse").innerText = error.message;
  }
}
