/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/*
 * el siguiente codigo permite implementar funcionalidad a la tabla de la
 * pagina listado-de-asignaturas.jsp, permite ordenar por columna, buscar registros,
 * y mostrar un numero limitado de registros por  pagina
 */
let dataTable;
let dataTableIsInitialized = false;

const dataTableOptions = {
    "columns": [
        null, // Columna 1 (ID)
        null, // Columna 2 (Titulo)
        null, // Columna 3 (Programa Academico)
        null, // Columna 4 (Docente)
        {"orderable": false}, // Columna 5 (Operaciones)
        {"orderable": false}   // Columna 6 (Operaciones)
    ],
    pageLength: 5,
    destroy: true,
    language: {
        lengthMenu: "Mostrar _MENU_ registros por página",
        zeroRecords: "Ningún registro encontrado",

        info: "Mostrando de _START_ a _END_ de un total de _TOTAL_ registros",
        infoEmpty: "Ninguna asignatura encontrada",

        infoFiltered: "(filtrados desde _MAX_ registros totales)",
        search: "Buscar:",

        loadingRecords: "Cargando...",
        paginate: {
            first: "Primero",
            last: "Último",
            next: "Siguiente",
            previous: "Anterior"
        }
    }
};


const initDataTable = async () => {
    if (dataTableIsInitialized) {
        dataTable.destroy();
    }

    dataTable = $("#miTabla").DataTable(dataTableOptions);

    dataTableIsInitialized = true;
};

window.addEventListener("load", async () => {
    await initDataTable();
});
