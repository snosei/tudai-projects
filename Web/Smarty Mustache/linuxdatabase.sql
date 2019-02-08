-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 30-11-2015 a las 22:32:01
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `linuxdatabase`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `nombre` varchar(30) NOT NULL,
  `detalle` varchar(100) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`nombre`, `detalle`) VALUES
('Actualizaciones', 'Actualizaciones'),
('Eventos', 'Eventos'),
('Seguridad', 'Categoria referente a vulnerabilidades y actualizaciones que solventen las mismas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distro`
--

CREATE TABLE IF NOT EXISTS `distro` (
  `id_distro` int(11) NOT NULL AUTO_INCREMENT,
  `logo` varchar(150) NOT NULL,
  `link_distro` varchar(300) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `arquitectura` varchar(10) NOT NULL,
  `requisito_memoria` text NOT NULL,
  `requisito_disco` text NOT NULL,
  `link_descarga` varchar(300) NOT NULL,
  PRIMARY KEY (`id_distro`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Volcado de datos para la tabla `distro`
--

INSERT INTO `distro` (`id_distro`, `logo`, `link_distro`, `nombre`, `arquitectura`, `requisito_memoria`, `requisito_disco`, `link_descarga`) VALUES
(1, 'uploaded/images/560cab723340alogoubuntu.png', 'http://www.ubuntu.com/', 'Ubuntu', 'x86', 'Memoria Ram: 1024 MB', 'Espacio en el Disco: 5 GB', 'https://mega.co.nz/#!ftYTBAAQ!9B5u5et3nb-oEQ3JezJYnPlH6OdWu--6jE9b1vgQi5E'),
(2, 'uploaded/images/560cabae6760elogoubuntu.png', 'http://www.ubuntu.com/', 'Ubuntu', 'x64', 'Memoria Ram: 2048 MB', 'Espacio en el Disco: 5 GB', 'https://mega.co.nz/#!f0RSmZgS!YsNrGxZNlpBsNnxUW2HG-6hNigOYY1x-j8NSV59w1pI'),
(3, 'uploaded/images/560cabe7d6b19logoxubuntu.png', 'http://xubuntu.org/', 'Xubuntu', 'x86', 'Memoria Ram: 512 MB', 'Espacio en el Disco: 6 GB', 'https://mega.co.nz/#!zwwUCJTS!xBA3sVIfo5-s4YpKRdDkWr0UsWtWX9_sy9l-3WS4tPE'),
(4, 'uploaded/images/560cac0ff1c00logoxubuntu.png', 'http://xubuntu.org/', 'Xubuntu', 'x64', 'Memoria Ram: 2048 MB', 'Espacio en el Disco: 6 GB', 'https://mega.co.nz/#!egZxFa7L!SIimsyGmIUCWR7aj9HWrSryTOMHz7qWVc1z6xbbUkP8'),
(5, 'uploaded/images/560cacfac5887logolubuntu.png', 'http://lubuntu.net/', 'Lubuntu', 'x86', 'Memoria Ram: 512 MB', 'Espacio en el Disco: 5 GB', 'https://mega.co.nz/#!vhB3VL6Y!yQ3s1NXpHk_1Xt4bHTyrxAd4utFdUzhvnFw60ARdJ4o'),
(6, 'uploaded/images/560cad188673alogolubuntu.png', 'http://lubuntu.net/', 'Lubuntu', 'x64', 'Memoria Ram: 2048 MB', 'Espacio en el Disco: 5 GB', 'https://mega.co.nz/#!ikQSwYgb!1E6f2JrNRXZ6-wQI3HQo7YpEd5vq-ck5Zw6cQktemIk'),
(7, 'uploaded/images/560cad4a3f50clogokubuntu.png', 'http://www.kubuntu.org/', 'Kubuntu', 'x86', 'Memoria Ram: 512 MB', 'Espacio en el Disco: 5 GB', 'https://mega.co.nz/#!bl4FVJLQ!21uA44k-wX0BoOZuUpPLYvZl9iqY0OiLzi1-QE-RTcs'),
(8, 'uploaded/images/560cad6851ce9logokubuntu.png', 'http://www.kubuntu.org/', 'Kubuntu', 'x64', 'Memoria Ram: 2048 MB', 'Espacio en el Disco: 5 GB', 'https://mega.co.nz/#!msx32BDJ!kCxoux_AjKDkz4CsZ3_FQbvJ4VGklvldKpgY71gvOwc'),
(9, 'uploaded/images/560cada068262logofedora.png', 'https://getfedora.org/es/', 'Fedora', 'x86', 'Memoria Ram: 1024 MB', 'Espacio en el Disco: 10 GB', 'https://mega.co.nz/#!nsQ12QZY!N8mKXQmRqQYgzVevb5PYBpz-gNlsK-ipz9gtpTiZNBo'),
(10, 'uploaded/images/560cadbeab5aflogofedora.png', 'https://getfedora.org/es/', 'Fedora', 'x64', 'Memoria Ram: 2048 MB', 'Espacio en el Disco: 10 GB', 'https://mega.co.nz/#!jpRX1JRZ!w09UPkTr9L1ciIHTxCgXiULd-7vhKayrdyDFywIoF_o'),
(11, 'uploaded/images/560cae02cdcf9logomint.png', 'http://www.linuxmint.com/', 'Mint (Mate)', 'x86', 'Memoria Ram: 512 MB', 'Espacio en el Disco: 5 GB', 'https://mega.co.nz/#!7sgUVAIC!yU8VmwHAr9eg58W9310NACS3SILhpvtsvSY4KyxaUic'),
(12, 'uploaded/images/560cae247195dlogomint.png', 'http://www.linuxmint.com/', 'Mint (Mate)', 'x64', 'Memoria Ram: 2048 MB', 'Espacio en el Disco: 5 GB', 'https://mega.co.nz/#!X4wkzZbR!oaYWk6-uPGkfQ3O9N8YJftp4ZQVv8d7wDH_G9y-Io4o'),
(13, 'uploaded/images/560cae47d915alogomint.png', 'http://www.linuxmint.com/', 'Mint (Cinnamon)', 'x86', 'Memoria Ram: 512 MB', 'Espacio en el Disco: 9 GB', 'https://mega.co.nz/#!L5ZxlJxR!kLVx_y5JkNs5NpdkYxEVKkjvRnMhZQW9QY8wlf4NV9Q'),
(14, 'uploaded/images/560cae6782a78logomint.png', 'http://www.linuxmint.com/', 'Mint (Cinnamon)', 'x64', 'Memoria Ram: 2048 MB', 'Espacio en el Disco: 9 GB', 'https://mega.co.nz/#!LpplRBRJ!rBtLTaa7r0qtNaawWGzc0vOy1Pt7saoqHMDViKCETEo'),
(15, 'uploaded/images/560caec861a97logodebian.png', 'https://www.debian.org/index.es.html', 'Debian', 'x86', 'Memoria Ram: 512 MB', 'Espacio en el Disco: 6 GB', 'https://mega.co.nz/#!C0YEHIyS!FbthNToYKIoRR_O2oIiPXrXy4S00Dd6OchdNMk9XuZY'),
(16, 'uploaded/images/560caef924cc6logohuayra.png', 'http://huayra.conectarigualdad.gob.ar/', 'Huayra', 'x86', 'Memoria Ram: 512 MB', 'Espacio en el Disco: 6 GB', 'https://mega.co.nz/#!SgR3DDgT!5peUWVzoOeQGTibIDHQfJJAQpEzO0XKiz0oyI7KZE2k'),
(18, 'uploaded/images/565cc025d7307560caf3e8a88flogopuppy.png', 'http://puppylinux.org/', 'Puppy', 'x86', 'Memoria Ram: 128 MB', 'Espacio en el Disco: 512 MB', 'https://mega.co.nz/#!ytYkBRzA!fLOJj3TOVsoVP46kEYMNVkJhVRInAmw7Nu_Z7Bi_Hmk');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagen`
--

CREATE TABLE IF NOT EXISTS `imagen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ruta` varchar(300) NOT NULL,
  `fk_id_noticia` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_noticia` (`fk_id_noticia`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

--
-- Volcado de datos para la tabla `imagen`
--

INSERT INTO `imagen` (`id`, `ruta`, `fk_id_noticia`) VALUES
(1, 'uploaded/images/560d6a0cef75aubuntu-logo-c-830x466.png', 1),
(2, 'uploaded/images/560d6a0cef7f2ubuntu.jpg', 1),
(3, 'uploaded/images/560d6a0cef854images.jpeg', 1),
(10, 'uploaded/images/560d997288afdraspbian-debian-8.jpg', 8),
(11, 'uploaded/images/560d99cd8efd3firefox.jpg', 9),
(12, 'uploaded/images/560d9ab28491etouch3.png', 10),
(13, 'uploaded/images/560d9ab28499btouch2.jpg', 10),
(14, 'uploaded/images/560d9ab2849ectouch.jpg', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE IF NOT EXISTS `mensaje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` varchar(30) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `mensaje` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `mensaje`
--

INSERT INTO `mensaje` (`id`, `fecha`, `nombre`, `mail`, `mensaje`) VALUES
(1, '2015-11-12 22:52:16', 'victor', 'g.victor.juan@gmail.com', 'hola mamá!!');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `noticia`
--

CREATE TABLE IF NOT EXISTS `noticia` (
  `id_noticia` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) NOT NULL,
  `contenido` text NOT NULL,
  `fk_nombre_categoria` varchar(100) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_noticia`),
  KEY `fk_nombre_categoria` (`fk_nombre_categoria`),
  KEY `fk_nombre_categoria_2` (`fk_nombre_categoria`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Volcado de datos para la tabla `noticia`
--

INSERT INTO `noticia` (`id_noticia`, `titulo`, `contenido`, `fk_nombre_categoria`, `fecha`) VALUES
(1, 'Corregido fallo de seguridad en Ubuntu', 'El equipo de Canonical ha detectado un fallo de seguridad en todas las versiones con soporte de Ubuntu(12.04 LTS,14.04 LTS y 15.04), el cual afortunadamente ha sido rápidamente corregido.\r\n\r\nÉste fallo de seguridad fue detectado en la gestión de la ICU(biblioteca de caracteres unicode), el cual permitía atacar remotamente un equipo con Ubuntu instalado y poder o bien colapsar el equipo o bien ejecutar código en el mismo con los mismos privilegios que el usuario del equipo.Podemos ver la nota oficial de seguridad aquí.\r\n\r\nEl equipo de Canonical aconseja una actualización inmediata del sistema para evitar males mayores, para ello debemos ejecutar la consola con privilegios de administrador(poniendo sudo delante de los comandos) y teclear apt-get update y a continuación apt-dist-upgrade. Con éstos comandos se nos instalará el parche de seguridad que corrige ésta vulnerabilidad, el cual nos deja de momento fuera de peligro.\r\n\r\nAunque Ubuntu sea un sistema GNU/Linux y por tanto infinitamente más seguro que sistemas como Windows, no es un sistema perfecto y por eso a veces tiene fallos de seguridad. No por ello debemos desconfiar de Ubuntu ni de su equipo ya que todo software puede tener fallos ya que los desarrolladores no son perfectos y como todo ser humano a veces se confunden.\r\n\r\nRealmente si lo piensas el fallo de seguridad no es para tanto si lo comparas con fallos de seguridad de Windows o de programas como Adobe Flash Player los cuáles son famosos por sus continuos y peligrosos agujeros de seguridad, sin embargo en Canonical les gusta la seguridad y ser precavidos y por ésta razón se han andado rápidos para corregirlo.\r\n\r\nPor último aconsejar ejecutar los comandos mencionados con anterioridad de forma regular(sudo apt-get update y sudo apt-get dist-upgrade), de ésta manera conseguiremos tener siempre el sistema actualizado lo que supone tener instalados siempre los últimos parches de seguridad.', 'Seguridad', '2015-10-01 17:14:52'),
(8, 'Raspbian se actualiza a Debian 8', 'Raspbian OS, el famoso sistema operativo o distribución Linux para la exitosa Raspberry Pi, se ha actualizado para basarse en Debian 8. Antes estaba basado en Debian 7 Wheezy, y sus desarrolladores han trabajado para que esté disponible la nueva versión con corazón Jessie. Todo listo para utilizar en el mal llamado “miniPC”, la SBC con más prestigio de todas, la Raspberry Pi.\r\n\r\nA pesar de su sencillez y los años, la Raspberry Pi continúa en su ascenso de popularidad y se actualiza tanto en hardware como en software. Igual que Arduino, parece una plataforma que ha sabido conquistar a los usuarios, sobre todo para fines educativos y aquellos manitas que les gusta el DIY. Su flexibilidad permite crear proyectos increíbles y su precio tan bajo la hacen perfecta para que cualquiera la vea asequible.', 'Actualizaciones', '2015-10-01 20:37:06'),
(9, 'Salió Firefox 41.0.1', '\r\nHacía unos días había salido la versión 41 de Mozilla Firefox, ésta versión traía ciertas mejoras en la seguridad y en la optimización de memoria, sin embargo Mozilla ha sacado una nueva actualización para Firefox, la 41.0.1.\r\n\r\nLa razón de ésta pequeña actualización es que se habían detectado algunos errores que provocaban un mal funcionamiento en Firefox, un mal funcionamiento que podría llegar a cerrar y bloquear completamente el navegador, lo cual hacía necesario sacar una actualización destinada a su corrección.\r\n\r\nAlgunos de éstos errores tenían que ver con ciertas extensiones muy utilizadas en Mozilla Firefox. En primer lugar se encontró un problema al arrancar Firefox con el famoso plugin AdBlock, concretamente en su versión plus, así como con la menos conocida barra de herramientas Yandez. Cuando arrancábamos el navegador con éstas extensiones activadas se producía una ralentización del funcionamiento y se colgaba por completo en algunas ocasiones.\r\n\r\nLuego había otro problema con la gestión de imágenes de mapa de bits y de optimización de texturas en general que también ha sido corregido por Mozilla, otro problema que se ha arreglado era un problema relacionado con la gestión de hojas de estilos de css y un problema menor al intentar modificar un marcador agregado en el cual no podíamos modificar el último marcador que habíamos agregado.\r\n\r\nFinalmente como no podía ser de otra manera, teníamos otro problema con Adobe Flash Player en el cual había algunos problemas de gestión de la memoria que gestionaba el plugin. Como sabes Mozilla no recomienda utilizar Adobe Flash Player en su navegador debido a todos los fallos de seguridad y de funcionamiento, recomendando en su lugar utilizar únicamente HTML5.\r\n\r\nMozilla ha recomendado a todos los usuarios de Mozilla Firefox 41 actualizar a ésta versión con la mayor brevedad posible, normalmente la actualización de Firefox es automática así que no hay que preocuparse por nada.', 'Actualizaciones', '2015-10-01 20:38:37'),
(10, 'El Whatsapp para Ubuntu Touch tendrá que esperar', 'Malas noticias para los que hayáis decidido a dar el paso de comprar un teléfono con el sistema Ubuntu Touch instalado, Canonical ha anunciado que hasta el momento no es posible la creación de una aplicación de Whatsapp.\r\n\r\nEl principal problema de Ubuntu Touch es que no tiene una interfaz de programación de aplicaciones para terceros, por lo tanto a día de hoy es imposible crear cierto tipo de aplicaciones ya que ningún tercero puede participar en su creación.\r\n\r\nLo que quiero decir es que la creación de aplicaciones como Whatsapp sólo sería posible que se creasen desde desarrolladores oficiales de Ubuntu, desde Canonical han dicho que para realizar ésta tarea necesitarían trabajar con proveedores más grandes.\r\n\r\nEl problema es que es como la pescadilla que se muerde la cola, los proveedores no vienen porque Ubuntu Touch no tiene apenas cuota de mercado y Ubuntu Touch no tiene cuota de mercado por la falta de proveedores que puedan crear aplicaciones competitivas.\r\n\r\nDesde que salió éste sistema,se están teniendo problemas de éste tipo ya que carece de todo tipo de aplicaciones que en otros sistemas el 95% de los usuarios tienen instaladas como Whatsapp, lo cual frena la llegada de nuevos usuarios a éste sistema.\r\n\r\nDesde Canonical son conscientes de ello y por ésa razón van a preparar una interfaz de programación de aplicaciones que podría estar lista para el año que viene. Así mismo va a lanzarse una versión de TextSecure para Ubuntu Touch, el cual tiene características similares a Whatsapp pero con mucha más seguridad.\r\n\r\nEn mi opinión como no se pongan las pilas, éste sistema puede morir sin nisiquiera comenzar a despegar.\r\nToda ésta información fue preguntada por usuarios en el evento preguntas y respuestas de Ubuntu, si sabes inglés y quieres ver con mayor detalle todo lo que se preguntó y todo lo que se respondió, lo tienes aquí.', 'Eventos', '2015-10-01 20:42:26');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `id_2` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `password`) VALUES
(1, 'snosei92@gmail.com', '$2y$10$HIy0Rv0.iP4hqsK6RmQH6eKsoKy.BbUjdF1/zTQzGy7oxflxEjhOm'),
(2, 'g.victor.juan@gmail.com', '$2y$10$dPb.mmZK3o49SkWcRwjVte5WwHjshzIG9bxQ/k1S.RKhaRKB2vzeC');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `imagen`
--
ALTER TABLE `imagen`
  ADD CONSTRAINT `fk_id_noticia` FOREIGN KEY (`fk_id_noticia`) REFERENCES `noticia` (`id_noticia`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `noticia`
--
ALTER TABLE `noticia`
  ADD CONSTRAINT `fk_nombre` FOREIGN KEY (`fk_nombre_categoria`) REFERENCES `categoria` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
