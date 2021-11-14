-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2021 at 02:43 AM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `store`
--

-- --------------------------------------------------------

--
-- Table structure for table `banner`
--

CREATE TABLE `banner` (
  `ID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Link` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `banner`
--

INSERT INTO `banner` (`ID`, `Name`, `Link`) VALUES
(1, 'Ice Juice', 'https://media.istockphoto.com/photos/strawberry-smoothie-or-milkshake-in-mason-jar-decorated-mint-on-pink-picture-id941442818?k=20&m=941442818&s=612x612&w=0&h=FO7eh5r7lZnbtRgZxZKl_BaWD3OjkWCdOray5UVtefc=\r\n'),
(2, 'Burger ', 'https://media.istockphoto.com/photos/beef-burgers-with-pesto-sauce-picture-id1337796232?k=20&m=1337796232&s=612x612&w=0&h=JphTMBIoGbSkt3XuFUQnIS0Er3yy7YzON1JCWqhf24s='),
(3, 'Ice Cream', 'https://media.istockphoto.com/photos/colorful-ice-cream-cones-of-different-flavors-melting-scoops-top-picture-id528741710?k=20&m=528741710&s=612x612&w=0&h=uxnEHhH8I2s36rHKxEMwrUrSssBN9K7tATzvN3QJnOE=');

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `ID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Link` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`ID`, `Name`, `Link`) VALUES
(1, 'ASIA', 'https://www.asiapharma-syria.com/images/front/shared/en-fr/main-temp/logo.gif'),
(2, 'Oubari', 'https://www.oubari.com/wp-content/uploads/2014/06/logo.png'),
(3, 'Unipharma', 'https://www.unipharma-sy.com/images/NLogo.png'),
(4, 'Barakat', 'http://gongcha.com.vn/wp-content/uploads/2018/02/Tr%C3%A0-B%C3%AD-%C4%90ao-Alisan-1.png'),
(5, 'OE Group', 'https://www.unipharma-sy.com/images/NLogo.png'),
(6, 'Ibn-Alhaytham', 'https://www.unipharma-sy.com/images/NLogo.png'),
(7, 'Diamond', 'https://www.unipharma-sy.com/images/NLogo.png'),
(8, 'ELSaad ', 'https://www.unipharma-sy.com/images/NLogo.png'),
(9, 'Mercypharma', 'https://www.unipharma-sy.com/images/NLogo.png'),
(10, 'Biomed', 'https://www.unipharma-sy.com/images/NLogo.png'),
(11, 'Massoud', 'https://www.unipharma-sy.com/images/NLogo.png'),
(12, 'Bregma', 'https://www.unipharma-sy.com/images/NLogo.png'),
(13, 'DONA-F', 'https://www.unipharma-sy.com/images/NLogo.png'),
(14, 'Recpharma', 'https://www.unipharma-sy.com/images/NLogo.png'),
(15, 'ALLIED', 'https://www.unipharma-sy.com/images/NLogo.png'),
(16, 'Univet', 'https://www.unipharma-sy.com/images/NLogo.png'),
(17, 'PHARMASYR', 'https://www.unipharma-sy.com/images/NLogo.png'),
(18, 'BM Pharm', 'https://www.unipharma-sy.com/images/NLogo.png'),
(19, 'Farabi', 'https://www.unipharma-sy.com/images/NLogo.png'),
(20, 'American Health Care Middle East', 'https://www.unipharma-sy.com/images/NLogo.png'),
(21, 'MainPharma', 'https://www.unipharma-sy.com/images/NLogo.png'),
(22, 'Jasmine medical', 'https://www.unipharma-sy.com/images/NLogo.png');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `ID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Link` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`ID`, `Name`, `Link`) VALUES
(1, 'Pizza', 'https://image.flaticon.com/icons/png/128/1404/1404945.png'),
(2, 'Burger', 'https://image.flaticon.com/icons/png/128/878/878052.png'),
(3, 'Hotdog', 'https://image.flaticon.com/icons/png/128/1041/1041344.png'),
(4, 'Drink', 'https://image.flaticon.com/icons/png/128/3076/3076073.png'),
(5, 'Donut', 'https://image.flaticon.com/icons/png/128/3496/3496528.png'),
(6, 'Sushi', 'https://image.flaticon.com/icons/png/128/2252/2252075.png');

-- --------------------------------------------------------

--
-- Table structure for table `orderr`
--

CREATE TABLE `orderr` (
  `OrderId` bigint(20) NOT NULL,
  `OrderStatus` tinyint(4) NOT NULL,
  `OrderPrice` float NOT NULL,
  `OrderDetail` text NOT NULL,
  `OrderComment` text NOT NULL,
  `OrderAddress` text NOT NULL,
  `UserEmail` text NOT NULL,
  `OrderDate` date NOT NULL,
  `PaymentMethod` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orderr`
--

INSERT INTO `orderr` (`OrderId`, `OrderStatus`, `OrderPrice`, `OrderDetail`, `OrderComment`, `OrderAddress`, `UserEmail`, `OrderDate`, `PaymentMethod`) VALUES
(1, 0, 17, '[{\"amount\":1,\"id\":15,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":17.0,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-18', 'COD'),
(2, -1, 31, '[{\"amount\":1,\"id\":16,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":31.0,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-18', 'COD'),
(3, 2, 45, '[{\"amount\":1,\"id\":17,\"link\":\"https://image.flaticon.com/icons/png/128/214/214681.png\",\"name\":\"Cake donuts\",\"price\":45.0,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  Milk Tea Black Pearl\\n  Beer\\n  \"}]', '', 'q', 's', '2021-09-18', 'COD'),
(4, 2, 21.75, '[{\"amount\":1,\"id\":42,\"link\":\"https://image.flaticon.com/icons/png/128/706/706934.png\",\"name\":\"Neapolitan Pizza\",\"price\":21.75,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  Beer\\n  Cocunut\\n  Green Tea \\n  \"}]', 'hello ', 'homs syria', 's', '2021-09-19', 'COD'),
(5, -1, 54, '[{\"amount\":1,\"id\":43,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Pepsi\\n  \"},{\"amount\":1,\"id\":44,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":21.75,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  Beer\\n  Cocunut\\n  Green Tea \\n  \"},{\"amount\":1,\"id\":45,\"link\":\"https://image.flaticon.com/icons/png/128/877/877951.png\",\"name\":\"Elk Burgers\",\"price\":21.75,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  Beer\\n  Cocunut\\n  Green Tea \\n  \"}]', 'hi', 'q', 's', '2021-09-19', 'COD'),
(6, 3, 11.25, '[{\"amount\":2,\"id\":46,\"link\":\"https://image.flaticon.com/icons/png/128/3511/3511307.png\",\"name\":\"California Pizza\",\"price\":11.25,\"size\":0,\"with\":\"Green Tea \\n  \"}]', 'quicly', 'homs', 's', '2021-09-19', 'COD'),
(7, -1, 18, '[{\"amount\":1,\"id\":49,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":18.0,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  Beer\\n  Cocunut\\n  \"}]', '', 'q', 's', '2021-09-23', 'COD'),
(8, 2, 10.5, '[{\"amount\":1,\"id\":50,\"link\":\"https://image.flaticon.com/icons/png/128/2405/2405466.png\",\"name\":\"Milk Tea Black Pearl\",\"price\":10.5,\"size\":1,\"with\":\"Beer\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(9, 1, 10.5, '[{\"amount\":1,\"id\":51,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(10, 2, 15, '[{\"amount\":2,\"id\":52,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":15.0,\"size\":0,\"with\":\"Beer\\n  \"}]', 'vgn', 'q', 's', '2021-09-25', 'COD'),
(11, 2, 10.5, '[{\"amount\":1,\"id\":53,\"link\":\"https://image.flaticon.com/icons/png/128/877/877951.png\",\"name\":\"Elk Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(12, 2, 10.5, '[{\"amount\":1,\"id\":54,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'we yhgggt', 'q', 's', '2021-09-25', 'COD'),
(13, 3, 25.5, '[{\"amount\":1,\"id\":55,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":11.25,\"size\":0,\"with\":\"Milk Tea Black Pearl\\n  Beer\\n  \"},{\"amount\":1,\"id\":56,\"link\":\"https://image.flaticon.com/icons/png/128/877/877951.png\",\"name\":\"Elk Burgers\",\"price\":14.25,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  Cocunut\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(14, 1, 10.5, '[{\"amount\":1,\"id\":57,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'uffi', 'q', 's', '2021-09-25', 'COD'),
(15, 2, 10.5, '[{\"amount\":1,\"id\":58,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Beer\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(16, -1, 10.5, '[{\"amount\":1,\"id\":59,\"link\":\"https://image.flaticon.com/icons/png/128/2405/2405466.png\",\"name\":\"Milk Tea Black Pearl\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(17, 2, 10.5, '[{\"amount\":1,\"id\":64,\"link\":\"https://image.flaticon.com/icons/png/128/3126/3126369.png\",\"name\":\"Cheese dog\",\"price\":10.5,\"size\":1,\"with\":\"Beer\\n  \"}]', 'gggo', 'q', 's', '2021-09-25', 'COD'),
(18, 1, 10.5, '[{\"amount\":1,\"id\":65,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":10.5,\"size\":1,\"with\":\"Beer\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(19, 1, 7.5, '[{\"amount\":1,\"id\":66,\"link\":\"https://image.flaticon.com/icons/png/128/877/877951.png\",\"name\":\"Elk Burgers\",\"price\":7.5,\"size\":0,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(20, 3, 7.5, '[{\"amount\":1,\"id\":66,\"link\":\"https://image.flaticon.com/icons/png/128/877/877951.png\",\"name\":\"Elk Burgers\",\"price\":7.5,\"size\":0,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(21, -1, 10.5, '[{\"amount\":1,\"id\":67,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":10.5,\"size\":1,\"with\":\"Beer\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(22, 3, 10.5, '[{\"amount\":1,\"id\":68,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(23, 3, 33.75, '[{\"amount\":3,\"id\":69,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":33.75,\"size\":0,\"with\":\"Green Tea \\n  Pepsi\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(24, 2, 10.5, '[{\"amount\":1,\"id\":70,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(25, -1, 22.5, '[{\"amount\":2,\"id\":1,\"link\":\"https://image.flaticon.com/icons/png/128/877/877951.png\",\"name\":\"Elk Burgers\",\"price\":15.0,\"size\":0,\"with\":\"Milk Tea Black Pearl\\n  Beer\\n  \"},{\"amount\":1,\"id\":2,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":7.5,\"size\":0,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'Ø¹Ø¬Ù„ ÙŠØ§ Ø®Ø±Ø§', 'Ø­Ù…Øµ', 's', '2021-09-25', 'COD'),
(26, 2, 10.5, '[{\"amount\":1,\"id\":3,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Cocunut\\n  \"}]', 'Ù…Ø¨ÙŠÙ…Ù‚', 'q', 's', '2021-09-25', 'COD'),
(27, 3, 10.5, '[{\"amount\":1,\"id\":4,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-25', 'COD'),
(28, -1, 10.5, '[{\"amount\":1,\"id\":5,\"link\":\"https://image.flaticon.com/icons/png/128/214/214681.png\",\"name\":\"Cake donuts\",\"price\":10.5,\"size\":1,\"with\":\"Beer\\n  \"}]', 'iiiiiii', 'q', 's', '2021-09-25', 'COD'),
(29, 1, 7.5, '[{\"amount\":1,\"id\":6,\"link\":\"https://image.flaticon.com/icons/png/128/877/877951.png\",\"name\":\"Elk Burgers\",\"price\":7.5,\"size\":0,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 'aa', '2021-09-25', 'COD'),
(30, -1, 14.25, '[{\"amount\":1,\"id\":71,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":14.25,\"size\":1,\"with\":\"Beer\\n  Cocunut\\n  \"}]', '', 'q', 'aa', '2021-09-26', 'COD'),
(31, -1, 10.5, '[{\"amount\":1,\"id\":72,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'Ø¨Ø³Ø±Ø¹Ø©', 'Ø­Ù…Øµ ', 'aa', '2021-09-26', 'COD'),
(32, 3, 15, '[{\"amount\":2,\"id\":1,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":15.0,\"size\":0,\"with\":\"Pepsi\\n  \"}]', 'Ø¨Ø³Ø±Ø¹Ø©', 'Ø­Ù…Øµ ', 's', '2021-09-27', 'COD'),
(33, 2, 10.5, '[{\"amount\":1,\"id\":2,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-27', 'COD'),
(34, 1, 10.5, '[{\"amount\":1,\"id\":3,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-27', 'COD'),
(35, 3, 10.5, '[{\"amount\":1,\"id\":4,\"link\":\"https://image.flaticon.com/icons/png/128/3126/3126369.png\",\"name\":\"Cheese dog\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-27', 'COD'),
(36, 2, 10.5, '[{\"amount\":1,\"id\":5,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-09-27', 'COD'),
(37, -1, 6.75, '[{\"amount\":1,\"id\":6,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":6.75,\"size\":1,\"with\":\"\"}]', '', 'q', 'aa', '2021-09-27', 'COD'),
(38, 1, 10.5, '[{\"amount\":1,\"id\":7,\"link\":\"https://image.flaticon.com/icons/png/128/3126/3126369.png\",\"name\":\"Cheese dog\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 'aa', '2021-09-27', 'COD'),
(39, 3, 6.75, '[{\"amount\":1,\"id\":8,\"link\":\"https://image.flaticon.com/icons/png/128/3993/3993977.png\",\"name\":\"Beer\",\"price\":6.75,\"size\":1,\"with\":\"\"}]', '', 'q', 's', '2021-09-27', 'COD'),
(40, 1, 7.5, '[{\"amount\":1,\"id\":9,\"link\":\"https://image.flaticon.com/icons/png/128/3126/3126369.png\",\"name\":\"Cheese dog\",\"price\":7.5,\"size\":0,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'gg', 'q', 's', '2021-10-02', 'COD'),
(41, 3, 10.5, '[{\"amount\":1,\"id\":10,\"link\":\"https://image.flaticon.com/icons/png/128/4977/4977974.png\",\"name\":\"Kappa\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'hh', 'q', 's', '2021-10-02', 'COD'),
(42, 3, 8.75, '[{\"amount\":1,\"id\":11,\"link\":\"https://image.flaticon.com/icons/png/128/3418/3418923.png\",\"name\":\"Half-smoke\",\"price\":8.75,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'yy', 'q', 's', '2021-10-02', 'COD'),
(43, 3, 6.75, '[{\"amount\":1,\"id\":12,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":6.75,\"size\":1,\"with\":\"\"}]', '', 'q', 's', '2021-10-02', 'COD'),
(44, 3, 10.5, '[{\"amount\":1,\"id\":13,\"link\":\"https://image.flaticon.com/icons/png/128/3126/3126369.png\",\"name\":\"Cheese dog\",\"price\":10.5,\"size\":1,\"with\":\"Beer\\n  \"}]', '', 'q', 's', '2021-10-02', 'COD'),
(45, 1, 10.5, '[{\"amount\":1,\"id\":14,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-10-02', 'COD'),
(46, 1, 10.5, '[{\"amount\":1,\"id\":15,\"link\":\"https://image.flaticon.com/icons/png/128/3126/3126369.png\",\"name\":\"Cheese dog\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-10-02', 'COD'),
(47, 1, 10.5, '[{\"amount\":1,\"id\":1,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-10-02', 'COD'),
(48, 3, 10.5, '[{\"amount\":1,\"id\":2,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'Ø¨Ø³Ø±Ø¹Ø©', 'q', 's', '2021-10-02', 'COD'),
(49, 1, 6.75, '[{\"amount\":1,\"id\":3,\"link\":\"https://image.flaticon.com/icons/png/128/4977/4977974.png\",\"name\":\"Kappa\",\"price\":6.75,\"size\":1,\"with\":\"\"}]', '', 'q', 's', '2021-10-02', 'COD'),
(50, 1, 10.5, '[{\"amount\":1,\"id\":1,\"link\":\"https://image.flaticon.com/icons/png/128/877/877951.png\",\"name\":\"Elk Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'Ø§Ø§Ø§Ø§', 'q', 'aa', '2021-10-02', 'COD'),
(51, 1, 10.5, '[{\"amount\":1,\"id\":4,\"link\":\"https://image.flaticon.com/icons/png/128/782/782940.png\",\"name\":\"Salmon\",\"price\":10.5,\"size\":1,\"with\":\"Beer\\n  \"}]', 'ÙŠÙ„Ø§ ÙŠØ§ Ø¬Ø­Ø´', 'q', 's', '2021-10-02', 'COD'),
(52, 3, 31.5, '[{\"amount\":3,\"id\":2,\"link\":\"https://image.flaticon.com/icons/png/128/706/706934.png\",\"name\":\"Neapolitan Pizza\",\"price\":31.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'ÙŠÙ„Ø§ Ø¹Ø§Ø§Ø¯Ù‚Ø±', 'q', 'aa', '2021-10-02', 'COD'),
(53, 0, 10.5, '[{\"amount\":1,\"id\":5,\"link\":\"https://image.flaticon.com/icons/png/128/3126/3126369.png\",\"name\":\"Cheese dog\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-10-03', 'COD'),
(54, 0, 10.5, '[{\"amount\":1,\"id\":1,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-10-03', 'COD'),
(55, -1, 7.5, '[{\"amount\":1,\"id\":1,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":7.5,\"size\":0,\"with\":\"Beer\\n  \"}]', 'Ø¨Ù„Ù„Ù„', 'q', 's', '2021-10-03', 'COD'),
(56, 3, 10.5, '[{\"amount\":1,\"id\":1,\"link\":\"https://image.flaticon.com/icons/png/128/877/877951.png\",\"name\":\"Elk Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'homs', 'war', '2021-10-04', 'COD'),
(57, -1, 10.5, '[{\"amount\":1,\"id\":2,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":10.5,\"size\":1,\"with\":\"Cocunut\\n  \"}]', 'Ø¨Ø³Ø±Ø¹Ø© ', 'london ', 'war', '2021-10-04', 'COD'),
(58, 0, 10.5, '[{\"amount\":1,\"id\":4,\"link\":\"https://image.flaticon.com/icons/png/128/1687/1687247.png\",\"name\":\"Beef Burgers\",\"price\":10.5,\"size\":1,\"with\":\"Beer\\n  \"}]', '', 'homs', 'war', '2021-10-04', 'COD'),
(59, -1, 6.75, '[{\"amount\":1,\"id\":5,\"link\":\"https://image.flaticon.com/icons/png/128/706/706934.png\",\"name\":\"Neapolitan Pizza\",\"price\":6.75,\"size\":1,\"with\":\"\"}]', '', 'homs', 'war', '2021-10-04', 'COD'),
(60, -1, 23, '[{\"amount\":1,\"id\":6,\"link\":\"https://image.flaticon.com/icons/png/128/3361/3361216.png\",\"name\":\"Chocolate Grind\",\"price\":8.75,\"size\":1,\"with\":\"coffe\\n  \"},{\"amount\":1,\"id\":7,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"},{\"amount\":1,\"id\":8,\"link\":\"https://image.flaticon.com/icons/png/128/5495/5495444.png\",\"name\":\"Bison Burgers\",\"price\":3.75,\"size\":0,\"with\":\"\"}]', 'ÙØ§Ùƒ ÙŠÙˆ', 'Ø­Ù…Øµ', 'war', '2021-10-05', 'COD'),
(61, 1, 8892.75, '[{\"amount\":1,\"id\":12,\"link\":\"http://192.168.1.109/store/server/product/product_img/27938cd3-e8c5-42e0-8363-a5b4c927ecbb.jpg\",\"name\":\"ÙƒØ³ÙƒÙˆØ³\",\"price\":8892.75,\"size\":1,\"with\":\"Beer\\n  \"}]', 'Ù‚Ù†ÙˆÙÙ‚ÙˆØ²Ù', 'homs', 'war', '2021-10-05', 'COD'),
(62, 1, 21, '[{\"amount\":2,\"id\":1,\"link\":\"https://image.flaticon.com/icons/png/128/877/877951.png\",\"name\":\"Elk Burgers\",\"price\":21.0,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', '', 'q', 's', '2021-10-09', 'COD'),
(63, 3, 10.5, '[{\"amount\":1,\"id\":2,\"link\":\"https://image.flaticon.com/icons/png/128/1041/1041344.png\",\"name\":\"Bagel dog \",\"price\":10.5,\"size\":1,\"with\":\"Milk Tea Black Pearl\\n  \"}]', 'ØªÙØ¹ÙŠÙ„ Ø®ÙŠØ§Ø±Ø§Øª Ù…ØªØ¹Ø¯Ø¯Ø© ', 'q', 's', '2021-10-09', 'COD');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Link` text NOT NULL,
  `Price` float NOT NULL,
  `MenuId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ID`, `Name`, `Link`, `Price`, `MenuId`) VALUES
(1, 'Beef Burgers', 'https://image.flaticon.com/icons/png/128/1687/1687247.png', 3.75, 2),
(3, 'Neapolitan Pizza', 'https://image.flaticon.com/icons/png/128/706/706934.png', 3.75, 1),
(4, ' Chicago Pizza', 'https://image.flaticon.com/icons/png/128/1384/1384676.png', 3.75, 1),
(5, 'New York-Style Pizza', 'https://image.flaticon.com/icons/png/128/3595/3595455.png', 3.75, 1),
(6, 'Sicilian Pizza', 'https://image.flaticon.com/icons/png/128/2674/2674065.png', 3.75, 1),
(7, 'Greek Pizza', 'https://image.flaticon.com/icons/png/128/2497/2497913.png', 3.75, 1),
(8, 'Elk Burgers', 'https://image.flaticon.com/icons/png/128/877/877951.png', 3.75, 2),
(9, 'Mushroom Burgers', 'https://image.flaticon.com/icons/png/128/2674/2674087.png', 3.75, 2),
(10, 'Turkey Burgers', 'https://image.flaticon.com/icons/png/128/1256/1256423.png', 3.75, 2),
(11, 'Bagel dog ', 'https://image.flaticon.com/icons/png/128/1041/1041344.png', 3.75, 3),
(12, 'Cheese dog', 'https://image.flaticon.com/icons/png/128/3126/3126369.png', 3.75, 3),
(13, 'Milk Tea Black Pearl', 'https://image.flaticon.com/icons/png/128/2405/2405466.png', 3.75, 4),
(14, 'Beer', 'https://image.flaticon.com/icons/png/128/3993/3993977.png', 3.75, 4),
(15, 'Cocunut', 'https://image.flaticon.com/icons/png/128/883/883468.png', 3.75, 4),
(16, 'Yeast donuts', 'https://image.flaticon.com/icons/png/128/2812/2812067.png', 3.75, 5),
(17, 'Cake donuts', 'https://image.flaticon.com/icons/png/128/214/214681.png', 3.75, 5),
(18, 'Glazed Donuts', 'https://image.flaticon.com/icons/png/128/3125/3125268.png', 3.75, 5),
(19, 'Green Tea ', 'https://image.flaticon.com/icons/png/128/4143/4143667.png', 3.75, 4),
(20, 'Sugar Donuts', 'https://image.flaticon.com/icons/png/128/1349/1349831.png', 3.75, 5),
(21, 'Powder Donuts', 'https://image.flaticon.com/icons/png/128/3125/3125268.png', 3.75, 5),
(22, 'Pepsi', 'https://image.flaticon.com/icons/png/128/1159/1159145.png', 3.75, 4),
(23, 'Veggie Burgers', 'https://image.flaticon.com/icons/png/128/1147/1147801.png', 3.75, 2),
(24, 'Chili dog', 'https://image.flaticon.com/icons/png/128/1685/1685045.png', 3.75, 3),
(25, 'coffe', 'https://image.flaticon.com/icons/png/128/1047/1047503.png', 3.75, 4),
(26, 'Churros', 'https://image.flaticon.com/icons/png/128/676/676715.png', 3.75, 5),
(27, 'Crumb Donuts', 'https://image.flaticon.com/icons/png/128/3230/3230701.png', 3.75, 5),
(28, 'California Pizza', 'https://image.flaticon.com/icons/png/128/3511/3511307.png', 3.75, 1),
(29, 'Bison Burgers', 'https://image.flaticon.com/icons/png/128/5495/5495444.png', 3.75, 2),
(30, 'Salmon', 'https://image.flaticon.com/icons/png/128/782/782940.png', 3.75, 6),
(31, 'Kappa', 'https://image.flaticon.com/icons/png/128/4977/4977974.png', 3.75, 6),
(32, 'Uni', 'https://image.flaticon.com/icons/png/128/5350/5350284.png', 3.75, 6),
(33, 'Toro', 'https://image.flaticon.com/icons/png/128/4978/4978109.png', 3.75, 6),
(34, 'Hamachi', 'https://image.flaticon.com/icons/png/128/3183/3183421.png', 3.75, 6),
(35, 'Tuna', 'https://image.flaticon.com/icons/png/128/1923/1923479.png', 3.75, 6),
(36, 'Completo ', 'https://image.flaticon.com/icons/png/128/2616/2616816.png', 2, 3),
(37, 'Black Tea ', 'https://image.flaticon.com/icons/png/128/2224/2224161.png', 2, 4),
(38, 'Chocolate Grind', 'https://image.flaticon.com/icons/png/128/3361/3361216.png', 2, 4),
(39, 'Corn dog', 'https://image.flaticon.com/icons/png/128/3126/3126350.png', 2, 3),
(40, 'Amaebi', 'https://image.flaticon.com/icons/png/128/5036/5036164.png', 2, 6),
(41, 'Anago', 'https://image.flaticon.com/icons/png/128/4977/4977869.png', 2, 6),
(42, 'Juice', 'https://image.flaticon.com/icons/png/128/1473/1473320.png', 2, 4),
(43, 'Chocolate sprinkle', 'https://image.flaticon.com/icons/png/128/1041/1041345.png', 2, 5),
(44, 'Half-smoke', 'https://image.flaticon.com/icons/png/128/3418/3418923.png', 2, 3),
(45, 'Ketwurst', 'https://image.flaticon.com/icons/png/128/3845/3845161.png', 2, 3),
(46, 'Detroit Pizza', 'https://image.flaticon.com/icons/png/128/673/673887.png', 2, 1),
(47, 'Salmon Burgers', 'https://image.flaticon.com/icons/png/128/3480/3480641.png', 2, 2),
(48, 'Lemon Green Tea', 'https://image.flaticon.com/icons/png/128/5304/5304721.png', 2.5, 4),
(49, 'Lemon Alisan Green Tea', 'https://image.flaticon.com/icons/png/128/2405/2405525.png', 2.5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `email` varchar(20) NOT NULL,
  `token` text NOT NULL,
  `isServerToken` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`email`, `token`, `isServerToken`) VALUES
('aa', 'fL_-7ySaSqu7ws1i3pBY_e:APA91bGalPvy_KAfPKrnJ967_LOqE4BNmEF1jkePqLDJ17Jzu5oNLOSSKr_on4RE1NBnOYt6qIQ-Ouhx873uM6lRmcOFJOyF7k6iJbsxg_ZD-sZp6ePQMxJQcB8aHAQqunVzBxAnL9By', 0),
('ali@gmailm.com', 'ecrCy4iqRwSHlTf2VAQVxY:APA91bGdZ0kEzI-f5L1nVdlYQNqyROocU2TJ1eR5CPJI_m6D7Nm38T4EIjgUsHgdLaOqbi2qMCH4iT57kxDgQRLgG5TGKjIY5YJL_tZ5rUQkjtQ9jdfKIvued9hzRgbK3Zt9F0298mZb', 0),
('ddd', 'c6BBE1jqQSWYM_Jdu-fnGV:APA91bGfmuvCP-qJR9EUz3nrGjXj3FvxGIZbn5KM4lM-BE79h14i53UFCALeeH-lEVthonKc-L236MgppPz5IpjfZ8TIvXEipkPxBZXtPLB-IjAygPobOswlSgnxgyuUmqlG0MzjB8i3', 0),
('ep', 'cGKg7uvcR4ODr9EmUYQvlf:APA91bGS0MIn4eD-w087Ea3g6iycSOeLLeJvE6fhlxadAt9xkADNmSU0LETdGIZdHVFy0VIUYb0wUmHdJEgAWddd75sNcWp39GvL_xtwGZtmFxduvqws8QvYKUKrxWt8_10zbu2O7Uff', 0),
('s', 'fv5hKa6FQBeQXwpf7gNJ69:APA91bF8LFho5sXMm-ph_kx8rjkY-Cw8OctjaY9mjejyF_tDhWGJfBXXoekfXQ1C3GBh8dJcDkOoIm24eAVFZAi5R-MV5TLPPCvW6xljqIdp9Kh-QxTgB2i2ddEW5A932X_f5_WAFRj2', 0),
('scn', 'ettyLTTATJqhTrCy00cKAs:APA91bENzi20kf9bWsECNi9Bx-agQeh5W6N8n5BsJlNyZXVIWn4Lxkk7hX8xPP8SFK9V5Ku6vIjHaK8veJqCgjWsH_0EtepCkTRjRTVf-QyIw1gEhKgvj3hx2_jNrSMPSQHMPIqI_zrn', 0),
('server_app', 'cHY22VGfQiyatJR7GP2Sd_:APA91bG0GAB_MsVKXCp2UcX_CjHc3ma2NCNaIYm-lRbwg0jtyk19tqj2oAyybqTP8TqV6C9I8fCOHkd1vMxGYlEejWj16VGDIZYNxx9LSFI6siX8nu8Mv_C6iYtGKsZe6se-frDMo-xG', 1),
('snf', 'f1d8FV-STU2gwCotaW1RIG:APA91bELzR3WmPgj5pvD9kMAzKX70u2w0oXIbXqnf8RB6WTxNyADWb3oosDV3VdWpcLBZL-JKWVBO3XIgtpsHH4FSb-itLcuH-osgSkwQZy3iWA866o8W5PSdnYTMe4mSJk29TRJw-6K', 0),
('war', 'eT64hVRIQ7qHpOG_LVmkti:APA91bGXe537l3LF1yA38IlF1GHIX3L3YpY304fLrUCeQL1_MIp4Spds7Co9v2J6gicTRny2RiH-id4r-3vON9z4Jpit_SjNhA_YMDCT_K4HKYXpOfbMYZU9aQWgF1V5pGtViqoZrh7z', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Email` varchar(20) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Password` text NOT NULL,
  `Address` text NOT NULL,
  `avatarUrl` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Email`, `Name`, `Password`, `Address`, `avatarUrl`) VALUES
('ali', 'ali', '1122', 'qq', 'FB_IMG_1630399789234.jpg'),
('alia', 'ali', '1122', 'qqq', '0'),
('aliaa', 'ali', '1122', 'qqq', '0'),
('aliaas', 'ali', '1122', 'qqq', '0'),
('s', 'ali', '11221122', 'q', 'IMG_20210930_092720.jpg'),
('w1', 'w', '11221122', 'w', '0'),
('aq', 'a', '11221122', 'a', '0'),
('qqqq', 'q', '11221122', 'q', '0'),
('qe', 'e', '11221122', 'h', '0'),
('1q', 'qq', '11221122', 'qqqqqq', '0'),
('qwqw', 'qq', '11221122', 'qqqq', '0'),
('qqs', 'q', '11221122', 'qq@', '0'),
('wq', 'q', '11221122', 'q', '0'),
('e', 'j', '11221122', 'h', '0'),
('ww', 'ee', '11221122', 'ee', '0'),
('qqd', 'qqqqqqqqqq', '11221122', 'qq', '0'),
('qqws', '2', '11221122', 'q', '0'),
('a', 'q', '11221122', 'q', '0'),
('we', 'w', '11221122', 'w', '0'),
('aa', 'ali', '11221122', 'q', '0'),
('qqa', 'qq', '11221122', 'qq', '0'),
('11', '111', '11221122', '11', '0'),
('qwe', 'qq', '11221122', 'qq', '0'),
('1qws', '1', '11221122', '1', '0'),
('qwas', 'q', '11221122', 'q', '0'),
('23', '1', '11221122', '1', '0'),
('ad', 'qqqq', '11221122', 'q', '0'),
('qq1', 'qqq', '11221122', 'qqq', '0'),
('11www', '11', '11221122', '112', '0'),
('111s', '11', '11221122', '1', '0'),
('1122', '11', '11221122', '1', '0'),
('wee', 'qqs', '11221122', 'qq1', '0'),
('aaa', 'aa', '11221122', 'aa', '0'),
('aas', 'asa', '11221122', 'a', '0'),
('qqw', 'wqdq', '11221122', 'qwddddddd', '0'),
('ali', 'qq', '11221122', 'qq', 'FB_IMG_1630399789234.jpg'),
('ttttt', 'nada', '11221122', 'homs', '0'),
('en', 'xxbhd', '11221122', 'bdhdhfh', '0'),
('Ahmadmfjfj', 'Ahmad Salem', '11221122', 'homs', '0'),
('alisalem', 'ali', '11221122', 'homs', ''),
('yy', 'shs', '11221122', 'hd', ''),
('hrfh', ' vc', '11221122', 'cc', 'preliminary-over-modularization-android.jpeg'),
('gggg', 'Ahmad', '11221122', 'gjj', 'magazine-unlock-01-2.3.9272-_096A638DE016A3FCF7A1A473D4C84FF5.jpg'),
('qp', 'ur', '11221122', 'hd', ''),
('ali11salem00@gmail.c', 'ali salem', '11221122', 'homs', ''),
('ddd', 'ali', '11221122', 'homs', ''),
('ali11salem00@gmail.c', 'xxhcyc', '11221122', 'hxxhcn', ''),
('ali11salem00@gmail.c', 'cjccj', '11221122', 'jffi', ''),
('ep', 'fch', '11111111', 'cnc', ''),
('e', 'khvh', 'sdfghjkl;kkkkk', '1', ''),
('s', 'kx', '11111111', 'xnbxd', 'IMG_20210930_092720.jpg'),
('sy', 'ghg', '11221122', 'vhh', ''),
('cvh', 'vbj', '11221122', 'cvj', ''),
('sg', 'rfg', '11111111', 'vh', ''),
('uuu', 'hhj', 'qqqqqqqq', 'fcg', ''),
('sjf', 'hd', '11221122', 'bxxb', ''),
('s', 'bsn', '11112222', 'vsbe', 'IMG_20210930_092720.jpg'),
('szb', 'bz', '11112222', 'bs', ''),
('szbjxjxnx', 'bz', '11112222', 'bs', ''),
('scn', 'xjd', '11221122', 'hxjx', ''),
('snf', 'dhdj', '11111111', 'xnmx', ''),
('ali@gmailm.com', 'Ali Assalem', '11111111', 'bzbxnd', 'Screenshot_20211001-154738.jpg'),
('war', 'ali', '11221122', 'homs', 'war.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banner`
--
ALTER TABLE `banner`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `orderr`
--
ALTER TABLE `orderr`
  ADD PRIMARY KEY (`OrderId`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `MenuId` (`MenuId`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `banner`
--
ALTER TABLE `banner`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `orderr`
--
ALTER TABLE `orderr`
  MODIFY `OrderId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`MenuId`) REFERENCES `menu` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
