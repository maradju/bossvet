-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 17, 2019 at 11:07 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 5.6.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `marijadjurovicseminarski`
--

-- --------------------------------------------------------

--
-- Table structure for table `klijent`
--

CREATE TABLE `klijent` (
  `pib` int(11) NOT NULL,
  `nazivKlijenta` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adresa` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `brojTelefona` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `emailKlijenta` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `klijent`
--

INSERT INTO `klijent` (`pib`, `nazivKlijenta`, `adresa`, `brojTelefona`, `emailKlijenta`) VALUES
(3423432, 'Maki doo', 'Kostolocka 25', '065 3231 123', 'makidoo@gmail.com'),
(12341234, 'Promet mega doo 666', 'Prote Mateje 666', '065 323 6666', 'das6666@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `nalog`
--

CREATE TABLE `nalog` (
  `sifraNaloga` int(11) NOT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `jmbg` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nalog`
--

INSERT INTO `nalog` (`sifraNaloga`, `username`, `password`, `jmbg`) VALUES
(1, 'maki', 'maki', '1505996715999');

-- --------------------------------------------------------

--
-- Table structure for table `narudzbenica`
--

CREATE TABLE `narudzbenica` (
  `narudzbenicaID` int(11) NOT NULL,
  `datum` date NOT NULL,
  `ukupno` double NOT NULL,
  `pib` int(11) NOT NULL,
  `jmbg` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sifraNaloga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `narudzbenica`
--

INSERT INTO `narudzbenica` (`narudzbenicaID`, `datum`, `ukupno`, `pib`, `jmbg`, `sifraNaloga`) VALUES
(5, '2019-04-22', 2400, 3423432, '2305996715923', 1);

-- --------------------------------------------------------

--
-- Table structure for table `proizvod`
--

CREATE TABLE `proizvod` (
  `sifraProizvoda` int(11) NOT NULL,
  `nazivProizvoda` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `cenaProizvoda` double NOT NULL,
  `opisProizvoda` varchar(1000) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `proizvod`
--

INSERT INTO `proizvod` (`sifraProizvoda`, `nazivProizvoda`, `cenaProizvoda`, `opisProizvoda`) VALUES
(1, 'Proizvod 1', 500, 'opis 1'),
(2, 'Proizvod 2', 200, 'opis 2'),
(3, 'Proiyvod 3', 800, 'opis 3'),
(4, 'Proiyvod 4', 1000, 'opis 4');

-- --------------------------------------------------------

--
-- Table structure for table `stavkanarudzbenice`
--

CREATE TABLE `stavkanarudzbenice` (
  `narudzbenicaID` int(11) NOT NULL,
  `rednibroj` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  `vrednost` double NOT NULL,
  `sifraProizvoda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `stavkanarudzbenice`
--

INSERT INTO `stavkanarudzbenice` (`narudzbenicaID`, `rednibroj`, `kolicina`, `vrednost`, `sifraProizvoda`) VALUES
(5, 1, 2, 1000, 1),
(5, 2, 3, 600, 2),
(5, 3, 1, 800, 3);

-- --------------------------------------------------------

--
-- Table structure for table `zaposleni`
--

CREATE TABLE `zaposleni` (
  `jmbg` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `imeZaposlenog` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prezimeZaposlenog` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `zaposleni`
--

INSERT INTO `zaposleni` (`jmbg`, `imeZaposlenog`, `prezimeZaposlenog`, `email`) VALUES
('1505996715999', 'Marija', 'Djurovic', 'marija@gmail.com'),
('2305996715923', 'Milan', 'Mikic', 'mika@gmail.com'),
('2407997715146', 'Teodora', 'Djoric', 'teodora@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `klijent`
--
ALTER TABLE `klijent`
  ADD PRIMARY KEY (`pib`);

--
-- Indexes for table `nalog`
--
ALTER TABLE `nalog`
  ADD PRIMARY KEY (`sifraNaloga`),
  ADD KEY `jmbg` (`jmbg`);

--
-- Indexes for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  ADD PRIMARY KEY (`narudzbenicaID`),
  ADD KEY `jmbg` (`jmbg`),
  ADD KEY `sifraNaloga` (`sifraNaloga`);

--
-- Indexes for table `proizvod`
--
ALTER TABLE `proizvod`
  ADD PRIMARY KEY (`sifraProizvoda`);

--
-- Indexes for table `stavkanarudzbenice`
--
ALTER TABLE `stavkanarudzbenice`
  ADD PRIMARY KEY (`narudzbenicaID`,`rednibroj`),
  ADD KEY `sifraProizvoda` (`sifraProizvoda`);

--
-- Indexes for table `zaposleni`
--
ALTER TABLE `zaposleni`
  ADD PRIMARY KEY (`jmbg`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `nalog`
--
ALTER TABLE `nalog`
  MODIFY `sifraNaloga` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  MODIFY `narudzbenicaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `proizvod`
--
ALTER TABLE `proizvod`
  MODIFY `sifraProizvoda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `nalog`
--
ALTER TABLE `nalog`
  ADD CONSTRAINT `nalog_ibfk_1` FOREIGN KEY (`jmbg`) REFERENCES `zaposleni` (`jmbg`);

--
-- Constraints for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  ADD CONSTRAINT `narudzbenica_ibfk_1` FOREIGN KEY (`jmbg`) REFERENCES `zaposleni` (`jmbg`),
  ADD CONSTRAINT `narudzbenica_ibfk_2` FOREIGN KEY (`sifraNaloga`) REFERENCES `nalog` (`sifraNaloga`);

--
-- Constraints for table `stavkanarudzbenice`
--
ALTER TABLE `stavkanarudzbenice`
  ADD CONSTRAINT `stavkanarudzbenice_ibfk_1` FOREIGN KEY (`narudzbenicaID`) REFERENCES `narudzbenica` (`narudzbenicaID`) ON DELETE CASCADE,
  ADD CONSTRAINT `stavkanarudzbenice_ibfk_2` FOREIGN KEY (`sifraProizvoda`) REFERENCES `proizvod` (`sifraProizvoda`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
