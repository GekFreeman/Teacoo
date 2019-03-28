-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: Teacoo
-- ------------------------------------------------------
-- Server version	5.6.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Apply`
--

DROP TABLE IF EXISTS `Apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Apply` (
  `ApplyID` varchar(45) NOT NULL,
  `QID` char(5) DEFAULT NULL,
  `UserID` char(5) DEFAULT NULL,
  `UserName` varchar(10) DEFAULT NULL,
  `ApplyDate` varchar(10) DEFAULT NULL,
  `ApplyStatus` varchar(10) DEFAULT NULL,
  `MainQuestion` text,
  `OptionA` tinytext,
  `OptionB` tinytext,
  `OptionC` tinytext,
  `OptionD` tinytext,
  `Answer` varchar(1) DEFAULT NULL,
  `Qtype` varchar(40) DEFAULT NULL,
  `Analysis` tinytext,
  PRIMARY KEY (`ApplyID`),
  UNIQUE KEY `QID_UNIQUE` (`QID`),
  KEY `UserID_idx` (`UserID`),
  CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `userinfo` (`UserID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Apply`
--

LOCK TABLES `Apply` WRITE;
/*!40000 ALTER TABLE `Apply` DISABLE KEYS */;
INSERT INTO `Apply` VALUES ('00001','10001','10000','Pacino','20141223','OK',NULL,'Yes','Of course','No','Too tired to click','D','Type1','To write this java program'),('00002','10002','10000','Pacino','20141223','NO','Is this software a good one?','Yes','Ofcourse','No','Very good','D','Type2','We worked hard on it');
/*!40000 ALTER TABLE `Apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Numlog`
--

DROP TABLE IF EXISTS `Numlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Numlog` (
  `Date` date NOT NULL,
  `UserID` char(5) NOT NULL,
  `Numbers` int(11) DEFAULT NULL,
  PRIMARY KEY (`Date`,`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Numlog`
--

LOCK TABLES `Numlog` WRITE;
/*!40000 ALTER TABLE `Numlog` DISABLE KEYS */;
INSERT INTO `Numlog` VALUES ('2014-12-15','10000',101),('2014-12-16','10000',111),('2014-12-17','10000',112),('2014-12-18','10000',104),('2014-12-19','10000',120),('2014-12-20','10000',115),('2014-12-21','10000',117),('2014-12-22','10000',87),('2014-12-23','10000',90),('2014-12-24','10000',99),('2014-12-25','10000',108);
/*!40000 ALTER TABLE `Numlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QInfo`
--

DROP TABLE IF EXISTS `QInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QInfo` (
  `UserID` char(5) NOT NULL,
  `T1Num` int(11) DEFAULT NULL,
  `T1RightNum` int(11) DEFAULT NULL,
  `T2Num` int(11) DEFAULT NULL,
  `T2RightNum` int(11) DEFAULT NULL,
  `T3Num` int(11) DEFAULT NULL,
  `T3RightNum` int(11) DEFAULT NULL,
  `Level1` int(11) DEFAULT NULL,
  `Level2` int(11) DEFAULT NULL,
  `Level3` int(11) DEFAULT NULL,
  `Level4` int(11) DEFAULT NULL,
  `Level5` int(11) DEFAULT NULL,
  `Level1Right` int(11) DEFAULT NULL,
  `Level2Right` int(11) DEFAULT NULL,
  `Level3Right` int(11) DEFAULT NULL,
  `Level4Right` int(11) DEFAULT NULL,
  `Level5Right` int(11) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QInfo`
--

LOCK TABLES `QInfo` WRITE;
/*!40000 ALTER TABLE `QInfo` DISABLE KEYS */;
INSERT INTO `QInfo` VALUES ('10000',57,6,105,2,50,6,12,12,12,12,164,57,57,57,57,57),('10001',11,2,22,4,3,4,11,33,22,11,11,11,2,2,2,5);
/*!40000 ALTER TABLE `QInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Question`
--

DROP TABLE IF EXISTS `Question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Question` (
  `QID` char(5) NOT NULL,
  `MainQuestion` text,
  `OptionA` tinytext,
  `OptionB` tinytext,
  `OptionC` tinytext,
  `OptionD` tinytext,
  `Answer` varchar(1) DEFAULT NULL,
  `Qtype` varchar(50) DEFAULT NULL,
  `Analysis` text,
  `Level` varchar(5) DEFAULT NULL,
  `TotalTimes` int(11) DEFAULT NULL,
  `RightTimes` int(11) DEFAULT NULL,
  PRIMARY KEY (`QID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Question`
--

LOCK TABLES `Question` WRITE;
/*!40000 ALTER TABLE `Question` DISABLE KEYS */;
INSERT INTO `Question` VALUES ('10001','Which of the following is the MAIN function of marketing?','To identify and anticipate customer needs','To maximise sales volume','To persuade potential consumers to convert latent demand into expenditure','To identify suitable outlets for goods and services supplied','B','Type1','To maximize something.','5',6,1),('10002','Which one of the following statements is correct in relation to monetary rewards in accordance with Herzberg’s','Pay increases are a powerful long-term motivator','Inadequate monetary rewards are a powerful dissatisfier','Monetary rewards are more important than non-monetary rewards','Pay can never be used as a motivator','B','Type1','According to Herzberg, money is a hygiene factor (or dissatisfier). Although it is a powerful short-term motivator, it is','5',2,1),('10003','Which of the following is not a connected stakeholder?','Shareholders','Senior managers','Suppliers','Lenders','B','Type1','Senior managers are internal stakeholders. Others are all connected stakeholders.','4',8,2),('10004','Which of the following is a secondary stakeholder?','Employees','Customers','Shareholders','Local communities','D','Type1','Others are all primary stakeholders.','5',13,1),('10005','We can use “PEST” to analyse the business environment of an organization. What does the “E” stand for here?','Environmental','Economic','Ecological','Efficient','B','Type1','It is a quite straight forward question that you should recite all elements consisted in “PEST”.','5',13,1),('10006','What is the situation where there is high unemployment and high inflation altogether ?','Full employment national income','Inflationary gaps','Deflationary gap','Stagflation','D','Type1','High inflation and unemployment are characteristics of stagflation. Choice A has no unemployment. Choice B has no unemployment which means the increase in demand will cause inflation. Choice C has unemployment but the price level is fairly constant which means the increase in demand will only cause the increase in supply.','5',14,1),('10007','Which of the following is NOT one of the impacts of inflation?','Balancing international trade','Redistributing wealth between lenders and borrowers','Boosting economic growth','Increasing uncertainty of the viability of investments','C','Type1','High inflation will do harm to the investment and economic growth. Inflation causes home currency to depreciate benefiting exports. Inflation also makes money “depreciate” so that wealth goes to lenders from borrowers.','5',9,1),('10008','Which of the following is NOT one of the objectives of the macroeconomic policy?','To achieve economic growth','To eliminate price inflation','To obtain full employment','To achieve a balance between exports and imports','B','Type1','The objective is to control inflation rather than to eliminate inflation. Others are all the objectives of macro economic policies.','5',16,1),('10009','Which of the following is NOT one of the tools of the monetary policy?','Interest rate','Taxation','Exchange rate','Money supply','B','Type1','It is a tool of the fiscal policy.','5',11,1),('10010','Which of the following is often used to analyse both the internal and external environmental factors of an organisation?','SMART','PEST','SWOT','SPAMSOAP','C','Type1','SMART is used to describe the objectives-setting(specific, measurable, attainable, relevant and time-bounded). PEST is to analyse the external environmental factors of an organization. SPAMSOAP is used to remember the financial control procedures.','5',13,1),('10011','Which of the following is NOT consisted in Porter’s model of five competitive factors?','The threat of new entrants','The threat of complement products','The bargaining power of suppliers','The rivalry amongst current competitors','B','Type1','The threat of substitute products is one of the five forces in Porter’s model.','3',15,6),('10012','Which of the following is NOT one of the supportive activities according to the value chain theory?','Procurement','Inbound logistics','HR management','Firm infrastructure','B','Type1','Inbound logistic is one of the primary activities in the value chain. Moreover, you should differentiate between procurement and inbound logistics.','5',6,1),('10013','Which of the following is NOT one of the opinions held by the scientific theory on management developed by F W Taylor?','The most efficient method should be established','Planning and doing are separated','The jobs should be micro-designed','There are many factors that can motivate people','D','Type1','Taylor believes that pay is the only incentive that can motivate people to behave. Other choices are all the conclusions of the scientific theory of management.','4',5,1),('10014','Which of the following is included in the “decisional” role according to Mintzberg’s manager’s roles?','Leader','Monitor','Disseminator','Disturbance handler','D','Type1','Choice A means hiring, firing and coordinating people which belongs to the “interpersonal” role. Choice B means collecting information from subordinates, superiors, peers which belongs to “informational” role. Choice C means disseminating information to subordinates which also belongs to “informational” role. Disturbance handler means taking decisions when there is deviation.','5',7,1),('10015','Which of the following is the “lower to higher” order according to Maslow’s hierarchy of needs?','Safety needs, physiological needs, love needs, self actualization, esteem need','Physiological needs, love needs, safety needs, self actualization, esteem needs','Physiological needs, safety needs, love needs, esteem needs, self actualization','Safety needs, physiological needs, love needs, esteem needs, self actualization','C','Type1','Maslow’s hierarchy of needs follows the form of a pyramid in which physiological needs is the lowest level while self actualization is the highest level.','5',9,1),('10016','Which of the following is NOT one of the motivator factors according to Herzberg’s two-factor theory?','Working conditions','Working status','Challenging work','A sense of achievement from current jobs','A','Type1','Working conditions belong to the hygiene factors which include those factors surrounding work. Other choices are motivator factors which refer to work itself and the sense of achievement or satisfaction from work.','5',11,1),('10017','A government body uses measures based upon the ‘three Es’ to the measure value for money generated bya publicly funded hospital. It considers the most important performance measure to be ‘cost persuccessfully treated patient’.Which of the three E’s best describes the above measure?','Economy','Effectiveness','Efficiency','Externality','C','Type2','Efficiency. Efficiency measures relate the resources used to the output produced.','5',16,1),('10018','Last year ABC Co made profits before tax of $2,628,000 The tax amounted to $788,000.ABC Co’s share capital was $2,000,000 (2,000,000 shares of $1) and $4,000,000 6% preference shares. What was the earnings per share (EPS) for the year?','31c','80c','92c','119c','B','Type2','80c.   Profit before tax- tax-preference dividend (6% × 4,000,000)=Earnings attributable to ordinary shareholders. 2,628,000-788,000-240,000=1,600,000.Number of ordinary shares=2,000,000.     EPS = 1,600,000 / 2,000,000 = 80c','5',20,1),('10019','Which of the following is most appropriate as an objective of a not-for-profit organisation?','To achieve long term growth in earnings','To maximise shareholder wealth','To make efficient use of resources','To minimise input costs','C','Type2','Not-for-profit organisations have objectives generally concerned with efficient use of resources in the light of specific targets. Controlling input costs will be important (economy) but minimising inpu tcosts would be likely to affect quality.','5',18,1),('10020','A government has adopted a contractionary fiscal policy.How would this typically affect businesses?','Higher interest rates and higher inflation','Lower taxes and higher government subsidies','Higher taxes and lower government subsidies','Lower inflation and lower interest rates','C','Type2','Fiscal policy is the balance of government taxation and spending. A contractionary fiscal policy implies a government budget surplus – the government is reducing demand by withdrawing higheramounts from the economy by way of higher taxation and/or spending less. ‘B’ would be the result of an expansionary fiscal policy. ‘A’ may happen as a result of an expansionary policy as an economy ‘booms.’ ‘D’ may happen following a contractionary fiscal policy, although lower inflation and interest  rates are only a secondary effect. As an economy enters recession inflationary pressure may decrease and interest rates may be reduced to encourage borrowing. However as these are not directly due to fiscal policy, C is the more direct and immediate impact. ','5',11,1),('10021','Which of the following statements about obtaining a full stock market listing is NOT correct?','Compliance costs are likely to increase, but better public profile and access to funds benefit the','All else being equal the value of the business is likely to be unaffected.','It allows owners to realise their investment.','It increases the liquidity of the shares for shareholders.','B','Type2','Increased regulation and transparency reduce the actual and perceived risk from the point of view of shareholders, making the shares more attractive and hence more valuable. In addition listed company shares are naturally more liquid than an equivalent unlisted company, again adding to their value. The process of listing is therefore likely to create value.','5',23,1),('10022','Which of the following organisations is most likely to benefit from a period of high price inflation?','An organisation which has a large number of long term payables','An exporter of goods to a country with relatively low inflation','A supplier of goods in a market where consumers are highly price sensitive and substitute imported','A large retailer with a high level of inventory on display and low rate of inventory turnover','A','Type2','Rationale: Debts lose ‘real’ value with inflation: a company that owes a lot of money would effectively pay less (in real terms) over time. The other organisations would suffer because: inflation would make exports relatively expensive and imports relatively cheap; business might be lost to price rises; and the cost of implementing price changes would be high.','5',24,1),('10023','WW Co has a current ratio of 2. Receivables are $3 million and current liabilities are $2 million.What are inventory days if cost of sales is $10 million per annum?','36.5 days','91.25 days','14.6 days','243.3 days','A','Type2','Current ratio = current assets / current liabilities = 2. Here = ($3m + inventory) / $2m = 2. So inventory = $1m.  If cost of sales is $10m then inventory days = (1/10) × 365 = 36.5 days','5',18,1),('10024','Which of the following is NOT a drawback of the EOQ model?','Assumes certain or zero lead times.','Assumes certainty in demand.','Assumes a small number of close suppliers.','Ignores hidden costs such as the risk of obsolescence.','C','Type2','C relates to just-in-time. It is not a drawback of EOQ.','5',11,1),('10025','Which of the following is a drawback of the payback period method of investment appraisal?','It is cash flow based','It considers the time value of money','It doesn’t measure the potential impact on shareholder wealth','It is profit based','C','Type2','Option A is a benefit not a drawback. Option B is incorrect. Payback period does not take account of the time value of money. D is incorrect. The calculation is not based on profit. On the assumption that the basic reason for approving a project is that it will increase shareholder wealth, a major drawback of payback period is that it does not attempt to measure the impact on shareholder wealth should the project go ahead.','5',14,1),('10026','Which of the following statements about net present value (NPV) and internal rate of return (IRR) are accurate?','Two NPV calculations are needed to estimate the IRR using linear interpolation.','The graphical approach to IRR is only an estimate; linear interpolation using the formula is required for a precise answer.','The IRR is unique.','An IRR graph with NPV on the ‘Y’ axis and discount rate on the ‘X’ axis will have a negative slope.','A','Type2','The IRR formula requires two NPV calculations at different rates to estimate the IRR.  B is inaccurate. Linear interpolation is still an estimate. It is not 100% precise.  C is inaccurate. There may be more than one IRR. It depends on whether the cash flows are conventional or not. D is not necessarily true. For example, an unusual project with an initial large inflow followed by years of outflows will have a positive slope.','5',19,1),('10027','Shadowline Co has a money cost of capital of 10%. If inflation is 4%, what is Shadowline Co’s real cost of capital?','6%','5.8%','14%','14.4%','B','Type2','(1 + r) × (1 + h) = (1 + i) where r=real rate, h=inflation, i=money rate, so (1 + r) × (1.04) = (1.10)    (1 + r) = 1.10/1.04 = 1.058 or 5.8%.','5',19,1),('10028','Who suffers financial risk as financial gearing increases, and why?','Lenders because they are less likely to be repaid.','Lenders because there are fewer assets to offer as security.','Shareholders as their returns are lower.','Shareholders as their dividends become more variable.','D','Type2','Statement D is correct. As interest payments do not vary with profits, interest is effectively a fixed cost to the business.High financial gearing means that a company is more vulnerable to poor trading conditions.','5',12,1),('10029','Which of the following best describes systematic risk?','The chance that automated processes may fail','The risk associated with investing in equity','The diversifiable risk associated with investing in equity','The residual risk associated with investing in a well-diversified portfolio.','D','Type2','A is incorrect. Systematic risk refers to return volatility. B is incorrect. This describes TOTAL risk, which has both systematic and unsystematic elements. C is incorrect. Systematic risk cannot be diversified away. D is correct. It is the risk generated by undiversifiable systemic economic risk factors.','5',19,1),('10030','Which of the following assumptions is not required when using the capital asset pricing model to estimate the cost of equity for project appraisal?','Efficient capital markets','Well diversified investors','Future periods are consistent with the present','Companies are well diversified','D','Type2','D is not a necessary assumption. So long as investors are well diversified, any individual company need not be. Investors are assumed to invest in a diversified portfolio of projects; it doesn’t matter how few or how many reside in any one company.','5',21,1),('10031','Why do Modigliani-Miller (with tax) assume increased gearing will reduce the weighted average cost of capital (‘WACC’)?','Debt is cheaper than equity','Interest payments are tax deductible','Reduced levels of expensive equity capital will reduce the WACC','Financial risk is not pronounced at moderate borrowing levels','B','Type2','Statement B is correct: The only different between MM (no tax) and MM (with tax) is the tax deductibility of interest payments. MM demonstrated that when a business does not pay tax, returns are not affected by capital structure. However, as interest is tax deductible (and dividends are not) paying relatively more interest will reduce tax payable and increase total returns to investors.','5',22,1),('10032','Pecking order theory suggests finance should be raised in which order?','Internal funds, rights issue, debt','Internal funds, debt, new equity','Debt, internal funds, new equity','Rights issue, internal funds, debt','B','Type2','Pecking order theory suggests that as internal funds are free to raise and immediate they should be used first. After that, debt is relatively quick and inexpensive to raise, interest is tax deductible and the cost of debt is lower than the cost of equity. New equity is relatively expensive hence is considered last.','5',24,1),('10033','Sarah decides to plot past share price movements to help spot patterns and create an investment strategy. What does Sarah believe the stock market is?','Completely inefficient','Weak form efficient','Semi-strong form efficient','Strong form efficient','A','Type2','In a weak form efficient market, all investors know previous share price movements, which will stop patterns consistently and predictably repeating. Sarah must therefore believe the markets are not even weak form efficient.','5',17,1),('10034','Which of the following is correct concerning the market value of a company\'s shares?','Market value must be greater than nominal value','Market value must be lower than nominal value','Market value equals nominal value','Market value may be equal, greater or lower than nominal value','D','Type3','A share\'s market value is based on the prospects of the company and may be equal, greater or lower than nominal value.','5',24,1),('10035','Which of the following statements regarding the differences between loan capital and share capital is correct?','Unlike loan capital, share capital does not have to be repaid','Loan capital has voting rights attached but share capital does not','Share capital offers the holder more security than loan capital','Share capital is transferrable but loan capital is not','A','Type3','Share capital does not have to be repaid, unlike a loan which must be repaid at some point. Share capital has voting rights but loan capital does not. Loan capital offers the holder more security than share capital because it has a higher priority to be repaid and may be secured on assets by a charge. Both loan and share capital are transferrable.','5',25,1),('10036','Which of the following statements regarding the differences between loan capital and share capital is NOT correct?','A shareholder is an owner of the company, a debentureholder is not','Shares may not be issued at a discount to their nominal value, debentures may be issued at a discount to their nominal value','An public sale of shares is known as a prospectus, the public sale of debentures is known as a listing','There are statutory restrictions on redeeming shares, there are no statutory restrictions on redeeming debentures','C','Type3','The public sale of shares and debentures are both known as a prospectus. The other options are true','5',26,1),('10037','The rules of capital maintenance exist to primarily protect which of the following parties?','A company\'s members','A company\'s creditors','A company\'s customers','The government','B','Type3','The rules on capital maintenance exist to protect the interest of a company\'s creditors.','5',25,1),('10038','Which of the following is a characteristic of a non-executive director?','They are individuals that are held out by the company to be directors','They are not involved in the day-to-day running of the company','They are individuals whose instructions concerning running the company are followed','They are not subject to the statutory duties of directors','B','Type3','Non-executive directors are not involved in the day-to-day running of the company.','5',26,1),('10039','Which of the following describes the correct way that directors should use their powers?','To achieve the goals of the business','For any reason necessary to maximise profit','For a proper purpose that is honestly believed to be in the best interests of the company','For any legal purpose','C','Type1','The purpose is honestly believed.','5',12,1),('10040','A project requires an initial outlay of $100,000 and will generate net cash inflows of $40,000 per annum. At a cost of capital of 10%, what is the adjusted payback period to the nearest month?','3 years','2 years 6 months','2 years 10 months','3 years 2 months','B','Type1','18 months will be needed.','5',11,1),('10041','Which of the following statements concerning capital structure theory is correct?','In the traditional view, there is a linear relationship between the cost of equity and financial risk','Modigliani and Miller said that, in the absence of tax, the cost of equity would remain constant','Pecking order theory indicates that preference shares are preferred to convertible debt as a source of finance','Business risk is assumed to be constant','D','Type3','Statement D is correct: Business risk is generated by the projects invested in, not where the finance comes from. As the projects are unaffected by the source of their finance, business risk is therefore unaffected by the capital structure decision.','5',25,1),('10042','Which of the following actions is LEAST likely to increase shareholder wealth?','RZS Co has recently paid a dividend of 34c per share. 4 years ago the dividend was 12c a share. There was a','The financial rewards of directors are linked to increasing earnings per share','The board of directors decides to invest in a project with a positive net present value','The annual report declares full compliance with the corporate governance code','C','Type1','Who care about this?','5',5,NULL),('10043','RZS Co has recently paid a dividend of 34c per share. 4 years ago the dividend was 12c a share. There was a1:1 bonus issue of shares 2 years ago. Current share price is $5 a share.\rWhat is the cost of equity capital for RZS Co?','38.5%','16.4%','17.9%','6.8%','A','Type2',NULL,'5',4,NULL),('10044',NULL,'Yes','Of course','No','Too tired to click','D','Multiple choice',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Star`
--

DROP TABLE IF EXISTS `Star`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Star` (
  `QID` char(5) NOT NULL,
  `MainQuestion` text,
  `OptionA` tinytext,
  `OptionB` tinytext,
  `OptionC` tinytext,
  `OptionD` tinytext,
  `Answer` varchar(1) DEFAULT NULL,
  `Qtype` varchar(40) DEFAULT NULL,
  `Analysis` tinytext,
  PRIMARY KEY (`QID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Star`
--

LOCK TABLES `Star` WRITE;
/*!40000 ALTER TABLE `Star` DISABLE KEYS */;
/*!40000 ALTER TABLE `Star` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserInfo`
--

DROP TABLE IF EXISTS `UserInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserInfo` (
  `UserID` char(5) NOT NULL,
  `Password` varchar(16) DEFAULT NULL,
  `Username` varchar(15) DEFAULT NULL,
  `Gender` char(1) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Profile` varchar(45) DEFAULT NULL,
  `Level` varchar(15) DEFAULT NULL,
  `Major` varchar(15) DEFAULT NULL,
  `Grade` varchar(45) DEFAULT NULL,
  `Advantage` varchar(15) DEFAULT NULL,
  `Hobby` varchar(15) DEFAULT NULL,
  `Signature` tinytext,
  `Teanums` int(11) DEFAULT NULL,
  `Exp` int(11) DEFAULT NULL,
  `Rank` int(11) DEFAULT NULL,
  `Authority` int(11) DEFAULT NULL,
  `SecureQuestion` varchar(60) DEFAULT NULL,
  `SecureAnswer` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserInfo`
--

LOCK TABLES `UserInfo` WRITE;
/*!40000 ALTER TABLE `UserInfo` DISABLE KEYS */;
INSERT INTO `UserInfo` VALUES ('10000','123456','Pacino','M','18005022942','351671774@qq.com','image/profile/pf1.jpg','7','Management','3','sleep','eat','If you dare defy me!',253,23715,1,1,'what\'s your student ID?','03845'),('10001','123456','Selina','F','18888888888','19803546@163.com','image/userprofile/10000.png','2','Management','3','sleep','sleep','?',12,500,4,1,'what\'s your student ID?','03222'),('10002','123456','df','M','13432243434','19803546@163.com','image/userprofile/10000.png','3','Management','3','eat','sleep','what a night',11,1500,3,1,'what\'s your student ID?','03234'),('10003','123456','zc','M','12312132343','3124325@qq.com','image/userprofile/10000.png','5','Management','3','eat','eat','tired',12,13500,2,1,'what\'s your student ID?','23523'),('10004','123456','Frankiee','M','1113235235','XetHESH@qq.com','image/profile/pf6.jpg','2','Management','3','eat','sleep','tired!!!',11,42,5,1,'what\'s your student ID?','23523'),('10005','123456','zhangchi',NULL,'1888888888','1235455@qq.com','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'what`s your mother`s name?','111');
/*!40000 ALTER TABLE `UserInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WrongCollection`
--

DROP TABLE IF EXISTS `WrongCollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WrongCollection` (
  `QID` char(5) NOT NULL,
  `MainQuestion` text,
  `OptionA` tinytext,
  `OptionB` tinytext,
  `OptionC` tinytext,
  `OptionD` tinytext,
  `Answer` varchar(1) DEFAULT NULL,
  `Qtype` varchar(40) DEFAULT NULL,
  `Analysis` tinytext,
  PRIMARY KEY (`QID`),
  CONSTRAINT `QID` FOREIGN KEY (`QID`) REFERENCES `Question` (`QID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WrongCollection`
--

LOCK TABLES `WrongCollection` WRITE;
/*!40000 ALTER TABLE `WrongCollection` DISABLE KEYS */;
/*!40000 ALTER TABLE `WrongCollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `star10000`
--

DROP TABLE IF EXISTS `star10000`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `star10000` (
  `QID` char(5) NOT NULL,
  `MainQuestion` text,
  `OptionA` tinytext,
  `OptionB` tinytext,
  `OptionC` tinytext,
  `OptionD` tinytext,
  `Answer` varchar(1) DEFAULT NULL,
  `Qtype` varchar(40) DEFAULT NULL,
  `Analysis` tinytext,
  PRIMARY KEY (`QID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `star10000`
--

LOCK TABLES `star10000` WRITE;
/*!40000 ALTER TABLE `star10000` DISABLE KEYS */;
INSERT INTO `star10000` VALUES ('10001','Which of the following is the MAIN function of marketing?','To identify and anticipate customer needs','To maximise sales volume','To persuade potential consumers to convert latent demand into expenditure','To identify suitable outlets for goods and services supplied','B','Type1',NULL),('10002','Which one of the following statements is correct in relation to monetary rewards in accordance with Herzberg’s','Pay increases are a powerful long-term motivator','Inadequate monetary rewards are a powerful dissatisfier','Monetary rewards are more important than non-monetary rewards','Pay can never be used as a motivator','B','Type1','According to Herzberg, money is a hygiene factor (or dissatisfier). Although it is a powerful short-term motivator, it is'),('10003','Which of the following is not a connected stakeholder?','Shareholders','Senior managers','Suppliers','Lenders','B','Type1','Senior managers are internal stakeholders. Others are all connected stakeholders.'),('10005','We can use “PEST” to analyse the business environment of an organization. What does the “E” stand for here?','Environmental','Economic','Ecological','Efficient','B','Type1','It is a quite straight forward question that you should recite all elements consisted in “PEST”.'),('10007','Which of the following is NOT one of the impacts of inflation?','Balancing international trade','Redistributing wealth between lenders and borrowers','Boosting economic growth','Increasing uncertainty of the viability of investments','C','Type1','High inflation will do harm to the investment and economic growth. Inflation causes home currency to depreciate benefiting exports. Inflation also makes money “depreciate” so that wealth goes to lenders from borrowers.'),('10010','Which of the following is often used to analyse both the internal and external environmental factors of an organisation?','SMART','PEST','SWOT','SPAMSOAP','C','Type1','SMART is used to describe the objectives-setting(specific, measurable, attainable, relevant and time-bounded). PEST is to analyse the external environmental factors of an organization. SPAMSOAP is used to remember the financial control procedures.'),('10013','Which of the following is NOT one of the opinions held by the scientific theory on management developed by F W Taylor?','The most efficient method should be established','Planning and doing are separated','The jobs should be micro-designed','There are many factors that can motivate people','D','Type1','Taylor believes that pay is the only incentive that can motivate people to behave. Other choices are all the conclusions of the scientific theory of management.'),('10036','Which of the following statements regarding the differences between loan capital and share capital is NOT correct?','A shareholder is an owner of the company, a debentureholder is not','Shares may not be issued at a discount to their nominal value, debentures may be issued at a discount to their nominal value','An public sale of shares is known as a prospectus, the public sale of debentures is known as a listing','There are statutory restrictions on redeeming shares, there are no statutory restrictions on redeeming debentures','C','Type3','The public sale of shares and debentures are both known as a prospectus. The other options are true');
/*!40000 ALTER TABLE `star10000` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `star10005`
--

DROP TABLE IF EXISTS `star10005`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `star10005` (
  `QID` char(5) NOT NULL,
  `MainQuestion` text,
  `OptionA` tinytext,
  `OptionB` tinytext,
  `OptionC` tinytext,
  `OptionD` tinytext,
  `Answer` varchar(1) DEFAULT NULL,
  `Qtype` varchar(40) DEFAULT NULL,
  `Analysis` tinytext,
  PRIMARY KEY (`QID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `star10005`
--

LOCK TABLES `star10005` WRITE;
/*!40000 ALTER TABLE `star10005` DISABLE KEYS */;
/*!40000 ALTER TABLE `star10005` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `QID` char(5) NOT NULL,
  `MainQuestion` text,
  `OptionA` tinytext,
  `OptionB` tinytext,
  `OptionC` tinytext,
  `OptionD` tinytext,
  `Answer` varchar(1) DEFAULT NULL,
  `Qtype` varchar(40) DEFAULT NULL,
  `Analysis` text,
  `Level` varchar(5) DEFAULT NULL,
  `UserAnswer` varchar(1) DEFAULT NULL,
  `Collected` varchar(45) DEFAULT NULL,
  `QOrder` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`QID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store10000`
--

DROP TABLE IF EXISTS `store10000`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store10000` (
  `QID` char(5) NOT NULL,
  `MainQuestion` text,
  `OptionA` tinytext,
  `OptionB` tinytext,
  `OptionC` tinytext,
  `OptionD` tinytext,
  `Answer` varchar(1) DEFAULT NULL,
  `Qtype` varchar(40) DEFAULT NULL,
  `Analysis` text,
  `Level` varchar(5) DEFAULT NULL,
  `UserAnswer` varchar(1) DEFAULT NULL,
  `Collected` varchar(45) DEFAULT NULL,
  `QOrder` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`QID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store10000`
--

LOCK TABLES `store10000` WRITE;
/*!40000 ALTER TABLE `store10000` DISABLE KEYS */;
/*!40000 ALTER TABLE `store10000` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrong10000`
--

DROP TABLE IF EXISTS `wrong10000`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wrong10000` (
  `QID` char(5) NOT NULL,
  `MainQuestion` text,
  `OptionA` tinytext,
  `OptionB` tinytext,
  `OptionC` tinytext,
  `OptionD` tinytext,
  `Answer` varchar(1) DEFAULT NULL,
  `Qtype` varchar(40) DEFAULT NULL,
  `Analysis` tinytext,
  PRIMARY KEY (`QID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrong10000`
--

LOCK TABLES `wrong10000` WRITE;
/*!40000 ALTER TABLE `wrong10000` DISABLE KEYS */;
INSERT INTO `wrong10000` VALUES ('10004','Which of the following is a secondary stakeholder?','Employees','Customers','Shareholders','Local communities','D','Type1','Others are all primary stakeholders.'),('10005','We can use “PEST” to analyse the business environment of an organization. What does the “E” stand for here?','Environmental','Economic','Ecological','Efficient','B','Type1','It is a quite straight forward question that you should recite all elements consisted in “PEST”.'),('10008','Which of the following is NOT one of the objectives of the macroeconomic policy?','To achieve economic growth','To eliminate price inflation','To obtain full employment','To achieve a balance between exports and imports','B','Type1','The objective is to control inflation rather than to eliminate inflation. Others are all the objectives of macro economic policies.'),('10009','Which of the following is NOT one of the tools of the monetary policy?','Interest rate','Taxation','Exchange rate','Money supply','B','Type1','It is a tool of the fiscal policy.'),('10010','Which of the following is often used to analyse both the internal and external environmental factors of an organisation?','SMART','PEST','SWOT','SPAMSOAP','C','Type1','SMART is used to describe the objectives-setting(specific, measurable, attainable, relevant and time-bounded). PEST is to analyse the external environmental factors of an organization. SPAMSOAP is used to remember the financial control procedures.'),('10011','Which of the following is NOT consisted in Porter’s model of five competitive factors?','The threat of new entrants','The threat of complement products','The bargaining power of suppliers','The rivalry amongst current competitors','B','Type1','The threat of substitute products is one of the five forces in Porter’s model.'),('10012','Which of the following is NOT one of the supportive activities according to the value chain theory?','Procurement','Inbound logistics','HR management','Firm infrastructure','B','Type1','Inbound logistic is one of the primary activities in the value chain. Moreover, you should differentiate between procurement and inbound logistics.'),('10013','Which of the following is NOT one of the opinions held by the scientific theory on management developed by F W Taylor?','The most efficient method should be established','Planning and doing are separated','The jobs should be micro-designed','There are many factors that can motivate people','D','Type1','Taylor believes that pay is the only incentive that can motivate people to behave. Other choices are all the conclusions of the scientific theory of management.'),('10016','Which of the following is NOT one of the motivator factors according to Herzberg’s two-factor theory?','Working conditions','Working status','Challenging work','A sense of achievement from current jobs','A','Type1','Working conditions belong to the hygiene factors which include those factors surrounding work. Other choices are motivator factors which refer to work itself and the sense of achievement or satisfaction from work.'),('10017','A government body uses measures based upon the ‘three Es’ to the measure value for money generated bya publicly funded hospital. It considers the most important performance measure to be ‘cost persuccessfully treated patient’.Which of the three E’s best describes the above measure?','Economy','Effectiveness','Efficiency','Externality','C','Type2','Efficiency. Efficiency measures relate the resources used to the output produced.'),('10018','Last year ABC Co made profits before tax of $2,628,000 The tax amounted to $788,000.ABC Co’s share capital was $2,000,000 (2,000,000 shares of $1) and $4,000,000 6% preference shares. What was the earnings per share (EPS) for the year?','31c','80c','92c','119c','B','Type2','80c.   Profit before tax- tax-preference dividend (6% × 4,000,000)=Earnings attributable to ordinary shareholders. 2,628,000-788,000-240,000=1,600,000.Number of ordinary shares=2,000,000.     EPS = 1,600,000 / 2,000,000 = 80c'),('10019','Which of the following is most appropriate as an objective of a not-for-profit organisation?','To achieve long term growth in earnings','To maximise shareholder wealth','To make efficient use of resources','To minimise input costs','C','Type2','Not-for-profit organisations have objectives generally concerned with efficient use of resources in the light of specific targets. Controlling input costs will be important (economy) but minimising inpu tcosts would be likely to affect quality.'),('10023','WW Co has a current ratio of 2. Receivables are $3 million and current liabilities are $2 million.What are inventory days if cost of sales is $10 million per annum?','36.5 days','91.25 days','14.6 days','243.3 days','A','Type2','Current ratio = current assets / current liabilities = 2. Here = ($3m + inventory) / $2m = 2. So inventory = $1m.  If cost of sales is $10m then inventory days = (1/10) × 365 = 36.5 days'),('10027','Shadowline Co has a money cost of capital of 10%. If inflation is 4%, what is Shadowline Co’s real cost of capital?','6%','5.8%','14%','14.4%','B','Type2','(1 + r) × (1 + h) = (1 + i) where r=real rate, h=inflation, i=money rate, so (1 + r) × (1.04) = (1.10)    (1 + r) = 1.10/1.04 = 1.058 or 5.8%.'),('10028','Who suffers financial risk as financial gearing increases, and why?','Lenders because they are less likely to be repaid.','Lenders because there are fewer assets to offer as security.','Shareholders as their returns are lower.','Shareholders as their dividends become more variable.','D','Type2','Statement D is correct. As interest payments do not vary with profits, interest is effectively a fixed cost to the business.High financial gearing means that a company is more vulnerable to poor trading conditions.'),('10030','Which of the following assumptions is not required when using the capital asset pricing model to estimate the cost of equity for project appraisal?','Efficient capital markets','Well diversified investors','Future periods are consistent with the present','Companies are well diversified','D','Type2','D is not a necessary assumption. So long as investors are well diversified, any individual company need not be. Investors are assumed to invest in a diversified portfolio of projects; it doesn’t matter how few or how many reside in any one company.'),('10033','Sarah decides to plot past share price movements to help spot patterns and create an investment strategy. What does Sarah believe the stock market is?','Completely inefficient','Weak form efficient','Semi-strong form efficient','Strong form efficient','A','Type2','In a weak form efficient market, all investors know previous share price movements, which will stop patterns consistently and predictably repeating. Sarah must therefore believe the markets are not even weak form efficient.'),('10034','Which of the following is correct concerning the market value of a company\'s shares?','Market value must be greater than nominal value','Market value must be lower than nominal value','Market value equals nominal value','Market value may be equal, greater or lower than nominal value','D','Type3','A share\'s market value is based on the prospects of the company and may be equal, greater or lower than nominal value.'),('10036','Which of the following statements regarding the differences between loan capital and share capital is NOT correct?','A shareholder is an owner of the company, a debentureholder is not','Shares may not be issued at a discount to their nominal value, debentures may be issued at a discount to their nominal value','An public sale of shares is known as a prospectus, the public sale of debentures is known as a listing','There are statutory restrictions on redeeming shares, there are no statutory restrictions on redeeming debentures','C','Type3','The public sale of shares and debentures are both known as a prospectus. The other options are true'),('10037','The rules of capital maintenance exist to primarily protect which of the following parties?','A company\'s members','A company\'s creditors','A company\'s customers','The government','B','Type3','The rules on capital maintenance exist to protect the interest of a company\'s creditors.'),('10038','Which of the following is a characteristic of a non-executive director?','They are individuals that are held out by the company to be directors','They are not involved in the day-to-day running of the company','They are individuals whose instructions concerning running the company are followed','They are not subject to the statutory duties of directors','B','Type3','Non-executive directors are not involved in the day-to-day running of the company.'),('10039','Which of the following describes the correct way that directors should use their powers?','To achieve the goals of the business','For any reason necessary to maximise profit','For a proper purpose that is honestly believed to be in the best interests of the company','For any legal purpose','C','Type1','The purpose is honestly believed.'),('10040','A project requires an initial outlay of $100,000 and will generate net cash inflows of $40,000 per annum. At a cost of capital of 10%, what is the adjusted payback period to the nearest month?','3 years','2 years 6 months','2 years 10 months','3 years 2 months','B','Type1','18 months will be needed.'),('10041','Which of the following statements concerning capital structure theory is correct?','In the traditional view, there is a linear relationship between the cost of equity and financial risk','Modigliani and Miller said that, in the absence of tax, the cost of equity would remain constant','Pecking order theory indicates that preference shares are preferred to convertible debt as a source of finance','Business risk is assumed to be constant','D','Type3','Statement D is correct: Business risk is generated by the projects invested in, not where the finance comes from. As the projects are unaffected by the source of their finance, business risk is therefore unaffected by the capital structure decision.'),('10042','Which of the following actions is LEAST likely to increase shareholder wealth?','RZS Co has recently paid a dividend of 34c per share. 4 years ago the dividend was 12c a share. There was a','The financial rewards of directors are linked to increasing earnings per share','The board of directors decides to invest in a project with a positive net present value','The annual report declares full compliance with the corporate governance code','C','Type1','Who care about this?'),('10043','RZS Co has recently paid a dividend of 34c per share. 4 years ago the dividend was 12c a share. There was a1:1 bonus issue of shares 2 years ago. Current share price is $5 a share.\rWhat is the cost of equity capital for RZS Co?','38.5%','16.4%','17.9%','6.8%','A','Type2',NULL);
/*!40000 ALTER TABLE `wrong10000` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wrong10005`
--

DROP TABLE IF EXISTS `wrong10005`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wrong10005` (
  `QID` char(5) NOT NULL,
  `MainQuestion` text,
  `OptionA` tinytext,
  `OptionB` tinytext,
  `OptionC` tinytext,
  `OptionD` tinytext,
  `Answer` varchar(1) DEFAULT NULL,
  `Qtype` varchar(40) DEFAULT NULL,
  `Analysis` tinytext,
  PRIMARY KEY (`QID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wrong10005`
--

LOCK TABLES `wrong10005` WRITE;
/*!40000 ALTER TABLE `wrong10005` DISABLE KEYS */;
/*!40000 ALTER TABLE `wrong10005` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-24 12:17:54
