# Arquitecturas WEB: Trabajo Especial

Indice:
* Trabajo realizado de a 3 aplicando conocimientos de la carrera para armar una Arquitectura de 3 capas utilizando JAVA
 - Wiki: https://github.com/JRDG/TPEArquiMakeMyDay/wiki
(Este trabajo posteriormente iba a ser llevado a ser una aplicación Android pero por motivos de tiempo no se llego a hacer)

MakeMyDay es una aplicación de seguimiento de actividades personales de un usuario, que permite asociar un nivel de satisfacción (o felicidad) a la realización de estas actividades. El sistema deberá permitir cargar actividades, actualizarlas, consultarlas y ofrecer un historial con los niveles de satisfacción del usuario a lo largo del tiempo. Las actividades pueden ser públicas o privadas, e individuales o grupales.

MakeMyDay debe poseer una aplicación  de interfaz Web intuitiva que permita cargar nuevas actividades, editarlas o eliminarlas. El historial se podrá visualizar como listado, como también en un gráfico que permite visualizar más intuitivamente los niveles de satisfacción en los últimos días.

Puede integrarse con Facebook, y permitir que el usuario pueda optar por publicar en Facebook cada una de las actividades que agregue. MakeMyDay podrá sugerir actividades basándose en la información que recaba de la interacción con el usuario.
 
   
# Requerimientos Funcionales y de Atributos de Calidad

# Requerimientos funcionales
El sistema debe permitir que un usuario cargue las actividades que realiza en una determinada fecha, con un determinado tiempo utilizado y con el nivel de “felicidad” que le brindó realizar dicha actividad.
El sistema deberá proporcionar una interfaz al usuario para poder ingresar datos relacionados a la actividad
Una vez cargado los datos, el sistema deberá validar que los datos son correctos
El sistema deberá persistir los datos cargados por el usuario
 
El sistema debe mantener un historial de las actividades que el usuario ha realizado. Este historial podrá consultarse de diversas maneras: por tipo de actividad, por actividades que otorgaron más felicidad, por actividades compartidas con ciertas personas, etc.

El sistema debe determinar (estimar) a través de las actividades realizadas por el usuario, la felicidad que el usuario posee en cada minuto:
Este nivel de felicidad, que el usuario posee dia a día, debe ser reportado por la aplicación.
 
 El sistema debe permitir cargar actividades que sean de conocimiento público y otras que se mantengan en forma privada. Esto será útil cuando el sistema utilice las redes sociales (por ej., Facebook) para comunicar acontecimientos del usuario. También puede utilizarse para recomendaciones grupales. 
El sistema debe persistir la actividad cargada por el usuario como pública o privada.
 
El sistema debe permitir compartir a través de las redes sociales las actividades que el usuario vaya realizando (aquellas definidas como públicas).
El sistema debe autenticar los usuarios con sus cuentas de Facebook.
El sistema debe obtener los permisos necesarios de las cuentas de Facebook.
El sistema debe compartir a través de Facebook las actividades que los usuarios registren como públicas.
 
El sistema deberá sugerir a los usuarios, dependiendo de los niveles de felicidad actuales, realizar actividades para que esté más feliz (o mantenga su nivel de felicidad)
El sistema debe determinar una actividad a sugerir al usuario.
El sistema debe notificar al usuario sobre la actividad recomendada.
El sistema debe recomendar actividades a los usuarios cuando estos lo soliciten.
 
El sistema debe autenticar a todo usuario de la aplicación.
El sistema autentificará al usuario cada vez que este desee utilizar el sistema.
 
  
# Atributos de calidad

Portabilidad.  El diseño del sistema debe estar desarrollado de manera tal, que incorporar nuevas maneras de utilización del sistema (por ejemplo, distintos navegadores Web) no implique un cambio en el diseño.

Escalabilidad.   El sistema debe estar desarrollado de forma tal que pueda crecer con facilidad, en el número de usuarios que utilizan la aplicación.

Seguridad. El sistema deberá reaccionar ante intentos no autorizados de uso, proveyendo al mismo tiempo servicio a usuarios legítimos.

Disponibilidad.  Ante alguna anomalía que se presente en el sistema, que como consecuencia afecte la funcionalidad del mismo, éste deberá seguir siendo operable e informar al usuario cuando se requiera.

Usabilidad. Un usuario sin experiencia previa en el uso del sistema debe poder operarlo correctamente. La interfaz gráfica debe ser intuitiva y amigable para el usuario.

Modularidad.  El diseño debe estar desarrollado mediante componentes o módulos independientes integrados, que permita evolucionar módulos por separado.
 
 
# Aspectos de Ambiente de Trabajo

El sistema se desarrollará en Java (backend), con el ambiente Eclipse. En dicho ambiente se integrarán las tecnologías necesarias para el desarrollo del sistema (por ej., Apache Derby, JAXB, Jersey) como así también herramientas de soporte al desarrollo (por ej., Git, JUnit).

El trabajo se desarrollará en grupos de 2-3 personas (no mas, no menos), que deberán ser informados a la cátedra.

# Primera entrega: 13 de septiembre. A entregar, clases que modelen la persistencia del dominio y JUnit para testear la siguiente funcionalidad:
Inicializar la base, si no está inicializada.
Crear usuarios (10 usuarios por ejemplo).
Consultar usuarios.
Crear actividades (10 actividades).
Consultar actividades y sus propiedades.
Logear actividades a un usuario (20 actividades con niveles de felicidad).
Seleccionar actividades de un usuario en una franja de tiempo utilizando consultas JPQL.
Obtener el mínimo, máximo y promedio de felicidad utilizando consultas JPQL.
Eliminar todos los datos de la base de datos para realizar otro testeo.

# Segunda entrega: 25 de octubre. A entregar: código fuente + despliegue + documentación
Implementar servicios de Login/Logout
Registración de usuarios
Modificación de datos de usuario (requiere login)
Consultar actividades de usuarios, dado un rango de fechas (requiere login)
Documentar el diseño (contexto, sistema, modelo de datos, API).

