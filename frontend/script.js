let token = localStorage.getItem("token");

function toggle(id) {
  const x = document.getElementById(id);
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
  } catch (error) {
    document.getElementById("response").innerText = error.message;
  }
}

async function createStudent() {
  try {
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("createResponse").innerText = "Not logged in";
      return;
    }

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
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("studentResponse").innerText = "Not logged in";
      return;
    }

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

async function createFeeType() {
  try {
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("createFeeTypeResponse").innerText =
        "Not logged in";
      return;
    }

    const name = document.getElementById("feeTypeName").value;
    const description = document.getElementById("feeTypeDescription").value;

    const response = await fetch("http://localhost:8080/api/feetype", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, description }),
    });

    const data = await response.text();
    document.getElementById("createFeeTypeResponse").innerText = data;
  } catch (error) {
    document.getElementById("createFeeTypeResponse").innerText = error.message;
  }
}

async function getFeeTypeById() {
  try {
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("feeTypeResponse").innerText = "Not logged in";
      return;
    }

    const feeTypeId = document.getElementById("feeTypeGet").value;
    const response = await fetch(
      `http://localhost:8080/api/feetype/${feeTypeId}`,
      {
        method: "GET",
        headers: { Authorization: "Bearer " + token },
      },
    );
    const data = await response.text();
    document.getElementById("feeTypeResponse").innerText = data;
  } catch (error) {
    document.getElementById("feeTypeResponse").innerText = error.message;
  }
}

async function assignFee() {
  try {
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("assignFeeResponse").innerText = "Not logged in";
      return;
    }

    const studentId = document.getElementById("assignStudentId").value;
    const feeTypeId = document.getElementById("assignFeeTypeId").value;
    const assignedAmount = document.getElementById("assignedAmount").value;

    const response = await fetch("http://localhost:8080/api/fee/assign-fee", {
      method: "POST",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ studentId, feeTypeId, assignedAmount }),
    });

    const data = await response.text();
    document.getElementById("assignFeeResponse").innerText = data;
  } catch (error) {
    document.getElementById("assignFeeResponse").innerText = error.message;
  }
}

async function getAssignedFeeById() {
  try {
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("assignedFeeResponse").innerText =
        "Not logged in";
      return;
    }

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
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("payFeeResponse").innerText = "Not logged in";
      return;
    }

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
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("feePaymentResponse").innerText = "Not logged in";
      return;
    }

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
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("duesResponse").innerText = "Not logged in";
      return;
    }

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
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("paymentsResponse").innerText = "Not logged in";
      return;
    }

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

function logout() {
  token = null;
  localStorage.removeItem("token");
  alert("logged out")
  document.getElementById("studentResponse").innerText = "";
  document.getElementById("createResponse").innerText = "";
  document.getElementById("createFeeTypeResponse").innerText = "";
  document.getElementById("feeTypeResponse").innerText = "";
  document.getElementById("assignFeeResponse").innerText = "";
  document.getElementById("assignedFeeResponse").innerText = "";
  document.getElementById("payFeeResponse").innerText = "";
  document.getElementById("feePaymentResponse").innerText = "";
  document.getElementById("duesResponse").innerText = "";
  document.getElementById("paymentsResponse").innerText = "";

  document
    .querySelectorAll(".content-section")
    .forEach((el) => (el.style.display = "none"));
}

async function getPendingDues() {
  try {
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("duesResponse").innerText = "Not logged in";
      return;
    }

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
    if (!token) token = localStorage.getItem("token");
    if (!token) {
      document.getElementById("paymentsResponse").innerText = "Not logged in";
      return;
    }

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