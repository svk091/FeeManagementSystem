const API_BASE = "http://localhost:8080/api";

function authHeaders(json = true) {
  const headers = { Authorization: "Bearer " + token };
  if (json) headers["Content-Type"] = "application/json";
  return headers;
}

function ensureAuth() {
  if (!token) throw new Error("Not logged in");
}

function clearResponses() {
  document
    .querySelectorAll(
      "#response,#createResponse,#studentResponse,#assignFeeResponse," +
        "#payFeeResponse,#feePaymentResponse,#duesResponse,#paymentsResponse," +
        "#allDuesResponse,#allPaymentsResponse,#assignmentPaymentsResponse",
    )
    .forEach((d) => d && (d.innerHTML = ""));
}

function renderTable(containerId, data) {
  const container = document.getElementById(containerId);
  container.innerHTML = "";

  if (!data || (Array.isArray(data) && data.length === 0)) {
    container.innerText = "No data";
    return;
  }

  const rows = Array.isArray(data) ? data : [data];
  const table = document.createElement("table");
  table.border = "1";
  table.style.borderCollapse = "collapse";
  table.style.width = "100%";

  const thead = document.createElement("thead");
  const trHead = document.createElement("tr");

  Object.keys(rows[0]).forEach((h) => {
    const th = document.createElement("th");
    th.innerText = h;
    th.style.padding = "5px";
    trHead.appendChild(th);
  });

  thead.appendChild(trHead);
  table.appendChild(thead);

  const tbody = document.createElement("tbody");
  rows.forEach((row) => {
    const tr = document.createElement("tr");
    Object.values(row).forEach((v) => {
      const td = document.createElement("td");
      td.innerText =
        typeof v === "string" && v.match(/^\d{4}-\d{2}-\d{2}T/) ?
          new Date(v).toLocaleString()
        : v;
      td.style.padding = "5px";
      tr.appendChild(td);
    });
    tbody.appendChild(tr);
  });

  table.appendChild(tbody);
  container.appendChild(table);
}
