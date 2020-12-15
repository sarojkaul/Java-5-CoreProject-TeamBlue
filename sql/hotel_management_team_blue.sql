-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2020 at 02:00 PM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_management_team_blue`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `Booking_ID` int(11) NOT NULL,
  `Check_in_Date` date NOT NULL,
  `Check_out_Date` date DEFAULT NULL,
  `Guest_ID` int(11) NOT NULL,
  `Room_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`Booking_ID`, `Check_in_Date`, `Check_out_Date`, `Guest_ID`, `Room_ID`) VALUES
(1, '2020-12-01', NULL, 19, 8),
(2, '2020-12-13', '2020-12-24', 21, 7),
(3, '2020-12-25', '2020-12-31', 24, 3),
(4, '2020-12-26', '2020-12-27', 22, 1),
(5, '2021-01-01', '2021-01-13', 23, 9),
(6, '2021-02-03', '2021-03-02', 20, 4);

-- --------------------------------------------------------

--
-- Table structure for table `cancellation`
--

CREATE TABLE `cancellation` (
  `Cancellation_ID` int(11) NOT NULL,
  `Cancellation_Date` date NOT NULL,
  `Booking_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cancellation`
--

INSERT INTO `cancellation` (`Cancellation_ID`, `Cancellation_Date`, `Booking_ID`) VALUES
(1, '2020-12-14', 4);

-- --------------------------------------------------------

--
-- Table structure for table `credit_card`
--

CREATE TABLE `credit_card` (
  `Credit_Card_ID` bigint(20) NOT NULL,
  `Payee_Name` varchar(100) NOT NULL,
  `Credit_Card_Number` varchar(16) NOT NULL,
  `CVC` int(11) NOT NULL,
  `Expiry_Month` int(11) NOT NULL,
  `Expiry_Year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `credit_card`
--

INSERT INTO `credit_card` (`Credit_Card_ID`, `Payee_Name`, `Credit_Card_Number`, `CVC`, `Expiry_Month`, `Expiry_Year`) VALUES
(1, 'Eason Chan', '5438232612345555', 650, 12, 24),
(2, 'Adele Blue', '4350124578980099', 788, 2, 22),
(3, 'Ivanka Trump', '5600900045327896', 560, 12, 23),
(4, 'Marcel Hirscher', '4567908023436541', 679, 3, 24),
(5, 'Shreya Ghoshal', '4563232123439876', 480, 4, 22),
(6, 'Ernst Fuchs', '5467234500998877', 345, 8, 25);

-- --------------------------------------------------------

--
-- Table structure for table `guest`
--

CREATE TABLE `guest` (
  `GuestID` int(11) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `Surname` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Telephone_Number` varchar(50) DEFAULT NULL,
  `Street` varchar(100) DEFAULT NULL,
  `Building_Number` varchar(100) DEFAULT NULL,
  `Post_Code` varchar(50) NOT NULL,
  `City` varchar(100) DEFAULT NULL,
  `Country` varchar(100) DEFAULT NULL,
  `Passport_ID` varchar(100) DEFAULT NULL,
  `Gender` varchar(100) DEFAULT NULL,
  `Nationality` varchar(3) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Credit_Card_ID` bigint(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `guest`
--

INSERT INTO `guest` (`GuestID`, `NAME`, `Surname`, `Email`, `Telephone_Number`, `Street`, `Building_Number`, `Post_Code`, `City`, `Country`, `Passport_ID`, `Gender`, `Nationality`, `Birthday`, `Credit_Card_ID`) VALUES
(19, 'Eason ', 'Chan', 'e.chan@gmail.com', '0085260743391', 'On Chun Street ', '16', '', 'Hong Kong ', 'Hong Kong', 'N2314578', 'Male', 'HKG', '1974-07-27', 1),
(20, 'Marcel ', 'Hirscher', 'hirscher.m@gmx.at', '004367874445665', 'Kettenbrückengasse ', '19', '1050', 'Vienna', 'Austria', 'AT543768', 'Male', 'AUT', '1989-03-02', 2),
(21, 'Ivanka', 'Trump', 'i.trunmp@gmail.com', '0016502449900', 'Westborough Blvd', '278', 'CA94080', 'San Francisco', 'United States', 'US1232425', 'Female', 'USA', '1981-10-31', 3),
(22, 'Shreya ', 'Ghoshal', 's.ghoshal@live.com', NULL, 'Panmaqtha, Rangareddy', '83/1', '500032', 'Telangana', 'India', 'i34567891', 'Female', 'IND', '1984-03-21', 4),
(23, 'Ernst', 'Fuchs', 'e.fuchs@gmx.com', '0043660 5779831', 'Wagramer Str. ', '94', '1220', 'Vienna', 'Austria', 'a67774993', 'Male', 'AUT', '1930-02-13', 5),
(24, 'Adele Laurie Blue  ', 'Adkins', 'a.adele@gmail.com', '00441925555129', 'Market Building, The Piazza, Covent Garden', '24', 'WC2E 8RD', 'London', 'United Kingdom', 'n45687443', 'Female', 'GBR', '1988-05-05', 6);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `Room_ID` int(11) NOT NULL,
  `Category` enum('Single','Double','Superior','Suite') NOT NULL,
  `Has_Wlan` tinyint(1) DEFAULT NULL,
  `Has_Coffee_Machine` tinyint(1) DEFAULT NULL,
  `Has_TV` tinyint(1) DEFAULT NULL,
  `Size` bigint(20) NOT NULL,
  `Description` text NOT NULL,
  `Capacity` int(11) NOT NULL,
  `Price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`Room_ID`, `Category`, `Has_Wlan`, `Has_Coffee_Machine`, `Has_TV`, `Size`, `Description`, `Capacity`, `Price`) VALUES
(1, 'Single', 1, 0, 1, 20, 'Warm, welcoming and modern, with a comfortable bed and a practical bathroom, our rooms offer everything you need for an enjoyable stay.', 1, '80.00'),
(2, 'Double', 1, 1, 1, 25, 'Warm, welcoming and modern, with a comfortable bed and a practical bathroom, our rooms offer everything you need for an enjoyable stay.', 2, '100.00'),
(3, 'Superior', 1, 1, 1, 30, 'Design economy hotel, open to creative minds', 2, '150.00'),
(4, 'Suite', 1, 1, 1, 45, 'Feel-good ambience for the whole family in your air-conditioned, soundproofed 45 sq. m. room with innovative Live N Dream double bed, sofa bed, roomy closet, workplace, free wi-fi and large flatscreen', 4, '250.00'),
(5, 'Single', 1, 0, 1, 20, 'Warm, welcoming and modern, with a comfortable bed and a practical bathroom, our rooms offer everything you need for an enjoyable stay.', 1, '80.00'),
(6, 'Double', 1, 1, 1, 25, 'Warm, welcoming and modern, with a comfortable bed and a practical bathroom, our rooms offer everything you need for an enjoyable stay.', 2, '100.00'),
(7, 'Superior', 1, 1, 1, 30, ' a room on the upper floors with desk and high-speed wi-fi, bathrobe and slippers. Enjoy a drink from the included minibar or coffee from the Nespresso machine.', 2, '150.00'),
(8, 'Suite', 1, 1, 1, 45, 'Feel-good ambience for the whole family in your air-conditioned, soundproofed 45 sq. m. room with innovative Live N Dream double bed, sofa bed, roomy closet, workplace, free wi-fi and large flatscreen', 4, '250.00'),
(9, 'Single', 1, 0, 1, 20, 'Your 20 sq. m. air-conditioned, the soundproofed room has a Live N Dream double bed with desk, office chair, USB port, free high-speed wi-fi and flatscreen TV.', 1, '80.00'),
(10, 'Double', 1, 1, 1, 25, 'Warm, welcoming and modern, with a comfortable bed and a practical bathroom, our rooms offer everything you need for an enjoyable stay.', 2, '100.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`Booking_ID`),
  ADD KEY `Room_ID` (`Room_ID`),
  ADD KEY `fk_guest` (`Guest_ID`);

--
-- Indexes for table `cancellation`
--
ALTER TABLE `cancellation`
  ADD PRIMARY KEY (`Cancellation_ID`),
  ADD KEY `Booking_ID` (`Booking_ID`);

--
-- Indexes for table `credit_card`
--
ALTER TABLE `credit_card`
  ADD PRIMARY KEY (`Credit_Card_ID`);

--
-- Indexes for table `guest`
--
ALTER TABLE `guest`
  ADD PRIMARY KEY (`GuestID`),
  ADD KEY `Credit_Card_ID` (`Credit_Card_ID`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`Room_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `Booking_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `cancellation`
--
ALTER TABLE `cancellation`
  MODIFY `Cancellation_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `credit_card`
--
ALTER TABLE `credit_card`
  MODIFY `Credit_Card_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `guest`
--
ALTER TABLE `guest`
  MODIFY `GuestID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `Room_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`Guest_ID`) REFERENCES `guest` (`GuestID`),
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`Room_ID`) REFERENCES `room` (`Room_ID`),
  ADD CONSTRAINT `fk_guest` FOREIGN KEY (`Guest_ID`) REFERENCES `guest` (`GuestID`);

--
-- Constraints for table `cancellation`
--
ALTER TABLE `cancellation`
  ADD CONSTRAINT `cancellation_ibfk_1` FOREIGN KEY (`Booking_ID`) REFERENCES `booking` (`Booking_ID`);

--
-- Constraints for table `guest`
--
ALTER TABLE `guest`
  ADD CONSTRAINT `guest_ibfk_1` FOREIGN KEY (`Credit_Card_ID`) REFERENCES `credit_card` (`Credit_Card_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
