
drop table WorksFor;
drop table RefsFor;
drop table GameSet;

drop table game;

drop table stadiumaddress;
drop table stadium;
drop table stadiumname;
drop table ref;
drop table coachexperience;
drop table headcoach;
drop table experience;


drop table LiberoBMI;
drop table ServerSpecialistBMI;
drop table OutsideHitterBMI;
drop table SetterBMI;
drop table MiddleBlockerBMI;

drop table BMI;
drop table Libero;
drop table ServerSpecialist;
drop table OutsideHitter;
drop table Setter;
drop table MiddleBlocker;
drop table team;
drop table league;
drop table country;







CREATE TABLE Country
	(Cname VARCHAR(20) NOT NULL,
    Population INTEGER,
    PRIMARY KEY (Cname));
            
CREATE TABLE League
	(LID INTEGER,
	Cname VARCHAR(20) NOT NULL,
	Name CHAR(40),
    PRIMARY KEY (LID),
    FOREIGN KEY (Cname) REFERENCES Country
	ON DELETE CASCADE);
    
    
CREATE TABLE Stadium
	(SID INTEGER,
    Name CHAR(40) NULL,
    PRIMARY KEY (SID));    
    
CREATE TABLE StadiumAddress
    (SID INTEGER NOT NULL,	
    City CHAR(40) NOT NULL,
    Address CHAR(40) NOT NULL,
    PRIMARY KEY (SID),
    FOREIGN KEY (SID) REFERENCES Stadium
	ON DELETE CASCADE);
    
CREATE TABLE StadiumName 
    (City CHAR(20) NOT NULL,
    Address CHAR(40) NOT NULL,
    Name CHAR(40),
    PRIMARY KEY (City, Address));


  
CREATE TABLE Ref
	(RID INTEGER,
	Name CHAR (20) NULL,
    Salary INTEGER,
    PRIMARY KEY (RID));


CREATE TABLE Experience
	(YearsCoaching INTEGER,
    Experience CHAR(20) NULL,
    PRIMARY KEY (YearsCoaching));
    


CREATE TABLE HeadCoach
	(CID INTEGER,
    Name Char(20) NULL,
    YearsCoaching INTEGER NOT NULL,
    PRIMARY KEY (CID),
    FOREIGN KEY (YearsCoaching)
	REFERENCES Experience(YearsCoaching)
	ON DELETE CASCADE);
    

CREATE TABLE CoachExperience
	(CID INTEGER,
    EXPERIENCE CHAR(20) NULL,
    PRIMARY KEY (CID),
    FOREIGN KEY (CID)
        REFERENCES HeadCoach(CID)
        ON DELETE CASCADE);

-- Record is # of wins
CREATE TABLE Team
	(TID INTEGER,
    TeamName CHAR(20) NULL,
    Record INTEGER,
    LID INTEGER NOT NULL,
    CID INTEGER NOT NULL,
    PRIMARY KEY (TID),
    FOREIGN KEY (LID) 
		REFERENCES League
		ON DELETE CASCADE);

-- One GID for one game of two teams
-- SID is stadium id
-- WinTID is TID of winning team
-- LoseTID is TID of losing team
-- ALSO REQUIRES BOTH TEAMS TO BE IN THE SAME LEAGUE
CREATE TABLE Game
	(GID INTEGER NOT NULL,
    SID INTEGER NOT NULL,
    WinTID INTEGER NOT NULL,
    LoseTID INTEGER NOT NULL,
    LID INTEGER NOT NULL,
    PRIMARY KEY (GID),
    FOREIGN KEY (SID)
		REFERENCES Stadium(SID)
        ON DELETE CASCADE,
	FOREIGN KEY (LID)
		REFERENCES League
        ON DELETE CASCADE,
    FOREIGN KEY (WinTID)
        REFERENCES Team(TID)
        ON DELETE CASCADE,
    FOREIGN KEY (LoseTID)
        REFERENCES Team(TID)
        ON DELETE CASCADE);
        

CREATE TABLE GameSet 
	(SetNumber INTEGER NOT NULL,
    GID INTEGER NOT NULL,
	WinnerScore INTEGER,
    LoserScore INTEGER,
    PRIMARY KEY (SetNumber, GID),
    FOREIGN KEY (GID)
		REFERENCES GAME(GID)
        ON DELETE CASCADE);
		

CREATE TABLE WorksFor
	(RID INTEGER NOT NULL,
	LID INTEGER NOT NULL,
	PRIMARY KEY (RID, LID),
	FOREIGN KEY (RID)
		REFERENCES Ref
		ON DELETE CASCADE,
	FOREIGN KEY (LID)
		REFERENCES League
		ON DELETE CASCADE);

CREATE TABLE RefsFor
	(RID INTEGER NOT NULL,
	GID INTEGER NOT NULL,
	PRIMARY KEY (RID, GID),
	FOREIGN KEY (RID)
		REFERENCES Ref
		ON DELETE CASCADE,
    FOREIGN KEY (GID)
        REFERENCES Game(GID)
        ON DELETE CASCADE);



-- PID refers to LiberoPID
CREATE TABLE Libero
    (PID INTEGER NOT NULL,
    NAME CHAR(20) NOT NULL,
    Weight INTEGER NOT NULL,
    Height INTEGER NOT NULL,
    Digs INTEGER NOT NULL,
    JerseyNumber INTEGER NOT NULL,
    TID INTEGER NOT NULL,
    UNIQUE (JerseyNumber, TID),
    PRIMARY KEY (PID),
    FOREIGN KEY (TID)
        REFERENCES Team
        ON DELETE CASCADE);


-- PID refers to ServerSpecialist PID
CREATE TABLE ServerSpecialist
    (PID INTEGER NOT NULL,
    NAME CHAR(20) NOT NULL,
    Weight INTEGER NOT NULL,
    Height INTEGER NOT NULL,
    Aces INTEGER NOT NULL,
    JerseyNumber INTEGER NOT NULL,
    TID INTEGER NOT NULL,
    UNIQUE (JerseyNumber, TID),
    PRIMARY KEY (PID),
    FOREIGN KEY (TID)
        REFERENCES Team
        ON DELETE CASCADE);


-- PID refers to OutsideHitter PID
CREATE TABLE OutsideHitter
    (PID INTEGER NOT NULL,
    NAME CHAR(20) NOT NULL,
    Weight INTEGER NOT NULL,
    Height INTEGER NOT NULL,
    Kills INTEGER NOT NULL,
    Aces INTEGER NOT NULL,
    Blocks INTEGER NOT NULL,
    JerseyNumber INTEGER NOT NULL,
    TID INTEGER NOT NULL,
    UNIQUE (JerseyNumber, TID),
    PRIMARY KEY (PID),
    FOREIGN KEY (TID)
        REFERENCES Team
        ON DELETE CASCADE);

-- PID refers to Setter PID
CREATE TABLE Setter
    (PID INTEGER NOT NULL,
    NAME CHAR(20),
    Weight INTEGER,
    Height INTEGER,
    SetAttempts INTEGER,
    SetSuccessRate DECIMAL(2,2),
    JerseyNumber INTEGER,
    TID INTEGER NOT NULL,
    UNIQUE (JerseyNumber, TID),
    PRIMARY KEY (PID),
    FOREIGN KEY (TID)
        REFERENCES Team(TID)
        ON DELETE CASCADE);
    
-- PID refers to MiddleBlocker PID
CREATE TABLE MiddleBlocker
    (PID INTEGER NOT NULL,
    NAME CHAR(20) NOT NULL,
    Weight INTEGER NOT NULL,
    Height INTEGER NOT NULL,
    Blocks INTEGER NOT NULL,
    JerseyNumber INTEGER NOT NULL,
    TID INTEGER NOT NULL,
    UNIQUE (JerseyNumber, TID),
    PRIMARY KEY (PID),
    FOREIGN KEY (TID)
        REFERENCES Team(TID)
        ON DELETE CASCADE);

-- Weight in kg, Height in cm
CREATE TABLE BMI
    (Height INTEGER NOT NULL,
    Weight INTEGER NOT NULL,
    BMI DECIMAL(3,1) NOT NULL,
    PRIMARY KEY(Height, Weight));

-- PID refers to Libero PID
CREATE TABLE LiberoBMI
    (PID INTEGER,
    BMI DECIMAL(3,1) NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (PID)
        REFERENCES Libero(PID)
        ON DELETE CASCADE);

CREATE TABLE ServerSpecialistBMI
    (PID INTEGER,
    BMI DECIMAL(3,1) NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (PID)
        REFERENCES ServerSpecialist(PID)
        ON DELETE CASCADE);


CREATE TABLE OutsideHitterBMI
    (PID INTEGER,
    BMI DECIMAL(3,1) NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (PID)
        REFERENCES OutsideHitter(PID)
        ON DELETE CASCADE);


CREATE TABLE SetterBMI
    (PID INTEGER,
    BMI DECIMAL(3,1) NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (PID)
        REFERENCES Setter(PID)
        ON DELETE CASCADE);

CREATE TABLE MiddleBlockerBMI
    (PID INTEGER,
    BMI DECIMAL(3,1) NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (PID)
        REFERENCES MiddleBlocker(PID)
        ON DELETE CASCADE);




-- Country, population
INSERT INTO Country VALUES('Canada', 38010000);
INSERT INTO Country VALUES('USA', 329500000);
INSERT INTO Country VALUES('Italy', 59550000);
INSERT INTO Country VALUES('Brazil', 212600000);
INSERT INTO Country VALUES('France', 67390000);

-- LID, Country, League
INSERT INTO League VALUES (1, 'Canada', 'The Canadian League');
INSERT INTO League VALUES (2, 'USA', 'American Volleyball Open');
INSERT INTO League VALUES (3, 'Italy', 'Superlega');
INSERT INTO League VALUES (4, 'Brazil', 'Super League');
INSERT INTO League VALUES (5, 'France', 'Pro A');

-- SID, Stadium name
INSERT INTO Stadium VALUES (1,'Stadium One');
INSERT INTO Stadium VALUES (2, 'Sydney Cricket Ground');
INSERT INTO Stadium VALUES (3, 'Maracana Stadium');
INSERT INTO Stadium VALUES (4, 'Staples Center');
INSERT INTO Stadium VALUES (5, 'Accor Arena');

-- SID, city, address
INSERT INTO StadiumAddress VALUES (1, 'Vancouver','1234 Numbers Street');
INSERT INTO StadiumAddress VALUES (2, 'Sydney Cricket Ground', 'Driver Ave');
INSERT INTO StadiumAddress VALUES (3, 'Maracana Stadium',  'Maracana Road');
INSERT INTO StadiumAddress VALUES (4, 'Staples Center', '1111 S Figueroa Street');
INSERT INTO StadiumAddress VALUES (5, 'Accor Arena',  'Bercy Boulevard');

-- City, address, Stadium name
INSERT INTO StadiumName VALUES ('Vancouver', '1234 Numbers Street','Stadium One');
INSERT INTO StadiumName VALUES ('Sydney', 'Driver Ave', 'Sydney Cricket Ground');
INSERT INTO StadiumName VALUES ('Rio de Janeiro', 'Maracana Road', 'Maracana Stadium');
INSERT INTO StadiumName VALUES ('Los Angeles', '1111 S Figueroa Street', 'Staples Center');
INSERT INTO StadiumName VALUES ('Paris', 'Bercy Boulevard', 'Accor Arena');

-- RID, name, Salary
INSERT INTO Ref VALUES ('1', 'Charlotte Leclerc', '45000');
INSERT INTO Ref VALUES ('2', 'Sam Sedin', '67000');
INSERT INTO Ref VALUES ('3', 'Michael Jordan', '400000');
INSERT INTO Ref VALUES ('4', 'Eric Demko', '23000');
INSERT INTO Ref VALUES ('5', 'Pam Fleming', '50000');
		
-- Years Coaching, CID		
INSERT INTO Experience VALUES ('4', 'Beginner');
INSERT INTO Experience VALUES ('8', 'Veteran');
INSERT INTO Experience VALUES ('17', 'Veteran');
INSERT INTO Experience VALUES ('1', 'Beginner');
INSERT INTO Experience VALUES ('5', 'Intermediate');

-- CID, name, Years Coaching
INSERT INTO HeadCoach VALUES ('1', 'Jason Jenkins', '4');
INSERT INTO HeadCoach VALUES ('2', 'Mary Smith', '8');
INSERT INTO HeadCoach VALUES ('3', 'Geoffrey Green', '17');
INSERT INTO HeadCoach VALUES ('4', 'Fletcher Flin', '1'); 
INSERT INTO HeadCoach VALUES ('5', 'Tiffany Thompson', '5'); 

-- CID, Experience
INSERT INTO CoachExperience VALUES ('1', 'Beginner');
INSERT INTO CoachExperience VALUES ('2', 'Veteran');
INSERT INTO CoachExperience VALUES ('3', 'Veteran');
INSERT INTO CoachExperience VALUES ('4', 'Beginner');
INSERT INTO CoachExperience VALUES ('5', 'Intermediate');

-- TID, name, Record (Win Numbers), League ID, Couch ID
INSERT INTO Team VALUES ('1', 'Dolphins', '5', '1', '2');
INSERT INTO Team VALUES ('2', 'Volley ViKings', '9', '1', '4');
INSERT INTO Team VALUES ('3', 'Dolphins', '3', '1', '2');
INSERT INTO Team VALUES ('4', 'Templeton Titans', '4', '2', '5');
INSERT INTO Team VALUES ('5', 'Apex14U', '1', '2', '1' );



-- GID, Stadium ID, Winning Team ID, Losing Team ID, LID
-- Both teams need to be in the same league to play the game
-- Stadium doesnt have to be in the same country as league is in 
-- (but it makes sense to be)
INSERT INTO Game VALUES ('1', '1', '1', '2', '1');
INSERT INTO Game VALUES ('2', '1', '3', '1', '1');
INSERT INTO Game VALUES ('3', '1', '2', '3', '2');
INSERT INTO Game VALUES ('4', '1', '1', '2', '1');
INSERT INTO Game VALUES ('5', '2', '4', '5', '2');

-- Set #, Score Winner Score, Loser Score, GID
INSERT INTO GameSet Values ('1', '1', '25', '20');
INSERT INTO GameSet Values ('2', '1', '25', '20');
INSERT INTO GameSet Values ('1', '2', '15', '19');
INSERT INTO GameSet Values ('2', '2', '15', '16');
INSERT INTO GameSet Values ('3', '2', '15', '13');

-- RID, LID
INSERT INTO WorksFor VALUES (1, 1);
INSERT INTO WorksFor VALUES (1, 2);
INSERT INTO WorksFor VALUES (1, 3);
INSERT INTO WorksFor VALUES (1, 4);
INSERT INTO WorksFor VALUES (1, 5);
INSERT INTO WorksFor VALUES (2, 5);
INSERT INTO WorksFor VALUES (3, 2);
INSERT INTO WorksFor VALUES (4, 2);

-- RID GID
INSERT INTO RefsFor VALUES (1, 1);
INSERT INTO RefsFor VALUES (2, 2);
INSERT INTO RefsFor VALUES (3, 3);
INSERT INTO RefsFor VALUES (4, 4);
INSERT INTO RefsFor VALUES (5, 5);



-- PID, Name, Weight in kg, Height in cm, Digs, Jersey Number, TID
INSERT INTO Libero VALUES (1, 'Bob Smith', 60, 160, 10, 14, 1);
INSERT INTO Libero VALUES  (2, 'Steve Mathhews', 65, 170, 20, 10, 2);
INSERT INTO Libero VALUES  (3, 'Frank Manger', 60, 170, 15, 1, 3);
INSERT INTO Libero VALUES  (4, 'Brook Pol', 50, 150, 30, 8, 4);
INSERT INTO Libero VALUES  (5, 'Mor West', 55, 180, 22, 2, 5);

-- PID (from Libero), BMI 
INSERT INTO LiberoBMI VALUES (1, 23.4);
INSERT INTO LiberoBMI VALUES (2, 22.5);
INSERT INTO LiberoBMI VALUES (3, 20.8);
INSERT INTO LiberoBMI VALUES (4, 22.2);
INSERT INTO LiberoBMI VALUES (5, 17.0);



-- Height Weight BMI
INSERT INTO BMI VALUES (160, 60, 23.4);
INSERT INTO BMI VALUES (170, 65, 22.5);
INSERT INTO BMI VALUES (170, 60, 20.8);
INSERT INTO BMI VALUES (150, 50, 22.2);
INSERT INTO BMI VALUES (180, 55, 17.0);


-- PID, Name,  Weight in kg, Height in cm, Aces, Jersey Number, TID
INSERT INTO ServerSpecialist VALUES (1, 'Jarvis Gibbs', 60, 160, 18, 14, 1);
INSERT INTO ServerSpecialist VALUES  (2, 'Jonah Muir', 65, 170, 28, 10, 2);
INSERT INTO ServerSpecialist VALUES  (3, 'Waqar Herrera', 60, 170, 22, 11, 3);
INSERT INTO ServerSpecialist VALUES  (4, 'Reagan Castro', 50, 150, 15, 18, 4);
INSERT INTO ServerSpecialist VALUES  (5, 'Gianni Snider', 55, 180, 21, 12, 5);

-- PID (from ServerSpecialist), BMI 
INSERT INTO ServerSpecialistBMI VALUES (1, 23.4);
INSERT INTO ServerSpecialistBMI VALUES (2, 22.5);
INSERT INTO ServerSpecialistBMI VALUES (3, 20.8);
INSERT INTO ServerSpecialistBMI VALUES (4, 22.2);
INSERT INTO ServerSpecialistBMI VALUES (5, 17.0);



-- PID, Name, Weight in kg, Height in cm, Kills, Aces, Blocks, Jersey Number, TID
INSERT INTO OutsideHitter VALUES (1, 'Elliot Metcalfe', 60, 160, 20, 10, 15, 21, 1);
INSERT INTO OutsideHitter VALUES  (2, 'Arlo Conrad', 65, 170, 18, 15, 10, 20, 2);
INSERT INTO OutsideHitter VALUES  (3, 'Adyan Herman', 60, 170, 14, 16, 18, 25, 3);
INSERT INTO OutsideHitter VALUES  (4, 'Jarred Jensen', 50, 150, 12, 14, 13, 28, 4);
INSERT INTO OutsideHitter VALUES  (5, 'Stan Dougherty', 55, 180, 21, 11, 19, 24, 5);

-- PID (from OutsideHitter), BMI 
INSERT INTO OutsideHitterBMI VALUES (1, 23.4);
INSERT INTO OutsideHitterBMI VALUES (2, 22.5);
INSERT INTO OutsideHitterBMI VALUES (3, 20.8);
INSERT INTO OutsideHitterBMI VALUES (4, 22.2);
INSERT INTO OutsideHitterBMI VALUES (5, 17.0);


-- PID, Name, Weight in kg, Height in cm, Set Attempts, Set SuccessRate Jersey Number, TID
INSERT INTO Setter VALUES (1, 'Steffan Connelly', 60, 160, 92, 0.78, 32, 1);
INSERT INTO Setter VALUES  (2, 'Remy Davidson', 65, 170, 80, 0.82 ,31, 2);
INSERT INTO Setter VALUES  (3, 'Damian Mclaughlin', 60, 170, 102, 0.91 ,34, 3);
INSERT INTO Setter VALUES  (4, 'Jeanne Mullins', 50, 150, 40, 0.67 ,35, 4);
INSERT INTO Setter VALUES  (5, 'Amir Ridley', 55, 180, 74, 0.71 ,39, 5);

-- PID (from Setter), BMI 
INSERT INTO SetterBMI VALUES (1, 23.4);
INSERT INTO SetterBMI VALUES (2, 22.5);
INSERT INTO SetterBMI VALUES (3, 20.8);
INSERT INTO SetterBMI VALUES (4, 22.2);
INSERT INTO SetterBMI VALUES (5, 17.0);



-- PID, Name, Weight in kg, Height in cm,  Blocks, Jersey Number, TID
INSERT INTO MiddleBlocker VALUES (1, 'Hashim Hodges', 60, 160, 22, 32, 1);
INSERT INTO MiddleBlocker VALUES  (2, 'Vijay Spence', 65, 170, 21, 31, 2);
INSERT INTO MiddleBlocker VALUES  (3, 'Dominic Levine', 60, 170, 12, 34, 3);
INSERT INTO MiddleBlocker VALUES  (4, 'Blane Davis', 50, 150, 35, 35, 4);
INSERT INTO MiddleBlocker VALUES  (5, 'Sal Goodman', 55, 180, 31, 39, 5);

-- PID (from MiddleBlocker), BMI 
INSERT INTO MiddleBlockerBMI VALUES (1, 23.4);
INSERT INTO MiddleBlockerBMI VALUES (2, 22.5);
INSERT INTO MiddleBlockerBMI VALUES (3, 20.8);
INSERT INTO MiddleBlockerBMI VALUES (4, 22.2);
INSERT INTO MiddleBlockerBMI VALUES (5, 17.0);
