-- MySQL dump 10.13  Distrib 8.0.42, for Linux (x86_64)
--
-- Host: localhost    Database: eventos_hyrule
-- ------------------------------------------------------
-- Server version	8.0.42-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Actividad`
--

DROP TABLE IF EXISTS `Actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Actividad` (
  `codigo_actividad` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  `codigo_evento` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  `tipo_actividad` enum('CHARLA','TALLER','DEBATE','OTRA') COLLATE utf8mb4_spanish_ci NOT NULL,
  `titulo_actividad` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL,
  `correo_encargado` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  `cupo_maximo` int NOT NULL,
  PRIMARY KEY (`codigo_actividad`),
  KEY `fk_actividad_encargado` (`correo_encargado`),
  KEY `fk_actividad_evento` (`codigo_evento`),
  CONSTRAINT `fk_actividad_encargado` FOREIGN KEY (`correo_encargado`) REFERENCES `Participante` (`correo`),
  CONSTRAINT `fk_actividad_evento` FOREIGN KEY (`codigo_evento`) REFERENCES `Evento` (`codigo_evento`),
  CONSTRAINT `Actividad_chk_1` CHECK ((`cupo_maximo` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Actividad`
--

LOCK TABLES `Actividad` WRITE;
/*!40000 ALTER TABLE `Actividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `Actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Asistencia`
--

DROP TABLE IF EXISTS `Asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Asistencia` (
  `id_asistencia` int NOT NULL AUTO_INCREMENT,
  `correo_participante` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `codigo_actividad` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`id_asistencia`),
  UNIQUE KEY `uq_asistencia` (`correo_participante`,`codigo_actividad`),
  KEY `fk_asistencia_actividad` (`codigo_actividad`),
  CONSTRAINT `fk_asistencia_actividad` FOREIGN KEY (`codigo_actividad`) REFERENCES `Actividad` (`codigo_actividad`),
  CONSTRAINT `fk_asistencia_participante` FOREIGN KEY (`correo_participante`) REFERENCES `Participante` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Asistencia`
--

LOCK TABLES `Asistencia` WRITE;
/*!40000 ALTER TABLE `Asistencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `Asistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Certificado`
--

DROP TABLE IF EXISTS `Certificado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Certificado` (
  `id_certificado` int NOT NULL AUTO_INCREMENT,
  `correo_participante` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `codigo_evento` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  `ruta_archivo` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`id_certificado`),
  KEY `fk_certificado_participante` (`correo_participante`),
  KEY `fk_certificado_evento` (`codigo_evento`),
  CONSTRAINT `fk_certificado_evento` FOREIGN KEY (`codigo_evento`) REFERENCES `Evento` (`codigo_evento`),
  CONSTRAINT `fk_certificado_participante` FOREIGN KEY (`correo_participante`) REFERENCES `Participante` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Certificado`
--

LOCK TABLES `Certificado` WRITE;
/*!40000 ALTER TABLE `Certificado` DISABLE KEYS */;
/*!40000 ALTER TABLE `Certificado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Evento`
--

DROP TABLE IF EXISTS `Evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Evento` (
  `codigo_evento` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `tipo_evento` enum('CHARLA','CONGRESO','TALLER','DEBATE') COLLATE utf8mb4_spanish_ci NOT NULL,
  `titulo` varchar(150) COLLATE utf8mb4_spanish_ci NOT NULL,
  `ubicacion` varchar(150) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `cupo_maximo` int NOT NULL,
  `costo_inscripcion` decimal(10,2) NOT NULL,
  PRIMARY KEY (`codigo_evento`),
  CONSTRAINT `Evento_chk_1` CHECK ((`cupo_maximo` > 0)),
  CONSTRAINT `Evento_chk_2` CHECK ((`costo_inscripcion` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Evento`
--

LOCK TABLES `Evento` WRITE;
/*!40000 ALTER TABLE `Evento` DISABLE KEYS */;
/*!40000 ALTER TABLE `Evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Inscripcion`
--

DROP TABLE IF EXISTS `Inscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Inscripcion` (
  `id_inscripcion` int NOT NULL AUTO_INCREMENT,
  `correo_participante` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `codigo_evento` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  `tipo_inscripcion` enum('ASISTENTE','CONFERENCISTA','TALLERISTA','OTRO') COLLATE utf8mb4_spanish_ci NOT NULL,
  `validada` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_inscripcion`),
  UNIQUE KEY `uq_inscripcion` (`correo_participante`,`codigo_evento`),
  KEY `fk_inscripcion_evento` (`codigo_evento`),
  CONSTRAINT `fk_inscripcion_evento` FOREIGN KEY (`codigo_evento`) REFERENCES `Evento` (`codigo_evento`),
  CONSTRAINT `fk_inscripcion_participante` FOREIGN KEY (`correo_participante`) REFERENCES `Participante` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Inscripcion`
--

LOCK TABLES `Inscripcion` WRITE;
/*!40000 ALTER TABLE `Inscripcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `Inscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pago`
--

DROP TABLE IF EXISTS `Pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pago` (
  `id_pago` int NOT NULL AUTO_INCREMENT,
  `correo_participante` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `codigo_evento` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  `metodo_pago` enum('EFECTIVO','TRANSFERENCIA','TARJETA') COLLATE utf8mb4_spanish_ci NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `fk_pago_participante` (`correo_participante`),
  KEY `fk_pago_evento` (`codigo_evento`),
  CONSTRAINT `fk_pago_evento` FOREIGN KEY (`codigo_evento`) REFERENCES `Evento` (`codigo_evento`),
  CONSTRAINT `fk_pago_participante` FOREIGN KEY (`correo_participante`) REFERENCES `Participante` (`correo`),
  CONSTRAINT `Pago_chk_1` CHECK ((`monto` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pago`
--

LOCK TABLES `Pago` WRITE;
/*!40000 ALTER TABLE `Pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Participante`
--

DROP TABLE IF EXISTS `Participante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Participante` (
  `correo` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombre_completo` varchar(45) COLLATE utf8mb4_spanish_ci NOT NULL,
  `tipo_participante` enum('ESTUDIANTE','PROFESIONAL','INVITADO') COLLATE utf8mb4_spanish_ci NOT NULL,
  `institucion` varchar(150) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Participante`
--

LOCK TABLES `Participante` WRITE;
/*!40000 ALTER TABLE `Participante` DISABLE KEYS */;
/*!40000 ALTER TABLE `Participante` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-19  6:56:42
