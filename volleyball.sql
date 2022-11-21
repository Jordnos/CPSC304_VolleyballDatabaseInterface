
drop table Plays;
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
	(Cname CHAR(20) NOT NULL,
    Population INTEGER,
    PRIMARY KEY (Cname));
            
CREATE TABLE League
	(LID INTEGER,
	Cname CHAR(20) NOT NULL,
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
	WinnerScore INTEGER,
	LoserScore INTEGER,
	GID INTEGER NOT NULL,
    PRIMARY KEY (SetNumber, GID),
    FOREIGN KEY (GID)
		REFERENCES GAME(GID)
        ON DELETE CASCADE);
		

CREATE TABLE WorksFor
	(CID INTEGER NOT NULL,
	LID INTEGER NOT NULL,
	PRIMARY KEY (CID, LID),
	FOREIGN KEY (CID)
		REFERENCES HeadCoach
		ON DELETE CASCADE,
	FOREIGN KEY (LID)
		REFERENCES League
		ON DELETE SET NULL);

CREATE TABLE RefsFor
	(RID INTEGER NOT NULL,
	GID INTEGER NOT NULL,
	PRIMARY KEY (RID, GID),
	FOREIGN KEY (RID)
		REFERENCES Ref
		ON DELETE SET NULL,
    FOREIGN KEY (GID)
        REFERENCES Game(GID)
        ON DELETE CASCADE);

-- Game to Team relation is many to many in our schema
-- since a game GID belongs to a single team TID (which are connected through Plays)
CREATE TABLE Plays
	(GID INTEGER NOT NULL,
	TID INTEGER NOT NULL,
	Win CHAR(2) NOT NULL,
	PRIMARY KEY (GID, TID),
	UNIQUE (GID, Win),
	FOREIGN KEY (TID)
		REFERENCES Team
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
    PRIMARY KEY (PID),
    FOREIGN KEY (TID)
        REFERENCES Team(TID)
        ON DELETE CASCADE);

-- Weight in kg, Height in cm
CREATE TABLE BMI
    (Height INTEGER NOT NULL,
    Weight INTEGER NOT NULL,
    BMI DECIMAL(2,2) NOT NULL,
    PRIMARY KEY(Height, Weight));

-- PID refers to Libero PID
CREATE TABLE LiberoBMI
    (PID INTEGER,
    BMI DECIMAL(2,2) NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (PID)
        REFERENCES Libero(PID)
        ON DELETE CASCADE);

CREATE TABLE ServerSpecialistBMI
    (PID INTEGER,
    BMI DECIMAL(2,2) NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (PID)
        REFERENCES ServerSpecialist(PID)
        ON DELETE CASCADE);


CREATE TABLE OutsideHitterBMI
    (PID INTEGER,
    BMI DECIMAL(2,2) NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (PID)
        REFERENCES OutsideHitter(PID)
        ON DELETE CASCADE);


CREATE TABLE SetterBMI
    (PID INTEGER,
    BMI DECIMAL(2,2) NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (PID)
        REFERENCES Setter(PID)
        ON DELETE CASCADE);

CREATE TABLE MiddleBlockerBMI
    (PID INTEGER,
    BMI DECIMAL(2,2) NOT NULL,
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
INSERT INTO GameSet Values ('1', '25', '20', '1');
INSERT INTO GameSet Values ('2', '25', '20', '1');
INSERT INTO GameSet Values ('1', '25', '19', '2');
INSERT INTO GameSet Values ('2', '25', '16', '2');
INSERT INTO GameSet Values ('3', '15', '13', '2');

-- RID, LID
INSERT INTO WorksFor VALUES (1, 1);
INSERT INTO WorksFor VALUES (2, 2);
INSERT INTO WorksFor VALUES (4, 5);
INSERT INTO WorksFor VALUES (4, 2);
INSERT INTO WorksFor VALUES (3, 2);

-- RID GID
INSERT INTO RefsFor VALUES (1, 1);
INSERT INTO RefsFor VALUES (2, 2);
INSERT INTO RefsFor VALUES (3, 3);
INSERT INTO RefsFor VALUES (4, 4);
INSERT INTO RefsFor VALUES (5, 5);

-- INSERT INTO Plays VALUES (1, 1, 'W');
-- INSERT INTO Plays VALUES (1, 3, 'L');
-- INSERT INTO Plays VALUES (2, 5, 'L');
-- INSERT INTO Plays VALUES (2, 4, 'W');
-- INSERT INTO Plays VALUES (3, 2, 'W');


-- -- Weight in kg, Height in cm
-- INSERT INTO Libero VALUES (1, 'Bob Smith', 60, 160, 10, 14, 1);
-- INSERT INTO Libero VALUES  (2, 'Steve Mathhews', 65, 170, , 14, 2)
-- INSERT INTO Libero VALUES  (3, 'Bob Smith', 100, 80, 10, 14, 3)
-- INSERT INTO Libero VALUES  (4, 'Bob Smith', 100, 80, 10, 14, 4)
-- INSERT INTO Libero VALUES  (5, 'Bob Smith', 100, 80, 10, 14, 5)

-- INSERT INTO LiberoBMI VALUES (1, 1)

