const botones = document.querySelectorAll('.cambiar-estilo');

botones.forEach(boton => {
    boton.addEventListener('mouseover', () => {
        boton.style.backgroundColor = 'black';
        boton.style.color = 'white';
    });

    boton.addEventListener('mouseout', () => {
        boton.style.backgroundColor = 'white';
        boton.style.color = 'black';
    });
});

const contenedor = document.querySelector('.cambiar-estilo2');

contenedor.addEventListener('mouseover', () => {
    contenedor.style.backgroundColor = 'gray';
    contenedor.style.color = 'white';
});
contenedor.addEventListener('mouseout', () => {
    contenedor.style.backgroundColor = 'white';
    contenedor.style.color = 'black';
});