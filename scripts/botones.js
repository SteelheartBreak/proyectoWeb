const botoness = document.querySelectorAll('.cambiar-estilo');

botoness.forEach(boton => {
    boton.addEventListener('mouseover', () => {
        boton.style.backgroundColor = 'black';
        boton.style.color = 'white';
    });

    boton.addEventListener('mouseout', () => {
        boton.style.backgroundColor = 'white';
        boton.style.color = 'black';
    });
});

const botones = document.querySelectorAll('button');
const overlay = document.getElementById('overlay');

botones.forEach((boton, index) => {
  boton.addEventListener('click', () => {
    overlay.classList.add('show-overlay');
    const cuadros = document.querySelectorAll('.cuadro');
    cuadros.forEach(cuadro => cuadro.style.display = 'none');
    cuadros[index].style.display = 'block';
  });
});

overlay.addEventListener('click', () => {
  overlay.classList.remove('show-overlay');
});
