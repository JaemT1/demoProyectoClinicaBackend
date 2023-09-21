
    // Función para cargar los estados de Colombia
    function cargarEstados(auth_token) {
    fetch("https://www.universal-tutorial.com/api/states/colombia", {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${auth_token}`,
            "Accept": "application/json"
        }
    })
        .then(response => response.json())
        .then(data => {
            const departamentoSelect = document.getElementById("departamento");

            // Llena el select de departamentos
            for (let i = 0; i < data.length; i++) {
                const option = document.createElement("option");
                option.text = data[i].state_name;
                departamentoSelect.add(option);
            }

            // Habilita el select de departamentos
            departamentoSelect.disabled = false;
        })
        .catch(error => {
            console.error("Error al obtener datos de departamentos:", error);
        });
}

    // Función para cargar las ciudades de un estado seleccionado
    function cargarCiudades(auth_token, estado) {
    fetch(`https://www.universal-tutorial.com/api/cities/${estado}`, {
        method: "GET",
        headers: {
            "Authorization": `Bearer ${auth_token}`,
            "Accept": "application/json"
        }
    })
        .then(response => response.json())
        .then(data => {
            const ciudadSelect = document.getElementById("ciudad");

            // Llena el select de ciudades
            ciudadSelect.innerHTML = "";
            for (let i = 0; i < data.length; i++) {
                const option = document.createElement("option");
                option.text = data[i].city_name;
                ciudadSelect.add(option);
            }

            // Habilita el select de ciudades
            ciudadSelect.disabled = false;
        })
        .catch(error => {
            console.error("Error al obtener datos de ciudades:", error);
        });
}

    // Obtener el token de autenticación
    fetch("https://www.universal-tutorial.com/api/getaccesstoken", {
    method: "GET",
    headers: {
    "Accept": "application/json",
    "api-token": "2XA6kP5Jis8AyQij3yf9lpe_dDT89bQp27Necv_XxUwlc5D8tdoMuW-75YSdmxUTQZ4",
    "user-email": "smoralesd@uqvirtual.edu.co"
}
})
    .then(response => response.json())
    .then(data => {
    const auth_token = data.auth_token;

    // Llamar a la función para cargar los estados de Colombia
    cargarEstados(auth_token);

    // Evento para cargar las ciudades cuando se selecciona un estado
    const departamentoSelect = document.getElementById("departamento");
    departamentoSelect.addEventListener("change", function () {
    const selectedDepartamento = departamentoSelect.value;
    cargarCiudades(auth_token, selectedDepartamento);
});
})
    .catch(error => {
    console.error("Error al obtener el token de autenticación:", error);
});
