
drop table Plays;
drop table WorksFor;
drop table RefsFor;
drop table gameSet;

drop table game;

drop table stadiumaddress;
drop table stadium;
drop table stadiumname;
drop table ref;
drop table experience;
drop table coachexperience;
drop table headcoach;

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
            
GRANT SELECT ON Country TO public;


CREATE TABLE League
	(LID INTEGER,
	Cname CHAR(20) NOT NULL,
	Name CHAR(40),
    PRIMARY KEY (LID),
    FOREIGN KEY (Cname) REFERENCES Country
	ON DELETE CASCADE);
    
GRANT SELECT ON League TO public;

    
CREATE TABLE StadiumAddress
	(SID INTEGER NOT NULL,
    Name CHAR(40) NOT NULL,
    Address CHAR(40) NOT NULL,
    PRIMARY KEY (SID));
    
GRANT SELECT ON StadiumAddress TO public;

CREATE TABLE StadiumName 
	(City CHAR(20) NOT NULL,
    Address CHAR(40) NOT NULL,
    StadiumName CHAR(40) NULL,
    PRIMARY KEY (City, Address));

GRANT SELECT ON StadiumName TO public;

CREATE TABLE Stadium
	(SID INTEGER,
    StadiumName CHAR(40) NULL,
    PRIMARY KEY (SID));
    
    
GRANT SELECT ON Stadium TO public;    

CREATE TABLE Ref
	(RID INTEGER,
	Name CHAR (20) NULL,
    Salary INTEGER,
    PRIMARY KEY (RID));

GRANT SELECT ON Ref to public;

CREATE TABLE HeadCoach
	(CID INTEGER,
    Name Char(20) NULL,
    YearsCoaching INTEGER,
    PRIMARY KEY (CID));
    
GRANT SELECT ON HeadCoach to public;

CREATE TABLE Experience
	(YearsCoaching INTEGER,
    Experience CHAR(20) NULL,
    PRIMARY KEY (YearsCoaching));
    
GRANT SELECT ON EXPERIENCE to public;

CREATE TABLE CoachExperience
	(CID INTEGER,
    EXPERIENCE CHAR(20) NULL,
    PRIMARY KEY (CID));

GRANT SELECT ON CoachExperience to public;

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

GRANT SELECT ON Team to public;

CREATE TABLE Game
	(GID INTEGER NOT NULL,
    SID INTEGER NOT NULL,
    LID INTEGER NOT NULL,
    PRIMARY KEY (GID, SID),
    FOREIGN KEY (SID)
		REFERENCES Stadium(SID)
        ON DELETE CASCADE,
	FOREIGN KEY (LID)
		REFERENCES League
        ON DELETE CASCADE);

GRANT SELECT ON Game to public;

CREATE TABLE gameSet 
	(SetNumber INTEGER NOT NULL,
	WinnerScore INTEGER,
	LoserScore INTEGER,
	GID INTEGER NOT NULL,
	SID INTEGER NOT NULL,
    PRIMARY KEY (SetNumber, GID),
    FOREIGN KEY (GID, SID)
		REFERENCES GAME
        ON DELETE CASCADE);
		

GRANT SELECT ON gameSet to public;

CREATE TABLE WorksFor
	(RID INTEGER NOT NULL,
	LID INTEGER NOT NULL,
	PRIMARY KEY (RID, LID),
	FOREIGN KEY (RID)
		REFERENCES Ref(RID)
		ON DELETE CASCADE,
	FOREIGN KEY (LID)
		REFERENCES League
		ON DELETE SET NULL);

CREATE TABLE RefsFor
	(RID INTEGER NOT NULL,
	GID INTEGER NOT NULL,
	SID INTEGER NOT NULL,
	PRIMARY KEY (RID, GID),
	FOREIGN KEY (GID, SID)
		REFERENCES Game
		ON DELETE CASCADE,
	FOREIGN KEY (RID)
		REFERENCES Ref
		ON DELETE CASCADE);

CREATE TABLE Plays
	(GID INTEGER NOT NULL,
	TID INTEGER NOT NULL,
	SID INTEGER NOT NULL,
	Win CHAR(2) NOT NULL,
	PRIMARY KEY (GID, TID),
	UNIQUE (GID, Win),
	FOREIGN KEY (GID, SID)
		REFERENCES Game
		ON DELETE CASCADE,
	FOREIGN KEY (TID)
		REFERENCES Team
		ON DELETE CASCADE);


CREATE TABLE Libero
    (PID INTEGER NOT NULL,
    NAME CHAR(20),
    Weight INTEGER,
    Height INTEGER,
    Digs INTEGER,
    JerseyNumber INTEGER,
    TID INTEGER NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (TID)
        REFERENCES Team
        ON DELETE CASCADE);

GRANT SELECT ON Libero to public;    


CREATE TABLE ServerSpecialist
    (PID INTEGER NOT NULL,
    NAME CHAR(20),
    Weight INTEGER,
    Height INTEGER,
    Aces INTEGER,
    JerseyNumber INTEGER,
    TID INTEGER NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (TID)
        REFERENCES Team
        ON DELETE CASCADE);

GRANT SELECT ON ServerSpecialist to public;    


CREATE TABLE OutsideHitter
    (PID INTEGER NOT NULL,
    NAME CHAR(20),
    Weight INTEGER,
    Height INTEGER,
    Kills INTEGER,
    Aces INTEGER,
    Blocks INTEGER,
    JerseyNumber INTEGER,
    TID INTEGER NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (TID)
        REFERENCES Team
        ON DELETE CASCADE);

GRANT SELECT ON OutsideHitter to public;    



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
    

GRANT SELECT ON Setter to public;


CREATE TABLE MiddleBlocker
    (PID INTEGER NOT NULL,
    NAME CHAR(20),
    Weight INTEGER,
    Height INTEGER,
    Blocks INTEGER,
    JerseyNumber INTEGER,
    TID INTEGER NOT NULL,
    PRIMARY KEY (PID),
    FOREIGN KEY (TID)
        REFERENCES Team(TID)
        ON DELETE CASCADE);

GRANT SELECT ON MiddleBlocker to public;        




INSERT INTO Country VALUES('Canada', '38010000');
INSERT INTO Country VALUES('USA', '329500000');
INSERT INTO Country VALUES('Italy', '59550000');
INSERT INTO Country VALUES('Brazil', '212600000');
INSERT INTO Country VALUES('France', '67390000');

INSERT INTO League VALUES (1, 'Canada', 'The Canadian League');
INSERT INTO League VALUES (2, 'USA', 'American Volleyball Open');
INSERT INTO League VALUES (3, 'Italy', 'Superlega');
INSERT INTO League VALUES (4, 'Brazil', 'Super League');
INSERT INTO League VALUES (5, 'France', 'Pro A');

INSERT INTO StadiumAddress VALUES ('1', 'Vancouver','1234 Numbers Street');
INSERT INTO StadiumAddress VALUES ('2', 'Sydney Cricket Ground', 'Driver Ave');
INSERT INTO StadiumAddress VALUES ('3', 'Maracana Stadium',  'Maracana Road');
INSERT INTO StadiumAddress VALUES ('4', 'Staples Center', '1111 S Figueroa Street');
INSERT INTO StadiumAddress VALUES ('5', 'Accor Arena',  'Bercy Boulevard');


INSERT INTO StadiumName VALUES ('Vancouver', '1234 Numbers Street','Stadium One');
INSERT INTO StadiumName VALUES ('Sydney', 'Driver Ave', 'Sydney Cricket Ground');
INSERT INTO StadiumName VALUES ('Rio de Janeiro', 'Maracana Road', 'Maracana Stadium');
INSERT INTO StadiumName VALUES ('Los Angeles', '1111 S Figueroa Street', 'Staples Center');
INSERT INTO StadiumName VALUES ('Paris', 'Bercy Boulevard', 'Accor Arena');



		
		
		