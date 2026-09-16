# Aegis_V — Emergency Response Management System

A desktop-based **Emergency Response Management System** built with **Java 26** and **Java Swing**.

I built this as a practice project to improve my understanding of Java OOP, Swing GUI development, file handling, data persistence, and basic application architecture. Instead of making only a simple CRUD application, I wanted to build a project where different parts of the system are connected through actual rules and workflow.

The basic idea is:

```text
User reports an emergency
        ↓
Emergency is created as PENDING
        ↓
Admin reviews it
        ↓
Required response team is determined
        ↓
Available suitable team is assigned
        ↓
Emergency becomes ASSIGNED
        ↓
Emergency moves to IN_PROGRESS
        ↓
Emergency is RESOLVED / CANCELLED
        ↓
Assigned team becomes AVAILABLE again
        ↓
Dashboard and reports are updated
```


## Features

### User

- User registration and login
- Report an emergency
- Select emergency type and priority
- Enter detailed location and description
- View personal emergency records
- Search and filter personal emergencies
- View emergency details

### Admin

- Separate admin login
- Dashboard with system statistics
- View and manage all emergencies
- Search and filter emergencies
- Update emergency priority
- Update emergency status
- View emergency details
- Assign response teams
- Add, update, and delete response teams
- Search and filter teams
- View emergency history
- View reports and statistics

### Emergency Types

The system currently supports:

- Medical Emergency
- Fire Emergency
- Road Accident
- Security Emergency
- Natural Disaster

The current team mapping is:

```text
Medical Emergency    → Ambulance Team
Fire Emergency       → Fire Team
Road Accident        → Rescue Team
Security Emergency   → Security Team
Natural Disaster     → Rescue Team
```

### Priority

Each emergency can be:

- Critical
- High
- Medium
- Low

Emergency lists are displayed using priority-based sorting:

```text
Critical → High → Medium → Low
```

### Response Teams

The response team hierarchy is:

```text
ResponseTeam
├── AmbulanceTeam
├── FireTeam
├── RescueTeam
└── SecurityTeam
```

Each team contains information such as:

- Team ID
- Team Name
- Team Type
- Contact Number
- Number of Members
- Availability

A team can be **Available** or **Busy**.

### Team Assignment

The system first determines the required team type based on the emergency type and then shows suitable teams that are currently available.

Example:

```text
Road Accident
      ↓
Required Team = Rescue
      ↓
Find available Rescue Teams
      ↓
Admin selects a team
      ↓
Assignment is created
      ↓
Emergency = ASSIGNED
      ↓
Team = BUSY
```

When an emergency is resolved or cancelled, the assigned team becomes available again.

## Emergency Status Workflow

The main status flow is:

```text
PENDING
   ↓
ASSIGNED
   ↓
IN_PROGRESS
   ↓
RESOLVED
```

Cancellation is also supported where appropriate, and the project prevents invalid status transitions.

## Tech Stack

- **Java 26**
- **Java Swing**
- **Java Serialization**
- **ArrayList**
- **Enums**
- **File Handling**
- **Object-Oriented Programming**

The project does **not** use a database, JDBC, REST API, or external UI framework.

## OOP Concepts Used

The project was built to practice core OOP concepts in a complete application.

### Encapsulation

Private fields with getter/setter methods are used to control access to object data.

### Inheritance

```text
Person
├── User
└── Admin
```

and:

```text
ResponseTeam
├── AmbulanceTeam
├── FireTeam
├── RescueTeam
└── SecurityTeam
```

### Abstraction

`ResponseTeam` is an abstract class containing common response-team information.

### Polymorphism

Different response-team subclasses are handled through the common `ResponseTeam` type.

For example:

```java
ArrayList<ResponseTeam>
```

can contain different response-team objects such as `AmbulanceTeam`, `FireTeam`, `RescueTeam`, and `SecurityTeam`.

### Enums

The project uses enums for:

- `EmergencyType`
- `Priority`
- `EmergencyStatus`
- `TeamType`

## Data Persistence

The project uses **Java Serialization** for local data storage.

The main data files are:

```text
users.dat
emergencies.dat
teams.dat
assignments.dat
```

The application loads these files when starting and saves updated data when changes are made.

This means data remains available after the application is closed and opened again.

## Main Classes

The project is organized around 12 core classes:

```text
Person
User
Admin
Emergency
ResponseTeam
AmbulanceTeam
FireTeam
RescueTeam
SecurityTeam
Assignment
EmergencyManager
FileManager
```

### `EmergencyManager`

This is the main business-logic class. It manages:

- Users and admins
- Emergencies
- Response teams
- Assignments
- Searching and filtering
- Priority updates
- Team suitability
- Team assignment
- Status changes
- Statistics
- Save/load operations

### `FileManager`

Handles Java Serialization and the `.dat` files used for persistent storage.

## GUI

The interface is built entirely with Java Swing.

Main screens include:

```text
Login
Registration
User Dashboard
Report Emergency
My Emergencies
Admin Dashboard
Emergency Management
Response Team Management
Team Assignment
Emergency History
Reports / Statistics
```

The UI uses a custom color theme:

```text
Alabaster Grey  #CFDBD5
Soft Linen      #E8EDDF
Tuscan Sun      #F5CB5C
Carbon Black    #242423
Graphite        #333533
```

## Validation

Basic input validation is implemented throughout the system.

Examples include:

- Required field validation
- Duplicate User ID checking
- Duplicate username checking
- Phone number validation
- Basic email validation
- Password length validation
- Duplicate Team ID checking
- Team member count validation
- Emergency location validation
- Emergency description validation

Some actions are also disabled until a relevant record is selected.

## Project Structure

```text
src/
├── Main.java
│
├── enums/
│   ├── EmergencyType.java
│   ├── Priority.java
│   ├── EmergencyStatus.java
│   └── TeamType.java
│
├── model/
│   ├── Person.java
│   ├── User.java
│   ├── Admin.java
│   ├── Emergency.java
│   ├── ResponseTeam.java
│   ├── AmbulanceTeam.java
│   ├── FireTeam.java
│   ├── RescueTeam.java
│   ├── SecurityTeam.java
│   └── Assignment.java
│
├── manager/
│   └── EmergencyManager.java
│
├── util/
│   └── FileManager.java
│
└── gui/
    ├── LoginFrame.java
    ├── RegistrationFrame.java
    ├── UserDashboard.java
    ├── ReportEmergencyFrame.java
    ├── MyEmergenciesFrame.java
    ├── AdminDashboard.java
    ├── EmergencyManagementFrame.java
    ├── ResponseTeamManagementFrame.java
    ├── TeamAssignmentFrame.java
    ├── EmergencyHistoryFrame.java
    └── ReportStatisticsFrame.java
```

## Running the Project

### IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Configure **JDK 26**.
3. Make sure the project uses the `src` directory correctly.
4. Run `Main.java`.

### JAR

The project can also be built as an executable JAR.

```bash
java -jar "Emergency Response Management System.jar"
```

### Windows Application

The project was also packaged as a standalone Windows application using `jpackage`.

The packaged application can be launched without opening IntelliJ, and the packaged app was tested with the core features and data persistence working after restart.

## Download

### Windows

Download the latest Windows installer from the GitHub Release:

[Download Aegis_V for Windows](../../releases/latest)

> **Note:** This is an unsigned student/portfolio application. Windows SmartScreen may show an "unrecognized app" warning on the first run because the application does not currently have an established publisher reputation.

---

## Example Workflow

A typical flow looks like this:

```text
User Registration
       ↓
User Login
       ↓
Report Emergency
       ↓
Emergency = PENDING
       ↓
Admin Login
       ↓
Admin Reviews Emergency
       ↓
Determine Required Team
       ↓
Find Suitable Available Team
       ↓
Assign Team
       ↓
Emergency = ASSIGNED
       ↓
Team = BUSY
       ↓
Emergency = IN_PROGRESS
       ↓
Emergency = RESOLVED
       ↓
Team = AVAILABLE
```

## What I Learned

This project helped me practice:

- Designing classes around a real-world problem
- Connecting multiple Java classes together
- Inheritance, abstraction, polymorphism, and encapsulation
- Java Swing GUI development
- Multi-screen desktop applications
- `ArrayList`
- Enums
- Exception handling
- File handling
- Java Serialization
- Input validation
- Search and filtering
- Business logic
- Separating business logic from GUI code
- Testing a complete application workflow
- Packaging a Java application for Windows

One of the main things I learned is that writing individual classes is only part of building an application. The more interesting part is making those classes, rules, GUI screens, and data work together as one system.

## Current Limitations

This is a local desktop practice project, so it has some limitations:

- No real database
- No networking
- No cloud backend
- No SMS/email/push notifications
- No GPS or live map integration
- No real-time team tracking
- No multi-user distributed access
- Basic authentication rather than production-level security
- Basic reports using numbers and tables

## Future Improvements

Some improvements I would like to explore in future versions:

- MySQL / PostgreSQL / SQLite integration
- Stronger authentication and password hashing
- Role-based access control
- Web-based version
- Mobile application
- GPS and map integration
- Real-time team tracking
- SMS/email/push notifications
- Intelligent team dispatch based on distance and availability
- Advanced analytics and charts
- Audit logs
- Automated backups
- Better automated testing


## Project Status

**Completed as a practice / academic project.**

The main features have been implemented and tested, including:

- User and admin workflows
- Emergency reporting
- Emergency prioritization
- Emergency status management
- Response team management
- Team assignment
- Search and filtering
- Dashboard statistics
- Reports
- Emergency history
- Data persistence
- Validation
- Swing GUI
- Standalone Windows packaging

There is still room for improvement, especially around databases, security, networking, real-time communication, GPS, analytics, and multi-user access.

## Why I Built It

I wanted this project to be more than a basic:

```text
Add
Edit
Delete
Search
```

management application.

The main thing I wanted to practice was how different parts of an application connect through actual rules.

For example:

```text
Emergency Type
      ↓
Required Team
      ↓
Team Availability
      ↓
Assignment
      ↓
Emergency Status
      ↓
Team Availability
      ↓
Dashboard Statistics
      ↓
Reports
```

That connection between different features was the main reason I chose this project for practice.

## Author

**Takbir Rahman**

Computer Science & Engineering student interested in:

- Software Engineering
- AI / Machine Learning
- Cybersecurity
- Full-Stack Development

This project is part of my learning journey with Java, Object-Oriented Programming, GUI development, and software project building.

## License

This project is mainly intended for **learning, practice, and educational purposes**.

You are free to explore the code and use it as a reference for learning.
