-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-08-2017 a las 06:35:31
-- Versión del servidor: 10.1.25-MariaDB
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `jaildb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorization`
--

CREATE TABLE `authorization` (
  `id` int(11) NOT NULL,
  `relationship` varchar(255) NOT NULL,
  `interview_id` int(11) DEFAULT NULL,
  `prisoner_id` int(11) DEFAULT NULL,
  `visitor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `authorization`
--

INSERT INTO `authorization` (`id`, `relationship`, `interview_id`, `prisoner_id`, `visitor_id`) VALUES
(19, 'Tio/a', 13, 3, 14),
(20, 'Hermano/a', 14, 3, 15),
(21, 'Hermano/a', 15, 4, 16),
(22, 'Amigo/a', 16, 4, 17),
(23, 'Abuelo/a', 17, 5, 18),
(24, 'Primo/a', 18, 9, 19),
(25, 'Hermano/a', 19, 11, 20),
(26, 'Tio/a', 20, 12, 21),
(27, 'Tio/a', 21, 13, 22),
(28, 'Primo/a', 22, 15, 23),
(29, 'Padre/Madre', 23, 16, 24),
(30, 'Amigo/a', 24, 17, 25),
(31, 'Abuelo/a', 25, 1, 26),
(32, 'Amigo/a', 26, 2, 27),
(33, 'Amigo/a', 27, 3, 28),
(34, 'Hermano/a', 28, 9, 29),
(35, 'Primo/a', 29, 12, 30);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `block`
--

CREATE TABLE `block` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `block`
--

INSERT INTO `block` (`id`) VALUES
(6),
(7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cell`
--

CREATE TABLE `cell` (
  `id` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  `block_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cell`
--

INSERT INTO `cell` (`id`, `capacity`, `block_id`) VALUES
(14, 6, 6),
(15, 5, 6),
(16, 3, 7),
(17, 8, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `interview`
--

CREATE TABLE `interview` (
  `id` int(11) NOT NULL,
  `assignation_date` datetime DEFAULT NULL,
  `end_date` datetime NOT NULL,
  `interviewee_id` int(11) NOT NULL,
  `interviewee_name` varchar(255) NOT NULL,
  `result` int(11) DEFAULT NULL,
  `start_date` datetime NOT NULL,
  `prisoner_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `interview`
--

INSERT INTO `interview` (`id`, `assignation_date`, `end_date`, `interviewee_id`, `interviewee_name`, `result`, `start_date`, `prisoner_id`) VALUES
(11, '2017-08-12 17:08:03', '2017-08-12 11:00:00', 347835892, 'Pepe Lotudo', 1, '2017-08-12 05:00:00', 15),
(12, '2017-08-12 17:36:30', '2017-08-13 05:30:00', 45348123, 'Monica Galindo', 1, '2017-08-13 04:00:00', 16),
(13, '2017-08-12 18:52:23', '2017-08-13 08:00:00', 78934897, 'Aquiles Castro ', 1, '2017-08-13 06:00:00', 3),
(14, '2017-08-12 18:53:25', '2017-08-13 11:30:00', 8934563, 'Aquiles Pinto Paredes', 1, '2017-08-13 09:00:00', 3),
(15, '2017-08-12 18:54:34', '2017-08-15 04:30:00', 4597834, 'Icela Creyo', 1, '2017-08-15 02:30:00', 4),
(16, '2017-08-12 18:56:00', '2017-08-15 08:30:00', 948533113, 'Hal Colico', 1, '2017-08-15 06:30:00', 4),
(17, '2017-08-12 18:57:16', '2017-08-14 08:00:00', 273863123, 'Sacarias Flores del Campo', 1, '2017-08-14 04:30:00', 5),
(18, '2017-08-12 18:59:38', '2017-08-16 11:00:00', 321698765, 'Rodrigo Melo Rosa', 1, '2017-08-16 09:00:00', 9),
(19, '2017-08-12 19:01:01', '2017-08-16 07:00:00', 89798465, 'Jack Hallate', 1, '2017-08-16 04:30:00', 11),
(20, '2017-08-12 19:02:29', '2017-08-17 02:00:00', 87567833, 'Dolores Delano', 1, '2017-08-17 00:00:00', 12),
(21, '2017-08-12 19:03:54', '2017-08-18 01:30:00', 57895734, 'Mela Halo', 1, '2017-08-18 01:00:00', 13),
(22, '2017-08-12 19:05:48', '2017-08-18 06:00:00', 34862381, 'Aitor Tilla', 1, '2017-08-18 03:30:00', 15),
(23, '2017-08-12 19:08:10', '2017-08-18 08:30:00', 43534145, 'Omar Garita', 1, '2017-08-18 07:30:00', 16),
(24, '2017-08-12 19:09:15', '2017-08-18 09:30:00', 48958349, 'Paco Gerlo', 1, '2017-08-18 09:00:00', 17),
(25, '2017-08-12 19:15:32', '2017-08-19 04:30:00', 98321321, 'Elba Zurita', 1, '2017-08-19 02:30:00', 1),
(26, '2017-08-12 19:16:46', '2017-08-23 13:00:00', 486358438, 'Lago Loza', 1, '2017-08-23 00:00:00', 2),
(27, '2017-08-12 19:19:13', '2017-08-24 08:00:00', 657898762, 'Lola Mento', 1, '2017-08-24 06:00:00', 3),
(28, '2017-08-12 19:21:51', '2017-08-24 11:00:00', 456245345, 'Armando Esteban Quito', 1, '2017-08-24 09:30:00', 9),
(29, '2017-08-12 19:24:32', '2017-08-26 03:30:00', 82348692, 'Alex Plosivo', 1, '2017-08-26 03:00:00', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prisoner`
--

CREATE TABLE `prisoner` (
  `id` int(11) NOT NULL,
  `birth_date` date NOT NULL,
  `birth_place` varchar(255) NOT NULL,
  `contact_phone_number` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `serial_number` varchar(255) NOT NULL,
  `cell_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `prisoner`
--

INSERT INTO `prisoner` (`id`, `birth_date`, `birth_place`, `contact_phone_number`, `last_name`, `name`, `serial_number`, `cell_id`) VALUES
(1, '1968-02-12', 'Manizales', '3123123123', 'Del Orto', 'Maria Dolores', '1234314', 14),
(2, '1968-06-12', 'Manizales', '3213213213', 'Lazo', 'Rita', '1623864', 15),
(3, '1981-08-11', 'Manizales', '3133133133', 'Lazo', 'Elba', '8734622', 16),
(4, '1973-06-06', 'Manizales', '3143143143', 'Melano', 'Rosa', '34773123', 17),
(5, '1992-04-10', 'Manizales', '3153153153', 'Melo', 'Debora', '34523141', 17),
(9, '1954-10-22', 'Manizales', '3223223223', 'Porrico', 'Elsa', '8827421', 14),
(11, '1990-01-25', 'Manizales', '3203203213', 'Delgado', 'Rosamel', '7623413', 17),
(12, '1950-01-01', 'Manizales', '3013013013', 'Ortega', 'Rosamel', '9854727', 15),
(13, '1969-05-05', 'Manizales', '3023003023', 'Delgado', 'Alan Brito', '52423412', 14),
(15, '2017-08-11', 'Manizales', '3030303030', 'Ganta', 'Edgar', '7548624', 15),
(16, '1947-08-12', 'Manizales', '3403403404', 'Tracio', 'Elba', '2343452', 14),
(17, '1987-08-12', 'Manizales', '3681412312', 'Becerra', 'Zoyla', '3242123', 16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sentence`
--

CREATE TABLE `sentence` (
  `id` int(11) NOT NULL,
  `end_date` datetime NOT NULL,
  `reason` varchar(255) NOT NULL,
  `start_date` datetime NOT NULL,
  `prisoner_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sentence`
--

INSERT INTO `sentence` (`id`, `end_date`, `reason`, `start_date`, `prisoner_id`) VALUES
(6, '2017-02-12 00:00:00', '', '2017-01-12 00:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visit`
--

CREATE TABLE `visit` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `prisoner_id` int(11) DEFAULT NULL,
  `visitor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `visit`
--

INSERT INTO `visit` (`id`, `date`, `prisoner_id`, `visitor_id`) VALUES
(7, '2017-08-12 00:00:00', 4, 17),
(8, '2017-08-12 00:00:00', 15, 23),
(9, '2017-08-12 00:00:00', 4, 16),
(10, '2017-08-12 00:00:00', 1, 26),
(11, '2017-08-12 00:00:00', 3, 14),
(12, '2017-08-12 00:00:00', 15, 23),
(13, '2017-08-12 00:00:00', 3, 14),
(14, '2017-08-12 00:00:00', 16, 24),
(15, '2017-08-12 00:00:00', 16, 24),
(16, '2017-08-12 00:00:00', 3, 14),
(17, '2017-08-12 00:00:00', 5, 18),
(18, '2017-08-12 00:00:00', 5, 18),
(19, '2017-08-12 00:00:00', 12, 30),
(20, '2017-08-12 00:00:00', 1, 26),
(21, '2017-08-12 00:00:00', 16, 24),
(22, '2017-08-12 00:00:00', 17, 25),
(23, '2017-08-12 00:00:00', 3, 15),
(24, '2017-08-12 00:00:00', 3, 14),
(25, '2017-08-12 00:00:00', 5, 18),
(26, '2017-08-12 00:00:00', 15, 23),
(27, '2017-08-12 00:00:00', 17, 25),
(28, '2017-08-12 00:00:00', 9, 29),
(29, '2017-08-12 00:00:00', 12, 30),
(30, '2017-08-12 00:00:00', 13, 22),
(31, '2017-08-12 00:00:00', 1, 26),
(32, '2017-08-12 00:00:00', 13, 22),
(33, '2017-08-12 00:00:00', 13, 22),
(34, '2017-08-12 00:00:00', 1, 26),
(35, '2017-08-12 00:00:00', 15, 23),
(36, '2017-08-12 00:00:00', 1, 26),
(37, '2017-08-12 00:00:00', 5, 18);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitor`
--

CREATE TABLE `visitor` (
  `id` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `birthday` datetime NOT NULL,
  `birthplace` varchar(255) NOT NULL,
  `id_card` int(11) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `visitor`
--

INSERT INTO `visitor` (`id`, `address`, `birthday`, `birthplace`, `id_card`, `last_name`, `name`, `phone`) VALUES
(13, 'Carrera 420 No. 420-420', '1999-08-12 00:00:00', 'Manizales', 45348123, 'Galindo', 'Monica', '3123112312'),
(14, 'Carrera x No. x-x Manizales', '1987-08-12 00:00:00', 'Manizales', 78934897, 'Castro', 'Aquiles', '3161231231'),
(15, 'Carrera f No. f-f Manizales', '1965-08-12 00:00:00', 'Manizales', 8934563, 'Pinto Paredes', 'Aquiles', '3185646498'),
(16, 'Carrera t No. t-t Manizales', '1850-08-17 00:00:00', 'Manizales', 4597834, 'Creyo', 'Icela', '9879546312'),
(17, 'Carrera q No. q-q Manizales', '1510-08-13 00:00:00', 'Manizales', 948533113, 'Colico', 'Hal', '3455679821'),
(18, 'Carrera w No. w-w Manizales', '1600-04-02 00:00:00', 'Manizales', 4538498, 'Flores del Campo', 'Sacarias', '3121223132'),
(19, 'Carrera e No. e-e Manizales', '1428-02-13 00:00:00', 'Manizales', 321698765, 'Melo Rosa', 'Rodrigo', '3216549877'),
(20, 'Carrera r No r-r Manizales', '1657-08-12 00:00:00', 'Manizales', 89798465, 'Hallate', 'Jack', '3232123215'),
(21, 'Carrera y No y-y Manizales', '1803-12-31 00:00:00', 'Manizales', 87567833, 'Delano', 'Dolores', '3465412312'),
(22, 'Carrera u No. u-u Manizales', '1990-05-01 00:00:00', 'Manizales', 57895734, 'Halo', 'Mela', '2364569872'),
(23, 'Carrera i No. i-i Manizales', '1910-01-29 00:00:00', 'Manizales', 34862381, 'Tilla', 'Aitor', '3549879532'),
(24, 'Carrera o No. o-o Manizales', '1545-03-10 00:00:00', 'Manizales', 65735662, 'Garita', 'Omar', '2326546468'),
(25, 'Carrera p No. P-p Manizales', '1400-02-22 00:00:00', 'Manizales', 987985412, 'Gerlo', 'Paco', '3213213211'),
(26, 'Carrera a No. a-a Manizales', '1875-08-12 00:00:00', 'Manizales', 98321321, 'Zurita', 'Elba', '3213213218'),
(27, 'Carrera s No. s-s Manizales', '1700-08-03 00:00:00', 'Manizales', 83748294, 'Loza', 'Lago', '3573573578'),
(28, 'Carrera d No. d-d Manizales', '0456-08-12 00:00:00', 'Manizales', 987652234, 'Mento', 'Lola', '5647213214'),
(29, 'Carrera g No. g-g Manizales', '1957-12-12 00:00:00', 'Manizales', 456245345, 'Quito', 'Armando Esteban', '3654878945'),
(30, 'Carrera h No. h-h Manizales', '1456-07-12 00:00:00', 'Manizales', 82348692, 'Plosivo', 'Alex', '3413123123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `authorization`
--
ALTER TABLE `authorization`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2b1pgyb5y2fq5rcumf3fopa58` (`interview_id`),
  ADD KEY `FKbyj1wxxcfk5d68t7rsfxuj48g` (`prisoner_id`),
  ADD KEY `FK3xvfe34d4bophprix5e3unjie` (`visitor_id`);

--
-- Indices de la tabla `block`
--
ALTER TABLE `block`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cell`
--
ALTER TABLE `cell`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs5y6tx140r65j1p7ht0d1qib7` (`block_id`);

--
-- Indices de la tabla `interview`
--
ALTER TABLE `interview`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjcb1d8dfujf8qll1bo4mjinod` (`prisoner_id`);

--
-- Indices de la tabla `prisoner`
--
ALTER TABLE `prisoner`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_cbybuj6id2vux4dyud1kr0dqj` (`serial_number`),
  ADD KEY `FK27s0ccy54abqc3qvr2n64cej3` (`cell_id`);

--
-- Indices de la tabla `sentence`
--
ALTER TABLE `sentence`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKih3uojf9ts0xvgaxwx162suvi` (`prisoner_id`);

--
-- Indices de la tabla `visit`
--
ALTER TABLE `visit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1ve88yo6fkjn42n3qoaikq47p` (`prisoner_id`),
  ADD KEY `FKb217j6rjijr1p3kde3hw2gm8x` (`visitor_id`);

--
-- Indices de la tabla `visitor`
--
ALTER TABLE `visitor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `authorization`
--
ALTER TABLE `authorization`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT de la tabla `block`
--
ALTER TABLE `block`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `cell`
--
ALTER TABLE `cell`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT de la tabla `interview`
--
ALTER TABLE `interview`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT de la tabla `prisoner`
--
ALTER TABLE `prisoner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT de la tabla `sentence`
--
ALTER TABLE `sentence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `visit`
--
ALTER TABLE `visit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT de la tabla `visitor`
--
ALTER TABLE `visitor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `authorization`
--
ALTER TABLE `authorization`
  ADD CONSTRAINT `FK2b1pgyb5y2fq5rcumf3fopa58` FOREIGN KEY (`interview_id`) REFERENCES `interview` (`id`),
  ADD CONSTRAINT `FK3xvfe34d4bophprix5e3unjie` FOREIGN KEY (`visitor_id`) REFERENCES `visitor` (`id`),
  ADD CONSTRAINT `FKbyj1wxxcfk5d68t7rsfxuj48g` FOREIGN KEY (`prisoner_id`) REFERENCES `prisoner` (`id`);

--
-- Filtros para la tabla `cell`
--
ALTER TABLE `cell`
  ADD CONSTRAINT `FKs5y6tx140r65j1p7ht0d1qib7` FOREIGN KEY (`block_id`) REFERENCES `block` (`id`);

--
-- Filtros para la tabla `interview`
--
ALTER TABLE `interview`
  ADD CONSTRAINT `FKjcb1d8dfujf8qll1bo4mjinod` FOREIGN KEY (`prisoner_id`) REFERENCES `prisoner` (`id`);

--
-- Filtros para la tabla `prisoner`
--
ALTER TABLE `prisoner`
  ADD CONSTRAINT `FK27s0ccy54abqc3qvr2n64cej3` FOREIGN KEY (`cell_id`) REFERENCES `cell` (`id`);

--
-- Filtros para la tabla `sentence`
--
ALTER TABLE `sentence`
  ADD CONSTRAINT `FKih3uojf9ts0xvgaxwx162suvi` FOREIGN KEY (`prisoner_id`) REFERENCES `prisoner` (`id`);

--
-- Filtros para la tabla `visit`
--
ALTER TABLE `visit`
  ADD CONSTRAINT `FK1ve88yo6fkjn42n3qoaikq47p` FOREIGN KEY (`prisoner_id`) REFERENCES `prisoner` (`id`),
  ADD CONSTRAINT `FKb217j6rjijr1p3kde3hw2gm8x` FOREIGN KEY (`visitor_id`) REFERENCES `visitor` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
