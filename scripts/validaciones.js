window.onload = function() {
    const formulario = document.querySelector("#formulario");

    formulario.addEventListener("submit", function(event) {
        event.preventDefault(); // Evitar el envío del formulario por defecto
        const nombres = document.querySelector("#nombres").value;
        const apellidos = document.querySelector("#apellidos").value;
        const correo = document.querySelector("#correo").value;
        const comentario = document.querySelector("#comentario").value;

        const errorDiv = document.querySelector("#errorDiv");
        errorDiv.style.display = "none"; // Ocultar el mensaje de error al principio

        let existeError = false;
        let mensajeError = "";

        if (!nombres || !apellidos || !correo || !comentario) {
            existeError = true;
            mensajeError = "Por favor, complete todos los campos.";
        } else {
            if (!validarNombresApellidos(nombres) || !validarNombresApellidos(apellidos)) {
                existeError = true;
                mensajeError = "Los nombres y apellidos solo deben contener letras.";
            } else if (!validarCorreo(correo)) {
                existeError = true;
                mensajeError = "Ingrese un correo electrónico válido.";
            } else if (comentario.length > 150) {
                existeError = true;
                mensajeError = "El comentario no debe superar los 150 caracteres.";
            }
        }

        if (existeError) {
            mostrarError(errorDiv, mensajeError);
        } else {
            // Aquí puedes realizar acciones adicionales si todos los campos son válidos
            mostrarError(errorDiv, ""); // Limpiar cualquier mensaje de error existente
            formulario.submit(); // Enviar el formulario si todo está bien
        }
    });

    function validarNombresApellidos(texto) {
        return /^[a-zA-Z\s]+$/.test(texto);
    }

    function validarCorreo(correo) {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(correo);
    }

    function mostrarError(elemento, mensaje) {
        elemento.textContent = mensaje;
        elemento.style.display = mensaje ? "block" : "none"; // Mostrar u ocultar el mensaje de error
    }
};
