-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 20, 2017 at 12:01 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mlb_war`
--

-- --------------------------------------------------------

--
-- Table structure for table `al_totals`
--

CREATE TABLE `al_totals` (
  `id` int(11) NOT NULL,
  `SEASON` int(11) NOT NULL,
  `PA` int(11) NOT NULL,
  `wRC` int(11) NOT NULL,
  `SB` int(11) NOT NULL,
  `CS` int(11) NOT NULL,
  `oneB` int(11) NOT NULL,
  `BB` int(11) NOT NULL,
  `HBP` int(11) NOT NULL,
  `IBB` int(11) NOT NULL,
  `battingRuns` float NOT NULL,
  `baseRunningRuns` float NOT NULL,
  `fieldingRuns` float NOT NULL,
  `positionalAdjustment` float NOT NULL,
  `HR` int(11) NOT NULL,
  `K` int(11) NOT NULL,
  `IFFB` int(11) NOT NULL,
  `IP` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `al_totals`
--

INSERT INTO `al_totals` (`id`, `SEASON`, `PA`, `wRC`, `SB`, `CS`, `oneB`, `BB`, `HBP`, `IBB`, `battingRuns`, `baseRunningRuns`, `fieldingRuns`, `positionalAdjustment`, `HR`, `K`, `IFFB`, `IP`) VALUES
(1, 2002, 86859, 10699, 1236, 579, 13405, 7325, 851, 488, -84.9, 36.4, -48.3, -215.2, 2464, 14233, 2722, 20164),
(2, 2003, 87323, 10926, 1279, 547, 13819, 7223, 882, 536, -69.9, 22.1, -56.7, -214.2, 2499, 13806, 2647, 20224.7),
(3, 2004, 88019, 11241, 1253, 573, 14010, 7486, 906, 512, -58.7, -9.1, 14.5, -220.9, 2605, 14529, 2701, 20248),
(4, 2005, 86670, 10587, 1216, 509, 13967, 6811, 817, 448, -44.4, 34.3, -49, -223, 2437, 13764, 2570, 20180),
(5, 2006, 87359, 11285, 1252, 500, 14334, 7247, 787, 529, -59.9, -40.4, 28.4, -218.4, 2546, 14229, 2444, 20121),
(6, 2007, 87493, 10974, 1354, 496, 14197, 7503, 821, 532, -50, -11.8, -36.2, -219.4, 2252, 14740, 2310, 20178.3),
(7, 2008, 87304, 10761, 1317, 488, 13938, 7521, 816, 530, -55.3, -38.7, -9.1, -227.2, 2270, 14834, 2409, 20222.3),
(8, 2009, 87211, 10886, 1541, 542, 13704, 7694, 725, 409, -99, 15, -25.8, -216.9, 2560, 15309, 2362, 20173.3),
(9, 2010, 86434, 10174, 1505, 540, 13538, 7367, 727, 457, -95.1, -19.5, -51.4, -226.1, 2209, 15214, 2216, 20217),
(10, 2011, 86171, 10029, 1600, 619, 13317, 6986, 738, 472, -79.6, -2.8, 26.4, -222.7, 2271, 15588, 2472, 20283.3),
(11, 2012, 85797, 10032, 1501, 501, 13103, 6896, 725, 421, -76.4, -35.1, 19.4, -220.5, 2500, 16618, 2217, 20285),
(12, 2013, 92401, 10501, 1428, 513, 14290, 7534, 698, 443, -106.7, 16.7, -73.6, -241.1, 2504, 18336, 2317, 21806),
(13, 2014, 91834, 10097, 1394, 492, 14350, 7091, 830, 471, -92.2, 20.9, -53.2, -238.1, 2161, 18245, 2320, 21798.7),
(14, 2015, 91485, 10593, 1185, 533, 13900, 7047, 826, 399, -74.6, -42.3, 41.2, -236.6, 2634, 18281, 2284, 21694.3),
(15, 2016, 91672, 11008, 1149, 448, 13820, 7365, 770, 342, -86.3, -43.1, 14.5, -230.5, 2953, 19096, 2320, 21611);

-- --------------------------------------------------------

--
-- Table structure for table `mlb_totals`
--

CREATE TABLE `mlb_totals` (
  `id` int(11) NOT NULL,
  `SEASON` int(11) NOT NULL,
  `wOBA` float NOT NULL,
  `wOBAScale` float NOT NULL,
  `rPerPA` float NOT NULL,
  `games` int(11) NOT NULL,
  `PA` int(11) NOT NULL,
  `ERA` float NOT NULL,
  `HR` int(11) NOT NULL,
  `BB` int(11) NOT NULL,
  `HBP` int(11) NOT NULL,
  `K` int(11) NOT NULL,
  `IFFB` int(11) NOT NULL,
  `IP` float DEFAULT NULL,
  `RA9` float NOT NULL,
  `runsPerWin` float NOT NULL,
  `runCS` float NOT NULL,
  `SB` int(11) NOT NULL,
  `CS` int(11) NOT NULL,
  `oneB` int(11) NOT NULL,
  `IBB` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mlb_totals`
--

INSERT INTO `mlb_totals` (`id`, `SEASON`, `wOBA`, `wOBAScale`, `rPerPA`, `games`, `PA`, `ERA`, `HR`, `BB`, `HBP`, `K`, `IFFB`, `IP`, `RA9`, `runsPerWin`, `runCS`, `SB`, `CS`, `oneB`, `IBB`) VALUES
(1, 2002, 0.326, 1.211, 0.12, 2430, 186632, 4.28, 5059, 16246, 1746, 31395, 5537, 43269, 4.66, 9.991, -0.42, 2750, 1282, 28593, 1452),
(2, 2003, 0.328, 1.194, 0.123, 2430, 187460, 4.4, 5207, 15889, 1849, 30803, 5419, 43335, 4.77, 10.158, -0.428, 2573, 1132, 29089, 1316),
(3, 2004, 0.33, 1.18, 0.124, 2430, 188541, 4.46, 5451, 16222, 1850, 31829, 5470, 43394, 4.85, 10.272, -0.434, 2589, 1100, 29255, 1381),
(4, 2005, 0.326, 1.208, 0.12, 2430, 186294, 4.29, 5017, 15207, 1797, 30644, 5238, 43232.3, 4.65, 9.971, -0.419, 2565, 1069, 29224, 1216),
(5, 2006, 0.332, 1.17, 0.126, 2430, 188071, 4.53, 5386, 15847, 1817, 31655, 5158, 43258, 4.91, 10.365, -0.439, 2767, 1110, 29600, 1410),
(6, 2007, 0.331, 1.192, 0.124, 2430, 186623, 4.47, 4957, 16079, 1755, 32189, 5027, 43425.7, 4.83, 10.25, -0.433, 2918, 1002, 29885, 1323),
(7, 2008, 0.328, 1.211, 0.12, 2430, 187631, 4.32, 4878, 16337, 1672, 32884, 4937, 43357.7, 4.69, 10.032, -0.422, 2799, 1035, 29194, 1310),
(8, 2009, 0.329, 1.21, 0.12, 2430, 187079, 4.32, 5042, 16620, 1590, 33591, 4856, 43272, 4.66, 9.994, -0.42, 2970, 1133, 28796, 1179),
(9, 2010, 0.321, 1.251, 0.115, 2430, 185553, 4.08, 4613, 15778, 1549, 34306, 4590, 43305.3, 4.43, 9.643, -0.403, 2959, 1129, 28589, 1216),
(10, 2011, 0.316, 1.264, 0.112, 2430, 185245, 3.94, 4552, 15018, 1554, 34488, 4994, 43527.3, 4.3, 9.454, -0.394, 3279, 1261, 28418, 1231),
(11, 2012, 0.315, 1.245, 0.114, 2430, 184179, 4.01, 4934, 14709, 1494, 36426, 4377, 43355.3, 4.36, 9.544, -0.398, 3229, 1136, 27941, 1055),
(12, 2013, 0.314, 1.277, 0.11, 2430, 184872, 3.87, 4661, 14640, 1536, 36710, 4298, 43653.3, 4.18, 9.264, -0.384, 2693, 1007, 28438, 1018),
(13, 2014, 0.31, 1.304, 0.108, 2430, 183928, 3.74, 4186, 14020, 1652, 37441, 4218, 43613.7, 4.08, 9.117, -0.377, 2764, 1035, 28423, 985),
(14, 2015, 0.313, 1.251, 0.113, 2430, 183627, 3.96, 4909, 14073, 1602, 37446, 4115, 43407.7, 4.28, 9.421, -0.392, 2505, 1064, 28016, 951),
(15, 2016, 0.318, 1.212, 0.118, 2430, 184578, 4.19, 5610, 15088, 1651, 38983, 4234, 43306.3, 4.52, 9.778, -0.41, 2537, 1001, 27538, 932);

-- --------------------------------------------------------

--
-- Table structure for table `nl_totals`
--

CREATE TABLE `nl_totals` (
  `id` int(11) NOT NULL,
  `SEASON` int(11) NOT NULL,
  `PA` int(11) NOT NULL,
  `wRC` float NOT NULL,
  `SB` int(11) NOT NULL,
  `CS` int(11) NOT NULL,
  `oneB` int(11) NOT NULL,
  `BB` int(11) NOT NULL,
  `HBP` int(11) NOT NULL,
  `IBB` int(11) NOT NULL,
  `battingRuns` float NOT NULL,
  `baseRunningRuns` float NOT NULL,
  `fieldingRuns` float NOT NULL,
  `positionalAdjustment` float NOT NULL,
  `HR` int(11) NOT NULL,
  `K` int(11) NOT NULL,
  `IFFB` int(11) NOT NULL,
  `IP` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nl_totals`
--

INSERT INTO `nl_totals` (`id`, `SEASON`, `PA`, `wRC`, `SB`, `CS`, `oneB`, `BB`, `HBP`, `IBB`, `battingRuns`, `baseRunningRuns`, `fieldingRuns`, `positionalAdjustment`, `HR`, `K`, `IFFB`, `IP`) VALUES
(1, 2002, 93717, 11720, 1514, 703, 15188, 8923, 895, 964, -776.3, -38.2, 49.4, 561.2, 2595, 17162, 2815, 23105),
(2, 2003, 93981, 12060, 1294, 585, 15270, 8666, 967, 780, -827.9, -22.3, 65.1, 592.6, 2708, 16997, 2772, 23110.3),
(3, 2004, 94375, 12166, 1336, 527, 15245, 8736, 944, 869, -870.3, 9.4, -31.1, 623, 2846, 17300, 2769, 23146),
(4, 2005, 93497, 11745, 1349, 560, 15257, 8396, 980, 768, -790.2, -34.2, 43.8, 577.6, 2580, 16880, 2668, 23052.3),
(5, 2006, 94664, 12380, 1515, 610, 15266, 8600, 1030, 881, -875.4, 40.1, -39.7, 666.9, 2840, 17426, 2714, 23137),
(6, 2007, 95178, 12359, 1564, 506, 15688, 8576, 934, 791, -796.9, 12.7, -1.7, 581.3, 2705, 17449, 2717, 23247.3),
(7, 2008, 94351, 11850, 1482, 547, 15256, 8816, 856, 780, -808.7, 38.7, -10.2, 584.3, 2608, 18050, 2528, 23135.3),
(8, 2009, 93840, 11543, 1429, 591, 15092, 8926, 865, 770, -774, -13.1, 20.9, 602.7, 2482, 18282, 2494, 23098.7),
(9, 2010, 93061, 11155, 1454, 589, 15051, 8411, 822, 759, -749.3, 18.8, 33.3, 551.8, 2404, 19092, 2374, 23088.3),
(10, 2011, 93114, 10750, 1679, 642, 15101, 8032, 816, 759, -681.4, 3.9, -28.6, 508.6, 2281, 18900, 2522, 23244),
(11, 2012, 92469, 11019, 1728, 635, 14838, 7813, 769, 634, -739.5, 35.1, -19.2, 574.3, 2434, 19808, 2160, 23070.3),
(12, 2013, 86959, 9783, 1265, 494, 14148, 7106, 838, 575, -640.9, -17.8, 74, 491.4, 2157, 18374, 1981, 21847.3),
(13, 2014, 86575, 9721, 1370, 543, 14073, 6929, 822, 514, -669.2, -20.6, 55, 507.7, 2025, 19196, 1898, 21815),
(14, 2015, 86736, 10112, 1320, 531, 14116, 7026, 776, 552, -673.8, 40.6, -44.1, 503.4, 2275, 19165, 1831, 21713.3),
(15, 2016, 87546, 10783, 1388, 553, 13718, 7723, 881, 590, -691.1, 42.7, -14.3, 516.8, 2657, 19887, 1914, 21695.1);

-- --------------------------------------------------------

--
-- Table structure for table `park_factors`
--

CREATE TABLE `park_factors` (
  `id` int(11) NOT NULL,
  `season` int(11) NOT NULL,
  `team` varchar(50) DEFAULT NULL,
  `factor` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `park_factors`
--

INSERT INTO `park_factors` (`id`, `season`, `team`, `factor`) VALUES
(1, 2002, 'Angels', 0.99),
(2, 2002, 'Orioles', 0.97),
(3, 2002, 'Red Sox', 1.02),
(4, 2002, 'White Sox', 1.03),
(5, 2002, 'Indians', 0.99),
(6, 2002, 'Tigers', 0.97),
(7, 2002, 'Royals', 1.06),
(8, 2002, 'Twins', 1.02),
(9, 2002, 'Yankees', 0.99),
(10, 2002, 'Athletics', 0.98),
(11, 2002, 'Mariners', 0.94),
(12, 2002, 'Rangers', 1.06),
(13, 2002, 'Devil Rays', 0.99),
(14, 2002, 'Blue Jays', 1.03),
(15, 2002, 'Diamondbacks', 1.05),
(16, 2002, 'Braves', 0.99),
(17, 2002, 'Cubs', 0.98),
(18, 2002, 'Reds', 1.02),
(19, 2002, 'Rockies', 1.18),
(20, 2002, 'Astros', 1.04),
(21, 2002, 'Marlins', 0.96),
(22, 2002, 'Dodgers', 0.94),
(23, 2002, 'Brewers', 1),
(24, 2002, 'Mets', 0.96),
(25, 2002, 'Expos', 1.03),
(26, 2002, 'Phillies', 0.98),
(27, 2002, 'Pirates', 1),
(28, 2002, 'Padres', 0.93),
(29, 2002, 'Cardinals', 0.98),
(30, 2002, 'Giants', 0.96),
(31, 2003, 'Orioles', 0.97),
(32, 2003, 'Red Sox', 1.02),
(33, 2003, 'White Sox', 1.02),
(34, 2003, 'Indians', 0.98),
(35, 2003, 'Angels', 0.98),
(36, 2003, 'Royals', 1.05),
(37, 2003, 'Tigers', 0.97),
(38, 2003, 'Twins', 1.01),
(39, 2003, 'Yankees', 0.99),
(40, 2003, 'Mariners', 0.95),
(41, 2003, 'Athletics', 0.99),
(42, 2003, 'Devil Rays', 0.99),
(43, 2003, 'Braves', 1),
(44, 2003, 'Rangers', 1.06),
(45, 2003, 'Diamondbacks', 1.05),
(46, 2003, 'Blue Jays', 1.03),
(47, 2003, 'Cubs', 1),
(48, 2003, 'Reds', 1.01),
(49, 2003, 'Marlins', 0.96),
(50, 2003, 'Rockies', 1.15),
(51, 2003, 'Expos', 1.03),
(52, 2003, 'Brewers', 1),
(53, 2003, 'Dodgers', 0.94),
(54, 2003, 'Astros', 1.02),
(55, 2003, 'Mets', 0.97),
(56, 2003, 'Pirates', 1),
(57, 2003, 'Phillies', 0.98),
(58, 2003, 'Giants', 0.97),
(59, 2003, 'Padres', 0.93),
(60, 2003, 'Cardinals', 0.98),
(61, 2004, 'Orioles', 0.98),
(62, 2004, 'Red Sox', 1.02),
(63, 2004, 'White Sox', 1.02),
(64, 2004, 'Indians', 0.97),
(65, 2004, 'Angels', 0.97),
(66, 2004, 'Royals', 1.05),
(67, 2004, 'Tigers', 0.97),
(68, 2004, 'Twins', 1),
(69, 2004, 'Yankees', 0.98),
(70, 2004, 'Mariners', 0.96),
(71, 2004, 'Athletics', 0.99),
(72, 2004, 'Devil Rays', 0.99),
(73, 2004, 'Braves', 0.99),
(74, 2004, 'Rangers', 1.07),
(75, 2004, 'Diamondbacks', 1.06),
(76, 2004, 'Blue Jays', 1.03),
(77, 2004, 'Cubs', 1.01),
(78, 2004, 'Reds', 1.01),
(79, 2004, 'Marlins', 0.96),
(80, 2004, 'Rockies', 1.12),
(81, 2004, 'Expos', 1.03),
(82, 2004, 'Brewers', 1),
(83, 2004, 'Dodgers', 0.96),
(84, 2004, 'Astros', 1.01),
(85, 2004, 'Mets', 0.97),
(86, 2004, 'Pirates', 1),
(87, 2004, 'Phillies', 1.02),
(88, 2004, 'Giants', 0.99),
(89, 2004, 'Padres', 0.92),
(90, 2004, 'Cardinals', 0.98),
(91, 2005, 'Orioles', 0.99),
(92, 2005, 'Red Sox', 1.04),
(93, 2005, 'White Sox', 1.03),
(94, 2005, 'Indians', 0.98),
(95, 2005, 'Angels', 0.98),
(96, 2005, 'Royals', 1.03),
(97, 2005, 'Tigers', 0.98),
(98, 2005, 'Twins', 0.99),
(99, 2005, 'Yankees', 0.99),
(100, 2005, 'Mariners', 0.96),
(101, 2005, 'Athletics', 0.97),
(102, 2005, 'Devil Rays', 0.98),
(103, 2005, 'Braves', 0.99),
(104, 2005, 'Rangers', 1.05),
(105, 2005, 'Diamondbacks', 1.05),
(106, 2005, 'Blue Jays', 1.02),
(107, 2005, 'Cubs', 1.03),
(108, 2005, 'Reds', 1.01),
(109, 2005, 'Marlins', 0.97),
(110, 2005, 'Rockies', 1.1),
(111, 2005, 'Nationals', 0.96),
(112, 2005, 'Brewers', 1.01),
(113, 2005, 'Dodgers', 0.98),
(114, 2005, 'Astros', 1),
(115, 2005, 'Mets', 0.98),
(116, 2005, 'Pirates', 0.99),
(117, 2005, 'Phillies', 1.02),
(118, 2005, 'Giants', 1),
(119, 2005, 'Padres', 0.92),
(120, 2005, 'Cardinals', 0.98),
(121, 2006, 'Orioles', 1.01),
(122, 2006, 'Red Sox', 1.03),
(123, 2006, 'White Sox', 1.04),
(124, 2006, 'Indians', 0.99),
(125, 2006, 'Angels', 0.99),
(126, 2006, 'Royals', 1),
(127, 2006, 'Tigers', 1),
(128, 2006, 'Twins', 0.98),
(129, 2006, 'Yankees', 1),
(130, 2006, 'Mariners', 0.96),
(131, 2006, 'Athletics', 0.98),
(132, 2006, 'Devil Rays', 0.98),
(133, 2006, 'Braves', 1),
(134, 2006, 'Rangers', 1.04),
(135, 2006, 'Diamondbacks', 1.05),
(136, 2006, 'Blue Jays', 1.01),
(137, 2006, 'Cubs', 1.04),
(138, 2006, 'Reds', 1.02),
(139, 2006, 'Marlins', 0.98),
(140, 2006, 'Rockies', 1.09),
(141, 2006, 'Nationals', 0.96),
(142, 2006, 'Brewers', 1),
(143, 2006, 'Dodgers', 0.98),
(144, 2006, 'Astros', 0.99),
(145, 2006, 'Mets', 0.97),
(146, 2006, 'Pirates', 0.98),
(147, 2006, 'Phillies', 1.02),
(148, 2006, 'Giants', 1.01),
(149, 2006, 'Padres', 0.92),
(150, 2006, 'Cardinals', 0.97),
(151, 2007, 'Orioles', 1),
(152, 2007, 'Red Sox', 1.03),
(153, 2007, 'White Sox', 1.03),
(154, 2007, 'Indians', 0.98),
(155, 2007, 'Angels', 1),
(156, 2007, 'Royals', 1.02),
(157, 2007, 'Tigers', 1.01),
(158, 2007, 'Twins', 0.98),
(159, 2007, 'Yankees', 1),
(160, 2007, 'Mariners', 0.97),
(161, 2007, 'Athletics', 0.97),
(162, 2007, 'Devil Rays', 0.99),
(163, 2007, 'Braves', 0.99),
(164, 2007, 'Rangers', 1.03),
(165, 2007, 'Diamondbacks', 1.06),
(166, 2007, 'Blue Jays', 1),
(167, 2007, 'Cubs', 1.04),
(168, 2007, 'Reds', 1.04),
(169, 2007, 'Marlins', 0.99),
(170, 2007, 'Rockies', 1.08),
(171, 2007, 'Nationals', 0.96),
(172, 2007, 'Brewers', 0.99),
(173, 2007, 'Dodgers', 0.97),
(174, 2007, 'Astros', 0.99),
(175, 2007, 'Mets', 0.97),
(176, 2007, 'Pirates', 0.99),
(177, 2007, 'Phillies', 1.03),
(178, 2007, 'Giants', 1),
(179, 2007, 'Padres', 0.91),
(180, 2007, 'Cardinals', 0.97),
(181, 2008, 'Orioles', 1.02),
(182, 2008, 'Red Sox', 1.04),
(183, 2008, 'White Sox', 1.04),
(184, 2008, 'Indians', 0.99),
(185, 2008, 'Angels', 0.99),
(186, 2008, 'Royals', 1.02),
(187, 2008, 'Tigers', 1.01),
(188, 2008, 'Twins', 0.98),
(189, 2008, 'Yankees', 1),
(190, 2008, 'Mariners', 0.96),
(191, 2008, 'Athletics', 0.97),
(192, 2008, 'Rays', 0.97),
(193, 2008, 'Braves', 0.98),
(194, 2008, 'Rangers', 1.03),
(195, 2008, 'Diamondbacks', 1.05),
(196, 2008, 'Blue Jays', 1),
(197, 2008, 'Cubs', 1.05),
(198, 2008, 'Reds', 1.03),
(199, 2008, 'Marlins', 1.01),
(200, 2008, 'Rockies', 1.08),
(201, 2008, 'Nationals', 1),
(202, 2008, 'Brewers', 0.99),
(203, 2008, 'Dodgers', 0.98),
(204, 2008, 'Astros', 0.98),
(205, 2008, 'Mets', 0.97),
(206, 2008, 'Pirates', 0.99),
(207, 2008, 'Phillies', 1.01),
(208, 2008, 'Giants', 1),
(209, 2008, 'Padres', 0.92),
(210, 2008, 'Cardinals', 0.97),
(211, 2009, 'Orioles', 1.02),
(212, 2009, 'Red Sox', 1.05),
(213, 2009, 'White Sox', 1.04),
(214, 2009, 'Indians', 0.99),
(215, 2009, 'Angels', 0.99),
(216, 2009, 'Royals', 1.01),
(217, 2009, 'Tigers', 1.02),
(218, 2009, 'Twins', 0.98),
(219, 2009, 'Yankees', 1.03),
(220, 2009, 'Mariners', 0.96),
(221, 2009, 'Athletics', 0.97),
(222, 2009, 'Rays', 0.95),
(223, 2009, 'Braves', 0.99),
(224, 2009, 'Rangers', 1.05),
(225, 2009, 'Diamondbacks', 1.05),
(226, 2009, 'Blue Jays', 1),
(227, 2009, 'Cubs', 1.04),
(228, 2009, 'Reds', 1.02),
(229, 2009, 'Marlins', 1.01),
(230, 2009, 'Rockies', 1.1),
(231, 2009, 'Nationals', 1),
(232, 2009, 'Brewers', 0.99),
(233, 2009, 'Dodgers', 0.97),
(234, 2009, 'Astros', 0.98),
(235, 2009, 'Mets', 0.97),
(236, 2009, 'Pirates', 0.99),
(237, 2009, 'Phillies', 1.01),
(238, 2009, 'Giants', 0.98),
(239, 2009, 'Padres', 0.91),
(240, 2009, 'Cardinals', 0.97),
(241, 2010, 'Orioles', 1.03),
(242, 2010, 'Red Sox', 1.05),
(243, 2010, 'White Sox', 1.05),
(244, 2010, 'Indians', 0.97),
(245, 2010, 'Angels', 0.96),
(246, 2010, 'Royals', 1.01),
(247, 2010, 'Tigers', 1.02),
(248, 2010, 'Twins', 1.01),
(249, 2010, 'Yankees', 1.03),
(250, 2010, 'Mariners', 0.94),
(251, 2010, 'Athletics', 0.97),
(252, 2010, 'Rays', 0.95),
(253, 2010, 'Braves', 1),
(254, 2010, 'Rangers', 1.07),
(255, 2010, 'Diamondbacks', 1.06),
(256, 2010, 'Blue Jays', 1.01),
(257, 2010, 'Cubs', 1.03),
(258, 2010, 'Reds', 1.02),
(259, 2010, 'Marlins', 1.01),
(260, 2010, 'Rockies', 1.13),
(261, 2010, 'Nationals', 1),
(262, 2010, 'Brewers', 1),
(263, 2010, 'Dodgers', 0.95),
(264, 2010, 'Astros', 0.99),
(265, 2010, 'Mets', 0.97),
(266, 2010, 'Pirates', 0.97),
(267, 2010, 'Phillies', 1),
(268, 2010, 'Giants', 0.96),
(269, 2010, 'Padres', 0.92),
(270, 2010, 'Cardinals', 0.97),
(271, 2011, 'Orioles', 1.03),
(272, 2011, 'Red Sox', 1.04),
(273, 2011, 'White Sox', 1.04),
(274, 2011, 'Indians', 0.96),
(275, 2011, 'Angels', 0.96),
(276, 2011, 'Royals', 1.02),
(277, 2011, 'Tigers', 1.02),
(278, 2011, 'Twins', 1.01),
(279, 2011, 'Yankees', 1.03),
(280, 2011, 'Mariners', 0.94),
(281, 2011, 'Athletics', 0.97),
(282, 2011, 'Rays', 0.95),
(283, 2011, 'Braves', 0.99),
(284, 2011, 'Rangers', 1.06),
(285, 2011, 'Diamondbacks', 1.04),
(286, 2011, 'Blue Jays', 1.02),
(287, 2011, 'Cubs', 1.04),
(288, 2011, 'Reds', 1.01),
(289, 2011, 'Marlins', 1.01),
(290, 2011, 'Rockies', 1.15),
(291, 2011, 'Nationals', 1),
(292, 2011, 'Brewers', 1.01),
(293, 2011, 'Dodgers', 0.95),
(294, 2011, 'Astros', 0.99),
(295, 2011, 'Mets', 0.97),
(296, 2011, 'Pirates', 0.97),
(297, 2011, 'Phillies', 1.01),
(298, 2011, 'Giants', 0.94),
(299, 2011, 'Padres', 0.92),
(300, 2011, 'Cardinals', 0.97),
(301, 2012, 'Orioles', 1.02),
(302, 2012, 'Red Sox', 1.04),
(303, 2012, 'White Sox', 1.04),
(304, 2012, 'Indians', 0.97),
(305, 2012, 'Angels', 0.95),
(306, 2012, 'Royals', 1.01),
(307, 2012, 'Tigers', 1.02),
(308, 2012, 'Twins', 1.01),
(309, 2012, 'Yankees', 1.03),
(310, 2012, 'Mariners', 0.94),
(311, 2012, 'Athletics', 0.97),
(312, 2012, 'Rays', 0.95),
(313, 2012, 'Braves', 0.99),
(314, 2012, 'Rangers', 1.06),
(315, 2012, 'Diamondbacks', 1.04),
(316, 2012, 'Blue Jays', 1.03),
(317, 2012, 'Cubs', 1.02),
(318, 2012, 'Reds', 1.01),
(319, 2012, 'Marlins', 0.98),
(320, 2012, 'Rockies', 1.17),
(321, 2012, 'Nationals', 1),
(322, 2012, 'Brewers', 1.03),
(323, 2012, 'Dodgers', 0.96),
(324, 2012, 'Astros', 1),
(325, 2012, 'Mets', 0.95),
(326, 2012, 'Pirates', 0.97),
(327, 2012, 'Phillies', 1),
(328, 2012, 'Giants', 0.93),
(329, 2012, 'Padres', 0.92),
(330, 2012, 'Cardinals', 0.98),
(331, 2013, 'Orioles', 1.03),
(332, 2013, 'Red Sox', 1.05),
(333, 2013, 'White Sox', 1.02),
(334, 2013, 'Indians', 1),
(335, 2013, 'Angels', 0.95),
(336, 2013, 'Royals', 1.01),
(337, 2013, 'Tigers', 1.01),
(338, 2013, 'Twins', 1.01),
(339, 2013, 'Yankees', 1.01),
(340, 2013, 'Mariners', 0.96),
(341, 2013, 'Athletics', 0.97),
(342, 2013, 'Rays', 0.96),
(343, 2013, 'Braves', 0.99),
(344, 2013, 'Rangers', 1.06),
(345, 2013, 'Diamondbacks', 1.04),
(346, 2013, 'Blue Jays', 1.02),
(347, 2013, 'Cubs', 1),
(348, 2013, 'Reds', 1.02),
(349, 2013, 'Marlins', 0.98),
(350, 2013, 'Rockies', 1.17),
(351, 2013, 'Nationals', 1),
(352, 2013, 'Brewers', 1.04),
(353, 2013, 'Dodgers', 0.96),
(354, 2013, 'Astros', 1),
(355, 2013, 'Mets', 0.95),
(356, 2013, 'Pirates', 0.96),
(357, 2013, 'Phillies', 1),
(358, 2013, 'Giants', 0.92),
(359, 2013, 'Padres', 0.95),
(360, 2013, 'Cardinals', 0.98),
(361, 2014, 'Orioles', 1.02),
(362, 2014, 'Red Sox', 1.05),
(363, 2014, 'White Sox', 1.01),
(364, 2014, 'Indians', 1.02),
(365, 2014, 'Angels', 0.95),
(366, 2014, 'Royals', 1.03),
(367, 2014, 'Tigers', 1.01),
(368, 2014, 'Twins', 1.02),
(369, 2014, 'Yankees', 1.01),
(370, 2014, 'Mariners', 0.96),
(371, 2014, 'Athletics', 0.96),
(372, 2014, 'Rays', 0.97),
(373, 2014, 'Braves', 0.99),
(374, 2014, 'Rangers', 1.04),
(375, 2014, 'Diamondbacks', 1.05),
(376, 2014, 'Blue Jays', 1.02),
(377, 2014, 'Cubs', 0.99),
(378, 2014, 'Reds', 1.01),
(379, 2014, 'Marlins', 0.98),
(380, 2014, 'Rockies', 1.18),
(381, 2014, 'Nationals', 1),
(382, 2014, 'Brewers', 1.03),
(383, 2014, 'Dodgers', 0.95),
(384, 2014, 'Astros', 0.98),
(385, 2014, 'Mets', 0.95),
(386, 2014, 'Pirates', 0.96),
(387, 2014, 'Phillies', 0.99),
(388, 2014, 'Giants', 0.95),
(389, 2014, 'Padres', 0.95),
(390, 2014, 'Cardinals', 0.98),
(391, 2015, 'Orioles', 1.01),
(392, 2015, 'Red Sox', 1.04),
(393, 2015, 'White Sox', 0.99),
(394, 2015, 'Indians', 1.03),
(395, 2015, 'Angels', 0.97),
(396, 2015, 'Royals', 1.02),
(397, 2015, 'Tigers', 1.01),
(398, 2015, 'Twins', 1.03),
(399, 2015, 'Yankees', 1.01),
(400, 2015, 'Mariners', 0.96),
(401, 2015, 'Athletics', 0.98),
(402, 2015, 'Rays', 0.98),
(403, 2015, 'Braves', 0.99),
(404, 2015, 'Rangers', 1.04),
(405, 2015, 'Diamondbacks', 1.05),
(406, 2015, 'Blue Jays', 1.01),
(407, 2015, 'Cubs', 1),
(408, 2015, 'Reds', 1.01),
(409, 2015, 'Marlins', 0.97),
(410, 2015, 'Rockies', 1.15),
(411, 2015, 'Nationals', 1.01),
(412, 2015, 'Brewers', 1.02),
(413, 2015, 'Dodgers', 0.95),
(414, 2015, 'Astros', 0.97),
(415, 2015, 'Mets', 0.95),
(416, 2015, 'Pirates', 0.98),
(417, 2015, 'Phillies', 1),
(418, 2015, 'Giants', 0.96),
(419, 2015, 'Padres', 0.95),
(420, 2015, 'Cardinals', 0.97);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `al_totals`
--
ALTER TABLE `al_totals`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mlb_totals`
--
ALTER TABLE `mlb_totals`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nl_totals`
--
ALTER TABLE `nl_totals`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `park_factors`
--
ALTER TABLE `park_factors`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `al_totals`
--
ALTER TABLE `al_totals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `mlb_totals`
--
ALTER TABLE `mlb_totals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `nl_totals`
--
ALTER TABLE `nl_totals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `park_factors`
--
ALTER TABLE `park_factors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=421;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
