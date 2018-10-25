CREATE TABLE Persons(
  Email varchar(255),
  PersonPassword varchar(255),
  PersonRole varchar(255),
  Primary Key(Email)
)

CREATE TABLE Subjects(
  SubjectId serial,
  SubjectName varchar(255),
  SubjectPassword varchar(255),
  Primary Key(SubjectId)
)

CREATE TABLE Appointments(
  AppointmentId int,
  SubjectKey int,
  AppointmentType int,
  AppointmentTime varchar(255),
  AppointmentDate varchar(255),
  Primary Key(AppointmentId),
  Foreign Key(SubjectKey) references Subjects(SubjectId)
)