# MotorPH Payroll System

## Project Description

The MotorPH Payroll System is a Java-based desktop application designed to manage employee records, authentication, attendance tracking, and payroll computation. The system follows an MVC-inspired layered architecture, separating GUI, controller/service logic, and data access layers for proper separation of concerns.

## Features

- **User login system** with role-based access (Admin, HR, Finance, Employee)
- **Employee management** - add, update, view, and search employee records
- **Attendance recording** - Time-In/Time-Out tracking with CSV storage
- **Payroll computation** - salary calculation with allowances (Rice Subsidy, Phone Allowance, Clothing Allowance)
- **Government contribution tracking** - SSS, PhilHealth, Pag-IBIG deductions
- **Withholding tax calculation** based on Philippine tax brackets
- **Role-based portal navigation** - separate dashboards for each user role
- **Leave request management** - submission and approval workflow
- **CSV-based data storage** for portability and simplicity
- **Password hashing** using BCrypt for security

## System Architecture

### Layer Structure

| Layer | Package | Purpose |
|-------|---------|---------|
| **GUI Layer** | `GUI` | Handles user interface and user interactions |
| **Controller/Service Layer** | `CTRL_SRVS` | Handles business logic and processing |
| **DAO Layer** | `DAO` | Handles CSV file reading and writing |
| **Model Layer** | `OOP` | Contains core classes like Employee, User, Admin, Finance, HR |
| **Utility Layer** | `Utils`, `OOP` | Helper classes for hashing, validation, session management |
| **Adapter Layer** | `Adapters` | Adapter classes for GUI components |

### Key Architectural Decisions

- **Separation of Concerns**: Business logic (payroll calculations) is moved from GUI to PayrollService to follow proper layering
- **DAO Pattern**: All file/CSV operations are handled by the DAO layer (EmployeeDAO, AttendanceDAO, etc.)
- **Service Layer**: Controller/Service classes (PayrollService, FinanceService, LoginService) handle all processing
- **No business logic in GUI**: GUI components only read input, call services, and display results

## Technologies Used

- **Java 21** - Core programming language
- **Maven** - Build tool
- **Java Swing** - GUI framework with AbsoluteLayout
- **CSV files** - Data storage for portability
- **BCrypt (jbcrypt)** - Password hashing (12 rounds)
- **NetBeans IDE** - Development environment

## How to Run

1. Download or clone the repository
2. Open the project in NetBeans
3. Build the project (Maven will handle dependencies)
4. Run the application
5. Login using available credentials

## Sample Credentials

Credentials are stored in CSV files under `src/main/java/CSV/`:

| Role | Credentials File |
|------|-----------------|
| Admin | `CSV/AdminLogin.csv` |
| HR | `CSV/HRLogin.csv` |
| Finance | `CSV/FinanceLogin.csv` |
| Employee | Email/Password in `CSV/EmpData.csv` |

## Key Design Decisions

- **Payroll calculations were moved from GUI to PayrollService** to follow proper layering and separation of concerns
- **DAO layer handles all file operations** - EmpData.csv, AttendanceRecords.csv, login files
- **Password handling uses BCrypt** to avoid re-hashing issues and ensure secure storage
- **PayrollResult class encapsulates all calculation results** for cleaner data flow between service and GUI
- **Validation rules are centralized** using ValidationRule enum and InputValidator class
- **Session timeout management** for security with automatic logout

## Known Limitations

- Attendance-based hourly payroll computation is not fully automated (supports manual hour entry)
- CSV storage limits scalability compared to database systems
- Some GUI components still contain minor computation logic
- Input validation can be further improved for edge cases

## Future Improvements

- Integration with MySQL/PostgreSQL database
- Complete attendance-to-payroll linkage automation
- Improved validation and error handling
- UI/UX enhancements with modern design patterns
- Export functionality for payroll reports (PDF, Excel)
- Audit logging for administrative actions

## Contributors

- Primary Developer: Ira (as noted in source files)
- Development started in Comp. Prog. 2, evolved through OOP and IAS courses
- Documentation assistance: DeepSeek AI