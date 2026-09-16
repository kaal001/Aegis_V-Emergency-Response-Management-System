# Emergency Response Management System
## Java Swing OOP Lab Project

**Team Name:** ArrayOf5  
**Language:** Java 26  
**GUI:** Java Swing  
**Data Storage:** Java Serialization (`.dat` files)  
**Project Type:** Desktop Management System  

---

## 1. Project Overview

The **Emergency Response Management System** is a Java Swing desktop application designed to manage emergency reports and coordinate suitable response teams.

The system provides separate interfaces for normal users and administrators. Users can register, log in, report emergencies, view their reported emergencies, and manage their profiles. Administrators can manage emergencies, response teams, users, assignments, emergency history, and system statistics.

The project demonstrates core **Object-Oriented Programming (OOP)** concepts together with Java Swing GUI development, event handling, JTable-based CRUD operations, validation, file handling, and persistent storage.

The application does **not** use a database, JDBC, web API, or external UI framework. Persistent data is stored using Java Serialization.

---

## 2. Main Objectives

The main objectives of the project are:

- Provide a simple interface for reporting emergencies.
- Classify emergencies by type and priority.
- Maintain a list of emergency-response teams.
- Assign suitable and available teams to emergencies.
- Track emergency and team status.
- Provide administrator CRUD operations.
- Store application data persistently.
- Demonstrate OOP and Java Swing concepts learned in the course.

---

## 3. Main Features

### Authentication

- Separate User and Admin access.
- User registration.
- Login validation.
- Logout confirmation.
- Exit confirmation.

### User Features

- User dashboard.
- Report a new emergency.
- Select emergency type and priority.
- Enter detailed location and description.
- View previously reported emergencies.
- View user profile.

### Admin Features

The Admin Management screen uses a `JTabbedPane` with four tabs:

```text
Emergencies
Response Teams
Users
Assignments
```

Each management tab supports:

- Add
- Update
- Delete
- Search
- Input validation
- Delete confirmation
- JTable-based data display

Additional admin features include:

- Emergency history
- Reports and statistics
- Team assignment
- Team availability management
- Emergency status management

---

## 4. Emergency Types

The application supports the following emergency types:

1. Medical Emergency
2. Fire Emergency
3. Road Accident
4. Security Emergency
5. Natural Disaster
6. Gas Leak
7. Electrical Emergency
8. Building Collapse
9. Industrial Accident
10. Missing Person
11. Water / Flood Emergency
12. Other / Custom

### Emergency-to-Team Mapping

| Emergency Type | Primary Response Team |
|---|---|
| Medical Emergency | Ambulance |
| Fire Emergency | Fire |
| Road Accident | Rescue |
| Security Emergency | Security |
| Natural Disaster | Rescue |
| Gas Leak | Fire |
| Electrical Emergency | Rescue |
| Building Collapse | Rescue |
| Industrial Accident | Rescue |
| Missing Person | Security |
| Water / Flood Emergency | Rescue |
| Other / Custom | No fixed primary team |

A team is only assigned when it is both **suitable and available**.

---

## 5. Priority Levels

Every emergency has one of four priority levels:

```text
Critical
High
Medium
Low
```

Pending emergencies can be ordered according to priority so higher-priority emergencies can be handled first in assignment-related views.

---

## 6. Emergency Status Workflow

The system uses the following main status flow:

```text
PENDING
   ↓
ASSIGNED
   ↓
IN_PROGRESS
   ↓
RESOLVED
```

An active emergency can also be cancelled according to the implemented status rules.

When a team is assigned:

```text
Emergency → ASSIGNED
Team      → BUSY
```

When an active assignment is removed through the supported assignment workflow:

```text
Assignment → Removed
Emergency  → PENDING
Team       → AVAILABLE
```

The business rules are handled by `EmergencyManager`.

---

## 7. Assignment Management

The **Assignments** tab is a full management tab rather than only an assignment workflow screen.

It supports:

- Add assignment
- Update assignment
- Delete assignment
- Search assignments
- Assignment ID
- Emergency ID
- Team ID
- Assigned time
- Notes

### Assignment Validation

A new assignment requires:

1. A valid emergency.
2. The emergency to be eligible for assignment.
3. A valid response team.
4. The team to be available.
5. The team to be suitable for the emergency.
6. A valid assigned time.

The GUI communicates with `EmergencyManager` rather than directly changing the core data lists.

---

## 8. OOP Design

The project demonstrates the four major pillars of OOP.

### Encapsulation

Model data is stored in private fields and accessed through methods such as getters and setters.

### Inheritance

The project contains two main inheritance structures:

```text
Person
├── User
└── Admin
```

and

```text
ResponseTeam
├── AmbulanceTeam
├── FireTeam
├── RescueTeam
└── SecurityTeam
```

### Polymorphism

`ResponseTeam` defines common team behaviour while individual team subclasses override methods such as `respondToEmergency()`.

This allows a response team to be treated through a common parent reference while still using team-specific behaviour.

### Abstraction

`ResponseTeam` is an abstract class containing common response-team data and behaviour.

---

## 9. Other Java Concepts Demonstrated

The project also demonstrates:

- Default constructors
- Parameterized constructors
- Constructor overloading
- `this`
- `super`
- `static`
- `final`
- `public`
- `private`
- `protected`
- `ArrayList`
- Enums
- String manipulation
- Conditional statements
- Loops
- Methods
- Exception handling
- File handling
- Object serialization

---

## 10. Project Architecture

The project follows a simple separation-of-concerns structure:

```text
GUI
 ↓
EmergencyManager
 ↓
Model Objects
 ↓
FileManager
 ↓
.dat Files
```

### GUI Layer

Responsible for:

- Displaying information
- Reading user input
- Button actions
- Table interaction
- Validation messages
- Navigation

### Manager Layer

Responsible for:

- Business rules
- CRUD logic
- Searching
- Team suitability
- Assignment rules
- Status changes
- Statistics
- Save/load coordination

### Model Layer

Responsible for storing entity data and implementing the OOP structure.

### Utility Layer

Responsible for file persistence through Java Serialization.

---

## 11. Project Structure

```text
Emergency Response Management System/
│
├── data/
│   ├── users.dat
│   ├── emergencies.dat
│   ├── teams.dat
│   └── assignments.dat
│
├── installer/
├── out/
├── packaged/
├── release/
│
└── src/
    ├── enums/
    │   ├── EmergencyStatus.java
    │   ├── EmergencyType.java
    │   ├── Priority.java
    │   └── TeamType.java
    │
    ├── gui/
    │   ├── AdminDashboard.java
    │   ├── EmergencyHistoryFrame.java
    │   ├── EmergencyManagementFrame.java
    │   ├── LoginFrame.java
    │   ├── MainFrame.java
    │   ├── ManagementTabbedPanel.java
    │   ├── MyEmergenciesFrame.java
    │   ├── RegistrationFrame.java
    │   ├── ReportEmergencyFrame.java
    │   ├── ReportStatisticsFrame.java
    │   ├── ResponseTeamManagementFrame.java
    │   ├── TeamAssignmentFrame.java
    │   ├── UserDashboard.java
    │   └── UserManagementFrame.java
    │
    ├── manager/
    │   └── EmergencyManager.java
    │
    ├── model/
    │   ├── Admin.java
    │   ├── AmbulanceTeam.java
    │   ├── Assignment.java
    │   ├── Emergency.java
    │   ├── FireTeam.java
    │   ├── Person.java
    │   ├── RescueTeam.java
    │   ├── ResponseTeam.java
    │   ├── SecurityTeam.java
    │   └── User.java
    │
    ├── util/
    │   └── FileManager.java
    │
    └── Main.java
```

---

## 12. Class Responsibilities

### Model Classes

**Person.java**  
Base class for common person information.

**User.java**  
Represents registered system users.

**Admin.java**  
Represents administrators and provides admin-related identity information.

**Emergency.java**  
Stores emergency ID, reporter, type, priority, location, description, date/time, status, and assigned team information.

**ResponseTeam.java**  
Abstract base class for all response teams.

**AmbulanceTeam.java**  
Represents ambulance response teams.

**FireTeam.java**  
Represents fire response teams.

**RescueTeam.java**  
Represents rescue response teams.

**SecurityTeam.java**  
Represents security response teams.

**Assignment.java**  
Stores the relationship between an emergency and a response team, including assignment time and notes.

### Manager Class

**EmergencyManager.java**  
Central business-logic class. It manages emergencies, users, teams, assignments, searching, validation, status transitions, suitability checks, statistics, and persistence coordination.

### Utility Class

**FileManager.java**  
Handles reading and writing serialized application data.

### Main Application Classes

**Main.java**  
Application entry point.

**MainFrame.java**  
Main Swing window and screen navigation controller using `CardLayout`.

**ManagementTabbedPanel.java**  
Provides the Admin Management `JTabbedPane`.

---

## 13. Swing Components Used

The project uses standard Java Swing components, including:

- `JFrame`
- `JPanel`
- `JLabel`
- `JTextField`
- `JTextArea`
- `JButton`
- `JTable`
- `DefaultTableModel`
- `JScrollPane`
- `JComboBox`
- `JTabbedPane`
- `JOptionPane`
- `JDialog`
- `JMenuBar`
- `JMenu`
- `JMenuItem`

### GUI Features

- Responsive main window sizing.
- Card-based navigation.
- Sidebar navigation.
- Tab-based management.
- Read-only JTable cells where appropriate.
- Search fields.
- Selection-based update/delete operations.
- Confirmation dialogs.
- Informational and warning dialogs.
- Custom modal `JDialog` for detailed information.

---

## 14. Validation and User Feedback

The application validates data before saving or modifying records.

Examples:

- Empty required fields are rejected.
- Invalid selections show warning messages.
- Missing table selection is reported.
- Invalid team assignment is rejected.
- Unavailable teams cannot be assigned.
- Unsuitable teams cannot be assigned.
- Delete operations require confirmation.

The primary feedback mechanism is `JOptionPane`.

---

## 15. Data Persistence

The project uses **Java Serialization** instead of a database.

The following files are used:

```text
data/users.dat
data/emergencies.dat
data/teams.dat
data/assignments.dat
```

This allows data to persist after the application is closed and reopened.

The system can load existing data during startup and save changes after management operations.

---

## 16. Default Demo Data

### Default Admin

```text
Admin ID : A001
Name     : System Administrator
Phone    : 01900000000
Email    : admin@aegis.com
Username : admin
Password : admin123
```

### Default Teams

```text
AT-001  Central Ambulance Team   01711111111
FT-001  Central Fire Team        01722222222
RT-001  Central Rescue Team      01733333333
ST-001  Campus Security Team     01744444444
```

Team records also store:

- Team ID
- Team Name
- Team Type
- Contact Number
- Number of Members
- Availability

---

## 17. Admin Management Tabs

### 17.1 Emergencies

Provides:

```text
Add
Update
Delete
Search
View Details
```

Emergency information is displayed in a JTable.

### 17.2 Response Teams

Provides:

```text
Add
Update
Delete
Search
```

Team availability is tracked and used during assignment.

### 17.3 Users

Provides:

```text
Add
Update
Delete
Search
```

### 17.4 Assignments

Provides:

```text
Add
Update
Delete
Search
```

Assignments use actual emergency/team relationships and are subject to the system's business rules.

---

## 18. User Flow

```text
Application Start
      ↓
Login
      ↓
User Login
      ↓
User Dashboard
      ↓
Report Emergency
      ↓
Enter Details
      ↓
Emergency Saved
      ↓
My Emergencies / Profile
```

---

## 19. Admin Flow

```text
Application Start
      ↓
Login
      ↓
Admin Login
      ↓
Admin Dashboard
      ↓
Management
      ├── Emergencies
      ├── Response Teams
      ├── Users
      └── Assignments
      ↓
History / Reports
```

---

## 20. Example Assignment Flow

```text
Admin opens Assignments
        ↓
Selects Add
        ↓
Selects eligible emergency
        ↓
System finds suitable available teams
        ↓
Admin selects a team
        ↓
Assignment is created
        ↓
Emergency becomes ASSIGNED
        ↓
Team becomes BUSY
        ↓
Data is saved
```

This demonstrates interaction between:

```text
Swing UI
   ↓
EmergencyManager
   ↓
Emergency + ResponseTeam + Assignment
   ↓
FileManager
   ↓
Serialized .dat files
```

---

## 21. Compilation

The project is intended to run with **JDK 26**.

From the project directory:

```bash
javac -d out src/enums/*.java src/model/*.java src/manager/*.java src/util/*.java src/gui/*.java src/Main.java
```

---

## 22. Running the Application

After compilation:

```bash
java -cp out Main
```

Or from IntelliJ IDEA:

```text
Run → Main.java
```

using the configured **Java 26 SDK**.

---

## 23. Demonstration Checklist

For the final demonstration, the recommended sequence is:

1. Start the application.
2. Show the login screen.
3. Demonstrate user registration/login.
4. Report an emergency.
5. Show the user emergency list.
6. Log in as administrator.
7. Open Admin Dashboard.
8. Open Management.
9. Demonstrate the four management tabs.
10. Demonstrate Add, Update, Delete, and Search.
11. Demonstrate validation warnings.
12. Demonstrate delete confirmation.
13. Create an assignment with a suitable available team.
14. Show emergency/team status changes.
15. Open Emergency History.
16. Open Reports/Statistics.
17. Close and reopen the application to demonstrate persistence.

---

## 24. Team Information

### Team Name
**ArrayOf5**

### Group Members and Individual Contributions

| Member | Assigned Contribution |
|---|---|
| **Takbir** | Core system architecture, overall integration, `EmergencyManager`, assignment logic, persistence integration, and overall testing |
| **Shuvo** | Emergency management module, emergency CRUD/search, validation, and emergency history |
| **Ifaz** | Response team management, team CRUD/search, team types, availability, and suitability flow |
| **Ehsan** | User management, registration/login flow, user CRUD/search, user dashboard, and profile screens |
| **Suraiya** | Reports/statistics, UI testing, validation/confirmation flows, and documentation support |

---

## 25. Project Summary

The Emergency Response Management System combines:

- Java 26
- Java Swing
- Object-Oriented Programming
- Inheritance
- Polymorphism
- Abstraction
- Encapsulation
- ArrayList
- Enums
- JTable CRUD
- Search
- Validation
- JOptionPane
- JDialog
- JTabbedPane
- JMenuBar
- CardLayout
- File handling
- Java Serialization
- Persistent `.dat` storage

The final system provides a desktop workflow for reporting emergencies, managing response teams, assigning teams, tracking statuses, viewing history, and generating basic management statistics.

---

**End of README**
