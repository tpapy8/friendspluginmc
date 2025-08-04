# Minecraft Friends Plugin for Paper 1.19.4

## URGENT: How to Get Your Working JAR File

Since Java code must be compiled to bytecode, follow ONE of these methods to get a working JAR:

### Method 1: GitHub Actions (Recommended - Completely Automated)

1. **Create a GitHub Repository:**
   - Go to github.com and create a new repository
   - Name it "minecraft-friends-plugin" or similar

2. **Upload This Project:**
   - Extract this ZIP file
   - Upload all files to your GitHub repository

3. **Create GitHub Actions Workflow:**
   - In your repository, create folder: `.github/workflows/`
   - Create file: `.github/workflows/build.yml`
   - Add this content:

```yaml
name: Build Minecraft Plugin

on:
  push:
    branches: [ main, master ]
  pull_request:
    branches: [ main, master ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3

    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'

    - name: Build with Maven
      run: mvn clean package

    - name: Upload Artifact
      uses: actions/upload-artifact@v3
      with:
        name: FriendsPlugin-JAR
        path: target/FriendsPlugin-1.0.0.jar
```

4. **Get Your JAR:**
   - Push your code to GitHub
   - Go to "Actions" tab in your repository
   - Wait for build to complete (2-3 minutes)
   - Download the artifact containing your JAR file
   - **This JAR is ready to use on your server!**

### Method 2: Online Java Compiler (Alternative)

Use one of these online compilers that support Maven:
- Replit.com (supports full Maven projects)
- GitPod.io (cloud development environment)
- CodeSandbox.io (supports Java/Maven)

Steps:
1. Create account on chosen platform
2. Import this project
3. Run: `mvn clean package`
4. Download the generated JAR from target/ folder

### Method 3: Local Build (If you have Java/Maven installed)

**Prerequisites:**
- Java 17+ JDK
- Maven 3.6+

**Steps:**
1. Extract this ZIP file
2. Open terminal/command prompt in the project folder
3. Run: `mvn clean package`
4. Find your JAR in `target/FriendsPlugin-1.0.0.jar`

## Installation on Your Server

1. **Stop your Paper 1.19.4 server**
2. **Copy the compiled JAR to your `plugins/` folder**
3. **Start your server**
4. **Test the commands:**
   - `/friend add <username>`
   - `/friends`

## Plugin Features

- ✅ Add friends with `/friend add <username>`
- ✅ View friends list with `/friends` (shows count and online status)
- ✅ Persistent storage (survives server restarts)
- ✅ Input validation (prevents duplicates, self-adding, etc.)
- ✅ Permission system
- ✅ Works with Paper 1.19.4

## Why Can't I Provide a Pre-Compiled JAR?

Java source code must be compiled to bytecode (.class files) for the JVM to execute. This requires:
- Java Development Kit (JDK)
- Build tools (Maven/Gradle)
- Access to Paper API dependencies

The methods above handle all of this automatically and give you a working JAR file!

## Support

If you encounter issues:
1. Ensure you're using Paper 1.19.4 (not Bukkit/Spigot)
2. Check server console for error messages
3. Verify the JAR was compiled successfully
4. Make sure the JAR is in the plugins/ folder

Your plugin is ready to work - just follow one of the build methods above!
