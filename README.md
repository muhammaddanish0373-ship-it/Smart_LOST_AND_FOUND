# Smart Lost & Found System

Campus Lost & Found application built with Java Swing using **MVC architecture**.

## Project Structure

```
src/smartlostfound/
├── Main.java                          # Application entry point
├── model/                             # MODEL: data & business logic
│   ├── User.java, Item.java, ...
│   ├── repository/FileRepository.java # File I/O persistence
│   └── service/MatchService.java      # Auto-matching engine
├── view/                              # VIEW: Swing UI only
│   ├── MainFrame.java, LoginView.java, DashboardView.java, ...
└── controller/                        # CONTROLLER: handles user actions
    ├── AppController.java, LoginController.java, ...
```

## How to Run

### Option 1: Command Line

```powershell
cd "C:\Users\IDEAL COMPUTERS\Documents\SmartLostAndFound"
mkdir out -Force
Get-ChildItem -Path src -Filter *.java -Recurse | ForEach-Object { $_.FullName } | ForEach-Object { javac -d out -sourcepath src $_ }
java -cp out smartlostfound.Main
```

### Option 2: Eclipse / IntelliJ

1. **File → Open** the `SmartLostAndFound` folder
2. Set `src` as source folder if prompted
3. Run `smartlostfound.Main`

## Test Demo

1. Login as U001 (Ali) → Report Lost: Wallet, Black Leather, Library
2. Logout → Login as U002 (Sara) → Report Found: Wallet, Black, Library
3. View System Matches → Refresh → see 100% match
4. My Notifications → Refresh → both users get alerts

## MVC Flow

User Action → **View** → **Controller** → **Model** → **Controller** → **View** (update)

Data files (`users.txt`, `lost_reports.txt`, etc.) are created automatically in the project root on first run.
