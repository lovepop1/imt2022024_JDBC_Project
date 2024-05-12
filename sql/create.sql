CREATE TABLE Event (
    EventID INT,
    EventName VARCHAR(255) NOT NULL,
    EventType VARCHAR(50) NOT NULL,
    EventStartDate DATE NOT NULL,
	EventEndDate DATE NOT NULL,
    EventDescription TEXT,
    PRIMARY KEY (EventID)
);
CREATE TABLE Participant (
    ParticipantID INT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Phone VARCHAR(20) NOT NULL,
    PRIMARY KEY (ParticipantID)
);
CREATE TABLE Registration (
    RegistrationID INT,
    EventID INT,
    ParticipantID INT,
    RegistrationDate DATE NOT NULL,
    PRIMARY KEY (RegistrationID),
    FOREIGN KEY (EventID) REFERENCES Event(EventID) ON DELETE CASCADE,
    FOREIGN KEY (ParticipantID) REFERENCES Participant(ParticipantID) ON DELETE CASCADE
);
CREATE TABLE Organizer (
    OrganizerID INT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Position VARCHAR(100) NOT NULL,
	EventName VARCHAR(50),
    PRIMARY KEY (OrganizerID)
);
CREATE TABLE Sponsor (
    SponsorID INT,
    SponsorName VARCHAR(100) NOT NULL,
    SponsorType VARCHAR(100),
    ContactPerson VARCHAR(100),
    ContactEmail VARCHAR(255),
    ContactPhone VARCHAR(20),
    PRIMARY KEY (SponsorID)
);
