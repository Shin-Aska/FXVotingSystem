-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 02, 2016 at 09:09 PM
-- Server version: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `votingsystem-um`
--

-- --------------------------------------------------------

--
-- Table structure for table `candidate`
--

CREATE TABLE `candidate` (
  `id` int(11) NOT NULL,
  `posterID` int(11) NOT NULL DEFAULT '1',
  `name` mediumtext,
  `votecount` int(10) UNSIGNED DEFAULT '0',
  `votecategory` enum('Qualitative','Quantitative') NOT NULL DEFAULT 'Qualitative'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `candidate`
--

INSERT INTO `candidate` (`id`, `posterID`, `name`, `votecount`, `votecategory`) VALUES
(56, 33, 'ICT Approach in Storytelling and Reading Comprehension of Grade III Pupils', 0, 'Qualitative'),
(4, 1, 'Social Work', 0, 'Quantitative'),
(5, 2, 'Engaging Learning Initiavies and General Learner Outcomes: Basis for Learn-to-learn Kit', 0, 'Quantitative'),
(6, 3, 'Occupational Stress of Provincial Police Office Personel: Basis for Coping Intervention Scheme', 0, 'Quantitative'),
(14, 7, 'The Academic Journey of Working Students: Along the Threshold of Hope', 0, 'Qualitative'),
(8, 4, 'Factors Affecting the Use of Vulgar Language among Intermediate Students in Davao Central District', 0, 'Quantitative'),
(55, 33, 'Domestic Violence in Interracial Relationship: A Multiple Case Study of Filipino Women', 0, 'Qualitative'),
(53, 32, 'Trilingual Reading Scaffolds as Instructional Media in Teaching Reading for Grade 1 Classes', 0, 'Qualitative'),
(52, 31, 'Teenage Pregnancy: A Phenomenological Case Study', 0, 'Qualitative'),
(48, 29, 'Discourse Analysis of Research Abstracts in Applied Linguistics', 0, 'Qualitative'),
(15, 5, 'Competency of Teacher in Teaching Mother Tongue: Based on Multilingual Education', 0, 'Quantitative'),
(50, 30, 'Issues and Challenges of Filipino Online Teachers in Teaching English as Foreign Language: A Case of Filipino Teachers', 0, 'Qualitative'),
(17, 6, 'Magsasaka: Farming Mobile Game', 0, 'Quantitative'),
(18, 7, 'The Intrinsic Valuation of Global Oil Stock Price: Graham Number Approach', 0, 'Quantitative'),
(47, 28, 'The Struggles of Master Teachers on Pedagogical Development: A Phenomenological Study', 0, 'Qualitative'),
(20, 8, 'Knowledge, Attitude and Interest on Biotechnology of High School Students: Exploring the Possibility of Offering the Degree in Higher Education', 0, 'Quantitative'),
(22, 9, 'Cultural Competence of Midsayap Police Office: Basis for Proposed Intervention Scheme', 0, 'Quantitative'),
(46, 27, 'Should I Go or Should I Stay? Exploring the Minds of Gay School Heads', 0, 'Qualitative'),
(24, 10, 'Organizational Effectiveness of Manufacturing Industry in Region XIII: A Structural Equation Model', 0, 'Quantitative'),
(45, 26, 'Young Criminologist in the Teaching Practice: Plight and Aspirations', 0, 'Qualitative'),
(43, 25, 'The Pedagogical Journey of Closet Male Educators: A Phenomenology of Disorientation', 0, 'Qualitative'),
(27, 11, 'Groundwater Modeling as Influenced by Climate Change Impact of Talomo-Lipadas Watersheds, Davao City, Philippines', 0, 'Quantitative'),
(40, 23, 'Impact of the Support Programs to the Agrarian Reform Communities in Central Mindanao', 0, 'Qualitative'),
(41, 24, 'Two Faces of Interlanguage Fossilization: A Multiple Case Study', 0, 'Qualitative'),
(30, 12, 'Count Model Estimation of Hospital Confinements', 0, 'Quantitative'),
(38, 22, 'Collaborative Professional Cheating in National Achievement Tests: Narratives of Concerned Teachers', 0, 'Qualitative'),
(37, 21, 'Literary Warrant for Mindanao Languages', 0, 'Qualitative'),
(35, 13, 'Performance-Based Training Effectiveness Evaluation: Faculty Retooling in Focus', 0, 'Quantitative'),
(36, 20, 'Status Single: Intertwined Stories of Love and Health', 0, 'Qualitative'),
(34, 19, 'Family Intervention in the Rehabilitation of Drug Dependents: A Phenomenology of Maternal Involvement', 0, 'Qualitative'),
(32, 17, 'Shelved Knowledge: The Necessity of Viable Research Endeavor', 0, 'Qualitative'),
(39, 14, 'Enforcement and Violation of Philippine Ecological Solid Waste Management Act of 2000(R.A No. 9003))', 0, 'Quantitative'),
(33, 18, 'HIV Testing for Closet Gays: A Phenomenology of Motivation', 0, 'Qualitative'),
(31, 16, 'Practitioners of English for Specific Purposes: Pedagogical Role in Context', 0, 'Qualitative'),
(42, 15, 'Internship Program on the College of Arts and Sciences: An Evaluation', 0, 'Quantitative'),
(29, 15, 'Leading the Machos: Female Officers in Context', 0, 'Qualitative'),
(44, 16, 'Accreditation Parameters of Private Higher Education Institutions: A Benchmark for Research-Based Curriculum', 0, 'Quantitative'),
(28, 14, 'The Lived Experiences of Bangsamoro Women in Armed Conflict Affected Area in Maguindanao', 0, 'Qualitative'),
(26, 13, 'Clinical Supervision as Professional Development: Through the Lens of Public School Teachers', 0, 'Qualitative'),
(49, 17, 'Crafting a Continual Improvement Plan for Selected Philippine Based ISO 9001:2008 Quality Management System Certified Manufacturing Companies', 0, 'Quantitative'),
(25, 12, 'University of Mindanao Assisted Resettlement: A Best Practice in Southeast Asia', 0, 'Qualitative'),
(51, 18, 'Access and Use of e-resources: Basis of Enhanced Information literacy Program', 0, 'Quantitative'),
(23, 11, 'The Professional Librarians in the Practice of their Profession in the Contemporary Setting: A Phenomenological Study', 0, 'Qualitative'),
(21, 10, 'Touching Dispositions of Students with Biethnic Identity: A Phenomenology', 0, 'Qualitative'),
(54, 19, 'The Influence of Student Affairs and Academic Affairs Programs towards Campus Climate', 0, 'Quantitative'),
(19, 9, 'The Dynamics of Micro-Politics in Academic Setting: Teachers Perspective', 0, 'Qualitative'),
(16, 8, 'From Womb to Tomb: Reinventing Balungis Inaul Weavings for Comtemporary Fashion', 0, 'Qualitative'),
(58, 20, 'San Vicente Water Distribution: Evaluation and Design Enhancement', 0, 'Quantitative'),
(59, 21, 'A Three Day Fun-Fair for Children with Special Needs: Parents and Students Perspectives', 0, 'Quantitative'),
(60, 22, 'Regular Public School Teacher Skills in Handling Children witht Autism', 0, 'Quantitative'),
(61, 23, 'A Model of Institutional Leadership in the Context Change', 0, 'Quantitative'),
(62, 24, '21st Century Skills of Teachers and Self-Efficacy of College Students', 0, 'Quantitative'),
(63, 25, 'Management Skills of Polica Officers in Region XIII as Determinants of Organizational Performace: Basis for Intervention Plan', 0, 'Quantitative'),
(64, 26, 'Conversational Leadership of School Heads and Teacher Sense of Self-Efficacy', 0, 'Quantitative'),
(65, 27, 'Rationalizing an Optimal Minimum Wage Compensation Package: An Input to Policy Formulation', 0, 'Quantitative'),
(66, 28, 'Leadership Styles as Function of Credibility of Barangay Chairmen in Davao City Poblacion', 0, 'Quantitative'),
(67, 29, 'Negative Media Portrayal of Politicians and Public Trust toward Governance among Voters in Davao City, Philippines', 0, 'Quantitative'),
(68, 30, 'Civil  Engineering Students\' Reading Proficiency: Bases for RCSP Module Production', 0, 'Quantitative'),
(69, 31, 'Facebook Usage and Study Habits of Junior High School Students', 0, 'Quantitative'),
(70, 32, 'Partial Mitochondrial DNA Barcode of Rafflesia mira using matR with Phylogenetic Analysis of Selected Rafflesia Species in the World', 0, 'Quantitative'),
(71, 33, 'Mediating Effect of Job Satisfaction on the Relationship between transformational Leadership and Quality of Worklife of Certified Public Accountants', 0, 'Quantitative'),
(72, 34, 'Crime and Inflation rates in the Philippines: A Co-integration Analysis', 0, 'Quantitative'),
(73, 35, 'Workplace Spirituality and Motivation to Achieve: Their Relationship to Employee Engagement', 0, 'Quantitative'),
(74, 36, 'The Factors and Influences of Customer Satisfaction for Integrity Office Supplies', 0, 'Quantitative'),
(75, 37, 'The Influence of Organizational Values of Interpersonal Skills on the Public Image of School Heads in Region XI', 0, 'Quantitative'),
(76, 38, 'Master of Science in Social Work: A Tracer Study', 0, 'Quantitative'),
(77, 39, 'Sports Consumption Behavior in Watching the Basketball Games Among Students of National Collegiate Athletic Association(NCAA)', 0, 'Quantitative'),
(78, 40, 'Preference of Students on the Format of Options in a Multiple-Choice Test', 0, 'Quantitative'),
(79, 41, 'Whistleblowing Intensions among Employees in Selected Government Agencies in Davao City: Basis for Policy Formulation', 0, 'Quantitative'),
(80, 42, 'Organizational Culture and Performance of Private HEIs Region XI', 0, 'Quantitative'),
(81, 43, 'Measurement and Correlates of Work Enthusiasm, Adherence to Work Goals, Work Absorption and Organizational Commitment among the Teachers in Public Secodary Schools in North Cotabato', 0, 'Quantitative'),
(82, 44, 'Matching the Criminology Program with the needs of the External Stakeholders', 0, 'Quantitative'),
(83, 45, 'CBSTBF on Coco Sap Production and TechnoMart Projects: Propelling the Cocosugar Industry Development in Davao City', 0, 'Quantitative'),
(84, 46, 'Employement Status Criminology Graduates', 0, 'Quantitative'),
(85, 47, 'Diversity Management and Organizational Culture in Relation to Employee Attitudes', 0, 'Quantitative'),
(86, 48, 'Determining Statistical Pattern on the Drug-Related Killing in Philippines Using ARIMA and POISSON Techniques', 0, 'Quantitative'),
(87, 49, 'Contribution of Continuing Professional Development to Career Advancement of a Certified Public Accountant', 0, 'Quantitative'),
(88, 50, 'A Model of Catholicity for Catholic Schools', 0, 'Quantitative'),
(89, 51, 'Organizational Culture and Job Satisfaction at PLDT Company: Davao Customer Service Zone', 0, 'Quantitative'),
(90, 52, 'Monte Carlo Geometric Brownian Motion: Peso-Dollar Exchange Rate Model Application', 0, 'Quantitative'),
(91, 53, 'Effectiveness of the University of Mindanao Microsoft Boot Camp training Program in Developing faculty and Staff Basic Computing Skills', 0, 'Quantitative'),
(13, 6, 'The Dwinding Demographic of Male Educators in Public Schools: A Multiple Case Study', 0, 'Qualitative'),
(12, 5, 'The Academic Struggles of Teenage Fathers: A Multiple Case Study', 0, 'Qualitative'),
(11, 4, 'The Two Faces of Code Switching: A Phenomenology', 0, 'Qualitative'),
(10, 3, 'Discrimination among Differetly Abled Students: A Multiple Case Study', 0, 'Qualitative'),
(9, 2, 'From Carers with Love: Landscapes of Linguistic Accommodation for the Elderly', 0, 'Qualitative'),
(7, 1, 'Waray Love Songs of the Past and the Present: A Perceived Cultural Deciption', 0, 'Qualitative'),
(57, 34, 'Student\'s Experiences in Translation and Adaptation: Exploring Creative Language Learning', 0, 'Qualitative');

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `value` varchar(45) NOT NULL,
  `category` enum('Qualitative','Quantitative') NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`id`, `value`, `category`) VALUES
(1, 'RP5TD', 'Qualitative'),
(2, 'UM8IY', 'Qualitative'),
(3, 'OPYX3', 'Qualitative'),
(4, 'UKS42', 'Qualitative'),
(5, 'K9X3R', 'Qualitative'),
(6, 'PEXDE', 'Qualitative'),
(7, 'IT0FW', 'Qualitative'),
(8, '0WO4L', 'Qualitative'),
(9, '6E94N', 'Qualitative'),
(10, 'IHUGQ', 'Qualitative'),
(11, '3TBH7', 'Qualitative'),
(12, 'FM4ZR', 'Qualitative'),
(13, 'W3KDS', 'Qualitative'),
(14, 'XD4BN', 'Qualitative'),
(15, 'VYQ33', 'Qualitative'),
(16, 'SVGD5', 'Qualitative'),
(17, 'J2Y56', 'Qualitative'),
(18, 'PX00K', 'Qualitative'),
(19, 'ZRBFY', 'Qualitative'),
(20, 'CAB56', 'Qualitative'),
(21, 'THT4P', 'Qualitative'),
(22, '24IJT', 'Qualitative'),
(23, '55QZ4', 'Qualitative'),
(24, 'N1Z2P', 'Qualitative'),
(25, 'CT3BS', 'Qualitative'),
(26, 'TQSCU', 'Qualitative'),
(27, 'GC3XA', 'Qualitative'),
(28, '070IC', 'Qualitative'),
(29, 'MMZ82', 'Qualitative'),
(30, 'QFGP6', 'Qualitative'),
(31, 'VQU72', 'Qualitative'),
(32, 'AF6BH', 'Qualitative'),
(33, 'SDTJ7', 'Qualitative'),
(34, 'O8Z6P', 'Qualitative'),
(35, '1J9HX', 'Qualitative'),
(36, '5Y1I0', 'Qualitative'),
(37, 'IKTK5', 'Qualitative'),
(38, '01FZU', 'Qualitative'),
(39, 'Q5ESI', 'Qualitative'),
(40, '0EGFS', 'Qualitative'),
(41, 'AMK0U', 'Qualitative'),
(42, 'J37T5', 'Qualitative'),
(43, '10ERK', 'Qualitative'),
(44, 'ZZSM8', 'Qualitative'),
(45, '5UX3S', 'Qualitative'),
(46, 'J8PVL', 'Qualitative'),
(47, 'CB053', 'Qualitative'),
(48, '5XXJN', 'Qualitative'),
(49, '3S4DM', 'Qualitative'),
(50, 'B9SL1', 'Qualitative'),
(51, 'YPAVC', 'Qualitative'),
(52, 'GVF9R', 'Qualitative'),
(53, 'GK3SM', 'Qualitative'),
(54, '5ISJT', 'Qualitative'),
(55, 'UYY2E', 'Qualitative'),
(56, 'WYHCV', 'Qualitative'),
(57, 'NJ8F8', 'Qualitative'),
(58, 'REIP2', 'Qualitative'),
(59, 'JZKVE', 'Qualitative'),
(60, 'K33XU', 'Qualitative'),
(61, '2T18V', 'Qualitative'),
(62, 'L1SYT', 'Qualitative'),
(63, '40BXL', 'Qualitative'),
(64, 'IZN5G', 'Qualitative'),
(65, 'O9R4W', 'Qualitative'),
(66, 'QIUH9', 'Qualitative'),
(67, 'L7SN3', 'Qualitative'),
(68, 'YAK8M', 'Qualitative'),
(69, 'NEUUR', 'Qualitative'),
(70, '413QZ', 'Qualitative'),
(71, 'UMS89', 'Qualitative'),
(72, '95GTV', 'Qualitative'),
(73, 'J0SHX', 'Qualitative'),
(74, 'FUWL4', 'Qualitative'),
(75, 'E7NLS', 'Qualitative'),
(76, 'B5N0A', 'Qualitative'),
(77, 'G29G9', 'Qualitative'),
(78, 'ZF67E', 'Qualitative'),
(79, 'HZ150', 'Qualitative'),
(80, 'VZJ57', 'Qualitative'),
(81, '6KTJ5', 'Qualitative'),
(82, 'P75OI', 'Qualitative'),
(83, 'JA7QZ', 'Qualitative'),
(84, '4CVTE', 'Qualitative'),
(85, '5G9NU', 'Qualitative'),
(86, 'HQCT8', 'Qualitative'),
(87, 'VAW9M', 'Qualitative'),
(88, 'HRL0B', 'Qualitative'),
(89, 'OSKBD', 'Qualitative'),
(90, 'A0LY5', 'Qualitative'),
(91, 'Q8X9Y', 'Qualitative'),
(92, 'NT7Y1', 'Qualitative'),
(93, '3XLW7', 'Qualitative'),
(94, 'PRE7E', 'Qualitative'),
(95, '3NLWP', 'Qualitative'),
(96, 'L1Z5P', 'Qualitative'),
(97, '5XSP8', 'Qualitative'),
(98, 'TNUDN', 'Qualitative'),
(99, 'EWAHZ', 'Qualitative'),
(100, 'KGMFX', 'Qualitative'),
(101, 'XFAO8', 'Qualitative'),
(102, 'NM7CW', 'Qualitative'),
(103, 'BJ8NS', 'Qualitative'),
(104, 'S8I2S', 'Qualitative'),
(105, 'ELQRS', 'Qualitative'),
(106, 'UQU48', 'Qualitative'),
(107, 'E9VW3', 'Qualitative'),
(108, 'IG3K8', 'Qualitative'),
(109, 'SAUUJ', 'Qualitative'),
(110, 'IFN38', 'Qualitative'),
(111, 'O6GTD', 'Qualitative'),
(112, 'J17G8', 'Qualitative'),
(113, '2RJ46', 'Qualitative'),
(114, 'PIBNQ', 'Qualitative'),
(115, 'DDD6H', 'Qualitative'),
(116, 'AZUWJ', 'Qualitative'),
(117, '6AFAR', 'Qualitative'),
(118, 'KIC5H', 'Qualitative'),
(119, 'VJB6P', 'Qualitative'),
(120, '41AHL', 'Qualitative'),
(121, 'X1669', 'Qualitative'),
(122, 'PRRDZ', 'Qualitative'),
(123, 'MHN1E', 'Qualitative'),
(124, 'W6FO2', 'Qualitative'),
(125, 'A47N3', 'Qualitative'),
(126, 'O4YAP', 'Qualitative'),
(127, 'AQAXD', 'Qualitative'),
(128, '5IFXZ', 'Qualitative'),
(129, 'WJC21', 'Qualitative'),
(130, '22YDN', 'Qualitative'),
(131, 'YKLIJ', 'Qualitative'),
(132, '88LAM', 'Qualitative'),
(133, 'Q9QIF', 'Qualitative'),
(134, 'H8N0T', 'Qualitative'),
(135, 'M0RXR', 'Qualitative'),
(136, 'KOW81', 'Qualitative'),
(137, 'M49VJ', 'Qualitative'),
(138, '8SRB2', 'Qualitative'),
(139, 'MAA8W', 'Qualitative'),
(140, '871HS', 'Qualitative'),
(141, 'UNETM', 'Qualitative'),
(142, 'H6RYV', 'Qualitative'),
(143, 'PFX7H', 'Qualitative'),
(144, 'NQMBX', 'Qualitative'),
(145, '9N775', 'Qualitative'),
(146, 'RB4AK', 'Qualitative'),
(147, 'WWP1D', 'Qualitative'),
(148, 'T707L', 'Qualitative'),
(149, 'XY7OZ', 'Qualitative'),
(150, 'SZNGC', 'Qualitative'),
(151, 'XZUEO', 'Qualitative'),
(152, '0GCJ8', 'Qualitative'),
(153, 'JJCVL', 'Qualitative'),
(154, 'YLE5J', 'Qualitative'),
(155, 'FNL35', 'Qualitative'),
(156, 'XKJFU', 'Qualitative'),
(157, 'GJH7U', 'Qualitative'),
(158, 'DS56R', 'Qualitative'),
(159, 'UGEO6', 'Qualitative'),
(160, 'Z3MLE', 'Qualitative'),
(161, '7NHZS', 'Qualitative'),
(162, 'SEZ2N', 'Qualitative'),
(163, 'HEPDL', 'Qualitative'),
(164, 'FGEO5', 'Qualitative'),
(165, 'ETLNL', 'Qualitative'),
(166, 'JUXHE', 'Qualitative'),
(167, 'F6EKJ', 'Qualitative'),
(168, 'F6VAQ', 'Qualitative'),
(169, '6Q3YI', 'Qualitative'),
(170, '59XMU', 'Qualitative'),
(171, 'IIIUV', 'Qualitative'),
(172, 'CGTLM', 'Qualitative'),
(173, 'H4FM2', 'Qualitative'),
(174, 'GTPLW', 'Qualitative'),
(175, 'EHXAA', 'Qualitative'),
(176, 'UMPQY', 'Qualitative'),
(177, 'ZPEYG', 'Qualitative'),
(178, 'L3GMS', 'Qualitative'),
(179, 'Y82UU', 'Qualitative'),
(180, 'EH0S3', 'Qualitative'),
(181, 'DZIV2', 'Qualitative'),
(182, 'WGG2N', 'Qualitative'),
(183, 'JYT3S', 'Qualitative'),
(184, 'XCT1T', 'Qualitative'),
(185, 'ZVLWG', 'Qualitative'),
(186, 'AZNO0', 'Qualitative'),
(187, 'V4XLS', 'Qualitative'),
(188, 'JZ5MB', 'Qualitative'),
(189, 'KQ0Y3', 'Qualitative'),
(190, '1TL19', 'Qualitative'),
(191, 'UPA5Z', 'Qualitative'),
(192, 'HE6ID', 'Qualitative'),
(193, '3YOHF', 'Qualitative'),
(194, 'OM2R0', 'Qualitative'),
(195, 'QA6SS', 'Qualitative'),
(196, 'WJXUP', 'Qualitative'),
(197, '3CQ29', 'Qualitative'),
(198, 'AQ3TA', 'Qualitative'),
(199, 'RKBJE', 'Qualitative'),
(200, 'SOVDA', 'Qualitative');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(1024) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(1, 'root', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `candidate`
--
ALTER TABLE `candidate`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`,`category`,`value`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `candidate`
--
ALTER TABLE `candidate`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;
--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
