# WEB Programming

Indice:
* Trabajo realizado de a 2 para armar un sitio web con PHP usando librerias de Templates (Smarty, Mustache.JS) y creando una API Rest, como asi tambien, que el mismo se conectará a una DB local con MySQL
Para probar el trabajo se tiene que utilizar Xampp o cualquier otro similar e importar linuxdatabase.sql en la DB local

Web II - TPE
Criterios de corrección
Se evaluará la correcta división de responsabilidades en las clases, no repetición de código, identificadores (nombres de clases, variables, etc) descriptivos, etc.
Los trabajos deben implementar la totalidad de la funcionalidad (ambas entregas) funcionando correctamente, sin tolerancia a bugs. 

User Story sin implementar: -3.
User Story que no anda: -2.
User Story con bug menor: -1.
Poca Prolijidad General: (codigo dificil, mala división de clases, pero respetando MVC): -2.
Sin chequeo de entradas en el servidor (isset && != ""): -1.
No respeta MVC: desaprobado.
PDO inseguro: desaprobado.


Se debe entregar en el mismo repositorio GIT del grupo.
Fecha de entrega: Miércoles 23 de Noviembre.
Defensa TUPAR: El Miércoles 23 de Noviembre
Defensa TUDAI:  El Jueves 24 de Noviembre


Pautas para la Segunda Entrega
Para la segunda entrega, se debe continuar el trabajo de la primera etapa. El objetivo es agregar nueva funcionalidad detallada en forma de historias abajo.

Como usuario, quiero poder filtrar los ítems según una categoría que indique.
Como usuario, quiero ver múltiples imágenes en cada ítem.
Como administrador de infraestructura, quiero que el sitio genere su base de datos con datos iniciales al momento de cargarlo por primera vez. 
Como administrador de infraestructura, quiero poder cargar los datos de acceso a la base desde un formulario al acceder por primera vez.
Como dueño del sitio, quiero poder loguearme para administrar el sitio. 
Como dueño del sitio, quiero que sólo los administradores logueados puedan modificar las categorías, items, imágenes y comentarios.
Como administrador del sitio, quiero poder subir/eliminar muchas imágenes a un mismo ítem.
Como usuario, quiero poder ver el sitio sin estar logueado.
Como usuario, quiero poder registrarme en el sitio generando mi nombre de usuario (mail) y contraseña. Al registrar loguea automaticamente.
Como usuario, quiero poder postear comentarios en los ítems del sitio asignándoles un puntaje de 1 a 5.
Como dueño del sitio, quiero que sólo los usuarios logueados puedan comentar.
Como arquitecto del sistema, quiero que todo el sistema de comentarios funcione por medio de una API REST. (Sin mezlcar HTML y Javascript)
Como administrador del sitio, quiero poder borrar comentarios.
Como administrador del sitio, quiero poder asignar o quitar permisos de administración a cualquier usuario.
Como usuario, quiero que los comentarios se actualicen en tiempo real, con una demora de 2 segundos.

Aclaraciones
Aca agregaremos comentarios en base a las consultas que recibamos.
Los comentarios se deben poder crear y eliminar. No es necesario poder modificarlos.

Pautas para la Primer Entrega

Tecnologias:
PHP
MySQL
Smarty

La primer entrega del trabajo práctico especial debe cubrir todo el alcance acordado en la planilla de trabajos prácticos especiales. Se debe dividir la funcionalidad siguiendo los principios del Model View Controller.

Planilla TUPAR
Planilla TUDAI

Requerimientos de Base de Datos
Debe contar con una base de datos (acorde al tipo de página a implementar). La base de datos debe tener al menos una relación 1-N. Por ejemplo, debe haber una categoría, y un nivel de ítem que es agrupado en la categoría. Las columnas de cada tabla deben ser acordes a la lógica de su página.

A continuación se dan dos ejemplos sugeridos que se pueden aplicar a muchas páginas, otras opciones son bienvenidas.

Comercial
Debe contar con las tablas Producto y Categoría. Un producto puede pertenecer sólo a una categoría.

Novedades
Debe contar con las tablas Noticia y Categoría. Una noticia puede pertenecer sólo a una categoría.

Otras
Funciones en salas de cine, animales en reinos animales, películas según el estudio de filmación (o género), materias con su profesor a cargo (uno solo), etc.


Requerimientos de la Página WEB
La navegación de la página debe ser utilizando Ajax.
Todos los HTML deben mostrarse utilizando Smarty.
Todas las acciones realizadas en la página deben estar manejadas utilizando el patrón MVC.
Debe tener 2 paginas donde se muestre el contenido dinámicamente generado de la base de datos. 
Listado de Items (Noticias/Producto/…)
Ver Item (Noticia/Producto/...)
En ambas páginas se debe mostrar la categoría a la que pertenece.

Administrador de Datos
Además de estas páginas, se debe crear el ABM para administrar los datos:
Administrar Noticias o Productos.
Lista de Items (Noticias/Productos/…)
Agregar Items (Noticias/Productos/…)
Eliminar Items (Noticias/Productos/…)
Editar Items (Noticias/Productos/…)
Notas: 
Al agregar Items (Noticias/Productos/…) se debe poder elegir la categoría a la que pertenecen utilizando un dropdown.
Al agregar Items (Noticias/Productos/…) se debe permitir la subida de imágenes utilizando Ajax.

Administrar Categorias
Lista de Categorias
Agregar Categorias
Eliminar Categorias
Editar Categorias.

Se debe entregar el link repositorio GIT del grupo con el código fuente
Fecha de entrega: Miércoles 19 de Octubre.
Def
ensa TUPAR: El Miércoles 19 de Octubre.
Defensa TUDAI: El Jueves 20 de Octubre.
