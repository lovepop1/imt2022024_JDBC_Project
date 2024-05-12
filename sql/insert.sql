INSERT INTO Event (EventID, EventName, EventType, EventStartDate, EventEndDate, EventDescription)
VALUES
    (1, 'Brush-Up', 'Workshop', '2024-07-10', '2024-07-11', 'A workshop to improve painting skills.'),
    (2, 'Gulp and Gobble', 'Food Festival', '2024-07-15', '2024-07-17', 'A festival showcasing various cuisines and food competitions.'),
    (3, 'League of Fanatics', 'Sports', '2024-07-20', '2024-07-22', 'A sports event featuring various games and competitions.'),
    (4, 'Nritta', 'Dance Competition', '2024-07-25', '2024-07-26', 'A dance competition highlighting various dance forms.'),
    (5, 'Infinitude', 'Cultural Show', '2024-07-30', '2024-08-01', 'A cultural show featuring music, dance, and drama performances.'),
    (6, 'Sargam', 'Music Concert', '2024-08-05', '2024-08-06', 'A musical concert featuring local and guest artists.'),
    (7, 'JAM', 'Literary', '2024-08-10', '2024-08-12', 'A Just-A-Minute competition testing participants’ speaking skills.'),
    (8, 'Battle of Bands', 'Music Competition', '2024-08-15', '2024-08-17', 'A competition where bands battle it out to win the title.'),
    (9, 'Cut to the Chase', 'Film Screening', '2024-08-20', '2024-08-21', 'A film screening event showcasing short films.'),
    (10, 'FIFA', 'Sports', '2024-08-25', '2024-08-27', 'A FIFA tournament for soccer enthusiasts.');

INSERT INTO Participant (ParticipantID, FirstName, LastName, Email, Phone)
VALUES
    (1, 'John', 'Doe', 'john.doe@example.com', '123-456-7890'),
    (2, 'Alice', 'Smith', 'alice.smith@example.com', '987-654-3210'),
    (3, 'Bob', 'Johnson', 'bob.johnson@example.com', '456-789-0123'),
    (4, 'Emily', 'Brown', 'emily.brown@example.com', '789-012-3456'),
    (5, 'Michael', 'Davis', 'michael.davis@example.com', '321-654-0987'),
    (6, 'Sarah', 'Wilson', 'sarah.wilson@example.com', '654-987-0321'),
    (7, 'Emma', 'Taylor', 'emma.taylor@example.com', '987-123-4567'),
    (8, 'Daniel', 'Anderson', 'daniel.anderson@example.com', '123-987-6543'),
    (9, 'Olivia', 'Martinez', 'olivia.martinez@example.com', '654-321-7890'),
    (10, 'William', 'Thompson', 'william.thompson@example.com', '789-456-1230');

INSERT INTO Registration (RegistrationID, EventID, ParticipantID, RegistrationDate)
VALUES
    (1, 1, 1, '2024-07-05'),
    (2, 2, 2, '2024-07-10'),
    (3, 3, 3, '2024-07-15'),
    (4, 4, 4, '2024-07-20'),
    (5, 5, 5, '2024-07-25'),
    (6, 6, 6, '2024-07-30'),
    (7, 7, 7, '2024-08-05'),
    (8, 8, 8, '2024-08-10'),
    (9, 9, 9, '2024-08-15'),
    (10, 10, 10, '2024-08-20');
INSERT INTO Organizer (OrganizerID, FirstName, LastName, Position, EventName)
VALUES
    (1, 'Michael', 'Anderson', 'Organiser', NULL),
    (2, 'Sarah', 'Clark', 'Organiser', NULL),
    (3, 'David', 'Wilson', 'SPOC', 'Gulp and Gobble'),
    (4, 'Emma', 'Lee', 'SPOC', 'Nritta'),
    (5, 'Olivia', 'Taylor', 'SPOC', 'Brush-Up'),
    (6, 'Daniel', 'Brown', 'SPOC', 'Gulp and Gobble'),
    (7, 'William', 'Garcia', 'SPOC', 'League of Fanatics'),
    (8, 'Sophia', 'Rodriguez', 'SPOC', 'Nritta'),
    (9, 'Ethan', 'Martinez', 'SPOC', 'Infinitude'),
    (10, 'Isabella', 'Hernandez', 'SPOC', 'Sargam');

INSERT INTO Sponsor (SponsorID, SponsorName, SponsorType, ContactPerson, ContactEmail, ContactPhone)
VALUES
    (1, 'ABC Company', 'Gold Sponsor', 'John Smith', 'john.smith@abccompany.com', '123-456-7890'),
    (2, 'XYZ Corporation', 'Silver Sponsor', 'Alice Johnson', 'alice.johnson@xyzcorp.com', '987-654-3210'),
    (3, 'PQR Enterprises', 'Bronze Sponsor', 'Bob Williams', 'bob.williams@pqrenterprises.com', '456-789-0123'),
    (4, 'LMN Industries', 'Platinum Sponsor', 'Emily Davis', 'emily.davis@lmnindustries.com', '789-012-3456');